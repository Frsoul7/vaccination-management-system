package app.mappers;

import app.dto.VaccAdministrationDosageDTO;
import app.domain.model.Vaccine;

public class VaccAdministrationDosageMapper {
    /***
     * Method to protect some vaccine and vaccine administration attributes with DTO
     *
     * @param vacc - Vaccine obj
     * @param nextDoseStep - next vaccine dose step
     * @param vaccDosage - vaccine dosage [mL]
     * @return VaccAdministrationDosageDTO
     */
    public VaccAdministrationDosageDTO toDTO(Vaccine vacc, int nextDoseStep, int vaccDosage) {
        return new VaccAdministrationDosageDTO(vacc.getVaccineType(), vacc.getName(), vacc.getLotNumber(), nextDoseStep,
                                               vaccDosage);
    }
}
