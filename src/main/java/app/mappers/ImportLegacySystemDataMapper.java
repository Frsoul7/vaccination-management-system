package app.mappers;

import app.domain.model.ImportedDataInformation;
import app.domain.model.VaccinationCenter;
import app.dto.ImportedDataInformationDTO;
import app.dto.VaccinationCenterDTO;
import app.interfaces.ExcludeFromJacocoGeneratedReport;

import java.util.ArrayList;
import java.util.List;

public class ImportLegacySystemDataMapper {

    @ExcludeFromJacocoGeneratedReport
    public ImportLegacySystemDataMapper() {
    }

    public List<ImportedDataInformationDTO> toDTO(List<ImportedDataInformation> importedDataInformationList) {

        List<ImportedDataInformationDTO> limportedDataInformationDTO = new ArrayList<>();
        for(ImportedDataInformation obj : importedDataInformationList) {
            limportedDataInformationDTO.add(new ImportedDataInformationDTO(obj));
        }
        return limportedDataInformationDTO;
    }

}
