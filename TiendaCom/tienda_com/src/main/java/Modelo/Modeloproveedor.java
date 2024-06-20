package Modelo;

import Controlador.Conexion;
import Controlador.ControladorPrincipal;
import com.toedter.calendar.JDateChooser;
import java.awt.Component;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

public class Modeloproveedor {

    Conexion cone = new Conexion();
    Connection cn = cone.iniciarConexion();

    private int Idprov, Idsex;
    private String nt,nom, dir, tele, corr, tiperso,Nºdoc;
    private Date fechanaci;

    public Conexion getCone() {
        return cone;
    }

    public void setCone(Conexion cone) {
        this.cone = cone;
    }

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }

    public int getIdprov() {
        return Idprov;
    }

    public void setIdprov(int Idprov) {
        this.Idprov = Idprov;
    }

    public int getIdsex() {
        return Idsex;
    }

    public void setIdsex(int Idsex) {
        this.Idsex = Idsex;
    }

    public String getNt() {
        return nt;
    }

    public void setNt(String nt) {
        this.nt = nt;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public String getCorr() {
        return corr;
    }

    public void setCorr(String corr) {
        this.corr = corr;
    }

    public String getTiperso() {
        return tiperso;
    }

    public void setTiperso(String tiperso) {
        this.tiperso = tiperso;
    }

    public Date getFechanaci() {
        return fechanaci;
    }

    public void setFechanaci(Date fechanaci) {
        this.fechanaci = fechanaci;
    }

    public String getNºdoc() {
        return Nºdoc;
    }

    public void setNºdoc(String Nºdoc) {
        this.Nºdoc = Nºdoc;
    }

   
    

    public Map<String, Integer> llenarCombo(String sexo) {
        String sql = "Select * from mostrar sexo";
        Map<String, Integer> llenar_combo = new HashMap<>();
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                llenar_combo.put(rs.getString(2), rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return llenar_combo;
    }
public void insertarProveedor() {
        Conexion conect = new Conexion();
        Connection co = conect.iniciarConexion();
        String sql = "Call inst_proveedor(?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement ps = co.prepareStatement(sql);
            ps.setInt(1, getIdprov());
            ps.setInt(2, getIdsex());
            ps.setString(3, getNt());
            ps.setString(4, getNom());
            ps.setString(5, getDir());
            ps.setString(6, getTele());
            ps.setString(7, getCorr());
            ps.setString(8, getTiperso());
            ps.setDate(9, getFechanaci());
//            ps.setString(, nt);

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Información Guardada");

            co.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        conect.cerrarConexion();
    }

    public void limpiar(Component[] panelproveedor) {
        for (Object limpiar : panelproveedor) {
            if (limpiar instanceof JTextField) {
                ((JTextField) limpiar).setText("");
            }
            if (limpiar instanceof JComboBox) {
                ((JComboBox) limpiar).setSelectedItem("Seleccione...");
            }
            if (limpiar instanceof JDateChooser) {
                ((JDateChooser) limpiar).setDate(null);
            }
        }
    }
    public void mostrarTablaProveedor(JTable tabla, String valor, String nomPesta) {

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

        String[] titulo = {"Documento", "Idsexo", "Nit", "Nombre", "Direccion", "Telefono", "Correo", "Tipo_persona", "Fechanacimiento"};
        int total = titulo.length;
        if (nomPesta.equals("proveedor")) {
            titulo = Arrays.copyOf(titulo, titulo.length + 2);
            titulo[titulo.length - 2] = "";
            titulo[titulo.length - 1] = "";
        } else {
            titulo = Arrays.copyOf(titulo, titulo.length + 1);
            titulo[titulo.length - 1] = "";
        }

        DefaultTableModel tablaproveedor = new DefaultTableModel(null, titulo) {
            public boolean isCellEditable(int row, int column) {

                return false;

            }
        };

        String sqlproveedor;
        if (valor.equals("")) {
            sqlproveedor = "SELECT * FROM mostrar_proveedor";
        } else {
            sqlproveedor = "call consultar_proveedor('" + valor + "')";
        }
        try {
            String[] dato = new String[titulo.length];
            Statement st = cn.createStatement(); //Crea una consulta
            ResultSet rs = st.executeQuery(sqlproveedor);
            while (rs.next()) {
                for (int i = 0; i < total; i++) {
                    dato[i] = rs.getString(i + 1);
                }
                Object[] fila = {dato[0], dato[1], dato[2], dato[3], dato[4], dato[5], dato[6], dato[7], dato[8], editar, eliminar};
                if (nomPesta.equals("proveedor")) {
                    fila = Arrays.copyOf(fila, fila.length + 2);
                    fila[fila.length - 2] = editar;
                    fila[fila.length - 1] = eliminar;
                } else {
                    fila = Arrays.copyOf(fila, fila.length + 1);
                    fila[fila.length - 1] = agregar;
                }
                tablaproveedor.addRow(fila);
            }
            co.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        tabla.setModel(tablaproveedor);
        //Darle Tamaño a cada Columna
        int cantColum = tabla.getColumnCount();
        int[] ancho = {100, 180, 100, 150, 100, 160, 100, 30, 30};
        if (nomPesta.equals("proveedor")) {
                    ancho = Arrays.copyOf(ancho, ancho.length + 2);
                    ancho[ancho.length - 2] = 25;
                    ancho[ancho.length - 1] = 25;
                } else {
                    ancho = Arrays.copyOf(ancho, ancho.length + 1);
                    ancho[ancho.length - 1] = 25;
                }
        for (int i = 0; i < cantColum; i++) {
            TableColumn columna = tabla.getColumnModel().getColumn(i);
            columna.setPreferredWidth(ancho[i]);
            
        }
        conect.cerrarConexion();
    }

    public void llenarproveedor() throws SQLException {
        Conexion cone = new Conexion();
        Connection cn = cone.iniciarConexion();//instanciamos la conexion
        String sql = "call ins_usuario (?,?,?,?,?,?)";

        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, getIdprov());
            ps.setString(2, getNom());
            ps.setString(3, getTele());
            ps.setString(4, getCorr());
            ps.setString(5, getDir());
            ps.setInt(6, getIdsex());
            ps.executeUpdate();
            JOptionPane.showConfirmDialog(null, "registro finalizado");
            cn.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar", "Error", JOptionPane.ERROR_MESSAGE);

        }
        cone.cerrarConexion();
    }


//Para que al actualizar me muestre el dato que selecciono el proveedor
     public String obtenerSeleccion(Map<String, Integer> info, int valor) {
        for (Map.Entry<String, Integer> seleccion : info.entrySet()) {
            if (seleccion.getValue() == valor) {
                return seleccion.getKey();
            }
        }
        return null;
    }

    //ACTUALIZAR Proveedor
    public void actualizarProveedor(){
        Conexion conect = new Conexion();
        Connection con = conect.iniciarConexion();
        String sql = "call actualizar_proveedor(?,?,?,?,?,?)";

        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, getIdprov());
            ps.setString(2, getNom());
            ps.setString(3, getTele());
            ps.setString(4, getCorr());
            ps.setString(5, getDir());
            ps.setInt(6, getIdsex());
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Información Actualizada");
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        conect.cerrarConexion();
    }
  //ELIMINAR PROVEEDOR
    public void eliminarProveedor() {
        Conexion conect = new Conexion();
        Connection con = conect.iniciarConexion();
        String sql = "call eliminar_proveedor(?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, getIdprov());
            ps.executeUpdate();
            Icon elimina = new ImageIcon(getClass().getResource("/img/basura.png"));
            JOptionPane.showMessageDialog(null, "Registro Eliminado", "Eliminar Usuario", JOptionPane.PLAIN_MESSAGE, (Icon) elimina);
//            JOptionPane.showMessageDialog(null, "¿Desea Eliminar el Registro?");
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        conect.cerrarConexion();
    }
}
