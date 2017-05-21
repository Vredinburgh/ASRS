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
import ordersys.Controller;
import ordersys.Receipt;

/**
 *
 * @author gerri
 */
public class StartExecuteSection extends JPanel implements ActionListener {

    private JButton startButton;
    
    private Controller controller;
    
    public StartExecuteSection(Controller controller) {
        setPreferredSize(new Dimension(500, 175));

        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Start uitvoeren"));

        this.controller = controller;
        
        startButton = new JButton("Start uitvoeren");
        startButton.setPreferredSize(new Dimension(450, 135));
        startButton.addActionListener(this);
        
        add(startButton);

        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == startButton) {
            //Start the orderpicking process
            controller.startOrderpicking();
            
            //For now generate receipt, will be removed in the future
            /*Receipt receipt = new Receipt(controller.getInvoiceData().getOrder().getProducts());
            receipt.createPDF("testpdf.pdf");*/
        }
    }
}
