package org.p5.gui;

import org.p5.arboles.Arbol;
import org.p5.archivos.LectorArchivo;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ArbolFrame extends JFrame {
    private Arbol<String> modelo;
    public ArbolFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new BorderLayout());
        modelo = new Arbol<>();
        try {
            crearArbolTest(modelo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ArbolPanel panel = new ArbolPanel(modelo);
        JScrollPane scroll = new JScrollPane(panel);
        scroll.setPreferredSize(new java.awt.Dimension(500, 500));

        this.getContentPane().add(scroll, BorderLayout.CENTER);

        this.pack();
        this.setVisible(true);
    }

    private void crearArbolTest(Arbol<String> arbol) throws IOException {
        /*
        * String a = "McPato";
        String b1 = "Donald";
        String b2 = "Daisy";
        String c1 = "Hugo";
        String c2 = "Paco";
        String c3 = "Luis";

        String d1 = "Maria";
        String d2 = "Lucia";
        String d3 = "Gloria";
        arbol.agregarNodo(null, a);
        arbol.agregarNodo(a.hashCode(), b1);
        arbol.agregarNodo(a.hashCode(), b2);
        arbol.agregarNodo(b2.hashCode(), c1);
        arbol.agregarNodo(b2.hashCode(), c2);
        arbol.agregarNodo(b2.hashCode(), c3);
        arbol.agregarNodo(b1.hashCode(), d1);
        arbol.agregarNodo(b1.hashCode(), d2);
        arbol.agregarNodo(b1.hashCode(), d3);*/

        File archivo = new File("C:\\Users\\erick\\IdeaProjects\\p5\\arbol.txt");
        if (!archivo.exists() || !archivo.canRead()) {
            System.out.println("No existe o no podemos leer");
            System.exit(0);
        }
        LectorArchivo lector = new LectorArchivo(archivo);
        Arbol<String> arbolLeido = lector.leer();
        modelo=arbolLeido;

    }

    public static void main(String[] args) {
        new ArbolFrame();
    }
}
