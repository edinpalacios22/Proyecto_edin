package Modelo;

import Controlador.Conexion;
import Vista.Iniciar_sesion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login_modelo {

    String usu, contra;
    Iniciar_sesion Is = new Iniciar_sesion();

    public Login_modelo() {
    }

    public String getUsu() {
        return usu;
    }

    public void setUsu(String usu) {
        this.usu = usu;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public boolean validar(String usuario, String pass) {
        Conexion conect = new Conexion();
        Connection cn = conect.iniciarConexion();
        boolean result = false;
        String sql = "call login(?,?)";
        try {
            PreparedStatement ps = cn.prepareCall(sql);
            ps.setString(1, usuario);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                result = true;
            }
            cn.close();
            conect.cerrarConexion();
        } catch (SQLException e) {
            e.printStackTrace();

        }

        return result;
    }

}
