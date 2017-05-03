/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordersys.gui;

import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author gerri
 */
public class RightGuiPanel extends JPanel {

    public RightGuiPanel() {
        setPreferredSize(new Dimension(800, 600));

        add(new OrderInformationSection());
        add(new StatusOrderpickSection());
        add(new StatusOrderwrapSection());

        setVisible(true);
    }
}
