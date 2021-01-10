package src.datastructures.linear.arrays;

/**
 * This class outlines the basic structure/functionality of an ArrayList.
 */
public class ArrayList<T> 
{
    //=========================================================================
    // Instance variables
    //=========================================================================
    /**
     * The number of elements currently stored in the ArrayList.
     */
    private int size;
    /**
     * The capacity of the storage array.
     */
    private int capacity;
    /**
     * The array of elements for storage.
     */
    private Object[] elements;

    //=========================================================================
    // Constructors
    //=========================================================================
    /**
     * Constructs a new ArrayList. A java ArrayList has a default capacity of
     * 10.
     */
    public ArrayList() 
    {
        this.size = 0;
        this.capacity = 10;
        this.elements = new Object[10];
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
    public Object access(int index)
    {
        if (isExistingIndex(index)) { return elements[index]; }
        return null;
    }

    /**
     * Given a value, sequentially seaches the array for a match.
     * @param value The value to search for in the array.
     * @return The index of the found value (if any), -1 otherwise.
     */
    public int search(Object value)
    {
        int index = -1;
        for (int i = 0; i < size; i++)
        {
            if (elements[i] == value)
            {
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     * Appends an element to the end of the ArrayList.
     * @param value The value to be inserted.
     */
    public void insert(Object value)
    {
        insert(size, value);
    }

    /**
     * Adds an element into the array at the specified index.
     * @param index The index of insertion.
     * @param value The value to be inserted.
     */
    public void insert(int index, Object value)
    {
        if (index == size) { insertAtEnd(value); }
        else if (!isExistingIndex(index)) { return; }
        else { insertInMiddle(index, value); }
        ensureCapacity();
    }

    /**
     * Removes the input value from the array if it exists.
     * @param value The value to be removed.
     * @return The removed value if it exists, null otherwise.
     */
    public Object delete(Object value)
    {
        int index = search(value);
        if (index == -1) { return null; }
        return delete(index);
    }

    /**
     * Removes an element from the array at the input index.
     * @param index The index of the value to be removed.
     * @return The removed value.
     */
    public Object delete(int index)
    {
        if (!isExistingIndex(index)) { return null; }
        Object deletedValue = access(index);
        if (index == size) { removeFromEnd(); }
        else { removeFromMiddle(index); }
        return deletedValue;
    }

    /**
     * Prints the contents of the ArrayList.
     */
    public void print()
    {
        if (size <= 0) { return; }
        System.out.print("[");
        for (int i = 0; i < size - 1; i++)
        {
            System.out.print(elements[i] + ", ");
        }
        System.out.println(elements[size - 1] + "]\n");
    }

    //=========================================================================
    // Private methods
    //=========================================================================
    /**
     * Checks whether the input index is exists in the ArrayList.
     * @param index The index to be checked.
     * @return True if index exists, false otherwise.
     */
    private boolean isExistingIndex(int index)
    {
        return index > 0 || index < size;
    }

    /**
     * Inserts the input object at the end of the ArrayList.
     * @param value The value to be inserted.
     */
    private void insertAtEnd(Object value)
    {
        elements[size] = value;
        size++; 
    }

    /**
     * Inserts a value in the middle of the ArrayList.
     * @param index The index of the insertion position.
     * @param value The value to be inserted.
     */
    private void insertInMiddle(int index, Object value)
    {
        // Move move elements past and including index up one slot
        for (int i = size; i > index; i--)
        {
            elements[i] = elements[i - 1];
        }
        // Insert new value at index and increase size
        elements[index] = value;
        size++;
    }

    /**
     * Removes an element from the end of the ArrayList.
     */
    private void removeFromEnd()
    {
        elements[size] = null;
        size--;
    }

    /**
     * Removes indexed element from the ArrayList.
     * @param index The index of the element to be removed.
     */
    private void removeFromMiddle(int index)
    {
        // Remove value at index and decrease size
        elements[index] = null;
        size--;
        // Move move elements past index down one slot
        for (int i = index; i < size; i++)
        {
            elements[i] = elements[i + 1];
        }
    }

    /**
     * Ensure the array has enough capcity to store more data. If capacity is
     * reached, increases capacity. Note that Java ArrayLists don't double in 
     * like a Vector. Instead, capacity increases using the below formula.
     */
    private void ensureCapacity()
    {
        if (size == capacity)
        {
            // Create larger array
            int newCapacity = (capacity * 3)/2 + 1;
            Object[] newArray = new Object[newCapacity];
            // Copy values from old array to new array
            for (int i = 0; i < capacity; i++)
            {
                newArray[i] = elements[i];
            }
            // Overwrite with updated values
            elements = newArray;
            capacity = newCapacity;
        }
    }

    //=========================================================================
    // Main
    //=========================================================================
    /**
     * Main method to show off ArrayList functionality.
     */
    public static void main(String[] args) 
    {
        // Creating and populating the data structure
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.insert("A");
        arrayList.insert("B");
        arrayList.insert("C");
        arrayList.insert("D");
        arrayList.insert("E");
        arrayList.insert("F");
        arrayList.insert("G");
        System.out.println("Initial list:");
        arrayList.print();
        // Access: O(1)
        System.out.println("Accessing element at index 0 (should be A):");
        System.out.println(arrayList.access(0) + "\n");
        // Search: O(n)
        System.out.println("Searching for the index of \"F\" (should be 5):");
        System.out.println(arrayList.search("F") + "\n");
        // Insertion: O(n)
        System.out.println("Inserting value of \"H\" into index 4:");
        arrayList.insert(4, "H");
        arrayList.print();
        // Deletion: O(n)
        System.out.println("Removing element of value \"C\":");
        arrayList.delete("C");
        arrayList.print();
    }
}
