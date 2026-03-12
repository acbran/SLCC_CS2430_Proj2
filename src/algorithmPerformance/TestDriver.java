package algorithmPerformance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * Team Name: Team 2
 * Members: Alexander Branch Julian Cloward Logan Chess
 * Course: CS 2430, section 502
 * Project: Programming Project 2 - Spring 2026
 * 
 * Test driver contains 3 methods to test and output results from running HeapSort, MergeSort, QuickSort, and 
 * ShakerSort on permutations of integer arrays for small number n. 
 */

public class TestDriver {

	public static void main(String[] args) {

		sortingAlgorithmTest(3);
//		sortingAlgorithmTest(4);
//		sortingAlgorithmTest(6);
		Map<Algorithm, List<SortResult>> results = sortPermutedArrays(3);	
		outputSortResults(results);

	}

	/** Runs the permutation generator and sorting algorithms and for each unsorted array outputs
	 * the array and the number of comparisons each algorithm required to sort it,
	 * fulfilling project requirement part 3.
	 * @param i is an integer of the number of digits in the array to be permuted
	 * @return
	 */
	private static void sortingAlgorithmTest(int i) {
		
		//generate permutations
		ArrayList<int[]> perms = PermutationGenerator.permutationGenerator(i);
		
		//run sorting methods on each permutation in the array
		for (int[] p : perms) {
			
			//initialize comparison counters
			int heapCount = 0;
			int mergeCount = 0;
			int quickCount = 0;
			int shakerCount = 0;
			
			System.out.println("Unsorted Array: " + Arrays.toString(p)); //print the permutation
			heapCount += HeapSort.sort(p.clone());
			mergeCount += MergeSort.sort(p.clone());
			quickCount += QuickSort.sort(p.clone());
			shakerCount += ShakerSort.sort(p.clone());
			
			//print the comparison counts
			System.out.println("HeapSort Comparisons: 		" + heapCount);
			System.out.println("MergeSort Comparisons: 		" + mergeCount);
			System.out.println("QuickSort Comparisons: 		" + quickCount);
			System.out.println("ShakerSort Comparisons: 	" + shakerCount);
			System.out.println();
		}
		
	}
	
	/** Calls the permutation generator and the sorting methods, stores the
	* sorting results in List<SortResult>, and returns a map of the 
	* algorithm names from the Algorithm Enum with the respective list.
	* @param i is an integer of the number of digits in the array to be permuted
	* @return Map<Algorithm, List<SortResult>>
	* */
	private static Map<Algorithm, List<SortResult>> sortPermutedArrays(int i) {
		//Map Algorithms from the algorithm enum to a list of SortResult objects that hold algorithm sorting data
		Map<Algorithm, List<SortResult>> results = new EnumMap<>(Algorithm.class);

		//creates the EnumMap keys and initializes an ArrayList to associate to each key
		for (Algorithm alg : Algorithm.values()) {
		    results.put(alg, new ArrayList<>());
		}
		
		//run the permutation generator
		ArrayList<int[]> perms = PermutationGenerator.permutationGenerator(i);
		for (int[] p : perms) {
			/* for each permutation, add the permutation p and the comparison count from the sort method
			* as a SortResult object to the corresponding EnumMap key */
			results.get(Algorithm.HEAP).add(new SortResult(p, HeapSort.sort(p.clone())));
			results.get(Algorithm.MERGE).add(new SortResult(p, MergeSort.sort(p.clone())));
			results.get(Algorithm.QUICK).add(new SortResult(p, QuickSort.sort(p.clone())));
			results.get(Algorithm.SHAKER).add(new SortResult(p, ShakerSort.sort(p.clone())));
		}
		
		return results;
	}
	
	/**
	 * 
	 * @param results
	 */
	private static void outputSortResults(Map<Algorithm, List<SortResult>> results) {
		/* Output number of results stored for each Algorithm in the Map as a preliminary check that correct number
		* of permutations are being sent to each algorithm for sorting */
		for (Algorithm alg : Algorithm.values()) {
		    System.out.println(alg + " stored " + results.get(alg).size() + " results");
		}
		
		for (Algorithm alg : Algorithm.values()) { //print Algorithm header
		    System.out.println("\n=== " + alg + " ===");

		    
		    results.get(alg).stream() //process the results stored in the Map using stream 
		        .limit(3) // only print first 3 to avoid clutter
		        .forEach(r -> { //for each SortResult object in the Map, print original array and num comparisons
		            System.out.println("Original Array: " + Arrays.toString(r.originalArray()));
		            System.out.println("Comparisons: " + r.comparisons());
		            System.out.println();
		        });
		}
	}
}

