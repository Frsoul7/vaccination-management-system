package app.mappers;

import app.domain.model.ImportedDataInformation;
import app.domain.model.PerformanceAnalysis;
import app.dto.ImportedDataInformationDTO;
import app.dto.PerformanceAnalysisDTO;
import app.interfaces.ExcludeFromJacocoGeneratedReport;

import java.util.ArrayList;
import java.util.List;

public class PerformanceAnalysisMapper {

    @ExcludeFromJacocoGeneratedReport
    public PerformanceAnalysisMapper() {
    }

    public PerformanceAnalysisDTO toDTO(PerformanceAnalysis performanceAnalysis) {

        PerformanceAnalysisDTO performanceAnalysisDTO;
            performanceAnalysisDTO = new PerformanceAnalysisDTO(performanceAnalysis);
        return performanceAnalysisDTO;
    }

}
