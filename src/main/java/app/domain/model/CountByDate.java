package app.domain.model;

import app.domain.model.utils.DateCustom;
import app.interfaces.ExcludeFromJacocoGeneratedReport;

public class CountByDate {

    private DateCustom date;
    private int count;

    @ExcludeFromJacocoGeneratedReport
    public CountByDate(DateCustom date) {
        setDate(date);
        setCount(0);
    }

    @ExcludeFromJacocoGeneratedReport
    public CountByDate(DateCustom date, int count) {
        setDate(date);
        setCount(count);
    }

    @ExcludeFromJacocoGeneratedReport
    public DateCustom getDate() {
        return this.date;
    }

    @ExcludeFromJacocoGeneratedReport
    public void setDate(DateCustom date) {
        this.date = date;
    }

    @ExcludeFromJacocoGeneratedReport
    public int getCount() {
        return count;
    }

    @ExcludeFromJacocoGeneratedReport
    public void setCount(int count) {
        this.count = count;
    }
}
