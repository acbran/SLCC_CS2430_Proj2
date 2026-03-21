package algorithmPerformance;

import java.util.ArrayList;
import java.util.Arrays;
/**
 * Team Name: Team 2
 * Members: Alexander Branch Julian Cloward Logan Chess
 * Course: CS 2430, section 502
 * Project: Programming Project 2 - Spring 2026
 * 
 * Utility class uses Heap's Algorithm to generate permutations of numbers 0 to k-1 for integer k.
 * Heap's algorithm generates permutations using swaps and an auxiliary array to track swap counts.
 * Source: https://www.w3tutorials.net/blog/permutations-without-recursive-function-call/
 */
public final class PermutationGenerator {

	/**
	 * Generate permutations of numbers 0 to k-1 for integer k using Heap's Algorithm.
	 * Calls private method swap(int[] array, int x, int y) to swap values in the array.
	 * @param k
	 * @return ArrayList<int[]>
	 */
		public static ArrayList<int[]> permutationGenerator(int k) {
			if (k <= 0) throw new IllegalArgumentException("Integer provided must be positive.");
			
			ArrayList<int[]> result = new ArrayList<int[]>();
			int[] baseArray = new int[k];
			int[] counter = new int[k];
			for (int i = 0; i < k; i++) {
				baseArray[i] = i;
			}
			result.add(Arrays.copyOf(baseArray, k));

			int i = 0;
			while (i < k) {
				if (counter[i] < i) {
					if (i % 2 == 0) {
						swap(baseArray, 0, i);
					} else {
						swap(baseArray, counter[i], i);
					}
					counter[i]++;
					i = 0;
					result.add(Arrays.copyOf(baseArray, k));
				} else {
					counter[i] = 0;
					i++;
				}
			}
			return result;
		}

		
	    /** 
	     * This method computes n! iteratively.
	     * This is to verify accuracy of 
	     * the permutationGenerator() method. 
	     * 
	     * @param n	the number to start at / to factorial
	     */
	    public static int factorial(int n) {
			if (n < 0) {
	            throw new IllegalArgumentException("Factorial is undefined for negative integers.");
	        }
	        int result = 1;
	        for (int i = 2; i <= n; i++) {
	            result *= i;
	        }
	        return result;
	    }
	    
		private static void swap(int[] array, int x, int y) {
			int temp = array[x];
			array[x] = array[y];
			array[y] = temp;
		}

		/**
	     * Converts a list of int[] permutations into a formatted string.
	     *
	     * @param perms list of permutations
	     * @return formatted string representation
	     */
	    public static String toString(ArrayList<int[]> perms) {
	        StringBuilder sb = new StringBuilder();

	        boolean firstPerm = true;
	        for (int[] p : perms) {
	            if (!firstPerm) sb.append("\n");
	            firstPerm = false;

	            sb.append("{");
	            for (int i = 0; i < p.length; i++) {
	                if (i > 0) sb.append(", ");
	                sb.append(p[i]);
	            }
	            sb.append("}");
	        }

	        return sb.toString();
	    }
			
}
