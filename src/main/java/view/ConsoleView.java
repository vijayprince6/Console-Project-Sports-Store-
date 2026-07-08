package view;

import controller.ProductController;
import model.Product;

import java.util.List;
import java.util.Scanner;

public class ConsoleView {

    ProductController controller = new ProductController();
    Scanner sc = new Scanner(System.in);

    public void showMenu() {

        while (true) {

            System.out.println("\n===== PRODUCT MENU =====");
            System.out.println("1. Add Product");
            System.out.println("2. View Products");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    addProduct();
                    break;

                case 2:
                    viewProducts();
                    break;

                case 3:
                    updateProduct();
                    break;

                case 4:
                    deleteProduct();
                    break;

                case 5:
                    System.out.println("Exiting... Bye!");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private void addProduct() {

        System.out.print("Enter name: ");
        String name = sc.next();

        System.out.print("Enter category: ");
        String category = sc.next();

        System.out.print("Enter price: ");
        double price = sc.nextDouble();

        System.out.print("Enter stock: ");
        int stock = sc.nextInt();

        controller.addProduct(name, category, price, stock);
    }

    private void viewProducts() {

        List<Product> list = controller.getAllProducts();

        System.out.println("\n--- PRODUCT LIST ---");

        for (Product p : list) {
            System.out.println(p);
        }
    }

    private void updateProduct() {

        System.out.print("Enter Product ID: ");
        int id = sc.nextInt();

        System.out.print("Enter new name: ");
        String name = sc.next();

        System.out.print("Enter new category: ");
        String category = sc.next();

        System.out.print("Enter new price: ");
        double price = sc.nextDouble();

        System.out.print("Enter new stock: ");
        int stock = sc.nextInt();

        controller.updateProduct(id, name, category, price, stock);
    }

    private void deleteProduct() {

        System.out.print("Enter Product ID to delete: ");
        int id = sc.nextInt();

        controller.deleteProduct(id);
    }
}