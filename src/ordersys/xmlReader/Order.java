package ordersys.xmlReader;

import java.util.ArrayList;

/**
 * Created by ian on 03/05/2017.
 */
public class Order {

    private ArrayList<Product> products = new ArrayList<>();

    public Order(int[] productNrs) {

            for(int i = 0; i < productNrs.length; i++) {
                products.add(new Product(i));
            }
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "Order{" +
                "products=" + products +
                '}';
    }
}
