//Basel Al-Dwairi 20221502065
//Mergesort Assignment

package sorting; // delete if not using a package

import java.nio.file.attribute.AclEntry;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Random;

public class Sort {

	public static final int SELECTION_SORT = 0;
	public static final int BUBBLE_SORT = 1;
	public static final int INSERCTION_SORT = 2;
	public static final int COUNTING_SORT = 3;
	public static final int MERGE_SORT = 4;
	public static final int QUICK_SORT = 5;
	public static final int HEAP_SORT = 6;
	public static final int SLEEP_TIME = 0;

	public static void printArray(int[] A) {

		int n = A.length;
		for (int i = 0; i < n; i++) {
			System.out.print(A[i] + " ");
		}
		System.out.println();
	}

	public static boolean isSorted(int[] A) {
		for (int i = 0; i < A.length - 1; i++) {
			if (A[i] > A[i + 1])
				return false;
		}
		return true;
	}

	public static void selectionSort(int[] A) {
		for (int i = 0; i < A.length; i++) {
			for (int j = i + 1; j < A.length; j++) {
				if (A[i] > A[j]) {
					A[i] = A[i] ^ A[j];
					A[j] = A[i] ^ A[j];
					A[i] = A[i] ^ A[j];
				}
			}
		}
	}

	public static void bubbleSort(int[] A) {
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A.length - i - 1; j++) {
				if (A[j] > A[j + 1]) {
					A[j + 1] = A[j + 1] ^ A[j];
					A[j] = A[j + 1] ^ A[j];
					A[j + 1] = A[j + 1] ^ A[j];
				}
			}
		}
	}

	public static void insertionSort(int[] A) {
		for (int i = 1; i < A.length; i++) {
			int x = A[i];
			int j = i - 1;
			try {
				while (j > -1 && A[j] > x) {
					A[j + 1] = A[j];
					j--;
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				e.printStackTrace();
			} finally {
				A[j + 1] = x;
			}

		}
	}

	public static void countingSort(int[] A) {
		int min = A[0];
		int max = A[0];
		for (int i = 1; i < A.length; i++) {
			if (min > A[i])
				min = A[i];
			if (max < A[i])
				max = A[i];
		}

		int[] B;
		try {
			B = new int[max - min + 1];
		} catch (OutOfMemoryError e) {
			throw new OutOfMemoryError("Error : B exceeds allowed memory in counting Sort {" + (max - min + 1) + "}");
		}

		int[] C = new int[A.length];
		for (int i = 0; i < B.length; i++) {
			B[i] = 0;
		}
		for (int i = 0; i < A.length; i++) {
			B[A[i] - min]++;
		}
		for (int i = 0; i < B.length - 1; i++) {
			B[i + 1] = B[i + 1] + B[i];
		}
		for (int i = 0; i < A.length; i++) {
			C[B[A[i] - min] - 1] = A[i];
			B[A[i] - min]--;
		}

		for (int i = 0; i < C.length; i++) {
			A[i] = C[i];
		}
	}

	public static void mergeSort(int[] A) {

		int n = A.length;
		int floorn = (int) Math.floor(n / 2.0);
		int ceiln = (int) Math.ceil(n / 2.0);

		if (n > 1) {
			int B[] = new int[ceiln];
			int C[] = new int[floorn];

			for (int i = 0; i < floorn; i++) {
				B[i] = A[i];
				C[i] = A[ceiln + i];
			}
			if (floorn != ceiln)
				B[floorn] = A[floorn];

			mergeSort(B);
			mergeSort(C);
			merge(A, B, C);

		}

	}

	public static void merge(int[] A, int[] B, int[] C) {
		int i = 0, j = 0, k = 0;
		int n = A.length;
		int floorn = (int) Math.floor(n / 2.0);
		int ceiln = (int) Math.ceil(n / 2.0);
		while (j < ceiln && k < floorn) {
			if (B[j] <= C[k]) {
				A[i] = B[j];
				j++;
				i++;
			} else {
				A[i] = C[k];
				k++;
				i++;

			}
		}

		while (j < ceiln) {
			A[i] = B[j];
			j++;
			i++;
		}
		while (k < floorn) {
			A[i] = C[k];
			k++;
			i++;
		}

	}

	public static void quickSort(int[] A, int lo, int hi) {
		if (lo < hi) {
			int pivot = partition(A, lo, hi);

			quickSort(A, lo, pivot - 1);
			quickSort(A, pivot, hi);
		}
	}

	public static int partition(int[] A, int lo, int hi) {

		int pivot = A[lo];
		int i = lo - 1;
		int j = hi + 1;
		boolean bi = true;
		boolean bj = true;
		try {
			while (true) {

				while (bi && i < hi) {

					i++;
					if (A[i] >= pivot)
						bi = false;
				}

				while (bj && j > lo) {
					j--;

					if (A[j] <= pivot)
						bj = false;
				}

				if (i < j) {
					A[i] = A[i] ^ A[j];
					A[j] = A[i] ^ A[j];
					A[i] = A[i] ^ A[j];
					bi = true;
					bj = true;
				} else
					return j + 1;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("i " + i + " j " + j);
		}
		return j;

	}

	public static void heapify(int[] A, int n, int i) {
		int largest = i;
		int left = 2 * i + 1;
		int right = 2 * i + 2;

		if (left < n && A[left] > A[largest]) {
			largest = left;
		}
		if (right < n && A[right] > A[largest]) {
			largest = right;
		}
		if (largest != i) {

			A[i] = A[i] ^ A[largest];
			A[largest] = A[i] ^ A[largest];
			A[i] = A[i] ^ A[largest];
			heapify(A, n, largest);
		}

	}

	public static void HeapSort(int[] A) {
		int n = A.length;
		for (int i = n / 2 - 1; i >= 0; i--) {
			heapify(A, n, i);
		}
		for (int i = n - 1; i > 0; i--) {
			A[0] = A[0] ^ A[i];
			A[i] = A[0] ^ A[i];
			A[0] = A[0] ^ A[i];
			heapify(A, i, 0);
		}

	}

	public static int[] randomArray(int size, int min, int max) {

		int[] array = new int[size];
		Random random = new Random();

		for (int i = 0; i < size; i++) {
			array[i] = (int) (random.nextInt(2 * max + 1) + min);
		}
		return array;
	}

	public static Duration getSortTime(int[] A, int Algorithm) {
		Instant start = Instant.now();
		if (Algorithm == SELECTION_SORT) {
			selectionSort(A);
		}
		if (Algorithm == BUBBLE_SORT) {
			bubbleSort(A);
		}
		if (Algorithm == INSERCTION_SORT) {
			insertionSort(A);
		}
		if (Algorithm == COUNTING_SORT) {
			countingSort(A);
		}
		if (Algorithm == MERGE_SORT) {
			mergeSort(A);
		}
		if (Algorithm == QUICK_SORT) {
			quickSort(A, 0, A.length - 1);
		}
		if (Algorithm == HEAP_SORT) {
			HeapSort(A);
		}

		Instant end = Instant.now();
		return Duration.between(start, end);

	}

	public static void main(String[] args) {

		int[] array1 = { 17, 654, 19, 5, 3, 7, 123, 43, 1, 6, 0, -3, -9 };
		int[] array2 = { 1, 2, 5, 7, 1, 0, 4, 3, 43, 1, 5, 3, 2, 4, 0, 11, 23, 22, 54 };

		int[] arrayRandom = randomArray(100000000, -10000, 10000); // makes a random array and then we sort it so we
																		// can
																		// test
		// random
		// numbers

		int[] arrayDuplicate = arrayRandom.clone();
		int[] arrayDuplicate2 = arrayRandom.clone();
		int[] arrayDuplicate3 = arrayRandom.clone();

		/*
		 * mergeSort(array1); uncomment to and edit the arrays to check correctness
		 * System.out.println("Array 1 sorted : "); printArray(array1);
		 * mergeSort(array2); System.out.println("Array 2 sorted : ");
		 * printArray(array2);
		 */
//		System.out.print("Random array  : ");
//		System.out.println(isSorted(arrayRandom));
//		printArray(arrayDuplicate2);
		new Thread(() -> {

			System.out.println("Merge Sort : " + getSortTime(arrayRandom, MERGE_SORT));

			System.out.println("After Merge sorting : " + isSorted(arrayRandom));
			// printArray(arrayRandom);
		}).start();
		new Thread(() -> {

			System.out.println("Counting Sort : " + getSortTime(arrayDuplicate, COUNTING_SORT));

			System.out.println("After Counting sorting : " + isSorted(arrayDuplicate));
			// printArray(arrayDuplicate);

		}).start();
		new Thread(() -> {

			System.out.println("Heap Sort : " + getSortTime(arrayDuplicate2, HEAP_SORT));

//			 printArray(arrayDuplicate2);
			System.out.println("After Heap sorting : " + isSorted(arrayDuplicate2));

		}).start();
		new Thread(() -> {

			System.out.println("Quick Sort : " + getSortTime(arrayDuplicate3, QUICK_SORT));

			System.out.println("After Quick sorting : " + isSorted(arrayDuplicate3));
			// printArray(arrayDuplicate3);

		}).start();
	}

}
