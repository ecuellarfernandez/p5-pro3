package org.p5.gui;

import org.p5.arboles.Arbol;
import org.p5.calculos.Ganancias;
import org.p5.listas.ListaDoble;
import org.p5.obj.Vendedor;
import org.p5.obj.Venta;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VendedorPanel extends JPanel {
    Arbol.Nodo<Vendedor> vendedor;
    public VendedorPanel(Arbol.Nodo<Vendedor> v) {
        vendedor = v;
        //tamaño de padre frame
        setSize(500, 500);
        setLayout(new GridLayout(1, 4));
        JLabel nombre = new JLabel(vendedor.getContenido().getNombre());
        JLabel gananciasPropias = new JLabel("Ganancias propias: " + vendedor.getContenido().calcularGananciaPropia());
        Ganancias g = new Ganancias();
        JLabel gananciasAcumuladas = new JLabel("Ganancias acumuladas: " + g.calcularGananciasTotales(vendedor));
        String ventas = vendedor.getContenido().getVentasRealizadas().toString();
        JList<String> lista = new JList<>(ventas.split(","));
        add(nombre);
        add(gananciasPropias);
        add(gananciasAcumuladas);
        add(lista);

    }


}
