package Controlador;

import Modelo.Modeloproveedor;
import Vista.Nuevo_usuario;
import Vista.Proveedor;
import Vista.Principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.sql.SQLException;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JOptionPane;
import javax.swing.border.Border;

public class Controlador_proveedor implements ActionListener {

    Proveedor nuev = new Proveedor();
    Principal prin = new Principal();
    Modeloproveedor prov = new Modeloproveedor();
    Nuevo_usuario vista = new Nuevo_usuario();
    ControladorPrincipal princi = new ControladorPrincipal();

    public Controlador_proveedor() {
        nuev.getjBGuardar().addActionListener(this);
        nuev.getjBCancelar().addActionListener(this);
        nuev.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        nuev.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                ControladorPrincipal princi = new ControladorPrincipal();
                princi.iniciarPrincipal(0);
            }

        });
    }

    public void llenarproveedor() {
        prin.setVisible(false);
        nuev.setLocationRelativeTo(null);
        nuev.setVisible(true);
        
        
        nuev.getjCSexo().addItem("seleccione");
        Map<String, Integer> dato = prov.llenarCombo("sexo");
        for (String sexo : dato.keySet()) {
            nuev.getjCSexo().addItem(sexo);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(nuev.getjBGuardar())) {
            if (nuev.getLblDocumento().getText().isEmpty() || nuev.getLblNombre().getText().isEmpty() || nuev.getLblTelefono().getText().isEmpty() || nuev.getLblDireccion().getText().isEmpty() || nuev.getLblDireccion().getText().isEmpty() || nuev.getjCSexo().getSelectedItem().equals("seleccione...") || nuev.getjDFecha().getDate() == null || nuev.getLbltipoPersona().getText().isEmpty());
            JOptionPane.showMessageDialog(null, "Falta de informacion");

        } else {
            ControladorPrincipal controlprinci = new ControladorPrincipal();
            if (princi.nuevo_ingreso(nuev.getTxtNombre().getText())) {
                JOptionPane.showMessageDialog(null, "Correo Invalido");
            } else {
                //Convertimos el dato de los combox al que entiende sql
                String valorSexo = nuev.getjCSexo().getSelectedItem().toString();
                int sexo = prov.llenarCombo("sexo").get(valorSexo);

                // seleccion de fecha, cambia al formato de fecha al que entiende sql
                java.util.Date fec = nuev.getjDFecha().getDate();
                long fe = fec.getTime();
                java.sql.Date fecha = new Date(fe);

//                prov.setTele(nuev.getjCtipoPersona().getSelectedItem().toString());
                prov.setNom(nuev.getTxtnombre().getText());
                prov.setDir(nuev.getTxtDireccion().getText());
                prov.setCorr(nuev.getTxtNombre().getText());
                prov.setTele(nuev.getTxtTelefono().getText());
                prov.setFechanaci(fecha);
                prov.setIdsex(sexo);

                if (nuev.getjBGuardar().getText().equals("Guardar")) {
                    nuev.dispose();
                } else {
                    prov.actualizarProveedor();
                    nuev.setVisible(false);
                    nuev.dispose();
                    prov.mostrarTablaProveedor(prin.getJpanelproveedor(), "", "Proveedor");
                }
            }
        if (e.getSource().equals(nuev.getjBGuardar())) {
            nuev.dispose();
        }
        if (e.getSource().equals(nuev.getjBCancelar())) {
            nuev.dispose();
        }
    }
}
        public void actualizarProveedor(int doc) {
//        prov.buscarProveedor(doc);
            nuev.getjCDocumento().setEnabled(false);
//            nuev.getCmbtipodoc_prove().setEnabled(false);
            nuev.getjCtipoPersona().setEnabled(false);
//            prov.getTxtdocuprovee().setText(String.valueOf(doc));
            nuev.getTxtnombre().setText(prov.getNom());
            nuev.getTxtTelefono().setText(prov.getTele());
            nuev.getLblCorreo().setText(prov.getCorr());
            nuev.getTxtDireccion().setText(prov.getDir());
            nuev.getjDFecha().setDate(prov.getFechanaci());

//            llenar Sexo
            Map<String, Integer> info = prov.llenarCombo("sexo");
            for (String sexo : info.keySet()) {
                nuev.getjCSexo().addItem(sexo);
            }
            //obtener el valor de la base de datos
            String valoSexo = prov.obtenerSeleccion(info, prov.getIdsex());
            nuev.getjCSexo().setSelectedItem(valoSexo);

            //Llenar tipo de documento y de persona
            nuev.getjCtipoPersona().setSelectedItem(nuev.getjCDocumento());
            nuev.getjCtipoPersona().setSelectedItem(nuev.getjCtipoPersona());

            //Cambiar Titulo
            Border borde = BorderFactory.createTitledBorder(null, "Actualizar Proveedor",
                    javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION,
                    new java.awt.Font("Yu Gothic UI", 1, 36),
                    new java.awt.Color(204, 0, 204));
            prin.getJpanelproveedor().setBorder(borde);
            prin.setVisible(false);
            nuev.setLocationRelativeTo(null);
            nuev.getjBGuardar().setText("Actualizar");
            nuev.setVisible(true);
        }

        //Eliminar proveedor
        void eliminarProveedor(int doc) {
        int resp = JOptionPane.showConfirmDialog(null, "¿Desea eliminar al Proveedor? \n" + doc,
                    "Eliminar Proveedor", JOptionPane.YES_OPTION);
            if (resp == JOptionPane.YES_OPTION) {
                prov.setIdprov(doc);
                prov.eliminarProveedor();
            }
        }
    }
