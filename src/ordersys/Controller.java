/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordersys;

import java.awt.Desktop;
import java.io.File;
import java.util.concurrent.TimeUnit;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import jssc.SerialPortException;
import ordersys.bpp.Container;
import ordersys.bpp.ProductBPP;
import ordersys.com.PortFinder;
import ordersys.com.Sorter;
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
    public Sorter sorter;

    private CustomerSection customerSection;
    private OrderInformationSection orderSection;
    private StatusOrderpickSection tspSection;
    private StatusOrderwrapSection bppSection;

    private Invoice invoice;
    private TSPHandler tsp;
    private BPPHandler bpp;

    private JDialog messageDialog;

    private int totalProductCounter = 0;

    private int timesBinsFull = 1;

    private int sizeProductsBin1 = 0;
    private int sizeProductsBin2 = 0;

    private int amountProductsBin1 = 0;
    private int amountProductsBin2 = 0;

    private int sizePreviousProductBin1 = 1;
    private int sizePreviousProductBin2 = 1;

    private boolean lastProduct = false;

    public Controller() {
        //Initiate the message dialog
        messageDialog = new JDialog();
    }

    public void startOrderpicking() {
        try {
            //Load the shortest path and set the productCounter to 0
            int path[] = tsp.shortestPath;
            int productCounter = 0;

            //Get the COM port for the Arduino
            String transporterPort = PortFinder.portNames[0];

            //Initiate the transporter with the COM port
            transporter = new Transporter(transporterPort);

            //Execute foreach product
            for (int i = 0; i < path.length; i++) {
                totalProductCounter++;

                //Check if the bins are full
                checkIfBinsFull();

                //Get the x, y and bin variables of the product
                String x = String.valueOf(tsp.products.get(path[i]).getX());
                String y = String.valueOf(tsp.products.get(path[i]).getY());
                String bin = String.valueOf(returnBinNumber(totalProductCounter));

                //Send the execute command to the transporter
                transporter.command(x + y + bin);

                //While message is null
                while (transporter.getMessage() == null) {
                    System.out.print("");
                }

                //Update the product, set red
                tspSection.updateProduct(tsp.products.get(path[i]));

                //If the loaded products is the last
                if (i == path.length - 1) {
                    //Check if one or two products are loaded
                    if ((path.length % 2) == 0) {
                        //Unload the products
                        unloadProducts();
                        
                        //Done unloading, show receipt
                        doneUnloading();
                    } else {
                        //Set lastproduct to true
                        lastProduct = true;
                        
                        //Unload the products
                        unloadProducts();
                        
                        //Set lastproduct to false
                        lastProduct = false;
                        
                        //Done unloading, show receipt
                        doneUnloading();
                    }
                    productCounter = 0;
                } else if (productCounter > 0) {
                    //Unload products if two products are loaded
                    unloadProducts();
                    
                    //Set productcounter to zero
                    productCounter = 0;
                } else {
                    //If a product is loaded, +1 to productcounter
                    productCounter++;
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
        try {
            //Create message pane
            final JOptionPane messagePane = new JOptionPane();

            //Bind the message bind to the dialog object
            messageDialog = messagePane.createDialog("Lossen.. Even geduld..");
            messageDialog.setModal(false);

            //Set the dialog to visible
            messageDialog.setVisible(true);

            //Send unload command
            transporter.command("00");

            //While message is nuull
            while (transporter.getMessage() == null) {
                System.out.print("");
            }

            //If the product is the last product, add one item to the bin
            //otherwise add two items to the bin
            if (lastProduct) {
                unloadToBin(totalProductCounter);
            } else {
                unloadToBin(totalProductCounter - 1);
                unloadToBin(totalProductCounter);
            }

            //If unloading done, close the message
            messageDialog.dispose();
        } catch (Exception ex) {
            System.out.println("Fout opgetreden: " + ex);
        }
    }

    private void unloadToBin(int productId) {
        //Get the container id and the size of a product
        //and update the panel
        containerLoop:
        for (Container c : bpp.bestFit.getContainers()) {
            if (c.getId() == 1) {
                for (ProductBPP product : c.getProducten()) {
                    if (product.getId() == productId) {
                        bppSection.updateContainer(product, sizePreviousProductBin1, c.getId(), amountProductsBin1);
                        sizePreviousProductBin1 = product.getGrootte();
                        sizeProductsBin1 += product.getGrootte();
                        amountProductsBin1++;
                        break containerLoop;
                    }
                }
            } else if (c.getId() == 2) {
                for (ProductBPP product : c.getProducten()) {
                    if (product.getId() == productId) {
                        bppSection.updateContainer(product, sizePreviousProductBin2, c.getId(), amountProductsBin2);
                        sizePreviousProductBin2 = product.getGrootte();
                        sizeProductsBin2 += product.getGrootte();
                        amountProductsBin2++;
                        break containerLoop;
                    }
                }
            }
        }
    }

    //Function to check if the bins are full, if a bin is full replace it with
    //a new one.
    private void checkIfBinsFull() {
        if (sizeProductsBin1 == 20 && sizeProductsBin2 == 20) {
            boolean drawBin2 = false;

            int dialogResult = JOptionPane.showConfirmDialog(null, "Dozen zijn vol, verwissel de dozen.", "Dozen vol", JOptionPane.DEFAULT_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION) {

                for (int i = 0; i < 2 * timesBinsFull; i++) {
                    bpp.bestFit.getContainers().get(i).setId(-1);
                }

                bpp.bestFit.getContainers().get((timesBinsFull * 2)).setId(1);
                try {
                    bpp.bestFit.getContainers().get((timesBinsFull * 2) + 1).setId(2);
                    drawBin2 = true;
                } catch (Exception ex) {
                    System.out.println("Geen 2e doos nodig");
                }

                amountProductsBin1 = 0;
                amountProductsBin2 = 0;
                sizePreviousProductBin1 = 1;
                sizePreviousProductBin2 = 1;
                sizeProductsBin1 = 0;
                sizeProductsBin2 = 0;

                bppSection.resetProducts((timesBinsFull * 2) + 1, (timesBinsFull * 2) + 2, drawBin2);
            }
            timesBinsFull++;
        }
    }

    //Function to return the bin number of a specified product
    private int returnBinNumber(int productId) {
        int binNumber = 0;
        containerLoop:
        for (Container c : bpp.bestFit.getContainers()) {
            if (c.getId() == 1) {
                for (ProductBPP product : c.getProducten()) {
                    if (product.getId() == productId) {
                        binNumber = 1;
                        break containerLoop;
                    }
                }
            } else if (c.getId() == 2) {
                for (ProductBPP product : c.getProducten()) {
                    if (product.getId() == productId) {
                        binNumber = 2;
                        break containerLoop;
                    }
                }
            }
        }
        return binNumber;
    }
    
    //Function to show an unloading screen and to generate a receipt
    private void doneUnloading() {
        //Done unloading, ask the user if he wants to generate a receipt
        int dialogResult = JOptionPane.showConfirmDialog(null, "Klaar. Wilt u de pakbon openen?", "Pakbon", JOptionPane.INFORMATION_MESSAGE);
        if (dialogResult == JOptionPane.YES_OPTION) {
            //Generate the receipt
            String receiptName = "receipt.pdf";
            String customerName = getInvoiceData().getCustomer().getFirstName() + " " + getInvoiceData().getCustomer().getLastName();

            Receipt receipt = new Receipt(bpp.bestFit, getInvoiceData().getOrder().getProducts(), customerName);
            receipt.createPDF(receiptName);

            //Open the receipt
            try {
                Desktop desktop = Desktop.getDesktop();
                File receiptFile = new File(receiptName);
                desktop.open(receiptFile);
            } catch (Exception ex) {
                System.out.println("Kan de pakbon helaas niet openen");
            }
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
        bpp = new BPPHandler(bppSection, tsp);
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
