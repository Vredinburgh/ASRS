/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordersys;

import ordersys.gui.StatusOrderpickSection;
import ordersys.gui.StatusOrderwrapSection;

/**
 *
 * @author gerri
 */
public class BPPHandler {

    private StatusOrderwrapSection bppSection;

    public boolean startBpp;

    public BPPHandler(StatusOrderwrapSection bppSection) {
        this.bppSection = bppSection;
    }

    public void startBpp() {
        startBpp = true;
        
        bppSection.repaint();
    }
}
