package com.daalgos;

import java.util.Random;

public class QuickSort {
    private static final Random rnd = new Random();

    public static void sort(int[] arr) {
        quicksort(arr, 0, arr.length - 1);
    }

    private static void quicksort(int[] arr, int left, int right) {
        while (left < right) {
            int pivotIndex = partition(arr, left, right);
            // рекурсируем в меньшую часть, итерация по большей
            if (pivotIndex - left < right - pivotIndex) {
                quicksort(arr, left, pivotIndex - 1);
                left = pivotIndex + 1;
            } else {
                quicksort(arr, pivotIndex + 1, right);
                right = pivotIndex - 1;
            }
        }
    }

    private static int partition(int[] arr, int left, int right) {
        int pivotIndex = left + rnd.nextInt(right - left + 1);
        int pivot = arr[pivotIndex];
        swap(arr, pivotIndex, right);
        int store = left;
        for (int i = left; i < right; i++) {
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
