package app.ui.console;

import app.domain.model.exceptions.OperationCanceledByUserException;
import app.domain.model.utils.Validations;
import app.ui.console.utils.Utils;

public abstract class ImportCSVUI implements Runnable {

    private String filePath;

    public ImportCSVUI() {
    }

    private void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public void run() {

        Utils.showText(this.getTitle());

        if(fillData()) {
            if(Utils.confirm("Confirms the filled data? (Y/N): ")) {
                this.controllerRun();
            }
        }
    }

    private boolean fillData() {
        try {
            Utils.showText("All fields with '*' are mandatory\n");

            String filePath = Utils.readLineFromConsole("Full path for CSV file *: ");
            while(!Validations.isFilePathValid(filePath, true, true)) {
                filePath = Utils.readLineFromConsole("[error] - Invalid content\nPlease try again: ");
            }
            setFilePath(filePath);
            return true;
        }
        catch(OperationCanceledByUserException ex) {
            Utils.showText(ex.getMessage());
            return false;
        }
    }


    public abstract String getTitle();

    public abstract void controllerRun();

}
