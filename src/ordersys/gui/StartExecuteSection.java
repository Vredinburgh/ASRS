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
public class StartExecuteSection extends JPanel implements ActionListener {

    private JButton startButton;
    
    public StartExecuteSection() {
        setPreferredSize(new Dimension(500, 175));

        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Start uitvoeren"));

        startButton = new JButton("Start uitvoeren");
        startButton.setPreferredSize(new Dimension(450, 135));
        startButton.addActionListener(this);
        
        add(startButton);

        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == startButton) {
            System.out.println("Uitvoeren gestart!");
        }
    }
}
