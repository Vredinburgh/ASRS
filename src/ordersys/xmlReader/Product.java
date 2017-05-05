package ordersys.xmlReader;

import java.sql.*;

/**
 * Created by ian on 03/05/2017.
 */
public class Product {

    private String name;
    private int productNr, height, stock, x, y;
    private double price;

    public Product(int productNr) {

        this.productNr = productNr;

        String query = "SELECT * from product where id="+productNr;
        // hier in de database zoeken naar bijbehorende gegevens
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://jansengames.com:3306/jansen1q_robot", "jansen1q_robot", "robot123");
            //here sonoo is database name, root is username and password
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                name = rs.getString("name");
                name = rs.getString("name");
                height = Integer.parseInt(rs.getString("height"));
                stock = Integer.parseInt(rs.getString("stock"));
                price = Double.parseDouble(rs.getString("price"));
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
    
    public double getPrice() {
        return price;
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
