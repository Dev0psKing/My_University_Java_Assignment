package WEEK_10_ASSIGNMENTS;

import java.util.ArrayList;
import java.util.List;

public class Customer {
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