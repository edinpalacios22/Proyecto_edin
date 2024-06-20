
package Modelo;

import Controlador.Conexion;
//import Modelo.GestionCeldas;
//import Modelo.Gestion_Encabezado;
import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

/**
 *
 * @author SENA
 */
public class modelo_producto {

    private float prec;
    private int can;
    private String nom, des, ruta;
    private byte imagen[];

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public float getPrec() {
        return prec;
    }

    public void setPrec(float prec) {
        this.prec = prec;
    }

    public int getCan() {
        return can;
    }

    public void setCan(int can) {
        this.can = can;
    }

    public void buscarImagen() {
        JFileChooser archivos = new JFileChooser();
////        String ruta_carpeta = getClass().getClassLoader().getResource("Producto").getPath();
////        File carpeta = new File(ruta_carpeta);
////        archivos.setCurrentDirectory(carpeta);
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("jpg,png & gif", "jpg", "png", "gif");
        archivos.setFileFilter(filtro);

        if (archivos.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            setRuta(archivos.getSelectedFile().getAbsolutePath());
        }
    }

    public byte[] convertirImagen(String ruta) {
        try {
            File archivo = new File(ruta);
            byte[] foto = new byte[(int) archivo.length()];
            InputStream imgen = new FileInputStream(archivo);
            imgen.read(foto);

            return foto;

        } catch (Exception e) {
            return null;
        }
    }

    public void insertarProducto() {
        Conexion conect = new Conexion();
        Connection co = conect.iniciarConexion();
        String instProducto = "call inst_producto(?,?,?,?)";
        try {
            PreparedStatement ps = co.prepareStatement(instProducto);
            ps.setString(1, getNom());
            ps.setString(2, getDes());
            ps.setBytes(3, getImagen());
            ps.setString(4, getRuta());
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Registro Guardado");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void limpiar(Component[] paneljtusua) {
        for (Object limpiar : paneljtusua) {
            if (limpiar instanceof JTextField) {
                ((JTextField) limpiar).setText("");
            }
            if (limpiar instanceof JScrollPane) {
                Component[] limpio = ((JScrollPane) limpiar).getViewport().getComponents();
                for (Object controltext : limpio) {
                    if (controltext instanceof JTextArea) {
                        ((JTextArea) controltext).setText("");
                    }
                }
            }
        }
    }

    public void mostrartablaproducto(JTable tabla, String valor, String nompesta) {
        Conexion conect = new Conexion();
        Connection co = conect.iniciarConexion();

        //Personalizar Emcabezado
        JTableHeader encabeza = tabla.getTableHeader();
        encabeza.setDefaultRenderer(new Gestion_Encabezado());
        tabla.setTableHeader(encabeza);

        //Personalizar Celdas
        tabla.setDefaultRenderer(Object.class, new GestionCeldas());
        JButton editar = new JButton("Editar");
        JButton eliminar = new JButton("Eliminar");
        JButton agregar = new JButton("Agregar");

        editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/lapiz.png")));
        eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eliminar.png")));
        agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/agregar-usuario.png")));

        String[] titulo = {"idProducto", "Nombre", "Descripcion", "Cantidad", "Imagen", "Precio"};

        int opcion = titulo.length;

        if (nompesta.equals("producto")) {
            titulo = Arrays.copyOf(titulo, titulo.length + 2);
            titulo[titulo.length - 2] = "";
            titulo[titulo.length - 1] = "";
        } else {
            titulo = Arrays.copyOf(titulo, titulo.length + 1);
            titulo[titulo.length - 1] = "";
        }

        DefaultTableModel tablaproducto = new DefaultTableModel(null, titulo) {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (!nompesta.equals("producto")) {
                    if (column == 6) {
                        return true;
                    }
                }
                return false;

            }
        };

        String sqlproducto;
        if (valor.equals("")) {
            sqlproducto = "SELECT * FROM mostrar_producto";
        } else {
            sqlproducto = "call consultar_producto('" + valor + "')";
        }
        TableModel tablaProducto;
        try {
            String[] dato = new String[titulo.length];
//            Object dato[] = new Object[opcion];
            
            Statement st = co.createStatement(); //Crea una consulta
            ResultSet rs = st.executeQuery(sqlproducto);

            while (rs.next()) {
                try {
                    byte[] imag = rs.getBytes(2);
                    BufferedImage bufIm = null;
                    InputStream inSt = new ByteArrayInputStream(imag);
                    bufIm = ImageIO.read(inSt);
                    ImageIcon icono = new ImageIcon(bufIm.getScaledInstance(64, 64, 0));
//                    dato[1] = new JLabel(icono); 

                } catch (Exception e) {
//                    dato[1] = new JLabel("no imagenes");
                }
                dato[0] = rs.getString(1);
                dato[2] = rs.getString(3);
                dato[3] = rs.getString(4);
////                dato[4] = rs.getInt(5);
//                dato[5] = rs.getInt(6);
                
                for (int i = 0; i < opcion; i++) {
                    dato[i] = rs.getString(i + 1);
                }
                Object[] fila = {dato[0], dato[1], dato[2], dato[3], dato[4], dato[5]};
                if (nompesta.equals("producto")) {
                    fila = Arrays.copyOf(fila, fila.length + 2);
                    fila[fila.length - 2] = editar;
                    fila[fila.length - 1] = eliminar;
                } else {
                    fila = Arrays.copyOf(fila, fila.length + 1);
                    fila[fila.length - 1] = false;
                }
                tablaproducto.addRow(fila);
            }
            co.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        tabla.setModel(tablaproducto);
        //Darle Tamaño a cada Columna
        int numColum = tabla.getColumnCount();
        if (!nompesta.equals("producto")) {
           int colum = numColum-1;
           TableColumn columna = tabla.getColumnModel().getColumn(colum);
           columna.setCellEditor(tabla.getDefaultEditor(Boolean.class));
           columna.setCellRenderer(tabla.getDefaultRenderer(Boolean.class));
        }
           int[] tamanos;
           
           if (nompesta.equals("producto")) {
              tamanos = new int[]{50,300,250,300,100,100};
              tamanos = Arrays.copyOf(tamanos, tamanos.length + 2);
              tamanos[tamanos.length - 2] = 15; 
        
//        int colum = numColum-1;
//        TableColumn columna = tabla.getColumnModel().getColumn(colum);
//        int[] ancho = {100, 180, 100, 150, 100, 160, 100, 150, 30, 30};
//        for (int i = 0; i < numColum; i++) {
//            TableColumn columna = tabla.getColumnModel().getColumn(i);
//            columna.setPreferredWidth(ancho[i]);
        }
        conect.cerrarConexion();
    }

    public void buscarproducto(int valor) {
        Conexion cone = new Conexion();
        Connection cn = cone.iniciarConexion();//instanciamos la conexion
        String sql = "call ins_producto (?,?,?,?,?,?)";

        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            
            ps.setString(1, getNom());
            ps.setString(2, getDes());
            ps.setBytes(3, getImagen());
            ps.setString(4, getRuta());
            ps.executeUpdate();
            JOptionPane.showConfirmDialog(null, "registro finalizado");
            cn.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar", "Error", JOptionPane.ERROR_MESSAGE);

        }
        cone.cerrarConexion();
    }

    public void actualizarProducto() {
        Conexion conect = new Conexion();
        Connection cn = conect.iniciarConexion();
        String sql = "call actualizar_producto(?,?,?,?,?)";

        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, getNom());
            ps.setString(2, getDes());
            ps.setBytes(3, getImagen());
            ps.setInt(4, getCan());
            ps.setFloat(5, getPrec());
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Información Actualizada");
            cn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        conect.cerrarConexion();
    }

    public void eliminarProducto() {
        Conexion conect = new Conexion();
        Connection con = conect.iniciarConexion();
        String sql = "call eliminar_producto(?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.executeUpdate();
            Icon elimina = new ImageIcon(getClass().getResource("/img/basura.png"));
            JOptionPane.showMessageDialog(null, "Registro Eliminado", "Eliminar Usuario", JOptionPane.PLAIN_MESSAGE, (Icon) elimina);
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        conect.cerrarConexion();
    }
}
