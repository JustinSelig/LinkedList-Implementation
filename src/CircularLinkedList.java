/** An instance is a circular doubly linked list. */
public class CircularLinkedList<E> {
	private Node head; // a node of linked list (null if none)
	private int size;  // Number of nodes in linked list.

	/** Constructor: an empty linked list. */
	public CircularLinkedList() {
	}

	/** Return the number of values in this list. */
	public int size() {
		return size;
	}

	/** Return the first node of the list (null if the list is empty). */
	public Node getFirst() {
		return head;
	}

	/** Return the last node of the list (null if the list is empty). */
	public Node getLast() {
		if (head == null)
			return null;
		return head.pred;
	}

	/** If this circular list is empty, return null.
	 *  Otherwise, move down the list in circular fashion, so that the
	 *  first node becomes the last node, the second becomes the first, ec. */
	public Node moveDown() {
		if (head != null) {
			head= head.succ;
		}
		return head;
	}

	/** Return the value of node e of this list.
	 * Precondition: e must be a node of this list; it may not be null. */
	public E valueOf(Node e) {
		assert e != null;
		return e.value;
	}

	/** Return a representation of this list: its values, with adjacent
	 * ones separated by ", ", "[" at the beginning, and "]" at the end. <br>
	 * 
	 * E.g. for the list containing 6 3 8 in that order, the result it "[6, 3, 8]". */
	public String toString() {
		/* Note: This method should NOT refer to field size. It refers to
		 * field head and all the succ fields of the nodes. Reason: It allows
		 * toString to be used in testing head and all the succ fields. */
		if (head == null){
			return "[]";
		}
		else{
			Node next = head.succ;
			String retVal = "[" + head.value;
			while (next != head){
				retVal = retVal + ", " + next.value;
				next = next.succ;
			}
			return retVal + "]";
		}
	}

	/** Return a representation of this list: its values in reverse, with adjacent
	 * ones separated by ", ", "[" at the beginning, and "]" at the end. <br>
	 * 
	 * E.g. for the list containing 6 3 8 in that order, the result it "[8, 3, 6]".*/
	public String toStringReverse() {
		/* Note: This method should NOT refer to field size. It refers to
		 * field head and all the pred fields of the nodes. Reason: It allows
		 * toStringReverse to be used in testing head and all the pred fields. */
		if (head == null){
			return "[]";
		}
		else{
			Node containlast = head.pred;
			Node prev = containlast.pred;
			String retVal = "[" + head.pred.value;
			while (prev != head.pred){
				retVal = retVal + ", " + prev.value;
				prev = prev.pred;
			}
			return retVal + "]";
		}
	}

	/** Append value v to the list. */
	public void append(E v) {
		/* Note: this method views the list as a list with a first and
		 * a last value. It adds a new value at the end, not changing any
		 * others. */
		if (head == null){
			Node newLast0 = new Node(null, v, null);
			newLast0.pred = newLast0;
			newLast0.succ = newLast0;
			head = newLast0;
			size += 1;
		}
		else{
			Node oldFirst0 = head;
			Node oldLast0 = head.pred; 
			Node newLast0 = new Node(oldLast0, v, oldFirst0);
			head = oldFirst0;
			oldFirst0.pred = newLast0;
			oldLast0.succ = newLast0;
			size += 1;
		}
	}

	/** Prepend value v to the list. */
	public void prepend(E v) {
		/* Note: this method views the list as a list with a first and
		 * a last value. It adds a new value at the beginning, so head
		 * should end up pointing to the new node. */
		if (head == null){
			Node newFirst1 = new Node(null, v, null);
			newFirst1.pred = newFirst1;
			newFirst1.succ = newFirst1;
			head = newFirst1;
			size += 1;
		}
		else{
			Node oldFirst1 = head;
			Node oldLast1 = head.pred; 
			Node newFirst1 = new Node(oldLast1, v, oldFirst1);
			head = newFirst1;
			oldFirst1.pred = newFirst1;
			oldLast1.succ = newFirst1;
			size += 1;
		}
	}

	/** Insert value v in a new node before node e of this circular list.
	 * Precondition: e must be a node of this list, i.e. it may not be null. */
	public void insertBefore(E v, Node e) {
		/* Note: This method views the list as a circular list, so it doesn't
		 * really matter which node head points to when the method is done.
		 * However, we require that head does not change. */
		assert e != null;

		Node oldFirst2 = head;
		Node beforeNew2 = e.pred;
		Node newInsert2 = new Node(beforeNew2, v, e);
		head = oldFirst2;
		e.pred = newInsert2;
		beforeNew2.succ = newInsert2;
		size += 1;
	}

	/** Insert value v in a new node after node e.
	 * Precondition: e must be a node of this list, i.e. it may not be null. */
	public void insertAfter(E v, Node e) {
		/* Note: This method views the list as a circular list, so it doesn't
		 * really matter which node head points to when the method is done.
		 * However, we require that head does not change. */
		assert e != null;

		Node oldFirst3 = head;
		Node afterNew3 = e.succ;
		Node newInsert3 = new Node(e, v, afterNew3);
		head = oldFirst3;
		e.succ = newInsert3;
		afterNew3.pred = newInsert3;
		size += 1;
	}

	/** Remove node e from this list.
	 *  Precondition: e must be a node of this list, i.e. it may not be null. */
	public void remove(Node e) {
		/* Note: if the head (first) node is being removed and size >= 2, head
		 * should end up pointing at head's successor. */
		assert e != null;
		
		Node nodeBefore = e.pred;
		Node nodeAfter = e.succ;
		//if linked list has only one Node
		if (e == head && e == head.pred){
			e.pred = null;
			e.succ = null;
			head = null;
			size = size-1;
		}
		//if head is being removed (size >= 2)
		else if (e == head){
			e.pred = null;
			e.succ = null;
			nodeBefore.succ = nodeAfter;
			nodeAfter.pred = nodeBefore;
			head = nodeAfter;
			size = size-1;
		}
		else{
			e.pred = null;
			e.succ = null;
			nodeBefore.succ = nodeAfter;
			nodeAfter.pred = nodeBefore;
			size = size-1;
		}
	} 

	/*********************/

	/** An instance is a node of this list. */
	public class Node {
		/** Predecessor of this node on the list (null if the list is empty). */
		private Node pred;

		/** The value of this element. */
		private E value; 

		/** Successor of this node on the list. (null if the list is empty). */
		private Node succ; 

		/** Constructor: an instance with predecessor p (p can be null),
		 * successor s (s can be null), and value v. */
		private Node(Node p, E v, Node s) {
			pred= p;
			value= v;
			succ= s;
		}

		/** Return the value of this node. */
		public E getValue() {
			return value;
		}

		/** Return the predecessor of this node e in the list. */
		public Node predecessor() {
			return pred;
		}

		/** Return the successor of this node in this list. */
		public Node successor() {
			return succ;
		}
	}
}
