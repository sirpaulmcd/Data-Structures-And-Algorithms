package src.datastructures.linear.linkedlists;

/**
 * This class outlines the basic structure/functionality of a singly linked 
 * list. The following code was modified from:
 * https://www.geeksforgeeks.org/implementing-a-linked-list-in-java-using-class/
 */
public class SinglyLinkedList 
{
    /**
     * This class represents a node of the singly linked list. For simplicity, 
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
    public SinglyLinkedList()
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
        Node[] nodeAndPrevNode = getIndexedNodeAndPrevNode(index);
        return nodeAndPrevNode[0].value;
    }

    /**
     * Given a value, sequentially seaches for a match.
     * @param value The value to search for.
     * @return True if match found, false otherwise.
     */
    public boolean search(String value)
    {
        if (isEmpty()) { return false; }
        Node[] nodeAndPrevNode = getValuedNodeAndPrevNode(value);
        if (nodeAndPrevNode[0] == null) { return false; }
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
            Node[] nodeAndPrevNode = getIndexedNodeAndPrevNode(index);
            if (nodeAndPrevNode[0] != null) { insertNode(nodeAndPrevNode[1], value); }
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
        Node[] nodeAndPrevNode = getValuedNodeAndPrevNode(value);
        // Case 1) Not match found
        if (nodeAndPrevNode[0] == null) { return null; }
        // Case 2) Removed value is head node
        else if (nodeAndPrevNode[0] == head) { return removeHeadNode(); }
        // Case 3) Removed value is further down the list
        else { return removeTypicalNode(nodeAndPrevNode[0], nodeAndPrevNode[1]); }
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
        Node[] nodeAndPrevNode = getIndexedNodeAndPrevNode(index);
        if (nodeAndPrevNode[0] == null) { return null; }
        else { return removeTypicalNode(nodeAndPrevNode[0], nodeAndPrevNode[1]); }
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
     * Gets the node corresponding to the input index (if any) and the previous
     * node.
     * @param index The index of the node to be returned.
     * @return If match found, array with node of matching index in the first 
     * index and previous node in the second index. If match not found, first
     * index will be null.
     */
    private Node[] getIndexedNodeAndPrevNode(int index)
    {
        if (index < 0) { return null; }
        Node cursor = head;
        Node prevNode = null;
        for (int i = 0; i < index; i++)
        {
            if (cursor == null) { break; }
            else 
            { 
                prevNode = cursor;
                cursor = cursor.next;
            }
        }
        Node[] nodeAndPrevNode = { cursor, prevNode };
        return nodeAndPrevNode;
    }

    /**
     * Gets the node corresponding to the input value (if any) and the previous 
     * node.
     * @param value The value of the node to be found.
     * @return If match found, array with node of matching value in the first 
     * index and previous node in the second index. If match not found, first
     * index will be null.
     */
    private Node[] getValuedNodeAndPrevNode(String value)
    {
        Node cursor = head;
        Node prevNode = null;
        while (cursor != null && cursor.value != value)
        {
            prevNode = cursor;
            cursor = cursor.next;
        }
        Node[] nodes = { cursor, prevNode };
        return nodes;
    }

    /**
     * Creates a new node and appends it to the input node.
     * @param prevNode The previous node in the LinkedList to the new node.
     * @param value The value corresponding to the new node.
     */
    private void insertNode(Node prevNode, String value)
    {
        Node newNode = new Node(value);
        newNode.next = prevNode.next;
        prevNode.next = newNode;
        if (newNode.next == null) { tail = newNode; }
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
        if (head == null) { tail = null; }
        return removedNode.value;
    }

    /**
     * Removes a non-head node from the LinkedList. Points the previous node
     * to the node after the removed node.
     * @param nodeToRemove The node to be removed.
     * @param prevNode The node directly before the node to be remove.
     * @return The value of the removed node.
     */
    private String removeTypicalNode(Node nodeToRemove, Node prevNode)
    {
        Node removedNode = nodeToRemove;
        if (removedNode == tail) { tail = prevNode; }
        prevNode.next = removedNode.next;
        removedNode.next = null;
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
        SinglyLinkedList singlyLinkedList = new SinglyLinkedList();
        // Insertion: O(1)
        singlyLinkedList.insert("A");
        singlyLinkedList.insert("B");
        singlyLinkedList.insert("C");
        singlyLinkedList.insert("D");
        singlyLinkedList.insert("E");
        singlyLinkedList.insert("F");
        singlyLinkedList.insert("G");
        System.out.println("Initial list:");
        singlyLinkedList.print();
        // Access: O(n)
        System.out.println("Accessing element at index 0 (should be A):");
        System.out.println(singlyLinkedList.access(0) + "\n");
        // Search: O(n)
        System.out.println("Searching if \"F\" is in list (should be true):");
        System.out.println(singlyLinkedList.search("F") + "\n");
        System.out.println("Searching if \"Q\" is in list (should be false):");
        System.out.println(singlyLinkedList.search("Q") + "\n");
        // Search: O(n) + Insertion: O(1)
        System.out.println("Inserting value of \"H\" into index 4:");
        singlyLinkedList.insert(4, "H");
        singlyLinkedList.print();
        // Search: O(n) + Deletion: O(1)
        System.out.println("Deleting element of value \"C\":");
        singlyLinkedList.delete("C");
        singlyLinkedList.print();
        System.out.println("Deleting element of index 5 (should be F):");
        singlyLinkedList.delete(5);
        singlyLinkedList.print();
    }
}