package app.ui.console;

import app.controller.App;
import app.controller.SerializationController;
import app.domain.model.Company;
import app.ui.console.utils.Utils;

import java.io.IOException;

public class LoadDataUi implements Runnable {

    /**
     * Instance of the app
     */
    private final App app;

    /**
     * Instance of the company
     */
    private final Company company;

    private final SerializationController serCont;

    public LoadDataUi() {
        this.app = App.getInstance();
        this.company = app.getCompany();
        this.serCont = company.getSerializationController();
    }

    public void run() {

        //Read data
        if(Utils.confirm("Do you want to load all saved data? (Y/N): ")) {
            company.getSerializationController().readFiles();
        } else {
            Utils.showText("Data not loaded in System ");
        }

    }
}
