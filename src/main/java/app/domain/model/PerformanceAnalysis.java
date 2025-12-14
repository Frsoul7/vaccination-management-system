package app.domain.model;

import app.domain.model.utils.DateCustom;
import app.domain.model.utils.TimeHour;
import app.interfaces.ExcludeFromJacocoGeneratedReport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class PerformanceAnalysis {

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

    @ExcludeFromJacocoGeneratedReport
    public PerformanceAnalysis(int[] differenceBetweenArrLeav, List<PerformanceRecords> inputList,
                               int[] contiguousSubListWithMaximumSum, int timeInterval, TimeHour openHour,DateCustom date) {

        setDifferenceBetweenArrLeav(differenceBetweenArrLeav);
        setInputList(inputList);
        setContiguousSubListWithMaximumSum(contiguousSubListWithMaximumSum);
        setTimeInterval(timeInterval);
        setOpenHour(openHour);
        this.lessEffectivePeriod = new TimeHour[2];
        setDate(date);
    }

    @ExcludeFromJacocoGeneratedReport
    public int[] getDifferenceBetweenArrLeav() {
        return differenceBetweenArrLeav;
    }

    @ExcludeFromJacocoGeneratedReport
    public void setDifferenceBetweenArrLeav(int[] differenceBetweenArrLeav) {
        this.differenceBetweenArrLeav = differenceBetweenArrLeav;
    }

    @ExcludeFromJacocoGeneratedReport
    public List<PerformanceRecords> getInputList() {
        return new ArrayList<>(inputList);
    }

    @ExcludeFromJacocoGeneratedReport
    public void setInputList(List<PerformanceRecords> inputList) {
        this.inputList = new ArrayList<>(inputList);
    }

    @ExcludeFromJacocoGeneratedReport
    public int[] getContiguousSubListWithMaximumSum() {
        return contiguousSubListWithMaximumSum;
    }

    @ExcludeFromJacocoGeneratedReport
    public void setContiguousSubListWithMaximumSum(int[] contiguousSubListWithMaximumSum) {
        this.contiguousSubListWithMaximumSum = contiguousSubListWithMaximumSum;
    }

    @ExcludeFromJacocoGeneratedReport
    public int getTimeInterval() {
        return timeInterval;
    }

    @ExcludeFromJacocoGeneratedReport
    public void setTimeInterval(int timeInterval) {
        this.timeInterval = timeInterval;
    }

    @ExcludeFromJacocoGeneratedReport
    public TimeHour getOpenHour() {
        return new TimeHour(openHour);
    }

    @ExcludeFromJacocoGeneratedReport
    public void setOpenHour(TimeHour openHour) {
        this.openHour = new TimeHour(openHour);
    }

    @ExcludeFromJacocoGeneratedReport
    public int getMaxSum() {
        return maxSum;
    }

    @ExcludeFromJacocoGeneratedReport
    public void setMaxSum(int maxSum) {
        this.maxSum = maxSum;
    }

    @ExcludeFromJacocoGeneratedReport
    public TimeHour[] getLessEffectivePeriod() {
        TimeHour[] array = new TimeHour[lessEffectivePeriod.length];
        for(int i = 0; i < lessEffectivePeriod.length; i++) {
            array[i] = new TimeHour(lessEffectivePeriod[i]);
        }
        return array;
    }

    @ExcludeFromJacocoGeneratedReport
    public void setLessEffectivePeriod(TimeHour[] lessEffectivePeriod) {
        this.lessEffectivePeriod = new TimeHour[lessEffectivePeriod.length];
        for(int i = 0; i < lessEffectivePeriod.length; i++) {
            this.lessEffectivePeriod[i] = new TimeHour(lessEffectivePeriod[i]);
        }
    }

    public void setDate(DateCustom date){
        this.date= new DateCustom(date);
    }

    public DateCustom getDate(){
        return new DateCustom(this.date);
    }


    @ExcludeFromJacocoGeneratedReport
    public void maximumSum() {
        this.maxSum = 0;
        for(int i = 0; i < this.contiguousSubListWithMaximumSum.length; i++) {
            maxSum = maxSum + contiguousSubListWithMaximumSum[i];
        }
    }

    @ExcludeFromJacocoGeneratedReport
    public void calculateLessEffectivePeriod() {
        int firstIndex = 0;

        for(int i = 0; i < differenceBetweenArrLeav.length; i++) {
            for(int j = 0, k = i; j < contiguousSubListWithMaximumSum.length; j++, k++) {
                if(contiguousSubListWithMaximumSum[j] != differenceBetweenArrLeav[k]) {
                    j = contiguousSubListWithMaximumSum.length;
                } else {
                    firstIndex = i;
                }
            }
        }
        int lastIndex = firstIndex + contiguousSubListWithMaximumSum.length - 1;

        int minutesToSumOpenHourBegin = timeInterval * firstIndex;
        int minutesToSumOpenHourEnd = timeInterval * lastIndex;

        int openH = openHour.getHour();
        int openM = openHour.getMinutes();

        int hourBeginLessEffective = openH + (minutesToSumOpenHourBegin + openM) / 60;
        int minutesBeginLessEffective = (minutesToSumOpenHourBegin + openM) % 60;

        int hourEndLessEffective = openH + (minutesToSumOpenHourEnd + openM + timeInterval) / 60;
        int minutesEndLessEffective = (minutesToSumOpenHourEnd + openM + timeInterval) % 60;

        lessEffectivePeriod[0] = new TimeHour(hourBeginLessEffective, minutesBeginLessEffective);
        lessEffectivePeriod[1] = new TimeHour(hourEndLessEffective, minutesEndLessEffective);
    }
}
