package org.p5;

import org.p5.arboles.Arbol;
import org.p5.archivos.LectorArchivo;
import org.p5.gui.ArbolFrame;
import org.p5.obj.Vendedor;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Piramide {
    private Arbol<Vendedor> arbol;
    public void cargarArbol() {
        File arbolArchivo = new File("C:\\Users\\erick\\IdeaProjects\\p5\\arbol.txt");
        File ventasArchivo = new File("C:\\Users\\erick\\IdeaProjects\\p5\\ventas.txt");
        if (!arbolArchivo.exists() || !arbolArchivo.canRead()) {
            System.out.println("No existe o no podemos leer");
            System.exit(0);
        }
        LectorArchivo lector = new LectorArchivo(arbolArchivo, ventasArchivo);
        Arbol<Vendedor> arbol = null;

        try {
            arbol = lector.leer();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.arbol = arbol;
        calcularGananciasTotales(arbol.getRaiz());
    }

    public void calcularGananciasTotales(Arbol.Nodo<Vendedor> raiz) {
        Vendedor vendedor = raiz.getContenido();
        double gananciaTotal = vendedor.calcularGananciaPropia() + recorrerNivelDeHijos(raiz, 1);
        System.out.println("Ganancia total: " + gananciaTotal);
    }

    private double recorrerNivelDeHijos(Arbol.Nodo<Vendedor> nodo, int nivel) {
        double ganancia = 0;

        for (Arbol.Nodo<Vendedor> hijo : nodo.getHijos()) {
            double gananciaHijo = hijo.getContenido().calcularGananciaPropia();
            double porcentajeGanancia = obtenerPorcentajeGanancia(nivel);

            ganancia += gananciaHijo * porcentajeGanancia;
            ganancia += recorrerNivelDeHijos(hijo, nivel + 1);
        }
        return ganancia;
    }

    private double obtenerPorcentajeGanancia(int nivel) {
        if (nivel == 0) {
            return 0.2;
        } else if (nivel == 1) {
            return 0.05;
        } else if (nivel == 2) {
            return 0.02;
        } else {
            return 0.005;
        }
    }

    public void mostrarArbol() {
        new ArbolFrame(arbol);
    }

    public void iniciar() {
        cargarArbol();
//        calcularGananciasDeRaiz();
        mostrarArbol();
    }
}
