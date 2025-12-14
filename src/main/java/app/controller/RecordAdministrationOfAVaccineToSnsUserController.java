package app.controller;

import app.domain.model.utils.Notifications;
import app.dto.*;
import app.mappers.*;
import app.domain.model.*;
import app.domain.model.store.*;
import app.domain.model.utils.DateCustom;
import app.domain.model.utils.TimeHour;
import app.interfaces.ExcludeFromJacocoGeneratedReport;
import app.ui.console.utils.Utils;

import java.io.IOException;
import java.util.*;

public class RecordAdministrationOfAVaccineToSnsUserController {

    /***
     * Selected sns user name
     */
    private String name;

    /***
     * Selected sns user age
     */
    private int age;
    /***
     * Next vaccine dose step
     */
    private int nextDoseStep;
    /**
     * Instance of vaccination center store
     */
    private VaccinationCenterStore vcStore;

    /***
     * Instance of vaccination center
     */
    private VaccinationCenter vaccCenter;

    /***
     * Instance of vaccine scheduler store
     */
    private VaccineSchedulerStore schedulerStore;

    /***
     * Instance of vaccine scheduler
     */
    private VaccineScheduler vaccScheduler;

    /***
     * Instance of vaccine store
     */
    private VaccineStore vStore;
    /***
     * Instance of vaccine
     */
    private Vaccine vacc;
    /***
     * Instance of vaccine administration process
     */
    private AdministrationProcess adminProcess;
    /**
     * Instance of sns user
     */
    private SNSUser snsUser;

    /***
     * Instance of sns user health records
     */
    private HealthRecords healthRecords;

    /***
     * Instance of vaccination records store
     */
    private VaccinationRecordsStore vaccRecordsStore;

    /***
     * Instance of vaccinations records - old
     */
    private VaccinationRecords vaccRecordOld;

    /***
     * Instance of vaccinations records - new
     */
    private VaccinationRecords vaccRecordNew;
    /***
     * Instance of performance records
     */
    private PerformanceRecordsStore perfRecordsStore;

    private Timer recoveryRoomTimer;
    /**
     * Instance of the app
     */
    private final App app;

    /**
     * Instance of the company
     */
    private final Company company;

    /**
     * Instance of the WaitingRoom Mapper
     */
    private final WaitingRoomMapper wrMapper;
    /***
     * Instance of the InfoAndHealthContitions Mapper
     */
    private final InfoAndHealthConditionsMapper ihcMapper;
    /***
     * Instance of the vaccine Mapper
     */
    private final VaccineMapper vMapper;
    /***
     * Instance of vaccine administration dosage Mapper
     */
    private final VaccAdministrationDosageMapper vadMapper;
    /***
     * Instance of vaccination records Mapper
     */
    private final VaccinationRecordsMapper vrMapper;
    /**
     * Instance of employee store
     */
    private final SNSUserStore snsUStore;

    /***
     * Sns user name by default
     */
    private static final String NAME_BY_DEFAULT = "# name not assigned #";
    /***
     * Sns user age by default
     */
    private static final int AGE_BY_DEFAULT = 0;

    /***
     * Next vaccine dose by default
     */
    private static final int NEXT_DOSE_STEP_BY_DEFAULT = -1;

    /***
     * First vaccine dose by default
     */
    private static final int FIRST_DOSE_STEP_BY_DEFAULT = 0;

    private static final long RECOVERY_ROOM_DELAY_BY_DEFAULT = 5000; //30 minutes //30 * 60 * 1000

    /***
     * Empty RecordAdministrationOfAVaccineToSnsUserController constructor
     */
    public RecordAdministrationOfAVaccineToSnsUserController() {
        this.app = App.getInstance();
        this.company = app.getCompany();
        this.snsUStore = this.company.getSnsUserStore();
        this.wrMapper = new WaitingRoomMapper();
        this.ihcMapper = new InfoAndHealthConditionsMapper();
        this.vMapper = new VaccineMapper();
        this.vadMapper = new VaccAdministrationDosageMapper();
        this.vrMapper = new VaccinationRecordsMapper();
        setName(NAME_BY_DEFAULT);
        setAge(AGE_BY_DEFAULT);
        setNextDoseStep(NEXT_DOSE_STEP_BY_DEFAULT);
    }

    /***
     * Get next vaccine dose step
     * @return next vaccine dose step
     */
    @ExcludeFromJacocoGeneratedReport
    public int getNextDoseStep() {
        return nextDoseStep;
    }

    /***
     * Get sns user name
     * @return sns user name
     */
    @ExcludeFromJacocoGeneratedReport
    public String getName() {
        return name;
    }

    /***
     * Get sns user current age
     * @return sns user current age
     */
    @ExcludeFromJacocoGeneratedReport
    public int getAge() {
        return age;
    }

    /***
     * Set next vaccine dose step
     * @param nextDoseStep next vaccine dose step
     */
    @ExcludeFromJacocoGeneratedReport
    public void setNextDoseStep(int nextDoseStep) {
        this.nextDoseStep = nextDoseStep;
    }

    /***
     * Set sns user name
     * @param name sns user name
     */
    @ExcludeFromJacocoGeneratedReport
    public void setName(String name) {
        this.name = name;
    }

    /***
     * Set sns user current age
     * @param age sns user current age
     */
    @ExcludeFromJacocoGeneratedReport
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Method used to get the list of SNSUsers in the waiting room with DTO
     *
     * @param vaccinationCenterId - vaccination center id
     *
     * @return list of SNSUsers in the waiting room with DTO
     */
    public List<WaitingRoomDTO> getListOfSnsUsers(int vaccinationCenterId) {
        vcStore = company.getVaccinationCenterStore();
        vaccCenter = vcStore.getVaccinationCenter(vaccinationCenterId);
        List<SNSUser> listWaitingRoom = vcStore.getVaccinationCenterWaitingRoomList(vaccCenter);

        return wrMapper.toDTO(listWaitingRoom);
    }

    /***
     * Method used to get the sns user InfoAndHealthConditions with DTO
     *
     * @param snsUserNumber - sns user number
     * @return sns user InfoAndHealthConditions with DTO
     */
    public InfoAndHealthConditionsDTO getInfoAndHealthConditions(long snsUserNumber) {
        snsUser = snsUStore.getSnsUserBySnsUserNumber(snsUserNumber);
        setName(snsUser.getName());
        setAge(snsUser.calculateAge());
        healthRecords = snsUser.getHealthRecords();
        String adverseReactions = healthRecords.getAdverseReactions();

        return ihcMapper.toDTO(name, age, adverseReactions);
    }

    /***
     * Method that returns the list of vaccines by one vaccine type or, if there's a vaccination
     * record in sns user history for that type of vaccine it lists vaccines only with the same name. The list is
     * returned with DTO protection
     *
     * @param snsUserNumber - sns user number
     * @return list of vaccines
     */
    public List<VaccineDTO> getListOfVaccines(long snsUserNumber, String date) {

        schedulerStore = vaccCenter.getVacineSchedulerStore();
        //DateCustom actualDate = DateCustom.getActualDate();
        DateCustom vaccineDate = new DateCustom(date);
        vaccScheduler = schedulerStore.getVaccinationScheduler(snsUserNumber, vaccineDate);
        String vaccineTypeDesignation = vaccScheduler.getVaccineTypeDesignation();
        vStore = company.getVaccineStore();
        List<Vaccine> listVaccinesWithSameVaccTypeAndAge =
                vStore.getVaccinesListWithVaccineTypeAndAge(vaccineTypeDesignation, age);
        vaccRecordsStore = healthRecords.getVaccRecordsStore();
        vaccRecordOld = vaccRecordsStore.findLastVaccRecordByVaccType(listVaccinesWithSameVaccTypeAndAge);
        List<Vaccine> listVaccinesFiltered = new ArrayList<>();
        if(vaccRecordOld == null) {
            listVaccinesFiltered = listVaccinesWithSameVaccTypeAndAge;
        } else {
            String vaccineName = vaccRecordOld.getVaccineName();
            List<Vaccine> listVaccinesWithSameVaccNameAndAge =
                    vStore.getVaccinesListWithVaccineNameAndAge(vaccineName, age);
            listVaccinesFiltered = listVaccinesWithSameVaccNameAndAge;
        }
        return vMapper.toDTO(listVaccinesFiltered);
    }

    /***
     * Method that returns vaccine and vaccine administration process attributes according to the vaccine selected
     * and passed (by lot number). It matches the sns user history with administration process rules for that vaccine
     * and sns current age. The final data is passed with DTO protection
     * @param vaccineLotNumber - vaccine lot number
     * @return vaccine and vaccine administration process attributes, namely vaccine dosage [mL] for this specific
     * vaccine step
     */
    public VaccAdministrationDosageDTO getVaccineAdministrationProcess(String vaccineLotNumber) {
        vacc = vStore.getVaccine(vaccineLotNumber);
        adminProcess = vacc.getAdministrationProcess(age);
        if(vaccRecordOld == null) {
            setNextDoseStep(FIRST_DOSE_STEP_BY_DEFAULT);
        } else {
            setNextDoseStep(vaccRecordOld.getNextDoseStep());
        }
        int vaccDosage = adminProcess.getVaccineDosageByDoseStep(this.nextDoseStep);

        return vadMapper.toDTO(vacc, nextDoseStep, vaccDosage);
    }

    /***
     * Method that manages que creation of a new VaccinationRecords obj and returns a DTO protected image of that obj
     * @param nurseAdministrationTime - nurse vaccine administration time
     * @return DTO protected image of a new VaccinationRecords obj
     */
    public VaccRecordsDTO recordAdministrationOfAVaccine(String nurseAdministrationTime) {
        TimeHour nurseAdministrationTimeHour = new TimeHour(nurseAdministrationTime, "12");
        vaccRecordNew = healthRecords.recordVaccRecords(vacc.getName(), this.getNextDoseStep(), vacc.getLotNumber(),
                                                        vaccScheduler.getDate(), nurseAdministrationTimeHour);
        return vrMapper.toDTO(vaccRecordNew);
    }

    /***
     * xxx
     * @return
     */
    public boolean saveVaccineRecords() {

        if(vaccRecordsStore.saveVaccinationRecords(vaccRecordNew)) {

            perfRecordsStore = vaccCenter.getPerformanceRecordsStore();
            if(perfRecordsStore.setPerfRecordsWithVaccAdministration(snsUser.getSnsUserNumber(), vaccRecordNew)) {

                if(vaccCenter.moveSnsUserFromWaitingRoomToRecoveryRoom(snsUser)) {

                    if(launchTimer()) {
                        return true;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public boolean launchTimer() {
        TimerTask task = new TimerTask() {
            public void run() {
                perfRecordsStore.setPerfRecordsWithTimerAfterLeaving(snsUser.getSnsUserNumber(),
                                                                     vaccScheduler.getDate(), TimeHour.getActualTime());
                vaccCenter.removeFromRecoveryList(snsUser);
                schedulerStore.removeFromVaccinationScheduler(snsUser.getSnsUserNumber(), vaccScheduler.getDate());

                try {
                    Notifications.sendSMSEndOfRecoveryTimeNotification(snsUser);
                }
                catch(RuntimeException|IOException ex) {
                    Utils.showText("\n\n########\n[warning] the end of recovery time couldn't be sent to sns " +
                                   "user:\n"+snsUser.toString());
                    Utils.showText("#########");

                }finally {
                    recoveryRoomTimer.cancel();
                    //System.out.println("fechou o assunto");
                }
            }
        };

        recoveryRoomTimer = new Timer();
        recoveryRoomTimer.schedule(task, RECOVERY_ROOM_DELAY_BY_DEFAULT);
        return true;
    }

}


