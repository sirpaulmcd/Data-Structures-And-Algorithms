package src.datastructures.linear.stacks;

/**
 * This class outlines the basic structure/functionality of a Stack.
 * This code was modified from: https://www.techiedelight.com/stack-implementation-in-java/
 */
public class StackArray
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
     * The index corresponding to the top of the stack.
     */
    private int top;
    /**
     * The capacity of the stack (i.e. array).
     */
    private int capacity;
 
    //=========================================================================
    // Constructors
    //=========================================================================
    /**
     * Constructs a stack of input size using an array for storage.
     * @param size The desired size of the stack.
     */
    public StackArray(int size)
    {
        capacity = size;
        elements = new Integer[size];
        top = -1;
    }

    //=========================================================================
    // Public methods
    //=========================================================================
    /**
     * Pushes a value to the top of the stack. Increases capacity if storage
     * maxed out.
     * @param value The value to be inserted into the stack.
     */
    public void push(Integer value)
    {
        if (isFull()) { increaseStackCapacity(); }
        elements[++top] = value;
    }
 
    /**
     * Pops a value from the top of the stack.
     */
    public Integer pop()
    {
        if (isEmpty()) { return null; }
        else { return elements[top--]; } 
    }
    
    /**
     * Peeks at the top element of the stack.
     * @return The top element of the stack if it exists, null otherwise.
     */
    public Integer peek()
    {
        if (isEmpty()) { return null; }
        else { return elements[top]; }
    }

    /**
     * Searches the stack for the input value.
     * @param value The value to be searched for.
     * @return The index of the value if found, -1 otherwise.
     */
    public Integer search(Integer value)
    {
        for (int i = 0; i <= top; i++)
        {
            if (elements[i] == value) { return i; }
        }
        return -1;
    }
 
    /**
     * Checks the size of the stack.
     * @return The size of the stack.
     */
    public int size()
    {
        return top + 1;
    }

    /**
     * Checks whether the stack is empty.
     * @return True if the stack is empty, false otherwise.
     */
    public Boolean isEmpty()
    {
        return size() == 0;
    }
 
    /**
     * Checks whether the stack is full.
     * @return True if the stack is full, false otherwise.
     */
    public Boolean isFull()
    {
        return size() == capacity;
    }


    /**
     * Turns the contents of the stack into a string for printing.
     */
    public String toString()
    {
        if (isEmpty()) { return "Cannot print empty stack."; }
        String s = "";
        for (int i = 0; i <= top; i++)
        {
            s += elements[i];
            if (i != top) { s += " > "; }
        }
        return s;
    }

    //=========================================================================
    // Private methods
    //=========================================================================
    /**
     * Doubles the capacity of the storage array.
     */
    private void increaseStackCapacity()
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
        StackArray stack = new StackArray(10);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        System.out.println("Initial stack: " + "\n" + stack + "\n");
        // Access: O(n)
        System.out.println("Peeking (i.e. accessing) the top element of the stack (should be 5): " + "\n" + stack.peek() + "\n");
        // Search: O(n)
        System.out.println("Searching for the index of the value 3 (should be 2): " + "\n" + stack.search(3) + "\n");
        // Insertion: O(1)
        System.out.println("Pushing (i.e. inserting) a value of 42 to the top of the stack:");
        stack.push(42);
        System.out.println(stack + "\n");
        // Deletion: O(1)
        System.out.println("Popping (i.e. deleting) two values from the top of the stack:");
        stack.pop();
        stack.pop();
        System.out.println(stack + "\n");
    }
}
