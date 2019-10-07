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
import modelo.Mensaje;
import modelo.Usuario;

/**
 *
 * @author Ccastill
 */
public class MensajeDAO {
       Conexion con;

    public MensajeDAO() {
        this.con = new Conexion();
    }
    
    public ArrayList<Mensaje> getMensajes(){
        
        ArrayList<Mensaje> mensajes = new ArrayList<>();
        Connection accesoBD = con.getConexion();

        try{
            String sql="SELECT * FROM mensaje ";
            
            //System.out.println(sql);
            Statement st = accesoBD.createStatement();
            ResultSet resultados = st.executeQuery(sql);
           
            
            while ( resultados.next() ) {
                int id= resultados.getInt("id_mensaje");
                String contenido = resultados.getString("contenido");
                int id_usr_emisor = resultados.getInt("id_usr_emisor");
                int id_usr_receptor = resultados.getInt("id_usr_receptor");
                
                UsuarioDAO pd= new UsuarioDAO();
                Usuario emisor=pd.getUsuario(id_usr_emisor);
                Usuario remitente = pd.getUsuario(id_usr_receptor);
                
                mensajes.add(new Mensaje(id, emisor, remitente, contenido));
            }
           // accesoBD.close();
            return mensajes;
        }catch (Exception e){
            System.out.println();
            System.out.println("Error al obtener");
            e.printStackTrace();
            return null;
        }   
    }
        
       public Mensaje getMensaje(int id){
           Mensaje a= new Mensaje();
           Connection accesoBD = con.getConexion();
            
             try{
            String sql="SELECT * FROM auto WHERE id="+id+";";
            
            Statement st = accesoBD.createStatement(); //Objeto que viaja hasta la base de datos y lleva la consulta
            ResultSet resultados = st.executeQuery(sql); //Resultados enviados de vuelta
           
            //tambien se puede hacer con if y con resultados.first(); que es el primer valor que aparece
             while( resultados.next()) { //es una iterador propio de resultset que entrega un boleano y cambia a la siguiente tupla para obtener datos de todas las tuplas.
                int idMensaje= resultados.getInt("id_mensaje");
                String contenido = resultados.getString("contenido");
                int id_usr_emisor = resultados.getInt("id_usr_receptor");
                int id_usr_receptor = resultados.getInt("remitente");
       
                UsuarioDAO pd= new UsuarioDAO();
                Usuario emisor=pd.getUsuario(id_usr_emisor);
                Usuario remitente = pd.getUsuario(id_usr_receptor);
                a = new Mensaje(idMensaje, emisor, remitente, contenido);
            }
                 if (a.getId_mensaje()==0) {
                     System.out.println("No se ha encontrado información sobre el id: "+id);
                 }
            return a;
        }catch (Exception e){
            System.out.println();
            System.out.println("Error al obtener");
            e.printStackTrace();
            return null;
        }   
    }
       
       public boolean insertarAuto(Mensaje a){
        Connection accesoBD = con.getConexion();
        
        try{
          String sql="INSERT INTO `mensaje` (`contenido`, `id_usr_emisor`, `id_usr_receptor`) VALUES ('"+a.getContenido()+"', "+a.getEmisor().getId_usuario()+", "+a.getRemitente().getId_usuario()+")";
          Statement st = accesoBD.createStatement();
          st.executeUpdate(sql); //Execute update es para modificar la BD y meter info
          
          return true;
        }catch(Exception e){
            System.out.println("Error al insertar");
            e.printStackTrace();
        }
           
           return false;
       }      
}
