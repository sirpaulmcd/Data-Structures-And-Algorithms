package src.datastructures.linear.queues;

/**
 * This class outlines the basic structure/functionality of a Queue.
 * This code was modified from: https://www.techiedelight.com/queue-implementation-in-java/
 */
public class QueueArray
{
    //=========================================================================
    // Instance variables
    //=========================================================================
    /**
     * The array used to store data. The integer class is used rather than the
     * primitive data type because it allows null values.
     */
    private Integer[] elements;
    /**
     * The index corresponding to the front element of the queue.
     */
    private int front;
    /**
     * The index corresponding to the back element of the queue.
     */
    private int rear;
    /**
     * The capacity of the queue (i.e. array).
     */
    private int capacity;
    /**
     * The current size of the queue.
     */
    private int size;
    
    //=========================================================================
    // Constructors
    //=========================================================================
    /**
     * Constructs a stack of input size using an array for storage.
     * @param size The desired size of the queue.
     */
    public QueueArray(int size)
    {
        capacity = size;
        elements = new Integer[size];
        front = -1;
        rear = -1;
        size = 0;
    }
 
    /**
     * Removes an element from the front of the queue. Note that 
     * `front+1 % capacity` will reset front to 0 if it exceeds the maximum 
     * array index.
     */
    public Integer dequeue()
    {
        if (isEmpty()) { return null; }
        Integer removedElement = front;
        // If dequeuing last element, reset front and back to -1
        if (front == rear) { front = rear = -1; }
        // Otherwise, increment front
        else { front = (front + 1) % capacity; }
        size--;
        return removedElement;
    }
 
    /**
     * Adds an element to the back of the queue. Note that 
     * `rear+1 % capacity` will reset rear to 0 if it exceeds the maximum 
     * array index.
     * @param value The value to be added to the queue.
     */
    public void enqueue(Integer value)
    {
        if (isFull()) { increaseQueueCapacity(); }
        // In adding to empty queue, set front and rear indexes to 0
        else if (isEmpty()) { front = rear = 0; }
        // Otherwise, increment rear
        else { rear = (rear + 1) % capacity; }
        elements[rear] = value;
        size++;
    }
 
    /**
     * Peeks at the front element of the queue.
     * @return The front element of the queue if it exists, null otherwise.
     */
    public Integer peek()
    {
        if (isEmpty()) { return null; }
        return elements[front];
    }

    /**
     * Searches the queue for the input value.
     * @param value The value to be searched for.
     * @return The index of the value if found, -1 otherwise.
     */
    public Integer search(Integer value)
    {
        for (int i = front; i != rear; i = (i + 1) % capacity)
        {
            if (elements[i] == value) { return i; }
        }
        if (elements[rear] == value) { return rear; }
        else { return -1; }
    }
 
    /**
     * Checks the size of the queue.
     * @return The size of the queue.
     */
    public int size()
    {
        return size;
    }
 
    /**
     * Checks whether the queue is empty.
     * @return True if the queue is empty, false otherwise.
     */
    public Boolean isEmpty()
    {
        return (size() == 0);
    }
 
    /**
     * Checks whether the stack is empty.
     * @return True if the stack is empty, false otherwise.
     */
    public Boolean isFull()
    {
        return (size() == capacity);
    }

    /**
     * Turns the contents of the queue into a string for printing.
     */
    public String toString()
    {
        if (isEmpty()) { return "Cannot print empty queue."; }
        String s = "";
        for (int i = front; i != rear; i = (i + 1) % capacity)
        {
            s += elements[i] + " > ";
        }
        s += elements[rear];
        return s;
    }

    //=========================================================================
    // Private methods
    //=========================================================================
    /**
     * Doubles the capacity of the storage array.
     */
    private void increaseQueueCapacity()
    {
        capacity *= 2;
        Integer[] newArray = new Integer[capacity];
        for (int i = 0; i < elements.length; i++)
        {
            newArray[i] = elements[i];
        }
        elements = newArray;
    }
    
    //=========================================================================
    // Main
    //=========================================================================
    /**
     * Main method to show off Stack functionality.
     */
    public static void main (String[] args)
    {
        // Creating and populating the data structure
        QueueArray queue = new QueueArray(10);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        System.out.println("Initial queue: " + "\n" + queue + "\n");
        // Access: O(n)
        System.out.println("Peeking (i.e. accessing) the front element of the queue (should be 1): " + "\n" + queue.peek() + "\n");
        // Search: O(n)
        System.out.println("Searching for the index of the value 3 (should be 2): " + "\n" + queue.search(3) + "\n");
        // Insertion: O(1)
        System.out.println("Enqueuing (i.e. inserting) a value of 42 to rear of the queue:");
        queue.enqueue(42);
        System.out.println(queue + "\n");
        // Deletion: O(1)
        System.out.println("Dequeuing (i.e. deleting) two values from the front of the queue:");
        queue.dequeue();
        queue.dequeue();
        System.out.println(queue + "\n");
    }
}
