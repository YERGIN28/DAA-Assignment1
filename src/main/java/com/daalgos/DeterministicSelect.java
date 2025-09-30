package com.daalgos;

import com.daalgos.util.Metrics;
import java.util.Arrays;

public class DeterministicSelect {

    public static int select(int[] arr, int k, Metrics metrics) {
        if (arr == null || arr.length == 0) throw new IllegalArgumentException("Array is empty");
        if (k < 0 || k >= arr.length) throw new IllegalArgumentException("k is out of range");
        return selectRecursive(arr, 0, arr.length - 1, k, metrics);
    }

    private static int selectRecursive(int[] arr, int left, int right, int k, Metrics metrics) {
        if (left == right) return arr[left];

        int pivotValue = medianOfMedians(arr, left, right);
        int pivotIndex = partition(arr, left, right, pivotValue, metrics);

        if (k == pivotIndex) {
            return arr[pivotIndex];
        } else if (k < pivotIndex) {
            return selectRecursive(arr, left, pivotIndex - 1, k, metrics);
        } else {
            return selectRecursive(arr, pivotIndex + 1, right, k, metrics);
        }
    }


    private static int partition(int[] arr, int left, int right, int pivotValue, Metrics metrics) {

        int pivotIndex = left;
        for (int i = left; i <= right; i++) {
            if (arr[i] == pivotValue) {
                pivotIndex = i;
                break;
            }
        }
        swap(arr, pivotIndex, right);

        int storeIndex = left;
        for (int i = left; i < right; i++) {
            if (metrics != null) metrics.incComparisons();
            if (arr[i] < pivotValue) {
                swap(arr, storeIndex, i);
                storeIndex++;
            }
        }
        swap(arr, storeIndex, right);
        return storeIndex;
    }

    private static int medianOfMedians(int[] arr, int left, int right) {
        int n = right - left + 1;
        if (n <= 5) {
            Arrays.sort(arr, left, right + 1);
            return arr[left + n / 2];
        }

        int numMedians = (n + 4) / 5;
        int[] medians = new int[numMedians];
        for (int i = 0; i < numMedians; i++) {
            int subLeft = left + i * 5;
            int subRight = Math.min(subLeft + 4, right);
            Arrays.sort(arr, subLeft, subRight + 1);
            medians[i] = arr[subLeft + (subRight - subLeft) / 2];
        }

        return medianOfMedians(medians, 0, numMedians - 1);
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    public static int select(int[] arr, int k) {
        return select(arr, k, null);
    }
}
