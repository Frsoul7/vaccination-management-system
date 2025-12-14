package app.interfaces;

import app.domain.model.ImportedDataInformation;

import java.util.List;

public interface SortAlg {

    public void sortByArrivalTime(List<ImportedDataInformation> list);

    public void sortByLeavingTime(List<ImportedDataInformation> list);

}
