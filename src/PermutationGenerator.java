package algorithmPerformance;

import java.util.ArrayList;
import java.util.Arrays;

public class PermutationGenerator {

	public static void main(String[] args) {
		ArrayList<int[]> perms = permutationGenerator(3);
		int permCount = 0;
		for (int[] p : perms) permCount++;
		System.out.println("permutations: " + permCount);
		for (int[] p : perms) {
			System.out.println(Arrays.toString(p));
		}
	}
		
	/**
	 * PermutationGenerator uses Heap's Algorithm to generate and store permutations of numbers 0 to k-1 for integer k.
	 * Heap's algorithm generates permutations using swaps and an auxiliary array to track swap counts.
	 * Source: https://www.w3tutorials.net/blog/permutations-without-recursive-function-call/
	 * Calls private method swap(int[] array, int x, int y) to swap values in the array and create a new permutation.
	 * @param k
	 * @return List<int[]>
	 */
		public static ArrayList<int[]> permutationGenerator(int k) {
			ArrayList<int[]> result = new ArrayList<int[]>();
			if (k == 0) return result;
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

		
		private static void swap(int[] array, int x, int y) {
			int temp = array[x];
			array[x] = array[y];
			array[y] = temp;
		}
			
}

