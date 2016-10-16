package restaurantmvc;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author isaac
 */
public class Model {
    
    protected static Connection obrir_conexio_db(){
        Connection con= null;
        try{
        con = DriverManager.getConnection("jdbc:mysql://localhost/restaurant?user=root&password=root");
        return con;
        }catch (SQLException e){
            System.out.println("Error a la base de dades: "+e.getMessage());
            System.out.println("Estat de la base de dades: "+e.getSQLState());
            System.out.println("Vendor Error: "+e.getErrorCode());
        }
        return con;
    }
    
    protected static void tancar_conexio_db(Connection con, ResultSet rs){
        try {
            rs.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
