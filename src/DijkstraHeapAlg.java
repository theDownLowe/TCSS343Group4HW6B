import java.util.ArrayList;
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
	
	private double shortestDistance;
	private Set<DijkstraHeapNode> vertexList;
	private Vertex startVert;
	
	public DijkstraHeapAlg(SimpleGraph tripGraph, Vertex start) throws EmptyHeapException {
		shortestDistance = -1;
		vertexList = new HashSet<DijkstraHeapNode>();
		startVert = start;
		computePath(tripGraph);
	}
	
	private void computePath(SimpleGraph tripGraph) throws EmptyHeapException {
		DijkstraHeap algHeap = new DijkstraHeap(tripGraph.numVertices() + 1);
		
		for (Object vert: tripGraph.vertexList.toArray()) {
			Vertex v = (Vertex) vert;
			v.setData(new DijkstraHeapNode(v, -1, null));
			algHeap.insert((DijkstraHeapNode)v.getData());
		}
		algHeap.decrease((DijkstraHeapNode)startVert.getData());
		Set<DijkstraHeapNode> spFound = new HashSet<DijkstraHeapNode>();
		for (int i = 0; i < tripGraph.numVertices() - 1; i++) {
			Vertex fringe = ((DijkstraHeapNode)algHeap.deleteMin()).getVertex();
			spFound.add((DijkstraHeapNode)fringe.getData());
			for (Object vert : tripGraph.vertexList.toArray()) {
				Vertex v = (Vertex) vert;
				if (!spFound.contains(v)) {
					boolean adjacent = false;
					for (Object obj : fringe.incidentEdgeList.toArray()) {
						Edge e = (Edge) obj;
						if (tripGraph.opposite(fringe, e).equals(v)) {
							adjacent = true;
							break;
						}
					}
					if (adjacent && ((DijkstraHeapNode)fringe.getData()).compareTo((DijkstraHeapNode)v.getData()) == -1) {
						((DijkstraHeapNode)v.getData()).setDistance(((DijkstraHeapNode)fringe.getData()).getDistance());
						((DijkstraHeapNode)v.getData()).setPenVert(fringe);
						algHeap.decrease((DijkstraHeapNode)v.getData());
					}
				}
			}
		}
		vertexList = spFound;
	}
	
	public ArrayList<Vertex> bestPath(Vertex end) {
		ArrayList<Vertex> path = new ArrayList<Vertex>();
		path.add(end);
		Vertex next = ((DijkstraHeapNode)end.getData()).getPenVert();
		while (next != null) {
			path.add(next);
			next = ((DijkstraHeapNode)end.getData()).getPenVert();
		}
		path.add(startVert);
		return path;
	}
	
	public double getShortestDistance(Vertex end) {
		shortestDistance = ((DijkstraHeapNode)end.getData()).getDistance();
		return shortestDistance;
	}
}
