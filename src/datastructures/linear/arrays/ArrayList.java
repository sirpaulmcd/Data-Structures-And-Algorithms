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
     * The array of elements.
     */
    private Object[] elements;

    //=========================================================================
    // Constructors
    //=========================================================================
    /**
     * Constructs a new ArrayList.
     */
    public ArrayList() 
    {
        elements = new Object[0];
    }

    //=========================================================================
    // Public methods
    //=========================================================================
    /**
     * Gets the element corresponding to the input index.
     * @param index The index corresponding to the desired element.
     * @return The element corresponding to the input index.
     */
    public Object get(int index)
    {
        return elements[index];
    }

    /**
     * Sequentially seaches the array and returns the index of the input value
     * if found.
     * @param value The value to search for in the array.
     * @return The index of the found value (if any), -1 otherwise.
     */
    public int indexOf(Object value)
    {
        int index = -1;
        for (int i = 0; i < elements.length; i++)
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
    public void add(Object value)
    {
        add(elements.length, value);
    }

    /**
     * Adds an element into the array at the specified index.
     * @param index The index of insertion.
     * @param value The value to be inserted.
     */
    public void add(int index, Object value)
    {
        // Create new array with one extra element
        Object[] newArray = new Object[elements.length + 1];
        // Copy values before index into new array
        for (int i = 0; i < index; i++)
        {
            newArray[i] = elements[i];
        }
        // Insert value at index into new array
        newArray[index] = value;
        // Copy values after index into new array
        for (int i = index + 1; i < newArray.length; i++)
        {
            newArray[i] = elements[i - 1];
        }
        // Overwrite old array with new array.
        elements = newArray;
    }

    /**
     * Removes the input value from the array if it exists.
     * @param value The value to be removed.
     * @return The removed value if it exists, null otherwise.
     */
    public Object remove(Object value)
    {
        // Search ArrayList for value matching input
        int index = indexOf(value);
        // If the value doesn't exist, return null
        if (index < 0) { return null; }
        // If value does exist, remove it
        return remove(index);
    }

    /**
     * Removes an element from the array at the input index.
     * @param index The index of the value to be removed.
     * @return The removed value.
     */
    public Object remove(int index)
    {
        // Check that index is valid
        if (index >= elements.length) { return null; }
        // Store value to be removed
        Object deletedValue = get(index);
        // Shift all elements after the index down by one
        for (int i = index + 1; i < elements.length; i++)
        {
            elements[i - 1] = elements[i];
        }
        // Copy elements into array of one size smaller
        Object[] newArray = new Object[elements.length - 1];
        for (int i = 0; i < newArray.length; i++)
        {
            newArray[i] = elements[i];
        }
        // Overwrite old array with new array and return removed element
        elements = newArray;
        return deletedValue;
    }

    /**
     * Turns the contents of the ArrayList into a string for printing.
     */
    public String toString()
    {
        String s = "[";
        for (int i = 0; i < elements.length - 1; i++)
        {
            s = s + elements[i].toString() + ", ";
        }
        s = s + elements[elements.length - 1].toString() + "]";
        return s;
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
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");
        arrayList.add("D");
        arrayList.add("E");
        arrayList.add("F");
        arrayList.add("G");
        System.out.println("Initial list:");
        System.out.println(arrayList + "\n");
        // Access: O(1)
        System.out.println("Accessing element at index 0 (should be A):");
        System.out.println(arrayList.get(0) + "\n");
        // Search: O(n) - Sequential search
        System.out.println("Searching for the index of \"F\" (should be 5):");
        System.out.println(arrayList.indexOf("F") + "\n");
        // Insertion: O(n) - Must populate new array
        System.out.println("Inserting value of \"H\" into index 4:");
        arrayList.add(4, "H");
        System.out.println(arrayList + "\n");
        // Deletion: O(n) - Must populate new array
        System.out.println("Removing element of value \"C\":");
        arrayList.remove("C");
        System.out.println(arrayList + "\n");
    }
}
