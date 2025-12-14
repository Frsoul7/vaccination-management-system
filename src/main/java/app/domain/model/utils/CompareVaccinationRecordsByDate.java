package app.domain.model.utils;

import app.domain.model.VaccinationRecords;

import javax.naming.directory.SearchResult;
import java.io.Serializable;
import java.util.Comparator;

public class CompareVaccinationRecordsByDate implements Comparator<VaccinationRecords>, Serializable {

    @Override
    public int compare(VaccinationRecords vacRecord1, VaccinationRecords vacRecord2) {
        return (vacRecord1.getNurseAdministrationDate().isBigger(vacRecord2.getNurseAdministrationDate())) ? 1 :
               (vacRecord2.getNurseAdministrationDate().isBigger(vacRecord1.getNurseAdministrationDate())) ? -1 : 0;
    }
}
