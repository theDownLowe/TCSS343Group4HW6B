import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* TCSS 343, Dr. Donald Chinn
 * Group 4: Trevor, Kyaw, Henry
 * Homework 6B
 */

/**
 * Computes Dijskstras algorithm using a heap.
 * 
 * @author Trevor Lowe
 * @version 1
 */
public class DijkstraHeapAlg {
	
	public DijkstraHeapAlg(SimpleGraph tripGraph, Vertex start) throws EmptyHeapException {
		DijkstraHeap algHeap = new DijkstraHeap(tripGraph.numVertices() + 1);
		
		for (Vertex v: (Vertex[]) tripGraph.vertexList.toArray()) {
			// Set length from start to v to 0
			// Set Penultimate Vertex of v to null
			// call algHeap.insert(v, penultinmate of v)
		}
		// Set penultimate of start to 0
		// Call algHeap.decrease(start, penultimate of start)
		Set<Vertex> spFound = new HashSet<Vertex>();
		for (int i = 0; i < tripGraph.numVertices() - 1; i++) {
			Vertex fringe = (Vertex) algHeap.deleteMin();
			spFound.add(fringe);
			for (Vertex v: (Vertex[]) tripGraph.vertexList.toArray()) {
				if (!spFound.contains(v)) {
					boolean adjacent = false;
					Iterator<Edge> itr = tripGraph.incidentEdges(v);
					for (Object e : itr) {
						
					}
					
				}
			}
		}
	}
}
