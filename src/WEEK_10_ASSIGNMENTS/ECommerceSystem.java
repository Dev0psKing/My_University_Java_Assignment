package WEEK_10_ASSIGNMENTS;



import java.util.ArrayList;

import java.util.List;

import java.util.Scanner;

import java.util.InputMismatchException;



public class ECommerceSystem {

    private static List<Product> inventory = new ArrayList<>();

    private static List<Customer> customers = new ArrayList<>();

    private static List<Order> orders = new ArrayList<>();

    private static int orderIdCounter = 1;

    private static Scanner scanner = new Scanner(System.in);



    public static void main(String[] args) {

        initializeInventory();



        while (true) {

            displayMenu();

            int choice = getIntInput("Enter your choice: ");



            try {

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

            } catch (Exception e) {

                System.out.println("An error occurred: " + e.getMessage());

            }

        }

    }



    // Initialize inventory with some products

    private static void initializeInventory() {

        inventory.add(new Product(1, "Laptop", 999.99));

        inventory.add(new Product(2, "Smartphone", 499.99));

        inventory.add(new Product(3, "Headphones", 79.99));

        inventory.add(new Product(4, "Tablet", 299.99));

        inventory.add(new Product(5, "Smartwatch", 199.99));

    }



    // Display main menu

    private static void displayMenu() {

        System.out.println("\n--- E-Commerce System ---");

        System.out.println("1. Create new customer");

        System.out.println("2. Browse products");

        System.out.println("3. Add product to cart");

        System.out.println("4. View cart");

        System.out.println("5. Remove product from cart");

        System.out.println("6. Place order");

        System.out.println("7. View orders");

        System.out.println("8. Exit");

    }



    // Create a new customer

    private static void createCustomer() {

        String name = getStringInput("Enter customer name: ");

        int id = customers.size() + 1;

        Customer customer = new Customer(id, name);

        customers.add(customer);

        System.out.println("Customer created: " + customer);

    }



    // Display all products in inventory

    private static void browseProducts() {

        System.out.println("\nAvailable products:");

        for (Product product : inventory) {

            System.out.println(product);

        }

    }



    // Add a product to customer's cart

    private static void addToCart() throws Exception {

        if (customers.isEmpty()) {

            throw new Exception("No customers available. Please create a customer first.");

        }



        int customerId = getIntInput("Enter customer ID: ");

        Customer customer = findCustomer(customerId);



        if (customer == null) {

            throw new Exception("Customer with ID " + customerId + " not found.");

        }



        int productId = getIntInput("Enter product ID to add to cart: ");

        Product product = findProduct(productId);



        if (product == null) {

            throw new Exception("Product with ID " + productId + " not found.");

        }



        customer.addToCart(product);

        System.out.println("Product added to cart.");

    }



    // View customer's cart

    private static void viewCart() throws Exception {

        if (customers.isEmpty()) {

            throw new Exception("No customers available. Please create a customer first.");

        }



        int customerId = getIntInput("Enter customer ID: ");

        Customer customer = findCustomer(customerId);



        if (customer == null) {

            throw new Exception("Customer with ID " + customerId + " not found.");

        }



        customer.displayCart();

    }



    // Remove a product from customer's cart

    private static void removeFromCart() throws Exception {

        if (customers.isEmpty()) {

            throw new Exception("No customers available. Please create a customer first.");

        }



        int customerId = getIntInput("Enter customer ID: ");

        Customer customer = findCustomer(customerId);



        if (customer == null) {

            throw new Exception("Customer with ID " + customerId + " not found.");

        }



        customer.displayCart();



        int productId = getIntInput("Enter product ID to remove from cart: ");

        Product product = findProduct(productId);



        if (product == null) {

            throw new Exception("Product with ID " + productId + " not found in cart.");

        }



        customer.removeFromCart(product);

        System.out.println("Product removed from cart.");

    }



    // Place an order for a customer

    private static void placeOrder() throws Exception {

        if (customers.isEmpty()) {

            throw new Exception("No customers available. Please create a customer first.");

        }



        int customerId = getIntInput("Enter customer ID: ");

        Customer customer = findCustomer(customerId);



        if (customer == null) {

            throw new Exception("Customer with ID " + customerId + " not found.");

        }



        if (customer.getShoppingCart().isEmpty()) {

            throw new Exception("Cart is empty. Cannot place an order.");

        }



        Order order = new Order(orderIdCounter++, customer, new ArrayList<>(customer.getShoppingCart()));

        orders.add(order);

        customer.clearCart();



        System.out.println("Order placed successfully.");

        System.out.println(order.generateOrderSummary());

    }



    // View all orders

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



    // Find a customer by ID

    private static Customer findCustomer(int id) {

        return customers.stream()

                .filter(c -> c.getCustomerID() == id)

                .findFirst()

                .orElse(null);

    }



    // Find a product by ID

    private static Product findProduct(int id) {

        return inventory.stream()

                .filter(p -> p.getProductID() == id)

                .findFirst()

                .orElse(null);

    }


    // Get string input from user

    private static String getStringInput(String prompt) {

        String input;

        do {

            System.out.print(prompt);

            input = scanner.nextLine().trim();

            if (input.isEmpty()) {

                System.out.println("Input cannot be empty. Please try again.");

            }

        } while (input.isEmpty());

        return input;

    }



    // Get integer input from user

    private static int getIntInput(String prompt) {

        while (true) {

            try {

                System.out.print(prompt);

                int input = scanner.nextInt();

                scanner.nextLine(); // Consume newline

                return input;

            } catch (InputMismatchException e) {

                System.out.println("Invalid input. Please enter a number.");

                scanner.nextLine(); // Consume invalid input

            }

        }

    }

}



class Product {

    private int productID;

    private String name;

    private double price;



    public Product(int productID, String name, double price) {

        this.productID = productID;

        this.name = name;

        setPrice(price);

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

        return String.format("Product{id=%d, name='%s', price=$%.2f}", productID, name, price);

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



    public void displayCart() {

        if (shoppingCart.isEmpty()) {

            System.out.println("Cart is empty.");

        } else {

            System.out.println("Cart contents:");

            for (Product product : shoppingCart) {

                System.out.println(product);

            }

            System.out.printf("Total: $%.2f%n", calculateTotal());

        }

    }



    public int getCustomerID() { return customerID; }

    public String getName() { return name; }



    @Override

    public String toString() {

        return String.format("Customer{id=%d, name='%s'}", customerID, name);

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

        summary.append(String.format("Order ID: %d%n", orderID));

        summary.append(String.format("Customer: %s%n", customer.getName()));

        summary.append("Products:\n");

        for (Product product : products) {

            summary.append(String.format("- %s: $%.2f%n", product.getName(), product.getPrice()));

        }

        summary.append(String.format("Total: $%.2f%n", total));

        summary.append(String.format("Status: %s", status));

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