package app.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CoordinatorGUI_0 implements Initializable {


    @FXML
    private Button LogoutButton;
    @FXML
    private Button option1Button;
    @FXML
    private Button option2Button;
    @FXML
    private Button option3Button;

    int vaccinationCenterId;


    /**
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vaccinationCenterId = (int) resourceBundle.getObject("");
    }

    @FXML
    private void SessionLogout(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Welcome2UI.fxml"));
        Stage stage = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    private void changeScene(String resourceUrl, ActionEvent event) throws IOException {
        VaccinationCenterIdResourceBundle vcid = new VaccinationCenterIdResourceBundle();
        vcid.setVaccinationCenterId(vaccinationCenterId);

        Parent root = FXMLLoader.load(getClass().getResource(resourceUrl),vcid);
        Stage stage = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Method to select the
     * @param event
     * @throws IOException
     */
    @FXML
    private void chooseOption1(ActionEvent event) throws IOException{
        changeScene("/fxml/coordinatorGUI_1.fxml", event);
    }
    @FXML
    private void chooseOption2(ActionEvent event) throws IOException{
        changeScene("/fxml/coordinatorGUI_4.fxml", event);
    }
    @FXML
    private void chooseOption3(ActionEvent event) throws IOException{
        changeScene("/fxml/coordinatorGUI_3.fxml", event);
    }


}
