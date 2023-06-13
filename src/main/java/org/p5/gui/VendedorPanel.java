package org.p5.gui;

import org.p5.arboles.Arbol;
import org.p5.calculos.Ganancias;
import org.p5.listas.ListaDoble;
import org.p5.obj.Vendedor;
import org.p5.obj.Venta;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class VendedorPanel extends JPanel implements PropertyChangeListener {
    Arbol.Nodo<Vendedor> vendedor;
    JList<String> lista;
    Ganancias g = new Ganancias();
    JLabel gananciasAcumuladas;
    JLabel gananciasPropias;

    public VendedorPanel(Arbol.Nodo<Vendedor> v) {
        v.getContenido().addObserver(this);
        vendedor = v;
        //tamaño de padre frame
        setSize(500, 500);
        setLayout(new FlowLayout());
        JLabel nombre = new JLabel(vendedor.getContenido().getNombre());
        gananciasPropias = new JLabel("Ganancias propias: " + vendedor.getContenido().calcularGananciaPropia());
        gananciasAcumuladas = new JLabel("Ganancias acumuladas: " + g.calcularGananciasTotales(vendedor));
        String ventas = vendedor.getContenido().getVentasRealizadas().toString();
        lista = new JList<>(ventas.split(","));
        JButton agregar = new JButton("Agregar venta");
        agregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnAgregar(v);
            }
        });
        agregar.setBounds(0, 0, 50, 10);
        nombre.setForeground(Color.BLUE);
        add(agregar);
        add(nombre);
        add(gananciasPropias);
        add(gananciasAcumuladas);
        add(lista);
    }

    public void btnAgregar(Arbol.Nodo<Vendedor> vendedorNodo) {
        new VentaFrame(vendedorNodo);
        //Venta venta = new Venta();
        //vendedorNodo.getContenido().agregarVenta(venta);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("ventas".equals(evt.getPropertyName())) {
            // Actualizar la lista de ventas en el panel
            String ventas = vendedor.getContenido().getVentasRealizadas().toString();
            String gananciasPropiasText = "Ganancias propias: " + vendedor.getContenido().calcularGananciaPropia();
            String gananciasAcumuladasText = "Ganancias acumuladas: " + g.calcularGananciasTotales(vendedor);
            lista.setListData(ventas.split(","));
            gananciasPropias.setText(gananciasPropiasText);
            gananciasAcumuladas.setText(gananciasAcumuladasText);
            revalidate();
            repaint();
        }
    }
}
