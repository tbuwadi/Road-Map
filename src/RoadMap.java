/**
 * This class represents the road map
 * @author tbuwadi, Tala Buwadi
 *
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Stack;

public class RoadMap {
	
	/**
	 * Declare instance variables
	 */
	private Graph graph;
	private int scale;
	private int start;
	private int end;
	private int width;
	private int length;
	private int initialBudget;
	private int toll;
	private int gain;
	Stack<Node> stack = new Stack<Node>();
	
	/**Constructor for building a graph from the input file specified in the parameter; 
	 * this graph represents the road map. If the input file does not exist, 
	 * this method should throw a MapException.
	 * Read below to learn about the format of the input file 
	 * @param inputFile
	 * 		inputFile representing the road map
	 */
	RoadMap(String inputFile){
		
		//Read the file input
		File file = new File(inputFile);
		
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
			
			//Read the first line to skip the scale
			bufferedReader.readLine();
			
			//Find the variable values and add them to the instance variables
			start = Integer.parseInt(bufferedReader.readLine());
			end = Integer.parseInt(bufferedReader.readLine());
			width = Integer.parseInt(bufferedReader.readLine());
			length = Integer.parseInt(bufferedReader.readLine());
			initialBudget = Integer.parseInt(bufferedReader.readLine());
			toll = Integer.parseInt(bufferedReader.readLine());
			gain = Integer.parseInt(bufferedReader.readLine());
			
			//create the graph
			graph = new Graph(length*width);
			
			int graphLength = length*2-1;
			int graphWidth = width*2-1;
			
			//Create a 2D array to hold the characters in the text file
			char graphChars[][] = new char[graphLength][graphWidth];
			
			//Traverse the 2D array and add the chars from the file to the indexes
			int currLine=0;
			while(currLine<graphLength) {
				String line = bufferedReader.readLine();
				for(int currChar=0; currChar<line.length(); currChar++) {
					graphChars[currLine][currChar]=line.charAt(currChar);
				}
				currLine = currLine+1;
			}
			
			//Use 2D array to add edges to the graph object
			for(int x=0; x<graphLength; x++) {
				for(int y=0; y<graphWidth; y++) {
					
					//Get the edgetype
					int edgeType = getEdgeType(graphChars[x][y]);
					
					//Add the edges to the graph
					if(isInBounds(x, graphLength) && isIntersection(graphChars[x-1][y]) && isIntersection(graphChars[x+1][y])) {
						Node nodeu = graph.getNode(((x+1)/2)*width +y/2);
						Node nodev = graph.getNode(((x-1)/2)*width +y/2);
						graph.insertEdge(nodeu, nodev, edgeType);
					}
					if(isInBounds(y, graphWidth) && isIntersection(graphChars[x][y-1]) && isIntersection(graphChars[x][y+1])){
						Node nodeu = graph.getNode((x/2)*width+(y-1)/2);
						Node nodev = graph.getNode((x/2)*width+(y+1)/2);
						graph.insertEdge(nodeu, nodev, edgeType);
					}
					else {
						continue;
					}
					
				}
			}
		
			//Close file input
			bufferedReader.close();		
		
		//catch Exceptions
		} catch (FileNotFoundException e) {
			throw new MapException();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Check if the array index is in bounds
	 * @param x
	 * @param measurement
	 * @return
	 */
	private boolean isInBounds(int x, int measurement) {
		if(x>0 && x < measurement-1) {
			return true;
		}
		return false;
	}
	
	/**
	 * Get the edge type
	 * @param c
	 * @return
	 */
	private int getEdgeType(char c) {
		if(c=='F') {
			return 0;
		}
		else if(c=='T') {
			return 1;
		}
		else if (c=='C'){
			return -1;
		}
		else {
			return 5;
		}
	}
	
	/**
	 * Check if we are at an intersection
	 * @param c
	 * @return
	 */
	private boolean isIntersection(char c) {
		if(c=='+') {
			return true;
		}
		return false;
	}
	
	/**
	 *  Returns the graph representing the road map.
	 * @return
	 * 		the graph representing the road map
	 */
	public Graph getGraph() {
		return graph;
	}
	
	/**
	 * Returns the starting node 
	 * (this value and the next two values are specified in the input file). 
	 * @return
	 * 		the starting node
	 */
	public int getStartingNode() {
		return start;
	}
	
	/**
	 * Returns the destination node.
	 * @return
	 * 		the destination node
	 */
	public int getDestinationNode() {
		return end;
	}
	
	/**
	 * Returns the initial amount of money available to pay tolls.
	 * @return
	 * 		the initial amount of money available to pay tolls
	 */
	public int getInitialMoney() {
		return initialBudget;
	}
	
	/**
	 * Returns a Java Iterator containing the nodes of a path from 
	 * the start node to the destination node as specified above,
	 * if such a path exists. The amount specified in initialMoney 
	 * plus the money earned by passing through the reward roads must
	 * be enough to pay for all the private roads. If the path does not exist,
	 * this method returns the value null. 
	 * @param start
	 * 			the start node
	 * @param detination
	 * 			the destination node
	 * @param initialMoney
	 * 			amount of initial money
	 * @return
	 * 			a Java Iterator containing the nodes of a path from 
	 * 			the start node to the detination node
	 * 			returns null if the path does not exist
	 */
	public Iterator findPath(int start, int destination, int initialMoney) {
		
		//Create a try catch to print any exceptions if they exist
		try {
			//Get the starting node and add it to the stack
			Node startNode = getGraph().getNode(start);
			startNode.setMark(true);
			stack.push(startNode);
		
			
			//If the start and the end are the same, return the iterator
			if(start == destination) {
				return stack.iterator();
			}
			
			//Get all the incident edges on the current node and look for an unmarked node
			//Ensure there is enough money to go on that rode
			Iterator<Edge> edges = graph.incidentEdges(startNode);
			while(edges.hasNext()) {
				
				Edge edge = edges.next();
				Node nodeNext = edge.secondEndpoint();
				
				if(nodeNext.getName() == startNode.getName()) {
					nodeNext = edge.firstEndpoint();
				}
				
				//Get the cost of the road
				int cost; 
				if(edge.getType()== 0) {
					cost=0;
				}else if(edge.getType() == -1) {
					cost=-gain;
				}else {
					cost=toll;
				}
				
				//Use a DFS traversal to find the path
				if(!nodeNext.getMark() && (initialMoney - cost >=0)) {
					initialMoney-=cost;
					Iterator<Edge> path = findPath(nodeNext.getName(), destination, initialMoney);
					if(path != null)
						return path;
					else 
						initialMoney+=cost;
					}
				}
			
			//If the path failed, take the node off and unmark it
			startNode.setMark(false);
			stack.pop();
			return null;
			
		}catch (Exception e) {
			System.out.println(e.toString());
		}
		return null;
	}

	}
