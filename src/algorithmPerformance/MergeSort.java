package algorithmPerformance;
/**
 * Team Name: Team 2
 * Members: Alexander Branch Julian Cloward Logan Chess
 * Course: CS 2430, section 502
 * Project: Programming Project 1 - Spring 2026
 *
 * Implements the mergesort algorithm with element-to-element comparison counting.
 * The merge step is where all element-to-element comparisons occur.
 */
public class MergeSort {

    /**
     * Sorts the given array in-place using mergesort and returns the number
     * of element-to-element comparisons performed.
     *
     * @param array 	the array of integers to sort
     * @return the total number of element-to-element comparisons made
     */
    public static long sort(int[] array) {
        // compCount[0] acts as the counter we update through recursive calls.
        long[] compCount = {0};
        mergeSort(array, 0, array.length - 1, compCount);
        return compCount[0];
    }

    /**
     * Recursively divides the array into halves and merges them back in sorted order.
     *
     * @param array     the array being sorted
     * @param left      the starting index of the subarray
     * @param right     the ending index of the subarray
     * @param compCount mutable comparison counter (single-element array)
     */
    private static void mergeSort(int[] array, int left, int right, long[] compCount) {
        // Base case: a subarray of size 0 or 1 is already sorted.
        if (left >= right) {
            return;
        }

        // Find the midpoint to split the array into two halves.
        int mid = left + (right - left) / 2;

        mergeSort(array, left, mid, compCount);
        mergeSort(array, mid + 1, right, compCount);

        merge(array, left, mid, right, compCount);
    }

    /**
     * Merges two sorted subarrays (array[left..mid] and array[mid+1..right])
     * into a single sorted subarray in-place.
     *
     * All element-to-element comparisons happen here, during the merge step.
     *
     * @param array		the array containing both subarrays
     * @param left		the start index of the left subarray
     * @param mid		the end index of the left subarray (mid+1 starts the right)
     * @param right		the end index of the right subarray
     * @param compCount	the comparison counter
     */
    private static void merge(int[] array, int left, int mid, int right, long[] compCount) {
        // Create temporary arrays to hold copies of the two halves.
        int leftSize = mid - left + 1;
        int rightSize = right - mid;

        int[] leftHalf = new int[leftSize];
        int[] rightHalf = new int[rightSize];

        // Copy data into temporary arrays.
        for (int i = 0; i < leftSize; i++) {
            leftHalf[i] = array[left + i];
        }
        for (int j = 0; j < rightSize; j++) {
            rightHalf[j] = array[mid + 1 + j];
        }

        int i = 0;  // index into leftHalf
        int j = 0;  // index into rightHalf
        int k = left; // index into the original array

        while (i < leftSize && j < rightSize) {
            compCount[0]++;
            if (leftHalf[i] <= rightHalf[j]) {
                array[k] = leftHalf[i];
                i++;
            } else {
                array[k] = rightHalf[j];
                j++;
            }
            k++;
        }

        // Copy any remaining elements from the left half.
        while (i < leftSize) {
            array[k] = leftHalf[i];
            i++;
            k++;
        }

        // Copy any remaining elements from the right half.
        while (j < rightSize) {
            array[k] = rightHalf[j];
            j++;
            k++;
        }
    }
}