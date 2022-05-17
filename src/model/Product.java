package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The Product class builds a product which consists of parts.
 */
public class Product {

    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /**
     * The Product method is a constructor that builds a product.
     * @param id the id for the product
     * @param name the name for the product
     * @param price the price for the product
     * @param stock the stock for the product
     * @param min the min for the product
     * @param max the max for the product
     */
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
        @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
        @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     @param min the min to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     @param max the max to set
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     @return the id
     */
    public int getId() {
        return id;
    }

    /**
     @return the name
     */
    public String getName() {
        return name;
    }

    /**
     @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     @return the stock
     */
    public int getStock() {
        return stock;
    }

    /**
     @return the min
     */
    public int getMin() {
        return min;
    }

    /**
     @return the max
     */
    public int getMax() {
        return max;
    }

    /**
     * The addAssociatedPart method takes a Part parameter and adds it to the associatedParts of a product.
     * @param part the part to add to the associatedParts table of a product
     */
    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    /**
     * The getAllAssociatedParts method can be used on a product object to get the associatedParts list.
     * @return the list of associatedParts for a product
     */
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }

    /**
     * The deleteAssociatedPart method deletes a selected associated part if it isn't null.
     * @param selectedAssociatedPart the part to be removed from associatedParts
     * @return returns nothing if false and deletes item from associatedParts if true
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        associatedParts.remove(selectedAssociatedPart);
        return false;
    }

    /**
     * The setAssociatedParts method takes a different list of parts and writes it to the associatedParts.
     * @param parts the list of parts to set to the associatedParts for a product
     */
    public void setAssociatedParts (ObservableList<Part> parts) {
        this.associatedParts = parts;
    }
}




