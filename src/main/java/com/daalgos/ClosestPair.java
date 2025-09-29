package com.daalgos;

import java.util.Arrays;
import java.util.Comparator;

public class ClosestPair {

    public static double findClosest(Point[] points) {
        Point[] sortedByX = points.clone();
        Arrays.sort(sortedByX, Comparator.comparingDouble(p -> p.x));
        return closestUtil(sortedByX, 0, sortedByX.length - 1);
    }

    private static double closestUtil(Point[] points, int left, int right) {
        if (right - left <= 3) {
            return bruteForce(Arrays.copyOfRange(points, left, right + 1));
        }

        int mid = (left + right) / 2;
        double dLeft = closestUtil(points, left, mid);
        double dRight = closestUtil(points, mid + 1, right);
        double d = Math.min(dLeft, dRight);

        double midX = points[mid].x;
        Point[] strip = Arrays.stream(points, left, right + 1)
                .filter(p -> Math.abs(p.x - midX) < d)
                .toArray(Point[]::new);

        Arrays.sort(strip, Comparator.comparingDouble(p -> p.y));

        return Math.min(d, stripClosest(strip, d));
    }

    private static double stripClosest(Point[] strip, double d) {
        double min = d;
        for (int i = 0; i < strip.length; i++) {
            for (int j = i + 1; j < strip.length && (strip[j].y - strip[i].y) < min; j++) {
                double dist = Point.distance(strip[i], strip[j]);
                if (dist < min) {
                    min = dist;
                }
            }
        }
        return min;
    }

    public static double bruteForce(Point[] points) {
        double minDist = Double.POSITIVE_INFINITY;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                double d = Point.distance(points[i], points[j]);
                if (d < minDist) {
                    minDist = d;
                }
            }
        }
        return minDist;
    }
}
