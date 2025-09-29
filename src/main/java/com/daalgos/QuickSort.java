package com.daalgos;

import com.daalgos.util.Metrics;
import java.util.Random;

public class QuickSort {
    private static final Random rand = new Random();

    public static void sort(int[] arr, Metrics metrics) {
        quickSort(arr, 0, arr.length - 1, metrics);
    }

    private static void quickSort(int[] arr, int low, int high, Metrics metrics) {
        while (low < high) {
            int pivotIndex = partition(arr, low, high, metrics);

            if (pivotIndex - low < high - pivotIndex) {
                quickSort(arr, low, pivotIndex - 1, metrics);
                low = pivotIndex + 1;
            } else {
                quickSort(arr, pivotIndex + 1, high, metrics);
                high = pivotIndex - 1;
            }
        }
    }

    private static int partition(int[] arr, int low, int high, Metrics metrics) {
        int pivotIndex = low + rand.nextInt(high - low + 1);
        int pivot = arr[pivotIndex];
        swap(arr, pivotIndex, high);

        int i = low;
        for (int j = low; j < high; j++) {
            if (metrics != null) metrics.incComparisons();
            if (arr[j] < pivot) {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, high);
        return i;
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    public static void sort(int[] arr) {
        sort(arr, null);
    }
}
