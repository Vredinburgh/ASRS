package ordersys.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by ian on 02/05/2017.
 */
public class OrdersysGUI extends JFrame implements ActionListener {

    private LeftGuiPanel leftPanel;
    private RightGuiPanel rightPanel;
    
    public OrdersysGUI() throws HeadlessException {
        leftPanel = new LeftGuiPanel();
        rightPanel = new RightGuiPanel();
        
        setTitle("Ordersysteem");
        setSize(1366, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setLayout(new FlowLayout());
        
        add(leftPanel);
        add(rightPanel);
        
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
