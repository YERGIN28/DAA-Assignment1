package com.daalgos;


import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class AlgorithmsTest {

    private final Random rand = new Random();

    // ==== MergeSort ====
    @Test
    void testMergeSortRandom() {
        int[] arr = rand.ints(1000, -1000, 1000).toArray();
        int[] expected = arr.clone();
        Arrays.sort(expected);

        MergeSort.sort(arr);

        assertArrayEquals(expected, arr);
    }

    @Test
    void testMergeSortSorted() {
        int[] arr = {1, 2, 3, 4, 5};
        int[] expected = arr.clone();

        MergeSort.sort(arr);

        assertArrayEquals(expected, arr);
    }

    // ==== QuickSort ====
    @Test
    void testQuickSortRandom() {
        int[] arr = rand.ints(1000, -1000, 1000).toArray();
        int[] expected = arr.clone();
        Arrays.sort(expected);

        QuickSort.sort(arr);

        assertArrayEquals(expected, arr);
    }

    @Test
    void testQuickSortAllEqual() {
        int[] arr = new int[1000];
        Arrays.fill(arr, 42);
        int[] expected = arr.clone();

        QuickSort.sort(arr);

        assertArrayEquals(expected, arr);
    }

    // ==== Deterministic Select ====
    @Test
    void testSelectMatchesSorted() {
        for (int t = 0; t < 50; t++) { // можно 100, я сделал поменьше для скорости
            int[] arr = rand.ints(200, -1000, 1000).toArray();
            int[] sorted = arr.clone();
            Arrays.sort(sorted);

            int k = rand.nextInt(arr.length);
            int selected = DeterministicSelect.select(arr.clone(), k);

            assertEquals(sorted[k], selected);
        }
    }

    // ==== Closest Pair ====
    @Test
    void testClosestPairSmall() {
        Point[] points = new Point[200];
        for (int i = 0; i < points.length; i++) {
            points[i] = new Point(rand.nextDouble() * 1000, rand.nextDouble() * 1000);
        }

        double fast = ClosestPair.findClosest(points);
        double slow = ClosestPair.bruteForce(points);

        assertEquals(slow, fast, 1e-9);
    }
}
