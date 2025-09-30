package com.daalgos;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

public class ClosestPairTest {

    @Test
    void testSmallSet() {
        Point[] points = {
                new Point(0, 0),
                new Point(3, 4),
                new Point(7, 7),
                new Point(1, 1)
        };
        double result = ClosestPair.findClosest(points);
        assertEquals(Math.sqrt(2), result, 1e-9);
    }

    @Test
    void testTwoPoints() {
        Point[] points = {
                new Point(0, 0),
                new Point(5, 12)
        };
        double result = ClosestPair.findClosest(points);
        assertEquals(13.0, result, 1e-9);
    }

    @Test
    void testRandomPointsSmallN() {
        Random rnd = new Random();
        Point[] points = new Point[100];
        for (int i = 0; i < 100; i++) {
            points[i] = new Point(rnd.nextDouble(), rnd.nextDouble());
        }


        double expected = bruteForce(points);
        double actual = ClosestPair.bruteForce(points);

        assertEquals(expected, actual, 1e-9);
    }

    private double bruteForce(Point[] points) {
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
