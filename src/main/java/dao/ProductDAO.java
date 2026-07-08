package dao;

import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    Connection con;

    public ProductDAO() {
        con = DBConnection.getConnection();
    }

    // INSERT
    public void addProduct(Product p) {
        try {
            String sql = "INSERT INTO products(name, category, price, stock) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, p.getName());
            ps.setString(2, p.getCategory());
            ps.setDouble(3, p.getPrice());
            ps.setInt(4, p.getStock());

            ps.executeUpdate();
            System.out.println("Product Added Successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // SELECT ALL
    public List<Product> getAll() {

        List<Product> list = new ArrayList<>();

        try {
            String sql = "SELECT * FROM products";
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Product p = new Product();

                p.setProductId(rs.getInt("product_id"));
                p.setName(rs.getString("name"));
                p.setCategory(rs.getString("category"));
                p.setPrice(rs.getDouble("price"));
                p.setStock(rs.getInt("stock"));

                list.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // UPDATE
    public void updateProduct(Product p) {
        try {
            String sql = "UPDATE products SET name=?, category=?, price=?, stock=? WHERE product_id=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, p.getName());
            ps.setString(2, p.getCategory());
            ps.setDouble(3, p.getPrice());
            ps.setInt(4, p.getStock());
            ps.setInt(5, p.getProductId());

            ps.executeUpdate();
            System.out.println("Product Updated Successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void deleteProduct(int id) {
        try {
            String sql = "DELETE FROM products WHERE product_id=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();
            System.out.println("Product Deleted Successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}