package app.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NurseGUI implements Initializable {

    @FXML
    private Label lbTest;
    @FXML
    private Button btnTest;
    @FXML
    private Button btnJumpToVaccAdministrationMenu;

    @FXML
    private Button btnSessionLogout;
    private int vaccCenterId;

    public NurseGUI() {

    }

    /**
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void initVaccCenterId(int Id) {
        this.vaccCenterId = Id;
    }

    @FXML
    private void btnTestAction(ActionEvent event) throws IOException {
        lbTest.setText(String.valueOf(vaccCenterId));
    }

    @FXML
    private void btnJumpToVaccAdministrationMenuAction(ActionEvent event) throws IOException {
        changeSceneToPassVCId("/fxml/vaccineAdministrationUI.fxml", event);
    }

    private void changeScene(String resourceUrl, ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource(resourceUrl));
        Stage stage = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void changeSceneToPassVCId(String resourceUrl, ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(resourceUrl));
        Parent root = loader.load();
        //Parent root = FXMLLoader.load(getClass().getResource(resourceUrl));
        Stage stage = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        VaccineAdministrationGUI controller = loader.getController();
        controller.initFirstParameters(vaccCenterId);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void btnSessionLogoutAction(ActionEvent event) throws IOException {
        changeScene("/fxml/Welcome2UI.fxml", event);
    }
}
