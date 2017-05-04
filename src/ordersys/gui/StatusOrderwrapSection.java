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
import ordersys.Controller;

/**
 *
 * @author gerri
 */
public class StatusOrderwrapSection extends JPanel {

    private Controller controller;
    
    public StatusOrderwrapSection(Controller controller) {
        setPreferredSize(new Dimension(395, 405));

        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Status inpakrobot"));

        this.controller = controller;
        
        setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawString("Doos 1", 30, 50);
        g.drawString("Doos 2", 205, 50);

        g.drawRect(30, 60, 158, 310);
        g.drawRect(205, 60, 158, 310);

        g.setColor(Color.green);
        g.fillRect(30, 60 + 155, 158, 155);

        g.setColor(Color.red);
        g.fillRect(205, 60, 158, 310);
    }
}
