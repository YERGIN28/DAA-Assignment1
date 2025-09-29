package com.daalgos.util;



import java.io.FileWriter;
import java.io.IOException;

public class CsvWriter {
    private final String file;

    public CsvWriter(String file) {
        this.file = file;
    }

    public void write(String algorithm, int n, Metrics m) throws IOException {
        try (FileWriter fw = new FileWriter(file, true)) {
            fw.write(String.format("%s,%d,%d,%d,%d,%d\n",
                    algorithm,
                    n,
                    m.getElapsedTime(),
                    m.getMaxRecursionDepth(),
                    m.getComparisons(),
                    m.getAllocations()));
        }
    }
}
