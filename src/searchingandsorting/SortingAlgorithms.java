package src.searchingandsorting;

import java.util.Random;
import java.util.Scanner;

/**
 * This class outlines various sorting algorithms. Using the main method, you
 * can find the execution time for the each sorting algorithm with various
 * array orders and sizes. Can be used to plot a time complexity chart.
 */
public class SortingAlgorithms
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
                "- the sorting algorithm (selection, bubble, insertion, quick, or merge)\n" +
                "all separated by a space:");
            String input = scanner.nextLine();
            String[] inputs = input.strip().split("\\s+");
            if (isValidOrder(inputs[0]) && isValidSize(inputs[1]) && isValidAlgorithm(inputs[2]))
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
        return algorithm.equals("selection") ||
            algorithm.equals("bubble") || 
            algorithm.equals("insertion") ||
            algorithm.equals("quick") || 
            algorithm.equals("merge");
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
    // Applying sort
    //=========================================================================
    /**
     * Sorts the input array using the input sorting algorithm.
     * @param algorithm The name of the sorting algorithm to be used.
     * @param array The array to be sorted.
     */
    public void sort(String algorithm, int[] array)
    {
        switch (algorithm)
        {
            case "bubble":
                bubbleSort(array); break;
            case "insertion":
                insertionSort(array); break;
            case "selection":
                selectionSort(array); break;
            case "quick":
                quickSort(array, 0, array.length-1); break;
            case "merge":
                mergeSort(array, 0, array.length-1); break;
            default:
                System.out.println("Error: Invalid algorithm input. Exiting program.");
                System.exit(0);
        }
    }

    //=========================================================================
    // Selection sort
    //=========================================================================
    /**
     * Performs selection sort on the input array. Algorithm modified from from 
     * https://www.geeksforgeeks.org/selection-sort/.\
     * @param arr The array to be sorted.
     */
    private void selectionSort(int[] arr)
    {
        int n = arr.length; 
        // One by one move boundary of unsorted subarray 
        for (int i = 0; i < n-1; i++) 
        { 
            // Find the minimum element in unsorted array 
            int minIndex = i; 
            for (int j = i + 1; j < n; j++)
            {
                if (arr[j] < arr[minIndex]) { minIndex = j; }      
            }
            // Swap minimum element with the first element in unsorted subarray
            int temp = arr[minIndex]; 
            arr[minIndex] = arr[i]; 
            arr[i] = temp; 
        } 
    }

    //=========================================================================
    // Bubble sort
    //=========================================================================
    /**
     * Performs bubble sort in the input array. Algorithm modified from 
     * https://stackabuse.com/sorting-algorithms-in-java/#bubblesort.
     * @param arr The array to be sorted.
     */
    private void bubbleSort(int[] arr)
    {
        boolean sorted = false;
        while (!sorted) 
        {
            sorted = true;
            // For every element in array...
            for (int i = 0; i < arr.length - 1; i++) 
            {
                // If larger than neighbor, swap and mark array as not sorted
                if (arr[i] > arr[i+1]) 
                {
                    int temp = arr[i];
                    arr[i] = arr[i+1];
                    arr[i+1] = temp;
                    sorted = false;
                }
            }
        }
    }

    //=========================================================================
    // Insertion sort
    //=========================================================================
    /**
     * Performs insertion sort on the input array. Algorithm modified from 
     * https://stackabuse.com/sorting-algorithms-in-java/#insertionsort.
     * @param arr The array to be sorted.
     */
    private void insertionSort(int[] arr)
    {
        // One by one move boundary of unsorted subarray 
        for (int i = 1; i < arr.length; i++) 
        {
            // Store first element of unsorted subarray
            int current = arr[i];
            // While previous neighbor is larger than stored element...
            int j = i - 1;
            while (j >= 0 && current < arr[j]) 
            {
                // Shift previous neighbor up one index
                arr[j+1] = arr[j];
                j--;
            }
            // Insert stored element into index before shifted larger neighbors
            arr[j+1] = current;
        }
    }

    //=========================================================================
    // Merge sort
    //=========================================================================
    /**
     * Performs merge sort on the input array. Algorithm modified from 
     * https://stackabuse.com/sorting-algorithms-in-java/#mergesort
     * @param arr The array to be sorted.
     * @param leftIndex The leftmost index of the array (or subarray).
     * @param rightIndex The rightmost index of the array (or subarray).
     */
    private void mergeSort(int[] arr, int leftIndex, int rightIndex)
    {
        // If subarray is of size 1, stop recursion
        if (rightIndex <= leftIndex) return;
        // Apply mergesort to left and right subarrays
        int middleIndex = (leftIndex + rightIndex)/2;
        mergeSort(arr, leftIndex, middleIndex);
        mergeSort(arr, middleIndex + 1, rightIndex);
        // Merge newly sorted left and right subarrays together
        merge(arr, leftIndex, middleIndex, rightIndex);
    }

    /**
      * Sorts the input array (left and right halfs already sorted). By
      * splitting each sorted half into separate subarrays and combining them 
      * into a single sorted array. Algorithm modified from 
      * https://stackabuse.com/sorting-algorithms-in-java/#mergesort
      * @param arr The array to be sorted. Contains left and right halves that 
      * have already been sorted.
      * @param leftIndex The leftmost index of the array.
      * @param middleIndex The middle index of the array. (i.e. the last index of the left subarray)
      * @param rightIndex The rightmost index of the array.
      */
    private void merge(int[] arr, int leftIndex, int middleIndex, int rightIndex) 
    { 
        // Step 1) Split array with sorted halves into 2 sorted subarrays (left and right)
        int sizeLeft = middleIndex - leftIndex + 1;
        int sizeRight = rightIndex - middleIndex; 
        int[] L = new int[sizeLeft]; 
        int[] R = new int[sizeRight];
        for (int i = 0; i < sizeLeft; i++) 
        {
            L[i] = arr[leftIndex + i];    
        }
        for (int i = 0; i < sizeRight; i++) 
        {
            R[i] = arr[middleIndex + 1 + i]; 
        }
        // Step 2) Copy data from sorted subarrays into single sorted array
        int i = 0; // Initial index of left subarray 
        int j = 0; // Initial index of right subarray 
        int k = leftIndex; // Initial index of single, merged array 
        // Until one subarray is completely copied over into new array...
        while (i < sizeLeft && j < sizeRight) 
        { 
            if (L[i] <= R[j]) 
            { 
                arr[k] = L[i]; 
                i++; 
            }
            else
            { 
                arr[k] = R[j]; 
                j++; 
            } 
            k++; 
        } 
        // Copy the remaining elements of left subarray, if any
        while (i < sizeLeft) 
        { 
            arr[k] = L[i]; 
            i++; 
            k++; 
        } 
        // Copy the remaining elements of right subarray, if any
        while (j < sizeRight) 
        { 
            arr[k] = R[j]; 
            j++; 
            k++; 
        } 
    }

    //=========================================================================
    // Quick sort
    //=========================================================================
    /**
     * Performs quick sort on the input array. Algorithm modified from 
     * https://www.geeksforgeeks.org/quicksort-using-random-pivoting/.
     * @param arr The array to be sorted.
     * @param leftIndex The leftmost index of the array.
     * @param rightIndex The rightmost index of the array.
     */
    private void quickSort(int[] arr, int leftIndex, int rightIndex) 
    {
        // If subarray only contains one element, stop recursion
        if (leftIndex < rightIndex) 
        { 
            // Partition array and recieive partition index
            int pIndex = partition(arr, leftIndex, rightIndex); 
            // Apply quicksort to left and right subarrays
            quickSort(arr, leftIndex, pIndex-1); 
            quickSort(arr, pIndex+1, rightIndex); 
        } 
    } 

    /**
     * Selects element at end of array to be the pivot. Then, swaps other
     * elements such that all elements to the left of the pivot are lesser and 
     * all elements to the right of the pivot are greater. Algorithm modified 
     * from https://www.geeksforgeeks.org/quicksort-using-random-pivoting/.
     * @param arr The array to be sorted.
     * @param leftIndex The leftmost index of the array.
     * @param rightIndex The rightmost index of the array.
     * @return The partition index of the rearranged array.
     */
    private int partition (int[] arr, int startIndex, int endIndex) 
    {
        // Select random element as pivot and swap to last index
        Random rand = new Random();
        int randomIndex = rand.nextInt(endIndex - startIndex) + startIndex;
        swap(arr, randomIndex, endIndex); 
        int pivot = arr[endIndex];
        // Create partition index used to swap smaller values into left subarray
        int pIndex = (startIndex - 1); 
        // For every element in the given range...
        for (int j = startIndex; j <= endIndex - 1; j++) 
        { 
            // If element is smaller than or equal to pivot...
            if (arr[j] <= pivot) 
            { 
                pIndex++;
                // Swap into left subarray
                swap(arr, pIndex, j);
            } 
        }
        // Swap pivot to end of the left subarray
        swap(arr, pIndex+1, endIndex);
        return pIndex + 1; 
    }

    /**
     * Swaps the values of two index positions in the input array.
     * @param arr The array containing values to be swapped.
     * @param index1 The index of the first value.
     * @param index2 The index of the second value.
     */
    private void swap(int[] arr, int index1, int index2)
    {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    //================================================================================
    // Main
    //================================================================================
    /**
     * Main method to time various sorting algorithms.
     */
    public static void main(String[] args) 
    {
        SortingAlgorithms sa = new SortingAlgorithms();
        // Get user input
        String[] inputs = sa.getUserInput();
        String order = inputs[0];
        String size = inputs[1];
        String algorithm = inputs[2];
        // Create array
        int[] array = sa.generateArray(order, size);
        // Sort while recording time
        float startTime = System.nanoTime();
        sa.sort(algorithm, array);
        float endTime = System.nanoTime();
        float totalTime = (endTime - startTime) / 1000000000;
        // Print results
        System.out.printf( "The " + order + " array of size " + size + " took %.9f seconds to " + algorithm + " sort.\n", totalTime);
    }
}
