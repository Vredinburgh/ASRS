/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordersys.gui;

import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author gerri
 */
public class LeftGuiPanel extends JPanel {
    public LeftGuiPanel() {
        setPreferredSize(new Dimension(500, 600));
        
        add(new OrderReadSection());
        add(new StartExecuteSection());
        add(new CustomerSection());
        
        setVisible(true);
    }
}
