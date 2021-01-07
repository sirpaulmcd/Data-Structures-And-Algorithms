package src.datastructures.tree.binarysearchtrees;

/**
 * This class outlines the basic structure/functionality of a binary search tree. 
 * The following code was modified from: 
 * https://algorithms.tutorialhorizon.com/binary-search-tree-complete-implementation/
 * and GeeksForGeeks example implementations.
 */
public class BinarySearchTree 
{
    /**
     * This class represents a node of the binary search tree. For simplicity,
     * I have left out getter and setter functions. Remember to use good
     * practice when implementing your own data stuctures. 
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
         * The left child of the node.
         */
        public Node left;
        /**
         * The right child of the node.
         */
        public Node right; 

        //=====================================================================
        // Consturctors
        //=====================================================================
        /**
         * Constructs a node with the input data.
         */
        public Node(Integer data) 
        { 
            this.data = data; 
            this.left = null;
            this.right = null;
        }
    }

    //=========================================================================
    // Instance variables
    //=========================================================================
    /**
     * The root of the binary search tree.
     */
    private Node root;

    //=========================================================================
    // Constructors
    //=========================================================================
    /**
     * Constructs an empty BST object.
     */
    public BinarySearchTree()
    {
        this.root = null;
    }

    //=========================================================================
    // Public methods
    //=========================================================================
    /**
     * Searches the BST for a match to the input value.
     * @param value The value to be searched in the BST.
     * @return True if match found, false otherwise.
     */
    public boolean search(Integer value)
    {
        if (isEmpty()) { return false; }
        // Navigate cursor to match (if any)
        Node cursor = root;
        while (cursor != null)
        {
            if (cursor.data == value) { return true; }
            else if (cursor.data > value) { cursor = cursor.left; }
            else { cursor = cursor.right; }
        }
        return false;
    }

    /**
     * Inserts a value into the BST.
     * @param value The value to be inserted into the BST.
     */
    public void insert(Integer value)
    {
        // Create new node
        Node newNode = new Node(value);
        // If tree empty, make node root
        if (isEmpty()) { root = newNode; return; }
        // Navigate cursor to insertion location
        Node cursor = root;
        Node parent = null;
        boolean isLeftChild = false;
        while (cursor != null)
        {
            parent = cursor;
            if (cursor.data > value)
            {
                cursor = cursor.left;
                isLeftChild = true;
            }
            else
            {
                cursor = cursor.right;
                isLeftChild = false;
            }
        }
        // Insert new node under proper parent
        if (isLeftChild) { parent.left = newNode; }
        else { parent.right = newNode; }
        return; 
    }

    /**
     * Deletes a value from the BST if found.
     * @param value The value of the node to be deleted.
     * @return The deleted node.
     */
    public Node delete(Integer value)
    {
        if (isEmpty()) { return null; }
        // Look for node of matching value.
        Node parent = root;
        Node cursor = root;
        boolean isLeftChild = false;
        while (cursor.data != value)
        {
            parent = cursor;
            if (cursor.data > value) 
            { 
                isLeftChild = true;
                cursor = cursor.left;
            }
            else
            {
                isLeftChild = false;
                cursor = cursor.right;
            }
            // If match not found, return false.
            if (cursor == null) { return null; }
        }
        // Case 1) If match has no children...
        if (cursor.left == null && cursor.right == null)
        {
            if (cursor == root) { root = null; }
            else if (isLeftChild == true) { parent.left = null; }
            else { parent.right = null; }
        }
        // Case 2A) If match has one child on the left.
        else if (cursor.right == null)
        {
            if (cursor == root) { root = cursor.left; }
            else if (isLeftChild) { parent.left = cursor.left; }
            else { parent.right = cursor.left; }
        }
        // Case 2B) If match has one child on the right.
        else if (cursor.left == null)
        {
            if (cursor == root) { root = cursor.right; }
            else if (isLeftChild) { parent.left = cursor.right; }
            else { parent.right = cursor.right; }
        }
        // Case 3) If match has two children.
        else
        {
            // Find successor node with right child already set.
            Node successor = getSuccessor(cursor);
            // Set parent of successor accordingly.
            if (cursor == root) { root = successor; }
            else if (isLeftChild) { parent.left = successor; }
            else { parent.right = successor; }
            // Set left child accordingly.
            successor.left = cursor.left;
        }
        // Remove references from deleted node and return
        cursor.left = cursor.right = null;
        return cursor;
    }

    /**
     * Prints the contents of the tree in order. This is a type of depth-first 
     * traversal. Wrapper function for recursive method.
     */
    public void printInOrder()
    {
        inOrderTraverse(root);
        System.out.println();
    }

    /**
     * Prints the contents of the tree in reverse order. This is a type of 
     * depth-first traversal. Wrapper function for recursive method.
     */
    public void printInReverseOrder()
    {
        reverseOrderTraverse(root);
        System.out.println();
    }

    /**
     * Prints the contents of the tree in pre-order. This is a type of 
     * depth-first traversal. Wrapper function for recursive method.
     */
    public void printInPreOrder()
    {
        preOrderTraverse(root);
        System.out.println();
    }

    /**
     * Prints the contents of the tree in post-order. This is a type of 
     * depth-first traversal. Wrapper function for recursive method.
     */
    public void printInPostOrder()
    {
        postOrderTraverse(root);
        System.out.println();
    }

    /**
     * Prints the contents of the tree in level-order. This is a type of
     * breadth-first traversal. Although this implementation is recursive,
     * this kind of traversal can also be implemented with a queue.
     */
    public void printInLevelOrder()
    {
        int height = height(root);
        for (int i = 1; i <= height + 1; i++)
        {
            printLevel(root, i);
        }
        System.out.println();
    }

    /**
     * Prints the height of the BST. The height is the number of edges between
     * the root node and the farthest leaf node.
     */
    public void printHeight()
    {
        System.out.println(height(root));
    }

    //=========================================================================
    // Private methods
    //=========================================================================
    /**
     * Checks whether the tree is currently empty.
     * @return True if tree is empty, false otherwise.
     */
    private boolean isEmpty()
    {
        return root == null;
    }

    /**
     * Gets the smallest node on the right subtree of the node to be deleted. 
     * Removes it from the tree so that it can be moved to the location of the
     * deleted node. Right child is properly set before successor node is
     * returned.
     * @param deletedNode The node to be deleted from the tree.
     * @return The successor node to replace the deleted node.
     */
    private Node getSuccessor(Node deletedNode)
    {
        // Locate successor node.
        Node successor = null;
        Node successorParent = null; 
        Node cursor = deletedNode.right;
        while (cursor != null)
        {
            successorParent = successor;
            successor = cursor;
            cursor = cursor.left;
        }
        // If found successor is not root of right subtree...
        if (successor != deletedNode.right)
        {
            // We know successor has no left child to worry about.
            // Make right child of successor left child of parent.
            successorParent.left = successor.right;
            successor.right = deletedNode.right;
        }
        // Return successor node with right child already updated.
        return successor;
    }

    /**
     * Returns the height of the input node. Tree height is the number of edges
     * between the lowest leaf node and the input node.
     * @param node The node of which height is to be found.
     * @return The height of the input node.
     */
    public int height(Node node)
    {
        if (node == null) { return -1; }
        // Find the height of left and right subtrees
        int leftSubtreeHeight = height(node.left);
        int rightSubtreeHeight = height(node.right);
        // Return the height of the largest subtree + 1
        return Math.max(leftSubtreeHeight, rightSubtreeHeight) + 1;
    }

    /**
     * Traverses the tree in order. This is a type of depth-first traversal.
     */
    private void inOrderTraverse(Node cursor)
    {
        if (cursor == null) { return; }
        inOrderTraverse(cursor.left);
        System.out.print(cursor.data + " ");
        inOrderTraverse(cursor.right);
    }

    /**
     * Traverses the tree in reverse order. This is a type of depth-first 
     * traversal.
     */
    private void reverseOrderTraverse(Node cursor)
    {
        if (cursor == null) { return; }
        reverseOrderTraverse(cursor.right);
        System.out.print(cursor.data + " ");
        reverseOrderTraverse(cursor.left);
    }

    /**
     * Traverses the tree in pre-order. This is a type of depth-first 
     * traversal.
     */
    private void preOrderTraverse(Node cursor)
    {
        if (cursor == null) { return; }
        System.out.print(cursor.data + " ");
        preOrderTraverse(cursor.left);
        preOrderTraverse(cursor.right);
    }

    /**
     * Traverses the tree in post-order. This is a type of depth-first 
     * traversal.
     */
    private void postOrderTraverse(Node cursor)
    {
        if (cursor == null) { return; }
        postOrderTraverse(cursor.left);
        postOrderTraverse(cursor.right);
        System.out.print(cursor.data + " ");
    }

    /**
     * Prints node contents if they belong to the input level.
     * @param cursor The cursor node for tree traversal. Should begin at root.
     * @param level The level of the tree to be printed (before recursion).
     */
    private void printLevel(Node cursor, int level)
    {
        if (cursor == null) { return; }
        else if (level == 1) { System.out.print(cursor.data + " "); }
        else 
        { 
            printLevel(cursor.left, level - 1);
            printLevel(cursor.right, level - 1);
        }
    }

    //=========================================================================
    // Main
    //=========================================================================
    /**
     * Main method to show off bst functionality.
     */
    public static void main(String[] args) 
    {
        // Creating and populating the data structure
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(8);
        bst.insert(3);
        bst.insert(10);
        bst.insert(1);
        bst.insert(6);
        bst.insert(14);
        bst.insert(4);
        bst.insert(7);
        bst.insert(13);
        // Traversal: O(n)
        System.out.println("BST data in order:");
        bst.printInOrder();
        System.out.println("BST data in reverse order:");
        bst.printInReverseOrder();
        System.out.println("BST data in pre-order:");
        bst.printInPreOrder();
        System.out.println("BST data in post-order:");
        bst.printInPostOrder();
        System.out.println("BST data in level-order:");
        bst.printInLevelOrder();
        // Search: Average O(log(n)), worst O(n)
        System.out.println("Searching if BST holds a value of 1:");
        System.out.println(bst.search(1)); 
        System.out.println("Searching if BST holds a value of 42:");
        System.out.println(bst.search(42)); 
        // Insertion: Average O(log(n)), worst O(n)
        System.out.println("Inserting a value of 22:");
        bst.insert(22);
        bst.printInOrder();
        // Deletion: Average O(log(n)), worst O(n)
        System.out.println("Deleting a value of 7:");
        bst.delete(7);
        bst.printInOrder();
    }
}
