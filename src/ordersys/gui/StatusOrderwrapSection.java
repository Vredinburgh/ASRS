/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordersys.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import ordersys.BPPHandler;
import ordersys.Controller;
import ordersys.bpp.ProductBPP;

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

    //Fucntion to add a product to a bin
    public void updateContainer(ProductBPP product, int sizePreviousProduct, int container, int amountsInBin) {
        //Get the graphic component and generate a new random
        Graphics gr = this.getGraphics();
        Random rand = new Random();

        //Get random RGB variables
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();

        //Get a random color
        Color randomColor = new Color(r, g, b);

        //Set the random color to the graphics component
        gr.setColor(randomColor);

        //Check to which container the product must be added
        if (container == 1) {
            if (sizePreviousProduct > 1) {
                gr.fillRect(31, (293 + 77) - (310 / (20 / sizePreviousProduct) * amountsInBin), 157, -(310 / (20 / product.getGrootte())));
            } else {
                gr.fillRect(31, (293 + 77) - (310 / (20 / sizePreviousProduct) * amountsInBin), 157, -(310 / (20 / product.getGrootte())));
            }
        } else if (container == 2) {
            if (sizePreviousProduct > 1) {
                gr.fillRect(206, (293 + 77) - (310 / (20 / sizePreviousProduct) * amountsInBin), 157, -(310 / (20 / product.getGrootte())));
            } else {
                gr.fillRect(206, (293 + 77) - (310 / (20 / sizePreviousProduct) * amountsInBin), 157, -(310 / (20 / product.getGrootte())));
            }
        }
    }

    //Function to add new bins
    public void resetProducts(int bin1, int bin2, boolean drawBin2) {
        Graphics gr = this.getGraphics();

        gr.clearRect(30, 40, 300, 10);
        gr.clearRect(31, 60, 157, 310);

        gr.drawString("Doos " + bin1, 30, 50);
        if (drawBin2) {
            gr.drawString("Doos " + bin2, 205, 50);
            gr.clearRect(206, 60, 157, 310);
        } else {
            gr.clearRect(205, 60, 160, 312);
        }
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
