package com.daalgos;

import com.daalgos.MergeSort;
import com.daalgos.QuickSort;
import com.daalgos.DeterministicSelect;
import com.daalgos.ClosestPair;
import com.daalgos.Point;
import com.daalgos.util.CsvWriter;
import com.daalgos.util.Metrics;


import java.io.IOException;
import java.util.Random;

public class Benchmark {
    public static void main(String[] args) throws IOException {
        CsvWriter writer = new CsvWriter("metrics.csv");
        Random rnd = new Random();

        for (int n : new int[]{100, 1000, 5000, 10000}) {
            int[] arr = rnd.ints(n, -1000, 1000).toArray();

            Metrics m1 = new Metrics();
            MergeSort.sort(arr.clone(), m1);
            writer.write("mergesort", n, m1);

            Metrics m2 = new Metrics();
            QuickSort.sort(arr.clone(), m2);
            writer.write("quicksort", n, m2);

            Metrics m3 = new Metrics();
            int kth = n / 2;
            DeterministicSelect.select(arr.clone(), kth, m3);
            writer.write("select", n, m3);

            Point[] pts = new Point[n];
            for (int i = 0; i < n; i++) {
                pts[i] = new Point(rnd.nextDouble() * 1000, rnd.nextDouble() * 1000);
            }
            Metrics m4 = new Metrics();
            ClosestPair.findClosest(pts);

            writer.write("closest", n, m4);
        }
    }
}
