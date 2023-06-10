package org.p5.archivos;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.p5.listas.ListaDoble;
import org.p5.arboles.Arbol;

import java.io.*;

public class LectorArchivo {
    private File fuente;
    private static Logger logger = LogManager.getRootLogger();
    public LectorArchivo(File f) {
        this.fuente = f;
    }

    public Arbol<String> leer()
            throws FileNotFoundException, IOException {
        if (fuente == null) {
            return null;
        }

        ListaDoble<String> nombres = new ListaDoble<>();
        ListaDoble<String> relaciones = new ListaDoble<>();

        leerNombresYRelaciones(nombres, relaciones);
        Arbol<String> resultado =
                formarArbol(nombres, relaciones);

        return resultado;
    }

    public Arbol<String> formarArbol(
            ListaDoble<String> nombres,
            ListaDoble<String> relaciones) {

        Arbol<String> resultado = new Arbol<>();
        boolean esRaiz = true;
        for (String relacion : relaciones) {
            String[] padreHijo = relacion.split(" ");
            String padre = nombres.encontrar(padreHijo[0]);
            String hijo = nombres.encontrar(padreHijo[1]);

            if (esRaiz) {
                resultado.agregarNodo(null, padre);
                esRaiz = false;
            }

            resultado.agregarNodo(padre.hashCode(), hijo);
        }

        return resultado;
    }

    private void leerNombresYRelaciones(ListaDoble<String> nombres, ListaDoble<String> relaciones)
            throws FileNotFoundException, IOException {

        BufferedReader reader =
                new BufferedReader(new FileReader(fuente));
        String linea;
        boolean leerNombres = true;
        while ((linea = reader.readLine()) != null)
        {
            if (linea.trim().equals("")) {
                leerNombres = false;
            }
            if (leerNombres) {
                nombres.insertar(linea);
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
    //leer ventas con formato: <nombre>,<cantidad>,<fecha en formato dd.mm.yyyy>
    private void leerVentas(){

    }
}
