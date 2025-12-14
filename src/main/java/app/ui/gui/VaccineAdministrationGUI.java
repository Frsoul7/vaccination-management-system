package app.ui.gui;

import app.controller.RecordAdministrationOfAVaccineToSnsUserController;
import app.domain.model.utils.DateCustom;
import app.dto.*;
import app.ui.console.utils.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class VaccineAdministrationGUI implements Initializable {
    @FXML
    private Button btnCancelOperation;
    @FXML
    private Button btnShowInfoHealthCondit;
    @FXML
    private Button btnTypeDate;
    @FXML
    private Button btnCheckDosage;
    @FXML
    private Button btnExit;
    @FXML
    private Button btnRecordVaccinationRecords;
    @FXML
    private Label lbInfoHealthConditions;
    @FXML
    private Label lblVaccAdministrationDate;
    @FXML
    private Label lblVaccAdministrationTime;
    @FXML
    private Label lblDosageVolume;

    @FXML
    private Label lblProgressBoard;
    @FXML
    private Label lblProgressBoardTxt;
    @FXML
    private ComboBox cboxWaitingRoom;
    @FXML
    private ComboBox cboxVaccineLotNumber;

    @FXML
    private Circle circleOne;
    @FXML
    private Circle circleTwo;
    @FXML
    private Circle circleThree;
    @FXML
    private Circle circleFour;
    @FXML
    private Circle circleFive;


    private Stage newDateSelectionStage;

    private Stage newTimeSelectionStage;

    private Stage newRecordVaccAdminStage;

    private int vaccinationCenterId;

    private int flowStep;

    private String vaccAdministratrionTime;

    private DateCustom vaccDaministrationDate;

    private WaitingRoomDTO snsUserSelected;

    private VaccineDTO vaccineWithLotNumberSelected;
    private Background backgroungYellow;
    private Background backgroungGreen;
    private final Paint paintRed;
    private final Paint paintGreen;
    private final Paint paintGrey;
    private final Paint paintYellow;
    private final RecordAdministrationOfAVaccineToSnsUserController controller;

    private static final String VACC_ADMINISTRATION_TIME_BY_DEFAULT = "00:00";

    //region Init ---------------------------------------------------------------------
    public VaccineAdministrationGUI() {
        // this.vaccinationCenterId = vaccinationCenterId;
        controller = new RecordAdministrationOfAVaccineToSnsUserController();
        this.snsUserSelected = null;
        this.vaccineWithLotNumberSelected = null;
        vaccAdministratrionTime = VACC_ADMINISTRATION_TIME_BY_DEFAULT;

        flowStep = 0;
        paintRed = Color.INDIANRED;
        paintGreen = Color.GREEN;
        paintGrey = Color.GREY;
        paintYellow = Color.YELLOW;

        backgroungYellow = new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY));
        backgroungGreen= new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        changeStepConditions(flowStep);
        System.out.println("2 PP");
    }
    //endregion Init

    //region 1. Shows sns users in waiting room and picks one --------------------------------------------
    public void initFirstParameters(int Id) {
        setVaccCenterId(Id);

        try {
            ObservableList<WaitingRoomDTO> options = FXCollections.observableArrayList();
            List<WaitingRoomDTO> listWaitingRoomDTO = controller.getListOfSnsUsers(vaccinationCenterId);
            if(!listWaitingRoomDTO.isEmpty()) {
                for(WaitingRoomDTO obj : listWaitingRoomDTO) {
                    options.add(obj);
                }
                cboxWaitingRoom.setItems(options);

            } else {
                Utils.showText("Attention!: The waiting room list is empty");
                return;
            }
        }
        catch(RuntimeException ex) {
            Utils.showText(ex.getMessage());
            Utils.showText("[warning] the list could not be presented, please contact your maintenance team!");
            return;
        }
    }

    public void setVaccCenterId(int Id) {
        this.vaccinationCenterId = Id;
    }

    @FXML
    private void cboxWaitingRoomAction(ActionEvent event) throws IOException {
        snsUserSelected = (WaitingRoomDTO)cboxWaitingRoom.getSelectionModel().getSelectedItem();

        changeStepConditions(flowStep = 1);
    }

    //endregion 1

    //region 2. Shows sns user info and health conditions ----------------------------------------
    @FXML
    private void btnShowInfoHealthConditAction(ActionEvent event) throws IOException {
        InfoAndHealthConditionsDTO infoAndHealthConditionsDto =
                controller.getInfoAndHealthConditions(snsUserSelected.getSnsUserNumber());

        lbInfoHealthConditions.setText(infoAndHealthConditionsDto.toString());

        changeStepConditions(flowStep = 2);
    }

    //endregion 2.

    //region 3. Sets vaccination Date, shows vaccines list and picks one vaccine by lot number--

    @FXML
    private void btnTypeDateAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/selectDate.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);

        newDateSelectionStage = new Stage();
        newDateSelectionStage.initModality(Modality.APPLICATION_MODAL);
        newDateSelectionStage.setTitle("Vaccine administration date selection");
        newDateSelectionStage.setResizable(false);
        newDateSelectionStage.setScene(scene);

        SelectDateGUI popupDate = loader.getController();
        popupDate.associateParentGUI(this);

        newDateSelectionStage.show();
    }


    public void setVaccDaministrationDate(DateCustom vaccDaministrationDate) {
        this.vaccDaministrationDate = new DateCustom(vaccDaministrationDate);
        lblVaccAdministrationDate.setText(vaccDaministrationDate.toString());
        cboxVaccineLotNumber.getItems().clear();

        changeStepConditions(flowStep = 3);

        try {
            ObservableList<VaccineDTO> options = FXCollections.observableArrayList();
            List<VaccineDTO> listVaccineDTO = controller.getListOfVaccines(snsUserSelected.getSnsUserNumber(),
                                                                           this.vaccDaministrationDate.toString());
            if(!listVaccineDTO.isEmpty()) {
                for(VaccineDTO obj : listVaccineDTO) {
                    options.add(obj);
                }
                cboxVaccineLotNumber.setItems(options);

            } else {
                Utils.showText("Attention!: The vaccines list is empty");

            }
        }
        catch(RuntimeException ex) {
            Utils.showText(ex.getMessage());
            Utils.showText("[warning] the list could not be presented, please contact your maintenance team!");

        }
    }

    @FXML
    private void cboxVaccineLotNumberAction(ActionEvent event) throws IOException {
        vaccineWithLotNumberSelected = (VaccineDTO)cboxVaccineLotNumber.getSelectionModel().getSelectedItem();
        changeStepConditions(flowStep = 4);
    }
    //endregion 3.---------

    //region 4. Shows vaccine administration dosage for that sns user and types vaccination hour -----------

    @FXML
    private void btnCheckDosageAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/showDosageAndTypeTime.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);

        newTimeSelectionStage = new Stage();
        newTimeSelectionStage.initModality(Modality.APPLICATION_MODAL);
        newTimeSelectionStage.setTitle("Final instructions to give the vaccine");
        newTimeSelectionStage.setResizable(false);
        newTimeSelectionStage.setScene(scene);

        ShowDosageAndTypeTimeGUI popupTime = loader.getController();
        popupTime.associateParentGUI(this);

        VaccAdministrationDosageDTO vaccAdministrationDosageDto =
                controller.getVaccineAdministrationProcess(vaccineWithLotNumberSelected.getLotNumber());

        popupTime.writeLabelDosageInformation(vaccAdministrationDosageDto);
        writeLabelDosageVolume(vaccAdministrationDosageDto.getVaccineDosage());

        newTimeSelectionStage.show();
    }

    public void writeLabelDosageVolume(int dosageVolume) {
        lblDosageVolume.setText(Integer.toString(dosageVolume));
    }

    public void setVaccAdministratrionTime(String vaccAdministratrionTime) {
        this.vaccAdministratrionTime = vaccAdministratrionTime;
        lblVaccAdministrationTime.setText(vaccAdministratrionTime);

        changeStepConditions(flowStep = 5);
    }

    //endregion 4.

    //region 5. Shows vaccine administration resume and asks to permanently record the information ---------

    @FXML
    private void btnRecordVaccinationRecordsAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/recordVaccineAdministration.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);

        newRecordVaccAdminStage = new Stage();
        newRecordVaccAdminStage.initModality(Modality.APPLICATION_MODAL);
        newRecordVaccAdminStage.setTitle("Final record");
        newRecordVaccAdminStage.setResizable(false);
        newRecordVaccAdminStage.setScene(scene);

        RecordVaccineAdministrationGUI popupFinalRecord = loader.getController();
        popupFinalRecord.associateParentGUI(this);

        VaccRecordsDTO vaccRecordsDto = controller.recordAdministrationOfAVaccine(vaccAdministratrionTime);

        popupFinalRecord.writeLabelFinalRecordInfo(vaccRecordsDto);

        newRecordVaccAdminStage.show();
    }

    //endregion 5.

    //region 6. Permanently save vaccine records and start TIMER -----------------------------------------

    public void saveRecordsPermanently() {
        controller.saveVaccineRecords();
        changeStepConditions(flowStep = 6);
    }

    //endregion 6.

    //region style management by flowStep --------------------------------------------------------
    private void changeStepConditions(int flowStep) {
        switch(flowStep) {
            case 0:
                //Progress status
                lblProgressBoard.setBackground(backgroungYellow);
                lblProgressBoardTxt.setText("in progress...");

                //Color
                circleOne.setFill(paintYellow);
                circleTwo.setFill(paintRed);
                circleThree.setFill(paintRed);
                circleFour.setFill(paintRed);
                circleFive.setFill(paintRed);

                //Disable
                cboxWaitingRoom.setDisable(false);
                btnShowInfoHealthCondit.setDisable(true);
                btnTypeDate.setDisable(true);
                cboxVaccineLotNumber.setDisable(true);
                btnCheckDosage.setDisable(true);
                btnRecordVaccinationRecords.setDisable(true);

                //Visible
                btnCancelOperation.setVisible(true);
                btnExit.setVisible(false);
                break;
            case 1:
                //Progress status
                lblProgressBoard.setBackground(backgroungYellow);
                lblProgressBoardTxt.setText("in progress...");

                //Color
                circleOne.setFill(paintGreen);
                circleTwo.setFill(paintYellow);
                circleThree.setFill(paintRed);
                circleFour.setFill(paintRed);
                circleFive.setFill(paintRed);

                //Disable
                cboxWaitingRoom.setDisable(false);
                btnShowInfoHealthCondit.setDisable(false);
                btnTypeDate.setDisable(true);
                cboxVaccineLotNumber.setDisable(true);
                btnCheckDosage.setDisable(true);
                btnRecordVaccinationRecords.setDisable(true);

                //Visible
                btnCancelOperation.setVisible(true);
                btnExit.setVisible(false);
                break;
            case 2:
                //Progress status
                lblProgressBoard.setBackground(backgroungYellow);
                lblProgressBoardTxt.setText("in progress...");

                //Color
                circleOne.setFill(paintGreen);
                circleTwo.setFill(paintGreen);
                circleThree.setFill(paintYellow);
                circleFour.setFill(paintRed);
                circleFive.setFill(paintRed);

                //Disable
                cboxWaitingRoom.setDisable(false);
                btnShowInfoHealthCondit.setDisable(false);
                btnTypeDate.setDisable(false);
                cboxVaccineLotNumber.setDisable(true);
                btnCheckDosage.setDisable(true);
                btnRecordVaccinationRecords.setDisable(true);

                //Visible
                btnCancelOperation.setVisible(true);
                btnExit.setVisible(false);

                break;
            case 3:
                //Progress status
                lblProgressBoard.setBackground(backgroungYellow);
                lblProgressBoardTxt.setText("in progress...");

                //Color
                circleOne.setFill(paintGreen);
                circleTwo.setFill(paintGreen);
                circleThree.setFill(paintYellow);
                circleFour.setFill(paintRed);
                circleFive.setFill(paintRed);

                //Disable
                cboxWaitingRoom.setDisable(true);
                btnShowInfoHealthCondit.setDisable(true);
                btnTypeDate.setDisable(false);
                cboxVaccineLotNumber.setDisable(false);
                btnCheckDosage.setDisable(true);
                btnRecordVaccinationRecords.setDisable(true);

                //Visible
                btnCancelOperation.setVisible(true);
                btnExit.setVisible(false);

                break;
            case 4:
                //Progress status
                lblProgressBoard.setBackground(backgroungYellow);
                lblProgressBoardTxt.setText("in progress...");

                //Color
                circleOne.setFill(paintGreen);
                circleTwo.setFill(paintGreen);
                circleThree.setFill(paintGreen);
                circleFour.setFill(paintYellow);
                circleFive.setFill(paintRed);

                //Disable
                cboxWaitingRoom.setDisable(true);
                btnShowInfoHealthCondit.setDisable(true);
                btnTypeDate.setDisable(true);
                cboxVaccineLotNumber.setDisable(true);
                btnCheckDosage.setDisable(false);
                btnRecordVaccinationRecords.setDisable(true);

                //Visible
                btnCancelOperation.setVisible(true);
                btnExit.setVisible(false);
                break;
            case 5:
                //Progress status
                lblProgressBoard.setBackground(backgroungYellow);
                lblProgressBoardTxt.setText("finalizing...");

                //Color
                circleOne.setFill(paintGreen);
                circleTwo.setFill(paintGreen);
                circleThree.setFill(paintGreen);
                circleFour.setFill(paintGreen);
                circleFive.setFill(paintYellow);

                //Disable
                cboxWaitingRoom.setDisable(true);
                btnShowInfoHealthCondit.setDisable(true);
                btnTypeDate.setDisable(true);
                cboxVaccineLotNumber.setDisable(true);
                btnCheckDosage.setDisable(false);
                btnRecordVaccinationRecords.setDisable(false);

                //Visible
                btnCancelOperation.setVisible(true);
                btnExit.setVisible(false);
                break;

            case 6:
                //Progress status
                lblProgressBoard.setBackground(backgroungGreen);
                lblProgressBoardTxt.setText("SUCCESS!!");

                //Color
                circleOne.setFill(paintGreen);
                circleTwo.setFill(paintGreen);
                circleThree.setFill(paintGreen);
                circleFour.setFill(paintGreen);
                circleFive.setFill(paintGreen);

                //Disable
                cboxWaitingRoom.setDisable(true);
                btnShowInfoHealthCondit.setDisable(true);
                btnTypeDate.setDisable(true);
                cboxVaccineLotNumber.setDisable(true);
                btnCheckDosage.setDisable(true);
                btnRecordVaccinationRecords.setDisable(true);

                //Visible
                btnCancelOperation.setVisible(false);
                btnExit.setVisible(true);
                break;
        }
    }
    //endregion style

    //region Others ---------------------------------------------------------------------
    @FXML
    private void btnCancelOperationAction(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit from record vaccine administration menu");
        alert.setHeaderText("Do you really want to exit from the vaccine administration\nrecord process? All " +
                            "changes will be " + "lost!");
        alert.setContentText("Press Cancel to go back to form");
        if(alert.showAndWait().get() == ButtonType.OK) {
            changeSceneToPassVCId("/fxml/nurseUI.fxml", event);
        }
    }

    @FXML
    private void btnExitAction(ActionEvent event) throws IOException {
        changeSceneToPassVCId("/fxml/nurseUI.fxml", event);
    }

    private void changeSceneToPassVCId(String resourceUrl, ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(resourceUrl));
        Parent root = loader.load();
        Stage stage = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        NurseGUI controller = loader.getController();
        controller.initVaccCenterId(vaccinationCenterId);

        stage.setScene(scene);
        stage.show();

    }
    //endregion Others
}
