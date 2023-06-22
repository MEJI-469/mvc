/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo;

import java.awt.Image;
import java.io.FileInputStream;
import java.sql.Date;

/**
 *
 * @author hp
 */
public class Persona {
    
    private String idPersona;
    private String nom1Persona;
    private String nom2Persona;
    private String ape1Persona;
    private String ape2Persona;
    private String direcPersona;
    private String telePersona;

    public Persona() {
    }

    public Persona(String idPersona, String nom1Persona, String nom2Persona, String ape1Persona, String ape2Persona, String direcPersona, String telePersona) {
        this.idPersona = idPersona;
        this.nom1Persona = nom1Persona;
        this.nom2Persona = nom2Persona;
        this.ape1Persona = ape1Persona;
        this.ape2Persona = ape2Persona;
        this.direcPersona = direcPersona;
        this.telePersona = telePersona;
    }

    public String getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(String idPersona) {
        this.idPersona = idPersona;
    }

    public String getNom1Persona() {
        return nom1Persona;
    }

    public void setNom1Persona(String nom1Persona) {
        this.nom1Persona = nom1Persona;
    }

    public String getNom2Persona() {
        return nom2Persona;
    }

    public void setNom2Persona(String nom2Persona) {
        this.nom2Persona = nom2Persona;
    }

    public String getApe1Persona() {
        return ape1Persona;
    }

    public void setApe1Persona(String ape1Persona) {
        this.ape1Persona = ape1Persona;
    }

    public String getApe2Persona() {
        return ape2Persona;
    }

    public void setApe2Persona(String ape2Persona) {
        this.ape2Persona = ape2Persona;
    }

    public String getDirecPersona() {
        return direcPersona;
    }

    public void setDirecPersona(String direcPersona) {
        this.direcPersona = direcPersona;
    }

    public String getTelePersona() {
        return telePersona;
    }

    public void setTelePersona(String telePersona) {
        this.telePersona = telePersona;
    }
    
    
}
