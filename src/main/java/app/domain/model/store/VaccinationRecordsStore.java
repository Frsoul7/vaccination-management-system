package app.domain.model.store;

import app.domain.model.VaccinationRecords;
import app.domain.model.Vaccine;
import app.domain.model.utils.CompareVaccinationRecordsByDate;
import app.domain.model.utils.DateCustom;
import app.domain.model.utils.TimeHour;
import app.interfaces.ExcludeFromJacocoGeneratedReport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VaccinationRecordsStore implements Serializable {

    /***
     * List of the sns user vaccination records
     */
    private List<VaccinationRecords> listVaccRecords;

    private static final DateCustom FIRST_DATE_CUSTOM_BY_DEFAULT = new DateCustom("01/01/1900");

    /***
     * Empty VaccinationRecordsStore constructor
     */
    @ExcludeFromJacocoGeneratedReport
    public VaccinationRecordsStore() {
        this.listVaccRecords = new ArrayList<>();
    }

    /***
     * Get list of vaccination records
     * @return
     */
    @ExcludeFromJacocoGeneratedReport
    public List<VaccinationRecords> getListVaccRecords() {
        return new ArrayList<>(listVaccRecords);
    }

    /***
     * Method RecordVaccRecords used to record employee
     *
     * @param vaccineName - vaccine name
     * @param doseStep - vaccine dose step (related to administration process plan)
     * @param vaccineLotNumber - vaccine lot number
     * @param nurseAdministrationDate - nurse vaccine administration date
     * @param nurseAdministrationTimeHour - nurse vaccine administration hour
     * @return a new VaccinationRecords obj is created
     */
    public VaccinationRecords recordVaccRecords(String vaccineName, int doseStep, String vaccineLotNumber,
                                                DateCustom nurseAdministrationDate,
                                                TimeHour nurseAdministrationTimeHour) {
        return new VaccinationRecords(vaccineName, doseStep, vaccineLotNumber, nurseAdministrationDate,
                                      nurseAdministrationTimeHour);
    }

    /***
     * Method used to save a vaccination record
     *
     * @param vaccRecords - VaccinationRecords obj
     * @return boolean (true if the vaccination record has been successfully saved into the list and, false if not)
     */
    public boolean saveVaccinationRecords(VaccinationRecords vaccRecords) {
        try {
            if(isUniqueVaccRecords(vaccRecords)) {
                return addVaccRecords(vaccRecords);
            }
        }
        catch(RuntimeException ex) {
            return false;
        }
        return false;
    }

    /***
     * Method used to add a vaccination record to the vaccination records list
     * @param vaccRecords - VaccinationRecords obj
     * @return boolean (true if has been added, false if not)
     */
    private boolean addVaccRecords(VaccinationRecords vaccRecords) {
        return listVaccRecords.add(vaccRecords);
    }

    /***
     * Checks if there's an objet VaccinationRecords in the listVaccRecords with the same attributes
     *
     * @param vaccRecords
     * @return
     */
    private boolean isUniqueVaccRecords(VaccinationRecords vaccRecords) {
        for(VaccinationRecords obj : listVaccRecords) {
            if(obj.equals(vaccRecords)) {
                return false;
            }
        }
        return true;
    }

    public List<VaccinationRecords> getVaccinationRecordsSortedByDate(
            List<VaccinationRecords> listHealthRecordsOfSnsUser) {

        Collections.sort(listHealthRecordsOfSnsUser, new CompareVaccinationRecordsByDate());
        return listHealthRecordsOfSnsUser;
    }

    /***
     * Method that receives a list of vaccines filtered by one vaccine type and searches in sns use vaccination records
     * for vaccines related to that vaccine type (by looking and comparing the vaccine lot number) and returns the
     * VaccinationRecords obj more recent if it finds any relation
     * @param listVaccinesFilteredByVaccineType
     * @return the VaccinationRecords obj more recent or null if there's no history
     */
    public VaccinationRecords findLastVaccRecordByVaccType(List<Vaccine> listVaccinesFilteredByVaccineType) {
        DateCustom mostRecentDateFound = new DateCustom(FIRST_DATE_CUSTOM_BY_DEFAULT);
        VaccinationRecords objToPass = null;
        for(VaccinationRecords objVaccRecord : listVaccRecords) {
            for(Vaccine objVaccine : listVaccinesFilteredByVaccineType)
                if(objVaccRecord.getVaccineLotNumber().equals(objVaccine.getLotNumber())) {
                    if(objVaccRecord.getNurseAdministrationDate().isBigger(mostRecentDateFound)) {
                        mostRecentDateFound = new DateCustom(objVaccRecord.getNurseAdministrationDate());
                        objToPass = new VaccinationRecords(objVaccRecord);
                    }
                }
        }
        if(mostRecentDateFound.equals(new DateCustom(FIRST_DATE_CUSTOM_BY_DEFAULT))) {
            return null;
        }
        return objToPass;
    }


    public boolean checkIfSnsUserTakeAnyDoseOfVaccine(String lotNumber) {

        for(VaccinationRecords vacR : listVaccRecords) {
            if(vacR.getVaccineLotNumber().equals(lotNumber)) {
                return true;
            }
        }
        return false;
    }

}
