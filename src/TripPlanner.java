/* TCSS 343, Dr. Donald Chinn
 * Group 4: Trevor, Kyaw, Henry
 * Homework 6B
 */

import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * The driver class for the Trip Planner program which allows
 * the user to input a graph, a starting location, and end location,
 * and using Dijstra's algorithm, computes the least cost trip route.
 * 
 * @author Trevor Lowe
 * @vrsion 1
 */
public class TripPlanner {
	public static void main(String[] args) {
		System.out.println("----- Trip Planner -----");
		SimpleGraph tripGraph = new SimpleGraph();
		Hashtable<String, Vertex> graphTable = GraphInput.LoadSimpleGraph(tripGraph); // Name, Vertex
		userMenu(tripGraph);
	}
	
	public static void userMenu(SimpleGraph tripGraph) {
		int selection = 0;
		while (selection != 4) {
			System.out.println("----- Main Menu -----");
			System.out.println("1) Run Dijkstra's Algorithm (Heap)");
			System.out.println("2) Run Dijkstra's Algorithm (Array)");
			System.out.println("3) List cities");
			System.out.println("4) Exit");
			
			Scanner input = new Scanner(System.in);
			selection = input.nextInt();
			if (selection == 1) { 			// Heap
				runDijkstrasHeap(tripGraph);
			} else if (selection == 2) { 	// Array
				runDijkstrasArray(tripGraph);
			} else if (selection == 3) { 	// List Cities
				listCities(tripGraph);
			} else if (selection != 4) {	// Invalid
				System.out.println("Please enter a valid input.");
			}
			
		}
	}
	
	private static void runDijkstrasHeap(SimpleGraph tripGraph) {
		
	}
	
	private static void runDijkstrasArray(SimpleGraph tripGraph) {
		
	}
	
	private static void listCities(SimpleGraph tripGraph) {
		for (Object v: tripGraph.vertexList.toArray()) {
			Vertex vert = (Vertex) v;
			System.out.println(vert.getName());
		}
	}
}
