/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordersys.gui;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import ordersys.Controller;

/**
 *
 * @author gerri
 */
public class OrderInformationSection extends JPanel {

    private JTable tableOrders;
    
    private Controller controller;

    public OrderInformationSection(Controller controller) {
        setPreferredSize(new Dimension(800, 175));

        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Order informatie"));

        this.controller = controller;
        
        //Column names
        String[] columns = new String[]{
            "Product id", "Naam", "Prijs", "Grootte"
        };

        //actual data for the table in a 2d array
        Object[][] data = new Object[][]{
            {1, "Peer", 1.35, 12.0},
            {2, "Ananas", 3.25, 30.0},
            {3, "Mandarijn", 0.75, 5.0}
        };
        
        //create table with data
        JTable tableOrders = new JTable(data, columns);
        tableOrders.setFocusable(false);
        tableOrders.setRowSelectionAllowed(false);
                
        //Create a frame holder
        JScrollPane tableHolder = new JScrollPane(tableOrders);
        tableHolder.setPreferredSize(new Dimension(750, 140));

        //add the table to the frame
        add(tableHolder);

        setVisible(true);
    }
}
