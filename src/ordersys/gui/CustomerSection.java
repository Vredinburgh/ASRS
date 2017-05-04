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
    
    public CustomerSection() {
        setPreferredSize(new Dimension(500, 225));
        
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Klant informatie"));
        setLayout(new BoxLayout(this, 3));
        
        labelFirstname = new JLabel("Voornaam: Piet");
        labelFirstname.setBorder(BorderFactory.createEmptyBorder(35, 10, 0, 0));
        labelLastname = new JLabel("Achternaam: Jansen");
        labelLastname.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));
        labelAddress = new JLabel("Adres: Kalverstraat 17");
        labelAddress.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));
        labelZipcode = new JLabel("Postcode: 1012NX");
        labelZipcode.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));
        labelCity = new JLabel("Plaats: Amsterdam");
        labelCity.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));
        
        add(labelFirstname);
        add(labelLastname);
        add(labelAddress);
        add(labelZipcode);
        add(labelCity);
        
        setVisible(true);
    }
}
