/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordersys;

import java.util.ArrayList;
import ordersys.gui.StatusOrderpickSection;
import ordersys.tsp.TravelingSalesman;
import ordersys.tsp.TravelingSalesmanBruteForce;
import ordersys.xmlReader.Product;

/**
 *
 * @author gerri
 */
public class TSPHandler {

    private StatusOrderpickSection tspSection;

    public ArrayList<Product> products;

    public int[] shortestPath;

    public boolean startTsp;

    public TSPHandler(StatusOrderpickSection tspSection, ArrayList<Product> products) {
        this.tspSection = tspSection;
        this.products = products;
        shortestPath = new int[products.size()];
    }

    public void startTsp() {
        startTsp = true;

        tspSection.repaint();
    }

    public int[] returnShortestPath() {
        //Generate an x and y array for the product locations
        double[] x = new double[products.size()];
        double[] y = new double[products.size()];

        //Fill the x and y array with the x and y of the products
        for (int i = 0; i < products.size(); i++) {
            x[i] = products.get(i).getX();
            y[i] = products.get(i).getY();
        }

        //Create the algorithm object
        TravelingSalesman salesman = new TravelingSalesman(x, y);
        TravelingSalesmanBruteForce bruteForce = new TravelingSalesmanBruteForce(salesman);

        //Returns the shortest path calculated by the Antcolony algorithm
        return bruteForce.run();
    }
}
