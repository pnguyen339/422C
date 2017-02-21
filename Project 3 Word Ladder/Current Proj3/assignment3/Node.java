package assignment3;
import java.util.*;

public class Node {
	public String word;
	public ArrayList<String> edges;
	public String parent; //doubles as a "visited flag"
	public int edgeNo;
	public int position; //for use in DFS, picking which edge to go to
	
	//Constructor from string
	Node(String s){
		word = s.toLowerCase();
		edges = new ArrayList<String>();
		parent = ""; //empty if unvisited
		edgeNo = 0;
		position = 0;
	}
	
	/**
	 * @param none
	 * @return none
	 * resets node by erasing parent/visited, and position to 0
	 */
	public void reset(){
		parent = ""; //all nodes set to unvisited/no parent
		position = 0; //reset position for DFS
	}
}
