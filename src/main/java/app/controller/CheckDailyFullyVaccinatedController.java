package app.controller;

import app.domain.model.*;
import app.domain.model.store.PerformanceRecordsStore;
import app.domain.model.store.SNSUserStore;
import app.domain.model.store.VaccineStore;
import app.domain.model.utils.DateCustom;
import pt.isep.lei.esoft.auth.AuthFacade;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CheckDailyFullyVaccinatedController {

    private final App app;
    private final Company company;
    private final AuthFacade authFacade;
    private final int vaccinationCenterId;

    /***
     *
     * @param vaccinationCenterId Vaccination Center Id of user logged in
     */
    public CheckDailyFullyVaccinatedController(int vaccinationCenterId) {
        this.app = App.getInstance();
        this.company = this.app.getCompany();
        this.authFacade = this.company.getAuthFacade();
        this.vaccinationCenterId = vaccinationCenterId;
    }

    public int getNumberDailyOfFullyVaccinated(DateCustom date) throws IllegalArgumentException {

        if(date == null) {
            throw new IllegalArgumentException("Invalid date received.");
        }

        PerformanceRecordsStore prStore =
                this.company.getVaccinationCenterStore().getVaccinationCenter(this.vaccinationCenterId)
                            .getPerformanceRecordsStore();

        List<PerformanceRecords> lPerfRecords = prStore.getPerformanceRecordsStore(date);

        SNSUserStore snsUStore = this.company.getSnsUserStore();
        VaccineStore vaccineStore = this.company.getVaccineStore();

        int totalFullVaccinated = 0;

        for(PerformanceRecords perfRecord : lPerfRecords) {
            long snsUserNumber = perfRecord.getSnsUserNumber();
            int doseTaken = perfRecord.getDoseStep();
            SNSUser snsUser = snsUStore.getSnsUserBySnsUserNumber(snsUserNumber);
            int age = snsUser.calculateAge(perfRecord.getNurseAdministrationDate());
            Vaccine vaccine = vaccineStore.getVaccine(perfRecord.getLotNumber());
            AdministrationProcess admProcess = vaccine.getAdministrationProcess(age);
            if(doseTaken == admProcess.getNumberOfDoses()) {
                totalFullVaccinated++;
            }
        }

        return totalFullVaccinated;
    }

    public boolean writeToCsv(String filePath, List<CountByDate> lStatistics) {
        final String HEADER = "Date(dd/mm/yyyy);Number Of Fully Vaccinated";
        try {
            FileWriter fileOutput = new FileWriter(filePath);
            PrintWriter out = new PrintWriter(fileOutput);
            out.printf("%s%n", HEADER);
            for(CountByDate line : lStatistics) {
                out.printf("%s;%d%n", line.getDate().toDayMonthYearFormat(), line.getCount());
            }
            out.close();
            return true;
        }
        catch(IOException e) {
            return false;
        }
    }
}