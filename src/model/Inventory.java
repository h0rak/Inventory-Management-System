package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The Inventory class creates the allParts list, the allProducts list,
 * and the various public static methods that are used in other classes
 * in order to interact with the two tables(lists.)
 */
public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();

    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     * The addPart method takes in a part parameter and adds that to the allParts list.
     * @param part the part to add
     */
    public static void addPart(Part part) {allParts.add(part);}

    /**
     * The addProduct method takes in product parameter and add that to the allProducts lists.
     * @param product the product to add
     */
    public static void addProduct(Product product) { allProducts.add(product); }

    /**
     * The lookupPart method is an overloaded method that takes a partId as a parameter.
     * @param partId the partId to lookup
     * @return returns the part if it's an id match
     */
    public static Part lookupPart(int partId) {
        ObservableList<Part> allParts = Inventory.getAllParts();
        for(int i = 0; i < allParts.size(); i++) {
            Part p = allParts.get(i);
            if(p.getId() == partId) {
                return p;
            }
        }
        return null;
    }

    /**
     * The lookupProduct method is an overloaded method that takes a productId as a parameter.
     * @param productId the productId to look up
     * @return returns the product if it's an id match
     */
    public static Product lookupProduct(int productId) {
        ObservableList<Product> allProducts = Inventory.getAllProducts();
        for(int i = 0; i < allProducts.size(); i++) {
            Product p = allProducts.get(i);
            if(p.getId() == productId) {
                return p;
            }
        }
        return null;
    }

    /**
     * The lookupPart method is an overloaded method that takes a partName as a parameter.
     * @param partName the partName to look up and add to list if a match
     * @return returns a list since there can be more than one part that meets the input criteria
     */
    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> namedParts = FXCollections.observableArrayList();
        ObservableList<Part> allParts = Inventory.getAllParts();
        for(int i = 0; i < allParts.size(); i++) {
            Part p = allParts.get(i);
            if(p.getName().contains(partName)) {
                namedParts.add(p);
            }
        }
        return namedParts;
    }

    /**
     * The lookupProduct method is an overloaded method that takes a productName as a parameter.
     * @param productName the productName to look up and add to list if a match
     * @return returns list of products matching name criteria
     */
    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> namedProducts = FXCollections.observableArrayList();
        ObservableList<Product> allProducts = Inventory.getAllProducts();
        for(int i = 0; i < allProducts.size(); i++) {
            Product p = allProducts.get(i);
            if(p.getName().contains(productName)) {
                namedProducts.add(p);
            }
        }
        return namedProducts;
    }

    /**
     * The updatePart method updates an existing part.
     * @param index the index of the part you want to overwrite
     * @param selectedPart the selectedPart is the modified Part that will be saved
     */
    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }

    /**
     * The updateProduct method updates an existing product.
     * @param index the index of the product you want to overwrite
     * @param selectedProduct the selectedProduct is the modified Product that will be saved
     */
    public static void updateProduct(int index, Product selectedProduct) {
        allProducts.set(index, selectedProduct);
    }

    /**
     * The deletePart method removes the selectedPart from the allParts list.
     * @param selectedPart the selectedPart to be deleted
     * @return deletes the Part if true, otherwise false and does nothing
     */
    public static boolean deletePart(Part selectedPart) {
        allParts.remove(selectedPart);
        return false;
    }

    /**
     * The deleteProduct method removes the selectedProduct from the allProducts list
     * @param selectedProduct the selectedProduct to be deleted
     * @return deletes the Part if true, otherwise false and does nothing
     */
    public static boolean deleteProduct(Product selectedProduct) {
        allProducts.remove(selectedProduct);
        return false;
    }

    /**
     * The getAllParts method is an ObservableList that gathers the Part table items
     * @return returns a list of allParts
     */
    public static ObservableList<Part> getAllParts() {return allParts;}

    /**
     * The getAllProducts method is an ObservableList that gathers the Product table items
     * @return returns a list of allProducts
     */
    public static ObservableList<Product> getAllProducts() { return allProducts; }

    // My additions
    public static int partIdCounter = 3;

    /**
     * The getAPartId method is a simple counter that is used whenever creating a new Part
     * @return returns a partId after adding 1 to the counter
     */
    public static int getAPartId() {
        partIdCounter++;
        return partIdCounter;
    }
    public static int productIdCounter = 1002;

    /**
     * The getAProductId method is a simple counter that is used whenever creating a new Product.
     * @return returns a productId after adding 1 to the counter
     */
    public static int getAProductId() {
        productIdCounter++;
        return productIdCounter;
    }

    /**
     * This is the inputChecker method.
     * The inputChecker method parses through multiple inputs which happen to be the same values for Parts and Products
     * This convenience allowed me to utilize this method multiple times
     * @param name the name to check to see if empty or too long
     * @param price the price to check to make sure the number is a double and greater than 1
     * @param stock the stock to check and make sure is between MIN and MAX values and greater than 0
     * @param min the min to check and make sure is lower than the max
     * @param max the max to check and make sure is greater than the min
     * @param errorMessage the errorMessage which begins as an empty string and grows with each satisfied if statement
     * @return returns the completed errorMessage to later be used in alert dialogue
     */
    public static String inputChecker(String name, double price, int stock, int min, int max, String errorMessage) {
        if(name.isEmpty()) {
            errorMessage = errorMessage + "\nPlease enter a name.";
        } else if (name.length() > 20) {
            errorMessage = errorMessage + "\nPlease enter a shorter name.";
        }
        if(price <= 1) {
            errorMessage = errorMessage + "\nPlease enter a price greater than $1.";
        }
        if(stock <= 0) {
            errorMessage = errorMessage + "\nThe inventory cannot be set to empty.";
        }
        if(stock > max || stock < min) {
            errorMessage = errorMessage + "\nThe inventory value must be between MIN and MAX values.";
        }
        if(min > max) {
            errorMessage = errorMessage + "\nThe MIN value must be less than the MAX.";
        }
        return errorMessage;
    }
}
