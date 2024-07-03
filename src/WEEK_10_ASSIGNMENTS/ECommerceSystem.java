package WEEK_10_ASSIGNMENTS;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ECommerceSystem {
    private static List<Product> inventory = new ArrayList<>();
    private static List<Customer> customers = new ArrayList<>();
    private static List<Order> orders = new ArrayList<>();
    private static int orderIdCounter = 1;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeInventory();

        while (true) {
            System.out.println("\n--- E-Commerce System ---");
            System.out.println("1. Create new customer");
            System.out.println("2. Browse products");
            System.out.println("3. Add product to cart");
            System.out.println("4. View cart");
            System.out.println("5. Remove product from cart");
            System.out.println("6. Place order");
            System.out.println("7. View orders");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1: createCustomer(); break;
                case 2: browseProducts(); break;
                case 3: addToCart(); break;
                case 4: viewCart(); break;
                case 5: removeFromCart(); break;
                case 6: placeOrder(); break;
                case 7: viewOrders(); break;
                case 8:
                    System.out.println("Thank you for using the E-Commerce System. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void initializeInventory() {
        inventory.add(new Product(1, "Laptop", 999.99));
        inventory.add(new Product(2, "Smartphone", 499.99));
        inventory.add(new Product(3, "Headphones", 79.99));
        inventory.add(new Product(4, "Tablet", 299.99));
        inventory.add(new Product(5, "Smartwatch", 199.99));
    }

    private static void createCustomer() {
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();
        int id = customers.size() + 1;
        Customer customer = new Customer(id, name);
        customers.add(customer);
        System.out.println("Customer created: " + customer);
    }

    private static void browseProducts() {
        System.out.println("\nAvailable products:");
        for (Product product : inventory) {
            System.out.println(product);
        }
    }

    private static void addToCart() {
        if (customers.isEmpty()) {
            System.out.println("No customers available. Please create a customer first.");
            return;
        }

        System.out.print("Enter customer ID: ");
        int customerId = scanner.nextInt();
        Customer customer = findCustomer(customerId);

        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        System.out.print("Enter product ID to add to cart: ");
        int productId = scanner.nextInt();
        Product product = findProduct(productId);

        if (product == null) {
            System.out.println("Product not found.");
            return;
        }

        customer.addToCart(product);
        System.out.println("Product added to cart.");
    }

    private static void viewCart() {
        if (customers.isEmpty()) {
            System.out.println("No customers available. Please create a customer first.");
            return;
        }

        System.out.print("Enter customer ID: ");
        int customerId = scanner.nextInt();
        Customer customer = findCustomer(customerId);

        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        List<Product> cart = customer.getShoppingCart();
        if (cart.isEmpty()) {
            System.out.println("Cart is empty.");
        } else {
            System.out.println("Cart contents:");
            for (Product product : cart) {
                System.out.println(product);
            }
            System.out.println("Total: $" + customer.calculateTotal());
        }
    }

    private static void removeFromCart() {
        if (customers.isEmpty()) {
            System.out.println("No customers available. Please create a customer first.");
            return;
        }

        System.out.print("Enter customer ID: ");
        int customerId = scanner.nextInt();
        Customer customer = findCustomer(customerId);

        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        List<Product> cart = customer.getShoppingCart();
        if (cart.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }

        System.out.println("Cart contents:");
        for (Product product : cart) {
            System.out.println(product);
        }

        System.out.print("Enter product ID to remove from cart: ");
        int productId = scanner.nextInt();
        Product product = findProduct(productId);

        if (product == null) {
            System.out.println("Product not found in cart.");
            return;
        }

        customer.removeFromCart(product);
        System.out.println("Product removed from cart.");
    }

    private static void placeOrder() {
        if (customers.isEmpty()) {
            System.out.println("No customers available. Please create a customer first.");
            return;
        }

        System.out.print("Enter customer ID: ");
        int customerId = scanner.nextInt();
        Customer customer = findCustomer(customerId);

        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        List<Product> cart = customer.getShoppingCart();
        if (cart.isEmpty()) {
            System.out.println("Cart is empty. Cannot place an order.");
            return;
        }

        Order order = new Order(orderIdCounter++, customer, new ArrayList<>(cart));
        orders.add(order);
        customer.clearCart();

        System.out.println("Order placed successfully.");
        System.out.println(order.generateOrderSummary());
    }

    private static void viewOrders() {
        if (orders.isEmpty()) {
            System.out.println("No orders placed yet.");
            return;
        }

        System.out.println("All orders:");
        for (Order order : orders) {
            System.out.println(order.generateOrderSummary());
            System.out.println("--------------------");
        }
    }

    private static Customer findCustomer(int id) {
        return customers.stream()
                .filter(c -> c.getCustomerID() == id)
                .findFirst()
                .orElse(null);
    }

    private static Product findProduct(int id) {
        return inventory.stream()
                .filter(p -> p.getProductID() == id)
                .findFirst()
                .orElse(null);
    }
}

class Product {
    private int productID;
    private String name;
    private double price;

    public Product(int productID, String name, double price) {
        this.productID = productID;
        this.name = name;
        this.price = price;
    }

    public int getProductID() { return productID; }
    public String getName() { return name; }
    public double getPrice() { return price; }

    public void setPrice(double price) {
        if (price >= 0) {
            this.price = price;
        } else {
            throw new IllegalArgumentException("Price cannot be negative");
        }
    }

    @Override
    public String toString() {
        return "Product{id=" + productID + ", name='" + name + "', price=$" + price + "}";
    }
}

class Customer {
    private int customerID;
    private String name;
    private List<Product> shoppingCart;

    public Customer(int customerID, String name) {
        this.customerID = customerID;
        this.name = name;
        this.shoppingCart = new ArrayList<>();
    }

    public void addToCart(Product product) {
        shoppingCart.add(product);
    }

    public void removeFromCart(Product product) {
        shoppingCart.remove(product);
    }

    public double calculateTotal() {
        return shoppingCart.stream().mapToDouble(Product::getPrice).sum();
    }

    public List<Product> getShoppingCart() {
        return new ArrayList<>(shoppingCart);
    }

    public void clearCart() {
        shoppingCart.clear();
    }

    public int getCustomerID() { return customerID; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return "Customer{id=" + customerID + ", name='" + name + "'}";
    }
}


class Order {
    private int orderID;
    private Customer customer;
    private List<Product> products;
    private double total;
    private String status;

    public Order(int orderID, Customer customer, List<Product> products) {
        this.orderID = orderID;
        this.customer = customer;
        this.products = products;
        this.total = calculateTotal();
        this.status = "Pending";
    }

    private double calculateTotal() {
        return products.stream().mapToDouble(Product::getPrice).sum();
    }

    public String generateOrderSummary() {
        StringBuilder summary = new StringBuilder();
        summary.append("Order ID: ").append(orderID).append("\n");
        summary.append("Customer: ").append(customer.getName()).append("\n");
        summary.append("Products:\n");
        for (Product product : products) {
            summary.append("- ").append(product.getName()).append(": $").append(product.getPrice()).append("\n");
        }
        summary.append("Total: $").append(total).append("\n");
        summary.append("Status: ").append(status);
        return summary.toString();
    }

    public void updateStatus(String newStatus) {
        this.status = newStatus;
    }


    public int getOrderID() { return orderID; }
    public Customer getCustomer() { return customer; }
    public List<Product> getProducts() { return products; }
    public double getTotal() { return total; }
    public String getStatus() { return status; }
}