package ordersys.xmlReader;

/**
 * Created by ian on 03/05/2017.
 */
public class Product {
    private String name, description;
    private int productNr;

    public Product(int productNr) {
        name = "kaas";
        description = "lekkere kaas";
        this.productNr = productNr;

        // hier in de database zoeken naar bijbehorende gegevens
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getProductNr() {
        return productNr;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", productNr=" + productNr +
                '}';
    }
}
