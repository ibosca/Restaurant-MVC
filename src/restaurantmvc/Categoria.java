/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurantmvc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author isaac
 */
public class Categoria {
    
    public int id;
    public String nom;
    public String descripcio; 
    
    public static Categoria[] getCategories() {
        Connection con = Model.obrir_conexio_db();
        Categoria[] categories = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT COUNT(*) FROM categoria");
            rs.next();
            int count = rs.getInt(1);

            rs = stmt.executeQuery("SELECT * FROM categoria");
            categories = new Categoria[count];
            int i = 0;
            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.id = rs.getInt(1);
                categoria.nom = rs.getString(2);
                categoria.descripcio = rs.getString(3);

                categories[i] = categoria;
                i++;
            }
        } catch (SQLException e) {
            System.out.println("Error a la base de dades: " + e.getMessage());
            System.out.println("Estat de la base de dades: " + e.getSQLState());
            System.out.println("Vendor Error: " + e.getErrorCode());
        }
        Model.tancar_conexio_db(con, rs);
        return categories;
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
    
    
    
}
