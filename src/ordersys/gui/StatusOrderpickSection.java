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
import ordersys.TSPHandler;
import ordersys.xmlReader.Product;

/**
 *
 * @author gerri
 */
public class StatusOrderpickSection extends JPanel {

    private Controller controller;
    private TSPHandler tsp;

    private int xMultiplier;
    private int yMultiplier;
    
    private int[] shortestPath;

    public StatusOrderpickSection(Controller controller) {
        setPreferredSize(new Dimension(395, 405));

        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Status ophaalrobot"));

        this.controller = controller;

        xMultiplier = 395 / 5;
        yMultiplier = 405 / 5;

        setVisible(true);
    }

    public void setTspHandler(TSPHandler tsp) {
        this.tsp = tsp;
        shortestPath = tsp.returnShortestPath();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (tsp != null && tsp.startTsp) {
            for (Product p : tsp.products) {
                g.setColor(Color.black);
                g.drawString(p.getName(), p.getX() * xMultiplier - 60, p.getY() * yMultiplier - 60);
                g.setColor(Color.green);
                g.fillRect(p.getX() * xMultiplier - 60, p.getY() * yMultiplier - 60, 50, 50);
            }
            
            g.setColor(Color.black);
            
            //Draw the lines between the products according to the shortest path
            for (int i = 0; i < shortestPath.length; i++) {
                if (i + 1 < shortestPath.length) {
                    int x1 = tsp.products.get(shortestPath[i]).getX() * xMultiplier - 30;
                    int y1 = tsp.products.get(shortestPath[i]).getY() * yMultiplier - 30;
                    int x2 = tsp.products.get(shortestPath[i + 1]).getX() * xMultiplier - 30;
                    int y2 = tsp.products.get(shortestPath[i + 1]).getY() * yMultiplier - 30;

                    g.drawLine(x1, y1, x2, y2);
                }
            }
        }
    }
}
