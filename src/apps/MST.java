package apps;

import structures.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class MST {
	
	/**
	 * Initializes the algorithm by building single-vertex partial trees
	 * 
	 * @param graph Graph for which the MST is to be found
	 * @return The initial partial tree list
	 */
	public static PartialTreeList initialize(Graph graph) {
		
		PartialTreeList L = new PartialTreeList();
		
		for (int i = 0; i < graph.vertices.length; i++){
			
			Vertex currRoot = graph.vertices[i];
			PartialTree currPT = new PartialTree(currRoot);
			Vertex.Neighbor currNeighborNode = currRoot.neighbors;
			
			while (currNeighborNode != null){
				
				Vertex currNeighborVertex = currNeighborNode.vertex;
				int currWeight = currNeighborNode.weight;
				PartialTree.Arc currArc = new PartialTree.Arc(currRoot, currNeighborVertex, currWeight);
				currPT.getArcs().insert(currArc);
				currNeighborNode = currNeighborNode.next;
			}
			
			L.append(currPT);
		}
	
		return L;
	}

	/**
	 * Executes the algorithm on a graph, starting with the initial partial tree list
	 * 
	 * @param ptlist Initial partial tree list
	 * @return Array list of all arcs that are in the MST - sequence of arcs is irrelevant
	 */
	public static ArrayList<PartialTree.Arc> execute(PartialTreeList ptlist) {
		
		ArrayList<PartialTree.Arc> MST = new ArrayList<PartialTree.Arc>(1000000);
		
		while (ptlist.size() > 1){
		
			PartialTree currPT1 = ptlist.remove();
			PartialTree.Arc currArc = currPT1.getArcs().deleteMin();

			while (currArc.v2.getRoot() == currPT1.getRoot() || containsArc(MST, currArc)){
				
				currArc = currPT1.getArcs().deleteMin();
			}
			
			MST.add(currArc);
			PartialTree currPT2 = ptlist.removeTreeContaining(currArc.v2);
			currPT1.merge(currPT2);
			ptlist.append(currPT1);
		}
		
		return MST;
	}
	
	private static boolean containsArc (ArrayList<PartialTree.Arc> MST, PartialTree.Arc currArc){
		
		for (int i = 0; i < MST.size(); i++){
			
			if (MST.get(i).equals(currArc)){
				
				return true;
			}
		}
		
		return false;
	}
}
