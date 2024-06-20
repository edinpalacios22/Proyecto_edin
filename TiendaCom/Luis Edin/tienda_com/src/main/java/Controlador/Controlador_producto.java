///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
package Controlador;

import Modelo.modelo_producto;
import Vista.Principal;
import Vista.Producto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.Border;

public class Controlador_producto implements ActionListener {

    Producto prod = new Producto();
    Principal prin = new Principal();
    modelo_producto modprod = new modelo_producto();

    public Controlador_producto() {
        prin.getBtnimg().addActionListener(this);
        prin.getBtnguardar().addActionListener(this);
        prod.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        prod.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                ControladorPrincipal princ = new ControladorPrincipal();
                princ.iniciarPrincipal(0);
            }
        });
    }

    public void controlProducto() {
        prin.setVisible(false);
        prod.setLocationRelativeTo(null);
        prod.setTitle("Producto");
        prod.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource().equals(prin.getBtnimg())){
            modprod.buscarImagen();
            File file= new File(modprod.getRuta());
            String archivo= file.getName();//obtiene solo el nombre de la ruta
            prin.getTxtimg().setText(archivo);
        }
        if(e.getSource().equals(prin.getBtnguardar())){
            modprod.setNom(prin.getTxtimg().getText());
            modprod.setDes(prin.getTXTbuscar().getText());
            modprod.setImagen(modprod.convertirImagen(modprod.getRuta()));
            modprod.insertarProducto();
        }
    if (e.getSource().equals(prod.getBTNguardar())) {
            //validar campos vacios
            if ((prod.getTXTnom_prod().getText().isEmpty()) || (prod.getTXTdesc_prod().getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Debe ingresar información en los campos de Nombre y Descripción");
            } else {
                modprod.setNom(prod.getTXTnom_prod().getText());
                modprod.setDes(prod.getTXTdesc_prod().getText());
                modprod.setImagen(modprod.convertirImagen(modprod.getRuta()));

                if (prod.getBTNguardar().getText().equals("Guardar")) {
                    modprod.insertarProducto();
                    prod.setVisible(false);//para que cuando guarde se cierre de inmediato la ventana
                    prod.dispose();
                } else {
                    modprod.actualizarProducto();
                    prod.setVisible(false);
                    prod.dispose();
                modprod.mostrartablaproducto(prin.getJpanel_prod(), "", "producto");
                }
            }
        }
        if (e.getSource().equals(prod.getBTNcancelar())) {
            prod.dispose();
        }
    }
    //Actualizar producto
    void actualizarProducto(int doc) {
        modprod.buscarproducto(doc);
        prod.getTXTnom_prod().setText(modprod.getNom());
        prod.getTXTdesc_prod().setText(modprod.getDes());
        prod.getTxtimagen().setText(modprod.getRuta());

        File file = new File(modprod.getRuta());
        String archivo = file.getName();//obtiene solo el nombre de la ruta
        prod.getTxtimagen().setText(archivo);
   
 //Cambiar Titulo
        Border borde = BorderFactory.createTitledBorder(null, "Actualizar Producto",
                javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new java.awt.Font("Yu Gothic UI", 1, 36),
                new java.awt.Color(204, 0, 204));
        prin.setVisible(false);
        prod.setLocationRelativeTo(null);
        prod.getBTNguardar().setText("Actualizar");
        prod.setVisible(true);
    }

    //Eliminar producto
    void eliminarProducto(int doc) {
        int resp = JOptionPane.showConfirmDialog(null, "¿Desea eliminar el Producto? \n" + doc,
                "Eliminar Producto", JOptionPane.YES_OPTION);
        if (resp == JOptionPane.YES_OPTION) {
            modprod.eliminarProducto();
        }
    }
}
    


