package com.daalgos.util;


public class Metrics {
    private long startTime;
    private long elapsedTime;
    private int recursionDepth;
    private int maxRecursionDepth;
    private long comparisons;
    private long allocations;

    public void start() {
        reset();
        startTime = System.nanoTime();
    }

    public void stop() {
        elapsedTime = System.nanoTime() - startTime;
    }

    public void reset() {
        elapsedTime = 0;
        recursionDepth = 0;
        maxRecursionDepth = 0;
        comparisons = 0;
        allocations = 0;
    }

    public void enterRecursion() {
        recursionDepth++;
        maxRecursionDepth = Math.max(maxRecursionDepth, recursionDepth);
    }

    public void exitRecursion() {
        recursionDepth--;
    }

    public void incComparisons() {
        comparisons++;
    }

    public void incAllocations() {
        allocations++;
    }

    // Getters
    public long getElapsedTime() { return elapsedTime; }
    public int getMaxRecursionDepth() { return maxRecursionDepth; }
    public long getComparisons() { return comparisons; }
    public long getAllocations() { return allocations; }
}
