package Controlador;

import Modelo.ModeloUsuario;
import Vista.Nuevo_usuario;
import Vista.Principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.Border;

public class Controlador_usuario implements ActionListener {

    Nuevo_usuario us = new Nuevo_usuario();
    Principal prin = new Principal();
    ModeloUsuario usu = new ModeloUsuario();

    public Controlador_usuario() {
        us.getLblguardar().addActionListener(this);
        us.getjButton2().addActionListener(this);
        us.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        us.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {

                ControladorPrincipal princi = new ControladorPrincipal();
                princi.iniciarPrincipal(0);
            }

        });
    }

    public void llenarcampos() {
        //   us.addWindowListener(l);
//        prin.setVisible(false);
        us.setLocationRelativeTo(null);
        us.setVisible(true);

//llenar sexo combobox sexo 
        us.getjComboBox2().addItem("seleccione");
        Map<String, Integer> dato = usu.llenarCombo("sexo");
        for (String sexo : dato.keySet()) {
            us.getjComboBox2().addItem(sexo);
        }

        us.getjComboBox1().addItem("seleccione");
        Map<String, Integer> dato2 = usu.llenarCombo("rol");
        for (String rol : dato2.keySet()) {
            us.getjComboBox1().addItem(rol);

//            us.getTxtdocumento().addItem("Seleccione...");
//        Map<String, Integer> datoT = usu.llenarCombo("tipodoc");
//        for (String tipo : datoT.keySet()) {
//            us.getJcTipo().addItem(tipo);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(us.getLblguardar())) {
            if (us.getTxtdocumento().getText().isEmpty() || us.getTxtnombre().getText().isEmpty() || us.getTxttelefono().getText().isEmpty() || us.getTxtcorreo().getText().isEmpty() || us.getTxtdireccion().getText().isEmpty() || us.getjComboBox1().getSelectedItem().equals("seleccione...") || us.getjComboBox2().getSelectedItem().equals("seleccione...") || us.getjDateChooser1().getDate() == null || us.getTxtLogin().getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Falta de informacion");

            } else {
//              JOptionPane.showMessageDialog(null, "informacion guardada");
                String valorSexo = us.getCBsexo().getSelectedItem().toString();
                int sexo = usu.llenarCombo("sexo").get(valorSexo);

                String valorRol = us.getCB_rol().getSelectedItem().toString();
                int Rol = usu.llenarCombo("rol").get(valorRol);
                // convierte a formato de fecha que entiende sql
                java.util.Date fec = us.getjDateChooser1().getDate();
                long fe = fec.getTime();
                java.sql.Date fecha = new Date(fe);

                // contraseña
                char[] contra = us.getjPcontra().getPassword();
                String contrasena = String.valueOf(contra);

                usu.setDoc(Integer.parseInt(us.getTxtdocumento().getText()));
                usu.setFec(fecha);
                usu.setNom(us.getTxtnombre().getText());
                usu.setDir(us.getTxtdireccion().getText());
                usu.setTec(us.getTxttelefono().getText());
                usu.setCor(us.getTxtcorreo().getText());
                usu.setLo(us.getTxtLogin().getText());
                usu.setCl(contrasena);
                usu.setSex(sexo);
                usu.setRol(Rol);
                usu.setTip(us.getJcombotipo_doc().getSelectedItem().toString());
                
                
                if (us.getLblguardar().getText().equals("Guardar")) {
                    usu.llenarnuevousuario();
                    usu.Limpiar(us.getjButton1().getComponents());
                    us.dispose();
                
                }else{
                    ControladorPrincipal contpri = new ControladorPrincipal();
                    if (contpri.nuevo_ingreso(us.getTxtcorreo().getText()));
                    
//                    JOptionPane.showInputDialog(null, "error", contraseña);
                    
                    usu.actualizarUsuario();
                    us.setVisible(false);
                    us.dispose();
                }
            }
        }
    }
    

    void actualizarUsuario(int doc) {
        usu.BuscarUsuario(doc);
        us.getTxtdocumento().setEnabled(false);
        us.getTxtLogin().setEnabled(false);
        us.getjComboBox1().setEnabled(false);
        us.getTxtdocumento().setText(String.valueOf(doc));
        us.getTxtcorreo().setText(usu.getCor());
        us.getTxtnombre().setText(usu.getNom());
        us.getTxtdireccion().setText(usu.getDir());
        us.getTxttelefono().setText(usu.getTec());
        us.getTxtLogin().setText(usu.getLo());
        us.getjPcontra().setText(usu.getCl());
        us.getjDateChooser1().setDate(usu.getFec());

        Map<String, Integer> dato = usu.llenarCombo("sexo");
        for (String sexo : dato.keySet()) {
            us.getjComboBox2().addItem(sexo);
        }
            String valorSexo = usu.obtenerSeleccion(dato, usu.getSex());
            us.getjComboBox2().setSelectedItem(valorSexo);

            Map<String, Integer> datos = usu.llenarCombo("rol");
            for (String Rol : datos.keySet()) {
                us.getjComboBox1().addItem(Rol);
            }
            String valorRol = usu.obtenerSeleccion(dato, usu.getRol());
            us.getjComboBox1().setSelectedItem(valorRol);

///            Map<String, Integer> datoT = usu.llenarCombo("tipodoc");
//            for (String Tipo : datoT.keySet()) {
//                us.getjComboBox1().addItem(Tipo);
//            }
//            String valorTipo = usu.obtenerSeleccion(datoT, usu.getTip());
            
            us.getjComboBox1().setSelectedItem(usu.getTip());
            Border borde = BorderFactory.createTitledBorder(null, "Actualizar Usuario",
                    javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION,
                    new java.awt.Font("Verdana", 1, 18), new java.awt.Color(153, 0, 153));
            us.getLblnuevousuario().setBorder(borde);
            prin.setVisible(false);
            us.setLocationRelativeTo(null);
            us.getjButton1().setText("actualizar");
            us.setVisible(true);

        
    }
    

        void eliminarUsuario(int doc) {
        int resp = JOptionPane.showConfirmDialog(null, "¿Desea eliminar al usuario? \n" + doc,
                    "Eliminar Usuario", JOptionPane.YES_OPTION);
            if (resp == JOptionPane.YES_OPTION) {
                usu.setDoc(doc);
                usu.eliminarUsuario();
            }

        }


}



