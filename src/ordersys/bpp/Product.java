package ordersys.bpp;

/**
 * Created by Laptop Roeland on 1-5-2017.
 */
public class Product {

    private boolean packed;
    private int grootte;
    private int personalNumber;
    private static int nummer = 0;

    public Product(int grootte) {
        this.grootte = grootte;
        personalNumber = nummer;
        nummer++;
    }

    public boolean isPacked() {
        return packed;
    }

    public void setPacked(boolean packed) {
        this.packed = packed;
    }

    public int getGrootte() {
        return grootte;
    }

    @Override
    public String toString() {
        return "[Product" + personalNumber + " Size= " + grootte + " Packed= " + isPacked() + "]";
    }
}