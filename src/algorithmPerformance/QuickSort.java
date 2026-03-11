package algorithmPerformance;
/**
 * Team Name: Team 2
 * Members: Alexander Branch Julian Cloward Logan Chess
 * Course: CS 2430, section 502
 * Project: Programming Project 1 - Spring 2026
 *
 * Implements the quicksort algorithm with element-to-element comparison counting.
 *
 * This implementation selects the last element as the pivot. This makes 
 * comparisons easier since every element in the subarray is compared
 * to the pivot exactly once per partition call.
 */
public class QuickSort {

    /**
     * Sorts the given array in-place using quicksort and returns the num
     * of comparisons performed.
     *
     * @param array 	the array of integers to sort
     * @return the total number of element-to-element comparisons made
     */
    public static long sort(int[] array) {
        long[] compCount = {0};
        quickSort(array, 0, array.length - 1, compCount);
        return compCount[0];
    }

    /**
     * Recursively partitions and sorts subarrays.
     *
     * @param array		the array being sorted
     * @param low		the starting index of the subarray
     * @param high		the ending index of the subarray
     * @param compCount	mutable comparison counter
     */
    private static void quickSort(int[] array, int low, int high, long[] compCount) {
        // Base case: subarrays of size 0 or 1 are already sorted.
        if (low >= high) {
            return;
        }

        // Partition the array around a pivot and get the pivot's final index.
        int pivotIndex = partition(array, low, high, compCount);

        // Recursively sort the elements to the left and right of the pivot.
        quickSort(array, low, pivotIndex - 1, compCount);
        quickSort(array, pivotIndex + 1, high, compCount);
    }

    /**
     * Selects the pivot based on last elem and then rearranges elements 
     * so everything <= pivot is on left and everything > pivot is on right.
     *
     * Every element in the range of low to high-1 is compared to the pivot
     * exactly once, so we increment compCount once per loop iteration.
     *
     * @param array		the array being partitioned
     * @param low		start index of the partition range
     * @param high		end index (also the pivot's initial position)
     * @param compCount	mutable comparison counter
     * @return the final sorted position of the pivot
     */
    private static int partition(int[] array, int low, int high, long[] compCount) {
    	// This selects the last element as the pivot.
    	int pivot = array[high]; 


        // 'i' tracks the boundary: everything at index <= i is <= pivot.
        int i = low - 1;

        for (int j = low; j < high; j++) {
            // Compare each element to the pivot to decide which side it belongs on.
            compCount[0]++;
            if (array[j] <= pivot) {
                i++;
                swap(array, i, j);
            }
        }

        // Place the pivot in its correct sorted position.
        swap(array, i + 1, high);
        return i + 1;
    }

    /**
     * Swaps two elements in the array.
     *
     * @param array	the array
     * @param a		index of the first element
     * @param b		index of the second element
     */
    private static void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}