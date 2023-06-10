package org.p5;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        Piramide piramide = new Piramide();
        piramide.cargarArbol();
        piramide.cargarVentas();
        piramide.mostrarArbol();
    }
}