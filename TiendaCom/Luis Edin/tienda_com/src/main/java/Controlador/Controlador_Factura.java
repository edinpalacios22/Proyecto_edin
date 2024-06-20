/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.ModeloUsuario;
import Modelo.Modelo_Factura;
import Vista.Factura;
import Vista.Nuevo_usuario;
import Vista.Principal;
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
public class Controlador_Factura implements ActionListener {

    Factura fact = new Factura();
    Principal prin = new Principal();
    Modelo_Factura modfact = new Modelo_Factura();

    public Controlador_Factura() {
        fact.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        fact.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                ControladorPrincipal princi = new ControladorPrincipal();
                princi.iniciarPrincipal(0);
            }

        });
    }

    public void buscar(String buscar) {
        if (buscar.equals("usuario")) {
            Nuevo_usuario busUsua = new Nuevo_usuario();
            Modelo.ModeloUsuario modUsu = new ModeloUsuario();
            modUsu.mostrarTablaUsuario(prin.getJtusua(), "", buscar);
            System.out.println("factura usuario");
            busUsua.setLocationRelativeTo(null);
            busUsua.setVisible(true);
        }

    }

    public void llenarFactura() {
        //   us.addWindowListener(l);
        prin.setVisible(false);
        fact.setLocationRelativeTo(null);
        fact.setVisible(true);
    }
        @Override
        public void actionPerformed(ActionEvent e) {

          if (e.getSource().equals(prin.getjBtnfactura())) {
                if (fact.getTxtprove().getText().isEmpty() || fact.getjCBpago().getSelectedItem().equals("seleccionar") || fact.getTxtusua().getText().isEmpty() || fact.getTxtDescuento().getText().isEmpty());
                JOptionPane.showMessageDialog(null, "falta de informacion");
            }
        }
    }






































































































































































































































































































































































































































































































































































































































































































































































































































































































































































//package Controlador;
//
//import Modelo.ModeloUsuario;
//import Modelo.Modelo_Factura;
//import Modelo.Modeloproveedor;
//import Modelo.modelo_producto;
////import Vista.Agregar_Detalleproducto;
////import Vista.Buscar;
////import Vista.Buscar_Producto;
//import Vista.Factura;
////import Vista.Mostrar_Detalle_Factura_Compra;
//import Vista.Principal;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;
//import javax.swing.BorderFactory;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JOptionPane;
//import javax.swing.border.Border;
//import javax.swing.event.DocumentEvent;
//import javax.swing.event.DocumentListener;
//
//public class ControladorFacturaCompra implements ActionListener, DocumentListener {
//
//    Factura factnuev = new Factura();
//    Principal prin = new Principal();
////    Buscar buscar = new Buscar();
////    Buscar_Producto buscapro = new Buscar_Producto();
////    Agregar_Detalleproducto detallefact = new Agregar_Detalleproducto();
////    Mostrar_Detalle_Factura_Compra mostradetalle = new Mostrar_Detalle_Factura_Compra();
//    ModeloUsuario modusu = new ModeloUsuario();
//    modelo_producto modpro = new modelo_producto();
//    Modeloproveedor modprove = new Modeloproveedor();
//    Modelo_Factura modfac = new Modelo_Factura();
//
//    public ControladorFactura() {
//        factnuev.getBtnguardarfactcomp().addActionListener(this);
//        factnuev.getBtncancelarfactcomp().addActionListener(this);
//        factnuev.getBtnbuscarproveedor().addActionListener(this);
//        factnuev.getBtnbuscarusuario().addActionListener(this);
//        buscar.getTxtbuscar().getDocument().addDocumentListener(this);
//        detallefact.getBtnbuscarproduct().addActionListener(this);
//        buscapro.getTxtbuscarproducto().getDocument().addDocumentListener(this);
//        detallefact.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// para que cuando se cierre se quede en la principal
//        mostradetalle.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        factnuev.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//Desactiva la x que cierra el programa para que permita abrir o volcer a la ventana principal
//        buscar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        buscapro.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        factnuev.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosed(WindowEvent e) {
//                ControladorPrincipal princ = new ControladorPrincipal();
//                princ.iniciarPrincipal(4);
//            }
//        });
//
//        buscar.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosed(WindowEvent e) {
//                factnuev.setVisible(true);
//                buscar.setVisible(false);
//            }
//        });
//
//        detallefact.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosed(WindowEvent e) {
//                factnuev.dispose();
//            }
//        });
//
//        buscapro.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosed(WindowEvent e) {
//                detallefact.setVisible(true);
//            }
//        });
//    }
//
////    public void agregarDetalle(int fac) {
////        detallefact.setVisible(true);
////        detallefact.setLocationRelativeTo(null);
////        detallefact.setTitle("Agregar Detalle");
////        detallefact.getTxtnumerofactura().setText(String.valueOf(fac));
////    }
//
//    public void controlFacturaCompra() {
//        prin.setVisible(false);
//        factnuev.setLocationRelativeTo(null);
//        factnuev.setTitle("Nueva Factura Compra");
//        factnuev.setVisible(true);
//        buscar();
//    }
//
////    public void buscar() {
////        buscar.getJTablaBuscarusuario().addMouseListener(new MouseAdapter() {
////            @Override
////            public void mouseClicked(MouseEvent e) {
////                int fila = buscar.getJTablaBuscarusuario().rowAtPoint(e.getPoint());
////                int colum = buscar.getJTablaBuscarusuario().columnAtPoint(e.getPoint());
////
////                if (buscar.getLblTitulo().getText().equals("Usuario")) {
////                    modusu.setDoc(Integer.parseInt(buscar.getJTablaBuscarusuario().getValueAt(fila, 0).toString()));
////                    if (colum == 9) {
////                        buscar.setVisible(false);
////                        factnuev.setVisible(true);
////                        Object idusua = modusu.getDoc();
////                        factnuev.getTxtidentiusufactcomp().setText(idusua.toString());
////                        JOptionPane.showMessageDialog(null, "Usuario Agregado");
////                    }
////                } else {
////                    modprovee.setDoc(Integer.parseInt(buscar.getJTablaBuscarusuario().getValueAt(fila, 0).toString()));
////                    if (colum == 9) {
////                        buscar.setVisible(false);
////                        factnuev.setVisible(true);
////                        Object idprovee = modprovee.getDoc();
////                        factnuev.getTxtidentiprovefact().setText(idprovee.toString());
////                        JOptionPane.showMessageDialog(null, "Proveedor Agregado");
////                    }
////                }
////            }
////        });
////        buscar.getTxtbuscar().addMouseListener(new MouseAdapter() {
////            @Override
////            public void mouseClicked(MouseEvent e) {
////                buscar.getTxtbuscar().setText("");
////                buscar.getTxtbuscar().setForeground(new java.awt.Color(0, 0, 0));
////            }
////        });
////    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (e.getSource().equals(detallefact.getBtnbuscarproduct())) {
//            //configuración del boton por codigo
//            JButton agregar = new JButton("Añadir");
//            agregar.setForeground(new java.awt.Color(0, 153, 153));
//            agregar.setFont(new java.awt.Font("Yu Gothic UI", 1, 18));
//            agregar.setBounds(800, 100, 110, 35);
//            buscapro.getJPanelbucarproducto().add(agregar);
//            agregar.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    modfactnuev.agregarProductos(buscapro.getJTablaBuscarproducto(), detallefact.getJTablaagragarproducto());
//                    buscapro.setVisible(false);
//                }
//            });
//            buscapro.setVisible(true);
//            buscapro.setLocationRelativeTo(null);
//            modproduc.mostrarTablaProducto(buscapro.getJTablaBuscarproducto(), "", "Produ");
////Activar el TXTbuscar producto
//            buscapro.getTxtbuscarproducto().addMouseListener(new MouseAdapter() {
//                @Override
//                public void mouseClicked(MouseEvent e) {
//                    buscapro.getTxtbuscarproducto().setText("");
//                    buscapro.getTxtbuscarproducto().setForeground(new java.awt.Color(0, 0, 0));
//                }
//            });
////boton agregar en la caja de texto el producto             
//            buscapro.getJTablaBuscarproducto().addMouseListener(new MouseAdapter() {
//                @Override
//                public void mouseClicked(MouseEvent e) {
//                    int fila = buscapro.getJTablaBuscarproducto().rowAtPoint(e.getPoint());
//
////Activar el boton de agregar producto
//                    modproduc.setDoc(Integer.parseInt(buscapro.getJTablaBuscarproducto().getValueAt(fila, 0).toString()));
//                }
//            });
//        }
//        if (e.getSource().equals(factnuev.getBtnbuscarusuario())) {
//            buscar.getLblTitulo().setText("Usuario");
//            factnuev.setVisible(false);
//            buscar.setLocationRelativeTo(null);
//            modusu.mostrarTablaUsuario(buscar.getJTablaBuscarusuario(), "", "Nueva Factura");
//            buscar.setVisible(true);
//            Border borde = BorderFactory.createTitledBorder(null, "Buscar Usuario",
//                    javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION,
//                    new java.awt.Font("Yu Gothic UI", 1, 36),
//                    new java.awt.Color(204, 0, 204));
//            buscar.getJPanelbucarusuario().setBorder(borde);
//        }
//
//        if (e.getSource().equals(factnuev.getBtnbuscarproveedor())) {
//            buscar.getLblTitulo().setText("Proveedor");
//            factnuev.setVisible(false);
//            buscar.setLocationRelativeTo(null);
//            modprovee.mostrarTablaProveedor(buscar.getJTablaBuscarusuario(), "", "Nueva Factura");
//            buscar.setVisible(true);
//            Border borde = BorderFactory.createTitledBorder(null, "Buscar Proveedor",
//                    javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION,
//                    new java.awt.Font("Yu Gothic UI", 1, 36),
//                    new java.awt.Color(204, 0, 204));
//            buscar.getJPanelbucarusuario().setBorder(borde);
//        }
//
//        if (e.getSource().equals(factnuev.getBtnguardarfactcomp())) {
//            //validar campos vacios
//            if ((factnuev.getTxtidentiprovefact().getText().isEmpty()) || (factnuev.getTxtidentiusufactcomp().getText().isEmpty()) || (factnuev.getTxtnumerodecomprobante().getText().isEmpty())
//                    || (factnuev.getCmbtipopagofactcompa().getSelectedItem().equals("Seleccione..."))) {
//                JOptionPane.showMessageDialog(null, "Debe ingresar información en los campos de Nombre y Descripción");
//            } else {
//                modfactnuev.setDocprovee(Integer.parseInt(factnuev.getTxtidentiprovefact().getText()));
//                modfactnuev.setDocusu((Integer.parseInt(factnuev.getTxtidentiusufactcomp().getText())));
//                modfactnuev.setCompro(Integer.parseInt(factnuev.getTxtnumerodecomprobante().getText()));
//                modfactnuev.setTipo_pag(factnuev.getCmbtipopagofactcompa().getSelectedItem().toString());
//
//                if (factnuev.getBtnguardarfactcomp().getText().equals("Guardar")) {
//                    modfactnuev.insertarFactcompra();
//                    modfactnuev.limpiar(factnuev.getPanelFacturacompra().getComponents());
//                    factnuev.setVisible(false);
//                    factnuev.dispose();
//                } else {
//                    modfactnuev.actualizarFactcompra();
//                    factnuev.setVisible(false);
//                    factnuev.dispose();
//                    modfactnuev.mostrarTablaFactCompra(prin.getTablafactura(), "", "Factura");
//                }
//            }
//        }
//
//        if (e.getSource().equals(factnuev.getBtncancelarfactcomp())) {
//            factnuev.dispose();
//        }
//    }
//
//    //Actualizar factura compra
//    void actualizarFactcompra(int doc) {
//        modfactnuev.buscarFactcompra(doc);
//        factnuev.getTxtnumerodecomprobante().setEnabled(false);
//        factnuev.getTxtidentiprovefact().setText(String.valueOf(modfactnuev.getDocprovee()));
//        factnuev.getTxtidentiusufactcomp().setText(String.valueOf(modfactnuev.getDocusu()));
//        factnuev.getTxtnumerodecomprobante().setText(String.valueOf(modfactnuev.getCompro()));
//
//        //Llenar tipo de pago
//        factnuev.getCmbtipopagofactcompa().setSelectedItem(modfactnuev.getTipo_pag());
//
//        //Cambiar Titulo
//        Border borde = BorderFactory.createTitledBorder(null, "Actualizar Factura Compra",
//                javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION,
//                new java.awt.Font("Yu Gothic UI", 1, 36),
//                new java.awt.Color(204, 0, 204));
//        factnuev.getPanelFacturacompra().setBorder(borde);
//        prin.setVisible(false);
//        factnuev.setLocationRelativeTo(null);
//        factnuev.getBtnguardarfactcomp().setText("Actualizar");
//        factnuev.setVisible(true);
//    }
//
//    @Override
//    public void insertUpdate(DocumentEvent e) {
//        if (buscar.getLblTitulo().getText().equals("Usuario")) {
//            modusu.mostrarTablaUsuario(buscar.getJTablaBuscarusuario(), buscar.getTxtbuscar().getText(), "Nueva Factura");
//        } else {
//            modprovee.mostrarTablaProveedor(buscar.getJTablaBuscarusuario(), buscar.getTxtbuscar().getText(), "Nueva Factura");
//        }
//        modproduc.mostrarTablaProducto(buscapro.getJTablaBuscarproducto(), buscapro.getTxtbuscarproducto().getText(), "Produ");
//    }
//
//    @Override
//    public void removeUpdate(DocumentEvent e) {
//        if (buscar.getLblTitulo().getText().equals("Usuario")) {
//            modusu.mostrarTablaUsuario(buscar.getJTablaBuscarusuario(), buscar.getTxtbuscar().getText(), "Nueva Factura");
//        } else {
//            modprovee.mostrarTablaProveedor(buscar.getJTablaBuscarusuario(), buscar.getTxtbuscar().getText(), "Nueva Factura");
//        }
//        modproduc.mostrarTablaProducto(buscapro.getJTablaBuscarproducto(), buscapro.getTxtbuscarproducto().getText(), "Produ");
//    }
//
//    @Override
//    public void changedUpdate(DocumentEvent e) {
//        if (buscar.getLblTitulo().getText().equals("Usuario")) {
//            modusu.mostrarTablaUsuario(buscar.getJTablaBuscarusuario(), buscar.getTxtbuscar().getText(), "Nueva Factura");
//        } else {
//            modprovee.mostrarTablaProveedor(buscar.getJTablaBuscarusuario(), buscar.getTxtbuscar().getText(), "Nueva Factura");
//        }
//        modproduc.mostrarTablaProducto(buscapro.getJTablaBuscarproducto(), buscapro.getTxtbuscarproducto().getText(), "Produ");
//    }
//
//}
//
