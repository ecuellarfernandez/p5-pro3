package org.p5.archivos;

import org.p5.arboles.Arbol;
import org.p5.obj.Vendedor;

import java.io.File;

public class TestLectorArbol {
    public static void main(String[] args) throws Exception {
        File archivo = new File("C:\\Users\\erick\\IdeaProjects\\p5\\arbol.txt");
        if (!archivo.exists() || !archivo.canRead()) {
            System.out.println("No existe o no podemos leer");
            System.exit(0);
        }
        LectorArchivo lector = new LectorArchivo(archivo);
        Arbol<Vendedor> arbol = lector.leer();

        System.out.println(arbol);

    }

}
