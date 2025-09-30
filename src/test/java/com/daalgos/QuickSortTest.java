package com.daalgos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Random;

public class QuickSortTest {

    @Test
    void testSmallArray() {
        int[] arr = {5, 2, 8, 3, 1};
        QuickSort.sort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 5, 8}, arr);
    }

    @Test
    void testAlreadySorted() {
        int[] arr = {1, 2, 3, 4, 5};
        QuickSort.sort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
    }

    @Test
    void testReversedArray() {
        int[] arr = {9, 8, 7, 6, 5};
        QuickSort.sort(arr);
        assertArrayEquals(new int[]{5, 6, 7, 8, 9}, arr);
    }

    @Test
    void testRandomLargeArray() {
        Random rnd = new Random();
        int[] arr = rnd.ints(1000, -1000, 1000).toArray();
        int[] expected = arr.clone();

        QuickSort.sort(arr);
        Arrays.sort(expected);

        assertArrayEquals(expected, arr);
    }
}
