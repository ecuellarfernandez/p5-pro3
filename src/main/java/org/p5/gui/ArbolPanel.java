package org.p5.gui;

import org.p5.arboles.Arbol;
import org.p5.obj.Vendedor;

import javax.swing.*;
import java.awt.*;

public class ArbolPanel extends JPanel {
    private Arbol<Vendedor> modelo;

    public ArbolPanel(Arbol<Vendedor> modelo) {
        this.modelo = modelo;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(600,400);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        DibujoArbol dibujo = new DibujoArbol(modelo);
        dibujo.dibujar(g);
    }
}
