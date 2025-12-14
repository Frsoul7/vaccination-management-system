package app.controller;

import app.dto.WaitingRoomDTO;
import app.mappers.WaitingRoomMapper;
import app.domain.model.Company;
import app.domain.model.SNSUser;
import app.domain.model.store.VaccinationCenterStore;

import java.util.List;

public class ConsultSnsUsersInWaitingRoomController {

    /**
     * Instance of the app
     */
    private final App app;

    /**
     * Instance of the company
     */
    private final Company company;

    /**
     * Instance of the WaitingRoom Mapper
     */
    private final WaitingRoomMapper wrMapper;

    /**
     * Instance of vaccination center store
     */
    private VaccinationCenterStore vcStore;

    /**
     * Empty ConsultSnsUsersInWaitingRoom constructor with default values
     */
    public ConsultSnsUsersInWaitingRoomController() {
        this.app = App.getInstance();
        this.company = app.getCompany();
        this.wrMapper = new WaitingRoomMapper();
    }

    /**
     * Method used to get the list of SNSUsers in the waiting room with DTO
     *
     * @param vaccinationCenterId
     *
     * @return
     */
    public List<WaitingRoomDTO> getListOfSnsUsers(int vaccinationCenterId) {

        vcStore = company.getVaccinationCenterStore();

        List<SNSUser> listWaitingRoom = vcStore.getVaccinationCenterWaitingRoomList(vaccinationCenterId);

        return wrMapper.toDTO(listWaitingRoom);
    }
}
