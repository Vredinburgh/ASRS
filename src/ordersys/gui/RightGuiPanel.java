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
public class RightGuiPanel extends JPanel {

    private OrderInformationSection orderInformationSection;
    private StatusOrderpickSection statusOrderpickSection;
    private StatusOrderwrapSection statusOrderwrapSection;
    
    public RightGuiPanel(Controller controller) {
        setPreferredSize(new Dimension(800, 600));
        
        orderInformationSection = new OrderInformationSection(controller);
        statusOrderpickSection = new StatusOrderpickSection(controller);
        statusOrderwrapSection = new StatusOrderwrapSection(controller);
        
        controller.setOrderSection(orderInformationSection);
        controller.setTspSection(statusOrderpickSection);
        controller.setBppSection(statusOrderwrapSection);
        
        add(orderInformationSection);
        add(statusOrderpickSection);
        add(statusOrderwrapSection);

        setVisible(true);
    }
}
