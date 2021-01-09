package src.datastructures.graph.graphs;

/**
 * This class outlines the basic structure/functionality of a undirected, 
 * unweighted graph implemented using an adjacency matrix. Adjacency matrices 
 * are dense graphs where nodes are connected to most other nodes. To implement
 * a directed graph, the addEdge and removeEdge methods would have to be 
 * changed to be one sided. To implement a weighted graph, the values in the 
 * matrix would have to be set to a weighted value rather than 1. The following 
 * code was modified from: 
 * https://opendatastructures.org/ods-java/12_1_AdjacencyMatrix_Repres.html.
 */
public class GraphAdjacencyMatrix 
{
    //=========================================================================
    // Instance variables
    //=========================================================================
    /**
     * The size of the nxn matrix.
     */
    private int size; 
    /**
     * The adjacency matrix to store edges.
     */
    private int[][] adjacencyMatrix;

    //=========================================================================
    // Constructors
    //=========================================================================
    /**
     * Creates a graph with nxn adjacency matrix of input size.
     * @param size The size of the nxn adjacency matrix.
     */
    public GraphAdjacencyMatrix(int size)
    {
        this.size = size;
        this.adjacencyMatrix = new int[size][size];
    }

    //=========================================================================
    // Public methods
    //=========================================================================
    /**
     * Adds an edge to the adjacency matrix.
     */
    public void addVertex()
    {
        size++;
        int[][] temp = adjacencyMatrix;
        this.adjacencyMatrix = new int[size][size];
        for (int i = 0; i < size - 1; i++)
        {
            for (int j = 0; j < size - 1; j++)
            {
                adjacencyMatrix[i][j] = temp[i][j];
            }
        }
    }

    /**
     * Removes an edge from the adjacency matrix.
     */
    public void removeVertex()
    {
        size--;
        int[][] temp = adjacencyMatrix;
        this.adjacencyMatrix = new int[size][size];
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                adjacencyMatrix[i][j] = temp[i][j];
            }
        }
    }

    /**
     * Adds an edge to the adjacency matrix.
     * @param i Index of the first vetrex.
     * @param j Index of the second vertex.
     */
    public void addEdge(int i, int j)
    {
        adjacencyMatrix[i][j] = 1;
        adjacencyMatrix[j][i] = 1;
    }

    /**
     * Removes an edge from the adjacency matrix.
     * @param i Index of the first vetrex.
     * @param j Index of the second vertex.
     */
    public void removeEdge(int i, int j)
    {
        adjacencyMatrix[i][j] = 0;
        adjacencyMatrix[j][i] = 0;
    }

    /**
     * Checks if the first vertex has a connection to the second vertex.
     * @param i The index of the first vertex.
     * @param j The index of the second vertex.
     * @return True if the vertices are connected by an edge, false otherwise.
     */
    public boolean hasConnection(int i, int j)
    {
        return adjacencyMatrix[i][j] == 1;
    }

    /**
     * Prints the contents of the graph.
     */
    public void print()
    {
        System.out.print("    ");
        String s = "   -";
        for (int e = 0; e < size; e++)
        {
            System.out.print(e + " ");
            s += "--";
        }
        System.out.println("\n" + s);
        for (int i = 0; i < size; i++)
        {
            System.out.print(i + " | ");
            for (int j = 0; j < size; j++)
            {
                System.out.print(adjacencyMatrix[i][j] + " ");
            }
            System.out.println();
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
        GraphAdjacencyMatrix graph = new GraphAdjacencyMatrix(6);
        graph.addEdge(0, 5);
        graph.addEdge(4, 1);
        graph.addEdge(2, 3);
        graph.addEdge(2, 1);
        graph.addEdge(5, 1);
        graph.addEdge(3, 5);
        System.out.println("Initial graph:");
        graph.print();
        // Add vertex: O(|V|^2)
        System.out.println("Adding vertex:");
        graph.addVertex();
        graph.print();
        // Add edge: O(1)
        System.out.println("Adding edge between vertices 6 and 4:");
        graph.addEdge(6, 4);
        graph.print();
        // Remove edge: O(1)
        System.out.println("Removing edge between vertices 2 and 1:");
        graph.removeEdge(2, 1);
        graph.print();
        // Remove vertex: O(|V|^2)
        System.out.println("Removing vertex:");
        graph.removeVertex();
        graph.print();
        // Check connection: O(1)
        System.out.println("Checking for connection between 4 and 1:");
        System.out.println(graph.hasConnection(4, 1));
        System.out.println();
        System.out.println("Checking for connection between 2 and 1:");
        System.out.println(graph.hasConnection(2, 1));
    }
}
