package src.datastructures.graph.graphs;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/**
 * This class outlines the basic structure/functionality of a undirected, 
 * unweighted graph implemented using an adjacency list. Adjacency lists are
 * used for most real-world (i.e. sparse) graphs. To implement a directed 
 * graph, you'd have to change the addEdge and removeEdge methods to be one 
 * sided. To implement a weighted graph, the nodes of the LinkedList would also 
 * need a weight field. The following code was modified from:
 * https://www.baeldung.com/java-graphs.
 */
public class GraphAdjacencyList
{
    /**
     * This class represents a node of the graph. For simplicity, I have left 
     * out getter and setter functions. Remember to use good practice when 
     * implementing your own data stuctures. 
     */
    public class Vertex
    { 
        //=====================================================================
        // Instance variables
        //=====================================================================
        /**
         * The label of the vertex.
         */
        public String label;

        //=====================================================================
        // Consturctors
        //=====================================================================
        /**
         * Constructs an Vertex object of input label.
         * @param label The label of the vertex.
         */
        public Vertex(String label) 
        { 
            this.label = label;
        }

        //=====================================================================
        // Private methods
        //=====================================================================
        private GraphAdjacencyList getOuterType()
        {
            return GraphAdjacencyList.this;
        }
    
        //=====================================================================
        // Overrides
        //=====================================================================
        /**
         * Allows the class to be printed.
         */
        @Override
        public String toString() 
        {
            return label;
        }

        /**
         * Defines hashcode to be related the label rather than the object.
         */
        @Override
        public int hashCode() 
        {
            final int prime = 31;
            int result = 1;
            result = prime * result + getOuterType().hashCode();
            result = prime * result + ((label == null) ? 0 : label.hashCode());
            return result;
        }

        /**
         * Since vertices are being checked for equality and this is a custom
         * class, must define what makes two vertices equal.
         */
        @Override
        public boolean equals(Object obj)
        {
            if (this == obj) { return true; }
            if (obj == null) { return false; }
            if (getClass() != obj.getClass()) { return false; }
            Vertex other = (Vertex) obj;
            if (!getOuterType().equals(other.getOuterType())) { return false; }
            if (label == null && other.label != null) { return false; } 
            if (!label.equals(other.label)) { return false; }
            return true;
        }
    }

    //=========================================================================
    // Instance variables
    //=========================================================================
    /**
     * ArrayList component that stores each adjacency list. A hashmap is used
     * so that we can find the adjacency lists in O(1) time rather than having
     * to search a list.
     */
    private HashMap<Vertex, LinkedList<Vertex>> adjacencyLists;

    //=========================================================================
    // Constructors
    //=========================================================================
    /**
     * Constructs an empty graph object.
     */
    public GraphAdjacencyList()
    {
        this.adjacencyLists = new HashMap<Vertex, LinkedList<Vertex>>();
    }

    //=========================================================================
    // Public methods
    //=========================================================================
    /**
     * Adds a vertex to the graph of input label.
     * @param label The label of the input vertex.
     */
    public void addVertex(String label)
    {
        adjacencyLists.putIfAbsent(new Vertex(label), new LinkedList<Vertex>());
    }

    /**
     * Removes a vertex from the graph corresponding to the input label.
     * @param label
     */
    public void removeVertex(String label)
    {
        // Removed vertex from all adjacency lists in hash map
        Vertex v = new Vertex(label);
        adjacencyLists.values().stream().forEach(adjacencyList -> adjacencyList.remove(v));
        // Remove adjacency list corresponding to input vertex from hash map
        adjacencyLists.remove(v);
    }

    /**
     * Adds an edge by adding the lablelled vertices to each others adjacency
     * lists.
     * @param label1 The label of the first vertex.
     * @param label2 The label of the second index.
     */
    public void addEdge(String label1, String label2)
    {
        // Get adjacency list for each vertex
        Vertex v1 = new Vertex(label1);
        Vertex v2 = new Vertex(label2);
        LinkedList<Vertex> adjacencyList1 = adjacencyLists.get(v1);
        LinkedList<Vertex> adjacencyList2 = adjacencyLists.get(v2);
        // Add vertices to each others adjacency lists
        if (adjacencyList1 != null) { adjacencyList1.add(v2); }
        if (adjacencyList2 != null) { adjacencyList2.add(v1); }
        
    }

    /**
     * Removes an edge by removing the labelled vertices from each others 
     * adjacency lists.
     */
    public void removeEdge(String label1, String label2)
    {
        // Get adjacency list for each vertex
        Vertex v1 = new Vertex(label1);
        Vertex v2 = new Vertex(label2);
        LinkedList<Vertex> adjacencyList1 = adjacencyLists.get(v1);
        LinkedList<Vertex> adjacencyList2 = adjacencyLists.get(v2);
        // Add vertices to each others adjacency lists
        if (adjacencyList1 != null) { adjacencyList1.remove(v2); }
        if (adjacencyList2 != null) { adjacencyList2.remove(v1); }
    }

    /**
     * Checks if the first vertex has a connection to the second vertex.
     * @param label1 The label of the first vertex.
     * @param label2 The label of the second vertex.
     * @return True if the vertices are connected by an edge, false otherwise.
     */
    public boolean hasConnection(String label1, String label2)
    {
        // Get adjacency list for first vertex
        Vertex v1 = new Vertex(label1);
        Vertex v2 = new Vertex(label2);
        LinkedList<Vertex> adjacencyList = adjacencyLists.get(v1);
        // Check if second vertex is in adjacency list of first vertex
        if (adjacencyList != null) { return adjacencyList.contains(v2); }
        return false;
    }

    /**
     * Prints the contents of the graph.
     */
    public void print()
    {
        Iterator it = adjacencyLists.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry<Vertex, LinkedList<Vertex>> pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + " -> " + pair.getValue());
        }
        System.out.println();
    }

    //=========================================================================
    // Main
    //=========================================================================
    /**
     * Main method to show off graph functionality.
     */
    public static void main(String[] args) 
    {
        // Creating and populating the data structure
        GraphAdjacencyList graph = new GraphAdjacencyList();
        graph.addVertex("Alex");
        graph.addVertex("Ben");
        graph.addVertex("Cody");
        graph.addVertex("Darcy");
        graph.addVertex("Emma");
        graph.addVertex("Faith");
        graph.addEdge("Alex", "Faith");
        graph.addEdge("Emma", "Ben");
        graph.addEdge("Cody", "Darcy");
        graph.addEdge("Cody", "Ben");
        graph.addEdge("Faith", "Ben");
        graph.addEdge("Darcy", "Faith");
        System.out.println("Initial graph:");
        graph.print();
        // Add vertex: O(1)
        System.out.println("Adding vertex for Joey:");
        graph.addVertex("Joey");
        graph.print();
        // Add edge: O(1)
        System.out.println("Adding edge between Joey and Emma:");
        graph.addEdge("Joey", "Emma");
        graph.print();
        // Remove edge: O(|V|)
        System.out.println("Removing edge between Cody and Ben:");
        graph.removeEdge("Cody", "Ben");
        graph.print();
        // Remove vertex: O(|V| + |E|)
        System.out.println("Removing vertex for Darcy:");
        graph.removeVertex("Darcy");
        graph.print();
        // Check connection: O(|V|)
        System.out.println("Checking for connection between Emma and Ben:");
        System.out.println(graph.hasConnection("Emma", "Ben"));
        System.out.println();
        System.out.println("Checking for connection between Cody and Ben:");
        System.out.println(graph.hasConnection("Cody", "Ben"));
    }
}
