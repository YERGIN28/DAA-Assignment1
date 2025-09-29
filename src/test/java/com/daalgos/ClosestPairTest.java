package com.daalgos;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

public class ClosestPairTest {

    @Test
    void testSmallSet() {
        ClosestPair.Point[] points = {
                new ClosestPair.Point(0, 0),
                new ClosestPair.Point(3, 4),
                new ClosestPair.Point(7, 7),
                new ClosestPair.Point(1, 1)
        };
        double result = ClosestPair.closestPair(points);
        assertEquals(Math.sqrt(2), result, 1e-9);
    }

    @Test
    void testTwoPoints() {
        ClosestPair.Point[] points = {
                new ClosestPair.Point(0, 0),
                new ClosestPair.Point(5, 12)
        };
        double result = ClosestPair.closestPair(points);
        assertEquals(13.0, result, 1e-9);
    }

    @Test
    void testRandomPointsSmallN() {
        Random rnd = new Random();
        ClosestPair.Point[] points = new ClosestPair.Point[100];
        for (int i = 0; i < 100; i++) {
            points[i] = new ClosestPair.Point(rnd.nextDouble(), rnd.nextDouble());
        }

        // сравнение с O(n^2) брутфорсом
        double expected = bruteForce(points);
        double actual = ClosestPair.closestPair(points);

        assertEquals(expected, actual, 1e-9);
    }

    private double bruteForce(ClosestPair.Point[] points) {
        double min = Double.MAX_VALUE;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                double dx = points[i].x - points[j].x;
                double dy = points[i].y - points[j].y;
                min = Math.min(min, Math.sqrt(dx * dx + dy * dy));
            }
        }
        return min;
    }
}
