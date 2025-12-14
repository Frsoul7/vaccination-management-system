package app.dto;

import app.domain.model.utils.DateCustom;
import app.domain.model.utils.TimeHour;
import app.interfaces.ExcludeFromJacocoGeneratedReport;

public class VaccineSchedulerDTO {
    private long snsUserNumber;

    private String vaccineTypeDesignation;

    private String vaccinationCenterName;

    private DateCustom date;

    private TimeHour schedulerTime;

    // Constructor
    @ExcludeFromJacocoGeneratedReport
    public VaccineSchedulerDTO(long snsUserNumber, String vaccineTypeDesignation, String vaccinationCenterName,
                               DateCustom date, TimeHour schedulerTime) {
        this.snsUserNumber = snsUserNumber;
        this.vaccineTypeDesignation = vaccineTypeDesignation;
        this.vaccinationCenterName = vaccinationCenterName;
        this.date = date;
        this.schedulerTime = schedulerTime;
    }


    @ExcludeFromJacocoGeneratedReport
    public long getSnsUserNumber() {
        return snsUserNumber;
    }

    @ExcludeFromJacocoGeneratedReport
    public String getVaccineTypeDesignation() {
        return vaccineTypeDesignation;
    }

    @ExcludeFromJacocoGeneratedReport
    public String getVaccinationCenterName() {
        return vaccinationCenterName;
    }

    @ExcludeFromJacocoGeneratedReport
    public DateCustom getDate() {
        return date;
    }

    @ExcludeFromJacocoGeneratedReport
    public TimeHour getSchedulerTime() {
        return schedulerTime;
    }

    @Override
    @ExcludeFromJacocoGeneratedReport
    public String toString() {
        return String.format("\nSNS User number: %d\nVaccine Type designation: %s\nVaccination Center name: %s\nDate:" +
                             " %s\nSchedule Time: %s", this.getSnsUserNumber(), this.getVaccineTypeDesignation(),
                             this.getVaccinationCenterName(), this.getDate().toString(),
                             this.getSchedulerTime().toString());
    }

}
