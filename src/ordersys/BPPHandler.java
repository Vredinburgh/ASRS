/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordersys;

import java.util.ArrayList;
import ordersys.bpp.BestFit;
import ordersys.bpp.Container;
import ordersys.bpp.ProductBPP;
import ordersys.gui.StatusOrderwrapSection;
import ordersys.xmlReader.Product;

/**
 *
 * @author gerri
 */
public class BPPHandler {

    private StatusOrderwrapSection bppSection;

    private ArrayList<ProductBPP> bppProducts;
    
    private TSPHandler tspHandler;
    
    public BestFit bestFit;

    public boolean startBpp;

    public BPPHandler(StatusOrderwrapSection bppSection, TSPHandler tspHandler) {
        this.bppSection = bppSection;
        
        bppProducts = new ArrayList<ProductBPP>();
        
        this.tspHandler = tspHandler;
    }

    public void startBpp() {
        startBpp = true;

        //Fill the bpp array with products
        for (int i = 0; i < tspHandler.shortestPath.length; i++) {
            int size = tspHandler.products.get(tspHandler.shortestPath[i]).getHeight();
            int id = tspHandler.products.get(tspHandler.shortestPath[i]).getProductNr();
            bppProducts.add(new ProductBPP(size, id));
        }

        //Execute the bestfit algorithm
        bestFit = new BestFit(bppProducts);
        bestFit.vulContainers();

        bppSection.repaint();
    }
}
