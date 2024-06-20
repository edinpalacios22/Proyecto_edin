/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Modelo_venta;
import Vista.Principal;
import Vista.Ventas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author SENA
 */
public class Controlador_Ventas implements ActionListener {

    Ventas ven = new Ventas();
    Principal prin = new Principal();
    Modelo_venta modvent = new Modelo_venta();

    public Controlador_Ventas() {
        ven.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ven.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                ControladorPrincipal princi = new ControladorPrincipal();
                princi.iniciarPrincipal(0);
            }

        });
    }

    public void llenarVentas() {
        //   us.addWindowListener(l);
        prin.setVisible(false);
        ven.setLocationRelativeTo(null);
        ven.setVisible(true);
    }
        @Override
        public void actionPerformed(ActionEvent e ) {

          if (e.getSource().equals(prin.getjBtnVentas())) {
                if (ven.getJCboxpago().getSelectedItem().equals("seleccionar") || ven.getTxtCedula().getText().isEmpty() || ven.getTxtusuario().getText().isEmpty());
                JOptionPane.showMessageDialog(null, "falta de informacion");
            }
        }
    }


