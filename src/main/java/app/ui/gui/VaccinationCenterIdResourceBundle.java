package app.ui.gui;

import java.util.Enumeration;
import java.util.ResourceBundle;

public class VaccinationCenterIdResourceBundle extends ResourceBundle {
    int vaccinationCenterId;

    public void setVaccinationCenterId(int vaccinationCenterId) {
        this.vaccinationCenterId = vaccinationCenterId;
    }

    @Override
    protected Object handleGetObject(String key) {
        return vaccinationCenterId;
    }

    /**
     * @return
     */
    @Override
    public Enumeration<String> getKeys() {
        return null;
    }

}
