package com.daalgos;




import com.daalgos.util.Metrics;

public class MergeSort {
    private static Metrics metrics;

    public static void sort(int[] arr, Metrics m) {
        metrics = m;
        metrics.start();
        int[] buffer = new int[arr.length];
        metrics.incAllocations();
        mergesort(arr, 0, arr.length - 1, buffer);
        metrics.stop();
    }

    private static void mergesort(int[] arr, int left, int right, int[] buffer) {
        metrics.enterRecursion();

        if (right - left < 16) { // cutoff insertion sort
            insertionSort(arr, left, right);
            metrics.exitRecursion();
            return;
        }

        if (left < right) {
            int mid = (left + right) / 2;
            mergesort(arr, left, mid, buffer);
            mergesort(arr, mid + 1, right, buffer);
            merge(arr, left, mid, right, buffer);
        }

        metrics.exitRecursion();
    }

    private static void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= left) {
                metrics.incComparisons();
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    j--;
                } else break;
            }
            arr[j + 1] = key;
        }
    }

    private static void merge(int[] arr, int left, int mid, int right, int[] buffer) {
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            metrics.incComparisons();
            if (arr[i] <= arr[j]) buffer[k++] = arr[i++];
            else buffer[k++] = arr[j++];
        }
        while (i <= mid) buffer[k++] = arr[i++];
        while (j <= right) buffer[k++] = arr[j++];
        for (i = left; i <= right; i++) arr[i] = buffer[i];
    }
}

