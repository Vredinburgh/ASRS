package ordersys.xmlReader;

/**
 * Created by ian on 03/05/2017.
 */
public class Invoice {

    private Customer customer;
    private Order order;
    private ProcessOrder processOrder;

    public Invoice(String pathname) {
        processOrder = new ProcessOrder(pathname);
        this.customer = new Customer(processOrder.getDetails());
        //this.order = new Order(processOrder.getProducts());
    }

    public Customer getCustomer() {
        return customer;
    }

    public Order getOrder() {
        return order;
    }

    @Override
    public String toString() {
        return "Invoice{"
                + "customer=" + customer
                + ", order=" + order
                + '}';
    }
}
