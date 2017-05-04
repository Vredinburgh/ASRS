package ordersys.xmlReader;

import java.sql.*;

/**
 * Created by ian on 03/05/2017.
 */
public class Product {

    private String name;
    private int productNr, height, stock, x, y;

    public Product(int productNr) {

        this.productNr = productNr;

        String query = "SELECT * FROM object o WHERE object_id = " + productNr
                + " JOIN location l ON o.object_id = l.object_id;";
        // hier in de database zoeken naar bijbehorende gegevens
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sonoo", "root", "root");
            //here sonoo is database name, root is username and password
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                name = rs.getString("name");
                height = Integer.parseInt(rs.getString("height"));
                stock = Integer.parseInt(rs.getString("stock"));
                x = Integer.parseInt(rs.getString("x"));
                y = Integer.parseInt(rs.getString("y"));
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public String getName() {
        return name;
    }

    public int getProductNr() {
        return productNr;
    }

    public int getHeight() {
        return height;
    }

    public int getStock() {
        return stock;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Product{"
                + "name='" + name + '\''
                + ", productNr=" + productNr
                + ", height=" + height
                + ", stock=" + stock
                + ", x=" + x
                + ", y=" + y
                + '}';
    }
}
