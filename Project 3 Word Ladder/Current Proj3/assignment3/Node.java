package assignment3;
import java.util.*;

public class Node {
	public String word;
	public ArrayList<String> edges;
	public String parent;
	public int edgeNo;
	public int position;
	
	Node(String s){
		word = s.toLowerCase();
		edges = new ArrayList<String>();
		parent = "";
		edgeNo = 0;
		position = 0;
	}
	
	public void reset(){
		parent = "";
		position = 0;
	}
}
