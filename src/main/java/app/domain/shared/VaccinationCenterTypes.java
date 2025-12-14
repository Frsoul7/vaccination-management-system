package app.domain.shared;

import app.interfaces.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
public enum VaccinationCenterTypes {

    MASSVACCINATIONCENTER("Mass Vaccination Center"), HEALTHCARECENTER("Healthcare Center");

    private final String description;

    VaccinationCenterTypes(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
