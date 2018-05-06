package rozetka.com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by val on 05.05.2018.
 */

public class Powder {
    private int id;
    private String name;
    private int price;

    public Powder() {}

    public Powder(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public int getPrice() {return price;}
    public void setPrice(int price) {this.price = price;}

    public void savePowderToDb(Connection connection, String dbName) {
        if (this == null)
            return;
        String request = " (name, price) ";
        String querry = "insert into " + dbName + request + "values (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(querry)) {
            ps.setString(1, getName());
            ps.setInt(2, getPrice());
            ps.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println("Data recording to table Powder mistake");
        }
    }

    @Override
    public String toString() {
        return name + " : " + price;
    }
}
