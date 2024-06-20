package Modelo;

import Controlador.Conexion;
import com.toedter.calendar.JDateChooser;
import java.awt.Component;
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

public class ModeloUsuario {

    Conexion cone = new Conexion();
    Connection cn = cone.iniciarConexion();

    private int doc, sex, rol;
    private String nom, dir, tec, cor, lo, cl, tip;
    private Date fec;

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

    public int getDoc() {
        return doc;
    }

    public void setDoc(int doc) {
        this.doc = doc;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
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

    public String getTec() {
        return tec;
    }

    public void setTec(String tec) {
        this.tec = tec;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getLo() {
        return lo;
    }

    public void setLo(String lo) {
        this.lo = lo;
    }

    public String getCl() {
        return cl;
    }

    public void setCl(String cl) {
        this.cl = cl;
    }

    public Date getFec() {
        return fec;
    }

    public void setFec(Date fec) {
        this.fec = fec;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public Map<String, Integer> llenarCombo(String valor) {

        Conexion cone = new Conexion();
        Connection cn = cone.iniciarConexion();

        String sql = "Select * from mostrar_" + valor;

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

    public void llenarnuevousuario() {
        Conexion cone = new Conexion();
        Connection cn = cone.iniciarConexion();//instanciamos la conexion
        String sql = "call ins_usuario (?,?,?,?,?,?,?,?,?,?,?)";
        try {

            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, getDoc());
            ps.setString(4, getNom());
            ps.setString(5, getTec());
            ps.setString(6, getCor());
            ps.setString(7, getDir());
            ps.setDate(10, getFec());
            ps.setInt(3, getSex());
            ps.setInt(2, getRol());
            ps.setString(8, getLo());
            ps.setString(9, getCl());
            ps.setString(11, getTip());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null,"Registro almacenado");
            cn.close();

        } catch (SQLException e) {
            e.printStackTrace();
//            e.printStackTrace();

        }
        cone.cerrarConexion();
    }

    public void Limpiar(Component[] panel) {
        for (Object control : panel) {
            if (control instanceof JTextField) {
                ((JTextField) control).setText("");

                if (control instanceof JComboBox) {
                    ((JComboBox) control).setSelectedItem("seleccione...");
                }
                if (control instanceof JDateChooser) {
                    ((JDateChooser) control).setDate(null);

                }
            }
        }
    }

    public void mostrarTablaUsuario(JTable tabla, String valor, String nomPesta) {

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
//        JButton agregar = new JButton("Agregar");

        editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/lapiz.png")));
        eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eliminar.png")));
//        agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/agregar-usuario.png")));

        String[] titulo = {"Documento", "Tipo de Documento", "Rol", "Genero","Nombre","Direccion","Telefono", "Correo", "Fecha de Nacimiento"};
        int total = titulo.length;
        if (nomPesta.equals("usuario")) {
            titulo = Arrays.copyOf(titulo, titulo.length + 2);
            titulo[titulo.length - 2] = "";
            titulo[titulo.length - 1] = "";
        } else {
            titulo = Arrays.copyOf(titulo, titulo.length + 1);
            titulo[titulo.length - 1] = "";
        }

        DefaultTableModel tablaUsuario = new DefaultTableModel(null, titulo) {
            public boolean isCellEditable(int row, int column) {

                return false;

            }
        };
        String sqlUsuario;
        if (valor.equals("")) {
            sqlUsuario = "SELECT * FROM mostrarusuario";
        } else {
            sqlUsuario = "call consultar_usuario('" + valor + "')";
        }
        try {
            String[] dato = new String[titulo.length];
            Statement st = cn.createStatement(); //Crea una consulta
            ResultSet rs = st.executeQuery(sqlUsuario);
            while (rs.next()) {
                for (int i = 0; i < total; i++) {
                    dato[i] = rs.getString(i + 1);
                }
                Object[] fila = {dato[0], dato[1], dato[2], dato[3], dato[4], dato[5], dato[6], dato[7], dato[8], editar, eliminar};
                if (nomPesta.equals("usuario")) {
                    fila = Arrays.copyOf(fila, fila.length + 2);
                    fila[fila.length - 2] = editar;
                    fila[fila.length - 1] = eliminar;
                } else {
                    fila = Arrays.copyOf(fila, fila.length + 1);
//                    fila[fila.length - 1] = agregar;
                }
                tablaUsuario.addRow(fila);
            }
            co.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        tabla.setModel(tablaUsuario);
        //Darle Tamaño a cada Columna
        int cantColum = tabla.getColumnCount();
        int[] ancho = {100, 180, 100, 150, 100, 160, 100, 190, 22, 22,22};
        for (int i = 0; i < cantColum; i++) {
            TableColumn columna = tabla.getColumnModel().getColumn(i);
            columna.setPreferredWidth(ancho[i]);
        }
        conect.cerrarConexion();
    }

    public void BuscarUsuario(int valor) {
        Conexion cone = new Conexion();
        Connection cn = cone.iniciarConexion();
        String sql = "call_mostrarusuario(" + valor + ")";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                setDoc(rs.getInt(1));
                setTec(rs.getNString(2));
                setNom(rs.getString(3));
                setTec(rs.getString(4));
                setCor(rs.getString(5));
                setDir(rs.getString(6));
                setFec(rs.getDate(7));
                setSex(rs.getInt(8));
                setRol(rs.getInt(9));
                setLo(rs.getString(10));
                setCl(rs.getString(11));

            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    /**
     *
     * @param dato
     * @param valor
     * @return
     */
    public String obtenerSeleccion(Map<String, Integer> dato, int valor) {
        for (Map.Entry<String, Integer> seleccion : dato.entrySet()) {
            if (seleccion.getValue() == valor) {
                return seleccion.getKey();
            }
        }
        return null;
    }
    public void actualizarUsuario() {
        Conexion conect = new Conexion();
        Connection cn = conect.iniciarConexion();
        String sql = "call actualizar_Usuario(?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, getDoc());
            ps.setString(2, getNom());
            ps.setInt(3, getRol());
            ps.setString(4, getTec());
            ps.setString(5, getCor());
            ps.setInt(6, getSex());
            ps.setString(7, getDir());
            ps.setDate(8, getFec());
            ps.setString(9, getCl());
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Información Actualizada");
            cn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        conect.cerrarConexion();
    }
    public void agregarBoton(String nomPesta, String[] objeto, Object valor, Object valor2, Object valor3) {

        if (nomPesta.equals("usuario")) {
            System.out.println(nomPesta);

            objeto = Arrays.copyOf(objeto, objeto.length + 2);

            objeto[objeto.length - 2] = String.valueOf(valor);
            objeto[objeto.length - 1] = String.valueOf(valor2);
            System.out.println(objeto.length);

        } else {
            objeto = Arrays.copyOf(objeto, objeto.length + 1);
            objeto[objeto.length - 1] = String.valueOf(valor3);
        }

    }
    public void eliminarUsuario() {
        Conexion con = new Conexion();
        Connection cn = con.iniciarConexion();

        String eliUsuario = "Call usuario_elim(?)";
        try {
            PreparedStatement ps = cn.prepareStatement(eliUsuario);
            ps.setInt(1, getDoc());
            ps.executeUpdate();
            Icon eliminar = new ImageIcon(getClass().getResource("/img/eliminar.png"));
            JOptionPane.showMessageDialog(null, "Registro Eliminado", "Eliminar Usuario", JOptionPane.PLAIN_MESSAGE, (Icon) eliminar);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
















































//package Modelo;
//
//import Controlador.Conexion;
//import com.toedter.calendar.JDateChooser;
//import java.awt.Component;
//import java.sql.Connection;
//import java.sql.Date;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Map;
//import javax.swing.Icon;
//import javax.swing.ImageIcon;
//import javax.swing.JButton;
//import javax.swing.JComboBox;
//import javax.swing.JOptionPane;
//import javax.swing.JTable;
//import javax.swing.JTextField;
//import javax.swing.table.DefaultTableModel;
//import javax.swing.table.JTableHeader;
//import javax.swing.table.TableColumn;
//
//public class ModeloUsuario {
//
//    Conexion cone = new Conexion();
//    Connection cn = cone.iniciarConexion();
//
//    private int doc, sex, rol, tip;
//    private String nom, dir, tec, cor, lo, cl;
//    private Date fec;
//
//    public Conexion getCone() {
//        return cone;
//    }
//
//    public void setCone(Conexion cone) {
//        this.cone = cone;
//    }
//
//    public Connection getCn() {
//        return cn;
//    }
//
//    public void setCn(Connection cn) {
//        this.cn = cn;
//    }
//
//    public int getDoc() {
//        return doc;
//    }
//
//    public void setDoc(int doc) {
//        this.doc = doc;
//    }
//
//    public int getSex() {
//        return sex;
//    }
//
//    public void setSex(int sex) {
//        this.sex = sex;
//    }
//
//    public int getRol() {
//        return rol;
//    }
//
//    public void setRol(int rol) {
//        this.rol = rol;
//    }
//
//    public String getNom() {
//        return nom;
//    }
//
//    public void setNom(String nom) {
//        this.nom = nom;
//    }
//
//    public String getDir() {
//        return dir;
//    }
//
//    public void setDir(String dir) {
//        this.dir = dir;
//    }
//
//    public String getTec() {
//        return tec;
//    }
//
//    public void setTec(String tec) {
//        this.tec = tec;
//    }
//
//    public String getCor() {
//        return cor;
//    }
//
//    public void setCor(String cor) {
//        this.cor = cor;
//    }
//
//    public String getLo() {
//        return lo;
//    }
//
//    public void setLo(String lo) {
//        this.lo = lo;
//    }
//
//    public String getCl() {
//        return cl;
//    }
//
//    public void setCl(String cl) {
//        this.cl = cl;
//    }
//
//    public Date getFec() {
//        return fec;
//    }
//
//    public void setFec(Date fec) {
//        this.fec = fec;
//    }
//
//    public int getTip() {
//        return tip;
//    }
//
//    public void setTip(int tip) {
//        this.tip = tip;
//    }
//// llenar los combos
//    public Map<String, Integer> llenarCombo(String valor) {
//
//        Conexion cone = new Conexion();
//        Connection cn = cone.iniciarConexion();
//
//        String sql = "Select * from mostrar_" + valor;
//
//        Map<String, Integer> llenar_combo = new HashMap<>();
//        try {
//            Statement st = cn.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//            while (rs.next()) {
//                llenar_combo.put(rs.getString(2), rs.getInt(1));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//
//        }
//        return llenar_combo;
//    }
//
//    public void llenarnuevousuario() {
//        Conexion cone = new Conexion();
//        Connection cn = cone.iniciarConexion();//instanciamos la conexion
//        String sql = "call ins_usuario (?,?,?,?,?,?,?,?,?,?,?)";
//        
//        try {
//
//            PreparedStatement ps = cn.prepareStatement(sql);
//            ps.setInt(1, getDoc());
//            ps.setString(4, getNom());
//            ps.setString(5, getTec());
//            ps.setString(6, getCor());
//            ps.setString(7, getDir());
//            ps.setDate(10, getFec());
//            ps.setInt(3, getSex());
//            ps.setInt(2, getRol());
//            ps.setString(8, getLo());
//            ps.setString(9, getCl());
//            ps.setInt(11, getTip());
//
//            ps.executeUpdate();
//            JOptionPane.showMessageDialog(null, "Registro almacenado");
//            cn.close();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(null, "Error al guardar", "Error", JOptionPane.ERROR_MESSAGE);
//
//        }
//        cone.cerrarConexion();
//    }
//
//    public void Limpiar(Component[] panel) {
//        for (Object control : panel) {
//            if (control instanceof JTextField) {
//                ((JTextField) control).setText("");
//
//                if (control instanceof JComboBox) {
//                    ((JComboBox) control).setSelectedItem("seleccione...");
//                }
//                if (control instanceof JDateChooser) {
//                    ((JDateChooser) control).setDate(null);
//
//                }
//            }
//        }
//    }
//
//    public void mostrarTablaUsuario(JTable tabla, String valor, String nomPesta) {
//
//        Conexion conect = new Conexion();
//        Connection co = conect.iniciarConexion();
//
//        //Personalizar Emcabezado
//        JTableHeader encabeza = tabla.getTableHeader();
//        encabeza.setDefaultRenderer(new Gestion_Encabezado());
//        tabla.setTableHeader(encabeza);
//
//        //Personalizar Celdas
//        tabla.setDefaultRenderer(Object.class, new GestionCeldas());
//        JButton editar = new JButton("Editar");
//        JButton eliminar = new JButton("Eliminar");
////        JButton agregar = new JButton("Agregar");
//
//        editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/lapiz.png")));
//        eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eliminar.png")));
////        agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/agregar-usuario.png")));
//
//        String[] titulo = {"Documento", "Tipo de Documento", "Rol", "Genero", "Nombre", "Telefono", "Correo", "Fecha de Nacimiento"};
//        int total = titulo.length;
//        if (nomPesta.equals("usuario")) {
//            titulo = Arrays.copyOf(titulo, titulo.length + 2);
//            titulo[titulo.length - 2] = "";
//            titulo[titulo.length - 1] = "";
//        } else {
//            titulo = Arrays.copyOf(titulo, titulo.length + 1);
//            titulo[titulo.length - 1] = "";
//        }
//
//        DefaultTableModel tablaUsuario = new DefaultTableModel(null, titulo) {
//            public boolean isCellEditable(int row, int column) {
//
//                return false;
//
//            }
//        };
//
//        String sqlUsuario;
//        if (valor.equals("")) {
//            sqlUsuario = "SELECT * FROM mostrarusuario";
//        } else {
//            sqlUsuario = "call consultar_usuario('" + valor + "')";
//        }
//        try {
//            String[] dato = new String[titulo.length];
//            Statement st = cn.createStatement(); //Crea una consulta
//            ResultSet rs = st.executeQuery(sqlUsuario);
//            while (rs.next()) {
//                for (int i = 0; i < total; i++) {
//                    dato[i] = rs.getString(i + 1);
//                }
//                Object[] fila = {dato[0], dato[1], dato[2], dato[3], dato[4], dato[5], dato[6], dato[7], editar, eliminar};
//                if (nomPesta.equals("usuario")) {
//                    fila = Arrays.copyOf(fila, fila.length + 2);
//                    fila[fila.length - 2] = editar;
//                    fila[fila.length - 1] = eliminar;
//                } else {
//                    fila = Arrays.copyOf(fila, fila.length + 1);
////                    fila[fila.length - 1] = agregar;
//                }
//                tablaUsuario.addRow(fila);
//            }
//            co.close();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        tabla.setModel(tablaUsuario);
//        //Darle Tamaño a cada Columna
//        int cantColum = tabla.getColumnCount();
//        int[] ancho = {100, 180, 100, 150, 100, 160, 100, 180, 150, 30, 30};
//        for (int i = 0; i < cantColum; i++) {
//            TableColumn columna = tabla.getColumnModel().getColumn(i);
//            columna.setPreferredWidth(ancho[i]);
//        }
//        conect.cerrarConexion();
//    }
//
//    public void BuscarUsuario(int valor) {
//        Conexion cone = new Conexion();
//        Connection cn = cone.iniciarConexion();
//        String sql = "call_mostrarusuario(" + valor + ")";
//
//        try {
//            Statement st = cn.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//
//            while (rs.next()) {
//                setDoc(rs.getInt(1));
//                setTec(rs.getNString(2));
//                setNom(rs.getString(3));
//                setTec(rs.getString(4));
//                setCor(rs.getString(5));
//                setDir(rs.getString(6));
//                setFec(rs.getDate(7));
//                setSex(rs.getInt(8));
//                setRol(rs.getInt(9));
//                setLo(rs.getString(10));
//                setCl(rs.getString(11));
//
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//
//        }
//    }
//
//    /**
//     *
//     * @param dato
//     * @param valor
//     * @return
//     */
//    public String obtenerSeleccion(Map<String, Integer> dato, int valor) {
//        for (Map.Entry<String, Integer> seleccion : dato.entrySet()) {
//            if (seleccion.getValue() == valor) {
//                return seleccion.getKey();
//            }
//        }
//        return null;
//    }
//
//    public void actualizarUsuario() {
//        Conexion conect = new Conexion();
//        Connection cn = conect.iniciarConexion();
//        String sql = "call actualizar_Usuario(?,?,?,?,?,?,?,?,?)";
//
//        try {
//            PreparedStatement ps = cn.prepareStatement(sql);
//            ps.setInt(1, getDoc());
//            ps.setString(2, getNom());
//            ps.setInt(3, getRol());
//            ps.setString(4, getTec());
//            ps.setString(5, getCor());
//            ps.setInt(6, getSex());
//            ps.setString(7, getDir());
//            ps.setDate(8, getFec());
//            ps.setString(9, getCl());
//            ps.executeUpdate();
//
//            JOptionPane.showMessageDialog(null, "Información Actualizada");
//            cn.close();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        conect.cerrarConexion();
//    }
//
//    public void agregarBoton(String nomPesta, String[] objeto, Object valor, Object valor2, Object valor3) {
//
//        if (nomPesta.equals("usuario")) {
//            System.out.println(nomPesta);
//
//            objeto = Arrays.copyOf(objeto, objeto.length + 2);
//
//            objeto[objeto.length - 2] = String.valueOf(valor);
//            objeto[objeto.length - 1] = String.valueOf(valor2);
//            System.out.println(objeto.length);
//        } else {
//            objeto = Arrays.copyOf(objeto, objeto.length + 1);
//            objeto[objeto.length - 1] = String.valueOf(valor3);
//        }
//    }
//
//    public void eliminarUsuario() {
//        Conexion con = new Conexion();
//        Connection cn = con.iniciarConexion();
//        String eliUsuario = "Call usuario_elim(?)";
//        try {
//            PreparedStatement ps = cn.prepareStatement(eliUsuario);
//            ps.setInt(1, getDoc());
//            ps.executeUpdate();
//            Icon eliminar = new ImageIcon(getClass().getResource("/img/eliminar.png"));
//            JOptionPane.showMessageDialog(null, "Registro Eliminado", "Eliminar Usuario", JOptionPane.PLAIN_MESSAGE, (Icon) eliminar);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}
