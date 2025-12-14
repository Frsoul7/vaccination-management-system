package app.domain.algorithms;

import app.domain.model.ImportedDataInformation;
import app.interfaces.ExcludeFromJacocoGeneratedReport;
import app.interfaces.SortAlg;

import java.util.ArrayList;
import java.util.List;

public class MergeSort implements SortAlg {

    public void sortByArrivalTime(List<ImportedDataInformation> list) {
        long startTime = System.currentTimeMillis();

        mergeSortArrival(list);

        long endTime = System.currentTimeMillis();
        System.out.println("MergeSort > sort by arrival time takes: " + (endTime - startTime) + "milliseconds");
    }


    @ExcludeFromJacocoGeneratedReport
    private List<ImportedDataInformation> mergeSortArrival(List<ImportedDataInformation> list) {
        int center;
        List<ImportedDataInformation> left_sep = new ArrayList<>();
        List<ImportedDataInformation> right_sep = new ArrayList<>();
        if(list.size() == 1) {
            return list;
        } else {
            center = list.size() / 2;
            for(int i = 0; i < center; i++) {
                left_sep.add(list.get(i));
            }

            //copy the right half of whole into the new arraylist.
            for(int i = center; i < list.size(); i++) {
                right_sep.add(list.get(i));
            }

            left_sep = mergeSortArrival(left_sep);
            right_sep = mergeSortArrival(right_sep);
            mergeArrival(left_sep, right_sep, list);
        }
        return list;
    }


    @ExcludeFromJacocoGeneratedReport
    private List<ImportedDataInformation> mergeArrival(List<ImportedDataInformation> left,
                                                       List<ImportedDataInformation> right,
                                                       List<ImportedDataInformation> sortedArray) {
        int leftIndex = 0;
        int rightIndex = 0;
        int sortIndex = 0;
        while(leftIndex < left.size() && rightIndex < right.size()) {
            //compare date
            if(!left.get(leftIndex).getArrivalDate().equals(right.get(rightIndex).getArrivalDate())) {
                if(left.get(leftIndex).getArrivalDate().isBigger(right.get(rightIndex).getArrivalDate())) {
                    sortedArray.set(sortIndex, right.get(rightIndex));
                    rightIndex++;
                } else {
                    sortedArray.set(sortIndex, left.get(leftIndex));
                    leftIndex++;
                }
                sortIndex++;
            } else {
                if(left.get(leftIndex).getArrivalTimeHour().isBigger(right.get(rightIndex).getArrivalTimeHour())) {
                    sortedArray.set(sortIndex, right.get(rightIndex));
                    rightIndex++;
                } else {
                    sortedArray.set(sortIndex, left.get(leftIndex));
                    leftIndex++;
                }
                sortIndex++;
            }
        }
        //get the elements that are not used
        List<ImportedDataInformation> rest;
        int indexOfElementsMissingCompare;
        if(leftIndex >= left.size()) {
            //there are elements from right that are not used
            rest = right;
            indexOfElementsMissingCompare = rightIndex;
        } else {
            rest = left;
            indexOfElementsMissingCompare = leftIndex;
        }

        // Copy the elements that are not used in comparison
        for(int i = indexOfElementsMissingCompare; i < rest.size(); i++) {
            sortedArray.set(sortIndex, rest.get(i));
            sortIndex++;
        }
        return sortedArray;
    }


    public void sortByLeavingTime(List<ImportedDataInformation> list) {
        long startTime = System.currentTimeMillis();

        mergeSortLeaving(list);
        long endTime = System.currentTimeMillis();
        System.out.println(
                "MergeSort > sort by leaving time and date takes: " + (endTime - startTime) + "milliseconds");
    }


    @ExcludeFromJacocoGeneratedReport
    private List<ImportedDataInformation> mergeSortLeaving(List<ImportedDataInformation> list) {
        int center;
        List<ImportedDataInformation> left_sep = new ArrayList<>();
        List<ImportedDataInformation> right_sep = new ArrayList<>();
        if(list.size() == 1) {
            return list;
        } else {
            center = list.size() / 2;
            for(int i = 0; i < center; i++) {
                left_sep.add(list.get(i));
            }

            //copy the right half of whole into the new arraylist.
            for(int i = center; i < list.size(); i++) {
                right_sep.add(list.get(i));
            }

            left_sep = mergeSortLeaving(left_sep);
            right_sep = mergeSortLeaving(right_sep);
            mergeLeaving(left_sep, right_sep, list);
        }
        return list;
    }


    @ExcludeFromJacocoGeneratedReport
    private List<ImportedDataInformation> mergeLeaving(List<ImportedDataInformation> left,
                                                       List<ImportedDataInformation> right,
                                                       List<ImportedDataInformation> sortedArray) {
        int leftIndex = 0;
        int rightIndex = 0;
        int sortIndex = 0;
        while(leftIndex < left.size() && rightIndex < right.size()) {
            //compare date
            if(!left.get(leftIndex).getLeavingDate().equals(right.get(rightIndex).getLeavingDate())) {
                if(left.get(leftIndex).getLeavingDate().isBigger(right.get(rightIndex).getLeavingDate())) {
                    sortedArray.set(sortIndex, right.get(rightIndex));
                    rightIndex++;
                } else {
                    sortedArray.set(sortIndex, left.get(leftIndex));
                    leftIndex++;
                }
                sortIndex++;
            } else {
                if(left.get(leftIndex).getLeavingTimeHour().isBigger(right.get(rightIndex).getLeavingTimeHour())) {
                    sortedArray.set(sortIndex, right.get(rightIndex));
                    rightIndex++;
                } else {
                    sortedArray.set(sortIndex, left.get(leftIndex));
                    leftIndex++;
                }
                sortIndex++;
            }
        }
        //get the elements that are not used
        List<ImportedDataInformation> rest;
        int indexOfElementsMissingCompare;
        if(leftIndex >= left.size()) {
            //there are elements from right that are not used
            rest = right;
            indexOfElementsMissingCompare = rightIndex;
        } else {
            rest = left;
            indexOfElementsMissingCompare = leftIndex;
        }

        // Copy the elements that are not used in comparison
        for(int i = indexOfElementsMissingCompare; i < rest.size(); i++) {
            sortedArray.set(sortIndex, rest.get(i));
            sortIndex++;
        }
        return sortedArray;
    }


}
