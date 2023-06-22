/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.controlador;

import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.xml.ws.Holder;
import mvc.modelo.ModeloPersona;
import mvc.modelo.Persona;
import mvc.vista.VistaMenuPrincipal;
import mvc.vista.VistaPersona;

/**
 *
 * @author hp
 */
public class ControladorPersonas {

//    private VistaMenuPrincipal vistaPri;
    private ModeloPersona modelo;
    private VistaPersona vistaP;
    private JFileChooser jfc; //Objeto de tipo JFileChooser

    public ControladorPersonas(ModeloPersona modelo, VistaPersona vista) {
        this.modelo = modelo;
        this.vistaP = vista;
        vista.setVisible(true);
//        vista.setLocationRelativeTo(null);
        cargaPersonasTabla();//carga datos 
    }

    public void iniciaControl() {
        vistaP.getBtnActualizar().addActionListener(l -> cargaPersonasTabla());

        vistaP.getBtnEditar().addActionListener(l -> CargarDatosEnLosTxt());

        vistaP.getBtnCancelar().addActionListener(l -> vistaP.getJdgCrearEditar().setVisible(false));
        vistaP.getBtnCrear().addActionListener(o -> abrirDialogo("Crear"));
        vistaP.getBtnAceptar().addActionListener(l -> crearEditarPersona());

        vistaP.getBtnEliminar().addActionListener(l -> eliminarPersona());
        vistaP.getBtnImprimir().addActionListener(l -> {
            JOptionPane.showMessageDialog(null, " !ESTA OPCION NO ESTA DISPONIBLE POR EL MOMENTO! ");
        });
        buscarPersona();
    }

    public void abrirDialogo(String ce) {

        vistaP.getJdgCrearEditar().setLocationRelativeTo(vistaP);
        vistaP.getJdgCrearEditar().setSize(715, 485);

        vistaP.getJdgCrearEditar().setTitle(ce);
        vistaP.getJdgCrearEditar().setVisible(true);
        limpiarCampos();

    }

    private void cargaPersonasTabla() {

        DefaultTableModel tblModel;
        tblModel = (DefaultTableModel) vistaP.getTbPersona().getModel();
        tblModel.setNumRows(0);//limpio filas de la tabla.

        List<Persona> listap = modelo.listaPersonasTabla();//Enlazo al Modelo y obtengo los datos

        listap.stream().forEach(p -> {

            Object[] rowData = {p.getIdPersona(), p.getNom1Persona(),
                p.getNom2Persona(), p.getApe1Persona(), p.getApe2Persona(),
                p.getDirecPersona(), p.getTelePersona()};
            tblModel.addRow(rowData);
        });
    }

    private void crearEditarPersona() {

        if (vistaP.getJdgCrearEditar().getTitle().contentEquals("Crear")) {
            //CREAR

            ModeloPersona persona = new ModeloPersona();
            persona.setIdPersona(vistaP.getTxtCedula().getText());
            persona.setNom1Persona(vistaP.getTxtNombre1().getText());
            persona.setNom2Persona(vistaP.getTxtNombre2().getText());
            persona.setApe1Persona(vistaP.getTxtApellido1().getText());
            persona.setApe2Persona(vistaP.getTxtApellido2().getText());
            persona.setDirecPersona(vistaP.getTxtDireccion().getText());
            persona.setTelePersona(vistaP.getTxtTelefono().getText());

            if (persona.crearPersonaSinFtos()) {
                vistaP.getJdgCrearEditar().setVisible(false);
                JOptionPane.showMessageDialog(vistaP, "Persona Creada Satisfactoriamente");
            } else {
                JOptionPane.showMessageDialog(vistaP, "No se pudo crear la persona");
            }
        } else {
            //EDITAR
            
            ModeloPersona persona = new ModeloPersona();
            persona.setIdPersona(vistaP.getTxtCedula().getText());
            persona.setNom1Persona(vistaP.getTxtNombre1().getText());
            persona.setNom2Persona(vistaP.getTxtNombre2().getText());
            persona.setApe1Persona(vistaP.getTxtApellido1().getText());
            persona.setApe2Persona(vistaP.getTxtApellido2().getText());
            persona.setDirecPersona(vistaP.getTxtDireccion().getText());
            persona.setTelePersona(vistaP.getTxtTelefono().getText());

            if (persona.modificarPersona()) {
                vistaP.getJdgCrearEditar().setVisible(false);
                JOptionPane.showMessageDialog(vistaP, "Persona Modificada Satisfactoriamente");
            } else {
                JOptionPane.showMessageDialog(vistaP, "No se pudo modificar la persona");
            }

            cargaPersonasTabla(); //Actualiza

        }
    }

    public void eliminarPersona() {

        int fila = vistaP.getTbPersona().getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Aun no ha seleccionado una fila");
        } else {

            int response = JOptionPane.showConfirmDialog(vistaP, "¿Seguro que desea eliminar la información?", "Confirme", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {

                String cedula;
                cedula = vistaP.getTbPersona().getValueAt(fila, 0).toString();

                if (modelo.eliminarPersona(cedula)) {
                    JOptionPane.showMessageDialog(null, "La persona fue eliminada exitosamente");
                    cargaPersonasTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "La persona no se pudo eliminar");
                }
            }
        }

    }

    public void CargarDatosEnLosTxt() {
        int seleccion = vistaP.getTbPersona().getSelectedRow();

        if (seleccion == -1) {
            JOptionPane.showMessageDialog(null, "Aun no ha seleccionado una fila");
        } else {

            String cedula = vistaP.getTbPersona().getValueAt(seleccion, 0).toString();
            modelo.listaPersonas().forEach((pe) -> {
                if (pe.getIdPersona().equals(cedula)) {

                    vistaP.getJdgCrearEditar().setTitle("Editar");
                    vistaP.getJdgCrearEditar().setSize(1100, 500);
                    vistaP.getJdgCrearEditar().setLocationRelativeTo(null);
                    vistaP.getJdgCrearEditar().setVisible(true);

                    vistaP.getTxtCedula().setText(pe.getIdPersona());
                    vistaP.getTxtNombre1().setText(pe.getNom1Persona());
                    vistaP.getTxtNombre2().setText(pe.getNom2Persona());
                    vistaP.getTxtApellido1().setText(pe.getApe1Persona());
                    vistaP.getTxtApellido2().setText(pe.getApe2Persona());
                    vistaP.getTxtDireccion().setText(pe.getDirecPersona());
                    vistaP.getTxtTelefono().setText(pe.getTelePersona());

                }
            });
        }
    }

    public void buscarPersona() {

        KeyListener eventkey = new KeyListener() {//Crear un objeto de tipo keyListener(Es una interface) por lo tanto se debe implementar sus metodos abstractos

            @Override
            public void keyTyped(KeyEvent e) {
                int key = e.getKeyChar();

                boolean numeros = key >= 48 && key <= 57;

                if (!numeros) {
                    e.consume();
                }

                if (vistaP.getTxtBuscar().getText().trim().length() == 10) {
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyReleased(KeyEvent e) {

                //Enlazar el modelo de tabla con mi controlador.
                DefaultTableModel tblModel;
                tblModel = (DefaultTableModel) vistaP.getTbPersona().getModel();
                tblModel.setNumRows(0);//limpio filas de la tabla.

                List<Persona> listap = modelo.buscarPersonas(vistaP.getTxtBuscar().getText());//Enlazo al Modelo y obtengo los datos
                Holder<Integer> i = new Holder<>(0);//contador para el no. fila
                listap.stream().forEach(p -> {

                    Object[] rowData = {p.getIdPersona(), p.getNom1Persona(),
                        p.getNom2Persona(), p.getApe1Persona(), p.getApe2Persona(),
                        p.getDirecPersona(), p.getTelePersona()};
                    tblModel.addRow(rowData);

                });
            }
        };

        vistaP.getTxtBuscar().addKeyListener(eventkey); //"addKeyListener" es un metodo que se le tiene que pasar como argumento un objeto de tipo keyListener 
    }

    public void limpiarCampos() {
        vistaP.getTxtCedula().setText("");
        vistaP.getTxtNombre1().setText("");
        vistaP.getTxtNombre2().setText("");
        vistaP.getTxtApellido1().setText("");
        vistaP.getTxtApellido2().setText("");
        vistaP.getTxtDireccion().setText("");
        vistaP.getTxtTelefono().setText("");
    }

}
