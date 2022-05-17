package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * The AddPartController class controls the AddPartScreen.fxml.
 */
public class AddPartController implements Initializable {

    @FXML
    private RadioButton inHouseRadioButton;
    @FXML
    private TextField partInventoryInput;
    @FXML
    private TextField partMaxInput;
    @FXML
    private TextField partMinInput;
    @FXML
    private TextField partNameInput;
    @FXML
    private TextField partPriceInput;
    @FXML
    private Label specialLabel;
    @FXML
    private TextField specialInput;

    private String valueError = new String();

    /**
     * The onInHouse method appropriately sets the label and prompt when inHouseRadioButton is selected.
     * @param event the event is the click of the inHouseRadioButton
     */
    @FXML
    void onInHouse(ActionEvent event) {
        specialLabel.setText("Machine ID");
        specialInput.setPromptText("##");
    }

    /**
     * The onOutsourced method appropriately sets the label and prompt when the outsourcedRadioButton is selected.
     * @param event the event is the click of the outsourceRadioButton
     */
    @FXML
    void onOutsourced(ActionEvent event) {
        specialLabel.setText("Company Name");
        specialInput.setPromptText("Aa");
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
     * This is the onActionSave method.
     * The onActionSave method checks the value of the input and tries to save the part and gives an error messages otherwise.
     * @param event the event is the click of the save button
     */
    @FXML
    void onActionSave(ActionEvent event) throws IOException {
        String name = partNameInput.getText();
        String price = partPriceInput.getText();
        String stock = partInventoryInput.getText();
        String min = partMinInput.getText();
        String max = partMaxInput.getText();
        String special = specialInput.getText();
        try {
            valueError = Inventory.inputChecker(name,Double.parseDouble(price),Integer.parseInt(stock),Integer.parseInt(min),Integer.parseInt(max),valueError);
            if(valueError.length() > 0) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Invalid input. Part not saved. See value error(s) below.");
                alert.setContentText(valueError);
                alert.showAndWait();
                valueError = "";
            }
            else {
                if (inHouseRadioButton.isSelected()) {
                    InHouse inhouse = new InHouse(0, null, 0.0, 0, 0, 0, 0);
                    inhouse.setId(Inventory.getAPartId());
                    inhouse.setName(name.toLowerCase());
                    inhouse.setPrice(Double.parseDouble(price));
                    inhouse.setStock(Integer.parseInt(stock));
                    inhouse.setMin(Integer.parseInt(min));
                    inhouse.setMax(Integer.parseInt(max));
                    inhouse.setMachineId(Integer.parseInt(special));
                    Inventory.addPart(inhouse);
                } else {
                    Outsourced outsourced = new Outsourced(0, null, 0.0, 0, 0, 0, null);
                    outsourced.setId((Inventory.getAPartId()));
                    outsourced.setName(name.toLowerCase());
                    outsourced.setPrice(Double.parseDouble(price));
                    outsourced.setStock(Integer.parseInt(stock));
                    outsourced.setMin(Integer.parseInt(min));
                    outsourced.setMax(Integer.parseInt(max));
                    outsourced.setCompanyName(special);
                    Inventory.addPart(outsourced);
                }
                Parent root = FXMLLoader.load(getClass().getResource("/view/MenuScreen.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root, 800, 600);
                stage.setScene(scene);
                stage.show();
            }

        }
        catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Adding Part");
            alert.setContentText("Please enter a valid value for each text field.");
            alert.showAndWait();
        }
    }

    /**
     * The initialize method is executed when the scene sets.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



    }

}
