/**
 * This class represents an undirected graph.
 * @author tbuwadi, Tala Buwadi
 */
import java.util.Iterator;
import java.util.Stack;
import java.util.Vector;

public class Graph implements GraphADT {

	/**
	 * Declaration of instance variables
	 */
	private int numNodes;
	private Node[] nodeArray;
	private Edge[][] edgeMatrix;
	
	
	/**
	 * Class constructor
	 * Creates a graph with n nodes and no edges
	 * The names of the nodes are 0,1,... , n-1. 
	 * @param n
	 * 		number of nodes 
	 */
	public Graph(int n) {
		
		//Add the number of nodes to the instance variable
		numNodes = n;
		
		//Initialize the size of the nodes array
		nodeArray = new Node[numNodes];
		
		//Name all the nodes
		for(int i=0; i<numNodes; i++) {
			nodeArray[i] = new Node(i);
		}
		
		//Initialize the size of the edge matrix
		edgeMatrix = new Edge[n][n];
	}
	
	/** 
	 * Adds to the graph an edge connecting the given vertices.
	 *  The type of the edge is as indicated. The label of the edge is set to the empty String. 
	 *  @param nodeu
	 *  		first endpoint
	 *  @param nodev
	 *  		second endpoint
	 *  @param edgeType
	 *  		the type of edge
	 *  @throws a GraphException if either node does not exist or if the edge is already in the graph.
	 **/
	public void insertEdge(Node nodeu, Node nodev, int edgeType) throws GraphException {
		
		//Get the names of the nodes
		int nodeuName = nodeu.getName();
		int nodevName = nodev.getName();
		
		//Check to make sure the nodes exist
		//If the names are smaller than the number of nodes, then they will have been named in the constructor and we can insert them
		if(nodeuName < numNodes && nodevName < numNodes) {
			
			//Check to make sure the edge doesn't already exist before inserting
			if(edgeMatrix[nodeuName][nodevName]==null && edgeMatrix[nodevName][nodeuName]==null) {
				
				//Create an edge to insert
				Edge newEdge = new Edge(nodeu,nodev,edgeType);
				
				//Insert the edge into the matrix to connect nodeu to nodev and nodev to nodeu
				edgeMatrix[nodeuName][nodevName] = newEdge;
				edgeMatrix[nodevName][nodeuName] = newEdge;
				
			}
			//If the edge already exists, throw a new exception
			else {
				throw new GraphException();
			}
		}
		//If the nodes don't exist, throw a graph exception
		else {
			throw new GraphException();	
		}
			
	}

	/**
	 *  Returns the node with the specified name. 
	 *  @return
	 *  		node with the specified name
	 *  @throws 
	 *  		a GraphException if the node does not exist.
	 **/	
	public Node getNode(int name) throws GraphException {
		
		//Check to make sure the node exists
		//If the name is smaller than the number of nodes, then it will have been named in the constructor and we can return the name
		if(name<numNodes) {
			return nodeArray[name];
		}
		else {
			throw new GraphException();
		}
	}

	 /** Returns a Java Iterator storing all the edges incident on the specified node.
	  *  It returns null if the node does not have any edges incident on it. 
	  *  @return 
	  *  		Java Iterator storing all the edges incident on the specified node
	  *  		Null if the node does not have any edges incident on it
	  *  @throws
	  *  		a GraphException if the node does not exist.
	  **/
	public Iterator incidentEdges(Node u) throws GraphException {
		
		int uName = u.getName();
		
		//If the node exists
		if(uName<numNodes) {
			 
			//To find incident edges, ie edges that share a vertex with the given node, we need to look through the adjacency matrix
			//We need to find any edges that have the given node as one of the endpoints and add them to a vector
			Stack<Edge> edgesFound = new Stack<Edge>();
			
			//Look through all possible edge locations for the node, and add the edge to the vector if it exits
			for (int i = 0; i<numNodes; i++) {
				
				//If an edge is found with the given node as the endpoint, add this edge to the vector
				if(edgeMatrix[i][uName]!=null) {
					edgesFound.push(edgeMatrix[i][uName]);
				}
			}
			
			//Create the iterator object using the edges vector
			Iterator<Edge> edgeIterator = edgesFound.iterator();
			
			//Return the iterator object
			return edgeIterator;
		}
		//Otherwise, if the node doesn't exist, throw an exception
		else {
			throw new GraphException();
		}
	}

	 /** 
	  * Returns the edge connecting the given vertices
	  * @returns 
	  * 	the connecting edge
	  * @throws 
	  * 	a GraphException if there is no edge connecting 
	  * 	the given vertices or if u or v do not exist. 
	  * */
	public Edge getEdge(Node u, Node v) throws GraphException {
		
		//Get the names of the nodes
		int uName = u.getName();
		int vName = v.getName();
			
			//Check to make sure the nodes exist and that the edges exist
			if((uName < numNodes && vName < numNodes) && (edgeMatrix[uName][vName]!=null && edgeMatrix[uName][vName]!=null)) {
				
				return edgeMatrix[uName][vName];
			}
			//If the nodes/edges don't exist, throw a graph exception
			else {
				throw new GraphException();
			}
	}

	 /** 
	  * Returns true is u and v are adjacent, and false otherwise. 
	  * @return
	  * 	true if u and v are adjacent
	  *		false otherwise
	  *		@throws
	  *		throws a GraphException if either vertex does not exist. 
	*/
	public boolean areAdjacent(Node u, Node v) throws GraphException {
		
		//Get the names of the nodes
		int uName = u.getName();
		int vName = v.getName();
		
		//If either u or v doesn't exist, throw an exception
		if(uName>=numNodes || vName>=numNodes) {
			throw new GraphException();
		}
		//otherwise, check for adjacency by checking to see if the edge from u-v and the edge from v-u aren't null
		//if they aren't null, we have adjacency
		else {
			//Return true if adjacent
			if (edgeMatrix[uName][vName]!=null && edgeMatrix[vName][uName]!=null){
				return true;
			}
			//Return false if not adjacent
			else {
				return false;
			}
		}
	}

}




















