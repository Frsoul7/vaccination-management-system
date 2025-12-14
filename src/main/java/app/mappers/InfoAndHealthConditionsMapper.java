package app.mappers;

import app.dto.InfoAndHealthConditionsDTO;

public class InfoAndHealthConditionsMapper {

    /***
     * Method to protect some sns user attributes with DTO
     *
     * @param name - sns user name
     * @param age - sns user age
     * @param adverseReactions - sns user adverse reactions records
     * @return new DTO obj with some sns user attributes
     */
    public InfoAndHealthConditionsDTO toDTO(String name, int age, String adverseReactions) {
        return new InfoAndHealthConditionsDTO(name, age, adverseReactions);
    }
}
