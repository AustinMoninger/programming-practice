
/**
 * The circular array queue implementation is advantageous because
 * entries do not have to be moved after enqueueing or dequeueing.
 * Since removing leaves space at the beginning of the array, that
 * space can be used for future additions, making the array "circular".
 */
public class CircularArrayQueue<T> 
				implements QueueInterface<T> {

	private T[] queue;
	private int frontIndex;
	private int backIndex;
	private boolean initialized = false;
	private static final int DEFAULT_CAPACITY = 50;
	private static final int MAXIMUM_CAPACITY = 10000;
	
	public CircularArrayQueue() {
		this(DEFAULT_CAPACITY);
	}
	
	@SuppressWarnings("unchecked")
	public CircularArrayQueue(int initialCapacity) {
		checkCapacity(initialCapacity);
		queue = (T[]) new Object[initialCapacity + 1];
		frontIndex = 0;
		backIndex = initialCapacity;
		initialized = true;
	}
	
	/**
	 * Throws an exception if the client requests a capacity that is too large
	 * @param initialCapacity Size of CircularArrayQueue
	 */
	private void checkCapacity(int initialCapacity) {
		if (initialCapacity > MAXIMUM_CAPACITY)
            throw new IllegalStateException("Attempt to create a queue whose " + 
                                            "capacity exceeds allowed " +  
                                            "maximum of " + MAXIMUM_CAPACITY);
	}

	/**
	 * Adds new entry to back of this queue
	 * @param newEntry An object to be added
	 */
	@Override
	public void enqueue(T newEntry) {
		checkInitialization();
		ensureCapacity(); 
		backIndex = (backIndex + 1) % queue.length; 
		queue[backIndex] = newEntry;
	}

	/**
	 * Doubles the size of the array queue if it is full.
     * Precondition: checkInitialization has been called
	 */
	private void ensureCapacity() {
		if(frontIndex == (backIndex + 2) % queue.length) { 
			T[] oldQueue = queue;
			int newSize = 2 * oldQueue.length; 
			checkCapacity(newSize - 1);
			// Double capacity
			@SuppressWarnings("unchecked")
			T[] tempQueue = (T[]) new Object[newSize];
			queue = tempQueue;
			// Copy contents
			for(int i = 0; i < oldQueue.length - 1; i++) {
				queue[i] = oldQueue[frontIndex];
				frontIndex = (frontIndex + 1) % oldQueue.length; 
			}
			frontIndex = 0;
			backIndex = oldQueue.length - 2; // accounts for empty space
		}
	}

	/**
	 * Throws an exception if this object is not initialized.
	 */
	private void checkInitialization() {
		if (!initialized)
	         throw new SecurityException("Queue is not initialized properly.");
	}

	/**
	 * Removes and returns the entry at the front of this queue
	 * @return The object at the front of the queue
	 * @throws EmptyQueueException if queue is empty
	 */
	@Override
	public T dequeue() {
		T temp = getFront();
		queue[frontIndex] = null; // new empty space
		frontIndex = (frontIndex + 1) % queue.length; 
		return temp;
	}

	/**
	 * Returns entry at front of this queue without modifying it
	 * @return The object at the front of the queue
	 * @throws EmptyQueueException if queue is empty
	 */
	@Override
	public T getFront() {
		checkInitialization();
		if(isEmpty())
			throw new EmptyQueueException();
		else
			return queue[frontIndex];
	}

	/**
	 * Detects whether the queue is empty
	 * @return True if the queue is empty, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		return frontIndex == (backIndex + 1) % queue.length; 
	}

	/**
	 * Removes all entries from this queue
	 */
	@Override
	public void clear() {
		while (!isEmpty())
			dequeue();
	}
}
