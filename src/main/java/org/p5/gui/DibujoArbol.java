package org.p5.gui;


import org.p5.arboles.Arbol;
import org.p5.obj.Vendedor;

import java.awt.*;
import java.util.HashMap;

public class DibujoArbol {
    private static final int ESPACIO_HORIZONTAL = 20;
    private static final int ESPACIO_VERTICAL = 50;
    private static final int TAMANO_NODO = 40;
    private final Arbol<Vendedor> modelo;
    private HashMap<Arbol.Nodo<Vendedor>, Point> nodos = new HashMap<>();

    public DibujoArbol(Arbol<Vendedor> modelo) {
        this.modelo = modelo;
    }

    public void dibujar(Graphics g) {
        dibujarNodo(modelo.getRaiz(), 100, 100, g);
    }

    public void dibujarNodo(Arbol.Nodo<Vendedor> nodo,
                            int x, int y, Graphics g) {
        int ancho = ancho(nodo);

        int xNodo = x + ancho / 2 - (TAMANO_NODO / 2);

        int xHijo = x;
        int yHijo = y + TAMANO_NODO + ESPACIO_VERTICAL;
        for (Arbol.Nodo<Vendedor> hijo : nodo.getHijos()) {

            int avanzar = ancho(hijo) + ESPACIO_HORIZONTAL;

            g.setColor(Color.black);
            g.drawLine(xNodo + TAMANO_NODO / 2,
                    y + TAMANO_NODO / 2,
                    xHijo + (avanzar - ESPACIO_HORIZONTAL) / 2,
                    yHijo + TAMANO_NODO / 2);

            dibujarNodo(hijo, xHijo, yHijo, g);
            xHijo += avanzar;
        }
        g.setColor(Color.white);
        g.fillOval(xNodo, y,
                TAMANO_NODO, TAMANO_NODO);
        g.setColor(Color.black);
        g.drawOval(xNodo, y,
                TAMANO_NODO, TAMANO_NODO);
        g.drawString(nodo.getContenido().getNombre(), xNodo + 10, y + 20);
        nodos.put(nodo, new Point(xNodo + 10, y));
    }

    public int ancho(Arbol.Nodo<Vendedor> nodo) {
        if (nodo.getHijos().tamano() == 0) {
            return TAMANO_NODO;
        }

        int ancho = 0;
        int espacio = 0;
        for (Arbol.Nodo<Vendedor> hijo : nodo.getHijos()) {
            int avanzar = espacio + ancho(hijo);
            ancho += avanzar;
            espacio = ESPACIO_HORIZONTAL;
        }
        nodos.put(nodo, new Point(ancho, 0));
        return ancho;
    }

    public HashMap<Arbol.Nodo<Vendedor>, Point> getNodos() {
        return nodos;
    }

}
