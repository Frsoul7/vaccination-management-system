package app.ui.gui;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/***
 * @author Paulo Maio <pam@isep.ipp.pt>
 *         Edgar Moreira <1010100@isep.ipp.pt>
 *         Fernando Ribeiro <1060064@isep.ipp.pt>
 *         José Silva <1060568@isep.ipp.pt>
 *         Pedro Gomes <1060588@isep.ipp.pt>
 */

//Teste
public class MainFX extends Application {

    /**
     * "fake stage" used for changing stages
     */
    private static Stage stg;


    @FXML
    private TextField userEmail;
    @FXML
    private PasswordField userPass;
    @FXML
    private Button loginButton;
    @FXML
    private Label wrongLogin;

    private MainFX mainApp;
    private Stage stage;
    private static final double MINIMUM_WINDOW_WIDTH = 600;
    private static final double MINIMUM_WINDOW_HEIGHT = 400;
    private final double SCENE_WIDTH = 650;
    private final double SCENE_HEIGHT = 450;

    /**
     * Method used to start the application
     *
     * @param primaryStage
     *
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Welcome2UI.fxml"));
        primaryStage.setTitle("WAGMI");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();

        // To avoid letting the user hard close the App
        primaryStage.setOnCloseRequest(event -> {
            event.consume();
            hardLogout(primaryStage);
        });


        Image appIcon = new Image("/images/WAGMIicon.png");
        primaryStage.getIcons().add(appIcon);

    }

    /**
     * Method to ask user if wants to hard close the app
     * @param stage  - primary stage
     */
    public void hardLogout(Stage stage){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Close the App");
        alert.setHeaderText("Do you really want to exit the App? Any unsaved data will be lost!");
        alert.setContentText("Press Cancel to go back");
        if (alert.showAndWait().get() == ButtonType.OK){
            System.out.println("You will logout!");
            stage.close();
        }

    }


}