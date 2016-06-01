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
	
	private boolean shortestDistance;
	private Set<DijkstraHeapNode> vertexList;
	
	public DijkstraHeapAlg(SimpleGraph tripGraph, Vertex start) throws EmptyHeapException {
		DijkstraHeap algHeap = new DijkstraHeap(tripGraph.numVertices() + 1);
		
		for (Vertex v: (Vertex[]) tripGraph.vertexList.toArray()) {
			v.setData(new DijkstraHeapNode(v, -1, null));
			// algHeap.insert(v, dist to v)
		}
		// Set length of start to 0
		// Call algHeap.decrease(start, length of start)
		Set<DijkstraHeapNode> spFound = new HashSet<DijkstraHeapNode>();
		for (int i = 0; i < tripGraph.numVertices() - 1; i++) {
			Vertex fringe = (Vertex) algHeap.deleteMin();
			//spFound.add(fringe);
			for (Vertex v: (Vertex[]) tripGraph.vertexList.toArray()) {
				if (!spFound.contains(v)) {
					boolean adjacent = false;
					for (Edge e : (Edge[]) fringe.incidentEdgeList.toArray()) {
						if (tripGraph.opposite(fringe, e).equals(v)) {
							adjacent = true;
							break;
						}
					}
					if (adjacent /* && length to fringe + weight from fringe to v < length to v*/) {
						// Length to v = length to fringe + weight from fringe to v
						// Penultimate of v = fringe
						// spFound.decrease(v, length of v)
					}
				}
			}
		}
	}
}
