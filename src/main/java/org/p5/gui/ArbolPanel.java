package org.p5.gui;

import org.p5.arboles.Arbol;
import org.p5.obj.Vendedor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.HashMap;

public class ArbolPanel extends JPanel {
    private Arbol<Vendedor> modelo;
    //nodos y sus coordenadas
    private HashMap<Arbol.Nodo<Vendedor>, Point> nodosPosiciones = new HashMap<>();

    public ArbolPanel(Arbol<Vendedor> modelo) {
        this.modelo = modelo;

        this.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                obtenerNodo(evt);
            }
        });
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(600,400);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        DibujoArbol dibujo = new DibujoArbol(modelo);
        nodosPosiciones = dibujo.getNodos();
        dibujo.dibujar(g);
    }

    private void obtenerNodo(MouseEvent evt) {
        //obtener coordenadas para compararlas con las de los nodos
        int x = evt.getX();
        int y = evt.getY();

        for (Arbol.Nodo<Vendedor> nodo : nodosPosiciones.keySet()) {
            Point p = nodosPosiciones.get(nodo);
            //si las coordenadas del click estan dentro de las del nodo
            if (x >= p.x && x <= p.x + 30 && y >= p.y && y <= p.y + 30) {
                //mostrar informacion del nodo
                //JOptionPane.showMessageDialog(this, vendedor.toString());
                new VendedorFrame(nodo);
            }
        }

    }

}
