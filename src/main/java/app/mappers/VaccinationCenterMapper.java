package app.mappers;

import app.dto.VaccinationCenterDTO;
import app.domain.model.VaccinationCenter;
import app.interfaces.ExcludeFromJacocoGeneratedReport;

import java.util.ArrayList;
import java.util.List;

public class VaccinationCenterMapper {

    @ExcludeFromJacocoGeneratedReport
    public VaccinationCenterMapper() {
    }

    public List<VaccinationCenterDTO> toDTO(List<VaccinationCenter> lVaccinationCenters) {

        List<VaccinationCenterDTO> lVaccinationCentersDTO = new ArrayList<>();

        for(VaccinationCenter obj : lVaccinationCenters) {

            String name = obj.getName();
            int id = obj.getVaccinationCenterId();
            String vaccinationCenterType = obj.getVaccinationCenterType();

            lVaccinationCentersDTO.add(new VaccinationCenterDTO(name, vaccinationCenterType, id));

        }
        return lVaccinationCentersDTO;
    }

}
