package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Adrián
 */
public class ConexionBD {

    public static void main(String[] args) {
        // TODO code application logic here
    }
    
     public static Connection conexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/desguaces", "root", "");
            return con;
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error de conexión con la base de datos. Asegúrate que el servidor XAMPP está iniciado\n"
                    + "y que el Driver de conexión a la BD está incluído en tu librería de Proyecto.");
        }
        return null;
    }
    
}
