package app.domain.algorithms;

import app.domain.model.ImportedDataInformation;
import app.interfaces.SortAlg;

import java.util.List;

public class BubbleSort implements SortAlg {

    public void sortByArrivalTime(List<ImportedDataInformation> list) {
        long startTime = System.currentTimeMillis();

        for(int i = 0; i < list.size() - 1; i++) {
            for(int j = 0; j < list.size() - i -1; j++) {
                //Date
                if(list.get(j).getArrivalDate().isBigger(list.get(j + 1).getArrivalDate())) {
                    ImportedDataInformation importedDataInformationAux = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, importedDataInformationAux);
                }
                //equals date sort by hour
                if(list.get(j).getArrivalDate().equals(list.get(j + 1).getArrivalDate())) {
                    //hour
                    if(list.get(j).getArrivalTimeHour().isBigger(list.get(j + 1).getArrivalTimeHour())) {
                        ImportedDataInformation importedDataInformationAux = list.get(j);
                        list.set(j, list.get(j + 1));
                        list.set(j + 1, importedDataInformationAux);
                    }
                }
            }
        }


        long endTime = System.currentTimeMillis();
        System.out.println("BubbleSort > sort by arrival time takes: " + (endTime - startTime) + "milliseconds");
    }


    public void sortByLeavingTime(List<ImportedDataInformation> list) {
        long startTime = System.currentTimeMillis();

        for(int i = 0; i < list.size() - 1; i++) {
            for(int j = 0; j < list.size() - i -1 ; j++) {
                //Date
                if(list.get(j).getLeavingDate().isBigger(list.get(j + 1).getLeavingDate())) {
                    ImportedDataInformation importedDataInformationAux = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, importedDataInformationAux);
                }
                if(list.get(j).getLeavingDate().equals(list.get(j + 1).getLeavingDate())) {
                    //hour
                    if(list.get(j).getLeavingTimeHour().isBigger(list.get(j + 1).getLeavingTimeHour())) {
                        ImportedDataInformation importedDataInformationAux = list.get(j);
                        list.set(j, list.get(j + 1));
                        list.set(j + 1, importedDataInformationAux);
                    }
                }
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println(
                "BubbleSort > sort by leaving time and date takes: " + (endTime - startTime) + "milliseconds");
    }

}
