package app.domain.model.utils;

import app.domain.model.SNSUser;

import java.io.Serializable;
import java.util.Comparator;

public class ComparisonSNSUsersByArrivalTime implements Comparator<SNSUser>, Serializable {

    @Override
    public int compare(SNSUser snsu1, SNSUser snsu2) {
        TimeHour arrivalTime1 = snsu1.getSnsUserArrivalTime();
        TimeHour arrivalTime2 = snsu2.getSnsUserArrivalTime();

        return (arrivalTime1.calculatesMinutes() < arrivalTime2.calculatesMinutes()) ? -1 :
               ((arrivalTime1.calculatesMinutes() > arrivalTime2.calculatesMinutes()) ? 1 : 0);
    }
}
