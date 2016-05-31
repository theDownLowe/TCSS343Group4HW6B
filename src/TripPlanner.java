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
		userMenu(tripGraph, graphTable);
	}
	
	public static void userMenu(SimpleGraph tripGraph, Hashtable<String, Vertex> graphTable) {
		int selection = 0;
		while (selection != 3) {
			System.out.println("----- Main Menu -----");
			System.out.println("1) Find best route");
			System.out.println("2) List cities");
			System.out.println("3) Exit");
			System.out.print("Selection: ");
			
			Scanner input = new Scanner(System.in);
			selection = input.nextInt();
			if (selection == 1) { 			// Heap
				findRoute(tripGraph, graphTable, input);
			} else if (selection == 2) { 	// List Cities
				listCities(tripGraph);
			} else if (selection != 3) {	// Invalid
				System.out.println("Please enter a valid input.");
			}
			
		}
	}
	
	public static void findRoute(SimpleGraph tripGraph, Hashtable<String, Vertex> graphTable, Scanner input) {
		System.out.println("----- Find Route -----");
		boolean found = false;
		Vertex start = null;
		Vertex end = null;
		
		while (!found) {
			System.out.print("Enter a starting location: ");
			String s = input.next();
			if (graphTable.containsKey(s)) {
				start = graphTable.get(s);
				found = true;
			} else {
				System.out.println("Please enter a valid city name (case sensitive).");
			}
		}
		
		found = false;
		while (!found) {
			System.out.print("Enter an end location: ");
			String s = input.next();
			if (graphTable.containsKey(s)) {
				end = graphTable.get(s);
				found = true;
			} else {
				System.out.println("Please enter a valid city name (case sensitive).");
			}
		}
		
		int selection = 0;
		while (selection != 1 && selection != 2) {
			System.out.println("Which version of Dijkstra's algorithm would you like to use?");
			System.out.println("1) Heap version");
			System.out.println("2) Array version"); 
			System.out.print("Selection: ");
			
			selection = input.nextInt();
			if (selection == 1) {
				runDijkstrasHeap(tripGraph, start, end);
			} else if (selection == 2) {
				runDijkstrasArray(tripGraph, start, end);
			} else {
				System.out.println("Please enter a valid input.");
			}
		}
	}
	
	private static void runDijkstrasHeap(SimpleGraph tripGraph, Vertex start, Vertex end) {
		System.out.println("----- From " + start.getName() + " to " + end.getName() + " Using Heap Algorithm -----");
		System.out.println("Best Path: ");
		
		System.out.println("Path's distance: ");
		
	}
	
	private static void runDijkstrasArray(SimpleGraph tripGraph, Vertex start, Vertex end) {
		System.out.println("----- From " + start.getName() + " to " + end.getName() + " Using Array Algorithm -----");
		DijkstraArray dArray = new DijkstraArray(start);
		for (Object v : tripGraph.vertexList.toArray()) {
			Vertex vert = (Vertex) v;
			dArray.addCity(vert);
		}
		System.out.println("Best Path: ");
		System.out.println("Path's distance: " + dArray.shortestDistance(end));
	}
	
	private static void listCities(SimpleGraph tripGraph) {
		System.out.println("----- List Cities -----");
		for (Object v: tripGraph.vertexList.toArray()) {
			Vertex vert = (Vertex) v;
			System.out.println(vert.getName());
		}
	}
}
