package src.datastructures.tree.heaps;

/**
 * This class outlines the basic structure/functionality of a min heap. Logic 
 * for a max heap is very similar. 
 * The following code is modified from: 
 * https://www.youtube.com/watch?v=t0Cq6tVNRBA
 */
public class MinHeap 
{
    //=========================================================================
    // Instance variables
    //=========================================================================
    /**
     * The current size of the heap.
     */
    private int size;
    /**
     * The maximum capacity of the heap storage array.
     */
    private int capacity;
    /**
     * The storage array.
     */
    private Integer[] elements;
    
    //=========================================================================
    // Constructors
    //=========================================================================
    /**
     * Constructs an empty min heap with a capacity of 10.
     */
    public MinHeap()
    {
        this.size = 0;
        this.capacity = 10;
        this.elements = new Integer[capacity];
    }

    //=========================================================================
    // Public methods
    //=========================================================================
    /**
     * Peeks at the root of the heap (i.e. the smallest value).
     * @return The root of the heap (if any), null otherwise.
     */
    public Integer peek()
    {
        if (isEmpty()) { return null; }
        return elements[0];
    }

    /**
     * Extracts the root (i.e. smallest) value from the min heap.
     * @return The smallest value from the heap if any, null otherwise.
     */
    public Integer extract()
    {
        return delete(0);
    }

    /**
     * Finds the index of the input value (if it exists).
     * @param value The value to be searched.
     * @return The index of the match if found, -1 otherwise.
     */
    public int search(Integer value)
    {
        if (isEmpty()) { return -1; }
        for (int i = 0; i < size; i++)
        {
            if (elements[i] == value) { return i; }
        }
        return -1;
    }

    /**
     * Adds a new value to the heap.
     * @param value Value to be inserted into the heap.
     */
    public void insert(Integer value)
    {
        if (size == capacity) { increaseHeapCapacity(); }
        // Place new element at end of array
        elements[size] = value;
        size++;
        // Heapify last element up to proper location
        heapifyUp(size - 1);
    }

    /**
     * Deletes the value at the input index. Note that the last element doesnt
     * need to be nullified because of the decrementation of the size variable.
     * @param index The index of the value to be deleted.
     * @return The deleted value if any, null otherwise.
     */
    public Integer delete(int index)
    {
        if (isEmpty() || index < 0 || index >= size) { return null; }
        Integer deletedValue = elements[index];
        // Overwrite root node with last element
        elements[0] = elements[size - 1];
        size--;
        // Heapify new root down to proper position
        heapifyDown(0);
        return deletedValue;
    }

    /**
     * Prints the contents of the heap to the terminal.
     */
    public void print()
    {
        for (int i = 0; i < size; i++)
        {
            System.out.print(elements[i] + " ");
        }
        System.out.println();
    }

    //=========================================================================
    // Private methods
    //=========================================================================
    /**
     * Checks whether the heap is currently empty.
     * @return True if heap is empty, false otherwise.
     */
    private boolean isEmpty()
    {
        return size <= 0;
    }

    /**
     * Swaps an element down the heap to its proper location.
     * @param index The starting index of the node to be heapified down.
     */
    private void heapifyDown(int index)
    {
        // While element has left child...
        while (hasLeftChild(index))
        {
            // Find the index of the smallest child
            int smallestChildIndex = getLeftChildIndex(index);
            if (hasRightChild(index) && rightChild(index) < leftChild(index))
            {
                smallestChildIndex = getRightChildIndex(index);
            }
            // If the element is smaller than it's children, it's in the right spot
            if (elements[index] < elements[smallestChildIndex]) { break; }
            // Otherwise, swap with smallest child and repeat the loop
            else { swap(index, smallestChildIndex); }
            index = smallestChildIndex;
        }
    }

    /**
     * Swaps an element up the heap to its proper location.
     * @param index The starting index of the node to be heapified up.
     */
    private void heapifyUp(int index)
    {
        // While element has a parent of larger value...
        while (hasParent(index) && parent(index) > elements[index])
        {
            // Swap with parent and update index
            swap(getParentIndex(index), index);
            index = getParentIndex(index);
        }
    }

    /**
     * Swaps the content of two index positions.
     * @param indexOne The first index position.
     * @param indexTwo The second index position.
     */
    private void swap(int indexOne, int indexTwo)
    {
        int temp = elements[indexOne];
        elements[indexOne] = elements[indexTwo];
        elements[indexTwo] = temp;
    }

    /**
     * Doubles the capacity of the storage array.
     */
    private void increaseHeapCapacity()
    {
        capacity *= 2;
        Integer[] newArray = new Integer[capacity];
        for (int i = 0; i < elements.length; i++)
        {
            newArray[i] = elements[i];
        }
        elements = newArray;
    }

    // Given index, return child/parent indexes
    private int getLeftChildIndex(int parentIndex) { return 2 * parentIndex + 1; }
    private int getRightChildIndex(int parentIndex) { return 2 * parentIndex + 2; }
    private int getParentIndex(int childIndex) { return (childIndex - 1) / 2; }
    // Check if child/parent exist
    private boolean hasLeftChild(int index) { return getLeftChildIndex(index) < size; }
    private boolean hasRightChild(int index) { return getRightChildIndex(index) < size; }
    private boolean hasParent(int index) { return getParentIndex(index) >= 0; }
    // Given index, return child/parent values
    private int leftChild(int index) { return elements[getLeftChildIndex(index)]; }
    private int rightChild(int index) { return elements[getRightChildIndex(index)]; }
    private int parent(int index) { return elements[getParentIndex(index)]; }

    //=========================================================================
    // Main
    //=========================================================================
    /**
     * Main method to show off min heap functionality.
     */
    public static void main(String[] args) 
    {
        // Creating and populating the data structure
        MinHeap heap = new MinHeap();
        heap.insert(8);
        heap.insert(3);
        heap.insert(10);
        heap.insert(1);
        heap.insert(6);
        heap.insert(14);
        heap.insert(4);
        heap.insert(7);
        heap.insert(13);
        System.out.println("Initial heap: ");
        heap.print();
        // Search: Average/worst - O(n)
        System.out.println("Searching for index of value 1 (should be zero):");
        System.out.println(heap.search(1)); 
        System.out.println("Searching for index of value 42 (should be -1, DNE):");
        System.out.println(heap.search(42)); 
        // Insertion: Average/worst - O(log(n))
        System.out.println("Inserting a value of 22:");
        heap.insert(22);
        heap.print();
        // Deletion: Average/worst - O(log(n))
        System.out.println("Deleting value in index 7 (should be 8):");
        heap.delete(7);
        heap.print();
        // Extraction: Average/worst - O(log(n))
        System.out.println("Extracting root of min heap (should be 1):");
        heap.extract();
        heap.print();
        // Access min: O(1)
        System.out.println("Accessing the minimum value (should be 4):");
        System.out.println(heap.peek());
    }
}
