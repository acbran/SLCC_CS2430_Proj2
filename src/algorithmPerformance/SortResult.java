package algorithmPerformance;

/**
 * Team Name: Team 2
 * Members: Alexander Branch Julian Cloward Logan Chess
 * Course: CS 2430, section 502
 * Project: Programming Project 2 - Spring 2026
 * 
 * Creates an object to store data from sorting arrays. Holds the original (unsorted) array
 * and the number of comparisons required to sort it.
 */
public class SortResult {
    private final int[] originalArray;
    private final long comparisons;

    public SortResult(int[] originalArray, long l) {
        this.originalArray = originalArray;
        this.comparisons = l;
    }

    public int[] originalArray() { return originalArray; }
    public long comparisons() { return comparisons; }

}
