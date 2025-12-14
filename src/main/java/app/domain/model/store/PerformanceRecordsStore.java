package app.domain.model.store;

import app.domain.model.PerformanceRecords;
import app.domain.model.VaccinationRecords;
import app.domain.model.utils.DateCustom;
import app.domain.model.utils.TimeHour;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PerformanceRecordsStore implements Serializable {

    /***
     * List of performance records for each vaccination center
     */
    private List<PerformanceRecords> listPerfRecords;

    /***
     * Empty PerformanceRecordsStore constructor
     */
    public PerformanceRecordsStore() {
        this.listPerfRecords = new ArrayList<>();
    }

    /***
     * Get PerformanceRecords list
     * @return PerformanceRecords list
     */
    public List<PerformanceRecords> getListPerfRecords() {
        return new ArrayList<>(listPerfRecords);
    }

    public List<PerformanceRecords> getPerformanceRecordsStore(DateCustom date) {
        List<PerformanceRecords> lSnsUserVac = new ArrayList<>();
        List<PerformanceRecords> lPerfRecords = new ArrayList<>(listPerfRecords);

        for(PerformanceRecords perfRecord : lPerfRecords) {
            DateCustom admDate = perfRecord.getNurseAdministrationDate();
            if(admDate.equals(date) ) {
                lSnsUserVac.add(perfRecord);
            }
        }
        return lSnsUserVac;
    }


    //US1
    public PerformanceRecords RegisterPerfRecords(long snsUserNumber, DateCustom scheduledDate,
                                                  TimeHour scheduledTimeHour) {

        return new PerformanceRecords(snsUserNumber, scheduledDate, scheduledTimeHour);
    }

    //US17
    public PerformanceRecords RegisterPerfRecords(long snsUserNumber, String vaccineName, int doseStep,
                                                  String lotNumber, DateCustom scheduledDate,
                                                  TimeHour scheduledTimeHour, DateCustom arrivalDate,
                                                  TimeHour arrivalTimeHour, DateCustom nurseAdministrationDate,
                                                  TimeHour nurseAdministrationTimeHour, DateCustom leavingDate,
                                                  TimeHour leavingTimeHour) {

        return new PerformanceRecords(snsUserNumber, vaccineName, doseStep, lotNumber, scheduledDate, scheduledTimeHour,
                                      arrivalDate, arrivalTimeHour, nurseAdministrationDate,
                                      nurseAdministrationTimeHour, leavingDate, leavingTimeHour);
    }

    //US1 + US17
    public boolean savePerfRecords(PerformanceRecords perfRecords) {
        try {
            if(isUniquePerfRecords(perfRecords)) {
                return addPerfRecords(perfRecords);
            }
        }
        catch(RuntimeException ex) {
            return false;
        }
        return false;
    }

    /***
     * Checks if there's an objet PerformanceRecords in the listPerfRecords with the same combined SnsUserNumber and
     * ScheduledDate
     *
     * @param perfRecordsToCheck
     * @return
     */
    private boolean isUniquePerfRecords(PerformanceRecords perfRecordsToCheck) {
        for(PerformanceRecords obj : listPerfRecords) {
            if(obj.equalsUniqueAttributes(perfRecordsToCheck)) {
                return false;
            }
        }
        return true;
    }

    private boolean addPerfRecords(PerformanceRecords perfRecords) {
        return listPerfRecords.add(perfRecords);
    }

    //US8
    public boolean setPerfRecordsWithVaccAdministration(long snsUserNumber, VaccinationRecords vaccRecords) {
        for(PerformanceRecords obj : listPerfRecords) {
            if(obj.getSnsUserNumber() == snsUserNumber &&
               obj.getScheduledDate().equals(vaccRecords.getNurseAdministrationDate())) {
                obj.setVaccineName(vaccRecords.getVaccineName());
                obj.setDoseStep(vaccRecords.getDoseStep());
                obj.setLotNumber(vaccRecords.getVaccineLotNumber());
                obj.setNurseAdministrationDate(vaccRecords.getNurseAdministrationDate());
                obj.setNurseAdministrationTimeHour(vaccRecords.getNurseAdministrationTimeHour());
                return true;
            }
        }
        return false;
    }

    //US8 TIMER
    public boolean setPerfRecordsWithTimerAfterLeaving(long snsUserNumber, DateCustom leavingDate,
                                                       TimeHour leavingTimeHour) {
        for(PerformanceRecords obj : listPerfRecords) {
            if(obj.getSnsUserNumber() == snsUserNumber && obj.getScheduledDate().equals(leavingDate)) {
                obj.setLeavingDate(leavingDate);
                obj.setLeavingTimeHour(leavingTimeHour);
                return true;
            }
        }
        return false;
    }

    //US4
    public boolean setPerfRecordsWithArrival(long snsUserNumber, DateCustom arrivalDate, TimeHour arrivalTimeHour) {
        for(PerformanceRecords obj : listPerfRecords) {
            if(obj.getSnsUserNumber() == snsUserNumber && obj.getScheduledDate().equals(arrivalDate)) {
                obj.setArrivalDate(arrivalDate);
                obj.setArrivalTimeHour(arrivalTimeHour);
                return true;
            }
        }
        return false;
    }

    //US6
    public int getTotalNumberOfPeopleVaccinated(DateCustom dateToAnalise) {
        int count = 0;
        for(PerformanceRecords obj : listPerfRecords) {
            if(obj.getNurseAdministrationDate().equals(dateToAnalise)) {
                count++;
            }
        }
        return count;
    }

    public boolean checkIfExistsRecordsForTheSelectedDate(DateCustom date) {

        for(PerformanceRecords obj : listPerfRecords) {
            if(obj.getNurseAdministrationDate().equals(date)) {
                return true;
            }
        }
        return false;
    }

}
