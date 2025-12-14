package app.ui.gui;

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
import javafx.scene.control.RadioButton;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class CoordinatorGUI_2 implements Initializable {

    @FXML
    private Button importCSVFileButton;
    @FXML
    private Label FileNotLoaded;

    @FXML
    private ListView ShowList;
    @FXML
    private RadioButton RadioArrivalTime;
    @FXML
    private RadioButton RadioLeavingTime;
    @FXML
    private RadioButton RadioAscending;
    @FXML
    private RadioButton RadioDescending;
    @FXML
    private Button SortButton;
    @FXML
    private Button BackButton;
    @FXML
    private Button LogoutButton;


    private Window stage;

    //private ImportCSVUI;

    /**
     * Method to initialize the scene "Coordinator Menu"
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    @FXML
    public void ImportCSV(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV " + "File", "*.csv"));
        File file = fc.showOpenDialog(this.stage);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file.getAbsolutePath()));

            ObservableList<String> items = FXCollections.observableArrayList();
            items.add("Centro 1 Porto");
            items.add("Centro 2 Coimbra");
            items.add("Centro 3 Guarda");

            ShowList.setItems(items);

            FileNotLoaded.setText("File loaded correctly");

        }
        catch(FileNotFoundException|NullPointerException ex) {
            FileNotLoaded.setText("File not loaded correctly!");
            // throw new RuntimeException(ex);
        }

    }

    @FXML
    private void BackToMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/coordinatorGUI_0.fxml"));
        Stage stage = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void SessionLogout(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Welcome2UI.fxml"));
        Stage stage = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



}
