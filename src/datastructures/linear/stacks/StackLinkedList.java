package src.datastructures.linear.stacks;

/**
 * This class outlines the basic structure/functionality of a stack that has
 * been implemented using a singly linked list. This code was modified from: 
 * https://www.geeksforgeeks.org/implement-a-stack-using-singly-linked-list/
 */
public class StackLinkedList 
{
    /**
     * This class represents one node of the stacks linked list. For 
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
     * The node corresponding to the top of the stack.
     */
    private Node top; 

    //=========================================================================
    // Constructors
    //=========================================================================
    /**
     * Constructs a stack using a linked list for storage.
     */
    public StackLinkedList() 
    { 
        this.top = null; 
    } 

    //=========================================================================
    // Public methods
    //=========================================================================
    /**
     * Pushes a value to the top of the stack.
     * @param value The value to be inserted into the stack.
     */
    public void push(Integer value)
    {
        Node newNode = new Node(value); 
        newNode.next = top;
        top = newNode; 
    } 

    /**
     * Pops a value from the top of the stack.
     */
    public Integer pop() 
    { 
        Node removedNode = top;
        if (top == null) { return null; }
        top = top.next;
        return removedNode.data;
    }

    /**
     * Peeks at the top element of the stack.
     * @return The top element of the stack if it exists, null otherwise.
     */
    public Integer peek() 
    {
        if (isEmpty()) { return null; }
        else { return top.data; } 
    } 

    /**
     * Searches the stack for the input value.
     * @param value The value to be searched for.
     * @return The index of the value if found, -1 otherwise.
     */
    public Integer search(Integer value)
    {
        if (isEmpty()) { return -1; }
        Node cursor = top;
        int i = 0;
        while (cursor != null && cursor.data != value)
        {
            cursor = cursor.next;
            i++;
        }
        if (cursor == null) { return -1; }
        else { return i; }
    }

    /**
     * Checks whether the stack is empty.
     * @return True if the stack is empty, false otherwise.
     */
    public boolean isEmpty() 
    { 
        return top == null; 
    } 
  
    /**
     * Prints the contents of the stack to the console.
     */
    public void print()
    {
        if (isEmpty()) { return; }
        Node cursor = top;
        while (cursor != null)
        {
            System.out.print(cursor.data);
            if (cursor.next != null) { System.out.print(" < ");}
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
        StackLinkedList stack = new StackLinkedList();
        // Insertion to top of stack: O(1)
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        System.out.println("Initial stack:");
        stack.print();
        // Access element at top of stack: O(n)
        System.out.println("Peeking (i.e. accessing) the top element of the stack (should be 5):");
        System.out.println(stack.peek() + "\n");
        // Search: O(n)
        System.out.println("Searching for the index of the value 3 (should be 2):");
        System.out.println(stack.search(3) + "\n");
        // Insertion to top of stack: O(1)
        System.out.println("Pushing (i.e. inserting) a value of 42 to the top of the stack:");
        stack.push(42);
        stack.print();
        // Deletion from top of stack: O(1)
        System.out.println("Popping (i.e. deleting) two values from the top of the stack:");
        stack.pop();
        stack.pop();
        stack.print();
    }
}
