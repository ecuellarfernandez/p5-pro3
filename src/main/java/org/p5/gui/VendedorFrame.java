package org.p5.gui;

import org.p5.arboles.Arbol;
import org.p5.obj.Vendedor;

import javax.swing.*;
import java.awt.*;

public class VendedorFrame extends JFrame {
    public VendedorFrame(Arbol.Nodo<Vendedor> nodo){
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 400);

        VendedorPanel panel = new VendedorPanel(nodo);

        JScrollPane scroll = new JScrollPane(panel);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        this.getContentPane().add(scroll);

        // Obtener el tamaño preferido del panel y establecerlo como el tamaño preferido del JScrollPane
        Dimension panelSize = panel.getPreferredSize();
        scroll.setPreferredSize(panelSize);

        this.pack();
        this.setVisible(true);

        panel.requestFocusInWindow();
    }
}
