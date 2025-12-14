package app.ui.gui;

import app.controller.App;
import app.controller.AuthController;
import app.controller.SelectVaccinationCenterController;
import app.domain.model.Company;
import app.dto.VaccinationCenterDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class ChooseVaccinationCenterGUI implements Initializable {

    /**
     * List of the vaccination centers to be seen on ListView
     */
    @FXML
    private ListView listVac;

    @FXML
    private Button selectContinueButton;

    @FXML
    private Label vaccinationCenterNotSelected;

    String currentVaccinationCenter;
private int selectedIndex;
   private int vaccinationCenterId;
    private SelectVaccinationCenterController ctrl;

    private App app;

    private Company company;

    private AuthController authCtrl;

    private List<VaccinationCenterDTO> lVaccinationCenters;

    private void loadSerializationData() {
        /*
        this.app = App.getInstance();
        Properties props = getProperties();
        this.company = new Company(props.getProperty(PARAMS_COMPANY_DESIGNATION));
        company.getSerializationController().readFiles();

         */
    }

    /**
     * Method to initialize the scene "Choose a Vaccination Center"
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        authCtrl = (AuthController)resourceBundle.getObject("");
        loadSerializationData();
        ctrl = new SelectVaccinationCenterController();

        lVaccinationCenters = ctrl.getVaccinationCenters();

        System.out.println("begin FOR");
        ObservableList<String> items = FXCollections.observableArrayList();

        for(VaccinationCenterDTO vaccinationCenter : lVaccinationCenters) {
            items.add(vaccinationCenter.getVaccinationCenterId() + " " + vaccinationCenter.getName() + " " +
                      vaccinationCenter.getVaccinationCenterType());

            System.out.println(vaccinationCenter.getVaccinationCenterId() + " " + vaccinationCenter.getName() + " " +
                               vaccinationCenter.getVaccinationCenterType());


        }

        listVac.setItems(items);
    }

    /**
     * Method to select the vaccination center from the ListView and get vaccination center index
     *
     * @param event
     */
    @FXML
    private void SelectAndContinue(ActionEvent event) throws IOException {

        selectedIndex= listVac.getSelectionModel().getSelectedIndex();
        if (selectedIndex==-1){
            vaccinationCenterNotSelected.setText("Please select a vaccination center");
            return;
        }
        vaccinationCenterId=lVaccinationCenters.get(selectedIndex).getVaccinationCenterId();





        List<UserRoleDTO> userRolesList = authCtrl.getUserRoles();

        for(UserRoleDTO userRole : userRolesList) {
            System.out.println(userRole.getId() + " " + userRole.getDescription());
            switch(userRole.getId()) {
                case "ROLE_CENTERCOORNIDATOR":
                    changeScene("/fxml/coordinatorGUI_0.fxml", event);
                    break;
                case "ROLE_NURSE":
                    changeSceneToPassVCId("/fxml/nurseUI.fxml", event);
                    break;
                case "ROLE_ADMIN":
                    changeScene("/fxml/nurseUI.fxml", event);
                    break;
                case "ROLE_RECEPTIONIST":
                    showReceptionistNotAvailableAlert();
                    break;
                case "SNS USER":
                    showSnsUserNotAvailableAlert();
                    break;

            }

        }


    }

    /**
     * Method to change scene generic
     * @param resourceUrl
     * @param event
     * @throws IOException
     */
    private void changeScene(String resourceUrl, ActionEvent event) throws IOException {
        VaccinationCenterIdResourceBundle vcid = new VaccinationCenterIdResourceBundle();
        vcid.setVaccinationCenterId(vaccinationCenterId);


        Parent root = FXMLLoader.load(getClass().getResource(resourceUrl), vcid);
        Stage stage = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    private void changeSceneToPassVCId(String resourceUrl, ActionEvent event) throws IOException {

        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource(resourceUrl));
        Parent root =loader.load();
        //Parent root = FXMLLoader.load(getClass().getResource(resourceUrl));
        Stage stage = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        List<UserRoleDTO> userRolesList = authCtrl.getUserRoles();

        for(UserRoleDTO userRole : userRolesList) {
            System.out.println(userRole.getId() + " " + userRole.getDescription());
            switch(userRole.getId()) {
                case "ROLE_CENTERCOORNIDATOR":
                    //preencher
                    break;
                case "ROLE_NURSE":
                    NurseGUI controller=loader.getController();
                    controller.initVaccCenterId(vaccinationCenterId);
                    break;
                case "ROLE_ADMIN":
                    //preencher
                    break;
                case "ROLE_RECEPTIONIST":
                    //preencher
                    break;

            }

        }



        stage.setScene(scene);
        stage.show();

    }

    private void showSnsUserNotAvailableAlert() {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alert.setTitle("SNS User GUI Not Available");
        alert.setHeaderText("SNS User Interface Not Implemented");
        alert.setContentText("The graphical user interface for SNS Users (vaccine scheduling) is not yet available.\n\n" +
                           "Please use the console mode instead:\n" +
                           "1. Close this application\n" +
                           "2. Run: java -jar Sem2App-1.0-SNAPSHOT-jar-with-dependencies.jar --console\n" +
                           "3. Login with your SNS User credentials");
        alert.showAndWait();
    }

    private void showReceptionistNotAvailableAlert() {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alert.setTitle("Receptionist GUI Not Available");
        alert.setHeaderText("Receptionist Interface Not Implemented");
        alert.setContentText("The graphical user interface for Receptionists is not yet available.\n\n" +
                           "Receptionist features include:\n" +
                           "- SNS User Registration\n" +
                           "- SNS User Check-in (Arrival Register)\n\n" +
                           "Please use the console mode instead:\n" +
                           "1. Close this application\n" +
                           "2. Run: java -jar Sem2App-1.0-SNAPSHOT-jar-with-dependencies.jar --console\n" +
                           "3. Login with your Receptionist credentials");
        alert.showAndWait();
    }

}
