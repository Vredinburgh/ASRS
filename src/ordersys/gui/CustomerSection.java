/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordersys.gui;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import ordersys.Controller;
import ordersys.xmlReader.Invoice;

/**
 *
 * @author gerri
 */
public class CustomerSection extends JPanel {
    
    private JLabel labelFirstname;
    private JLabel labelLastname;
    private JLabel labelAddress;
    private JLabel labelZipcode;
    private JLabel labelCity;
    
    private Controller controller;
    
    public CustomerSection(Controller controller) {
        setPreferredSize(new Dimension(500, 225));
        
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Klant informatie"));
        setLayout(new BoxLayout(this, 3));
        
        this.controller = controller;
        
        labelFirstname = new JLabel("Voornaam: -");
        labelFirstname.setBorder(BorderFactory.createEmptyBorder(35, 10, 0, 0));
        labelLastname = new JLabel("Achternaam: -");
        labelLastname.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));
        labelAddress = new JLabel("Adres: -");
        labelAddress.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));
        labelZipcode = new JLabel("Postcode: -");
        labelZipcode.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));
        labelCity = new JLabel("Plaats: -");
        labelCity.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));
        
        add(labelFirstname);
        add(labelLastname);
        add(labelAddress);
        add(labelZipcode);
        add(labelCity);
        
        setVisible(true);
    }
    
    public void updateInformation(Invoice invoice) {
        labelFirstname.setText("Voornaam: " + invoice.getCustomer().getFirstName());
        labelLastname.setText("Achternaam: " + invoice.getCustomer().getLastName());
        labelAddress.setText("Adres: " + invoice.getCustomer().getAddress());
        labelZipcode.setText("Postcode: " + invoice.getCustomer().getZipCode());
        labelCity.setText("Plaats: " + invoice.getCustomer().getCity());
    }
}
