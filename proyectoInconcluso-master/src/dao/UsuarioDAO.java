/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.Usuario;

/**
 *
 * @author Ccastill
 */
public class UsuarioDAO {
     Conexion con;

    public UsuarioDAO() {
        this.con = new Conexion();
    }

    public ArrayList<Usuario> getUsuarios() {

        ArrayList<Usuario> usuarios = new ArrayList<>();
        Connection accesoBD = con.getConexion();

        try {
            String sql = "SELECT * FROM usuario ";

            //System.out.println(sql);
            Statement st = accesoBD.createStatement();
            ResultSet resultados = st.executeQuery(sql);

            while (resultados.next()) {
                int idPersona = resultados.getInt("id_usuario");
                String nombre = resultados.getString("nombre");
                

                usuarios.add(new Usuario(idPersona, nombre));
            }
            // accesoBD.close();
            return usuarios;
        } catch (Exception e) {
            System.out.println();
            System.out.println("Error al obtener");
            e.printStackTrace();
            return null;
        }
    }

    public Usuario getUsuario(int id) {
        Usuario p = new Usuario();
        Connection accesoBD = con.getConexion();

        try {
            String sql = "SELECT * FROM usuario WHERE id_usuario=" + id + ";";

            Statement st = accesoBD.createStatement(); //Objeto que viaja hasta la base de datos y lleva la consulta
            ResultSet resultados = st.executeQuery(sql); //Resultados enviados de vuelta

            //tambien se puede hacer con if y con resultados.first(); que es el primer valor que aparece
            if (resultados.first()) { //es una iterador propio de resultset que entrega un boleano y cambia a la siguiente tupla para obtener datos de todas las tuplas.
                int idPersona = resultados.getInt("id_usuario");
                String nombre = resultados.getString("nombre");
                p = new Usuario(idPersona, nombre);
                
                if (p.getId_usuario() == 0) {
                    System.out.println("No se ha encontrado información sobre el id: " + id);
                }
                return p;
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println();
            System.out.println("Error al obtener");
            e.printStackTrace();
            return null;
        }
    }

}
