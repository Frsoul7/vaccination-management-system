package app.domain.model.store;

import app.domain.model.*;
import app.domain.model.exceptions.OperationCanceledByUserException;
import app.domain.model.utils.ComparisonSNSUsersByArrivalTime;
import app.domain.model.utils.DateCustom;
import app.domain.model.utils.TimeHour;
import app.interfaces.ExcludeFromJacocoGeneratedReport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/***
 * @author Paulo Maio <pam@isep.ipp.pt>
 *         Edgar Moreira <1010100@isep.ipp.pt>
 *         Fernando Ribeiro <1060064@isep.ipp.pt>
 *         José Silva <1060568@isep.ipp.pt>
 *         Pedro Gomes <1060588@isep.ipp.pt>
 */

public class VaccinationCenterStore implements Serializable {

    /***
     * Instance of the vaccination center
     */
    private VaccinationCenter vc;

    /***
     * Instance of the list of vaccination centers
     */
    private List<VaccinationCenter> vaccinationCenterList;

    /***
     * Instances the method to compare the SNS users by arrival time to the vaccination center
     */
    private final ComparisonSNSUsersByArrivalTime criterionByArrivalTime = new ComparisonSNSUsersByArrivalTime();

    /***
     * Vaccination Center identifier
     */
    private static int id = 0;

    /***
     * Instances a new list of vaccination centers
     */
    @ExcludeFromJacocoGeneratedReport
    public VaccinationCenterStore() {
        vaccinationCenterList = new ArrayList<>();
    }

    public VaccinationCenterStore(VaccinationCenterStore vaccinationCenterStore) {
        vaccinationCenterList = vaccinationCenterStore.getVaccinationCenterList();
    }

    /***
     * Get the vaccination center list
     * @return the vaccination center list if is registered in the app
     */
    @ExcludeFromJacocoGeneratedReport
    public List<VaccinationCenter> getVaccinationCenterList() {
        return new ArrayList<>(this.vaccinationCenterList);
    }

    /***
     * Constructor to register a new vaccination center of type "massive"
     * @param name                  - name of the vaccination center
     * @param address               - address of the vaccination center
     * @param phoneNumber           - phone number of the vaccination center
     * @param emailAddress          - email address of the vaccination center
     * @param faxNumber             - fax number of the vaccination center
     * @param websiteAddress        - website address of the vaccination center
     * @param openHour              - opening hour of the vaccination center
     * @param closeHour             - closing hour of the vaccination center
     * @param slotDuration          - slot duration of the vaccination center
     * @param maxVaccinesPerSlot    - max vaccine per slot of the vaccination center
     * @param vaccinationCenterType - type of the vaccination center
     * @return a vaccination center of type "massive"
     */
    public VaccinationCenter registerVaccinationCenter(String name, String address, long phoneNumber,
                                                       String emailAddress, long faxNumber, String websiteAddress,
                                                       TimeHour openHour, TimeHour closeHour, int slotDuration,
                                                       int maxVaccinesPerSlot, String vaccinationCenterType)
            throws OperationCanceledByUserException {
        return new VaccinationCenter(name, address, phoneNumber, emailAddress, faxNumber, websiteAddress, openHour,
                                     closeHour, slotDuration, maxVaccinesPerSlot, vaccinationCenterType);
    }

    /***
     * Constructor to register a new vaccination center of type "healthcare"
     * @param name                  - name of the vaccination center
     * @param address               - address of the vaccination center
     * @param phoneNumber           - phone number of the vaccination center
     * @param emailAddress          - email address of the vaccination center
     * @param faxNumber             - fax number of the vaccination center
     * @param websiteAddress        - website address of the vaccination center
     * @param openHour              - opening hour of the vaccination center
     * @param closeHour             - closing hour of the vaccination center
     * @param slotDuration          - slot duration of the vaccination center
     * @param maxVaccinesPerSlot    - max vaccine per slot of the vaccination center
     * @param vaccinationCenterType - type of the vaccination center
     * @param designation           - designation of the vaccination center
     * @return a vaccination center of type "healthcare"
     */
    public VaccinationCenter registerVaccinationCenter(String name, String address, long phoneNumber,
                                                       String emailAddress, long faxNumber, String websiteAddress,
                                                       TimeHour openHour, TimeHour closeHour, int slotDuration,
                                                       int maxVaccinesPerSlot, String vaccinationCenterType,
                                                       String designation) throws OperationCanceledByUserException {

        return new VaccinationCenter(name, address, phoneNumber, emailAddress, faxNumber, websiteAddress, openHour,
                                     closeHour, slotDuration, maxVaccinesPerSlot, vaccinationCenterType, designation);
    }

    /***
     * Method to save a vaccination center
     * @param vaccinationCenter         - vaccination center object
     * @return saves the vaccination center if true, returns null if false
     */
    public boolean saveVaccinationCenter(VaccinationCenter vaccinationCenter) {
        if(this.validateVaccinationCenter(vaccinationCenter)) {
            return this.addVaccinationCenter(vaccinationCenter);
        }
        return false;
    }

    /***
     * Validates if the vaccination center already exists
     * @param vaccinationCenter         - vaccination center
     * @return true if vaccination center doesn't exist; false if it exists
     */
    public boolean validateVaccinationCenter(VaccinationCenter vaccinationCenter) {

        if(vaccinationCenter == null) {
            return false;
        }
        List<VaccinationCenter> vaccinationCenterList = this.getVaccinationCenterList();
        if(!vaccinationCenterList.isEmpty()) {
            for(VaccinationCenter vac : vaccinationCenterList) {
                if(vac.equalsUniqueAttributes(vaccinationCenter)) {
                    return false;
                }
            }
        }
        return true;
    }

    /***
     * Adds the vaccination center to the list with an unique ID
     * @param vaccinationCenter     - vaccination center to be added to the store list with an ID
     * @return true if vaccination center is added; false if not
     */
    public boolean addVaccinationCenter(VaccinationCenter vaccinationCenter) {
        id++;
        vaccinationCenter.setVaccinationCenterId(id);
        return this.vaccinationCenterList.add(vaccinationCenter);
    }

    public VaccinationCenter findVaccinationCenterById(int vaccinationCenterId) {
        for(VaccinationCenter obj : this.vaccinationCenterList) {
            if(obj.getVaccinationCenterId() == vaccinationCenterId) {
                return obj;
            }
        }
        return null;
    }


    @ExcludeFromJacocoGeneratedReport
    public List<SNSUser> getVaccinationCenterWaitingRoomList(int vaccinationCenterId) {
        this.vc = findVaccinationCenterById(vaccinationCenterId);
        List<SNSUser> listWaitingRoom = vc.getWaitingList();

        Collections.sort(listWaitingRoom, criterionByArrivalTime);

        return listWaitingRoom;
    }

    @ExcludeFromJacocoGeneratedReport
    public List<SNSUser> getVaccinationCenterWaitingRoomList(VaccinationCenter vaccCenter) {
        List<SNSUser> listWaitingRoom = vaccCenter.getWaitingList();

        Collections.sort(listWaitingRoom, criterionByArrivalTime);

        return listWaitingRoom;
    }

    /***
     * Get of the time intervals of the available slots
     * @param otherDate             - otherDate represents the dates in string type
     * @param vaccinationCenterId   - vaccination center identifier
     * @return the time intervals of the available slots (e.g. 11:00 - 11:30)
     */
    @ExcludeFromJacocoGeneratedReport
    //todo@José: falta ver este fluxo
    public int[][] getTimeOfFreeSlots(String otherDate, int vaccinationCenterId) {

        int[][] timeOfFreeSlots = null;

        DateCustom date = new DateCustom(otherDate);

        if(date.isBigger(DateCustom.getActualDate())) {
            VaccinationCenter vaccinationCenterAux = findVaccinationCenterById(vaccinationCenterId);
            if(vaccinationCenterAux != null) {

                VaccineSchedulerStore vacSchedulerStore = vaccinationCenterAux.getVacineSchedulerStore();

                int difBetweenCloseHourAndOpenHourInMinutes =
                        (vaccinationCenterAux.getCloseHour().getHour() - vaccinationCenterAux.getOpenHour().getHour()) *
                        60 - vaccinationCenterAux.getOpenHour().getMinutes() +
                        vaccinationCenterAux.getCloseHour().getMinutes();

                float numberOfSlotsPerDay = ((float)difBetweenCloseHourAndOpenHourInMinutes /
                                             (float)vaccinationCenterAux.getSlotDuration());

                int[][] numberOfVaccinesPerSlot = new int[(int)numberOfSlotsPerDay][3];

                // fill minutes of begin and end for each the slot

                int aux = 0;
                for(int i = 0; i < numberOfVaccinesPerSlot.length; i++) {

                    if(i != numberOfVaccinesPerSlot.length - 1) {
                        numberOfVaccinesPerSlot[i][0] = aux;
                        numberOfVaccinesPerSlot[i][1] = aux + vaccinationCenterAux.getSlotDuration();
                        aux = numberOfVaccinesPerSlot[i][1];
                    } else {
                        //the last slot have the extra time if the division is a float number
                        numberOfVaccinesPerSlot[i][0] = aux;
                        numberOfVaccinesPerSlot[i][1] = difBetweenCloseHourAndOpenHourInMinutes;
                    }
                }
                vacSchedulerStore.getVaccineSchedulesList();
                if(!vacSchedulerStore.getVaccineSchedulesList().isEmpty()) {

                    for(VaccineScheduler obj : vacSchedulerStore.getVaccineSchedulesList()) {
                        //date of scheduler equals to the dates in schedules list
                        if(obj.getDate().equals(date)) {
                            //find the slot and add a vaccine to the slot
                            int slotScheduleTime = Math.abs(
                                    obj.getSchedulerTime().diffTimeInMinutes(vaccinationCenterAux.getOpenHour()));

                            numberOfVaccinesPerSlot[(slotScheduleTime / vaccinationCenterAux.getSlotDuration())][2] +=
                                    1;

                        }
                    }
                }
                int numberOfFreeSlots = 0;

                for(int[] ints : numberOfVaccinesPerSlot) {
                    if(ints[2] < vaccinationCenterAux.getMaxVaccinesPerSlot()) {
                        numberOfFreeSlots++;
                    }
                }

                timeOfFreeSlots = new int[numberOfFreeSlots][4];
                int h;
                int m;
                int contAux = numberOfFreeSlots;
                for(int i = 0; i < numberOfVaccinesPerSlot.length; i++) {

                    if(numberOfVaccinesPerSlot[i][2] < vaccinationCenterAux.getMaxVaccinesPerSlot()) {

                        h = numberOfVaccinesPerSlot[i][0] / 60;
                        m = numberOfVaccinesPerSlot[i][0] % 60;

                        timeOfFreeSlots[numberOfFreeSlots - contAux][0] =
                                h + vaccinationCenterAux.getOpenHour().getHour();
                        timeOfFreeSlots[numberOfFreeSlots - contAux][1] =
                                m + vaccinationCenterAux.getOpenHour().getMinutes();

                        h = numberOfVaccinesPerSlot[i][1] / 60;
                        m = numberOfVaccinesPerSlot[i][1] % 60;

                        timeOfFreeSlots[numberOfFreeSlots - contAux][2] =
                                h + vaccinationCenterAux.getOpenHour().getHour();
                        timeOfFreeSlots[numberOfFreeSlots - contAux][3] =
                                m + vaccinationCenterAux.getOpenHour().getMinutes();
                        --contAux;
                    }
                }
            }
        }
        return timeOfFreeSlots;
    }

    public VaccinationCenter getVaccinationCenter(int vaccinationCenterId) {
        return findVaccinationCenterById(vaccinationCenterId);
    }


    public boolean addVaccinationCentersAndAllObjectsAssociated(VaccinationCenterStore vaccinationCenterStore)
            throws OperationCanceledByUserException {
        if(!vaccinationCenterStore.getVaccinationCenterList().isEmpty()) {
            for(VaccinationCenter vacc : vaccinationCenterStore.vaccinationCenterList) {
                id++;
                VaccinationCenter vaccAux = new VaccinationCenter(vacc);
                if(!vaccAux.getVacineSchedulerStore().getVaccineSchedulesList().isEmpty()) {
                    for(VaccineScheduler vSch : vaccAux.getVacineSchedulerStore().getVaccineSchedulesList()) {
                        new VaccineScheduler(vSch);
                    }
                }
                if(!vaccAux.getPerformanceRecordsStore().getListPerfRecords().isEmpty()) {
                    for(PerformanceRecords perf : vaccAux.getPerformanceRecordsStore().getListPerfRecords()) {
                        new PerformanceRecords(perf);
                    }
                }
            }
            return true;
        }
        return false;
    }

}
