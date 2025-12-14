package app.controller;

import app.dto.VaccineSchedulerDTO;
import app.mappers.VaccineSchedulerMapper;
import app.domain.model.Company;
import app.domain.model.VaccinationCenter;
import app.domain.model.VaccineScheduler;
import app.domain.model.store.PerformanceRecordsStore;
import app.domain.model.utils.DateCustom;
import app.domain.model.utils.TimeHour;
import app.ui.console.utils.Utils;

import java.util.List;

public class RegisterArrivalOfSnsUserToTakeVaccineController {
    /***
     * Instance of the app
     */
    private final App app;
    /**
     * Instance of the company
     */
    private final Company company;

    private final VaccineSchedulerMapper vaccineSchedulerMapper;

    private VaccinationCenter vaccinationCenter;

    /***
     * Instance of performance records store
     */
    private PerformanceRecordsStore perfRecordsStore;

    public RegisterArrivalOfSnsUserToTakeVaccineController() {
        this.app = App.getInstance();
        this.company = this.app.getCompany();
        this.vaccineSchedulerMapper = new VaccineSchedulerMapper();
    }

    public List<VaccineSchedulerDTO> getVaccineScheduler(long snsUserNumber, int vaccinationCenterId) {
        this.vaccinationCenter =
                this.company.getVaccinationCenterStore().findVaccinationCenterById(vaccinationCenterId);

        List<VaccineScheduler> vaccineSchedulers =
                vaccinationCenter.getVacineSchedulerStore().getVaccineSchedulerBySnsUserNumber(snsUserNumber);

        if(!vaccineSchedulers.isEmpty()) {
            return this.vaccineSchedulerMapper.toDTO(vaccineSchedulers);
        } else {
            return null;
        }
    }

    public boolean setSnsUserArrivalTime(String arrivalTime, DateCustom selectedScheduleTimeDate,
                                         TimeHour selectedScheduleTime, long snsUserNumber) {
        try {
            int arrivalHour = TimeHour.hourTo24Format(arrivalTime);
            int arrivalMinutes = TimeHour.minutes(arrivalTime);
            TimeHour arrivalTimeHour = new TimeHour(arrivalHour, arrivalMinutes);
            int slotDuration = vaccinationCenter.getSlotDuration();
            //if Sns user arrives in the slot or in the slot before he can take the vaccine
            if(arrivalTimeHour.diffTimeInMinutes(selectedScheduleTime) <= slotDuration) {
                company.getSnsUserStore().getSnsUserBySnsUserNumber(snsUserNumber)
                       .setSnsUserArrivalTime(arrivalTimeHour);

                //new instructions
                perfRecordsStore = vaccinationCenter.getPerformanceRecordsStore();
                return perfRecordsStore.setPerfRecordsWithArrival(snsUserNumber, selectedScheduleTimeDate,
                                                                  arrivalTimeHour);
            } else {
                Utils.showText("The arrival time is not valid!");
                return false;
            }
        }
        catch(RuntimeException ex) {
            Utils.showText(ex.getMessage());
            return false;
        }
    }

    public boolean addSnsUserToWaitingList(long snsUserNumber) {
        return this.vaccinationCenter.addToWaitingList(
                company.getSnsUserStore().getSnsUserBySnsUserNumber(snsUserNumber));
    }
}
