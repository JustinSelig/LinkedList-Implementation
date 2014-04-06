import static org.junit.Assert.*;

import org.junit.Test;


public class CircularLinkedListTester {

	/** Test Method for constructor. Concurrently tests 
	 * methods toString and toStringReverse */
	@Test
	public void testConstructor() {
		CircularLinkedList<Integer> a = new CircularLinkedList<Integer>();
		assertEquals("[]", a.toString());
		assertEquals("[]", a.toStringReverse());
		assertEquals(0, a.size());
	}
	
	/** Tests method append(). Concurrently tests 
	 * methods toString and toStringReverse */
	@Test
	public void testAppend() {
		//test append with a linked list of integers
		CircularLinkedList<Integer> b = new CircularLinkedList<Integer>();
		b.append(5);
		assertEquals("[5]", b.toString());
		assertEquals("[5]", b.toStringReverse());
		assertEquals(b.getFirst(), b.getLast());
		assertEquals(b.getFirst().predecessor(), b.getLast());
		assertEquals(b.getFirst(), b.getLast().successor());
		assertEquals(1, b.size());

		b.append(7);
		assertEquals("[5, 7]", b.toString());
		assertEquals("[7, 5]", b.toStringReverse());
		assertEquals(b.getFirst().predecessor(), b.getLast());
		assertEquals(b.getFirst(), b.getLast().successor());
		assertEquals(2, b.size());

		b.append(9);
		assertEquals("[5, 7, 9]", b.toString());
		assertEquals("[9, 7, 5]", b.toStringReverse());
		assertEquals(b.getFirst().predecessor(), b.getLast());
		assertEquals(b.getFirst(), b.getLast().successor());
		assertEquals(3, b.size());
		//append same integer (9)
		b.append(9);
		assertEquals("[5, 7, 9, 9]", b.toString());
		assertEquals("[9, 9, 7, 5]", b.toStringReverse());
		assertEquals(b.getFirst().predecessor(), b.getLast());
		assertEquals(b.getFirst(), b.getLast().successor());
		assertEquals(4, b.size());
	}

	/** Tests method prepend(). Concurrently tests 
	 * methods toString and toStringReverse */
	@Test
	public void testPrepend() {
		//test prepend with a linked list of integers
		
		CircularLinkedList<Integer> c = new CircularLinkedList<Integer>();
		//test prepending with 0
		c.prepend(0);
		assertEquals(c.getFirst().predecessor(), c.getLast());
		assertEquals(c.getFirst(), c.getLast().successor());
		assertEquals("[0]", c.toString());
		assertEquals("[0]", c.toStringReverse());
		assertEquals(1, c.size());

		c.prepend(1);
		assertEquals(c.getFirst().predecessor(), c.getLast());
		assertEquals(c.getFirst(), c.getLast().successor());
		assertEquals("[1, 0]", c.toString());
		assertEquals("[0, 1]", c.toStringReverse());
		assertEquals(2, c.size());

		c.prepend(5);
		assertEquals(c.getFirst().predecessor(), c.getLast());
		assertEquals(c.getFirst(), c.getLast().successor());
		assertEquals("[5, 1, 0]", c.toString());
		assertEquals("[0, 1, 5]", c.toStringReverse());
		assertEquals(3, c.size());

		c.prepend(5); //prepend same value (5) 
		assertEquals(c.getFirst().predecessor(), c.getLast());
		assertEquals(c.getFirst(), c.getLast().successor());
		assertEquals("[5, 5, 1, 0]", c.toString());
		assertEquals("[0, 1, 5, 5]", c.toStringReverse());
		assertEquals(4, c.size());

		c.prepend(129); // test prepending integers with more than 1 digit
		assertEquals(c.getFirst().predecessor(), c.getLast());
		assertEquals(c.getFirst(), c.getLast().successor());
		assertEquals("[129, 5, 5, 1, 0]", c.toString());
		assertEquals("[0, 1, 5, 5, 129]", c.toStringReverse());
		assertEquals(5, c.size());

		//test prepend with String
		CircularLinkedList<String> c1 = new CircularLinkedList<String>();
		c1.prepend("dog");
		assertEquals(c1.getFirst().predecessor(), c1.getLast());
		assertEquals(c1.getFirst(), c1.getLast().successor());
		assertEquals("[dog]", c1.toString());
		assertEquals("[dog]", c1.toStringReverse());
		assertEquals(1, c1.size());

		c1.prepend("cat");
		assertEquals(c1.getFirst().predecessor(), c1.getLast());
		assertEquals(c1.getFirst(), c1.getLast().successor());
		assertEquals("[cat, dog]", c1.toString());
		assertEquals("[dog, cat]", c1.toStringReverse());
		assertEquals(2, c1.size());
	}

	/** Tests method insertBefore() */
	@Test
	public void testInsertBefore() {
		CircularLinkedList<Integer> d = new CircularLinkedList<Integer>();
		//test prepending with 0
		d.prepend(0);
		assertEquals("[0]", d.toString());
		assertEquals("[0]", d.toStringReverse());
		assertEquals(1, d.size());

		d.insertBefore(5, d.getFirst());
		assertEquals("[0, 5]", d.toString());
		assertEquals("[5, 0]", d.toStringReverse());
		assertEquals(2, d.size());

		d.insertBefore(9, d.getLast());
		assertEquals(d.getFirst().predecessor(), d.getLast());
		assertEquals(d.getFirst(), d.getLast().successor());
		assertEquals(d.getFirst().successor(), d.getLast().predecessor());
		assertEquals("[0, 9, 5]", d.toString());
		assertEquals("[5, 9, 0]", d.toStringReverse());
		assertEquals(3, d.size());

		d.insertBefore(10, d.getFirst());
		assertEquals("[0, 9, 5, 10]", d.toString());
		assertEquals("[10, 5, 9, 0]", d.toStringReverse());
		assertEquals(4, d.size());
		
		d.insertBefore(16479, d.getLast().predecessor().predecessor());
		assertEquals("[0, 16479, 9, 5, 10]", d.toString());
		assertEquals("[10, 5, 9, 16479, 0]", d.toStringReverse());
	}

	/** Tests method insertAfter() */
	@Test
	public void testInsertAfter() {
		CircularLinkedList<Integer> e = new CircularLinkedList<Integer>();
		e.append(3);
		assertEquals("[3]", e.toString());
		assertEquals("[3]", e.toStringReverse());
		assertEquals(1, e.size());
		
		e.insertAfter(7, e.getFirst());
		assertEquals(e.getFirst(), e.getLast().successor());
		assertEquals(e.getFirst().predecessor(), e.getLast());
		assertEquals("[3, 7]", e.toString());
		assertEquals("[7, 3]", e.toStringReverse());
		assertEquals(2, e.size());
		
		e.insertAfter(8, e.getLast());
		assertEquals("[3, 7, 8]", e.toString());
		assertEquals("[8, 7, 3]", e.toStringReverse());
		assertEquals(3, e.size());
		
		e.insertAfter(123, e.getFirst());
		assertEquals("[3, 123, 7, 8]", e.toString());
		assertEquals("[8, 7, 123, 3]", e.toStringReverse());
		assertEquals(4, e.size());
		
		e.insertAfter(22, e.getFirst().successor());
		assertEquals("[3, 123, 22, 7, 8]", e.toString());
		assertEquals("[8, 7, 22, 123, 3]", e.toStringReverse());
		assertEquals(5, e.size());
		
		e.insertAfter(11, e.getLast().predecessor().predecessor());
		assertEquals("[3, 123, 22, 11, 7, 8]", e.toString());
		assertEquals("[8, 7, 11, 22, 123, 3]", e.toStringReverse());
		assertEquals(6, e.size());
		
		CircularLinkedList<Integer> e1 = new CircularLinkedList<Integer>();
		e1.append(2);
		assertEquals("[2]", e1.toString());
		assertEquals("[2]", e1.toStringReverse());
		assertEquals(1, e1.size());
		
		e1.insertAfter(6, e1.getLast());
		assertEquals(e1.getFirst(), e1.getLast().successor());
		assertEquals(e1.getFirst().predecessor(), e1.getLast());
		assertEquals("[2, 6]", e1.toString());
		assertEquals("[6, 2]", e1.toStringReverse());
		assertEquals(2, e1.size());
	}

	/** Tests method remove() */
	@Test
	public void testRemove() {
		//test when list only has one node
		CircularLinkedList<Integer> f = new CircularLinkedList<Integer>();
		f.append(3);
		assertEquals("[3]", f.toString());
		assertEquals("[3]", f.toStringReverse());
		assertEquals(1, f.size());
		
		f.remove(f.getFirst());
		assertEquals("[]", f.toString());
		assertEquals("[]", f.toStringReverse());
		assertEquals(null, f.getFirst());
		assertEquals(null, f.getLast());
		assertEquals(0, f.size());
		
		CircularLinkedList<String> f1 = new CircularLinkedList<String>();
		f1.append("one");
		assertEquals("[one]", f1.toString());
		assertEquals("[one]", f1.toStringReverse());
		assertEquals(1, f1.size());
		f1.remove(f1.getFirst());
		assertEquals("[]", f1.toString());
		assertEquals("[]", f1.toStringReverse());
		assertEquals(null, f1.getFirst());
		assertEquals(null, f1.getLast());
		assertEquals(0, f1.size());
		
		//remove head of list (length >= 2)
		CircularLinkedList<Integer> g = new CircularLinkedList<Integer>();
		g.append(3);
		g.append(5);
		g.append(7);
		assertEquals("[3, 5, 7]", g.toString());
		
		g.remove(g.getFirst());
		assertEquals("[5, 7]", g.toString());
		assertEquals("[7, 5]", g.toStringReverse());
		assertEquals(g.getFirst(), g.getLast().successor());
		assertEquals(g.getFirst(), g.getLast().predecessor());
		assertEquals(g.getLast(), g.getFirst().predecessor());
		assertEquals(g.getLast(), g.getFirst().successor());
		assertEquals(2, g.size());
		
		g.remove(g.getFirst());
		assertEquals("[7]", g.toString());
		assertEquals("[7]", g.toStringReverse());
		assertEquals(g.getFirst(), g.getLast().successor());
		assertEquals(g.getFirst(), g.getLast().predecessor());
		assertEquals(g.getLast(), g.getFirst().predecessor());
		assertEquals(g.getLast(), g.getFirst().successor());
		assertEquals(1, g.size());
		
		//remove last node
		g.remove(g.getFirst());
		assertEquals("[]", g.toString());
		assertEquals("[]", g.toStringReverse());
		assertEquals(null, f.getFirst());
		assertEquals(null, f.getLast());
		
		CircularLinkedList<Integer> h = new CircularLinkedList<Integer>();
		h.append(2);
		h.append(5);
		h.append(8);
		h.append(10);
		h.append(67);
		h.append(98);
		assertEquals("[2, 5, 8, 10, 67, 98]", h.toString());
		assertEquals("[98, 67, 10, 8, 5, 2]", h.toStringReverse());
		
		h.remove(h.getFirst().successor().successor());
		assertEquals("[2, 5, 10, 67, 98]", h.toString());
		assertEquals("[98, 67, 10, 5, 2]", h.toStringReverse());
		assertEquals(5, h.size());
		
		h.remove(h.getLast().predecessor().predecessor());
		assertEquals("[2, 5, 67, 98]", h.toString());
		assertEquals("[98, 67, 5, 2]", h.toStringReverse());
		assertEquals(4, h.size());
		
		h.remove(h.getLast().predecessor());
		assertEquals("[2, 5, 98]", h.toString());
		assertEquals("[98, 5, 2]", h.toStringReverse());
		assertEquals(3, h.size());
	}
}