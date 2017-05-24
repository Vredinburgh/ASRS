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

    private ArrayList<Product> orderProducts;
    private ArrayList<ProductBPP> bppProducts;

    public boolean startBpp;

    public BPPHandler(StatusOrderwrapSection bppSection, ArrayList<Product> orderProducts) {
        this.orderProducts = orderProducts;
        this.bppSection = bppSection;
        
        bppProducts = new ArrayList<ProductBPP>();
    }

    public void startBpp() {
        startBpp = true;

        //Fill the bpp array with products
        for (Product p : orderProducts) {
            bppProducts.add(new ProductBPP(p.getHeight())); 
        }

        //Execute the bestfit algorithm
        BestFit bestFit = new BestFit(bppProducts);
        bestFit.vulContainers();

        bppSection.repaint();
    }
}
