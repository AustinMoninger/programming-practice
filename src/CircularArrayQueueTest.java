import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CircularArrayQueueTest {

	private CircularArrayQueue<String> caq1;
	private CircularArrayQueue<String> caq2;
	private CircularArrayQueue<Integer> caq3;
	private CircularArrayQueue<Double> caq4;

	@Before
	public void setUp() throws Exception {
		// queue of Strings
		caq1 = new CircularArrayQueue<>(5);
		caq1.enqueue("First");
		caq1.enqueue("Second");
		caq1.enqueue("Third");
		// Empty queue of Strings
		caq2 = new CircularArrayQueue<>();
		// queue of Integers
		caq3 = new CircularArrayQueue<>(0);
		caq3.enqueue(999);
		caq3.enqueue(-14);
		caq3.enqueue(3);
		caq3.enqueue(0);
		// queue of Doubles
		caq4 = new CircularArrayQueue<>(3);
		caq4.enqueue(656.34);
		caq4.enqueue(-13.0);
		caq4.enqueue(4.1);
		caq4.enqueue(0.0);
	}

	@After
	public void tearDown() throws Exception {
		caq1.clear();
		caq3.clear();
		caq4.clear();
	}

	@Test
	public void testEnqueue() {
		// Enqueue elements
		caq1.enqueue("Fourth");
		caq1.enqueue("Fifth");
		caq1.enqueue("Sixth");
		caq1.enqueue("Seventh");
		// Test if they were enqueued properly by testing order of dequeuing
		assertEquals("First", caq1.dequeue());
		assertEquals("Second", caq1.dequeue());
		assertEquals("Third", caq1.dequeue());
		assertEquals("Fourth", caq1.dequeue());
		assertEquals("Fifth", caq1.dequeue());
		assertEquals("Sixth", caq1.dequeue());
		assertEquals("Seventh", caq1.dequeue());

		// Enqueue elements
		caq3.enqueue(65);
		caq3.enqueue(-150);
		caq3.enqueue(2);
		// Test if they were enqueued properly by testing order of dequeuing
		assertEquals(new Integer (999), caq3.dequeue());
		assertEquals(new Integer (-14), caq3.dequeue());
		assertEquals(new Integer (3), caq3.dequeue());
		assertEquals(new Integer (0), caq3.dequeue());
		assertEquals(new Integer (65), caq3.dequeue());
		assertEquals(new Integer (-150), caq3.dequeue());
		assertEquals(new Integer (2), caq3.dequeue());

		// Enqueue elements
		caq4.enqueue(0.01);
		caq4.enqueue(22.9);
		caq4.enqueue(-1.0);
		// Test if they were enqueued properly by testing order of dequeuing
		assertEquals(new Double (656.34), caq4.dequeue());
		assertEquals(new Double (-13.0), caq4.dequeue());
		assertEquals(new Double (4.1), caq4.dequeue());
		assertEquals(new Double (0.0), caq4.dequeue());
		assertEquals(new Double (0.01), caq4.dequeue());
		assertEquals(new Double (22.9), caq4.dequeue());
		assertEquals(new Double (-1.0), caq4.dequeue());
	}

	@Test
	public void testDequeue() {
		// Testing queue of Strings
		assertEquals("First", caq1.dequeue()); // First in, first out
		assertEquals("Second", caq1.dequeue());
		assertEquals("Third", caq1.dequeue());
		// Should throw exception when dequeueing empty queue
		boolean exception = false;
		try { 
			caq1.dequeue();
		} catch(EmptyQueueException e) {
			exception = true;
		}
		assertTrue(exception);

		// Testing other empty queue
		try { 
			caq2.dequeue();
		} catch(EmptyQueueException e) {
			exception = true;
		}
		assertTrue(exception);

		// Testing queue of Integers
		assertEquals(new Integer (999), caq3.dequeue());
		assertEquals(new Integer (-14), caq3.dequeue());
		assertEquals(new Integer (3), caq3.dequeue());
		assertEquals(new Integer (0), caq3.dequeue());
		try { 
			caq3.dequeue();
		} catch(EmptyQueueException e) {
			exception = true;
		}
		assertTrue(exception);

		// Testing queue of Doubles
		assertEquals(new Double (656.34), caq4.dequeue());
		assertEquals(new Double (-13.0), caq4.dequeue());
		assertEquals(new Double (4.1), caq4.dequeue());
		assertEquals(new Double (0.0), caq4.dequeue());
		try { 
			caq4.dequeue();
		} catch(EmptyQueueException e) {
			exception = true;
		}
		assertTrue(exception);
	}

	@Test
	public void testGetFront() {
		// Testing queue of Strings
		assertEquals("First", caq1.getFront());
		caq1.dequeue(); // discard "First"
		assertEquals("Second", caq1.getFront()); // Front should change accordingly when entries are dequeued
		caq1.dequeue(); // discard "Second"
		assertEquals("Third", caq1.getFront());
		caq1.dequeue(); // discard "Third"
		// Front should throw an exception when empty
		boolean exception = false;
		try { 
			caq1.getFront();
		} catch(EmptyQueueException e) {
			exception = true;
		}
		assertTrue(exception);

		// Front should stay the same when entries are enqueued (corner case)
		caq1.enqueue("1st");
		assertEquals("1st", caq1.getFront());
		caq1.enqueue("2nd");
		assertEquals("1st", caq1.getFront());

		// Testing empty queue
		try { 
			caq2.getFront();
		} catch(EmptyQueueException e) {
			exception = true;
		}
		assertTrue(exception);

		// Testing queue of Integers
		assertEquals(new Integer (999), caq3.getFront());
		caq3.dequeue(); // discard 999
		assertEquals(new Integer (-14), caq3.getFront()); // Front should change accordingly when entries are dequeued
		caq3.dequeue(); // discard -14
		assertEquals(new Integer (3), caq3.getFront());
		caq3.dequeue(); // discard 3
		assertEquals(new Integer (0), caq3.getFront());
		caq3.dequeue(); // discard 0
		// Front should throw an exception when empty
		try { 
			caq3.getFront();
		} catch(EmptyQueueException e) {
			exception = true;
		}
		assertTrue(exception);

		// Testing queue of Doubles
		assertEquals(new Double (656.34), caq4.getFront());
		caq4.dequeue(); // discard 656.34
		assertEquals(new Double (-13.0), caq4.getFront()); // Front should change accordingly when entries are dequeued
		caq4.dequeue(); // discard -13.0
		assertEquals(new Double (4.1), caq4.getFront());
		caq4.dequeue(); // discard 4.1
		assertEquals(new Double (0.0), caq4.getFront());
		caq4.dequeue(); // discard 0.0
		// Front should throw an exception when empty
		try { 
			caq4.getFront();
		} catch(EmptyQueueException e) {
			exception = true;
		}
		assertTrue(exception);
	}

	@Test
	public void testIsEmpty() {
		// Not currently empty, but empty after clearing
		assertFalse(caq1.isEmpty());
		caq1.clear();
		assertTrue(caq1.isEmpty());

		// Empty queue
		assertTrue(caq2.isEmpty());

		// Not currently empty, but empty after clearing
		assertFalse(caq3.isEmpty());
		caq3.clear();
		assertTrue(caq3.isEmpty());

		// Not currently empty, but empty after clearing
		assertFalse(caq4.isEmpty());
		caq4.clear();
		assertTrue(caq4.isEmpty());
	}

	@Test
	public void testClear() {
		// Not currently empty, but empty after clearing
		assertFalse(caq1.isEmpty());
		caq1.clear();
		assertTrue(caq1.isEmpty());

		// Not currently empty, but empty after clearing
		assertFalse(caq3.isEmpty());
		caq3.clear();
		assertTrue(caq3.isEmpty());

		// Not currently empty, but empty after clearing
		assertFalse(caq4.isEmpty());
		caq4.clear();
		assertTrue(caq4.isEmpty());
	}

}
