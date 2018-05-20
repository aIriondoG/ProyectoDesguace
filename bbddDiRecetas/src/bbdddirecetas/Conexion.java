/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bbdddirecetas;

import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Raul y Adrian
 * @version 1.0.2
 */
public class Conexion {
    /**
     * miConexion que instancia una Connection que despues sera utilizada para poder conectarse a la base de datos.
     */
    public static Connection conexionMySQL(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=(Connection) DriverManager.getConnection
            ("jdbc:mysql://localhost:3306/","root","");
            return con;
        } catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    } 
}
