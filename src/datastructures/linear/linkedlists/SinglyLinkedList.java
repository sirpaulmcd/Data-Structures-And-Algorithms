package src.datastructures.linear.linkedlists;

/**
 * This class outlines the basic structure/functionality of a LinkedList. The 
 * following code was modified from:
 * https://www.geeksforgeeks.org/implementing-a-linked-list-in-java-using-class/
 */
public class SinglyLinkedList 
{
    /**
     * This class represents a node of the singly linked list. 
     */
    public class Node 
    { 
        //=====================================================================
        // Instance variables
        //=====================================================================
        /**
         * The data stored in the node. In this case, a String. However, 
         * depending on what you want, this could be changed to another data 
         * type. Even your own custom data type.
         */
        private String data; 
        /**
         * The next node in the linked list.
         */
        private Node next; 

        //=====================================================================
        // Getters and setters
        //=====================================================================
        public String getData() { return this.data; }
        public void setData(String data) { this.data = data; }
        public Node getNext() { return this.next; }
        public void setNext(Node next) { this.next = next; }

        //=====================================================================
        // Consturctors
        //=====================================================================
        /**
         * Constructs a node with the input data.
         */
        public Node(String data) 
        { 
            this.data = data; 
            this.next = null;
        } 
    }

    //=========================================================================
    // Instance variables
    //=========================================================================
    /**
     * The head (i.e. first element) of the list.
     */
    private Node head;

    //=========================================================================
    // Constructors
    //=========================================================================
    /**
     * Constructs an empty LinkedList object.
     */
    public SinglyLinkedList()
    {
        this.head = null;
    }

    //=========================================================================
    // Public methods
    //=========================================================================
    /**
     * Sequentially indexes the list for the node corresponding to the input 
     * index.
     * @param index The index corresponding to the desired element.
     * @return The node corresponding to the input index, null otherwise.
     */
    public Node get(int index)
    {
        if (isEmpty()) { return null; }
        // Sequentially search through list for node of input index
        Node cursor = head;
        for (int i = 0; i < index; i++)
        {
            if (cursor != null) { cursor = cursor.getNext(); }
        }
        return cursor;
    }

    /**
     * Sequentially seaches the list and returns the index of the input value
     * if found.
     * @param data The data of the node to be searched for.
     * @return The index of the found value (if any), -1 otherwise.
     */
    public int indexOf(String data)
    {
        if (isEmpty()) { return -1; }
        // Sequentially search list for node with matching criteria
        Node cursor = head;
        int i = 0;
        while (cursor != null && cursor.getData() != data)
        {
            cursor = cursor.getNext();
            i++;
        }
        // If cursor reached the end with no match, return -1
        if (cursor == null) { return -1; }
        // If match found, return index
        else { return i; }
    }

    /**
     * Adds a new data node to the list.
     * @param data The data corresponding to the new node.
     */
    public void add(String data)
    {
        // If LinkedList is empty, make the new node as head 
        if (isEmpty()) { initHeadNode(data); } 
        // Otherwise, append data to end of list
        else 
        { 
            Node cursor = head; 
            while (cursor.getNext() != null) { cursor = cursor.getNext(); }
            insert(cursor, data); 
        } 
    }

    /**
     * Adds a new data node to the list at the specified index.
     * @param index Index for the node to be inserted.
     * @param data The data corresponding to the new node.
     */
    public void add(int index, String data)
    {
        if (isEmpty()) { initHeadNode(data); }
        // If indexed node is head, replace head
        else if (index == 0) { replaceHeadNode(data); }
        else if (index > 0)
        {
            // Move cursor to the node before the indexed position
            Node cursor = head;
            for (int i = 0; i < index - 1; i++)
            {
                if (cursor != null) { cursor = cursor.getNext(); }
            }
            // If node exists on cursor, insert new node after cursor
            if (cursor != null) { insert(cursor, data); }
        }
    }

    /**
     * Removes a node of the input value from the LinkedList if it exists.
     * @param data The data corresponding to the node to be removed.
     * @return The removed node if any, null otherwise.
     */
    public Node remove(String data)
    {
        if (isEmpty()) { return null; }
        // Sequentially search the array for a node of matching criteria
        Node cursor = head;
        Node prevNode = null;
        while (cursor != null && cursor.getData() != data)
        {
            prevNode = cursor;
            cursor = cursor.getNext();
        }
        // If no results found, return null
        if (cursor == null) { return null; }
        // If cursor is head, make next element head
        else if (cursor == head) { return removeHeadNode(); }
        // If cursor is further in the list, delete node normally
        else { return removeTypicalNode(cursor, prevNode); }
    }

    /**
     * Removes a node of the input value from the LinkedList if it exists.
     * @param index The index of the node to be removed.
     * @param data The data corresponding to the node to be removed.
     * @return The removed node if any, null otherwise.
     */
    public Node remove(int index, String data)
    {
        if (isEmpty()) { return null; }
        // If index is for head, remove head
        if (index == 0) { return removeHeadNode(); }
        // Move cursor to indexed node
        Node cursor = head;
        Node prevNode = null;
        int i = 0;
        while (cursor != null && i < index)
        {
            prevNode = cursor;
            cursor = cursor.getNext();
            i++;
        }
        // If indexed node exists, remove node
        if (cursor != null) { return removeTypicalNode(cursor, prevNode); }
        // Otherwise, return null
        else { return null; }
    }

    /**
     * Turns the contents of the LinkedList into a string for printing.
     */
    public String toString()
    {
        String s = "";
        if (isEmpty()) { return s; }
        Node cursor = head;
        while (cursor != null)
        {
            s += "[" + cursor.getData() + "]";
            if (cursor.getNext() != null) { s += "->"; }
            cursor = cursor.getNext();
        }
        return s;
    }
  
    //=========================================================================
    // Private methods
    //=========================================================================
    /**
     * Checks whether or not the LinkedList is empty.
     * @return True if list is empty, false otherwise.
     */
    private boolean isEmpty()
    {
        return head == null;
    }

    /**
     * Creates a new node and appends it to the input node.
     * @param prevNode The previous node in the LinkedList to the new node.
     * @param data The data corresponding to the new node.
     */
    private void insert(Node prevNode, String data)
    {
        // Create new node with data
        Node newNode = new Node(data);
        // Point new node at previous nodes neighbors
        newNode.setNext(prevNode.getNext());
        // Point previous node to new node
        prevNode.setNext(newNode);
    }

    /**
     * Creates a new node and makes it the head of the LinkedList.
     * @param data The data corresponding to the new node.
     */
    private void initHeadNode(String data)
    {
        Node newNode = new Node(data); 
        head = newNode; 
    }

    /**
     * Creates a new node and makes it the head of the LinkedList. Makes the
     * previous head the next element in the LinkedList.
     * @param data
     */
    private void replaceHeadNode(String data)
    {
        Node newNode = new Node(data);
        if (head != null) { newNode.setNext(head); }
        head = newNode;
    }

    /**
     * Removes the head node from the LinkedList. The second node becomes the
     * new head node.
     * @return
     */
    private Node removeHeadNode()
    {
        Node removedNode = new Node(head.getData());
        head = head.getNext();
        return removedNode;
    }

    /**
     * Removes a non-head node from the LinkedList. Points the previous node
     * to the node after the removed node.
     * @param nodeToRemove The node to be removed.
     * @param prevNode The node directly before the node to be remove.
     * @return The removed node.
     */
    private Node removeTypicalNode(Node nodeToRemove, Node prevNode)
    {
        Node removedNode = nodeToRemove;
        prevNode.setNext(nodeToRemove.getNext());
        removedNode.setNext(null);
        return removedNode;
    }

    //=========================================================================
    // Main
    //=========================================================================
    /**
     * Main method to show off LinkedList functionality.
     */
    public static void main(String[] args) 
    {
        // Creating and populating the data structure
        SinglyLinkedList singlyLinkedList = new SinglyLinkedList();
        singlyLinkedList.add("A");
        singlyLinkedList.add("B");
        singlyLinkedList.add("C");
        singlyLinkedList.add("D");
        singlyLinkedList.add("E");
        singlyLinkedList.add("F");
        singlyLinkedList.add("G");
        System.out.println("Initial list:");
        System.out.println(singlyLinkedList + "\n");
        // Access: O(n) - Sequential search
        System.out.println("Accessing element at index 0 (should be A):");
        System.out.println(singlyLinkedList.get(0).getData() + "\n");
        // Search: O(n) - Sequential search
        System.out.println("Searching for the index of \"F\" (should be 5):");
        System.out.println(singlyLinkedList.indexOf("F") + "\n");
        // Insertion: O(n) - Can be O(1) if you have a reference to the previous node
        System.out.println("Inserting value of \"H\" into index 4:");
        singlyLinkedList.add(4, "H");
        System.out.println(singlyLinkedList + "\n");
        // Deletion: O(n) - Can be O(1) if you have a reference to the previous node
        System.out.println("Removing element of value \"C\":");
        singlyLinkedList.remove("C");
        System.out.println(singlyLinkedList + "\n");
    }
}