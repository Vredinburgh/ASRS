/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordersys.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author gerri
 */
public class OrderReadSection extends JPanel implements ActionListener {
    
    private JButton readButton;
    
    public OrderReadSection() {
        setPreferredSize(new Dimension(500, 175));
        
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Order inlezen"));
        
        readButton = new JButton("Selecteer XML bestand");
        readButton.setPreferredSize(new Dimension(450, 135));
        readButton.addActionListener(this);
        
        add(readButton);
        
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == readButton) {
            System.out.println("XML bestand ingelezen :D");
        }
    }
}
