package app.domain.shared;

import app.interfaces.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
public enum StepDosesPTtoStringConversion {
    FIRST(0, "Primeira"), SECOND(1, "Segunda"),THIRD(2,"Terceira"),FOURTH(3,"Quarta"),FIFTH(4,"Quinta"),DEFAULT(-1,
                                                                                                          " ");

    private final int stepDoseIndex;
    private final String ptDescription;

    StepDosesPTtoStringConversion(int stepDoseIndex, String ptDescription) {
        this.stepDoseIndex = stepDoseIndex;
        this.ptDescription = ptDescription;
    }

    public int getStepDoseIndex() {
        return stepDoseIndex;
    }

    public String getPtDescription() {
        return ptDescription;
    }

    public static String getPtDescriptionByStepDoseIndex(int stepDoseIndex) {
        for(StepDosesPTtoStringConversion obj : StepDosesPTtoStringConversion.values()) {
            if(obj.getStepDoseIndex()==stepDoseIndex){
                return obj.getPtDescription();
            }
        }
        return "#dose step description conversion not found#";
    }

    public static int getStepDoseIndexByPtDescription(String ptDescription){
        for(StepDosesPTtoStringConversion obj : StepDosesPTtoStringConversion.values()) {
            if(obj.getPtDescription().equals(ptDescription)){
                return obj.getStepDoseIndex();
            }
        }
        return -1;
    }
}
