package app.controller;

import app.domain.algorithms.BruteForce;
import app.domain.model.Company;
import app.domain.model.PerformanceAnalysis;
import app.domain.model.PerformanceRecords;
import app.domain.model.VaccinationCenter;
import app.domain.model.exceptions.OperationCanceledByUserException;
import app.domain.model.store.PerformanceRecordsStore;
import app.domain.model.store.VaccinationCenterStore;
import app.domain.model.utils.Configurations;
import app.domain.model.utils.DateCustom;
import app.domain.model.utils.TimeHour;
import app.dto.ImportedDataInformationDTO;
import app.dto.PerformanceAnalysisDTO;
import app.interfaces.BruteForceAlg;
import app.interfaces.Constants;
import app.mappers.ImportLegacySystemDataMapper;
import app.mappers.PerformanceAnalysisMapper;
import app.ui.console.utils.Utils;
import jdk.jshell.execution.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PerformanceAnalysisController implements Constants {

    /**
     * Instance of the app
     */
    private final App app;

    /**
     * Instance of the company
     */
    private final Company company;

    /**
     * Instance of vaccination center store
     */
    private VaccinationCenterStore vcStore;

    /***
     * Instance of the vaccination center
     */
    private VaccinationCenter vc;

    /***
     * Instance of performance records
     */
    private PerformanceRecordsStore perfRecordsStore;


    private List<PerformanceRecords> lInputPerformanceRecords;

    private PerformanceAnalysis outPut;

    private int[] subListArray;

    private PerformanceAnalysisMapper map;

    public PerformanceAnalysisController(int vaccinationCenterId) {
        this.app = App.getInstance();
        this.company = app.getCompany();
        this.vcStore = company.getVaccinationCenterStore();
        this.vc = vcStore.getVaccinationCenter(vaccinationCenterId);
        this.perfRecordsStore = vc.getPerformanceRecordsStore();
        lInputPerformanceRecords = new ArrayList<>();
        this.map = new PerformanceAnalysisMapper();
    }

    public boolean validateData(String date, int timeInterval) {
        if(vc.validateTimeInterval(timeInterval)) {
            try {
                DateCustom dateNew = new DateCustom(date);
                if(perfRecordsStore.checkIfExistsRecordsForTheSelectedDate(dateNew)) {
                    return true;
                }
            }
            catch(RuntimeException ex) {
                Utils.showText(ex.getMessage());
            }
        } else {
            Utils.showText("The time interval is not valid!");
            return false;
        }
        return false;
    }


    public boolean analyseThePerformanceOfACenter(String date, int timeInterval) {
        DateCustom dateNew = null;
        try {
            dateNew = new DateCustom(date);
        }
        catch(RuntimeException ex) {
            Utils.showText(ex.getMessage());
        }
        try {
            List<PerformanceRecords> lPerformanceRecords = perfRecordsStore.getListPerfRecords();
            TimeHour arrivalTime;
            TimeHour leavingTime;
            int numberOfSlots;
            numberOfSlots = vc.numberOfSlotsByTimeInterval(timeInterval);
            int arrivalSnsUser[] = new int[numberOfSlots];
            int leavingSnsUser[] = new int[numberOfSlots];
            int index;
            DateCustom dateRegist =null;
            for(PerformanceRecords obj : lPerformanceRecords) {
                 dateRegist = obj.getNurseAdministrationDate();
                if(dateRegist.equals(dateNew)) {
                    lInputPerformanceRecords.add(obj);
                    arrivalTime = obj.getArrivalTimeHour();
                    leavingTime = obj.getLeavingTimeHour();
                    index = vc.slotIndex(arrivalTime, timeInterval);
                    arrivalSnsUser[index] += 1;
                    index = vc.slotIndex(leavingTime, timeInterval);
                    leavingSnsUser[index] += 1;
                }
            }

            int[] differenceBetweenArrLeavSnsUser = new int[numberOfSlots];

            for(int i = 0; i < differenceBetweenArrLeavSnsUser.length; i++) {
                differenceBetweenArrLeavSnsUser[i] = arrivalSnsUser[i] - leavingSnsUser[i];
            }

           // differenceBetweenArrLeavSnsUser= new int[] {29, -32, -9, -25, 44, 12, -61, 51, -9, 44, 74, 4};

            subListArray = algorithmAnalysis(differenceBetweenArrLeavSnsUser);

            outPut = new PerformanceAnalysis(differenceBetweenArrLeavSnsUser, lInputPerformanceRecords, subListArray,
                                             timeInterval, vc.getOpenHour(),dateRegist);
            outPut.maximumSum();
            outPut.calculateLessEffectivePeriod();


        }
        catch(IOException|ClassNotFoundException|InstantiationException|IllegalAccessException ex) {
            return false;
        }
        return true;
    }

    public int[] algorithmAnalysis(int[] differenceBetween)
            throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {

        BruteForceAlg bruteForce = Configurations.getBrutAlg();

        subListArray = bruteForce.determineContiguousSublistWithMaximumSum(differenceBetween);

        return subListArray;
    }

    public PerformanceAnalysisDTO getPerformanceAnalysis() {
        return map.toDTO(outPut);
    }

}

