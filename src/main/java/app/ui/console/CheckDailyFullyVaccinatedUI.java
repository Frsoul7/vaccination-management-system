package app.ui.console;

import app.controller.CheckDailyFullyVaccinatedController;
import app.domain.model.CountByDate;
import app.domain.model.exceptions.OperationCanceledByUserException;
import app.domain.model.utils.DateCustom;
import app.domain.model.utils.Validations;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class CheckDailyFullyVaccinatedUI implements Runnable {

    private DateCustom startDate;
    private DateCustom endDate;
    private int vaccinationCenterId;
    private final CheckDailyFullyVaccinatedController controller;

    public CheckDailyFullyVaccinatedUI(int vaccinationCenterId) {
        setVaccinationCenterId(vaccinationCenterId);
        this.controller = new CheckDailyFullyVaccinatedController(getVaccinationCenterId());
    }

    @Override
    public void run() {
        Utils.showText("\n# # Export Vaccination Statistics # #");

        if(fillData()) {
            if(Utils.confirm("Do you want to confirm the filled data? (Y/N): ")) {
                List<CountByDate> lStatistics = new ArrayList<>();
                DateCustom actualDate = getStartDate();

                while(actualDate.compareTo(getEndDate()) <= 0) {
                    CountByDate countDate = new CountByDate(new DateCustom(actualDate));
                    countDate.setCount(controller.getNumberDailyOfFullyVaccinated(actualDate));
                    lStatistics.add(countDate);
                    actualDate.addDays(1);
                }

                for(CountByDate countDate : lStatistics) {
                    System.out.printf("%-10s - %d%n", countDate.getDate().toDayMonthYearFormat(), countDate.getCount());
                }

                if(Utils.confirm("Do you want to save data? (Y/N): ")) {
                    String filePath = Utils.readLineFromConsole("Full path for CSV file: ");
                    while(!Validations.isFilePathNewCSV(filePath)) {
                        filePath = Utils.readLineFromConsole(
                                "[error] - File already exists or is not valid.\nPlease try again: ");
                    }

                    if(controller.writeToCsv(filePath, lStatistics)){
                        System.out.println("CSV file generated with success");
                    }else{
                        System.out.println("CSV file not generated");

                    }

                }
            }
        }
    }


    private boolean fillData() {
        try {
            Utils.showText("All fields with '*' are mandatory\n");
            boolean dateValid = false;

            String sDate = Utils.readLineFromConsole("Start date (dd/mm/yyyy) *: ");
            while(!dateValid(sDate)) {
                sDate = Utils.readLineFromConsole("[ERROR] - Invalid start date\nPlease try again: ");
            }
            this.setStartDate(sDate);

            String eDate = Utils.readLineFromConsole("End date (dd/mm/yyyy) *: ");
            while(!dateValid(eDate) || !Validations.isPeriodValid(startDate, new DateCustom(eDate))) {
                eDate = Utils.readLineFromConsole("[ERROR] - Invalid end date\nPlease try again: ");
            }
            this.setEndDate(eDate);

            return true;
        }
        catch(OperationCanceledByUserException e) {
            Utils.showText(e.getMessage());
            return false;
        }
    }

    private boolean dateValid(String date) throws OperationCanceledByUserException {
        boolean valid = Validations.isDateFormatValid(date, true, true);
        if(valid) {
            DateCustom aux = new DateCustom(date);
            valid = (aux.compareTo(DateCustom.getActualDate()) <= 0);
        }
        return valid;
    }

    public DateCustom getStartDate() {
        return this.startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = new DateCustom(startDate);
    }

    public DateCustom getEndDate() {
        return this.endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = new DateCustom(endDate);
    }

    public int getVaccinationCenterId() {
        return this.vaccinationCenterId;
    }

    public void setVaccinationCenterId(int vaccinationCenterId) {
        this.vaccinationCenterId = vaccinationCenterId;
    }
}
