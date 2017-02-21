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
		
		void nextSet(Entry e) {
			next = e;
		}
		
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
	
	void enqueue(Node n) {
		Entry add = new Entry(n);
		last.prev.nextSet(add);
		add.prevSet(last.prev);
		add.nextSet(last);
		last.prevSet(add);
		length++;
	}
	
	void dequeue() {
		first.nextSet(first.next.next);
		//delete first.next.prev;
		//wrap pointers?
		first.next.prevSet(first);
		length--;
	}
	
	Node firstNode() {
		return first.next.n;
	}

}
