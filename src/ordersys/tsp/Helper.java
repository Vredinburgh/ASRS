/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordersys.tsp;

/**
 *
 * @author gerri
 */
public class Helper {
    //Function to calculate the distance between two points
    public static double calculateDistance(int x1, int x2, int y1, int y2) {
        double xDiff = x1 - x2;
        double yDiff = x1 - x2;
        double distance = Math.sqrt(Math.pow(xDiff, 2.0) + Math.pow(yDiff, 2.0));
        return distance;
    }

    /**
     * Builds up the weightmatrix from the coordinates. Calculates distance
     * between all pairs.
     *
     * @param x	X-coordinates
     * @param y	Y-coordinates
     * @return Two-dimensional weightmatrix.
     * @since 1.0
     */
    public static double[][] buildWeightMatrix(double[] x, double[] y) {
        int dim = x.length;
        double[][] wt = new double[dim][dim];
        double dist;

        for (int u1 = 0; u1 < dim; u1++) {
            for (int u2 = 0; u2 < dim; u2++) {
                if (u1 == u2) {
                    wt[u1][u2] = 0.0;
                    wt[u2][u1] = 0.0;
                    continue;
                }
                dist = Math.sqrt(
                        Math.pow((x[u1] - x[u2]), 2.0)
                        + Math.pow((y[u1] - y[u2]), 2.0)
                );
                wt[u1][u2] = dist;
                wt[u2][u1] = dist;
            }
        }

        return wt;
    }
}
