package app.controller;

import app.domain.model.Company;
import app.interfaces.Constants;
import app.ui.console.utils.Utils;
import pt.isep.lei.esoft.auth.AuthFacade;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class ImportCSVController implements Constants {

    private final App app;
    private final Company company;
    private final AuthFacade authFacade;


    private File file;
    private String delimiter;
    private int initialLine;
    private List<String[]> csvData;
    private String[] csvDataObservation;
    private int numberOfAttributes;
    private String[] header;


    public ImportCSVController(String filePath) throws IllegalArgumentException {
        this.app = App.getInstance();
        this.company = app.getCompany();
        this.authFacade = this.getCompany().getAuthFacade();
        this.setFile(filePath);
    }

    public ImportCSVController() throws IllegalArgumentException {
        this.app = App.getInstance();
        this.company = app.getCompany();
        this.authFacade = company.getAuthFacade();
    }

    public void setFile(String filePath) throws IllegalArgumentException {
        this.file = new File(filePath);
        if(!this.file.exists()) {
            this.file = null;
            throw new IllegalArgumentException("Invalid file.");
        }
        setDelimiter();
        setNumberOfAttributes();
        setInitialLine();
        try {
            setCsvData();
        }
        catch(FileNotFoundException e) {
            throw new IllegalArgumentException("Invalid file.");
        }
    }

    public void setDelimiter() {
        this.delimiter = this.defineDelimiter();
    }

    public void setInitialLine() {
        this.initialLine = defineInitialLine();
    }

    public void setCsvData() throws FileNotFoundException {
        this.csvData = this.readAll();
        this.csvDataObservation = new String[this.getCsvData().size()];
    }

    public void setHeader(String[] header) {
        String[] aux = new String[this.getNumberOfAttributes() + 1];
        System.arraycopy(header, 0, aux, 0, header.length);
        aux[header.length] = "Observations";
        this.header = aux;
    }

    public void setNumberOfAttributes() {
        this.numberOfAttributes = this.defineNumberOfAttributes();
    }

    public File getFile() {
        return this.file;
    }

    public String getDelimiter() {
        return this.delimiter;
    }

    private int getInitialLine() {
        return this.initialLine;
    }

    private List<String[]> getCsvData() {
        return this.csvData;
    }

    private String[] getHeader() {
        return this.header;
    }

    private String getHeader(int index) {
        return this.header[index];
    }

    private int getNumberOfAttributes() {
        return this.numberOfAttributes;
    }

    public Company getCompany() {
        return this.company;
    }

    public AuthFacade getAuthFacade() {
        return this.authFacade;
    }

    private List<String[]> readAll() throws FileNotFoundException {
        Scanner readFile = new Scanner(this.getFile());
        List<String[]> fileData = new ArrayList<>();

        while(readFile.hasNextLine()) {
            String[] lineFile = readFile.nextLine().split(this.delimiter);
            fileData.add(lineFile);
        }

        readFile.close();
        return fileData;
    }

    public void consume() {
        int actualLine = 0;
        if(!this.getCsvData().isEmpty()) {
            System.out.print("Importing: ");
            for(String[] line : this.getCsvData()) {
                try {
                    if(actualLine >= this.getInitialLine()) {
                        System.out.print(".");
                        if(line.length != this.getNumberOfAttributes()) {
                            csvDataObservation[actualLine] =
                                    (line.length < this.getNumberOfAttributes()) ? INFO_WRONG_NUMBER_ATTRIBUTES_LESS :
                                    INFO_WRONG_NUMBER_ATTRIBUTES_MORE;
                        } else {
                            if(importLine(line)) {
                                csvDataObservation[actualLine] = INFO_IMPORT_WITH_SUCCESS;
                            } else {
                                csvDataObservation[actualLine] = INFO_IMPORT_WITHOUT_SUCCESS;
                            }
                        }
                    } else {
                        this.setHeader(line);
                    }
                }
                catch(IllegalArgumentException e) {
                    csvDataObservation[actualLine] = e.getMessage();
                }
                finally {
                    actualLine++;
                }
            }
            System.out.println();
        }
    }

    public List<String[]> generateReport() throws NullPointerException {
        List<String[]> lstData = this.getCsvData();


        if(lstData == null || lstData.isEmpty()) {
            throw new NullPointerException(INFO_IMPORT_FILE_FIRST);
        }

        List<String[]> report = new ArrayList<>();
        int index = 0;

        for(String[] row : lstData) {
            int rowAttributes = this.getNumberOfAttributes() + 1;

            String[] rowReport = new String[rowAttributes];
            System.arraycopy(row, 0, rowReport, 0, row.length);
            rowReport[(rowAttributes - 1)] = csvDataObservation[index];

            report.add(rowReport);
            index++;
        }

        return report;

    }

    public void printImportReport() {
        String line = "--------------------------------------------------------------";
        Utils.showText("\n# # Import Report # #\n");
        List<String[]> importReport = this.generateReport();

        int lineIndex = 0;
        for(String[] lineReport : importReport) {
            if(lineIndex >= this.getInitialLine()) {
                for(int i = 0; i < lineReport.length; i++) {
                    if(this.getHeader().length != 0) {
                        String val = this.getHeader(i);
                        System.out.printf("%-25s : ", val);
                    }

                    System.out.printf("%s%n", (lineReport[i] == null) ? "" : lineReport[i]);
                }
                System.out.printf("%n%s%n%n", line);
            }
            lineIndex++;
        }
    }

    public abstract String defineDelimiter();

    public abstract int defineNumberOfAttributes();

    public abstract int defineInitialLine();

    public abstract boolean importLine(String[] line);

}
