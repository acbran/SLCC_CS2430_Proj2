package algorithmPerformance;
/**
 * Team Name: Team 2
 * Members: Alexander Branch Julian Cloward Logan Chess
 * Course: CS 2430, section 502
 * Project: Programming Project 2 - Spring 2026
 *
 * Uses Shaker Sort algorithm to manulpulate arrays while 
 * keeping track of the number of comparisons.
 */
public class ShakerSort {

    /**
     * Uses a shaker sort to sort items from array input.
     * 
     * @param items the amount of comparisons in the array.
     */
    public static long sort(int[] items) {
        long comparisons = 0;
        int startIndex = 0, endIndex = items.length - 1;
        while (startIndex < endIndex) {
            for (int i = startIndex; i < endIndex; i++) {
                if (items[i] > items[i+1]) {
                    int temp = items[i+1];
                    items[i+1] = items[i];
                    items[i] = temp;
                }
                comparisons++;
            }
            endIndex--;
            for (int i = endIndex; i > startIndex; i--) {
                if (items[i] < items[i-1]) {
                    int temp = items[i-1];
                    items[i-1] = items[i];
                    items[i] = temp;
                }
                comparisons++;
            }
            startIndex++;
        }
        return comparisons;
    }
}
