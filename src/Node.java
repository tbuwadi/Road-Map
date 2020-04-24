/**
 * This class represents a node of the graph
 * @author tbuwadi, Tala Buwadi
 *
 */
public class Node {

	/**
	 * Declare instance variables
	 */
	private int nodeName;
	private boolean nodeMark;
	
	/**
	 * This is the constructor for the class and it creates a node with the given name. 
	 * The name of a node is an integer value between 0 and n-1, where n is the number of nodes in the graph.
	 * @param name 
	 * 			the name of the node
	 */
	public Node(int name) {
		nodeName = name;
	}
	
	/**
	 * Marks the node with the specified value, either true or false.
	 * This is useful when traversing the graph to know which vertices have already been visited.
	 * @param mark
	 * 		 the value to set the node to, true or false
	 */
	public void setMark(boolean mark) {
		nodeMark = mark;
	}
	
	/**
	 * Returns the value with which the node has been marked
	 * @return
	 * 		True if the node has been marked
	 * 		False if the node hasn't been marked
	 */
	public boolean getMark() {
		return nodeMark;
	}
	
	/**
	 * Returns the name of the vertex
	 * @return
	 * 		the name of the vertex
	 */
	public int getName() {
		return nodeName;
	}
	
}
