package org.p5.obj;

import java.util.Date;

public class Venta {
    private Vendedor vendedor;
    private int cantidadVendida;
    private Date fechaVenta;

    public Venta(Vendedor vendedor, int cantidadVendida, Date fechaVenta) {
        this.vendedor = vendedor;
        this.cantidadVendida = cantidadVendida;
        this.fechaVenta = fechaVenta;
    }

    // Getters y setters

    public Vendedor getVendedor() {
        return vendedor;
    }

    public int getCantidadVendida() {
        return cantidadVendida;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public double getMontoVenta() {
        return cantidadVendida * 15.0;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    @Override
    public String toString() {
        return "Venta{" +
                "vendedor=" + vendedor +
                ", cantidadVendida=" + cantidadVendida +
                ", fechaVenta=" + fechaVenta +
                '}';
    }

}
