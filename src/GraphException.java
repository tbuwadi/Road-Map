/**
 * This class implements the exception thrown by the methods in Graph.java 
 * if a node given doesn't exist or if an edge already exists.
 * @author tbuwadi, Tala Buwadi
 *
 */
public class GraphException extends RuntimeException{
		public GraphException() {
			super("Error: The node doesn't exist or the edge already exists.");
		}
}
