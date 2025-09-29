package com.daalgos;


import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class DeterministicSelectTest {

    @Test
    void testSmallArray() {
        int[] arr = {7, 2, 1, 6, 8, 5, 3, 4};
        int k = 3;
        int expected = Arrays.stream(arr).sorted().toArray()[k];
        assertEquals(expected, DeterministicSelect.select(arr, k));
    }

    @Test
    void testRandomArrays() {
        Random rnd = new Random();
        for (int t = 0; t < 100; t++) {
            int[] arr = rnd.ints(50, -100, 100).toArray();
            int[] sorted = arr.clone();
            Arrays.sort(sorted);
            for (int k = 0; k < arr.length; k++) {
                assertEquals(sorted[k], DeterministicSelect.select(arr.clone(), k));
            }
        }
    }

    @Test
    void testSingleElement() {
        int[] arr = {42};
        assertEquals(42, DeterministicSelect.select(arr, 0));
    }
}
