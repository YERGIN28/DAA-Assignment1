package com.daalgos;


import java.util.*;

public class ClosestPair {

    public static class Point {
        public final double x, y;
        public Point(double x, double y) { this.x = x; this.y = y; }
    }

    public static double closestPair(Point[] points) {
        Point[] sortedByX = points.clone();
        Arrays.sort(sortedByX, Comparator.comparingDouble(p -> p.x));

        Point[] sortedByY = points.clone();
        Arrays.sort(sortedByY, Comparator.comparingDouble(p -> p.y));

        return closestRecursive(sortedByX, sortedByY);
    }

    private static double closestRecursive(Point[] px, Point[] py) {
        int n = px.length;
        if (n <= 3) return bruteForce(px);

        int mid = n / 2;
        Point midPoint = px[mid];

        Point[] Qx = Arrays.copyOfRange(px, 0, mid);
        Point[] Rx = Arrays.copyOfRange(px, mid, n);

        List<Point> QyList = new ArrayList<>();
        List<Point> RyList = new ArrayList<>();
        for (Point p : py) {
            if (p.x <= midPoint.x) QyList.add(p);
            else RyList.add(p);
        }

        Point[] Qy = QyList.toArray(new Point[0]);
        Point[] Ry = RyList.toArray(new Point[0]);

        double dLeft = closestRecursive(Qx, Qy);
        double dRight = closestRecursive(Rx, Ry);

        double d = Math.min(dLeft, dRight);

        return Math.min(d, stripClosest(py, midPoint.x, d));
    }

    private static double stripClosest(Point[] py, double midX, double d) {
        List<Point> strip = new ArrayList<>();
        for (Point p : py) {
            if (Math.abs(p.x - midX) < d) strip.add(p);
        }

        double min = d;
        for (int i = 0; i < strip.size(); i++) {
            for (int j = i + 1; j < strip.size() && (strip.get(j).y - strip.get(i).y) < min; j++) {
                min = Math.min(min, dist(strip.get(i), strip.get(j)));
            }
        }
        return min;
    }

    private static double bruteForce(Point[] points) {
        double min = Double.MAX_VALUE;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                min = Math.min(min, dist(points[i], points[j]));
            }
        }
        return min;
    }

    private static double dist(Point a, Point b) {
        double dx = a.x - b.x, dy = a.y - b.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}
