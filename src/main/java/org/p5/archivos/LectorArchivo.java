package org.p5.archivos;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.p5.arboles.Arbol;
import org.p5.listas.ListaDoble;
import org.p5.obj.Vendedor;
import org.p5.obj.Venta;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class LectorArchivo {
    private File fuente;
    private File fuenteVentas;
    private static Logger logger = LogManager.getRootLogger();

    public LectorArchivo(File f, File fv) {
        this.fuente = f;
        this.fuenteVentas = fv;
    }

    public Arbol<Vendedor> leer()
            throws FileNotFoundException, IOException {
        if (fuente == null) {
            return null;
        }
        //Leer arbol de relaciones
        ListaDoble<String> nombres = new ListaDoble<>();
        ListaDoble<String> relaciones = new ListaDoble<>();
        //leer ventas
        ListaDoble<String> nombreVendedorVenta = new ListaDoble<>();
        ListaDoble<String> numVentas = new ListaDoble<>();
        ListaDoble<String> fechaVentas = new ListaDoble<>();

        leerNombresYRelaciones(nombres, relaciones);
        leerVentas(nombreVendedorVenta, numVentas, fechaVentas);

        try {
            return formarArbol(nombres, relaciones,
                    nombreVendedorVenta, numVentas, fechaVentas);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public Arbol<Vendedor> formarArbol(ListaDoble<String> nombres,
                                       ListaDoble<String> relaciones,
                                       ListaDoble<String> nombreVendedorVenta,
                                       ListaDoble<String> numVentas,
                                       ListaDoble<String> fechaVentas) throws ParseException {
        Arbol<Vendedor> resultado = new Arbol<>();
        HashMap<String, Arbol.Nodo<Vendedor>> nodos = new HashMap<>();

        // Agregar los nodos iniciales al mapa de nodos
        for (int i = 0; i < nombres.tamano(); i++) {
            String nombreVendedor = nombres.getIndex(i);
            Vendedor vendedor = new Vendedor(nombreVendedor);

            for (int j = 0; j < nombreVendedorVenta.tamano(); j++) {
                if (nombreVendedor.equals(nombreVendedorVenta.getIndex(j))) {

                    int numeroVenta = Integer.parseInt(numVentas.getIndex(j));
                    Date fechaVenta = formatearFecha(fechaVentas.getIndex(j));
                    Venta nuevaVenta = new Venta(vendedor, numeroVenta, fechaVenta);

                    vendedor.agregarVenta(nuevaVenta);
                }
            }
            vendedor.calcularGanancia();

            Arbol.Nodo<Vendedor> nodo = new Arbol.Nodo<>(vendedor);
            nodos.put(nombreVendedor, nodo);
        }

        // Agregar las relaciones entre los nodos
        for (String relacion : relaciones) {
            String[] padreHijo = relacion.split(" ");
            String padre = padreHijo[0];
            String hijo = padreHijo[1];

            Arbol.Nodo<Vendedor> nodoPadre = nodos.get(padre);
            Arbol.Nodo<Vendedor> nodoHijo = nodos.get(hijo);
            nodoPadre.getContenido().agregarRecluta(nodoHijo.getContenido());
            //logger.debug("Padre: " + nodoPadre.getContenido().getNombre() + " Hijo: " + nodoHijo.getContenido().getNombre());

            if (nodoPadre == null || nodoHijo == null) {
                // Si alguno de los nodos no existe, omitir la relación incorrecta
                continue;
            }

            if (resultado.getRaiz() == null) {
                // Establecer el primer nodo encontrado como raíz
                resultado.setRaiz(nodoPadre);
            }

            nodoPadre.agregarHijo(nodoHijo);
        }

        return resultado;
    }

    private void leerVentas(ListaDoble<String> nombreVendedor,
                            ListaDoble<String> numVentas,
                            ListaDoble<String> fechaVentas) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fuenteVentas));
        String linea;
        while ((linea = reader.readLine()) != null) {
            String[] datos = linea.split(",");
            if (datos.length >= 3) {
                nombreVendedor.agregar(datos[0].trim());
                numVentas.agregar(datos[1].trim());
                fechaVentas.agregar(datos[2].trim());
            } else {
                logger.debug("Datos de ventas incompletos: " + linea);
            }
        }
        reader.close();
    }


    private void leerNombresYRelaciones(ListaDoble<String> nombres, ListaDoble<String> relaciones)
            throws FileNotFoundException, IOException {

        BufferedReader reader =
                new BufferedReader(new FileReader(fuente));
        String linea;
        boolean leerNombres = true;
        while ((linea = reader.readLine()) != null) {
            if (linea.trim().equals("")) {
                leerNombres = false;
            }
            if (leerNombres) {
                nombres.agregar(linea);
                continue;
            } else {
                if (!linea.trim().equals("")) {
                    relaciones.agregar(linea);
                }
                continue;
            }
        }

        reader.close();

        logger.debug(nombres);
        logger.debug(relaciones);
    }

    private Date formatearFecha(String fecha) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("dd.MM.yyyy");
        try {
            return formato.parse(fecha);
        } catch (ParseException e) {
            logger.error("Error al formatear fecha: " + fecha);
            throw e;
        }
    }

}
