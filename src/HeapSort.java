/**
 * Team Name: Team 2
 * Members: Alexander Branch Julian Cloward Logan Chess
 * Course: CS 2430, section 502
 * Project: Programming Project 1 - Spring 2026
 * 
 * Heapify and sorts a list of integers while keeping track
 * and returning the number of comparisons.
 */
public class HeapSort {

    /**
     * Heapifies and sorts items
     * 
     * @param items     The items to sort.
     * @return          The number of comparisons from both heapify and sorting.
     */
    public static int sort(int[] items) {
        return heapify(items) + heapSort(items);
    }

    /**
     * Turns the array into a heapified array.
     * 
     * @param items     The array to heapify.
     * @return          number of comparisons.
     */
    public static int heapify(int[] items) {
        int length = items.length;
        int comparisons = 0;

        for (int i = length / 2 - 1; i >= 0; i--) {

            int current = i;

            while (true) {

                int left = 2 * current + 1;
                int right = 2 * current + 2;
                int largest = current;

                if (left < length) {
                    comparisons++;
                    if (items[left] > items[largest])
                        largest = left;
                }

                if (right < length) {
                    comparisons++;
                    if (items[right] > items[largest])
                        largest = right;
                }

                if (largest == current) break;

                int temp = items[current];
                items[current] = items[largest];
                items[largest] = temp;

                current = largest;
            }
        }
        return comparisons;
    }

    /**
     * Orders a heapified array into increasing order.
     * 
     * @param items     Heapified array to order.
     * @return          Number of comparisons.
     */
    public static int heapSort(int[] items) {
        int comparisons = 0;

        for (int i = items.length - 1; i > 0; i--) {
            int temp = items[0];
            items[0] = items[i];
            items[i] = temp;

            int current = 0;

            while (true) {
                int left = 2 * current + 1;
                int right = 2 * current + 2;
                int largest = current;

                if (left < i) {
                    comparisons++;
                    if (items[left] > items[largest])
                        largest = left;
                }

                if (right < i) {
                    comparisons++;
                    if (items[right] > items[largest])
                        largest = right;
                }

                if (largest == current) break;

                int swap = items[current];
                items[current] = items[largest];
                items[largest] = swap;

                current = largest;
            }
        }
        return comparisons;
    }
}
