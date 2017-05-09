/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordersys.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import ordersys.Controller;
import ordersys.xmlReader.Product;

/**
 *
 * @author gerri
 */
public class OrderInformationSection extends JPanel {

    private JTable tableOrders;
    private DefaultTableModel tableModel;

    private Controller controller;
    private ArrayList<Product> products;

    public OrderInformationSection(Controller controller) {
        setPreferredSize(new Dimension(800, 175));

        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Order informatie"));

        this.controller = controller;
        
        //Set table model and set editable to false
        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        //Set column names
        tableModel.addColumn("Product id");
        tableModel.addColumn("Naam");
        tableModel.addColumn("Prijs");
        tableModel.addColumn("Grootte");
        tableModel.addColumn("X");
        tableModel.addColumn("Y");
        tableModel.addColumn("Voorraad");

        //Create table with data
        tableOrders = new JTable(tableModel);
        tableOrders.setFocusable(false);
        tableOrders.setRowSelectionAllowed(false);

        //Create a frame holder
        JScrollPane tableHolder = new JScrollPane(tableOrders);
        tableHolder.setPreferredSize(new Dimension(750, 140));

        //add the table to the frame
        add(tableHolder);

        setVisible(true);
    }

    public void updateOrderDetails() {
        //First clear the table
        tableModel.setRowCount(0);
        
        //Bind the product arraylist
        products = controller.getInvoiceData().getOrder().getProducts();
        
        //Add a row to the table for each product
        for(Product p : products) {
            tableModel.addRow(new Object[]{p.getProductNr(), p.getName(), p.getPrice(), p.getHeight(), p.getX(), p.getY(), p.getStock()});
        }
    }
}
