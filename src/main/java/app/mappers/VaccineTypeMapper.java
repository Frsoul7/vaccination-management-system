package app.mappers;

import app.dto.VaccineTypeDTO;
import app.domain.model.VaccineType;
import app.interfaces.ExcludeFromJacocoGeneratedReport;

import java.util.ArrayList;
import java.util.List;

public class VaccineTypeMapper {

    @ExcludeFromJacocoGeneratedReport
    public VaccineTypeMapper() {
    }


    public List<VaccineTypeDTO> toDTO(List<VaccineType> lVacType) {

        List<VaccineTypeDTO> lVacTypeDTO = new ArrayList<>();

        for(VaccineType obj : lVacType) {
            String designation = obj.getDesignation();

            lVacTypeDTO.add(new VaccineTypeDTO(designation));

        }
        return lVacTypeDTO;
    }

}
