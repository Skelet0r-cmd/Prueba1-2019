/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.UsuarioDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JComboBox;
import modelo.Usuario;
import vista.BandejaEntrada;
import vista.NuevoMensaje;

/**
 *
 * @author Ccastill
 */
public class NuevoMensajeController implements ActionListener {

    BandejaEntrada bandejaEntrada;
    NuevoMensaje nuevoMensaje;
    JComboBox destinatarioCB;

    public NuevoMensajeController(BandejaEntrada bandejaEntrada, NuevoMensaje nuevoMensaje, JComboBox destinatarioCB) {
        this.bandejaEntrada = bandejaEntrada;
        this.nuevoMensaje = nuevoMensaje;
        this.destinatarioCB = destinatarioCB;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        NuevoMensaje nuevoMensaje = new NuevoMensaje();
        nuevoMensaje.setVisible(true);
        UsuarioDAO ud = new UsuarioDAO();
        ArrayList<Usuario> usuarios = ud.getUsuarios();
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario u = usuarios.get(i);
            nuevoMensaje.getComboBox().addItem(u.getNombre());
        }
    }

}
