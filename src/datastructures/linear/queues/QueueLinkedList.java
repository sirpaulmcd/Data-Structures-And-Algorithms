package src.datastructures.linear.queues;

/**
 * This class outlines the basic structure/functionality of a queue implemented
 * using a singly linked list. This code was modified from:
 * https://www.geeksforgeeks.org/queue-linked-list-implementation/
 */
public class QueueLinkedList 
{
    /**
     * This class represents one node of the queues linked list. For 
     * simplicity, I have left out getter and setter functions. Remember to use 
     * good practice when implementing your own value stuctures. 
     */
    public class Node 
    { 
        //=====================================================================
        // Instance variables
        //=====================================================================
        /**
         * The data stored in the node. In this case, an Integer. However, 
         * depending on what you want, this could be changed to another data 
         * type. Even your own custom data type.
         */
        public Integer data; 
        /**
         * The next node in the linked list.
         */
        public Node next; 

        //=====================================================================
        // Consturctors
        //=====================================================================
        /**
         * Constructs a node with the input data.
         */
        public Node(Integer data) 
        { 
            this.data = data; 
            this.next = null;
        } 
    }

    //=========================================================================
    // Instance variables
    //=========================================================================
    /**
     * The node corresponding to the front of the queue.
     */
    private Node front;
    /**
     * The node corresponding to the back of the queue.
     */
    private Node rear; 

    //=========================================================================
    // Constructors
    //=========================================================================
    /**
     * Constructs a queue using a linked list for storage.
     */
    public QueueLinkedList() 
    { 
        this.front = this.rear = null; 
    } 
  
    //=========================================================================
    // Public methods
    //=========================================================================
    /**
     * Adds an element to the back of the queue.
     * @param value The value to be added to the queue.
     */
    public void enqueue(int value) 
    {
        Node newNode = new Node(value);
        if (isEmpty()) { front = rear = newNode; }
        else
        {
            rear.next = newNode;
            rear = rear.next;
        }
    } 

    /**
     * Removes an element from the front of the queue.
     */
    public Integer dequeue() 
    { 
        if (isEmpty()) { return null; }
        Node removedNode = front;
        if (front.next != null) { front = front.next; }
        else { front = rear = null; }
        return removedNode.data;
    }

    /**
     * Peeks at the front element of the queue.
     * @return The front element of the queue if it exists, null otherwise.
     */
    public Integer peek()
    {
        if (isEmpty()) { return null; }
        return front.data;
    }

    /**
     * Searches the queue for the input value.
     * @param value The value to be searched for.
     * @return The index of the value if found, -1 otherwise.
     */
    public Integer search(Integer value)
    {
        Node cursor = front;
        int i = 0;
        while (cursor != null && cursor.data != value)
        {
            cursor = cursor.next;
            i++;
        }
        if (cursor == null) { return null; }
        else { return i; }
    }

    /**
     * Checks whether the queue is empty.
     * @return True if the queue is empty, false otherwise.
     */
    public boolean isEmpty()
    {
        return front == null || rear == null;
    }

    /**
     * Prints the contents of the queue.
     */
    public void print()
    {
        if (isEmpty()) { return; }
        Node cursor = front;
        while (cursor != null)
        {
            System.out.print(cursor.data);
            if (cursor.next != null) { System.out.print(" < "); }
            cursor = cursor.next;
        }
        System.out.println("\n");
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
        QueueLinkedList queue = new QueueLinkedList();
        // Insertion to rear of queue: O(1)
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        System.out.println("Initial queue:");
        queue.print();
        // Access element at front of queue: O(1)
        System.out.println("Peeking (i.e. accessing) the front element of the queue (should be 1):");
        System.out.println(queue.peek() + "\n");
        // Search: O(n)
        System.out.println("Searching for the index of the value 3 (should be 2):");
        System.out.println(queue.search(3)  + "\n");
        // Insertion to rear of queue: O(1)
        System.out.println("Enqueuing (i.e. inserting) a value of 42 to rear of the queue:");
        queue.enqueue(42);
        queue.print();
        // Deletion from front of queue: O(1)
        System.out.println("Dequeuing (i.e. deleting) two values from the front of the queue:");
        queue.dequeue();
        queue.dequeue();
        queue.print();
    }
}
