package src.datastructures.hash.hashtable;

import java.util.ArrayList;

/**
 * This class outlines the basic structure/functionality of a hash table.
 * The following code is modified from: 
 * https://www.geeksforgeeks.org/implementing-our-own-hash-table-with-separate-chaining-in-java/
 */
public class HashTable<K, V>
{
    /**
     * This class represents a node of an index in the hash table. For 
     * simplicity, I have left out getter and setter functions. Remember to use 
     * good practice when implementing your own data stuctures. 
     */
    public class Node<K, V>
    { 
        //=====================================================================
        // Instance variables
        //=====================================================================
        /**
         * The key of the key-value pair.
         */
        public K key; 
        /**
         * The value of the key-value pair.
         */
        public V value;
        /**
         * The next node in the linked list.
         */
        public Node<K, V> next;

        //=====================================================================
        // Consturctors
        //=====================================================================
        /**
         * Constructs a node with the input data.
         */
        public Node(K key, V value) 
        { 
            this.key = key;
            this.value = value;
        }
    }

    //=========================================================================
    // Instance variables
    //=========================================================================
    /**
     * The storage array. For simplicity, an ArrayList was used so we don't
     * have to define methods for insert, delete, etc. See ArrayList 
     * implementation for more details.
     */
    private ArrayList<Node<K, V>> elements;
    /**
     * The number of elements currently stored in the ArrayList.
     */
    private int size;
    /**
     * The number of total capacity of the ArrayList.
     */
    private int capacity;

    //=========================================================================
    // Constructors
    //=========================================================================
    /**
     * Constructs a hash table populated with empty nodes. Note that the
     * default capacity of an ArrayList is 10.
     */
    public HashTable()
    {
        // Initialize variables
        this.elements = new ArrayList<Node<K, V>>();
        this.size = 0;
        this.capacity = 10;
        // Initialize ArrayList with nullified nodes
        for (int i = 0; i < capacity; i++)
        {
            elements.add(null);
        }
    }

    //=========================================================================
    // Public methods
    //=========================================================================
    /**
     * Searches the hash table for a value corresponding to the input key.
     * @param key The key of the key-value pair.
     * @return The value corresponding to the input key if found, null otherwise.
     */
    public V search(K key)
    {
        if (isEmpty()) { return null; }
        // Get the first node of the linked list stored at the index corresponding to the key
        int index = getIndex(key);
        Node<K, V> cursor = elements.get(index);
        // Search list for node of matching key and return value if any 
        cursor = getNodeOfMatchingKey(cursor, key);
        if (cursor != null) { return cursor.value; }
        else { return null; }
    }

    /**
     * Inserts a value into the hash table.
     * @param key The key of the key-value pair.
     * @param value The value of the key-value pair.
     */
    public void insert(K key, V value)
    {
        // Get the first node of the linked list stored at the index corresponding to the key
        int index = getIndex(key);
        Node<K, V> cursor = elements.get(index);
        // Search list for node of matching key
        cursor = getNodeOfMatchingKey(cursor, key);
        // If match found, update value
        if (cursor != null) { cursor.value = value; }
        // If no key match found, add before head of linked list
        else { addToLinkedList(index, key, value); }
        // If reached 70%, double storage capcity
        if ((1.0*size)/capacity >= 0.7) { doubleArrayStorage(); }
    }

    /**
     * Deletes a value from the hash table.
     * @param key The key of the key-value pair.
     * @return The deleted value if any, null otherwise.
     */
    public V delete(K key)
    {
        if (isEmpty()) { return null; }
        // Get the first node of the linked list stored at the index corresponding to the key
        int index = getIndex(key);
        Node<K, V> cursor = elements.get(index);
        Node<K, V> prevNode = null;
        // Search for node of matching key
        while (cursor != null)
        {
            // If match found, remove from linked list
            if (cursor.key.equals(key)) 
            {
                removeFromLinkedList(index, cursor, prevNode);
            }
            prevNode = cursor;
            cursor = cursor.next;
        }
        return null;
    }

    /**
     * Prints the contents of the hash table.
     */
    public void print()
    {
        for (int i = 0; i < elements.size(); i++)
        {
            Node<K, V> node = elements.get(i);
            System.out.print("[");
            if (node == null) { System.out.print("null"); }
            while (node != null)
            {
                System.out.print(node.value);
                if (node.next != null) { System.out.print("->");}
                node = node.next;
            }
            System.out.print("]");
            if (i < (elements.size() - 1)) { System.out.print(", ");}
        }
        System.out.println();
    }

    //=========================================================================
    // Private methods
    //=========================================================================
    /**
     * Checks whether the hash table is empty.
     * @return True if the hash table is empty, false otherwise.
     */
    private boolean isEmpty()
    {
        return this.size <= 0;
    }

    /**
     * Returns the index corresponding to a key in the hash table. Since this
     * implementation is generic, I used Java's built-in hash function. Note 
     * that the modulus is used to ensure the index is within the capacity of 
     * the hash table.
     * @param key The key to be converted into an index.
     * @return The index corresponding to the input key.
     */
    private int getIndex(K key)
    {
        return Math.abs(key.hashCode()) % capacity;
    }

    /**
     * Finds the node corresponding to the input key.
     * @param cursor The head node of the linked list.
     * @param key The key of the key-value pair.
     * @return The node corresponding to the input key if found, null otherwise.
     */
    private Node<K, V> getNodeOfMatchingKey(Node<K, V> cursor, K key)
    {
        while (cursor != null)
        {
            if (cursor.key.equals(key)) { return cursor; }
            cursor = cursor.next;
        }
        return null;
    }

    /**
     * Adds a node corresponding to the input key to the linked list.
     * @param index The index corresponding to the key.
     * @param key The key of the key-value pair.
     * @param value The value of the key-value pair.
     */
    private void addToLinkedList(int index, K key, V value)
    {
        Node<K, V> headNode = elements.get(index);
        Node<K, V> newNode = new Node<K, V>(key, value);
        newNode.next = headNode;
        elements.set(index, newNode);
        size++;
    }

    /**
     * Removes a node corresponding to the input key from the linked list.
     * @param index The index corresponding to the key.
     * @param removedNode The node to be removed.
     * @param prevNode The previous node.
     * @return The value of the removed node.
     */
    private V removeFromLinkedList(int index, Node<K, V> removedNode, Node<K, V> prevNode)
    {
        if (prevNode == null) { elements.set(index, removedNode.next); }
        else { prevNode.next = removedNode.next; }
        size--;
        return removedNode.value; 
    }

    /**
     * Doubles the storage of the ArrayList. We want a sparsely populated hash
     * table to minimize collisions.
     */
    private void doubleArrayStorage()
    {
        // Store previous ArrayList
        ArrayList<Node<K, V>> temp = elements;
        // Create new nullified ArrayList with double capacity
        elements = new ArrayList<>();
        capacity *= 2;
        size = 0;
        for (int i = 0; i < capacity; i++)
        {
            elements.add(null);
        }
        // Copy over linked list elements from previous ArrayList
        for (Node<K, V> cursor: temp)
        {
            while (cursor != null)
            {
                insert(cursor.key, cursor.value);
                cursor = cursor.next;
            }
        }
    }

    //=========================================================================
    // Main
    //=========================================================================
    /**
     * Main method to show off hash table functionality.
     */
    public static void main(String[] args) 
    {
        // Creating and populating the data structure
        HashTable<String, Integer> hashTable = new HashTable<String, Integer>();
        hashTable.insert("apple", 1);
        hashTable.insert("orange", 2);
        hashTable.insert("banana", 3);
        hashTable.insert("blueberry", 4);
        hashTable.insert("blackberry", 5);
        hashTable.insert("strawberry", 6);
        System.out.println("Initial hash table:");
        hashTable.print();
        // Search: Average O(1), worst O(n)
        System.out.println("Searching for number of bananas (should be 3):");
        System.out.println(hashTable.search("banana"));
        System.out.println("Searching for number of grape (should be null):");
        System.out.println(hashTable.search("null"));
        // Insertion: Average O(1), worst O(n)
        System.out.println("Inserting <cantaloupe, 7> (should increase hashtable capacity and reduce collisions):");
        hashTable.insert("cantaloupe", 7);
        hashTable.print();
        // Deletion: Average O(1), worst O(n)
        System.out.println("Deleting orange from the hash table (should remove number 2):");
        hashTable.delete("orange");
        hashTable.print();
    }
}
