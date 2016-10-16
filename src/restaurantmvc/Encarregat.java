package restaurantmvc;

import java.sql.*;

/**
 *
 * @author isaac
 */
public class Encarregat extends Model {

    public int id;
    public String nom;
    public String cognom;
    public String email;
    public String telefon;

    public static Encarregat[] getEncarregats() {
        Connection con = Model.obrir_conexio_db();
        Encarregat[] encarregats = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT COUNT(*) FROM encarregats");
            rs.next();
            int count = rs.getInt(1);

            rs = stmt.executeQuery("SELECT * FROM encarregats");
            encarregats = new Encarregat[count];
            int i = 0;
            while (rs.next()) {
                Encarregat encarregat = new Encarregat();
                encarregat.id = rs.getInt(1);
                encarregat.nom = rs.getString(2);
                encarregat.cognom = rs.getString(3);
                encarregat.email = rs.getString(4);
                encarregat.telefon = rs.getString(5);

                encarregats[i] = encarregat;
                i++;
            }
        } catch (SQLException e) {
            System.out.println("Error a la base de dades: " + e.getMessage());
            System.out.println("Estat de la base de dades: " + e.getSQLState());
            System.out.println("Vendor Error: " + e.getErrorCode());
        }
        Model.tancar_conexio_db(con, rs);
        return encarregats;
    }

    public static Encarregat findEncarregatByPlat(Plat plat) {
        Connection con = Model.obrir_conexio_db();
        Statement stmt = null;
        ResultSet rs = null;
        Encarregat encarregat = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT DISTINCT encarregats.*\n"
                    + "FROM encarregats, categoria_encarregat, plats\n"
                    + "WHERE encarregats.id = categoria_encarregat.encarregat AND categoria_encarregat.categoria =\n"
                    + "(SELECT plats.categoria FROM plats WHERE plats.id =" + plat.getId() + " )");
            rs.next();
            encarregat = new Encarregat();
            encarregat.id = rs.getInt(1);
            encarregat.nom = rs.getString(2);
            encarregat.cognom = rs.getString(3);
            encarregat.email = rs.getString(4);
            encarregat.telefon = rs.getString(5);

        } catch (SQLException e) {
            System.out.println("Error a la base de dades: " + e.getMessage());
            System.out.println("Estat de la base de dades: " + e.getSQLState());
            System.out.println("Vendor Error: " + e.getErrorCode());
        }
        Model.tancar_conexio_db(con, rs);
        return encarregat;
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

    public String getCognom() {
        return cognom;
    }

    public void setCognom(String cognom) {
        this.cognom = cognom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

}
