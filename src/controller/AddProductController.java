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
import model.Inventory;
import model.Part;
import model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * The AddProductController class controls the AddProductScreen.fxml.
 */
public class AddProductController implements Initializable {

    Product product = new Product(1000, null, 0, 0, 0, 0);

    @FXML
    public TableView<Part> associatedPartTable;
    @FXML
    private TableView<Part> allPartTable;
    @FXML
    private TableColumn<Part, Integer> associatedPartIdCol;
    @FXML
    private TableColumn<Part, Integer> associatedPartInventoryCol;
    @FXML
    private TableColumn<Part, String> associatedPartNameCol;
    @FXML
    private TableColumn<Part, Double> associatedPartPriceCol;
    @FXML
    private TableColumn<Part, Integer> partIdCol;
    @FXML
    private TableColumn<Part, Integer> partInventoryCol;
    @FXML
    private TableColumn<Part, String> partNameCol;
    @FXML
    private TableColumn<Part, Double> partPriceCol;
    @FXML
    private TextField productInventoryInput;
    @FXML
    private TextField productMaxInput;
    @FXML
    private TextField productMinInput;
    @FXML
    private TextField productNameInput;
    @FXML
    private TextField productPriceInput;
    @FXML
    private TextField searchPartField;

    private String valueError = new String();

    /**
     * The onActionSearchPartBar method allows a user to search by partId or by partial name.
     * @param event the event is Enter/Return on the search bar
     */
    @FXML
    void onActionSearchPartBar(ActionEvent event) {
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
        allPartTable.setItems(parts);
        searchPartField.setText("");
    }

    /**
     * The onActionSearchPartButton method allows a user to search by partId or by partial name.
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
        allPartTable.setItems(parts);
        searchPartField.setText("");
    }

    /**
     * The onActionAddAssociatedPart method allows a user to add a selected part to the product's associated parts list.
     * @param event the event is the click of the add button
     */
    @FXML
    void onActionAddAssociatedPart(ActionEvent event) {
        Part selectedPart = allPartTable.getSelectionModel().getSelectedItem();
        if (selectedPart == null)
            return;
        else
            product.addAssociatedPart(selectedPart);
    }

    /**
     * The onActionCancel method returns to the MenuScreen upon confirmation and does not record changes.
     * @param event the event is the click of the cancel button
     */
    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Any unsaved information will be lost. Do you wish to continue?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Parent root = FXMLLoader.load(getClass().getResource("/view/MenuScreen.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 800, 600);
            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     * The onActionRemoveAssociatedPart method allows a user to remove a part from a product's associated parts list.
     * @param event the event is the clock of the remove button
     */
    @FXML
    void onActionRemoveAssociatedPart(ActionEvent event) {
        Part selectedPart = associatedPartTable.getSelectionModel().getSelectedItem();
        if(selectedPart == null) {
            return;
        }
        else {
            product.deleteAssociatedPart(selectedPart);
        }
    }

    /**
     * The onActionSave method allows a user to save a new product and returns to the MenuScreen.
     * @param event the event is the click of the save button
     */
    @FXML
    void onActionSave(ActionEvent event) throws IOException {
        String name = productNameInput.getText();
        String price = productPriceInput.getText();
        String stock = productInventoryInput.getText();
        String min = productMinInput.getText();
        String max = productMaxInput.getText();
        try {
            valueError = Inventory.inputChecker(name,Double.parseDouble(price),Integer.parseInt(stock),Integer.parseInt(min),Integer.parseInt(max),valueError);
            if(valueError.length() > 0) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Invalid input. Product not saved. See value error(s) below.");
                alert.setContentText(valueError);
                alert.showAndWait();
                valueError = "";
            }
            else {
                product.setId(Inventory.getAProductId());
                product.setName(name.toLowerCase());
                product.setPrice(Double.parseDouble(price));
                product.setStock(Integer.parseInt(stock));
                product.setMin(Integer.parseInt(min));
                product.setMax(Integer.parseInt(max));
                Inventory.addProduct(product);

                Parent root = FXMLLoader.load(getClass().getResource("/view/MenuScreen.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root, 800, 600);
                stage.setScene(scene);
                stage.show();
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Adding Product");
            alert.setContentText("Please enter a valid value for each text field.");
            alert.showAndWait();
        }

    }

    /**
     * The initialize method is executed when the scene sets.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allPartTable.setItems(Inventory.getAllParts());
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        associatedPartTable.setItems(product.getAllAssociatedParts());
        associatedPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedPartInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}
