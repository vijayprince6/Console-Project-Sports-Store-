package controller;

import dao.ProductDAO;
import model.Product;

import java.util.List;

public class ProductController {

    ProductDAO dao = new ProductDAO();

    public void addProduct(String name, String category, double price, int stock) {
        Product p = new Product(name, category, price, stock);
        dao.addProduct(p);
    }

    public List<Product> getAllProducts() {
        return dao.getAll();
    }

    public void updateProduct(int id, String name, String category, double price, int stock) {
        Product p = new Product(id, name, category, price, stock);
        dao.updateProduct(p);
    }

    public void deleteProduct(int id) {
        dao.deleteProduct(id);
    }
}