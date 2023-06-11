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

        setSize(1000, 1000);

        ArbolPanel panel = new ArbolPanel(modelo);

        JScrollPane scroll = new JScrollPane(panel);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        this.getContentPane().add(scroll, BorderLayout.CENTER);

        // Obtener el tamaño preferido del panel y establecerlo como el tamaño preferido del JScrollPane
        Dimension panelSize = panel.getPreferredSize();
        scroll.setPreferredSize(panelSize);

        this.pack();
        this.setVisible(true);

        panel.requestFocusInWindow();
    }

    private void crearArbolTest(Arbol<String> arbol) {
        String a = "McPato";
        String b1 = "Donald";
        String b2 = "Daisy";
        String c1 = "Hugo";
        String c2 = "Paco";
        String c3 = "Luis";

        String d1 = "Maria";
        String d2 = "Lucia";
        String d3 = "Gloria";
        arbol.agregarNodo(null, a);
        arbol.agregarNodo(a.hashCode(), b1);
        arbol.agregarNodo(a.hashCode(), b2);
        arbol.agregarNodo(b2.hashCode(), c1);
        arbol.agregarNodo(b2.hashCode(), c2);
        arbol.agregarNodo(b2.hashCode(), c3);
        arbol.agregarNodo(b1.hashCode(), d1);
        arbol.agregarNodo(b1.hashCode(), d2);
        arbol.agregarNodo(b1.hashCode(), d3);
    }
}
