package com.benchmarkingapp;

public class MemoryBenchmark {

    public long runTest() {
        return allocateMemory(50,5);
    }

    public long allocateMemory(int blockSizeInBytes, int numBlocks) {
        long startTime = System.nanoTime();

        // Allocate memory blocks
        byte[][] memoryBlocks = new byte[numBlocks][];
        for (int i = 0; i < numBlocks; i++) {
            memoryBlocks[i] = new byte[blockSizeInBytes];
        }

        // Deallocate memory blocks
        for (int i = 0; i < numBlocks; i++) {
            memoryBlocks[i] = null;
        }

        long endTime = System.nanoTime();
        return endTime - startTime;
    }
    // Add other memory benchmark methods if needed
}

