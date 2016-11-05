package restaurantmvc;
import java.lang.reflect.Field;
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
    
    /*protected static Object[] FindAll(String clase) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
        
        Connection con = Model.obrir_conexio_db();
        Object[] objects = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT COUNT(*) FROM " + clase);
            rs.next();
            int count = rs.getInt(1);

            rs = stmt.executeQuery("SELECT * FROM "+ clase);
            objects = new Object[count];
            int i = 0;
            while (rs.next()) {
                Object xyz = Class.forName(clase).newInstance();
                Field[] propiedades = xyz.getClass().getFields();
                xyz.getClass(). = rs.getInt(1);
                encarregat.nom = rs.getString(2);
                encarregat.cognom = rs.getString(3);
                encarregat.email = rs.getString(4);
                encarregat.telefon = rs.getString(5);

                objects[i] = encarregat;
                i++;
            }
        } catch (SQLException e) {
            System.out.println("Error a la base de dades: " + e.getMessage());
            System.out.println("Estat de la base de dades: " + e.getSQLState());
            System.out.println("Vendor Error: " + e.getErrorCode());
        }
        Model.tancar_conexio_db(con, rs);
        return encarregats;
        
    }*/
}
