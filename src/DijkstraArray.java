import java.util.LinkedList;

public class DijkstraArray {
	
	//A linked list of Strings that contains the name of the city
	//The zeroth index is the starting city.
	LinkedList<String> cities = new LinkedList<>();
	
	//A linked list of integers that contains the distance value from the starting point to other vertices
	LinkedList<Double> dist = new LinkedList<>();

	
    /**
     * A constructor for the Dijkstra's array with
     * a starting vertex
     * @param startingPt	a starting vertex for the algorithm
     */
	public DijkstraArray(Vertex startingPt) {
		cities.add((String) startingPt.getName());
		dist.add(0.);
	}
	
    /**
     * Add a city(a vertex) to the linkedlist.
     * Initializes the distance of that vertex to infinity in dist.
     * @param v		a vertex to be added to the linkedlist
     */
	public void addCity(Vertex v) {
		if(!cities.contains(v)) {
			cities.add((String) v.getName());
			dist.add(Double.MAX_VALUE);
		}
	}
	
    /**
     * Calculate the distance of the given edge and
     * update the label(value) for the dist linkedlist
     * if the label at that vertex is higher than the weight of the given edge
     * @param e		an edge from the graph
     */
	public void calcEdgeDistance(Edge e) {
		Vertex v1 = e.getFirstEndpoint();
		Vertex v2 = e.getSecondEndpoint();
		
		for(int i = 1; i < cities.size(); i++) {
			if(cities.get(i) == (String)v1.getName()) {
				for(int j = 1; j <cities.size(); j++) {
					if(cities.get(j) == (String)v1.getName()) {
						double distance = dist.get(i) + (Double)e.getData();
						dist.add(j, Double.min(distance, dist.get(j)));
					}
				}
			}
		}
	}
	
    /**
     * Return the minimum distance of the given ending vertex
     * from the starting vertex.
     * @return the minimum shortest distance from the starting vertex to the given ending vertex.
     */
	public Double shortestDistance(Vertex endingPt) {
		for(int i = 1; i < cities.size(); i++) {
			if(cities.get(i) == (String)endingPt.getName()) {
				return (Double)dist.get(i);
			}
		}
		return 0.;
	}
}
