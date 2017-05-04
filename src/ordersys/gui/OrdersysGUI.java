package ordersys.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ordersys.Controller;

/**
 * Created by ian on 02/05/2017.
 */
public class OrdersysGUI extends JFrame {

    private LeftGuiPanel leftPanel;
    private RightGuiPanel rightPanel;
    
    private Controller controller;
    
    public OrdersysGUI() {
        controller = new Controller();
        
        leftPanel = new LeftGuiPanel(controller);
        rightPanel = new RightGuiPanel(controller);
        
        setTitle("Ordersysteem");
        setSize(1366, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setLayout(new FlowLayout());
        
        add(leftPanel);
        add(rightPanel);
        
        setVisible(true);
    }
}
