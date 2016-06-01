/* TCSS 343, Dr. Donald Chinn
 * Group 4: Trevor, Kyaw, Henry
 * Homework 6B
 */

/**
 * Holds node data for Dijkstra's Heap Implementation.
 * 
 * @author Trevor Lowe
 * @version 1
 */
public class DijkstraHeapNode implements Comparable {
	
	public static final double ERROR = 0.01;
	public static final double INFINITY = -1;
	
	private Vertex theVertex;
	private double distance;
	private Vertex penVert;
	
	public DijkstraHeapNode(Vertex newVertex, double newDist, Vertex newPenultimate) {
		theVertex = newVertex;
		distance = newDist;
		penVert = newPenultimate;
	}
	
	public void setDistance(double newDist) {
		distance = newDist;
	}
	
	public double getDistance() {
		return this.distance;
	}
	
	public Vertex getPenVert() {
		return this.penVert;
	}
	
	public Vertex getVertex() {
		return this.theVertex;
	}

	@Override
	public int compareTo(Object o) {
		DijkstraHeapNode other = (DijkstraHeapNode) o;
		if ((this.distance > other.getDistance() && Math.abs(other.getDistance() - INFINITY) >= ERROR) ||
				Math.abs(this.getDistance() - INFINITY) < ERROR && other.getDistance() > this.getDistance()) { // Greater than
			return 1;
		} else if ((this.distance < other.getDistance() && Math.abs(this.getDistance() - INFINITY) >= ERROR) ||
					Math.abs(other.getDistance() - INFINITY) < ERROR && this.getDistance() > other.getDistance()) { // Less than
			return -1;
		} else { // Equal
			return 0;
		}
	}
	
	
}
