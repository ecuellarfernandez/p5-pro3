package org.p5.obj;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.p5.listas.ListaDoble;

import java.util.Date;

public class Vendedor {
    private static Logger logger = LogManager.getRootLogger();
    private String nombre;
    private ListaDoble<Venta> ventasRealizadas;
    private ListaDoble<Vendedor> reclutas;
    private Date fecha;
    private double gananciasAcomuladas;

    public Vendedor(String nombre) {
        this.nombre = nombre;
        this.ventasRealizadas = new ListaDoble<>();
        this.reclutas = new ListaDoble<>();
        this.fecha = new Date();
        this.gananciasAcomuladas = 0.0;
    }

    private void calcularGananciasAcomuladas(){
        gananciasAcomuladas = calcularGanancia() + calcularGananciasReclutas();
    }

    //calcularganancias
    public double calcularGanancia() {
        int precioProducto = 15;
        int porcentaje = 20; //nivel 0
        double resultado = 0.0;
        for (Venta venta : ventasRealizadas) {
            resultado += (double) (venta.getCantidadVendida() * precioProducto) /100 * porcentaje;
        }
        //logger.info("Ganancia de " + nombre + " es: " + resultado);

        return resultado;
    }

    //calcularganancias de reclutas
    public double calcularGananciasReclutas(){
        double resultado = 0.0;
        int nivelActual = 0;
        //calculamos ganancia de los hijos
        int porcentajeHijos = 5; //nivel 1
        int porcentajeNietos = 3; //nivel 2
        //a partir de bisnietos sera 0.05% para abajo de ese nivel
        //es decir tataranietos, etc
        double porcentajeBisnietos = 0.5; //nivel 3

        double gananciaHijos = 0.0;
        for (Vendedor recluta : reclutas) {
            //entrando a primer nivel de hijos
            gananciaHijos += recluta.getGananciasAcomuladas() * porcentajeHijos / 100;
            //entrando a segundo nivel de nietos

            for (Vendedor nieto : recluta.reclutas) {
                resultado += nieto.getGananciasAcomuladas() * porcentajeNietos / 100;
                //entrando a tercer nivel de bisnietos

                for (Vendedor bisnieto : nieto.reclutas) {
                    resultado += bisnieto.getGananciasAcomuladas() * porcentajeBisnietos / 100;
                }
            }
        }


        return resultado;
    }

    public void agregarVenta(Venta venta) {
        venta.setVendedor(this); // Asignar el vendedor actual a la venta
        ventasRealizadas.agregar(venta);
    }

    public void agregarRecluta(Vendedor r) {
        reclutas.agregar(r);
    }

    public ListaDoble<Venta> getVentasRealizadas() {
        return ventasRealizadas;
    }

    public double getGananciasAcomuladas(){
        if(gananciasAcomuladas == 0.0){
            logger.info("Todavía no se han calculado las ganancias...");
            calcularGananciasAcomuladas();
        }
        return gananciasAcomuladas;
    }

    public Date getFecha(){
        return fecha;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }

    public String toStringVentasRealizadas() {
        return ventasRealizadas.toString();
    }


}
