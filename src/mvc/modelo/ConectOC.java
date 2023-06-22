/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author hp
 */
public class ConectOC {

    public Connection con;

    private final String DRIVER = "oracle.jdbc.driver.OracleDriver";
    private final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private final String USER = "MARCAM3A";
    private final String PASWORD = "M1234";

    public ConectOC() {
        this.con = null;
    }

    public Connection conectar() {
        try {
            Class.forName(DRIVER);
            this.con = DriverManager.getConnection(URL, USER, PASWORD);

        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.exit(0);
        }
        return this.con;

    }

    public void desconectar() {
        try {
            this.con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public ResultSet consulta(String sql) {

        try {
            Statement st = con.createStatement(); //recive como parametro la consulta
            return st.executeQuery(sql);//Ejecuto la consulta y me devuelve un 'Resultset'

        } catch (SQLException ex) {
            Logger.getLogger(ConectOC.class.getName()).log(Level.SEVERE, null, ex);
            return null; //Si se da la excepcion me retorna un null
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ConectOC.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /*Metodo generico cuando no devuelve datos. FORMA 1*/
    public boolean accion(String sql) {
        boolean correcto;
        try {
            Statement st = con.createStatement();
            st.execute(sql);
            st.close();
            correcto = true;
        } catch (SQLException ex) {
            Logger.getLogger(ConectOC.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            correcto = false;
        }

        return correcto;
    }

    public Connection getCon() {
        return con;
    }
}
