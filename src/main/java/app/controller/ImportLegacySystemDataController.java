package app.controller;

import app.domain.model.*;
import app.domain.model.exceptions.OperationCanceledByUserException;
import app.domain.model.store.*;
import app.domain.model.utils.DateCustom;
import app.domain.model.utils.TimeHour;
import app.domain.model.utils.Validations;
import app.domain.model.utils.Configurations;
import app.domain.shared.StepDosesPTtoStringConversion;
import app.dto.ImportedDataInformationDTO;
import app.interfaces.Constants;
import app.interfaces.Reader;
import app.interfaces.SortAlg;
import app.mappers.ImportLegacySystemDataMapper;
import app.ui.console.utils.Utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ImportLegacySystemDataController implements Constants {

    /**
     * Instance of the app
     */
    private final App app;

    /**
     * Instance of the company
     */
    private final Company company;

    /***
     * Instance of the vaccination center
     */
    private SNSUserStore snsUserStore;

    /**
     * Instance of vaccine store
     */
    private VaccineStore vStore;

    /**
     * Instance of VaccinationRecordsStore
     */
    private VaccinationRecordsStore vaccRecordsStore;

    /***
     * Instance of the vaccination center
     */
    private VaccinationCenter vc;

    /**
     * Instance of vaccination center store
     */
    private VaccinationCenterStore vcStore;

    /***
     * Instance of performance records
     */
    private PerformanceRecordsStore perfRecordsStore;


    private List<ImportedDataInformation> importedDataInformation;

    private ImportLegacySystemDataMapper map;


    public ImportLegacySystemDataController(int vaccinationCenterId) {
        this.app = App.getInstance();
        this.company = app.getCompany();
        this.snsUserStore = company.getSnsUserStore();
        this.vStore = company.getVaccineStore();
        this.vcStore = company.getVaccinationCenterStore();
        this.vc = vcStore.getVaccinationCenter(vaccinationCenterId);
        this.perfRecordsStore = vc.getPerformanceRecordsStore();
        this.map = new ImportLegacySystemDataMapper();
        this.importedDataInformation = new ArrayList<>();
    }

    public boolean validateIfFileExist(String filePath) throws OperationCanceledByUserException {
        return !Validations.isFilePathValid(filePath, true, true);
    }

    public boolean importLegacyData(String filePath, String timeCriteria, String orderCriteria) {
        int cont = 0;
        try {
            Reader readerType = Configurations.getReader();
            List<String> legacyData = readerType.readFile(filePath);
            if(legacyData == null) {
                return false;
            }
            String delimiter = Configurations.getDelimiter();
            String splitDataTimeColumn = Configurations.getSplitDataTimeInfo();
            String[] data;

            for(String lineData : legacyData) {
                data = readerType.splitData(lineData, splitDataTimeColumn, delimiter);
                if(data != null) {
                    int numberOfAttributes = Configurations.getNumberOfAttributes();
                    if(Validations.validateNumberOfAttributes(data, numberOfAttributes)) {
                        //except hours, this will be validated in conversion to Timehour format
                        if(validateAllAttributes(data) && snsUserStore.validateSnsUser(Long.parseLong(data[0])) &&
                           vStore.validateIfVaccineExist(data[3])) {
                            this.vaccRecordsStore =
                                    snsUserStore.getSnsUserBySnsUserNumber(Long.parseLong(data[0])).getHealthRecords()
                                                .getVaccRecordsStore();
                            int snsUserAge =
                                    snsUserStore.getSnsUserBySnsUserNumber(Long.parseLong(data[0])).calculateAge();
                            int controller = 0;
                            if(vaccRecordsStore.checkIfSnsUserTakeAnyDoseOfVaccine(data[3])) {
                                //if already took a dose need to check if the registe is valid
                                int doseNumber = vaccRecordsStore.findLastVaccRecordByVaccType(
                                                                         vStore.getVaccinesListWithVaccineTypeAndAge(
                                                                                 vStore.getVaccine(data[3]).getVaccineType(), snsUserAge))
                                                                 .getDoseStep() + 1;
                                if(StepDosesPTtoStringConversion.getStepDoseIndexByPtDescription(data[2]) + 1 ==
                                   doseNumber + 1) {
                                    //is the registe of the next dose
                                    Vaccine vac = vStore.getVaccine(data[3]);
                                    if(doseNumber > vac.getAdministrationProcess(snsUserAge).getNumberOfDoses()) {
                                        //is not valid the dose number is bigger than the number of doses for the
                                        // vaccine and age group
                                        controller = 1;
                                    }
                                } else {
                                    controller = 1;
                                }
                            }
                            //if is the first register or if the register is valid
                            if(controller == 0) {
                                try {
                                    DateCustom scheduledDate = DateCustom.convertDateFormat(data[4], "MM/dd/yyyy");
                                    TimeHour scheduledTimeHour = new TimeHour(data[5], "24");
                                    DateCustom arrivalDate = DateCustom.convertDateFormat(data[6], "MM/dd/yyyy");
                                    TimeHour arrivalTimeHour = new TimeHour(data[7], "24");
                                    DateCustom nurseAdministrationDate =
                                            DateCustom.convertDateFormat(data[8], "MM/dd" + "/yyyy");
                                    TimeHour nurseAdministrationTimeHour = new TimeHour(data[9], "24");
                                    DateCustom leavingDate = DateCustom.convertDateFormat(data[10], "MM/dd/yyyy");
                                    TimeHour leavingTimeHour = new TimeHour(data[11], "24");

                                    if(perfRecordsStore.savePerfRecords(
                                            perfRecordsStore.RegisterPerfRecords(Long.parseLong(data[0]), data[1],
                                                                                 (StepDosesPTtoStringConversion.getStepDoseIndexByPtDescription(
                                                                                         data[2])+1), data[3],
                                                                                 scheduledDate, scheduledTimeHour,
                                                                                 arrivalDate, arrivalTimeHour,
                                                                                 nurseAdministrationDate,
                                                                                 nurseAdministrationTimeHour,
                                                                                 leavingDate, leavingTimeHour))) {

                                        if(vaccRecordsStore.saveVaccinationRecords(
                                                vaccRecordsStore.recordVaccRecords(data[1],
                                                                                   (StepDosesPTtoStringConversion.getStepDoseIndexByPtDescription(
                                                                                           data[2])+1), data[3],
                                                                                   nurseAdministrationDate,
                                                                                   nurseAdministrationTimeHour))) {

                                            String vacType = vStore.getVaccine(data[3]).getVaccineType();
                                            String snsUserName =
                                                    snsUserStore.getSnsUserBySnsUserNumber(Long.parseLong(data[0]))
                                                                .getName();
                                            ImportedDataInformation dataImp =
                                                    new ImportedDataInformation(snsUserName, Long.parseLong(data[0]),
                                                                                data[1], vacType,
                                                                                (StepDosesPTtoStringConversion.getStepDoseIndexByPtDescription(
                                                                                        data[2])+1), data[3],
                                                                                scheduledDate, scheduledTimeHour,
                                                                                arrivalDate, arrivalTimeHour,
                                                                                nurseAdministrationDate,
                                                                                nurseAdministrationTimeHour,
                                                                                leavingDate, leavingTimeHour);

                                            importedDataInformation.add(dataImp);
                                            cont++;

                                        }
                                    }
                                }
                                catch(RuntimeException ex) {
                                    Utils.showText(
                                            "[warning] couldn't create Performance Records obj, please contact your " +
                                            "maintenance team!");
                                    return false;
                                }

                            }
                        }
                    }
                }
            }

            //Sort and Polymorphism
            if(importedDataInformation != null && !importedDataInformation.isEmpty()) {
                SortAlg sortType = Configurations.getSortAlg();
                if(timeCriteria.equals("Arrival Time")) {
                    sortType.sortByArrivalTime(importedDataInformation);
                } else {
                    sortType.sortByLeavingTime(importedDataInformation);
                }
            } else {
                return false;
            }

        }
        catch(IOException|ClassNotFoundException|InstantiationException|IllegalAccessException|OperationCanceledByUserException ex) {
            return false;
        }
        Utils.showText(String.format("Imported %d registers into system", cont));
        return true;
    }

    public boolean validateAllAttributes(String[] data) throws OperationCanceledByUserException {

        return Validations.isLengthValidEquals(Long.parseLong(data[0]), true, false, SIZE_SNS_USER_NUMBER) &&
               Validations.isStringLengthValid(data[1], true, false, MAX_LENGTH_NAME) &&
               Validations.isNameValid(data[1], true, false) && Validations.isNumberDosesValidAdm(
                StepDosesPTtoStringConversion.getStepDoseIndexByPtDescription(data[2]) + 1, true, false,
                SIZE_NUMBEROFDOSESADM) && Validations.isLotNumberValid(data[3], true, false) &&
               Validations.isDateFormatValid(data[4], true, false) &&
               Validations.isDateFormatValid(data[6], true, false) &&
               Validations.isDateFormatValid(data[8], true, false) &&
               Validations.isDateFormatValid(data[10], true, false);

    }


    public List<ImportedDataInformationDTO> getImportedLegacyData() {
        return map.toDTO(importedDataInformation);
    }
}
