package algorithmPerformance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Comparator;

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
		
		experimentalRun(4);
		experimentalRun(6);
		experimentalRun(8);

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
	
	/**
	 * This method calls the permutation generator and the sorting methods and 
	 * stores the sorting results in List<SortResult>, and then returns a map of 
	 * the algorithm names from the Algorithm Enum with the respective list.
	 * 
	 * @param i is an integer of the number of digits in the array to be permuted
	 * @return Map<Algorithm, List<SortResult>>
	 */
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
	
	/**
	 * Runs the full Part 4 experiment for a given n and does the following:
	 * (1) generates all permutations,
	 * (2) sorts with all four algorithms, and then 
	 * (3) outputs best 10, worst 10, and average comparisons to console.
	 * 
	 * @param n the size of the array to permute
	 */
	private static void experimentalRun(int n) {
		Map<Algorithm, List<SortResult>> results = sortPermutedArrays(n);
		int permCount = PermutationGenerator.factorial(n);
 
		System.out.println("=== n = " + n + " (" + permCount + " permutations) ===\n");
 
		for (Algorithm alg : Algorithm.values()) {
			// Sort results by comparison count ascending (best first)
			List<SortResult> sorted = results.get(alg).stream()
				.sorted(Comparator.comparingLong(SortResult::comparisons))
				.toList();
 
			List<SortResult> best10 = sorted.stream().limit(10).toList();
			List<SortResult> worst10 = sorted.reversed().stream().limit(10).toList();
			double average = sorted.stream()
				.mapToLong(SortResult::comparisons)
				.average()
				.orElse(0.0);
 
			printAlgorithmSummary(alg, best10, worst10, average);
		}
	}
	
	/**
	 * This method prints the best 10, worst 10, and average comparisons for 
	 * a single algorithm in tab-separated format.
	 * 
	 * @param alg		the algorithm
	 * @param best10	the 10 permutations with fewest comparisons
	 * @param worst10	the 10 permutations with most comparisons
	 * @param average	the mean comparisons across all permutations
	 */
	private static void printAlgorithmSummary(Algorithm alg, List<SortResult> best10,
			List<SortResult> worst10, double average) {
		System.out.println("--- " + alg + " Best 10 ---");
		System.out.println("Original Array\tComparisons");
		for (SortResult r : best10) {
			System.out.println(Arrays.toString(r.originalArray()) + "\t" + r.comparisons());
		}

		System.out.println("--- " + alg + " Worst 10 ---");
		System.out.println("Original Array\tComparisons");
		for (SortResult r : worst10) {
			System.out.println(Arrays.toString(r.originalArray()) + "\t" + r.comparisons());
		}

		System.out.printf("--- %s Average: %.2f ---%n%n", alg, average);
	}
}

