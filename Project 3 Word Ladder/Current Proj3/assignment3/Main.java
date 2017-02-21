/* WORD LADDER Main.java
 * EE422C Project 3 submission by
 * Replace <...> with your actual data.
 * <Student1 Name>
 * <Student1 EID>
 * <Student1 5-digit Unique No.>
 * <Student2 Name>
 * <Student2 EID>
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Git URL:
 * Spring 2017
 */


package assignment3;
import java.util.*;
import java.io.*;

public class Main {
	
	public static HashMap<String, Node> graph;
	// static variables and constants only here.
	
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
	
	/**
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
	
	public static ArrayList<String> getWordLadderDFS(String start, String end) {
		
		reset();
		Node first = graph.get(start);
		DFS(first, first, end);
		return listSet(start, end);
	}
	
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
	
	public static void printLadder(ArrayList<String> ladder) {
		if (ladder.size() == 2) {
			System.out.println("no word ladder can be found between " + ladder.get(0) + " and " + ladder.get(1));
			//System.out.println(ladder.get(0));
			//System.out.println(ladder.get(1));
		}
		else{
			int number = ladder.size();
			System.out.println("a " + (number-2) + "-rung ladder exists between " + ladder.get(0) + " and " + ladder.get(number-1));
			for (int i = 0; i < number; i++) {
				System.out.println(ladder.get(i));
			}
		}
	}
	// TODO
	// Other private static methods here
	private static void reset() {
		for (Node n : graph.values()) {
			n.reset();
		}
	}
	
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
	
	private static boolean DFS(Node m, Node n, String test) {
		if (!n.parent.equals("")) {
			return false;
		}
		else {
			n.parent = m.word;
			if (n.word.equals(test)) {
				return true;
			}
			boolean check = false;
			while (n.position < n.edgeNo && check == false) {
				check = DFS(n, graph.get(n.edges.get(n.position)), test);
				n.position++;
			}
			return check;
		}
	}
}
