package ordersys.bpp;

import java.util.ArrayList;

/**
 * Created by Laptop Roeland on 1-5-2017.
 */
public class Container {

    private int totalSpace = 20;
    private int freeSpace = 20;
    private ArrayList<ProductBPP> producten = new ArrayList<>();

    public Container(ArrayList<ProductBPP> producten) {
        this.producten = producten;
    }

    public Container(ProductBPP product) {
        producten.add(product);
    }

    public Container() {
    }

    protected void addProduct(ProductBPP product) {
        if (product.getGrootte() <= freeSpace) {
            producten.add(product);
            freeSpace = freeSpace - product.getGrootte();
        }
    }

    public int getFreeSpace() {
        return freeSpace;
    }

    public ArrayList<ProductBPP> getProducten() {
        return producten;
    }

    @Override
    public String toString() {
        return "" + producten;
    }
}