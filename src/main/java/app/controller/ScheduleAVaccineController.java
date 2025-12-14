package app.controller;

import app.dto.VaccinationCenterDTO;
import app.dto.VaccineTypeDTO;
import app.mappers.VaccinationCenterMapper;
import app.mappers.VaccineSchedulerMapper;
import app.mappers.VaccineTypeMapper;
import app.domain.model.*;
import app.domain.model.exceptions.OperationCanceledByUserException;
import app.domain.model.store.*;
import app.domain.model.utils.DateCustom;
import app.domain.model.utils.TimeHour;
import app.domain.model.utils.Validations;
import app.interfaces.Constants;
import app.interfaces.ExcludeFromJacocoGeneratedReport;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/***
 * @authors Edgar Moreira <1010100@isep.ipp.pt>
 *          Fernando Ribeiro <1060064@isep.ipp.pt>
 *          José Silva <1060568@isep.ipp.pt>
 *          Pedro Gomes <1060588@isep.ipp.pt>
 */

public class ScheduleAVaccineController implements Constants {

    /***
     * Instance of the app
     */
    private final App app;

    /***
     * Instance of the company
     */
    private final Company company;

    /***
     * Instance of the vaccination center store
     */
    private final VaccinationCenterStore vcStore;
    /***
     * Instance of vaccination center
     */
    private VaccinationCenter vaccCenter;

    /***
     * Instance of performance records store
     */
    private PerformanceRecordsStore perfRecordsStore;
    /***
     * Instance of performance records
     */
    private PerformanceRecords perfRecords;
    /***
     * Instance of the vaccination center
     */
    private VaccineTypeStore vtStore;

    /***
     * Instance of the vaccination center
     */
    private SNSUserStore snsUserStore;

    /***
     * Instance of vaccine type mapper
     */
    private final VaccineTypeMapper vtmapper;

    /***
     * Instance of vaccination center mapper
     */
    private final VaccinationCenterMapper vcmapper;

    /***
     * Instance of the mapper for the vaccine Scheduler
     */
    private VaccineSchedulerMapper vaccineSchedulerMapper;

    /***
     * Instance for the Vaccine Scheduler store
     */
    private VaccineSchedulerStore vaccineSchedulerStore;

    /**
     * Instance of the vaccine scheduler
     */
    private VaccineScheduler vacScheduler;

    /***
     * Controller of Schedule a vaccine
     */
    @ExcludeFromJacocoGeneratedReport
    public ScheduleAVaccineController() {
        this.app = App.getInstance();
        this.company = app.getCompany();
        this.vtmapper = new VaccineTypeMapper();
        this.vcmapper = new VaccinationCenterMapper();
        this.vcStore = company.getVaccinationCenterStore();
        this.snsUserStore = company.getSnsUserStore();
        this.vtStore = company.getVaccineTypeStore();
    }

    /***
     * Method to register the vaccine scheduler in the vaccine scheduler store, of a specific vaccination center
     * @param snsUserNumber         - sns number of the sns user
     * @param vaccinationCenterID   - vaccination center identifier (ID) where the sns user scheduled the vaccine
     * @param vaccineType           - type of the vaccine chosen by the sns user (e.g. covid-19)
     * @param date                  - date (dd/mm/yyyy) chosen by the sns user for taking the vaccine
     * @param selectedSlot          - slot of time available in the vaccination center, selected by the sns user
     * @return true if data inserted by the user is validated (e.g. date) and goes in accordance with the vaccination
     * center availability; false if any parameter of selection/insertion fails
     */
    public boolean registerVaccineScheduler(long snsUserNumber, int vaccinationCenterID, String vaccineType,
                                            String date, int[] selectedSlot) {
        try {
            if(Validations.isDateFormatValid(date, true, false)) {
                DateCustom newDate = new DateCustom(date);

                String vaccinationCenterName = vcStore.findVaccinationCenterById(vaccinationCenterID).getName();

                // TimeHour class verify the parameters and turns back the exception
                TimeHour scheduleTime = new TimeHour(selectedSlot[0], selectedSlot[1]);

                this.vaccineSchedulerStore =
                        vcStore.findVaccinationCenterById(vaccinationCenterID).getVacineSchedulerStore();
                this.vacScheduler = vaccineSchedulerStore.registerVaccineScheduler(snsUserNumber, vaccinationCenterName,
                                                                                   vaccineType, newDate, scheduleTime);

                //new instructions
                try {
                    vaccCenter = vcStore.getVaccinationCenter(vaccinationCenterID);
                    perfRecordsStore = vaccCenter.getPerformanceRecordsStore();
                    perfRecords = perfRecordsStore.RegisterPerfRecords(snsUserNumber, newDate, scheduleTime);
                    return true;
                }
                catch(RuntimeException ex) {
                    Utils.showText(ex.getMessage());
                    Utils.showText(
                            "[warning] couldn't create Performance Records obj, please contact your " + "maintenance" +
                            " team!");
                    return false;
                }

            }
            return false;
        }
        catch(RuntimeException|OperationCanceledByUserException ex) {
            Utils.showText(ex.getMessage());
            return false;
        }
    }


    /***
     * Verifies if the SNS user number is valid and if it is already registered in the system
     * @param snsUserNumber - SNS number of SNS user
     * @return true if valid, false if not
     */
    public boolean validateSnsUserNumber(long snsUserNumber) {
        try {
            if(Validations.isLengthValidEquals(snsUserNumber, true, true, SIZE_SNS_USER_NUMBER)) {
                if(snsUserStore.validateSnsUser(snsUserNumber)) {
                    return true;
                }
            }
        }
        catch(RuntimeException|OperationCanceledByUserException ex) {
            Utils.showText(ex.getMessage());
            return false;
        }
        return false;
    }

    /***
     * Data Transfer Object (DTO) to get the list of vaccine types
     * @return List of vaccine types registered
     */
    @ExcludeFromJacocoGeneratedReport
    public List<VaccineTypeDTO> getVaccineTypes() {

        List<VaccineType> lVacTypes = vtStore.getVaccineTypesList();

        return vtmapper.toDTO(lVacTypes);

    }

    /***
     * Data Transfer Object list to get the vaccination centers
     * @return
     */
    @ExcludeFromJacocoGeneratedReport
    public List<VaccinationCenterDTO> getVaccinationCenters() {

        List<VaccinationCenter> lVaccinationCenters = vcStore.getVaccinationCenterList();

        return vcmapper.toDTO(lVaccinationCenters);

    }

    /***
     * Get of the time intervals of the available slots
     * @param otherDate             - otherDate represents the dates in string type
     * @param vaccinationCenterId   - vaccination center identifier
     * @return the time intervals of the available slots (e.g. 11:00 - 11:30)
     */
    @ExcludeFromJacocoGeneratedReport
    public int[][] getTimeOfFreeSlots(String otherDate, int vaccinationCenterId) {
        try {
            return vcStore.getTimeOfFreeSlots(otherDate, vaccinationCenterId);
        }
        catch(RuntimeException e) {
            Utils.showText(e.getMessage());
            return null;
        }
    }

    /***
     * Get the Schedule information in a String format
     * @return String with the schedule information
     */
    @ExcludeFromJacocoGeneratedReport
    public String getScheduleInformation() {
        return vacScheduler.toString();
    }

    /***
     * Saves the vaccine scheduler defined by the sns User after being selected and validated
     * @return vaccine scheduler
     */
    public boolean saveVaccineScheduler(boolean smsNotification) {
        long phoneNumber = snsUserStore.getSnsUserBySnsUserNumber(vacScheduler.getSnsUserNumber()).getPhoneNumber();

        DateCustom birth = snsUserStore.getSnsUserBySnsUserNumber(vacScheduler.getSnsUserNumber()).getBirthDate();
        int age = birth.differenceOfYears();
        //check the vaccination history of SNSUser to know if he can schedule the vaccine

        //1. sns user starts selecting vaccine type - vTypeStore
        //2. then, checks if there are vaccines related to that vaccine type - vStore
        //3. then, checks if SNS user has any kind of vaccination records related to that vaccine/vaccineType -
        // vRecordsStore + vStore
        //  3.1 - if not:
        //  3.1 - else:

        //check if the selected type of vaccine have vaccines defined
        if(validateIfExistVaccinesDefinedForSelectedVaccineType()) { //this should always pass because we choose from
            // the registered list

            //check if SnsUser have already taken a vaccine
            VaccinationRecords lastVacRecordForVaccineTypeSelected = validateIfSnsUserAlreadyTakeAVaccine(); //TODO:
            // 1 - devolve o último registo de vacinação
            if(lastVacRecordForVaccineTypeSelected == null) {

                //check if exist a vaccine for Sns User age group and its administration process
                int aux = 0;
                String ageGroup;
                String[] ageGroupParts;
                int numI;
                int numS;
                for(Vaccine vac : this.company.getVaccineStore().getVaccineList()) {
                    if(vac.getVaccineType().equals(vacScheduler.getVaccineTypeDesignation())) {
                        ageGroup = vac.getAgeGroup();
                        ageGroupParts = ageGroup.split(",");
                        numI = Integer.parseInt(ageGroupParts[0]);
                        numS = Integer.parseInt(ageGroupParts[1]);
                        if(age >= numI && age <= numS) {
                            //search for the right age group interval in administration process rules
                            for(AdministrationProcess admin : vac.getAdmProcList()) {
                                ageGroup = admin.getAgeGroup();
                                ageGroupParts = ageGroup.split(",");
                                numI = Integer.parseInt(ageGroupParts[0]);
                                numS = Integer.parseInt(ageGroupParts[1]);
                                if(age >= numI && age <= numS) {
                                    aux = 1;
                                }
                            }
                        }
                    }
                }
                if(aux == 0) {
                    Utils.showText(
                            "There are no defined vaccines or administration processes for the age of Sns " + "User!");
                    return false;
                } else {
                    return this.vaccineSchedulerStore.saveVaccineScheduler(vacScheduler, smsNotification,
                                                                           phoneNumber) &&
                           perfRecordsStore.savePerfRecords(perfRecords);
                }
            } else {
                if(validateDoseAndIntervalBetweenDoses(age, lastVacRecordForVaccineTypeSelected)) {
                    return this.vaccineSchedulerStore.saveVaccineScheduler(vacScheduler, smsNotification,
                                                                           phoneNumber) &&
                           perfRecordsStore.savePerfRecords(perfRecords);
                }
            }
        }
        return false;
    }

    public boolean validateIfExistVaccinesDefinedForSelectedVaccineType() {

        if(!this.company.getVaccineStore().getVaccineList().isEmpty()) {
            for(Vaccine vac : this.company.getVaccineStore().getVaccineList()) {
                if(vac.getVaccineType().equals(vacScheduler.getVaccineTypeDesignation())) {
                    return true;
                }
            }
        }
        Utils.showText("There are no defined vaccines for the selected vaccine type!");
        return false;
    }


    public VaccinationRecords validateIfSnsUserAlreadyTakeAVaccine() {
        HealthRecords healthRecordsOfSnsUser =
                snsUserStore.getSnsUserBySnsUserNumber(vacScheduler.getSnsUserNumber()).getHealthRecords();

        List<VaccinationRecords> listHealthRecordsOfSnsUser =
                healthRecordsOfSnsUser.getVaccRecordsStore().getListVaccRecords();

        //sort the list by date
        listHealthRecordsOfSnsUser = healthRecordsOfSnsUser.getVaccRecordsStore().getVaccinationRecordsSortedByDate(
                listHealthRecordsOfSnsUser);

        for(VaccinationRecords vacRecords : listHealthRecordsOfSnsUser) {
            for(Vaccine vac : this.company.getVaccineStore().getVaccineList()) {
                if(vac.getVaccineType().equals(vacScheduler.getVaccineTypeDesignation())) {
                    if(vacRecords.getVaccineLotNumber().equals(vac.getLotNumber())) {
                        return vacRecords;
                    }
                }
            }
        }
        return null;
    }


    public boolean validateDoseAndIntervalBetweenDoses(int age,
                                                       VaccinationRecords lastVacRecordForVaccineTypeSelected) {

        int lastDose = lastVacRecordForVaccineTypeSelected.getDoseStep()+1;

        //get the list of administrations process for the vaccine administered previously to Sns User
        List<AdministrationProcess> vaccineAdministrationProcessList = new ArrayList<>();
        for(Vaccine vac : this.company.getVaccineStore().getVaccineList()) {
            if(vac.getVaccineType().equals(vacScheduler.getVaccineTypeDesignation())) {
                if(lastVacRecordForVaccineTypeSelected.getVaccineLotNumber().equals(vac.getLotNumber())) {
                    vaccineAdministrationProcessList = vac.getAdmProcList();
                }
            }
        }

        //find the administration process that corresponds to the Sns User age
        AdministrationProcess administrationProcessDefinedForTheUser =
                findAdministrationProcessForSnsUserAge(age, vaccineAdministrationProcessList);
        if(administrationProcessDefinedForTheUser == null) {
            Utils.showText("There are no defined administration process in accordance with the Sns User age!");
            return false;
        }

        //find if the SnsUser already have all doses for the administration process
        if(administrationProcessDefinedForTheUser.getNumberOfDoses() == lastDose+1) {
            Utils.showText("The Sns User already take all the doses for the selected vaccine type!");
            return false;
        }

        //Get the range of days between the dose that the Sns User taken and the new one that he wants to schedule
        int numberOfDaysBetweenDoses =
                administrationProcessDefinedForTheUser.getTimeIntervalBetweenDoses()[lastDose];

        if(lastVacRecordForVaccineTypeSelected.getNurseAdministrationDate().countingDays() + numberOfDaysBetweenDoses <=
           vacScheduler.getDate().countingDays()) {
            return true;
        }
        {
            Utils.showText("Interval of days between doses doesn't was fulfilled!");
            return false;
        }

    }

    public AdministrationProcess findAdministrationProcessForSnsUserAge(int age,
                                                                        List<AdministrationProcess> vaccineAdministrationProcess) {

        for(AdministrationProcess admin : vaccineAdministrationProcess) {
            String ageGroup = admin.getAgeGroup();
            String[] ageGroupParts = ageGroup.split(",");
            int numI = Integer.parseInt(ageGroupParts[0]);
            int numS = Integer.parseInt(ageGroupParts[1]);
            if(age >= numI && age <= numS) {
                return admin;
            }
        }
        return null;
    }

}
