package org.p5.gui;

import org.p5.arboles.Arbol;
import org.p5.obj.Vendedor;
import org.p5.obj.Venta;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class VentaFrame extends JFrame {

    public VentaFrame(Arbol.Nodo<Vendedor> vendedorNodo){
        this.setSize(300, 400);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(2, 1));

        JTextField fieldVenta = new JTextField(10);
        JButton btnAceptar = new JButton("Aceptar");
        add(fieldVenta);
        add(btnAceptar);
        btnAceptar.addActionListener(e -> {
            String regex = "\\d+";
            if(fieldVenta.getText().matches(regex)) {
                vendedorNodo.getContenido().agregarVenta(new Venta(vendedorNodo.getContenido(), Integer.parseInt(fieldVenta.getText()), new Date()));
                this.dispose();
            }
        });

        this.pack();
        this.setVisible(true);
    }

}
