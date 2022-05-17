package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.*;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * The MenuController class controls the MenuScreen.fxml.
 */
public class MenuController implements Initializable {

    @FXML
    private TableColumn<Part, Integer> partIdCol;
    @FXML
    private TableColumn<Part, Integer> partInventoryCol;
    @FXML
    private TableColumn<Part, String> partNameCol;
    @FXML
    private TableColumn<Part, Double> partPriceCol;
    @FXML
    private TableView<Part> partTable;
    @FXML
    private TableColumn<Product, Integer> productIdCol;
    @FXML
    private TableColumn<Product, Integer> productInventoryCol;
    @FXML
    private TableColumn<Product, String> productNameCol;
    @FXML
    private TableColumn<Product, Double> productPriceCol;
    @FXML
    private TableView<Product> productTable;
    @FXML
    private TextField searchPartField;
    @FXML
    private TextField searchProductField;

    private static Part modifyPart;
    private static int modifyPartIndex;
    private static Product modifyProduct;
    private static int modifyProductIndex;

    /**
     * @return returns a part being modified
     */
    public static Part getModifyPart () {
        return modifyPart;
    }

    /**
     * @return returns a product being modified
     */
    public static Product getModifyProduct () {
        return modifyProduct;
    }

    /**
     * @return returns the index of the part being modified
     */
    public static int getModifyPartIndex () {
        return modifyPartIndex;
    }

    /**
     * @return returns the index of the product being modified
     */
    public static int getModifyProductIndex () {
        return modifyProductIndex;
    }

    /**
     * The initialize method is executed when the scene sets.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        partTable.setItems(Inventory.getAllParts());
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        productTable.setItems(Inventory.getAllProducts());
        productIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    /**
     * The toExit method closes the application upon confirmation.
     * @param actionEvent the action is the button click
     */
    public void toExit(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you wish to exit?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.exit(0);
        }
    }

    /**
     * The toAddPart method sets the scene for the AddPartScreen.
     * @param actionEvent the action is the button click
     * @throws IOException if changing screens fails
     */
    public void toAddPart(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AddPartScreen.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The toModifyPart sets the scene for the ModifyPartScreen.
     * The selected item is populated in the ModifyPartScreen.
     * @param actionEvent the action is the button click
     * @throws IOException if changing screens fails
     */
    public void toModifyPart(ActionEvent actionEvent) throws IOException {

        try {
            modifyPart = partTable.getSelectionModel().getSelectedItem();
            modifyPartIndex = Inventory.getAllParts().indexOf(modifyPart);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/ModifyPartScreen.fxml"));
            loader.load();

            ModifyPartController MPaController = loader.getController();
            MPaController.SendPart(partTable.getSelectionModel().getSelectedItem());

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please select a part to modify.");
            alert.showAndWait();
        }
    }

        /**
         * The toAddProduct method sets the scene for the AddProductScreen.
         * @param actionEvent the action is the button click
         * @throws IOException if changing screens fails
         */
        public void toAddProduct (ActionEvent actionEvent) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("/view/AddProductScreen.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 800, 600);
            stage.setScene(scene);
            stage.show();
        }

        /**
         * The toModifyProduct method sets the scene for the ModifyProductScreen.
         * The selected item is populated in the ModifyProductScreen
         * @param actionEvent the action is the button click
         * @throws IOException if changing screens fails
         */
        public void toModifyProduct (ActionEvent actionEvent) throws IOException {

            try {
                modifyProduct = productTable.getSelectionModel().getSelectedItem();
                modifyProductIndex = Inventory.getAllProducts().indexOf(modifyProduct);

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/ModifyProductScreen.fxml"));
                loader.load();

                ModifyProductController MPrController = loader.getController();
                MPrController.SendProduct(productTable.getSelectionModel().getSelectedItem());

                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                Parent scene = loader.getRoot();
                stage.setScene(new Scene(scene));
                stage.show();
            } catch (NullPointerException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Please select a product to modify.");
                alert.showAndWait();
            }

        }

        /**
         * The toDeletePart method will delete a selected part in the part table if one is selected.
         * @param event the event is the button click
         */
        public void toDeletePart (ActionEvent event) {

                Part selectedPart = partTable.getSelectionModel().getSelectedItem();

                if (selectedPart == null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Please select a part to delete.");
                    alert.showAndWait();
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you wish to delete this part?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent() && result.get() == ButtonType.OK) {
                        Inventory.deletePart(selectedPart);
                    }
                    else {
                        partTable.getSelectionModel().clearSelection();
                    }
                }

            }

        /**
         * The toDeleteProduct method removes a selected product from the product table.
         * Products with associated parts cannot be removed.
         * @param actionEvent the action is the button click
         */
        public void toDeleteProduct (ActionEvent actionEvent) {

            Product selectedProduct = productTable.getSelectionModel().getSelectedItem();

            if (selectedProduct == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Please select a product to delete.");
                alert.showAndWait();
            }
            else if (!selectedProduct.getAllAssociatedParts().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Products with associated parts cannot be deleted.");
                alert.showAndWait();
                productTable.getSelectionModel().clearSelection();
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you wish to delete this product?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    Inventory.deleteProduct(selectedProduct);
                }
                else {
                    productTable.getSelectionModel().clearSelection();
                }
            }
        }

    /**
     * The onActionSearchPartButton method allows to user to search by partId or partial name.
     * @param event the event is the click of the search button
     */
    @FXML
    void onActionSearchPartButton(ActionEvent event) {
        String s = searchPartField.getText().toLowerCase();
        ObservableList<Part> parts = Inventory.lookupPart(s);
        try {
            if (parts.size() == 0) {
                int id = Integer.parseInt(s);
                Part np = Inventory.lookupPart(id);
                if (np != null) {
                    parts.add(np);
                }
                else if (parts.size() == 0 && np == null){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Invalid search.");
                    alert.setContentText("No parts match search criteria.");
                    alert.showAndWait();
                }
            }
        }
        catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid search.");
                alert.setContentText("No parts match search criteria.");
                alert.showAndWait();

        }
        partTable.setItems(parts);
        searchPartField.setText("");
        }

    /**
     * The onActionSearchProductButton method allows a user to search by productId or partial name.
     * @param event the event is the click of the search button
     */
    @FXML
    void onActionSearchProductButton(ActionEvent event) {
        String s = searchProductField.getText().toLowerCase();
        ObservableList<Product> products = Inventory.lookupProduct(s);
        try {
            if (products.size() == 0) {
                int id = Integer.parseInt(s);
                Product np = Inventory.lookupProduct(id);
                if (np != null) {
                    products.add(np);
                }
                else if (products.size() == 0 && np == null){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Invalid search.");
                    alert.setContentText("No products match search criteria.");
                    alert.showAndWait();
                }
            }
        }
        catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid search.");
            alert.setContentText("No products match search criteria.");
            alert.showAndWait();
        }
        productTable.setItems(products);
        searchProductField.setText("");
    }

    /**
     * The onActionSearchPartBar method allows a user to search by partId or partial name.
     * @param actionEvent the event is Enter/Return on the search bar
     */
    public void onActionSearchPartBar(ActionEvent actionEvent) {
        String s = searchPartField.getText().toLowerCase();
        ObservableList<Part> parts = Inventory.lookupPart(s);
        try {
            if (parts.size() == 0) {
                int id = Integer.parseInt(s);
                Part np = Inventory.lookupPart(id);
                if (np != null) {
                    parts.add(np);
                }
                else if (parts.size() == 0 && np == null){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Invalid search.");
                    alert.setContentText("No parts match search criteria.");
                    alert.showAndWait();
                }
            }
        }
        catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid search.");
            alert.setContentText("No parts match search criteria.");
            alert.showAndWait();
        }
        partTable.setItems(parts);
        searchPartField.setText("");
    }

    /**
     *The onActionSearchProductBar method allows a user to search by productId or partial name.
     * @param actionEvent the event is Enter/Return on the search bar.
     */
    public void onActionSearchProductBar(ActionEvent actionEvent) {
        String s = searchProductField.getText().toLowerCase();
        ObservableList<Product> products = Inventory.lookupProduct(s);
        try {
            if (products.size() == 0) {
                int id = Integer.parseInt(s);
                Product np = Inventory.lookupProduct(id);
                if (np != null) {
                    products.add(np);
                }
                else if (products.size() == 0 && np == null){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Invalid search.");
                    alert.setContentText("No products match search criteria.");
                    alert.showAndWait();
                }
            }
        }
        catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid search.");
            alert.setContentText("No products match search criteria.");
            alert.showAndWait();
        }
        productTable.setItems(products);
        searchProductField.setText("");
    }

}
