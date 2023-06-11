package org.p5.obj;

import org.p5.listas.ListaDoble;

import java.util.ArrayList;
import java.util.Date;

public class Vendedor {
    private String nombre;
    private ArrayList<Venta> ventasRealizadas;
    private Date fecha;
    private double gananciasAcomuladas;

    public Vendedor(String nombre) {
        this.nombre = nombre;
        this.ventasRealizadas = new ArrayList<>();
        this.fecha = new Date();
        this.gananciasAcomuladas = 0.0;
    }

    public void agregarVenta(Venta venta) {
        venta.setVendedor(this); // Asignar el vendedor actual a la venta
        ventasRealizadas.add(venta);
    }

    public String getVentasRealizadas() {
        return ventasRealizadas.toString();
    }

    public Venta[] getVentas() {
        Venta[] ventas = new Venta[ventasRealizadas.size()];
        for (int i = 0; i < ventas.length; i++) {
            ventas[i] = ventasRealizadas.get(i);
        }
        return ventas;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }

    //calcularganancias

    //calcularganancias de reclutas

}
