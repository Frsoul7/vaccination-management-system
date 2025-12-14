package app.domain.model;

import app.domain.model.store.VaccinationRecordsStore;
import app.domain.model.utils.DateCustom;
import app.domain.model.utils.TimeHour;
import app.interfaces.ExcludeFromJacocoGeneratedReport;

import java.io.Serializable;

public class HealthRecords implements Serializable {

    /***
     * Sns user health condition
     */
    private String healthCondition;

    /***
     * Sns user adverse reactions
     */
    private String adverseReactions;

    /***
     * Instance of vaccination records store
     */
    private VaccinationRecordsStore vaccRecordsStore;

    /***
     * Health condition by default
     */
    private static final String HEALTH_CONDITION_BY_DEFAULT = "# empty / nothing to declare #";

    /***
     * Adverse reactions by default
     */
    private static final String ADVERSE_REACTIONS_BY_DEFAULT = "# empty / nothing to declare #";

    /***
     * Empty HealthRecords constructor
     */
    @ExcludeFromJacocoGeneratedReport
    public HealthRecords() {
        setHealthCondition(HEALTH_CONDITION_BY_DEFAULT);
        setAdverseReactions(ADVERSE_REACTIONS_BY_DEFAULT);
        this.vaccRecordsStore = new VaccinationRecordsStore();
    }

    /***
     * Get sns user health condition
     * @return sns user health condition
     */
    @ExcludeFromJacocoGeneratedReport
    public String getHealthCondition() {
        return healthCondition;
    }

    /***
     * Get sns user adverse reactions
     * @return sns user adverse reactions
     */
    @ExcludeFromJacocoGeneratedReport
    public String getAdverseReactions() {
        return adverseReactions;
    }

    /***
     * Get sns user vaccination records store
     * @return sns user vaccination records store
     */
    @ExcludeFromJacocoGeneratedReport
    public VaccinationRecordsStore getVaccRecordsStore() {
        return vaccRecordsStore;
    }

    /***
     * Set sns user health condition
     * @param healthCondition - sns user health condition
     */
    @ExcludeFromJacocoGeneratedReport
    public void setHealthCondition(String healthCondition) {
        this.healthCondition = healthCondition;
    }

    /***
     * Set sns user adverse reactions
     * @param adverseReactions - sns user adverse reactions
     */
    @ExcludeFromJacocoGeneratedReport
    public void setAdverseReactions(String adverseReactions) {
        this.adverseReactions = adverseReactions;
    }

    /***
     * Method to record a new VaccinationRecords obj
     * @param vaccineName - vaccine name
     * @param doseStep - vaccine dose step
     * @param lotNumber - vaccine lot number
     * @param date - nurse vaccine administration DateCustom
     * @param time - nurse vaccine administration TimeHour
     * @return new VaccinationRecords obj
     */
    public VaccinationRecords recordVaccRecords(String vaccineName, int doseStep, String lotNumber, DateCustom date,
                                                TimeHour time) {
        return new VaccinationRecords(vaccineName, doseStep, lotNumber, date, time);
    }
}
