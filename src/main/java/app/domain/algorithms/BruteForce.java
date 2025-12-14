package app.domain.algorithms;

import app.interfaces.BruteForceAlg;

import java.util.Arrays;

public class BruteForce implements BruteForceAlg {

    public int[] determineContiguousSublistWithMaximumSum(int[] array) {
        long startTime = System.currentTimeMillis();
        int sum;
        int maxSum = 0;
        int optimalStart = 0;
        int optimalEnd = 0;
        for(int i = 0; i < array.length; i++) {
            sum = 0;
            for(int j = i; j < array.length; j++) {
                sum = sum + array[j];
                if(sum > maxSum) {
                    maxSum = sum;
                    optimalStart = i;
                    optimalEnd = j;
                }
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Brute-Force algorithm takes: " + (endTime - startTime) + "milliseconds");
        return Arrays.copyOfRange(array, optimalStart, optimalEnd + 1);
    }
}
