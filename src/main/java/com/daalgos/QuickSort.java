package com.daalgos;

import java.util.Random;


import com.daalgos.util.Metrics;
import java.util.Random;

public class QuickSort {
    private static final Random rnd = new Random();
    private static Metrics metrics;

    public static void sort(int[] arr, Metrics m) {
        metrics = m;
        metrics.start();
        quicksort(arr, 0, arr.length - 1);
        metrics.stop();
    }

    private static void quicksort(int[] arr, int left, int right) {
        while (left < right) {
            metrics.enterRecursion();
            int pivotIndex = partition(arr, left, right);

            if (pivotIndex - left < right - pivotIndex) {
                quicksort(arr, left, pivotIndex - 1);
                left = pivotIndex + 1;
            } else {
                quicksort(arr, pivotIndex + 1, right);
                right = pivotIndex - 1;
            }
            metrics.exitRecursion();
        }
    }

    private static int partition(int[] arr, int left, int right) {
        int pivotIndex = left + rnd.nextInt(right - left + 1);
        int pivot = arr[pivotIndex];
        swap(arr, pivotIndex, right);
        int store = left;
        for (int i = left; i < right; i++) {
            metrics.incComparisons();
            if (arr[i] < pivot) swap(arr, i, store++);
        }
        swap(arr, store, right);
        return store;
    }

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
