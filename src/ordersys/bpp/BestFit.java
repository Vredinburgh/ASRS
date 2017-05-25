package ordersys.bpp;

import java.util.ArrayList;

/**
 * Created by kasper on 2-5-17.
 */
public class BestFit {

    private ArrayList<ProductBPP> producten = new ArrayList<>();
    private ArrayList<Container> containers = new ArrayList<>();
    boolean newContainer = true;
    
    private int containerCounter = 1;

    public BestFit(ArrayList<ProductBPP> producten) {
        this.producten = producten;
    }

    public void vulContainers() {
        containers.add(new Container(containerCounter));

        for (ProductBPP product : producten) {
            if (product.getGrootte() <= checkSmallestSpace(containers).getFreeSpace() && !product.isPacked()) {
                checkSmallestSpace(containers).addProduct(product);
                product.setPacked(true);
                newContainer = false;
            } else {
                for (Container container : containers){
                    if (product.getGrootte() <= container.getFreeSpace() && !product.isPacked()){
                        container.addProduct(product);
                        product.setPacked(true);
                        newContainer = false;
                    } else {
                        newContainer = true;
                    }
                }
            }
            if (newContainer && !product.isPacked()) {
                containerCounter++;
                Container container = new Container(containerCounter);
                container.addProduct(product);
                product.setPacked(true);
                containers.add(container);
            }
        }
    }

    public Container checkSmallestSpace(ArrayList<Container> containers) {

        Container leastSpaceContainer = containers.get(0);

        for (Container container : containers) {
            if (container != null && leastSpaceContainer != null) {
                if (container.getFreeSpace() <= leastSpaceContainer.getFreeSpace()) {
                    leastSpaceContainer = container;
                }
            }
        }

        return leastSpaceContainer;
    }

    public ArrayList<Container> getContainers() {
        return containers;
    }
}