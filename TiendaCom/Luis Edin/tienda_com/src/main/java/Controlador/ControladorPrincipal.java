package Controlador;

// import Modelo.ModeloUsuario;
import Modelo.ModeloUsuario;
import Modelo.Modelo_Factura;
import Modelo.Modelo_venta;
import Modelo.Modelocliente;
import Modelo.Modeloproveedor;
import Modelo.modelo_producto;
import Vista.Factura;
import Vista.Nuevo_Cliente;
import Vista.Nuevo_usuario;
import Vista.Proveedor;
import Vista.Principal;
import Vista.Producto;
import Vista.Ventas;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
//import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author SENA
 */
public class ControladorPrincipal implements ActionListener, ChangeListener, DocumentListener {

    ModeloUsuario modusu = new ModeloUsuario();
    Proveedor nuev = new Proveedor();
    Nuevo_Cliente nue = new Nuevo_Cliente();
    Principal prin = new Principal();
    Nuevo_usuario us = new Nuevo_usuario();
    modelo_producto modpro = new modelo_producto();
    Modelocliente modclien = new Modelocliente();
    Modeloproveedor modprove = new Modeloproveedor();
    Factura fac = new Factura();
    Modelo_Factura modfac = new Modelo_Factura();
    Ventas ven = new Ventas();
    Modelo_venta modvent = new Modelo_venta();
    Producto prod = new Producto();

    public ControladorPrincipal() {
        prin.getLblnuevo().addActionListener(this);
        prin.getLblNuevo().addActionListener(this);
        prin.getjBproveedorp().addActionListener(this);
        prin.getBtnimg().addActionListener(this);
        prin.getBtnguardar().addActionListener(this);
        prin.getLblNuevo2().addActionListener(this);
        prin.getjBproveedorp().addActionListener(this);
        prin.getjBtnfactura().addActionListener(this);
        prin.getjBtnVentas().addActionListener(this);
        prin.getjBtnproducto().addActionListener(this);

        prin.getJdprincipal().addChangeListener(this);

        prin.getTXTbuscar().getDocument().addDocumentListener(this);

        us.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        nue.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        nuev.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        fac.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ven.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        prod.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        prin.getJdprincipal().addChangeListener(this);
    }

    public void iniciarPrincipal(int valor) {
        prin.setLocationRelativeTo(null);
        prin.setTitle("Principal");
        prin.setVisible(true);
        prin.setTitle("principal panel| ventana");
        prin.setExtendedState(JFrame.MAXIMIZED_BOTH);
        prin.getJdprincipal().setSelectedIndex(valor);
        prin.setVisible(true);
        gestionUsuario();
    }

    public void gestionpanel() {
        if (prin.getJdprincipal().getSelectedIndex() == 0) {
            gestionUsuario();
        }

        if (prin.getJdprincipal().getSelectedIndex() == 3) {
            gestionFactura();

        }

        if (prin.getJdprincipal().getSelectedIndex() == 5) {
            gestionProducto();
        }
        if (prin.getJdprincipal().getSelectedIndex() == 4) {
            gestionVenta();
        }
    }

    public boolean nuevo_ingreso(String correo) {
        String valor = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+.[A-Z|a-z]{2,}$";
        Pattern validar = Pattern.compile(valor);
        Matcher cor = validar.matcher(correo);

        return cor.matches();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(prin.getLblnuevo())) {
            Controlador_usuario con = new Controlador_usuario();
            con.llenarcampos();
        }
        if (e.getSource().equals(prin.getLblNuevo())) {
            Controlador_Cliente con = new Controlador_Cliente();
            con.llenarcamposCliente();

        }
        if (e.getSource().equals(prin.getjBproveedorp())) {
            Controlador_proveedor con = new Controlador_proveedor();
            con.llenarproveedor();

        }

        if (e.getSource().equals(prin.getjBtnfactura())) {
            Controlador_Factura con = new Controlador_Factura();
            con.llenarFactura();
        }
        
        if (e.getSource().equals(prin.getjBtnproducto())) {
            Controlador_producto con = new Controlador_producto();
            con.controlProducto();
        }
        
        if (e.getSource().equals(prin.getjBtnVentas())) {
            Controlador_Ventas con = new Controlador_Ventas();
            con.llenarVentas();
        }
        if (e.getSource().equals(prin.getLblnuevo())) {

        }
        if (e.getSource().equals(prin.getBtnimg())) {
            modpro.buscarImagen();
            File file = new File(modpro.getRuta());
            String archivo = file.getName();
            prin.getTxtimg().setText(archivo);
        }
        if (e.getSource().equals(prin.getjBtnproducto())) {
         
        }
//        if (e.getSource().equals(prin.getBtnimg())) {
//            modpro.setNom(prin.getTxtimg().getText());
//            modpro.setDes(prin.getTXTbuscar().getText());
//            modpro.setImagen(modpro.convertirImagen(modpro.getRuta()));
//        }
    }

    public void gestionUsuario() {
        modusu.mostrarTablaUsuario(prin.getJtusua(), "", "usuario");
        ModeloUsuario modUsu = new ModeloUsuario();
        prin.getTXTbuscar().addMouseListener(new MouseAdapter() {
//            public void MouseClickd(MouseAdapter e) {                
            @Override
            public void mouseClicked(MouseEvent e) {
                prin.getTXTbuscar().setText("");
                prin.getTXTbuscar().setForeground(Color.BLACK);

            }

        });
        prin.getJtusua().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = prin.getJtusua().rowAtPoint(e.getPoint());
//                int columna = prin.getJtusua().columnAtPoint(e.getPoint());
                modUsu.setDoc(Integer.parseInt((String) prin.getJtusua().getValueAt(fila, 1).toString()));
//                if (columna == 9) {
//                    prin.setVisible(false);
////                    us.
//                }

            }
        });

    }

    public void gestioncliente() {
        modclien.mostrarTablaCliente(prin.getJpanelcliente(), "", "cliente");
    }

    public void gestionproveedor() {

        modprove.mostrarTablaProveedor(prin.getJpanelproveedor(), "", "proveedor");
    }

    public void gestionfactura() {
        modfac.mostrarTablafactura(prin.getjTablefactura(), "", "factura");
    }

    public void gestionVenta() {
        modvent.mostrarTablaventa(prin.getJpanelventa(), "", "venta");

    }
    
    public void gestionProducto() {
        modpro.mostrartablaproducto(prin.getJpanel_prod(), "", "producto");
    }
               
    public void limpiar(Component[] panel) {
        for (Object control : panel) {
            if (control instanceof JTextField) {
                ((JTextField) control).setText("");
            }
            if (control instanceof JScrollPane) {
                Component[] limpio = ((JScrollPane) control).getViewport().getComponents();
                for (Object controltext : limpio) {
                    if (controltext instanceof JTextArea) {
                        ((JTextArea) control).setText("");
                    }
                }
            }

        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        int seleccion = prin.getJdprincipal().getSelectedIndex();

        if (seleccion == 1) {
            gestioncliente();
        }
        if (seleccion == 3) {
            gestionfactura();
        }

        if (seleccion == 2) {
            gestionproveedor();
        }

        if (seleccion == 4) {
            gestionVenta();
        }
        
        if (seleccion == 5) {
           gestionProducto();
    }

        ModeloUsuario modUsu = new ModeloUsuario();
        modUsu.mostrarTablaUsuario(prin.getJtusua(), "", "usuario");
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        modusu.mostrarTablaUsuario(prin.getJtusua(), prin.getTXTbuscar().getText(), "usuario");
        modclien.mostrarTablaCliente(prin.getJpanelcliente(), "", "cliente");
        modprove.mostrarTablaProveedor(prin.getJpanelproveedor(), "", "proveedor");
        modfac.mostrarTablafactura(prin.getjTablefactura(), "", "factura");
        modvent.mostrarTablaventa(prin.getJpanelventa(), "", "venta");
        modpro.mostrartablaproducto(prin.getJpanel_prod(), "", "producto");
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        modusu.mostrarTablaUsuario(prin.getJtusua(), prin.getTXTbuscar().getText(), "usuario");

    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        modusu.mostrarTablaUsuario(prin.getJtusua(), prin.getTXTbuscar().getText(), "usuario");
    }

    private void gestionFactura() {
    }

    boolean modificadorAccesoCorreo(String text) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
