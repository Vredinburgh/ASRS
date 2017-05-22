/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordersys;

import javax.swing.JOptionPane;
import jssc.SerialPortException;
import ordersys.com.PortFinder;
import ordersys.com.Transporter;
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

    public Transporter transporter;

    private CustomerSection customerSection;
    private OrderInformationSection orderSection;
    private StatusOrderpickSection tspSection;
    private StatusOrderwrapSection bppSection;

    private Invoice invoice;
    private TSPHandler tsp;
    private BPPHandler bpp;

    public Controller() {
        
    }

    public void startOrderpicking() {
        try {
            int path[] = tsp.shortestPath;
            int productCounter = 0;
            String transporterPort = PortFinder.portNames[0];

            transporter = new Transporter(transporterPort);
            for (int i = 0; i < path.length; i++) {
                String x = String.valueOf(tsp.products.get(path[i]).getX());
                String y = String.valueOf(tsp.products.get(path[i]).getY());

                transporter.command(x + y);

                while (transporter.getMessage() == null) {
                    System.out.print("");
                }

                //Update the product, set red
                tspSection.updateProduct(tsp.products.get(path[i]));

                //Check if three products are loaded, if so return to unloading
                //place.
                if (productCounter > 1) {
                    System.out.println("Lossen terug naar het lospunt");
                    //Set loading text at the orderpick section
                    unloadProducts();
                    System.out.println("Klaar met lossen");
                    productCounter = 0;
                } else {
                    productCounter++;
                }

                //If the loaded products is the last..
                if (i == path.length - 1) {
                    System.out.println("Laatste product gehad");
                    unloadProducts();
                    System.out.println("Klaar met lossen, dus helemaal klaar!");
                }
            }
        } catch (SerialPortException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Er zijn geen poorten beschikbaar");
        } catch (NullPointerException ex) {
            System.out.println("Je moet eerst een XML bestand inladen");
        }
    }

    private void unloadProducts() {
        //Hier een dialog
        transporter.command("00");
        while (transporter.getMessage() == null) {
            System.out.print("");
        }
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
        tsp = new TSPHandler(tspSection, invoice.getOrder().getProducts());
        tspSection.setTspHandler(tsp);
        tsp.startTsp();

        //Give signal to start the TSP and BPP
        bpp = new BPPHandler(bppSection);
        bppSection.setBppHandler(bpp);
        bpp.startBpp();
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
