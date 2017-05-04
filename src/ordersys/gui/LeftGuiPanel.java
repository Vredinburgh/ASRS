/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordersys.gui;

import java.awt.Dimension;
import javax.swing.JPanel;
import ordersys.Controller;

/**
 *
 * @author gerri
 */
public class LeftGuiPanel extends JPanel {
    
    private OrderReadSection orderReadSection;
    private StartExecuteSection startExecuteSection;
    private CustomerSection customerSection;
    
    public LeftGuiPanel(Controller controller) {
        setPreferredSize(new Dimension(500, 600));
        
        orderReadSection = new OrderReadSection(controller);
        startExecuteSection = new StartExecuteSection(controller);
        customerSection = new CustomerSection(controller);
        
        controller.setCustomerSection(customerSection);
        
        add(orderReadSection);
        add(startExecuteSection);
        add(customerSection);
        
        setVisible(true);
    }
}
