package app.mappers;

import app.dto.VaccineSchedulerDTO;
import app.domain.model.VaccineScheduler;
import app.interfaces.ExcludeFromJacocoGeneratedReport;

import java.util.ArrayList;
import java.util.List;

public class VaccineSchedulerMapper {

    @ExcludeFromJacocoGeneratedReport
    public VaccineSchedulerMapper() {
    }

    public List<VaccineSchedulerDTO> toDTO(List<VaccineScheduler> vaccineSchedulers) {
        List<VaccineSchedulerDTO> lVaccineSchedulerSnsUser = new ArrayList<>();

        for(VaccineScheduler obj : vaccineSchedulers) {
            lVaccineSchedulerSnsUser.add(
                    new VaccineSchedulerDTO(obj.getSnsUserNumber(), obj.getVaccineTypeDesignation(),
                                            obj.getVaccinationCenterName(), obj.getDate(), obj.getSchedulerTime()));
        }
        return lVaccineSchedulerSnsUser;

    }
}
