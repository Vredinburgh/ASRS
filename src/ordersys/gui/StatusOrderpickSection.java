/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordersys.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author gerri
 */
public class StatusOrderpickSection extends JPanel {
    public StatusOrderpickSection() {
        setPreferredSize(new Dimension(395, 405));
        
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Status ophaalrobot"));
        
        setVisible(true);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.green);
        g.fillRect(315, 325, 50, 50);
        g.setColor(Color.red);
        g.fillRect(30, 325, 50, 50);
        g.fillRect(167, 180, 50, 50);
        
        g.setColor(Color.black);
        g.drawLine(80, 325, 167, 230);
        g.drawLine(217, 230, 315, 325);
        
        g.fillOval(217, 230, 25, 25);
        
    }
}
