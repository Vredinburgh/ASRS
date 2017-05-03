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

/**
 *
 * @author gerri
 */
public class StatusOrderwrapSection extends JPanel {
    public StatusOrderwrapSection() {
        setPreferredSize(new Dimension(395, 405));
        
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Status inpakrobot"));
        
        setVisible(true);
    }
}
