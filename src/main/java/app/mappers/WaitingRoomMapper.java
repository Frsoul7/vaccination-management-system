package app.mappers;

import app.dto.WaitingRoomDTO;
import app.domain.model.SNSUser;

import java.util.ArrayList;
import java.util.List;

public class WaitingRoomMapper {

    /**
     * Method to protect List<SNSUser> listWaitingRoom with DTO
     *
     * @param listWaitingRoom
     *
     * @return
     */
    public List<WaitingRoomDTO> toDTO(List<SNSUser> listWaitingRoom) {

        List<WaitingRoomDTO> listWaitingRoomDTO = new ArrayList<>();

        for(SNSUser obj : listWaitingRoom) {
            listWaitingRoomDTO.add(
                    new WaitingRoomDTO(obj.getName(), obj.getGender(), obj.getBirthDate(), obj.getSnsUserNumber(),
                                       obj.getPhoneNumber(), obj.getSnsUserArrivalTime()));
        }
        return listWaitingRoomDTO;
    }

}
