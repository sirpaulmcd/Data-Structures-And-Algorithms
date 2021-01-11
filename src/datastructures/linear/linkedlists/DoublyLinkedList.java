package src.datastructures.linear.linkedlists;

/**
 * This class outlines the basic structure/functionality of a doubly linked 
 * list. The code is very similar to the singly linked list. However, when 
 * searching for an indexed/valued node, the previous node to the cursor no 
 * longer has to be stored. Additionally, when setting up connections between 
 * nodes, the previous node must also be considered. The following code was 
 * modified from:
 * https://www.geeksforgeeks.org/implementing-a-linked-list-in-java-using-class/
 */
public class DoublyLinkedList 
{
    /**
     * This class represents a node of the doubly linked list. For simplicity, 
     * I have left out getter and setter functions. Remember to use good 
     * practice when implementing your own value stuctures. 
     */
    public class Node 
    { 
        //=====================================================================
        // Instance variables
        //=====================================================================
        /**
         * The value stored in the node. In this case, a String. However, 
         * depending on what you want, this could be changed to another value 
         * type. Even your own custom value type.
         */
        public String value;
        /**
         * The next node in the linked list.
         */
        public Node next;
        /**
         * The previous node in the linked list.
         */
        public Node prev;

        //=====================================================================
        // Consturctors
        //=====================================================================
        /**
         * Constructs a node with the input value.
         */
        public Node(String value) 
        { 
            this.value = value; 
            this.next = null;
            this.prev = null;
        } 
    }

    //=========================================================================
    // Instance variables
    //=========================================================================
    /**
     * The first node of the list.
     */
    private Node head;
    /**
     * The last node of the list. This field isn't totally necessary but
     * linked lists typically have the option to insert to either the front
     * or the back of the list.
     */
    private Node tail;

    //=========================================================================
    // Constructors
    //=========================================================================
    /**
     * Constructs an empty LinkedList object.
     */
    public DoublyLinkedList()
    {
        this.head = null;
        this.tail = null;
    }

    //=========================================================================
    // Public methods
    //=========================================================================
    /**
     * Given an index, accesses the corresponding element.
     * @param index The index of the desired element.
     * @return The element corresponding to the input index if valid, null 
     * otherwise.
     */
    public String access(int index)
    {
        if (isEmpty()) { return null; }
        Node indexedNode = getIndexedNode(index);
        return indexedNode.value;
    }

    /**
     * Given a value, sequentially seaches for a match.
     * @param value The value to search for.
     * @return True if match found, false otherwise.
     */
    public boolean search(String value)
    {
        if (isEmpty()) { return false; }
        Node valuedNode = getValuedNode(value);
        if (valuedNode == null) { return false; }
        else { return true; }
    }

    /**
     * Inserts a new value node to the end of the list.
     * @param value The value corresponding to the new node.
     */
    public void insert(String value)
    {
        if (isEmpty()) { initHeadNode(value); }
        else { insertNode(tail, value); } 
    }

    /**
     * Inserts a value into the list at the specified index.
     * @param index Index for the insertion.
     * @param value The value to be inserted.
     */
    public void insert(int index, String value)
    {
        // Case 1) List empty
        if (isEmpty()) { initHeadNode(value); }
        // Case 2) Insertion at head node
        else if (index == 0) { replaceHeadNode(value); }
        // Case 3) Insertion further in the list
        else if (index > 0)
        {
            Node indexedNode = getIndexedNode(index);
            if (indexedNode != null) { insertNode(indexedNode.prev, value); }
        }
    }

    /**
     * Deletes a node of the input value from the LinkedList if it exists.
     * @param value The value corresponding to the node to be removed.
     * @return The value of the removed node if any, null otherwise.
     */
    public String delete(String value)
    {
        if (isEmpty()) { return null; }
        // Sequentially search the array for a node of matching criteria
        Node valuedNode = getValuedNode(value);
        // Case 1) Not match found
        if (valuedNode == null) { return null; }
        // Case 2) Removed value is head node
        else if (valuedNode == head) { return removeHeadNode(); }
        // Case 3) Removed value is further down the list
        else { return removeTypicalNode(valuedNode); }
    }

    /**
     * Deletes a node of the input index from the LinkedList if it exists.
     * @param index The index of the node to be removed.
     * @param value The value corresponding to the node to be removed.
     * @return The value of the removed node if any, null otherwise.
     */
    public String delete(int index)
    {
        if (isEmpty()) { return null; }
        // Case 1) Removed value is at head
        if (index == 0) { return removeHeadNode(); }
        // Case 2) Removed value is further in the list
        Node indexedNode = getIndexedNode(index);
        if (indexedNode == null) { return null; }
        else { return removeTypicalNode(indexedNode); }
    }

    /**
     * Prints the contents of the linked list.
     */
    public void print()
    {
        if (isEmpty()) { return; }
        Node cursor = head;
        while (cursor != null)
        {
            System.out.print("[" + cursor.value + "]");
            if (cursor.next != null) { System.out.print("->");; }
            cursor = cursor.next;
        }
        System.out.println("\n");
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
     * Gets the node corresponding to the input index (if any).
     * @param index The index of the node to be returned.
     * @return The node corresponding to the input index if found. Null
     * otherwise.
     */
    private Node getIndexedNode(int index)
    {
        if (index < 0) { return null; }
        Node cursor = head;
        for (int i = 0; i < index; i++)
        {
            if (cursor == null) { break; }
            else { cursor = cursor.next; }
        }
        return cursor;
    }

    /**
     * Gets the node corresponding to the input value (if any).
     * @param value The value of the node to be found.
     * @return The node corresponding to the input value if found. Null
     * otherwise.
     */
    private Node getValuedNode(String value)
    {
        Node cursor = head;
        while (cursor != null && cursor.value != value)
        {
            cursor = cursor.next;
        }
        return cursor;
    }

    /**
     * Creates a new node and appends it to the input node.
     * @param prevNode The previous node in the LinkedList to the new node.
     * @param value The value corresponding to the new node.
     */
    private void insertNode(Node prevNode, String value)
    {
        Node newNode = new Node(value);
        Node nextNode = prevNode.next;
        newNode.next = nextNode;
        newNode.prev = prevNode;
        prevNode.next = newNode;
        if (nextNode != null) { nextNode.prev = newNode; }
        else { tail = newNode; }
    }

    /**
     * Creates a new node and makes it the head of the LinkedList.
     * @param value The value corresponding to the new node.
     */
    private void initHeadNode(String value)
    {
        Node newNode = new Node(value); 
        head = newNode;
        tail = newNode;
    }

    /**
     * Creates a new node and makes it the head of the LinkedList. Makes the
     * previous head the next element in the LinkedList.
     * @param value
     */
    private void replaceHeadNode(String value)
    {
        Node newNode = new Node(value);
        if (head != null) { newNode.next = head; }
        head = newNode;
    }

    /**
     * Removes the head node from the LinkedList. The second node becomes the
     * new head node.
     * @return The value of the removed node.
     */
    private String removeHeadNode()
    {
        Node removedNode = head;
        head = head.next;
        if (head != null) { head.prev = null; } 
        else { tail = null; }
        return removedNode.value;
    }

    /**
     * Removes a non-head node from the LinkedList.
     * @param nodeToRemove The node to be removed.
     * @return The value of the removed node.
     */
    private String removeTypicalNode(Node nodeToRemove)
    {
        Node removedNode = nodeToRemove;
        Node prevNode = nodeToRemove.prev;
        Node nextNode = nodeToRemove.next;
        if (removedNode == tail) { tail = prevNode; }
        prevNode.next = removedNode.next;
        if (nextNode != null) { nextNode.prev = removedNode.prev; } 
        return removedNode.value;
    }

    //=========================================================================
    // Main
    //=========================================================================
    /**
     * Main method to show off LinkedList functionality.
     */
    public static void main(String[] args) 
    {
        // Creating and populating the value structure
        DoublyLinkedList doublyLinkedList = new DoublyLinkedList();
        // Insertion: O(1)
        doublyLinkedList.insert("A");
        doublyLinkedList.insert("B");
        doublyLinkedList.insert("C");
        doublyLinkedList.insert("D");
        doublyLinkedList.insert("E");
        doublyLinkedList.insert("F");
        doublyLinkedList.insert("G");
        System.out.println("Initial list:");
        doublyLinkedList.print();
        // Access: O(n)
        System.out.println("Accessing element at index 0 (should be A):");
        System.out.println(doublyLinkedList.access(0) + "\n");
        // Search: O(n)
        System.out.println("Searching if \"F\" is in list (should be true):");
        System.out.println(doublyLinkedList.search("F") + "\n");
        System.out.println("Searching if \"Q\" is in list (should be false):");
        System.out.println(doublyLinkedList.search("Q") + "\n");
        // Search: O(n) + Insertion: O(1)
        System.out.println("Inserting value of \"H\" into index 4:");
        doublyLinkedList.insert(4, "H");
        doublyLinkedList.print();
        // Search: O(n) + Deletion: O(1)
        System.out.println("Deleting element of value \"C\":");
        doublyLinkedList.delete("C");
        doublyLinkedList.print();
        System.out.println("Deleting element of index 5 (should be F):");
        doublyLinkedList.delete(5);
        doublyLinkedList.print();
    }
}