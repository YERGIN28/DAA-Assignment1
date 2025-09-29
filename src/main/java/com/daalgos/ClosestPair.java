package com.daalgos;


import java.util.*;


import com.daalgos.util.Metrics;
import java.util.Arrays;
import java.util.Comparator;

public class ClosestPair {
    private static Metrics metrics;

    public static double findClosest(Point[] points, Metrics m) {
        metrics = m;
        metrics.start();
        Point[] sortedX = points.clone();
        Point[] sortedY = points.clone();
        Arrays.sort(sortedX, Comparator.comparingDouble(p -> p.x));
        Arrays.sort(sortedY, Comparator.comparingDouble(p -> p.y));
        double result = closest(sortedX, sortedY);
        metrics.stop();
        return result;
    }

    private static double closest(Point[] Px, Point[] Py) {
        metrics.enterRecursion();
        int n = Px.length;
        if (n <= 3) {
            metrics.exitRecursion();
            return bruteForce(Px);
        }

        int mid = n / 2;
        Point midPoint = Px[mid];

        Point[] Qx = Arrays.copyOfRange(Px, 0, mid);
        Point[] Rx = Arrays.copyOfRange(Px, mid, n);

        Point[] Qy = new Point[mid];
        Point[] Ry = new Point[n - mid];
        int li = 0, ri = 0;
        for (Point p : Py) {
            if (p.x <= midPoint.x && li < mid) Qy[li++] = p;
            else Ry[ri++] = p;
        }

        double dl = closest(Qx, Qy);
        double dr = closest(Rx, Ry);
        double d = Math.min(dl, dr);

        double stripMin = stripClosest(Py, midPoint.x, d);

        metrics.exitRecursion();
        return Math.min(d, stripMin);
    }

    private static double bruteForce(Point[] pts) {
        double min = Double.POSITIVE_INFINITY;
        for (int i = 0; i < pts.length; i++) {
            for (int j = i + 1; j < pts.length; j++) {
                metrics.incComparisons();
                double dist = pts[i].distance(pts[j]);
                if (dist < min) min = dist;
            }
        }
        return min;
    }

    private static double stripClosest(Point[] Py, double midX, double d) {
        Point[] strip = Arrays.stream(Py)
                .filter(p -> Math.abs(p.x - midX) < d)
                .toArray(Point[]::new);

        double min = d;
        for (int i = 0; i < strip.length; i++) {
            for (int j = i + 1; j < strip.length && (strip[j].y - strip[i].y) < min; j++) {
                metrics.incComparisons();
                double dist = strip[i].distance(strip[j]);
                if (dist < min) min = dist;
            }
        }
        return min;
    }

    public static class Point {
        public final double x, y;
        public Point(double x, double y) { this.x = x; this.y = y; }
        public double distance(Point other) {
            return Math.hypot(x - other.x, y - other.y);
        }
    }
}

