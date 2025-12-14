package app.domain.model.store;

import app.domain.model.AdministrationProcess;
import app.domain.model.Vaccine;
import app.domain.model.exceptions.OperationCanceledByUserException;
import app.interfaces.ExcludeFromJacocoGeneratedReport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class VaccineStore implements Serializable {

    /**
     * List of vaccine
     */
    private List<Vaccine> vaccineList;

    /**
     * Empty VaccineStore constructor with default values
     */
    @ExcludeFromJacocoGeneratedReport
    public VaccineStore() {
        vaccineList = new ArrayList<>();
    }

    public VaccineStore(VaccineStore otherVaccineStore) {
        vaccineList = otherVaccineStore.getVaccineList();
    }

    /**
     * xx
     *
     * @return
     */
    @ExcludeFromJacocoGeneratedReport
    public List<Vaccine> getVaccineList() {
        return new ArrayList<>(vaccineList);
    }

    public Vaccine specifyVaccine(String name, String lotNumber, int id, String brand, String vaccineType,
                                  String ageGroup, int numberDoses, int vaccineDosage)
            throws OperationCanceledByUserException {
        return new Vaccine(name, lotNumber, id, brand, vaccineType, ageGroup, numberDoses, vaccineDosage);
    }

    public boolean saveVaccine(Vaccine vaccine) {
        if(!this.validateVaccine(vaccine)) {
            return this.addVaccine(vaccine);
        }
        return false;
    }

    public boolean validateVaccine(Vaccine vaccine) {
        try {
            if(vaccine == null) {
                return true;
            }
            List<Vaccine> listVaccines = this.getVaccineList();
            if(!listVaccines.isEmpty()) {
                for(Vaccine vac : listVaccines) {
                    if(vaccine.equals(vac)) {
                        return true;
                    }
                }
            }
            return false;
        }
        catch(NullPointerException e) {
            return true;
        }
    }

    public boolean addVaccine(Vaccine vaccine) {
        return this.vaccineList.add(vaccine);
    }

    public boolean deleteVaccine(Vaccine vaccine) {
        return this.vaccineList.remove(vaccine);
    }

    public boolean checkID(int id) {

        if(!vaccineList.isEmpty()) {
            for(Vaccine vaccine : vaccineList) {
                if(vaccine.getId() == id) {
                    return true;
                }
            }
        }
        return false;
    }


    public Vaccine getVaccine(int id) {

        if(!vaccineList.isEmpty()) {
            for(Vaccine vaccine : vaccineList) {
                if(vaccine.getId() == id) {
                    return vaccine;
                }
            }
        }
        return null;
    }

    /***
     * Get vaccine obj by searching for unique vaccine lot number
     * @param vaccineLotNumber - vaccine lot number
     * @return vaccine obj
     */
    public Vaccine getVaccine(String vaccineLotNumber) {

        if(!vaccineList.isEmpty()) {
            for(Vaccine vaccine : vaccineList) {
                if(vaccine.getLotNumber().equals(vaccineLotNumber)) {
                    return vaccine;
                }
            }
        }
        return null;
    }

    /***
     * Get list of vaccines filtered by one vaccineType and checking the ageGroup with the sns use current age
     * @param vaccineTypeDesignation - vaccine type designation
     * @param age - sns use current age
     * @return list of vaccines filtered by one vaccineType and checking the ageGroup with the sns use current age
     */
    public List<Vaccine> getVaccinesListWithVaccineTypeAndAge(String vaccineTypeDesignation, int age) {
        List<Vaccine> listVaccinesWithSameVaccTypeAndAge = new ArrayList<>();
        for(Vaccine obj : vaccineList) {
            if(obj.getVaccineType().equals(vaccineTypeDesignation)) {
                if(obj.isAgeValid(age)) {
                    listVaccinesWithSameVaccTypeAndAge.add(obj);
                }
            }
        }
        return listVaccinesWithSameVaccTypeAndAge;
    }

    /***
     * Get list of vaccines filtered by one vaccineName and checking the ageGroup with the sns use current age
     * @param vaccineName - vaccine name
     * @param age - sns use current age
     * @return list of vaccines filtered by one vaccineName and checking the ageGroup with the sns use current age
     */
    public List<Vaccine> getVaccinesListWithVaccineNameAndAge(String vaccineName, int age) {
        List<Vaccine> listVaccinesWithSameVaccNameAndAge = new ArrayList<>();
        for(Vaccine obj : vaccineList) {
            if(obj.getName().equals(vaccineName)) {
                if(obj.isAgeValid(age)) {
                    listVaccinesWithSameVaccNameAndAge.add(obj);
                }
            }
        }
        return listVaccinesWithSameVaccNameAndAge;
    }

    public boolean addVaccinesAndAdm(VaccineStore vaccineStore) throws OperationCanceledByUserException {
        if(!vaccineStore.getVaccineList().isEmpty()) {
            for(Vaccine vac : vaccineStore.vaccineList) {
                Vaccine vacAux = new Vaccine(vac);
                if(!vacAux.getAdmProcList().isEmpty()) {
                    for(AdministrationProcess adm : vacAux.getAdmProcList()) {
                        new AdministrationProcess(adm);
                    }
                }
            }
            return true;
        }
        return false;
    }


    public boolean validateIfVaccineExist(String lotNumber){
        if(!vaccineList.isEmpty()) {
            for(Vaccine vaccine : vaccineList) {
                if(vaccine.getLotNumber().equals(lotNumber)) {
                    return true;
                }
            }
        }
        return false;
    }

}
