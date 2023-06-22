/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.controlador;

import mvc.modelo.ModeloPersona;
import mvc.vista.VistaMenuPrincipal;
import mvc.vista.VistaPersona;

/**
 *
 * @author hp
 */
public class ControladorMenuPrincipal {

    VistaMenuPrincipal vistaPrincipal;

    public ControladorMenuPrincipal(VistaMenuPrincipal vistaPrincipal) {
        this.vistaPrincipal = vistaPrincipal;
        vistaPrincipal.setLocationRelativeTo(vistaPrincipal);
        vistaPrincipal.setVisible(true);
    }

    public void iniciaControl() {
        //MENU PRINCIPAL    
        vistaPrincipal.getMnuClientes().addActionListener(l -> menuPersonas());
//        vistaPrincipal.getMnuProductos().addActionListener(l -> menuProducto());
//        vistaPrincipal.getMnuFactura().addActionListener(l -> menuFactura());
        //TOOLBAR
        vistaPrincipal.getTblClientes().addActionListener(l -> menuPersonas());
//        vistaPrincipal.getTblProductos().addActionListener(l -> menuProducto());
//        vistaPrincipal.getTblFactura().addActionListener(l -> menuFactura());
    }

    private void menuPersonas() {
        ModeloPersona modelo = new ModeloPersona();
        VistaPersona vista = new VistaPersona();
        vistaPrincipal.getDskPrincipal().add(vista);//Agrega la vista per a la Principal
        ControladorPersonas controlador = new ControladorPersonas(modelo, vista);
        controlador.iniciaControl();
    }
    
    
}
