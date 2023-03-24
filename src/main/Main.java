package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;

/**
 * The main class creates the Inventory Management System app.
 * FUTURE ENHANCEMENT Product price will be at least the price of all the combined associated parts
 * RUNTIME ERROR This happened in all the controllers with a save button. After applying a value checker in the try blocks
 * I could no longer parse the inputs directly to their final types without getting number format exception errors. I also was
 * unable to get prompted with the message in the catch block. Parsing to final types after parsing to string first solved issue
 * @author raymondhorak
 */
public class Main extends Application {

    /**
     * This method sets the stage for the app.
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/MenuScreen.fxml"));
        stage.setScene(new Scene(root, 800, 600));
        stage.setResizable(false);
        stage.show();
    }
    /**
     * This is the main method which sets some sample data.
     * @param args the args parameter
     */
    public static void main(String[] args) {

            Part inHouse1 = new InHouse(1, "throttle", 59.99, 5, 1, 20, 201);
            Part outsourced1 = new Outsourced(2, "sprocket", 89.99, 10, 5, 25, "Sprock Bros");
            Part inHouse2 = new InHouse(3, "chain", 199.99, 15, 1, 25, 205);
            Inventory.addPart(inHouse1);
            Inventory.addPart(outsourced1);
            Inventory.addPart(inHouse2);

            Product product1 = new Product(1001,"cafe racer", 22109.99, 10, 5, 20);
            Product product2 = new Product(1002, "sport bike", 25799.99, 3, 1, 5);
            Inventory.addProduct(product1);
            Inventory.addProduct(product2);

            launch(args);

    }

}



