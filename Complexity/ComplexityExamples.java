package Complexity;

/**
 * ComplexityExamples
 */
public class ComplexityExamples 
{
    int factorial(int n)
    {
        if (n <= 1) { return 1; }
        else { return n * factorial(n-1); }
    }

    int fibonacci(int n) 
    {
        if (n == 0 || n == 1) { return n; }
        else { return fibonacci(n-1) + fibonacci(n-2); }
    }

    public static void main(String[] args) 
    {
        //=====================================================================
        // Example
        //=====================================================================
        int[] arr = {1, 2, 3, 4};
        int n = arr.length;

        int sum = 0;                    // 1 op = O(1)
        for (int i = 0; i < n; i++)     // (1 op) + (1 op * n+1 loops) + (1 op * n loops) = O(1 + (n+1) + n) = O(2n + 2)
        {
            sum += arr[i];              // 3 op * n loop = O(3n)
        }
                                        // Time complexity: (1) + (2n + 2) + (3n) = O(5n + 3) = O(n)

        sum = sum + 1;

        //=====================================================================
        // Example
        //=====================================================================
        int[][] arr = {{1,2}, {3,4}, {5,6}};

        for (int i = 0; i < n; i++)     // (1 op) + (1 op * n+1 loops) + (1 op * n loops) = O(1 + (n+1) + n) = O(2n + 2)
        {
            for (int j = 0; j < n; j++) // ((1 op) + (1 op * n+1 loops) + (1 op * n loops)) * n loops = O(n + (n^2+n) + n^2) = O(2n^2 + 2n)
            {
                arr[i][j] = i * j;      // (3 ops * n loops) * n loops = 3n = O(3n^2)
            }
        }                               // Time complexity: (2n + 2) + (2n^2 + 2n) + 3n^2 = 5n^2 + 4n + 2 = O(n^2)
                                        


        for (int i = n; i >= 1; i--)        // O(n)
        {
            for (int j = i; j <= n; j++)    // O(n) * n = O(n^2)
            {
                arr[i][j] = 0;              // O(1) * n * n = O(n^2)
            }
        }                                   // Time complexity: O(n^2)
                                            


        for (int i = 1; i <= n; i++)        // O(n)
        {
            for (int j = 1; j <= i; j++)    // O(n) * n(n+1)/2 loops (repeats for cases where j = 1 to j = n) = O(n^2)
            {
                ...                         // ... * n(n+1)/2 loops
            }
        }                                   // Time complexity: O(n^2)
                                            


        for (int i = 0; i < n; i++)         // O(n)
        {
            for (int j = n; j > 1; j/=2)    // O(log n) * n loops
            {
                ...
            }
        }                                   // Time complexity: O(n log n)
                                            
    }
}