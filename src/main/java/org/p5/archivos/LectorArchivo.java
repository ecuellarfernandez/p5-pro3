package org.p5.archivos;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.p5.arboles.Arbol;
import org.p5.listas.ListaDoble;
import org.p5.obj.Vendedor;

import java.io.*;
import java.util.HashMap;

public class LectorArchivo {
    private File fuente;
    private static Logger logger = LogManager.getRootLogger();
    public LectorArchivo(File f) {
        this.fuente = f;
    }

    public Arbol<Vendedor> leer()
            throws FileNotFoundException, IOException {
        if (fuente == null) {
            return null;
        }

        ListaDoble<String> nombres = new ListaDoble<>();
        ListaDoble<String> relaciones = new ListaDoble<>();

        leerNombresYRelaciones(nombres, relaciones);
        Arbol<Vendedor> resultado =
                formarArbol(nombres, relaciones);

        return resultado;
    }

    public Arbol<Vendedor> formarArbol(ListaDoble<String> nombres, ListaDoble<String> relaciones) {
        Arbol<Vendedor> resultado = new Arbol<>();
        HashMap<String, Arbol.Nodo<Vendedor>> nodos = new HashMap<>();

        // Agregar los nodos iniciales al mapa de nodos
        for (String nombre : nombres) {
            Vendedor vendedor = new Vendedor(nombre);
            Arbol.Nodo<Vendedor> nodo = new Arbol.Nodo<>(vendedor);
            nodos.put(nombre, nodo);
        }

        // Agregar las relaciones entre los nodos
        for (String relacion : relaciones) {
            String[] padreHijo = relacion.split(" ");
            String padre = padreHijo[0];
            String hijo = padreHijo[1];

            Arbol.Nodo<Vendedor> nodoPadre = nodos.get(padre);
            Arbol.Nodo<Vendedor> nodoHijo = nodos.get(hijo);

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

}
