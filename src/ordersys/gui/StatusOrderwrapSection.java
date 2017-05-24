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
import ordersys.BPPHandler;
import ordersys.Controller;

/**
 *
 * @author gerri
 */
public class StatusOrderwrapSection extends JPanel {

    private Controller controller;
    private BPPHandler bpp;

    public StatusOrderwrapSection(Controller controller) {
        setPreferredSize(new Dimension(395, 405));

        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Status inpakrobot"));

        this.controller = controller;

        setVisible(true);
    }

    public void setBppHandler(BPPHandler bpp) {
        this.bpp = bpp;
    }
    
    public void updateContainer() {
        
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (bpp != null && bpp.startBpp) {
            System.out.println("");
            g.drawString("Doos 1", 30, 50);
            g.drawString("Doos 2", 205, 50);

            g.drawRect(30, 60, 158, 310);
            g.drawRect(205, 60, 158, 310);
        }
    }
}
