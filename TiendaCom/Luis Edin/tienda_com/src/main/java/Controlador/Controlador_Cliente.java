package Controlador;

import Modelo.Modelocliente;
import Vista.Nuevo_Cliente;
import Vista.Principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Controlador_Cliente implements ActionListener {

    Nuevo_Cliente nue = new Nuevo_Cliente();
    Principal prin = new Principal();
    Modelocliente mod = new Modelocliente();
    private int sexo;

    public Controlador_Cliente() {
        nue.getjBGuardarC().addActionListener(this);
        nue.getjBCancelarC().addActionListener(this);
        nue.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        nue.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                ControladorPrincipal princi = new ControladorPrincipal();
                princi.iniciarPrincipal(0);
            }

        });
    }

    public void llenarcamposCliente() {
        //   us.addWindowListener(l);
        prin.setVisible(false);
        nue.setLocationRelativeTo(null);
        nue.setTitle("Nuevo_Cliente");
        nue.setVisible(true);

        nue.getjCSexo().addItem("seleccione");
        Map<String, Integer> dato = mod.llenarCombo("sexo");
        for (String sexo : dato.keySet()) {
            nue.getjCSexo().addItem(sexo);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(nue.getjBGuardarC())) {
            if (nue.getLblDocumento().getText().isEmpty()
                    || nue.getLblNombre().getText().isEmpty()
                    || nue.getLblTelefono().getText().isEmpty()
                    || nue.getTxtCorreo().getText().isEmpty()
                    || nue.getLblDocumento().getText().isEmpty()
                    || nue.getjCSexo().getSelectedItem().equals("seleccione...")
                    || nue.getjDateChooserFECHA().getDate() == null) {
                JOptionPane.showMessageDialog(null, "Falta de informacion");
            }

        } else {
            ControladorPrincipal conpri = new ControladorPrincipal();
            if (conpri.modificadorAccesoCorreo(nue.getTxtCorreo().getText()) == false) {
                JOptionPane.showMessageDialog(null, "Correo invalido");
            } else {
                String valorSexo = nue.getjCSexo().getSelectedItem().toString();
                int sexo = mod.llenarCombo("sexo").get(valorSexo);
            }
//            String valorSexo = nue.getjCSexo().getSelectedItem().toString();
//            int sexo = mod.llenarCombo("sexo").get(valorSexo);

            //seleccion de fecha
            java.util.Date fech = nue.getjDateChooserFECHA().getDate();
            long fe = fech.getTime();
            java.sql.Date fecha = new Date(fe);

            mod.setDoc(Integer.parseInt(nue.getTxtDocumento().getText()));
//                    mod.setTipo_doc(nue.getCmbtipodocu_cli().getSelectedItem().toString());
            mod.setNom(nue.getTxtNombre().getText());
            mod.setTec(nue.getTxtTelefono().getText());
            mod.setCor(nue.getTxtCorreo().getText());
            mod.setDir(nue.getTxtDireccion().getText());
            mod.setSex(sexo);
            mod.setFec(fecha);

            if (nue.getjBGuardarC().getText().equals("Guardar")) {
                mod.llenarnuevoCliente();
                mod.limpiar(nue.getCliente().getComponents());
                nue.dispose();
            } else {
                mod.actualizarCliente();
                nue.setVisible(false);
                nue.dispose();
                        mod.mostrarTablaCliente(prin.getJpanelcliente(), "", "Cliente");
            }
        }

    }
}
