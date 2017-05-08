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
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import ordersys.Controller;

/**
 *
 * @author gerri
 */
public class OrderReadSection extends JPanel implements ActionListener {

    private JButton readButton;

    private Controller controller;

    public OrderReadSection(Controller controller) {
        setPreferredSize(new Dimension(500, 175));

        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Order inlezen"));

        this.controller = controller;

        readButton = new JButton("Selecteer XML bestand");
        readButton.setPreferredSize(new Dimension(450, 135));
        readButton.addActionListener(this);

        add(readButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == readButton) {
            JFileChooser chooser = new JFileChooser();
            chooser.setAcceptAllFileFilterUsed(false);
            chooser.setFileFilter(new FileNameExtensionFilter("Alleen XML", "xml"));

            int returnValue = chooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                try {
                    controller.setInvoiceData(chooser.getSelectedFile().getAbsolutePath());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Geen geldig XML bestand, controleer de indeling.");
                    System.out.println(ex);
                }
            }
        }
    }
}
