/* WORD LADDER Queue.java
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

public class Queue {
	public int length;
	Entry first, last;
	
	public class Entry {
		Node n;
		Entry next;
		Entry prev;
		
		Entry(String s) {
			n = new Node(s);
		}
		
		Entry(Node a) {
			n = a;
		}
		
		//sets pointer to next element in queue
		void nextSet(Entry e) {
			next = e;
		}
		
		//sets pointer to previous element in queue
		void prevSet(Entry e) {
			prev = e;
		}
	}
	
	Queue() {
		first = new Entry("0");
		last = new Entry("1");
		first.nextSet(last);
		last.nextSet(last);
		first.prevSet(first);
		last.prevSet(first);
		length = 0;
	}
	
	//adds node n to the queue
	void enqueue(Node n) {
		Entry add = new Entry(n);
		last.prev.nextSet(add);
		add.prevSet(last.prev);
		add.nextSet(last);
		last.prevSet(add);
		length++;
	}
	
	//removes first element from queue
	void dequeue() {
		first.nextSet(first.next.next);
		first.next.prevSet(first);
		length--;
	}
	
	//returns the Node at the front of the queue
	Node firstNode() {
		return first.next.n;
	}

}
