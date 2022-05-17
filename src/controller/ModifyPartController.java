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
import model.Part;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * The ModifyPartController class controls the ModifyPartScreen.fxml.
 */
public class ModifyPartController implements Initializable {

    @FXML
    private RadioButton inHouseRadioButton;
    @FXML
    private RadioButton outsourcedRadioButton;
    @FXML
    private TextField partIdInput;
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
    private TextField specialInput;
    @FXML
    private Label specialLabel;

    private String valueError = new String();

    /**
     * The onInHouse method appropriately sets the label and prompt when the inHouseRadioButton is selected.
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
     * The onActionSave method checks the value of the input and tries to save the part and gives an error messages otherwise.
     * @param event the event is the click of the save button
     */
    @FXML
    void onActionSave(ActionEvent event) throws Exception {
        String id = partIdInput.getText();
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
                alert.setHeaderText("Invalid input. Part not modified. See value error(s) below.");
                alert.setContentText(valueError);
                alert.showAndWait();
                valueError = "";
            }
            else {
                if (inHouseRadioButton.isSelected()) {
                    InHouse inhouse = new InHouse(0, null, 0.0, 0, 0, 0, 0);
                    inhouse.setId(Integer.parseInt(id));
                    inhouse.setName(name.toLowerCase());
                    inhouse.setPrice(Double.parseDouble(price));
                    inhouse.setStock(Integer.parseInt(stock));
                    inhouse.setMin(Integer.parseInt(min));
                    inhouse.setMax(Integer.parseInt(max));
                    inhouse.setMachineId(Integer.parseInt(special));
                    Inventory.updatePart(MenuController.getModifyPartIndex(), inhouse);
                } else {
                    Outsourced outsourced = new Outsourced(0, null, 0.0, 0, 0, 0, null);
                    outsourced.setId(Integer.parseInt(id));
                    outsourced.setName(name.toLowerCase());
                    outsourced.setPrice(Double.parseDouble(price));
                    outsourced.setStock(Integer.parseInt(stock));
                    outsourced.setMin(Integer.parseInt(min));
                    outsourced.setMax(Integer.parseInt(max));
                    outsourced.setCompanyName(special);
                    Inventory.updatePart(MenuController.getModifyPartIndex(), outsourced);
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
     * The SendPart method is utilized by the MenuController to populate the selected data into the ModifyPartScreen.
     * @param part the part to modify
     */
    public void SendPart(Part part) {
        partIdInput.setText(String.valueOf(part.getId()));
        partNameInput.setText(String.valueOf(part.getName()));
        partInventoryInput.setText(String.valueOf(part.getStock()));
        partPriceInput.setText(String.valueOf(part.getPrice()));
        partMaxInput.setText(String.valueOf(part.getMax()));
        partMinInput.setText(String.valueOf(part.getMin()));

        if (part instanceof InHouse){
            inHouseRadioButton.fire();
            specialInput.setText(String.valueOf(((InHouse) part).getMachineId()));
        }
        else {
            outsourcedRadioButton.fire();
            specialInput.setText(String.valueOf(((Outsourced) part).getCompanyName()));
        }
    }

    /**
     * The initialize method is executed when the scene sets.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



    }

}
