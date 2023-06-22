/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo;

import java.awt.Image;
import java.io.*;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hp
 */
public class ModeloPersona extends Persona {

    ConectOC coc = new ConectOC();

    public ModeloPersona() {

    }

    public ModeloPersona(String idPersona, String nom1Persona, String nom2Persona, String ape1Persona, String ape2Persona, String direcPersona, String telePersona) {
        super(idPersona, nom1Persona, nom2Persona, ape1Persona, ape2Persona, direcPersona, telePersona);
    }

    public List<Persona> listaPersonas() {
        try {
            //Me retorna un "List" de "persona"
            List<Persona> lista = new ArrayList<>();

            String sql = "select cedula, nombre1, nombre2, apellido1, apellido2, direccion, telefono from persona";

            ResultSet rs = coc.consulta(sql); //La consulta nos devuelve un "ResultSet"

            //Pasar de "ResultSet" a "List"
            while (rs.next()) {
                //Crear las instancias de las personas
                Persona persona = new Persona();

                //Todo lo que haga en la sentencia define como voy a extraer los datos
                persona.setIdPersona(rs.getString("cedula"));
                persona.setNom1Persona(rs.getString("nombre1"));
                persona.setNom2Persona(rs.getString("nombre2"));
                persona.setApe1Persona(rs.getString("apellido1"));
                persona.setApe2Persona(rs.getString("apellido2"));
                persona.setDirecPersona(rs.getString("direccion"));
                persona.setTelePersona(rs.getString("telefono"));

                lista.add(persona);
            }

            rs.close();
            return lista;

        } catch (SQLException ex) {
            Logger.getLogger(ModeloPersona.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<Persona> listaPersonasTabla() {
        try {
            //Me retorna un "List" de "persona"
            List<Persona> lista = new ArrayList<>();

            String sql = "select * from persona";

            ResultSet rs = coc.consulta(sql); //La consulta nos devuelve un "ResultSet" 

            while (rs.next()) {
                //Crear las instancias de las personas
                Persona persona = new Persona();

                //Todo lo que haga en la sentencia define como voy a extraer los datos
                persona.setIdPersona(rs.getString("cedula"));
                persona.setNom1Persona(rs.getString("nombre1"));
                persona.setNom2Persona(rs.getString("nombre2"));
                persona.setApe1Persona(rs.getString("apellido1"));
                persona.setApe2Persona(rs.getString("apellido2"));
                persona.setDirecPersona(rs.getString("direccion"));
                persona.setTelePersona(rs.getString("telefono"));

                lista.add(persona); //Agrego los datos a la lista
            }
            rs.close();
            return lista;

        } catch (SQLException ex) {
            Logger.getLogger(ModeloPersona.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean crearPersonaSinFtos() {

        String sql = "INSERT INTO persona(cedula, nombre1, nombre2, apellido1, apellido2, direccion, telefono) VALUES ('" + getIdPersona() + "', '" + getNom1Persona()+ "', '" + getNom2Persona()+ "', '" + getApe1Persona() + "', '" + getApe2Persona() + "', '" + getDirecPersona() + "'," + getTelePersona() + "');";

        return coc.accion(sql);
    }

    public boolean modificarPersona() { //modificar 

        String sql = "UPDATE persona SET nombre1='" + getNom1Persona() + "', nombre2='" + getNom2Persona() + "', apellido1='" + getApe1Persona() + "', apellido2='" + getApe2Persona() + "', direccion='" + getDirecPersona()+ "', telefono='" + getTelePersona()+ "' WHERE cedula = '" + getIdPersona() + "';";

        return coc.accion(sql);
    }

    public boolean eliminarPersona(String cedula) { //elimina 

        String sql = "DELETE FROM persona WHERE cedula = '" + cedula + "';";

        return coc.accion(sql);
    }

    public List<Persona> buscarPersonas(String cedula) {
        try {
            //Me retorna un "List" de "persona"
            List<Persona> lista = new ArrayList<>();

            String sql = "Select * from persona where cedula like '" + cedula + "%'";

            //ConectPG conpg = new ConectPG();
            ResultSet rs = coc.consulta(sql); //La consulta nos devuelve un "ResultSet"

            //Pasar de "ResultSet" a "List"
            while (rs.next()) {
                //Crear las instancias de las personas
                Persona persona = new Persona();

                //Todo lo que haga en la sentencia define como voy a extraer los datos
                persona.setIdPersona(rs.getString("idpersona"));
                persona.setNom1Persona(rs.getString("nombre1"));
                persona.setNom2Persona(rs.getString("nombre2"));
                persona.setApe1Persona(rs.getString("apellido1"));
                persona.setApe2Persona(rs.getString("apellido2"));
                persona.setDirecPersona(rs.getString("direccion"));
                persona.setTelePersona(rs.getString("telefono"));

                lista.add(persona); //Agrego los datos a la lista
            }

            //Cierro la conexion a la BD
            rs.close();
            //Retorno la lista
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloPersona.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

//    public List<Persona> listarPersonasFac(String cedula) {
//        List<Persona> listaPersonas = new ArrayList<>();
//        try {
//            String sql = "SELECT idpersona, nombres, telefono, correo FROM persona WHERE idpersona like '" + cedula + "%'";
//            ResultSet rs = coc.consulta(sql);
//            while (rs.next()) {
//                Persona persona = new Persona();
//                persona.setIdPersona(rs.getString(1));
//                persona.setNombrePersona(rs.getString(2));
//                persona.setTelefonoPersona(rs.getString(3));
//                persona.setCorreoPersona(rs.getString(4));
//                listaPersonas.add(persona);
//            }
//            return listaPersonas;
//        } catch (SQLException ex) {
//            return null;
//        }
//    }

}
