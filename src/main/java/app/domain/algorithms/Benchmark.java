package app.domain.algorithms;

import app.interfaces.BruteForceAlg;
// import com.isep.mdis.Sum; // External library not available

/**
 * Benchmark implementation - requires external Sum library
 * To use this class, uncomment the import and ensure Sum-1.0.0.jar is in dependencies folder
 */
public class Benchmark implements BruteForceAlg {

    public int[] determineContiguousSublistWithMaximumSum(int[] array) {
        throw new UnsupportedOperationException(
            "Benchmark algorithm requires the external Sum library (com.isep.mdis.Sum) which is not available. " +
            "Please use BruteForce algorithm instead by setting 'brutAlg = app.domain.algorithms.BruteForce' in config.properties"
        );
        // long startTime = System.currentTimeMillis();
        // int[] newArray = Sum.Max(array);
        // long endTime = System.currentTimeMillis();
        // System.out.println("Benchmark algorithm takes: " + (endTime - startTime) + "milliseconds");
        // return newArray;
    }
}
