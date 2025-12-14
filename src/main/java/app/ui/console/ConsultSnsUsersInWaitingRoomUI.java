package app.ui.console;

import app.controller.ConsultSnsUsersInWaitingRoomController;
import app.dto.WaitingRoomDTO;
import app.ui.console.utils.Utils;

import java.util.List;

public class ConsultSnsUsersInWaitingRoomUI implements Runnable {

    /***
     * Vaccination Center Id attribute
     */
    private int vaccinationCenterId;

    /**
     * Consult sns user waiting room instance
     */
    private final ConsultSnsUsersInWaitingRoomController controller;

    /**
     * Consult SnsUser WaitingRoom UI constructor
     *
     * @param vaccinationCenterId
     */
    public ConsultSnsUsersInWaitingRoomUI(int vaccinationCenterId) {
        controller = new ConsultSnsUsersInWaitingRoomController();
        this.vaccinationCenterId = vaccinationCenterId;
    }

    /**
     * Method used to run the ConsultSnsUserWaitingRoom UI
     */
    public void run() {
        Utils.showText("\n# # List of SNS Users currently in the waiting room #" + " #");

        try {
            List<WaitingRoomDTO> listWaitingRoomDTO = controller.getListOfSnsUsers(vaccinationCenterId);
            if(!listWaitingRoomDTO.isEmpty()) {

                for(WaitingRoomDTO vcDTO : listWaitingRoomDTO) {
                    System.out.println(vcDTO.toString());
                }

            } else {
                Utils.showText("\nAttention!: The waiting room list is empty");
            }
        }
        catch(RuntimeException ex) {
            Utils.showText(ex.getMessage());
            Utils.showText("\n[warning] the list could not be presented, please contact your maintenance team!");
        }
    }
}
