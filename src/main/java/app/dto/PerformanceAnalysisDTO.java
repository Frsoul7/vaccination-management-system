package app.dto;

import app.domain.model.PerformanceAnalysis;
import app.domain.model.PerformanceRecords;
import app.domain.model.utils.DateCustom;
import app.domain.model.utils.TimeHour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PerformanceAnalysisDTO {

    /**
     * array with difference between arrival and leaving sns users in a specific center
     */
    private int[] differenceBetweenArrLeav;

    /**
     * performance records analysed
     */
    private List<PerformanceRecords> inputList;

    /**
     * array with information about the slots of time interval defined with the maximum sum
     */
    private int[] contiguousSubListWithMaximumSum;

    /**
     * time interval defined by the center coordinator
     */
    private int timeInterval;

    /**
     * selected center open hour
     */
    private TimeHour openHour;

    /**
     * maximum sum of sublist
     */
    private int maxSum;

    /**
     * less effective period of the center
     */
    private TimeHour[] lessEffectivePeriod;

    private DateCustom date;

    public PerformanceAnalysisDTO(PerformanceAnalysis otherObject) {

        this.contiguousSubListWithMaximumSum = otherObject.getContiguousSubListWithMaximumSum();
        this.lessEffectivePeriod = otherObject.getLessEffectivePeriod();
        this.differenceBetweenArrLeav = otherObject.getDifferenceBetweenArrLeav();
        this.inputList = otherObject.getInputList();
        this.maxSum = otherObject.getMaxSum();
        this.openHour = otherObject.getOpenHour();
        this.timeInterval = otherObject.getTimeInterval();
        this.date = otherObject.getDate();
    }


    public int[] getDifferenceBetweenArrLeav() {
        return differenceBetweenArrLeav;
    }

    public void setDifferenceBetweenArrLeav(int[] differenceBetweenArrLeav) {
        this.differenceBetweenArrLeav = differenceBetweenArrLeav;
    }

    public List<PerformanceRecords> getInputList() {
        return new ArrayList<>(inputList);
    }

    public void setInputList(List<PerformanceRecords> inputList) {
        this.inputList = new ArrayList<>(inputList);
    }

    public int[] getContiguousSubListWithMaximumSum() {
        return contiguousSubListWithMaximumSum;
    }

    public void setContiguousSubListWithMaximumSum(int[] contiguousSubListWithMaximumSum) {
        this.contiguousSubListWithMaximumSum = contiguousSubListWithMaximumSum;
    }

    public int getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(int timeInterval) {
        this.timeInterval = timeInterval;
    }

    public String getOpenHour() {
        return openHour.toString();
    }

    public void setOpenHour(TimeHour openHour) {
        this.openHour = new TimeHour(openHour);
    }

    public int getMaxSum() {
        return maxSum;
    }

    public void setMaxSum(int maxSum) {
        this.maxSum = maxSum;
    }

    public TimeHour[] getLessEffectivePeriod() {
        TimeHour[] array = new TimeHour[lessEffectivePeriod.length];
        for(int i = 0; i < lessEffectivePeriod.length; i++) {
            array[i] = new TimeHour(lessEffectivePeriod[i]);
        }
        return array;
    }

    public void setLessEffectivePeriod(TimeHour[] lessEffectivePeriod) {
        this.lessEffectivePeriod = new TimeHour[lessEffectivePeriod.length];
        for(int i = 0; i < lessEffectivePeriod.length; i++) {
            this.lessEffectivePeriod[i] = new TimeHour(lessEffectivePeriod[i]);
        }
    }

    public String getDate() {
        return date.toString();
    }

    public void setDate(DateCustom date) {
        this.date = new DateCustom(date);
    }

    @Override
    public String toString() {
        return String.format("Maximum sum: %d\nLess effective " + "period: [%s %s - %s %s]", this.maxSum,
                             this.date.toString(), this.lessEffectivePeriod[0].toString(), this.date.toString(),
                             this.lessEffectivePeriod[1].toString());

    }

    public String toStringData() {
        String[] data = new String[inputList.size()];
        for(int i = 0; i < inputList.size(); i++) {
            data[i] = inputList.get(i).toStringLine();
        }
        return String.format("\nData analysed: %s\n", Arrays.toString(data));
    }

    public String toStringContinuousSublist() {

        return String.format("Maximum sum contiguous sublist: %s", Arrays.toString(contiguousSubListWithMaximumSum));
    }

}
