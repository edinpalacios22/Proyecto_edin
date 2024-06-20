
package Controlador;

import Modelo.Login_modelo;
import Vista.Iniciar_sesion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JOptionPane;


public class Controlador_login implements ActionListener, WindowListener {
    Login_modelo modlog = new Login_modelo();
    Iniciar_sesion log = new Iniciar_sesion();
  
    ControladorPrincipal prin = new ControladorPrincipal();

    public Controlador_login() {
        log.getBtnIniciar1().addActionListener(this);
        log.getBtnOjo().addActionListener(this);
        
    }
    public void iniciarVista(){
        log.setLocationRelativeTo(null);//Centrando la vista
        log.setTitle("Iniciar Sesión");// Titulo a la vista
        log.setVisible(true);//Visible la vista
    }
            

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(log.getBtnOjo())){
            if(log.getJpcontra().getEchoChar()=='\u2022'){
                log.getJpcontra().setEchoChar((char)0);
                log.getBtnOjo().setIcon(new javax.swing.ImageIcon(
                        getClass().getResource("/img/ojo-cruzado.png")));
            }else{
                log.getJpcontra().setEchoChar('\u2022');
                log.getBtnOjo().setIcon(new javax.swing.ImageIcon(
                        getClass().getResource("/img/ojo.png")));
            }
        }
        
         if(e.getSource()==(log.getBtnIniciar1())){
             validar();
        }
    }
//        if(e.getSource()==(log.getBtnIniciar1())){
            public void validar(){
            modlog.setUsu(log.getTxtusuario().getText());
            String pass = new String(log.getJpcontra().getPassword());
            modlog.setContra(pass);
            if(modlog.validar(modlog.getUsu(), modlog.getContra())){
                log.setVisible(false);
                prin.iniciarPrincipal(0);
                
                
            }else{
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrecta");
                
            }
        
  
    }

    @Override
    public void windowOpened(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void windowClosing(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void windowClosed(WindowEvent e) {
        ControladorPrincipal princi = new ControladorPrincipal();
        princi.iniciarPrincipal(0);
   
    }

    @Override
    public void windowIconified(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void windowActivated(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
