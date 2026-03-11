package algorithmPerformance;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Team Name: Team 2
 * Members: Alexander Branch Julian Cloward Logan Chess
 * Course: CS 2430, section 502
 * Project: Programming Project 2 - Spring 2026
 * 
 * Test class runs 4 sorting algorithms on permutations of integer arrays for a small number n and outputs the 
 * count of comparisons each algorithm required to sort a given array.
 */

public class TestDriver {

	public static void main(String[] args) {

		sortingAlgorithmTest(4);
		sortingAlgorithmTest(6);
		sortingAlgorithmTest(8);
	}


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
			heapCount += HeapSort.sort(p);
			mergeCount += MergeSort.sort(p);
			quickCount += QuickSort.sort(p);
			shakerCount += ShakerSort.sort(p);
			
			//print the comparison counts
			System.out.println("HeapSort Comparisons: 		" + heapCount);
			System.out.println("MergeSort Comparisons: 		" + mergeCount);
			System.out.println("QuickSort Comparisons: 		" + quickCount);
			System.out.println("ShakerSort Comparisons: 	" + shakerCount);
			System.out.println();
		}
		
	}
}


