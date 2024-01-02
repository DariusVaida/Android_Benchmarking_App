package com.benchmarkingapp;

public class CPUBenchmark {

    public long runTest() {
        long startTime = System.currentTimeMillis();


        calculatePrimes(5000);

        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }
    public long calculatePrimes(int maxNumber) {

        int primeCount = 0;
        for (int num = 2; num <= maxNumber; num++) {
            if (isPrime(num)) {
                primeCount++;
            }
        }
        return 1;
    }

    private boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        if (num <= 3) {
            return true;
        }
        if (num % 2 == 0 || num % 3 == 0) {
            return false;
        }
        int sqrtNum = (int) Math.sqrt(num);
        for (int i = 5; i <= sqrtNum; i += 6) {
            if (num % i == 0 || num % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }


    // Add other CPU benchmark methods if needed
}
