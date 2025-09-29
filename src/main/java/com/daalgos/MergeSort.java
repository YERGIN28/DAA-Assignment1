package com.daalgos;

import com.daalgos.util.Metrics;

public class MergeSort {
    public static void sort(int[] arr, Metrics metrics) {
        if (arr == null || arr.length <= 1) return;

        int[] buffer = new int[arr.length];
        mergeSort(arr, buffer, 0, arr.length - 1, metrics);
    }

    private static void mergeSort(int[] arr, int[] buffer, int left, int right, Metrics metrics) {
        if (left >= right) return;

        int mid = (left + right) / 2;
        mergeSort(arr, buffer, left, mid, metrics);
        mergeSort(arr, buffer, mid + 1, right, metrics);
        merge(arr, buffer, left, mid, right, metrics);
    }

    private static void merge(int[] arr, int[] buffer, int left, int mid, int right, Metrics metrics) {
        int i = left, j = mid + 1, k = left;

        while (i <= mid && j <= right) {
            if (metrics != null) metrics.incComparisons();
            if (arr[i] <= arr[j]) {
                buffer[k++] = arr[i++];
            } else {
                buffer[k++] = arr[j++];
            }
        }
        while (i <= mid) buffer[k++] = arr[i++];
        while (j <= right) buffer[k++] = arr[j++];

        for (i = left; i <= right; i++) {
            arr[i] = buffer[i];
        }
    }


    public static void sort(int[] arr) {
        sort(arr, null);
    }
}
