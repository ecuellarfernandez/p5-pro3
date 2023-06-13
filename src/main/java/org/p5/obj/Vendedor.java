package org.p5.obj;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.p5.arboles.Arbol;
import org.p5.listas.ListaDoble;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Date;

public class Vendedor {
    private static Logger logger = LogManager.getRootLogger();
    private String nombre;
    private ListaDoble<Venta> ventasRealizadas = new ListaDoble<>();
    private ListaDoble<Vendedor> reclutas = new ListaDoble<>();
    private Date fecha;
    //ganancias acumuladas por el vendedor y sus reclutas
    //Es decir suma de ganancias propias y de sus reclutas
    //ganancias que el propio vendedor ha generado
    private double gananciasPropias = 0.0;
    PropertyChangeSupport observado;

    public Vendedor(String nombre) {
        this.nombre = nombre;
        this.fecha = new Date();
        gananciasPropias = calcularGananciaPropia();
        observado = new PropertyChangeSupport(this);
    }

    public double calcularGananciaPropia() {
        if (!ventasRealizadas.isEmpty()) {
            int precioProducto = 15;
            double porcentaje = 0.2; // Porcentaje para las ventas propias (nivel 0)
            double resultado = 0.0;
            for (Venta venta : ventasRealizadas) {
                resultado += venta.getCantidadVendida() * precioProducto * porcentaje;
            }
            //logger.info("Ganancias propias de " + nombre + ": " + resultado);
            return resultado;
        } else {
            //logger.info("Aún no hay ventas en: " + nombre);
            return 0.0;
        }
    }

    public void agregarVenta(Venta venta) {
        venta.setVendedor(this);
        ventasRealizadas.agregar(venta);
//        logger.info("=====================================\u001B[32m VENTAS DE " + nombre + "\u001B[0m (" + ventasRealizadas.tamano() + ")=====================================");
//        logger.info("Venta agregada: " + venta + " a " + nombre);
//        logger.info("Venta agregada, calculando de nuevo ganancias acomuladas...");
//        logger.info("======================================================================================");
        calcularGananciaPropia();
        observado.firePropertyChange("ventas", true, false);
    }

    //    public void agregarRecluta(Vendedor recluta) {
//        reclutas.agregar(recluta);
//        logger.info("--------------------------------\u001B[34mRECLUTAS DE " + nombre + " \u001B[0m(" + reclutas.tamano() + ")--------------------------------");
//        logger.info("Recluta agregado: " + recluta.getNombre());
//        logger.info("Recluta agregado, calculando de nuevo ganancias acomuladas...");
//        logger.info("--------------------------------------------------------------------------------");
//        calcularGananciasAcumuladas();
//    }
    public void setGananciasPropias(double ganancia) {
        this.gananciasPropias = ganancia;
    }

    public ListaDoble<Vendedor> getReclutas() {
        return reclutas;
    }

    public ListaDoble<Venta> getVentasRealizadas() {
        return ventasRealizadas;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }

    public ListaDoble<Venta> getVentas() {
        return ventasRealizadas;
    }

    public void agregarRecluta(Vendedor contenido) {
        reclutas.agregar(contenido);
    }
    public void addObserver(PropertyChangeListener o) {observado.addPropertyChangeListener(o);}
}
