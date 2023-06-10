package org.p5.obj;

import org.p5.listas.ListaDoble;

import java.util.Date;

public class Vendedor {
    private String nombre;
    private ListaDoble<Venta> ventasRealizadas;
    private Date fecha;
    private double gananciasAcomuladas;

    public Vendedor(String nombre) {
        this.nombre = nombre;
        this.ventasRealizadas = new ListaDoble<>();
        this.fecha = fecha;
        this.gananciasAcomuladas = 0.0;
    }

    //calcularganancias

    //calcularganancias de reclutas

}
