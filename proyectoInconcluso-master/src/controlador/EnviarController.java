/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.MensajeDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import modelo.Mensaje;
import vista.BandejaEntrada;
import vista.NuevoMensaje;

/**
 *
 * @author Ccastill
 */
public class EnviarController implements ActionListener {

    private BandejaEntrada bandejaVentana;
    private NuevoMensaje nv;
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        
        MensajeDAO ad = new MensajeDAO();
        JTextArea mensaje = nv.getCuerpoMensaje();
        ArrayList<Mensaje> mensajes = ad.getMensajes();
        DefaultTableModel dtm = (DefaultTableModel) bandejaVentana.getTablaMensajes().getModel();

        dtm.setRowCount(0); //limpiar la tabla antes de mostrar

        for (int i = 0; i < mensajes.size(); i++) {

            Mensaje a = mensajes.get(i);
            Object[] fila = {a.getEmisor(), a.getContenido()};
            dtm.addRow(fila);

        }
    }

}
