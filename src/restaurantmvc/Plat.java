package restaurantmvc;

import java.sql.*;

/**
 *
 * @author isaac
 */
public class Plat extends Model {

    public int id;
    public String nom;
    public String descripcio;
    public int dificultat;
    public float coste_elaboracio;
    public float preu;
    public String categoria;

    public static Plat[] getPlats() {
        Connection con = Model.obrir_conexio_db();
        Plat[] plats = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();

            rs = stmt.executeQuery("SELECT COUNT(*) FROM plats");
            rs.next();
            int count = rs.getInt(1);
            plats = new Plat[count];
            rs = stmt.executeQuery("SELECT plats.id, plats.nom, plats.descripcio, plats.dificultat, plats.coste_elaboracio, plats.preu, categoria.nom as categoria FROM plats INNER JOIN categoria ON  plats.categoria = categoria.id");
            int i = 0;
            while (rs.next()) {
                Plat plat = new Plat();
                plat.id = rs.getInt(1);
                plat.nom = rs.getString(2);
                plat.descripcio = rs.getString(3);
                plat.dificultat = rs.getInt(4);
                plat.coste_elaboracio = rs.getFloat(5);
                plat.preu = rs.getFloat(6);
                plat.categoria = rs.getString(7);
                plats[i] = plat;
                i++;
            }
        } catch (SQLException e) {
            System.out.println("Error a la base de dades: " + e.getMessage());
            System.out.println("Estat de la base de dades: " + e.getSQLState());
            System.out.println("Vendor Error: " + e.getErrorCode());
        }
        Model.tancar_conexio_db(con, rs);
        return plats;
    }

    public static Plat[] findPlatsByCategory(Categoria categoria) {
        Connection con = Model.obrir_conexio_db();
        Plat[] plats = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();

            rs = stmt.executeQuery("SELECT COUNT(*) FROM plats WHERE categoria=" + categoria.getId());
            rs.next();
            int count = rs.getInt(1);
            plats = new Plat[count];
            rs = stmt.executeQuery("SELECT plats.id, plats.nom, plats.descripcio, plats.dificultat, plats.coste_elaboracio, plats.preu, categoria.nom as categoria FROM plats INNER JOIN categoria ON  plats.categoria = categoria.id AND plats.categoria =" + categoria.getId());
            int i = 0;
            while (rs.next()) {
                Plat plat = new Plat();
                plat.id = rs.getInt(1);
                plat.nom = rs.getString(2);
                plat.descripcio = rs.getString(3);
                plat.dificultat = rs.getInt(4);
                plat.coste_elaboracio = rs.getFloat(5);
                plat.preu = rs.getFloat(6);
                plat.categoria = rs.getString(7);
                plats[i] = plat;
                i++;
            }
        } catch (SQLException e) {
            System.out.println("Error a la base de dades: " + e.getMessage());
            System.out.println("Estat de la base de dades: " + e.getSQLState());
            System.out.println("Vendor Error: " + e.getErrorCode());
        }
        Model.tancar_conexio_db(con, rs);
        return plats;
    }

    public static Plat[] findPlatsByIngredient(Ingredient ingredient) {
        Connection con = Model.obrir_conexio_db();
        Plat[] plats = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();

            rs = stmt.executeQuery("SELECT COUNT(*) FROM receptes WHERE ingredient=" + ingredient.getId());
            rs.next();
            int count = rs.getInt(1);
            plats = new Plat[count];
            rs = stmt.executeQuery("SELECT plats.id, plats.nom, plats.descripcio, plats.dificultat, plats.coste_elaboracio, plats.preu, categoria.nom as categoria \n"
                    + "FROM  receptes INNER JOIN plats \n"
                    + "ON receptes.plat = plats.id\n"
                    + "INNER JOIN categoria\n"
                    + "ON  plats.categoria = categoria.id\n"
                    + "WHERE receptes.ingredient =" + ingredient.getId());
            int i = 0;
            while (rs.next()) {
                Plat plat = new Plat();
                plat.id = rs.getInt(1);
                plat.nom = rs.getString(2);
                plat.descripcio = rs.getString(3);
                plat.dificultat = rs.getInt(4);
                plat.coste_elaboracio = rs.getFloat(5);
                plat.preu = rs.getFloat(6);
                plat.categoria = rs.getString(7);
                plats[i] = plat;
                i++;
            }
        } catch (SQLException e) {
            System.out.println("Error a la base de dades: " + e.getMessage());
            System.out.println("Estat de la base de dades: " + e.getSQLState());
            System.out.println("Vendor Error: " + e.getErrorCode());
        }
        Model.tancar_conexio_db(con, rs);
        return plats;
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

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public int getDificultat() {
        return dificultat;
    }

    public void setDificultat(int dificultat) {
        this.dificultat = dificultat;
    }

    public float getCoste_elaboracio() {
        return coste_elaboracio;
    }

    public void setCoste_elaboracio(float coste_elaboracio) {
        this.coste_elaboracio = coste_elaboracio;
    }

    public float getPreu() {
        return preu;
    }

    public void setPreu(float preu) {
        this.preu = preu;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

}
