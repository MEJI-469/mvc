/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.main;

import mvc.controlador.ControladorMenuPrincipal;
import mvc.controlador.ControladorPersonas;
import mvc.modelo.ModeloPersona;
import mvc.vista.VistaMenuPrincipal;
import mvc.vista.VistaPersona;

/**
 *
 * @author hp
 */
public class Main {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
        // TODO code application logic here
//        VistaPersona vista = new VistaPersona();
//        ModeloPersona modelo = new ModeloPersona();
//        
//        ControladorPersonas control = new ControladorPersonas(modelo, vista);
//        control.iniciarControl();

        VistaMenuPrincipal vistaMenuPrincipal=new VistaMenuPrincipal();
        ControladorMenuPrincipal controlPrincipal= new ControladorMenuPrincipal(vistaMenuPrincipal);
        controlPrincipal.iniciaControl();

        
    }
}
