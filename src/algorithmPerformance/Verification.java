package algorithmPerformance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Team Name: Team 2
 * Members: Alexander Branch Julian Cloward Logan Chess
 * Course: CS 2430, section 502
 * Project: Programming Project 2 - Spring 2026
 *
 * Verifies correctness of sorting algorithms and permutation generator
 * through edge case testing and exhaustive correctness checks.
 */
public class Verification {

    private static int testsPassed = 0;
    private static int testsFailed = 0;
    
    public static void main(String[] args) {
        System.out.println("=== Edge Case Tests ===\n");
        edgeCaseTests();
 
        System.out.println("\n=== Permutation Generator Tests ===\n");
        testPermutationGenerator(0);
        testPermutationGenerator(1);
        testPermutationGenerator(2);
        testPermutationGenerator(3);
        testPermutationGenerator(4);
        testPermutationGenerator(6);
 
        System.out.println("\n=== Exhaustive Correctness Tests ===\n");
        verifyCorrectness(8);
 
        System.out.println("\n=== Summary ===");
        System.out.println("Passed: " + testsPassed);
        System.out.println("Failed: " + testsFailed);
        System.out.println("Total:  " + (testsPassed + testsFailed));
    }
    
    /**
     * Tests each algorithm on boundary inputs: empty arrays,
     * single elements, two-element sorted and reversed arrays.
     */
    private static void edgeCaseTests() {
        int[] empty = {};
        int[] single = {0};
        int[] sorted2 = {0, 1};
        int[] reversed2 = {1, 0};
 
        // This tests for empty array
        // each check should return 0 comparisons and not crash
        checkComparisons("HeapSort empty",   HeapSort.sort(empty.clone()),   0);
        checkComparisons("MergeSort empty",  MergeSort.sort(empty.clone()),  0);
        checkComparisons("QuickSort empty",  QuickSort.sort(empty.clone()),  0);
        checkComparisons("ShakerSort empty", ShakerSort.sort(empty.clone()), 0);
 
        // This tests for a single element
        // each check should already be sorted and therefore 0 comparisons
        checkComparisons("HeapSort single",   HeapSort.sort(single.clone()),   0);
        checkComparisons("MergeSort single",  MergeSort.sort(single.clone()),  0);
        checkComparisons("QuickSort single",  QuickSort.sort(single.clone()),  0);
        checkComparisons("ShakerSort single", ShakerSort.sort(single.clone()), 0);
 
        // This checks for two elements. 
        // Therefore it verifies sorting correctness and that comparisons > 0
        checkSortAndNonZero("HeapSort [0,1]",   sorted2.clone(),   Verification::heapSort);
        checkSortAndNonZero("HeapSort [1,0]",   reversed2.clone(), Verification::heapSort);
        checkSortAndNonZero("MergeSort [0,1]",  sorted2.clone(),   Verification::mergeSort);
        checkSortAndNonZero("MergeSort [1,0]",  reversed2.clone(), Verification::mergeSort);
        checkSortAndNonZero("QuickSort [0,1]",  sorted2.clone(),   Verification::quickSort);
        checkSortAndNonZero("QuickSort [1,0]",  reversed2.clone(), Verification::quickSort);
        checkSortAndNonZero("ShakerSort [0,1]", sorted2.clone(),   Verification::shakerSort);
        checkSortAndNonZero("ShakerSort [1,0]", reversed2.clone(), Verification::shakerSort);
    }
    
    /**
     * Verifies the permutation generator produces the correct count
     * It should be n-factorial with no duplicates.
     */
    private static void testPermutationGenerator(int n) {
        ArrayList<int[]> perms = PermutationGenerator.permutationGenerator(n);
        int expected = PermutationGenerator.factorial(n);
 
        // Check count
        if (perms.size() == expected) {
            pass("PermGen n=" + n + " count: expected " + expected + ", got " + perms.size());
        } else {
            fail("PermGen n=" + n + " count: expected " + expected + ", got " + perms.size());
        }
 
        // Check for duplicates
        Set<String> seen = new HashSet<>();
        boolean hasDuplicates = false;
        for (int[] p : perms) {
            if (!seen.add(Arrays.toString(p))) {
                hasDuplicates = true;
                break;
            }
        }
        if (!hasDuplicates) {
            pass("PermGen n=" + n + " no duplicates");
        } else {
            fail("PermGen n=" + n + " has duplicates");
        }
    }
    /**
     * For every permutation that has a size that is greater than 
     * 0 but smaller than or equal to 'maxN', it runs all four 
     * algorithms and verifies the output array is sorted.
     * 
     * @param maxN	...
     */
    private static void verifyCorrectness(int maxN) {
        for (int n = 0; n <= maxN; n++) {
            ArrayList<int[]> perms = PermutationGenerator.permutationGenerator(n);
            boolean allPassed = true;
 
            for (int[] p : perms) {
                for (Algorithm alg : Algorithm.values()) {
                    int[] clone = p.clone();
                    switch (alg) {
                        case HEAP -> HeapSort.sort(clone);
                        case MERGE -> MergeSort.sort(clone);
                        case QUICK -> QuickSort.sort(clone);
                        case SHAKER -> ShakerSort.sort(clone);
                    }
                    if (!isSorted(clone)) {
                        fail(alg + " FAILED on " + Arrays.toString(p));
                        allPassed = false;
                    }
                }
            }
 
            if (allPassed) {
                pass("n=" + n + ": all " + perms.size() 
                	 + " permutations sorted correctly by all algorithms");
            }
        }
    }
    
    /** 
     * This method returns true if the array 
     * is in non-decreasing order. In other words,
     * if the array is sorted. 
     * 
     * @param array	the array that gets checked to see if it's sorted
     */
    private static boolean isSorted(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) return false;
        }
        return true;
    }

    /** 
     * This method checks that comparison count 
     * equals the expected value. 
     * 
     * @param label		...
     * @param actual	the value the method actually produced
     * @param expected	the value the method should have produced
     */
    private static void checkComparisons(String label, long actual, long expected) {
        if (actual == expected) {
            pass(label + ": " + actual + " comparisons");
        } else {
            fail(label + ": expected " + expected + " comparisons, got " + actual);
        }
    }

    /** 
     * This method sorts the array with the given algorithm and
     * checks it's sorted and made at least 1 comparison. 
     */
    private static void checkSortAndNonZero(String label, int[] array, SortFunction fn) {
        long comps = fn.sort(array);
        if (isSorted(array) && comps > 0) {
            pass(label + ": sorted with " + comps + " comparisons");
        } else if (!isSorted(array)) {
            fail(label + ": array not sorted");
        } else {
            fail(label + ": expected > 0 comparisons, got " + comps);
        }
    }
    
    /** 
     * Functional interface so we can pass sort methods as arguments. 
     * 
     * Without it, we'd have to write out full logic for each algorithm
     * in each test.
     */
    @FunctionalInterface
    private interface SortFunction {
        long sort(int[] array);
    }
 
    // Wrappers to match the SortFunction interface
    private static long heapSort(int[] a)   { return HeapSort.sort(a); }
    private static long mergeSort(int[] a)  { return MergeSort.sort(a); }
    private static long quickSort(int[] a)  { return QuickSort.sort(a); }
    private static long shakerSort(int[] a) { return ShakerSort.sort(a); }
    
    /**
     * Simple method that outputs a success and
     * increments total amount of tests passed.
     * @param msg	reason and context test passed
     */
    private static void pass(String msg) {
        System.out.println("  PASS: " + msg);
        testsPassed++;
    }
 
    /**
     * Simple method that outputs a failure and
     * increments total amount of tests failed.
     * @param msg	reason and context test failed
     */
    private static void fail(String msg) {
        System.err.println("  FAIL: " + msg);
        testsFailed++;
    }
}
