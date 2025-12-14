package app.ui.gui;

import app.controller.App;
import app.controller.AuthController;
import app.controller.SerializationController;
import app.domain.model.Company;
import app.interfaces.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class Welcome2GUI implements Initializable, Constants {


    private Company company;
    private AuthController ctrl;
    private App app;
    private SerializationController serCont;

    @FXML
    private MainFX mainApp;
    @FXML
    private MenuItem FileSaveButton;
    @FXML
    private MenuItem FileLoadButton;
    @FXML
    private MenuItem QuitButton;
    @FXML
    private MenuItem AboutButton;
    @FXML
    private TextField userEmail;
    @FXML
    private PasswordField userPass;
    @FXML
    private Button loginButton;
    @FXML
    private Label wrongLogin;


    public void setMainApp(MainFX mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.userEmail.setText(null);
        this.userPass.setText(null);
        this.app = App.getInstance();
        this.company = app.getCompany();
        company.setSerializationController();
        this.serCont = company.getSerializationController();
        // Automatically load serialized data on startup
        try {
            company.getSerializationController().readFiles();
        } catch (Exception e) {
            System.err.println("Warning: Could not load serialized data. " + e.getMessage());
        }
    }

    @FXML
    private void userLogin(ActionEvent event) throws IOException {
        ctrl = new AuthController();
        checkLogin(event, ctrl);
    }

    private void checkLogin(ActionEvent event, AuthController ctrl) throws IOException {
        //ctrl.doLogin(userEmail.getText(),userPass.getText());
        if(userEmail.getText() == null || userPass.getText() == null ||
           (userEmail.getText().isEmpty() && userPass.getText().isEmpty())) {
            wrongLogin.setText("Please enter your data.");
            return;
        }
        boolean result = ctrl.doLogin(userEmail.getText(), userPass.getText());

        if(result) {
            System.out.println("true");

            List<UserRoleDTO> userRolesList = ctrl.getUserRoles();

            for(UserRoleDTO userRole : userRolesList) {
                System.out.println(userRole.getId() + " " + userRole.getDescription());
            }

            // Used to send data in a safe way between the scenes
            ControlResourceBundle crb = new ControlResourceBundle();
            crb.setCtrl(ctrl);


            Parent root = FXMLLoader.load(getClass().getResource("/fxml/vaccinationCenterSelection.fxml"), crb);
            Stage stage = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);

        } else {
            System.out.println("false");
            wrongLogin.setText("Wrong user email and password.");
        }

    }

    @FXML
    private void saveDataButton(ActionEvent event) throws IOException {
        serCont.saveData(FILE_NAME_EMP, company.getEmployeeStore(), "Employees");
        serCont.saveData(FILE_NAME_SNSUSER, company.getSnsUserStore(), "Sns Users");
        serCont.saveData(FILE_NAME_VACCINE_TYPE, company.getVaccineTypeStore(), "Vaccine Type");
        serCont.saveData(FILE_NAME_VACCINE, company.getVaccineStore(), "Vaccine");
        serCont.saveData(FILE_NAME_VACCINATION_CENTER, company.getVaccinationCenterStore(), "Vaccination Centers");
        serCont.saveDataEmployeesSnsUserAuthFacade(FILE_NAME_SNS_US_EMAIL_PW, "SnsUsersEmailsAndPasswords");
        serCont.saveDataEmployeesSnsUserAuthFacade(FILE_NAME_EMPLOYEE_EMAIL_PW, "EmployeeEmailsAndPasswords");

    }

    /**
     * Load data from File Menu > Load
     *
     * @param event press Load option
     *
     * @throws IOException
     */
    @FXML
    private void loadDataButton(ActionEvent event) throws IOException {
        company.getSerializationController().readFiles();
    }

    /**
     * Save data from File Menu > Save
     *
     * @param event press Load option
     *
     * @throws IOException
     */
    @FXML
    private void closeApp(ActionEvent event) throws IOException {
        javafx.application.Platform.exit();
    }


    @FXML
    private void aboutUs(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About WAGMI Project");
        alert.setHeaderText("Thank you for choosing the App of WAGMI - We Are Gonna Make It!\n" +
                            "Version 1.0 Final\n\n" +
                            "DEVELOPMENT TEAM:\n" +
                            "Edgar Moreira\n" +
                            "Fernando Ribeiro\n" +
                            "Inês Veiga\n" +
                            "José Silva\n" +
                            "Pedro Gomes\n" +
                            "Ricardo Soares\n\n" +
                            "THANKS TO:\n" +
                            "Prof. Ana Barata\n" +
                            "Prof. António Sousa\n" +
                            "Prof. Alexandra Gavina\n" +
                            "Prof. Carlos Ferreira\n" +
                            "Prof. Filipe Fonseca\n" +
                            "Prof. Jorge Duarte\n" +
                            "\n" +
                            "Projecto Integrador 2021/2022\n" +
                            "LEI - ISEP" +
                            "");
        alert.setContentText("Press OK to return to the App");
        alert.show();

    }
}



