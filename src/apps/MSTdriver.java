package apps;

import structures.*;

import structures.Graph;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class MSTdriver {
	
	public static void main(String[] args) {
		
		try{
		
			Graph G = new Graph("graph2.txt");
			G.print();
			System.out.println("");
			PartialTreeList L = MST.initialize(G);
			Iterator<PartialTree> iter = L.iterator();
			
			while (iter.hasNext()){
				
				PartialTree currPT = iter.next();
				System.out.println(currPT.toString());
			}
			
			System.out.println("");
			ArrayList<PartialTree.Arc> sol = MST.execute(L);
			System.out.println(sol.toString());
		}
		
		catch(Exception e){
			
		}
	}
}