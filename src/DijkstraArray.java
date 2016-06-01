import java.util.LinkedList;
import java.util.Stack;

public class DijkstraArray {
	
	/**A linked list of Strings that contains the name of the city*/
	//The zeroth index is the starting city.
	LinkedList<String> cities = new LinkedList<>();
	
	/**A linked list of integers that contains the distance value from the starting point to other vertices */
	LinkedList<Integer> dist = new LinkedList<>();

	/** a linkedlist of stacks that contains the names of the cities that 
	 * make up the shortest path from the given starting point to each corresponding vertex.
	 */
	LinkedList<Stack<String>> shortestPaths = new LinkedList<>();
	
    /**
     * A constructor for the Dijkstra's array with
     * a starting vertex, initializing the dist, cities, and shortestPaths linkedlists
     * @param startingPt	a starting vertex for the algorithm
     */
	public DijkstraArray(Vertex startingPt) {
		cities.add((String) startingPt.getName());
		dist.add(0);
		Stack<String> a = new Stack<>();
		a.push((String)startingPt.getName());
		shortestPaths.add(a);
	}
	
    /**
     * Add a city(a vertex) to the linkedlist.
     * Initializes the distance of that vertex to infinity in dist.
     * and add a stack at the corresponding index for that vertex 
     * in shortestPaths linkedlist. 
     * @param v				a vertex to be added to the linkedlist
     * @param startinPt		a starting point for the algorithm
     */
	public void addCity(Vertex v, Vertex startingPt) {
		if(!cities.contains(v)) {
			cities.add((String) v.getName());
			dist.add(Integer.MAX_VALUE);
			
			Stack<String> a = new Stack<>();
			a.push((String)startingPt.getName());
			shortestPaths.add(a);
		}
	}
	
    /**
     * Calculate the distance of the given edge and
     * update the label(value) for the dist linkedlist
     * if the label at that vertex is higher than the weight of the given edge
     * update the stack for the ending vertex once the edge is calculated.
     * @param e		an edge from the graph
     */
	public void calcEdgeDistance(Edge e) {
		Vertex v1 = e.getFirstEndpoint();
		Vertex v2 = e.getSecondEndpoint();
		
		String str1 = (String)v1.getName();
		String str2 = (String)v2.getName();
		
		int index1 = 0;
		int index2 = 0;
		
		for(int i = 1; i < cities.size(); i++) {
			if(cities.get(i) ==str1) {
				index1 = i;
			}
		}
		
		for(int j = 1; j <cities.size(); j++) {
			if(cities.get(j) == str2) {
				index2 = j;
			}
		}
		
		int distance = dist.get(index1) + (Integer)e.getData();
		if(distance < dist.get(index2) && dist.get(index2) != Integer.MAX_VALUE) {
			
			//Stack<String> stack1 = shortestPaths.get(index1);
			Stack<String> s2 = shortestPaths.get(index2);
			
			//Pop the old path and push a new path thats shorter
			shortestPaths.get(index2).pop();
			
			//Add the first vertex into the stack if it hasn't been accounted yet
			//before adding the second vertex into the stack
			if(s2.search(str1) == -1) {
				shortestPaths.get(index2).push(str1);
			}
			shortestPaths.get(index2).push(str2);
			dist.add(index2, Integer.min(distance, dist.get(index2)));
		} 
		else if (distance < dist.get(index2) && dist.get(index2) == Integer.MAX_VALUE) {
			
			//Stack<String> stack1 = shortestPaths.get(index1);
			Stack<String> s2 = shortestPaths.get(index2);
			
			//Add the first vertex into the stack if it hasn't been accounted yet
			//before adding the second vertex into the stack
			if(s2.search(str1) == -1) {
				shortestPaths.get(index2).push(str1);
			}
			shortestPaths.get(index2).push(str2);
			dist.add(index2, Integer.min(distance, dist.get(index2)));
		}
	}
	
    /**
     * Return the minimum distance of the given ending vertex
     * from the starting vertex.
     * @return the minimum shortest distance from the starting vertex to the given ending vertex.
     */
	public int shortestDistance(Vertex endingPt) {
		for(int i = 1; i < cities.size(); i++) {
			if(cities.get(i) == (String)endingPt.getName()) {
				return (Integer)dist.get(i);
			}
		}
		return 0;
	}
	
    /**
     * Prints out a list of vertices/ cities in reverse order that make up the shortest path
     * from the starting vertex to a given ending vertex
     * using the shortestPaths linkedlist at the correspondin vertex.
     * @return the minimum shortest distance from the starting vertex to the given ending vertex.
     */
	public void shortestPath(Vertex endingPt) {
		for(int i = 1; i < cities.size(); i++) {
			if(cities.get(i) == (String)endingPt.getName()) {
				Stack<String> a = shortestPaths.get(i);
				System.out.print("Shortest Path: ");
				while(!a.isEmpty()) {
					System.out.print(a.pop());
				}
			}
		}
	}
}