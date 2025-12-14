package app.mappers;

import app.dto.VaccRecordsDTO;
import app.domain.model.VaccinationRecords;

public class VaccinationRecordsMapper {

    /***
     * Method to protect VaccinationRecords vaccRecord with DTO
     *
     * @param vaccRecord - VaccinationRecords obj
     * @return VaccRecordsDTO
     */
    public VaccRecordsDTO toDTO(VaccinationRecords vaccRecord) {
        return new VaccRecordsDTO(vaccRecord.getVaccineName(), vaccRecord.getDoseStep(),
                                  vaccRecord.getVaccineLotNumber(), vaccRecord.getNurseAdministrationDate(),
                                  vaccRecord.getNurseAdministrationTimeHour());
    }
}
