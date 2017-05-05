/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordersys;

import ordersys.gui.CustomerSection;
import ordersys.gui.OrderInformationSection;
import ordersys.gui.StatusOrderpickSection;
import ordersys.gui.StatusOrderwrapSection;
import ordersys.xmlReader.Invoice;

/**
 *
 * @author gerri
 */
public class Controller {

    private CustomerSection customerSection;
    private OrderInformationSection orderSection;
    private StatusOrderpickSection tspSection;
    private StatusOrderwrapSection bppSection;

    private Invoice invoice;

    public boolean startTsp;
    public boolean startBpp;

    public Controller() {

    }

    public Invoice getInvoiceData() {
        return invoice;
    }

    public void setInvoiceData(String invoicePath) {
        //Initiate invoice
        invoice = new Invoice(invoicePath);

        //Update the customer section
        customerSection.updateInformation(invoice);

        //Update the order section
        orderSection.updateOrderDetails();

        //Give signal to start the TSP and BPP
        startTsp = true;
        startBpp = true;

        //Repaint the TSP and BPP panel
        tspSection.repaint();
        bppSection.repaint();
    }

    public void setCustomerSection(CustomerSection customerSection) {
        this.customerSection = customerSection;
    }

    public void setOrderSection(OrderInformationSection orderSection) {
        this.orderSection = orderSection;
    }

    public void setTspSection(StatusOrderpickSection tspSection) {
        this.tspSection = tspSection;
    }

    public void setBppSection(StatusOrderwrapSection bppSection) {
        this.bppSection = bppSection;
    }
}
