/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.MensajeDAO;
import dao.UsuarioDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import vista.BandejaEntrada;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Mensaje;
import modelo.Usuario;
import vista.Login;

/**
 *
 * @author chelo
 */
public class LoginController implements ActionListener {

    private Login vLogin;
    private BandejaEntrada bEntrada;

    public LoginController(Login vLogin, BandejaEntrada bEntrada) {
        this.vLogin = vLogin;
        this.bEntrada = bEntrada;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("ingresar")) {

            String nombre = vLogin.getNombreTf().getText(); //nombre obtenido

            UsuarioDAO uDAO = new UsuarioDAO(); //No modificar

            ArrayList<Usuario> usuariosBD = uDAO.getUsuarios();
            boolean nombreExisteEnBD = false;
            int id = -1;
            for (int i = 0; i < usuariosBD.size(); i++) {

                if (usuariosBD.get(i).getNombre().equals(nombre)) {
                    nombreExisteEnBD = true;
                    id = usuariosBD.get(i).getId_usuario();
                    break;
                }

            }

            if (nombreExisteEnBD) {
                BandejaEntrada bandejaVentana = new BandejaEntrada(id);//sería el Id del usuario logueado
                //Código para obtener Mensajes.
                MensajeDAO ad = new MensajeDAO();
                ArrayList<Mensaje> mensajes = ad.getMensajes();
                DefaultTableModel dtm = (DefaultTableModel) bandejaVentana.getTablaMensajes().getModel();

                dtm.setRowCount(0); //limpiar la tabla antes de mostrar

                for (int i = 0; i < mensajes.size(); i++) {
                    if (id == mensajes.get(i).getRemitente().getId_usuario()) {
                        Mensaje a = mensajes.get(i);
                        Object[] fila = {a.getEmisor(), a.getContenido()};
                        dtm.addRow(fila);
                    }
                }

                bandejaVentana.setVisible(true);

            } else {
                JOptionPane.showMessageDialog(vLogin, "Error el usuariono existe en la BD");
            }

        }

    }

}
