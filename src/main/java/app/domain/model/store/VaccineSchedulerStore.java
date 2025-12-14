package app.domain.model.store;

import app.domain.model.VaccineScheduler;
import app.domain.model.exceptions.OperationCanceledByUserException;
import app.domain.model.utils.DateCustom;
import app.domain.model.utils.Notifications;
import app.domain.model.utils.TimeHour;
import app.interfaces.ExcludeFromJacocoGeneratedReport;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class VaccineSchedulerStore implements Serializable {

    /**
     * List of schedules
     */
    private List<VaccineScheduler> lVacSchedules;

    /**
     * Empty VaccineStore constructor with default values
     */
    @ExcludeFromJacocoGeneratedReport
    public VaccineSchedulerStore() {
        lVacSchedules = new ArrayList<>();
    }

    @ExcludeFromJacocoGeneratedReport
    public VaccineSchedulerStore(VaccineSchedulerStore otherStore) {
        lVacSchedules = new ArrayList<>(otherStore.lVacSchedules);
    }

    /**
     * xx
     *
     * @return
     */
    @ExcludeFromJacocoGeneratedReport
    public List<VaccineScheduler> getVaccineSchedulesList() {
        return new ArrayList<>(this.lVacSchedules);
    }

    public VaccineScheduler registerVaccineScheduler(long snsUserNumber, String vaccinationCenterName,
                                                     String vaccineType, DateCustom date, TimeHour time)
            throws OperationCanceledByUserException {

        return new VaccineScheduler(snsUserNumber, vaccineType, vaccinationCenterName, date, time);
    }


    public boolean saveVaccineScheduler(VaccineScheduler scheduler, boolean smsNotification, long phoneNumber) {

        if(!validateVaccineScheduleIsUnique(scheduler)) {
            try {
                if(smsNotification) {
                    Notifications.sendSMSSchedulerNotification(scheduler, phoneNumber);
                }
                return this.addVaccineSchedule(scheduler);
            }
            catch(RuntimeException|IOException ex) {
                return false;
            }
        }
        return false;
    }



    public boolean addVaccineSchedule(VaccineScheduler vaccineScheduler) {
        return this.lVacSchedules.add(vaccineScheduler);
    }

    public boolean validateVaccineScheduleIsUnique(VaccineScheduler vaccineScheduler) {
        if(vaccineScheduler == null) {
            return true;
        }
        List<VaccineScheduler> vaccineSchedulerList = this.getVaccineSchedulesList();
        if(!vaccineSchedulerList.isEmpty()) {
            for(VaccineScheduler vacSch : vaccineSchedulerList) {
                if(vacSch.equals(vaccineScheduler)) {
                    return true;
                }
            }
        }
        return false;
    }

    @ExcludeFromJacocoGeneratedReport
    public List<VaccineScheduler> getVaccineSchedulerBySnsUserNumber(long snsUserNumber) {
        List<VaccineScheduler> snsUserVaccineScheduler = new ArrayList<>();
        for(VaccineScheduler vaccineScheduler : lVacSchedules) {
            if(snsUserNumber == ((vaccineScheduler.getSnsUserNumber())) &&
               vaccineScheduler.getDate().isBigger(DateCustom.getActualDate())) {
                snsUserVaccineScheduler.add(vaccineScheduler);
            }
        }
        return snsUserVaccineScheduler;
    }


    /***
     * Gets the single entry obj VaccineScheduler for two key attributes: snsUserNumber, Date
     * @param snsUserNumber - sns user number
     * @param Date - DateCustom
     * @return - single entry obj VaccineScheduler for two key attributes: snsUserNumber, Date
     */
    public VaccineScheduler getVaccinationScheduler(long snsUserNumber, DateCustom Date) {
        for(VaccineScheduler obj : lVacSchedules) {
            if(obj.getSnsUserNumber() == snsUserNumber && obj.getDate().equals(Date)) {
                return obj;
            }
        }
        return null;
    }

    public boolean removeFromVaccinationScheduler(long snsUserNumber, DateCustom Date) {
        if(!lVacSchedules.isEmpty()) {
            for(VaccineScheduler obj : lVacSchedules) {
                if(obj.getSnsUserNumber() == snsUserNumber && obj.getDate().equals(Date)) {
                    return this.lVacSchedules.remove(obj);
                }
            }
            return false;
        }
        return false;
    }
}
