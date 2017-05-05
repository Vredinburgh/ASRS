/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordersys;

import java.util.ArrayList;
import ordersys.gui.StatusOrderpickSection;
import ordersys.xmlReader.Product;

/**
 *
 * @author gerri
 */
public class TSPHandler {
    
    private StatusOrderpickSection tspSection;
    private ArrayList<Product> products;
    
    public boolean startTsp;
    
    public TSPHandler(StatusOrderpickSection tspSection, ArrayList<Product> products) {
        this.tspSection = tspSection;
        this.products = products;
    }
    
    public void startTsp() {
        startTsp = true;
        tspSection.repaint();
    }
}
