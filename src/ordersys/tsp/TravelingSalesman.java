/* 
 * Created on Dec 17, 2005
 *
 * This source code is released under the GNU Lesser General Public License Version 3, 29 June 2007
 * see http://www.gnu.org/licenses/lgpl.html or the plain text version of the LGPL included with this project
 *
 * It comes with no warranty whatsoever
 */
package ordersys.tsp;

import ordersys.tsp.Helper;

/**
 * This class encodes a traveling salesman problem in the form of n integer
 * coordinates.
 *
 * @author Bjoern Guenzel - http://blog.blinker.net
 */
public class TravelingSalesman {

    private double[] x;
    private double[] y;

    private double[][] costs;

    public int n;

    boolean[] visited;

    public TravelingSalesman(double[] x, double[] y) {
        this(x, y, x.length);
    }

    public TravelingSalesman(double[] x, double[] y, int n) {
        this.n = n;

        visited = new boolean[n];
        costs = new double[n][n];

        this.x = x;
        this.y = y;

        initCostsByCoordinates();
    }

    /*	
	 * create costs matrix by creating coordinates for cities and using flight distance as cost
	 * however, the algorithm is more general than that, arbitrary cost matrices should work, even unsymmetric ones
     */
    private void initCostsByCoordinates() {
        costs = Helper.buildWeightMatrix(x, y);
    }

    protected double calculateCosts(int[] route) {
        double length = costs[route[route.length - 1]][route[0]];
        for (int i = 0; i < route.length - 1; i++) {
            length += costs[route[i]][route[i + 1]];
        }
        return length;
    }
}
