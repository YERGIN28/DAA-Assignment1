package com.daalgos;
import java.util.Arrays;
import com.daalgos.util.Metrics;

public class DeterministicSelect {
    private static Metrics metrics;

    public static int select(int[] arr, int k, Metrics m) {
        metrics = m;
        metrics.start();
        int result = selectRecursive(arr, 0, arr.length - 1, k);
        metrics.stop();
        return result;
    }

    private static int selectRecursive(int[] arr, int left, int right, int k) {
        metrics.enterRecursion();

        if (left == right) {
            metrics.exitRecursion();
            return arr[left];
        }

        int pivot = medianOfMedians(arr, left, right);
        int pivotIndex = partition(arr, left, right, pivot);

        int length = pivotIndex - left + 1;
        if (k == length) {
            metrics.exitRecursion();
            return arr[pivotIndex];
        } else if (k < length) {
            int res = selectRecursive(arr, left, pivotIndex - 1, k);
            metrics.exitRecursion();
            return res;
        } else {
            int res = selectRecursive(arr, pivotIndex + 1, right, k - length);
            metrics.exitRecursion();
            return res;
        }
    }

    private static int medianOfMedians(int[] arr, int left, int right) {
        int n = right - left + 1;
        if (n < 5) {
            insertionSort(arr, left, right);
            return arr[left + n / 2];
        }

        int numMedians = (int) Math.ceil(n / 5.0);
        for (int i = 0; i < numMedians; i++) {
            int subLeft = left + i * 5;
            int subRight = Math.min(subLeft + 4, right);
            insertionSort(arr, subLeft, subRight);
            int median = arr[subLeft + (subRight - subLeft) / 2];
            swap(arr, left + i, subLeft + (subRight - subLeft) / 2);
        }

        return medianOfMedians(arr, left, left + numMedians - 1);
    }

    private static int partition(int[] arr, int left, int right, int pivot) {
        int pivotIndex = left;
        for (int i = left; i <= right; i++) {
            metrics.incComparisons();
            if (arr[i] == pivot) {
                pivotIndex = i;
                break;
            }
        }
        swap(arr, pivotIndex, right);
        int store = left;
        for (int i = left; i < right; i++) {
            metrics.incComparisons();
            if (arr[i] < pivot) swap(arr, store++, i);
        }
        swap(arr, store, right);
        return store;
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

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}

