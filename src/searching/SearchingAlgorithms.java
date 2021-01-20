package src.searching;

import java.util.Random;
import java.util.Scanner;

/**
 * This class outlines various searching algorithms. Using the main method, you
 * can find the execution time for the each searching algorithm with various
 * array orders and sizes. Can be used to plot a time complexity chart.
 */
public class SearchingAlgorithms
{
    //=========================================================================
    // User input
    //=========================================================================
    /**
     * Gets user input such that all fields are valid.
     * @return Array of user input strings corresponding with order, size, and 
     * algorithm.
     */
    public String[] getUserInput()
    {
        Scanner scanner = new Scanner(System.in);
        while (true)
        {
            System.out.println(
                "Please enter\n" +
                "- the order of the array (ascending, descending, or random)\n" + 
                "- the size of the array (positive integer)\n" + 
                "- the searching algorithm (linear or binary)\n" +
                "- the type of value to be searched for (nonexistent, random, first, or last) \n" + 
                "all separated by a space:");
            
            String input = scanner.nextLine();
            String[] inputs = input.strip().split("\\s+");
            if (isValidOrder(inputs[0]) && isValidSize(inputs[1]) && isValidAlgorithm(inputs[2]) && isValidValueType(inputs[3]))
            {
                scanner.close();
                return inputs;
            }
            System.out.println("Error: Invalid input. Press enter to try again.");
            scanner.nextLine();
        }
    }

    /**
     * Returns true if input order valid. False otherwise.
     */
    private boolean isValidOrder(String order)
    {
        return order.equals("ascending") || 
            order.equals("descending") || 
            order.equals("random");
    }

    /**
     * Returns true if input size valid. False otherwise.
     */
    private boolean isValidSize(String size)
    {
        try 
        {
            int value = Integer.parseInt(size);
            if (value > 0) { return true; }
        }
        catch (Exception e) { return false; }
        return false;
    }

    /**
     * Returns true if input algorithm valid. False otherwise.
     */
    private boolean isValidAlgorithm(String algorithm)
    {
        return algorithm.equals("linear") ||
            algorithm.equals("binary");
    }

    /**
     * Returns true if input algorithm valid. False otherwise.
     */
    private boolean isValidValueType(String algorithm)
    {
        return algorithm.equals("nonexistent") ||
            algorithm.equals("randomexistent") ||
            algorithm.equals("first") ||
            algorithm.equals("last");
    }

    //=========================================================================
    // Array generation
    //=========================================================================
    /**
     * Generates and returns an array of the input order and size.
     * @param order The order of the values in the array.
     * @param size The size of the array.
     * @return Array of the input order and size.
     */
    public int[] generateArray(String order, String size)
    {
        switch (order)
        {
            case "ascending":
                return generateAscendingArray(Integer.parseInt(size));
            case "descending":
                return generateDescendingArray(Integer.parseInt(size));
            case "random":
                return generateRandomArray(Integer.parseInt(size));
            default:
                System.out.println("Error: Invalid order input. Exiting program.");
                System.exit(0);
                return null;
        }
    }

    /**
     * Returns an ascending integer array of input size.
     */
    private int[] generateAscendingArray(int size)
    {
        int[] a = new int[size];
        for (int i = 0; i < size; i++)
        {
            a[i] = i;
        }
        return a;
    }
    
    /**
     * Returns an descending integer array of input size.
     */
    private int[] generateDescendingArray(int size)
    {
        int[] a = new int[size];
        for (int i = 0; i < size; i++)
        {
            a[i] = size-i-1;
        }
        return a;
    }

    /**
     * Returns an randomly ordered integer array of input size.
     */
    private int[] generateRandomArray(int size)
    {
        Random rand = new Random();
        int[] a = new int[size];
        for (int i = 0; i < size; i++)
        {
            a[i] = rand.nextInt(1000000);
        }
        return a;
    }

    //=========================================================================
    // Get search value
    //=========================================================================
    private int getSearchValue(int[] arr, String valueType)
    {
        switch (valueType)
        {
            case "nonexistent":
                return -1;
            case "random":
                Random rand = new Random();
                return arr[rand.nextInt(arr.length)];
            case "first":
                return arr[0];
            case "last":
                return arr[arr.length - 1];
            default:
                System.out.println("Error: Invalid value type input. Exiting program.");
                System.exit(0);
        }
        System.exit(0);
        return -1;
    }

    //=========================================================================
    // Applying search
    //=========================================================================
    /**
     * Searches the input array using the input searching algorithm.
     * @param algorithm The name of the searching algorithm to be used.
     * @param array The array to be searched.
     * @param order The order of the values in the array.
     */
    public void search(String algorithm, int[] array, String order, int value)
    {
        switch (algorithm)
        {
            case "linear":
                linearSearch(array, value); break;
            case "binary":
                if (!order.equals("ascending"))
                {
                    System.out.println("Error: Binary search requires a sorted (i.e. ascending) array. Exiting program.");
                    System.exit(0);
                }
                binarySearch(array, value); break;
            default:
                System.out.println("Error: Invalid algorithm input. Exiting program.");
                System.exit(0);
        }
    }

    //=========================================================================
    // Linear search
    //=========================================================================
    /**
     * Performs a linear/sequential search on the input array.
     * @param arr The array to be searched.
     * @param value The value to be found.
     */
    private void linearSearch(int[] arr, int value)
    {
        for (int i = 0; i < arr.length; i++)
        {
            if (arr[i] == value) { return; }
        }
    }

    //=========================================================================
    // Binary search
    //=========================================================================
    /**
     * Performs a binary search on the input array.
     * @param arr The array to be searched.
     * @param value The value to be searched for.
     */
    private int binarySearch(int[] arr, int value)
    {
        int leftIndex = 0;
        int midIndex;
        int rightIndex = arr.length - 1;
        while (leftIndex <= rightIndex)
        {
            midIndex = (leftIndex + rightIndex) / 2;
            if (value < arr[midIndex]) { rightIndex = midIndex - 1; }
            else if (value > arr[midIndex]) { leftIndex = midIndex + 1;}
            else { return midIndex; }
        }
        return -1;
    }

    /**
     * Performs a recursive binary search on the input array.
     * @param arr The array to be searched.
     * @param leftIndex The leftmost index of the array.
     * @param rightIndex The rightmost index of the array.
     * @param value The value to be searched for.
     * @return The index of the value if found. -1 otherwise.
     */
    private int binarySearchRecursive(int[] arr, int leftIndex, int rightIndex, int value)
    {
        int midIndex = -1;
        if (leftIndex <= rightIndex) { midIndex = (leftIndex + rightIndex) / 2; }
        if (value == arr[midIndex]) { return midIndex; }
        else if (value < arr[midIndex]) { return binarySearchRecursive(arr, leftIndex, midIndex-1, value); }
        else if (value > arr[midIndex]) { return binarySearchRecursive(arr, midIndex+1, rightIndex, value); }
        else { return -1; }
    }

    //================================================================================
    // Main
    //================================================================================
    /**
     * Main method to time various sorting algorithms.
     */
    public static void main(String[] args) 
    {
        SearchingAlgorithms sa = new SearchingAlgorithms();
        // Get user input
        String[] inputs = sa.getUserInput();
        String order = inputs[0];
        String size = inputs[1];
        String algorithm = inputs[2];
        String valueType = inputs[3];
        // Create array
        int[] array = sa.generateArray(order, size);
        int searchValue = sa.getSearchValue(array, valueType);
        // Sort while recording time
        float startTime = System.nanoTime();
        sa.search(algorithm, array, order, searchValue);
        float endTime = System.nanoTime();
        float totalTime = (endTime - startTime) / 1000000000;
        // Print results
        System.out.printf( "The " + order + " array of size " + size + " took %.9f seconds to " + algorithm + " search.\n", totalTime);
    }
}
