package com.daalgos;


public class MergeSort {
    private static int[] buffer;

    public static void sort(int[] arr) {
        buffer = new int[arr.length];
        mergesort(arr, 0, arr.length - 1);
    }

    private static void mergesort(int[] arr, int left, int right) {
        // cutoff
        if (right - left <= 16) {
            insertionSort(arr, left, right);
            return;
        }

        int mid = (left + right) / 2;
        mergesort(arr, left, mid);
        mergesort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int i = left, j = mid + 1, k = left;

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                buffer[k++] = arr[i++];
            } else {
                buffer[k++] = arr[j++];
            }
        }
        while (i <= mid) buffer[k++] = arr[i++];
        while (j <= right) buffer[k++] = arr[j++];

        for (k = left; k <= right; k++) arr[k] = buffer[k];
    }

    private static void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= left && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
}
