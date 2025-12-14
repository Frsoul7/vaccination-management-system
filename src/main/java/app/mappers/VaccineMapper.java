package app.mappers;

import app.dto.VaccineDTO;
import app.domain.model.Vaccine;

import java.util.ArrayList;
import java.util.List;

public class VaccineMapper {

    /***
     * Method to protect List<Vaccine> listVaccinesFiltered with DTO
     * @param listVaccinesFiltered - List<Vaccine>
     * @return List<VaccineDTO> listVaccinesDTO
     */
    public List<VaccineDTO> toDTO(List<Vaccine> listVaccinesFiltered) {

        List<VaccineDTO> listVaccinesDTO = new ArrayList<>();

        for(Vaccine obj : listVaccinesFiltered) {
            listVaccinesDTO.add(
                    new VaccineDTO(obj.getName(), obj.getLotNumber(), obj.getId(), obj.getBrand(), obj.getVaccineType(),
                                   obj.getAgeGroup()));

        }
        return listVaccinesDTO;
    }
}
