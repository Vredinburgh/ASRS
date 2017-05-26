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

    private int amountProductsBin1 = 0;
    private int amountProductsBin2 = 0;

    private int sizePreviousProductBin1 = 1;
    private int sizePreviousProductBin2 = 1;

    private boolean lastProduct = false;

    public Controller() {
        messageDialog = new JDialog();
    }

    public void startOrderpicking() {
        /*if (testp <= 5) {
            unloadToBin(testp);
            testp++;
        }*/

        try {
            int path[] = tsp.shortestPath;
            int productCounter = 0;

            String transporterPort = PortFinder.portNames[0];

            transporter = new Transporter(transporterPort);

            for (int i = 0; i < path.length; i++) {
                totalProductCounter++;

                String x = String.valueOf(tsp.products.get(path[i]).getX());
                String y = String.valueOf(tsp.products.get(path[i]).getY());
                String bin = String.valueOf(returnBinNumber(totalProductCounter));

                transporter.command(x + y + bin);

                while (transporter.getMessage() == null) {
                    System.out.print("");
                }

                //Update the product, set red
                tspSection.updateProduct(tsp.products.get(path[i]));

                //Check if three products are loaded, if so return to unloading
                //place.
                if (productCounter > 0) {
                    //System.out.println("Lossen terug naar het lospunt");
                    //Set loading text at the orderpick section
                    unloadProducts();
                    productCounter = 0;
                } else {
                    productCounter++;
                }

                //If the loaded products is the last..
                if (i == path.length - 1) {
                    lastProduct = true;
                    unloadProducts();
                    doneUnloading();
                    lastProduct = false;
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

            while (transporter.getMessage() == null) {
                System.out.print("");
            }
            
            if(lastProduct) {
                unloadToBin(totalProductCounter);
            } else {
                unloadToBin(totalProductCounter - 1);
                unloadToBin(totalProductCounter);
            }

            //If unloading done, close the message
            messageDialog.dispose();
        } catch (Exception ex) {
            System.out.println("Fout opgetreden");
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
                        amountProductsBin1++;
                        break containerLoop;
                    }
                }
            } else if (c.getId() == 2) {
                for (ProductBPP product : c.getProducten()) {
                    if (product.getId() == productId) {
                        bppSection.updateContainer(product, sizePreviousProductBin2, c.getId(), amountProductsBin2);
                        sizePreviousProductBin2 = product.getGrootte();
                        amountProductsBin2++;
                        break containerLoop;
                    }
                }
            } else if (c.getId() > 2) {
                //Bins full, ask to replace bins
                int dialogResult = JOptionPane.showConfirmDialog(null, "Dozen zijn vol, verwissel de dozen.", "Dozen vol", JOptionPane.YES_NO_OPTION);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    bpp.bestFit.getContainers().remove(0);
                    bpp.bestFit.getContainers().remove(1);
                    bpp.bestFit.getContainers().get(2).setId(1);

                    amountProductsBin1 = 0;
                    amountProductsBin2 = 0;
                    sizePreviousProductBin1 = 1;
                    sizePreviousProductBin2 = 1;

                    bppSection.repaint();
                    unloadToBin(productId);
                }
            }
        }
    }

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

    private void doneUnloading() {
        //Done unloading, ask the user if he wants to generate a receipt
        int dialogResult = JOptionPane.showConfirmDialog(null, "Klaar. Wilt u de pakbon openen?", "Pakbon", JOptionPane.YES_NO_OPTION);
        if (dialogResult == JOptionPane.YES_OPTION) {
            //Generate the receipt
            String receiptName = "receipt.pdf";
            Receipt receipt = new Receipt(getInvoiceData().getOrder().getProducts());
            receipt.createPDF(receiptName);

            //Open the receipt
            try {
                Desktop desktop = Desktop.getDesktop();
                File receiptFile = new File("C:/Users/gerri/Desktop/" + receiptName);
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
