/* WORD LADDER Main.java
 * EE422C Project 3 submission by
 * Bryan Yao
 * byy77
 * 16225
 * Peter Nguyen
 * ppn229
 * 16225
 * Slip days used: <0>
 * Git URL: https://github.com/pnguyen339/422C/tree/master/Project%203%20Word%20Ladder/Current%20Proj3
 * Spring 2017
 */


package assignment3;
import java.util.*;
import java.io.*;

public class Main {
	
	public static HashMap<String, Node> graph; //graph constructed from the dictionary
	
	public static void main(String[] args) throws Exception {
		
		Scanner kb;	// input Scanner for commands
		PrintStream ps;	// output file
		// If arguments are specified, read/write from/to files instead of Std IO.
		if (args.length != 0) {
			kb = new Scanner(new File(args[0]));
			ps = new PrintStream(new File(args[1]));
			System.setOut(ps);			// redirect output to ps
		} else {
			kb = new Scanner(System.in);// default from Stdin
			ps = System.out;			// default to Stdout
		}
		initialize();
		while(true) {
			ArrayList<String> arguments = parse(kb);
			if (arguments.get(0).equals("/quit")) {
				break;
			}
			else if (arguments.get(1).equals("/quit")) {
				break;
			}
			printLadder(getWordLadderDFS(arguments.get(0), arguments.get(1)));
		}
	}
	
	/** initialize
	 * @param none
	 * @return none
	 * Calls makeDictionary and creates the dictionary graph from the returned dict. 
	 */
	public static void initialize() {
		Set<String> dict = makeDictionary();
		graph = new HashMap<String, Node>();
		for (String s : dict) {
			Node node = new Node(s);
			for (int i = 0; i < s.length(); i++) {
				for (char c = 'A'; c <= 'Z'; c++) {
					char[] temp = s.toCharArray();
					temp[i] = c;
					String test = String.valueOf(temp);
					if (!test.equals(s) && dict.contains(test)) {
						node.edges.add(test.toLowerCase());
						node.edgeNo++;
					}
				}
			}
			graph.put(s.toLowerCase(), node);
		}
	}
	
	/** parse
	 * @param keyboard Scanner connected to System.in
	 * @return ArrayList of Strings containing start word, rungs, and end word. 
	 * If command is /quit, return empty ArrayList. 
	 */
	public static ArrayList<String> parse(Scanner keyboard) {
		ArrayList<String> arguments = new ArrayList<String>();
		String first = keyboard.next().trim();
		arguments.add(first);
		if (!first.equals("/quit")) {
			String second = keyboard.next().trim();
			arguments.add(second);
		}
		return arguments;
	}
	
	/** getWordLadderDFS
	 * Our DFS utilizes a function DFS that recursively calls itself to try to find the
	 * end word. The DFS function has two parameters, the node to be modified, and its
	 * parent. It checks to see if a node is visited by checking the parent field, and 
	 * if it has not, it sets the parent field. Then sorts the edges field by checking
	 * which edges have more letters in common with the target final word, and searches
	 * those branches first. If the function eventually returns false, then that means
	 * the target word was not found in that branch and signifies the need to call along
	 * another edge/branch, or if the entire function returns false there is no ladder
	 * at the end of all of the recursion. Returning true means that the word was found
	 * and it stops further needless recursive calls. The position field in the nodes
	 * keeps track of which branches have already been explored.
	 * @param none
	 * @return ArrayList of word ladder words
	 * resets the graph, conducts a depth first search and returns a ladder if found 
	 */
	public static ArrayList<String> getWordLadderDFS(String start, String end) {
		
		reset();
		Node first = graph.get(start);
		DFS(first, first, end);
		return listSet(start, end);
	}
	
	/** getWordLadderBFS
	 * Our BFS algorithm starts from the Node containing the start word and places all of
	 * its children in a queue. Then it checks the first entry in the queue to see if it
	 * is the end word. If not, it places all of the children of that node in the queue. 
	 * This way, all nodes of distance 1 will be checked before nodes of distance 2, etc.
	 * When each node is checked, the parent node it came from before is set as the parent
	 * field. This doubles as a visited flag. The BFS continues until the queue is empty
	 * (no path) or the end word is found. Then to return the array, we check the parent
	 * field of the end node. If it is blank, there is no path. If there is a parent, we
	 * insert the String word field of the node into the ArrayList, and then the parent 
	 * before that, and the parents of the parents, etc. until we reach the start node.
	 * @param none
	 * @return ArrayList of word ladder words
	 * resets the graph, conducts a breadth first search and returns a ladder if found
	 * utilizes a queue for checking nodes 
	 */
    public static ArrayList<String> getWordLadderBFS(String start, String end) {
		
    	reset();

    	Queue queue = new Queue();
    	Node first = graph.get(start);
    	first.parent = start;
    	for (int i = 0; i < first.edgeNo; i++) {
    		Node toQueue = graph.get(first.edges.get(i));
    		toQueue.parent = start;
    		queue.enqueue(toQueue);
    	}
    	while (queue.length != 0) {
    		Node check = queue.firstNode();
    		if (check.word.equals(end)) {
    			break;
    		}
    		for (int k = 0; k < check.edgeNo; k++) {
    			Node toQueue2 = graph.get(check.edges.get(k));
    			if (toQueue2.parent.equals("")) {
    				toQueue2.parent = check.word;
    				queue.enqueue(toQueue2);
    			}
    		}
    		queue.dequeue();
    	}
    	return listSet(start,end);
	}
    
	public static Set<String>  makeDictionary () {
		Set<String> words = new HashSet<String>();
		Scanner infile = null;
		try {
			infile = new Scanner (new File("five_letter_words.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Dictionary File not Found!");
			e.printStackTrace();
			System.exit(1);
		}
		while (infile.hasNext()) {
			words.add(infile.next().toUpperCase());
		}
		return words;
	}
	
	/** printLadder
	 * @param word ladder ArrayList
	 * @return none
	 * prints the inputted word ladder, including how long the ladder is
	 * if no ladder exists (ie given ladder length = 2), prints no ladder can be found
	 */
	public static void printLadder(ArrayList<String> ladder) {
		if (ladder.size() == 2) {
			System.out.println("no word ladder can be found between " + ladder.get(0) + " and " + ladder.get(1));
		}
		else{
			int number = ladder.size();
			System.out.println("a " + (number-2) + "-rung ladder exists between " + ladder.get(0) + " and " + ladder.get(number-1));
			for (int i = 0; i < number; i++) {
				System.out.println(ladder.get(i));
			}
		}
	}

	/** reset
	 * @param none
	 * @return none
	 * calls the node reset function on every node in the graph to reset the graph
	 */
	private static void reset() {
		for (Node n : graph.values()) {
			n.reset();
		}
	}
	
	/** listSet
	 * @param Strings start word, end word
	 * @return word ladder ArrayList
	 * after search is finished, and a path has been found (or not found),
	 * creates the word ladder arrayList from the graph entries/marked path.
	 */
	private static ArrayList<String> listSet(String start, String end) {
		Node last = graph.get(end);
    	if (last.parent.equals("")) {
    		ArrayList<String> notPossible = new ArrayList<String>();
    		notPossible.add(start);
    		notPossible.add(end);
    		return notPossible;
    	}
    	else {
    		Node insert = last;
    		ArrayList<String> possible = new ArrayList<String>();
    		while (!(insert.parent.equals(insert.word))) {
    			possible.add(0, insert.word);
    			insert = graph.get(insert.parent);
    		}
    		possible.add(0, insert.word);
    		return possible;
    	}
	}
	
	/** optimize
	 * @param arrayList of edges, String end word
	 * @return new edge ArrayList
	 * sorts the edge arrayList by putting the words that are closest to the
	 * end word at the beginning of the ArrayList to optimize the DFS
	 */
	private static ArrayList<String> optimize(ArrayList<String> a, String check) {
		ArrayList<Integer> sames = new ArrayList<Integer>();
		for (int i = 0; i < a.size(); i++) {
			int same = 0;
			for (int j = 0; j < check.length(); j++) {
				if (a.get(i).charAt(j) == check.charAt(j)) {
					same++;
				}
			}
			sames.add(same);
		}
		for (int k = 1; k < a.size(); k++) {
			for (int m = k; m > 0; m--) {
				if (sames.get(m) > sames.get(m-1)) {
					String temps = a.get(m);
					int tempi = sames.get(m);
					a.set(m, a.get(m-1));
					sames.set(m, sames.get(m-1));
					a.set(m-1, temps);
					sames.set(m-1, tempi);
				}
			}
		}
		return a;
	}
	
	/** DFS
	 * @param Node m (parent), n (to be modified), String end word
	 * @return boolean true if word is found, false otherwise
	 * recursively calls self to conduct a DFS, uses the true and false booleans
	 * to aid with going back from nodes. While loop determines which node to
	 * check next.
	 */
	private static boolean DFS(Node m, Node n, String test) {
		if (!n.parent.equals("")) {
			return false;
		}
		else {
			n.parent = m.word;
			if (n.word.equals(test)) {
				return true;
			}
			n.edges = optimize(n.edges, test);
			boolean check = false;
			while (n.position < n.edgeNo && check == false) {
				check = DFS(n, graph.get(n.edges.get(n.position)), test);
				n.position++;
			}
			return check;
		}
	}
}
