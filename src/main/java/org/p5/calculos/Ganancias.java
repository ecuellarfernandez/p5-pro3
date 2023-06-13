package org.p5.calculos;

import org.p5.arboles.Arbol;
import org.p5.obj.Vendedor;

public class Ganancias {
    public double calcularGananciasTotales(Arbol.Nodo<Vendedor> raiz) {
        Vendedor vendedor = raiz.getContenido();
        double gananciaTotal = vendedor.calcularGananciaPropia() + recorrerNivelDeHijos(raiz, 1);
        System.out.println("Ganancia total: " + gananciaTotal);
        return gananciaTotal;
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

}
