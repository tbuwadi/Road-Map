/**
 * This class represents an edge of the graph
 * @author tbuwadi, Tala Buwadi
 *
 */
public class Edge {
	
	/**
	 * Declare instance variables
	 */
	private Node firstEndpoint;
	private Node secondEndpoint;
	private int edgeType;
	
	/**
	 * The constructor for the class. The first two parameters are the endpoints of the edge. 
	 * The last parameter is the type of the edge, which for this project can be either
	 *  0 (if the edge represents a public road), 
	 *  1 (if the edge represents a private road), or
	 * -1 (if the edge represents a reward road).
	 */
	public Edge(Node u, Node v, int type) {
		firstEndpoint = u;
		secondEndpoint = v;
		edgeType = type;
	}
	
	/**
	 * Returns the first endpoint of the edge
	 * @return
	 * 		the first endpoint
	 */
	public Node firstEndpoint() {
		return firstEndpoint;
	}
	
	/**
	 * Returns the second endpoint of the edge
	 * @return
	 * 		the second endpoint
	 */
	public Node secondEndpoint() {
		return secondEndpoint;
	}
	
	public int getType() {
		return edgeType;
	}
	
	
	
}
