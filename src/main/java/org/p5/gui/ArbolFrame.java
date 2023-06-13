package org.p5.gui;

import org.p5.arboles.Arbol;
import org.p5.obj.Vendedor;

import javax.swing.*;
import java.awt.*;

public class ArbolFrame extends JFrame {
    private Arbol<Vendedor> modelo;

    public ArbolFrame(Arbol<Vendedor> modelo) {
        this.modelo = modelo;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new BorderLayout());
        setSize(500, 500);

        ArbolPanel panel = new ArbolPanel(modelo);

        JScrollPane scroll = new JScrollPane(panel);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        this.getContentPane().add(scroll, BorderLayout.CENTER);

        // Obtener el tamaño preferido del panel y establecerlo como el tamaño preferido del JScrollPane
        Dimension panelSize = panel.getPreferredSize();
        scroll.setPreferredSize(panelSize);

        this.pack();
        this.setTitle("Visca el Barça");
        this.setVisible(true);

        panel.requestFocusInWindow();
    }

}
