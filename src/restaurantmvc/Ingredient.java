package restaurantmvc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author isaac
 */
public class Ingredient extends Model{

    public int id;
    public String nom;
    public float preu;
    public float existencies;
    public String unitat_medida;
    public String proveedor;
    public float stock_minim;

    public static Ingredient[] findIngredientsByPlat(Plat plat) {

        Connection con = Model.obrir_conexio_db();
        Ingredient[] ingredients = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT COUNT(*) FROM receptes WHERE plat=" + plat.getId());
            rs.next();
            int count = rs.getInt(1);
            ingredients = new Ingredient[count];

            rs = stmt.executeQuery("SELECT ingredients.*\n"
                    + "FROM receptes, ingredients\n"
                    + "WHERE ingredients.id = receptes.ingredient\n"
                    + "AND receptes.plat = " + plat.getId());

            int i = 0;
            while (rs.next()) {
                Ingredient ingredient = new Ingredient();
                ingredient.id = rs.getInt(1);
                ingredient.nom = rs.getString(2);
                ingredient.preu = rs.getFloat(3);
                ingredient.existencies = rs.getFloat(4);
                ingredient.unitat_medida = rs.getString(5);
                ingredient.proveedor = rs.getString(6);
                ingredient.stock_minim = rs.getFloat(7);

                ingredients[i] = ingredient;
                i++;
            }
        } catch (SQLException e) {
            System.out.println("Error a la base de dades: " + e.getMessage());
            System.out.println("Estat de la base de dades: " + e.getSQLState());
            System.out.println("Vendor Error: " + e.getErrorCode());
        }
        Model.tancar_conexio_db(con, rs);
        return ingredients;

    }

    public static Object[] findIngredients_I_ReceptesByPlat(Plat plat) {

        Connection con = Model.obrir_conexio_db();
        Ingredient[] ingredients = null;
        Recepta[] receptes = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT COUNT(*) FROM receptes WHERE plat=" + plat.getId());
            rs.next();
            int count = rs.getInt(1);
            ingredients = new Ingredient[count];
            receptes = new Recepta[count];

            rs = stmt.executeQuery("SELECT ingredients.*, receptes.id as ReceptesId, plat, cantitat\n"
                    + "FROM receptes, ingredients\n"
                    + "WHERE ingredients.id = receptes.ingredient\n"
                    + "AND receptes.plat =" + plat.getId());

            int i = 0;
            while (rs.next()) {
                Ingredient ingredient = new Ingredient();
                ingredient.id = rs.getInt(1);
                ingredient.nom = rs.getString(2);
                ingredient.preu = rs.getFloat(3);
                ingredient.existencies = rs.getFloat(4);
                ingredient.unitat_medida = rs.getString(5);
                ingredient.proveedor = rs.getString(6);
                ingredient.stock_minim = rs.getFloat(7);
                Recepta recepta = new Recepta();
                recepta.ingredient = rs.getInt(1);
                recepta.id = rs.getInt(8);
                recepta.plat = rs.getInt(9);
                recepta.cantitat = rs.getFloat(10);

                ingredients[i] = ingredient;
                receptes[i] = recepta;
                i++;
            }
        } catch (SQLException e) {
            System.out.println("Error a la base de dades: " + e.getMessage());
            System.out.println("Estat de la base de dades: " + e.getSQLState());
            System.out.println("Vendor Error: " + e.getErrorCode());
        }
        Model.tancar_conexio_db(con, rs);
        Object[] arr = new Object[2];
        arr[0] = ingredients;
        arr[1] = receptes;
        return arr;
    }
    
    public static Ingredient[] getIngredients() {
        Connection con = Model.obrir_conexio_db();
        Ingredient[] ingredients = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT COUNT(*) FROM ingredients");
            rs.next();
            int count = rs.getInt(1);

            rs = stmt.executeQuery("SELECT * FROM ingredients");
            ingredients = new Ingredient[count];
            int i = 0;
            while (rs.next()) {
                Ingredient ingredient = new Ingredient();
                ingredient.id = rs.getInt(1);
                ingredient.nom = rs.getString(2);
                ingredient.preu = rs.getFloat(3);
                ingredient.existencies = rs.getFloat(4);
                ingredient.unitat_medida = rs.getString(5);
                ingredient.proveedor = rs.getString(6);
                ingredient.stock_minim = rs.getFloat(7);

                ingredients[i] = ingredient;
                i++;
            }
        } catch (SQLException e) {
            System.out.println("Error a la base de dades: " + e.getMessage());
            System.out.println("Estat de la base de dades: " + e.getSQLState());
            System.out.println("Vendor Error: " + e.getErrorCode());
        }
        Model.tancar_conexio_db(con, rs);
        return ingredients;
    }
    
    public static Ingredient[] getIngredientsByStock(String signe) {
        Connection con = Model.obrir_conexio_db();
        Ingredient[] ingredients = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT COUNT(*) FROM ingredients WHERE existencies"+ signe +" stock_minim");
            rs.next();
            int count = rs.getInt(1);

            rs = stmt.executeQuery("SELECT * FROM ingredients WHERE existencies"+ signe +" stock_minim");
            ingredients = new Ingredient[count];
            int i = 0;
            while (rs.next()) {
                Ingredient ingredient = new Ingredient();
                ingredient.id = rs.getInt(1);
                ingredient.nom = rs.getString(2);
                ingredient.preu = rs.getFloat(3);
                ingredient.existencies = rs.getFloat(4);
                ingredient.unitat_medida = rs.getString(5);
                ingredient.proveedor = rs.getString(6);
                ingredient.stock_minim = rs.getFloat(7);

                ingredients[i] = ingredient;
                i++;
            }
        } catch (SQLException e) {
            System.out.println("Error a la base de dades: " + e.getMessage());
            System.out.println("Estat de la base de dades: " + e.getSQLState());
            System.out.println("Vendor Error: " + e.getErrorCode());
        }
        Model.tancar_conexio_db(con, rs);
        return ingredients;
    }
    
    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public float getPreu() {
        return preu;
    }

    public void setPreu(float preu) {
        this.preu = preu;
    }

    public float getExistencies() {
        return existencies;
    }

    public void setExistencies(float existencies) {
        this.existencies = existencies;
    }

    public String getUnitat_medida() {
        return unitat_medida;
    }

    public void setUnitat_medida(String unitat_medida) {
        this.unitat_medida = unitat_medida;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public float getStock_minim() {
        return stock_minim;
    }

    public void setStock_minim(float stock_minim) {
        this.stock_minim = stock_minim;
    }

}
