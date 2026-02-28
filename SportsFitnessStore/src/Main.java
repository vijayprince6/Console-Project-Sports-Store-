import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/sports_store";
        String username = "root";
        String password = "root"; // replace with your MySQL root password

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Welcome to Sports & Fitness Store!");
            System.out.println("Connection Successful!");

            Scanner sc = new Scanner(System.in);
            int choice = 0;

            while (choice != 5) {
                System.out.println("\n===== MENU =====");
                System.out.println("1. View Products");
                System.out.println("2. Add Product");
                System.out.println("3. Update Product");
                System.out.println("4. Delete Product");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();
                sc.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        viewProducts(conn);
                        break;
                    case 2:
                        addProduct(conn, sc);
                        break;
                    case 3:
                        updateProduct(conn, sc);
                        break;
                    case 4:
                        deleteProduct(conn, sc);
                        break;
                    case 5:
                        System.out.println("Exiting... Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }

            conn.close();
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void viewProducts(Connection conn) throws SQLException {
        String query = "SELECT * FROM products";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        System.out.println("\nProducts in the store:");
        System.out.printf("%-5s %-25s %-15s %-10s %-5s%n", "ID", "Name", "Category", "Price", "Stock");
        System.out.println("---------------------------------------------------------------");

        while (rs.next()) {
            System.out.printf(
                "%-5d %-25s %-15s %-10.2f %-5d%n",
                rs.getInt("product_id"),
                rs.getString("name"),
                rs.getString("category"),
                rs.getDouble("price"),
                rs.getInt("stock")
            );
        }

        rs.close();
        stmt.close();
    }

    private static void addProduct(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter product name: ");
        String name = sc.nextLine();
        System.out.print("Enter category: ");
        String category = sc.nextLine();
        System.out.print("Enter price: ");
        double price = sc.nextDouble();
        System.out.print("Enter stock: ");
        int stock = sc.nextInt();
        sc.nextLine(); // consume newline

        String query = "INSERT INTO products (name, category, price, stock) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, name);
        pstmt.setString(2, category);
        pstmt.setDouble(3, price);
        pstmt.setInt(4, stock);
        int rows = pstmt.executeUpdate();
        if (rows > 0) {
            System.out.println("Product added successfully!");
        }
        pstmt.close();
    }

    private static void updateProduct(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter product ID to update: ");
        int id = sc.nextInt();
        sc.nextLine(); // consume newline
        System.out.print("Enter new price: ");
        double price = sc.nextDouble();
        System.out.print("Enter new stock: ");
        int stock = sc.nextInt();
        sc.nextLine(); // consume newline

        String query = "UPDATE products SET price = ?, stock = ? WHERE product_id = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setDouble(1, price);
        pstmt.setInt(2, stock);
        pstmt.setInt(3, id);
        int rows = pstmt.executeUpdate();
        if (rows > 0) {
            System.out.println("Product updated successfully!");
        } else {
            System.out.println("Product ID not found.");
        }
        pstmt.close();
    }

    private static void deleteProduct(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter product ID to delete: ");
        int id = sc.nextInt();
        sc.nextLine(); // consume newline

        String query = "DELETE FROM products WHERE product_id = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setInt(1, id);
        int rows = pstmt.executeUpdate();
        if (rows > 0) {
            System.out.println("Product deleted successfully!");
        } else {
            System.out.println("Product ID not found.");
        }
        pstmt.close();
    }
}
