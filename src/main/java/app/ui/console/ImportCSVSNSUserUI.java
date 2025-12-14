package app.ui.console;

import app.controller.ImportCSVSNSUserController;

public class ImportCSVSNSUserUI extends ImportCSVUI {

    private ImportCSVSNSUserController controller;

    public ImportCSVSNSUserUI() {
        super();
        this.controller = new ImportCSVSNSUserController();
    }

    @Override
    public String getTitle() {
        return "\n# # Import SNS Users # #";
    }

    @Override
    public void controllerRun() {
        this.controller.setFile(this.getFilePath());
        this.controller.consume();
        this.controller.printImportReport();
    }

}
