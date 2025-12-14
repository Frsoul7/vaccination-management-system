package app.ui;

import app.ui.console.MainMenuUI;
import app.ui.gui.MainFX;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author Paulo Maio <pam@isep.ipp.pt>
 *         Edgar Moreira <1010100@isep.ipp.pt>
 *         Fernando Ribeiro <1060064@isep.ipp.pt>
 *         José Silva <1060568@isep.ipp.pt>
 *         Pedro Gomes <1060588@isep.ipp.pt>
*/

public class Main {

    private static final String CONSOLE = "--console";
    private static final String GRAPHIC = "--graphic";

    public static void main(String[] args) {
        boolean consoleMode = false;
        try {
            consoleMode = isConsoleMode(args);
            // CONSOLE MODE
            if(consoleMode) {
                MainMenuUI menu = new MainMenuUI();
                menu.run();
            }
            // GRAPHIC MODE
            else {
                Application.launch(MainFX.class, args);
            }
        }
        catch(IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean isConsoleMode(String[] arguments) throws IllegalArgumentException {
        int argsLength = arguments.length;

        if (argsLength > 0){
            String argValue = arguments[0];
            switch(argValue) {
                case CONSOLE: {
                    return true;
                }
                case GRAPHIC: {
                    return false;
                }
                default:
                    throw new IllegalArgumentException("[ERROR] Argument is invalid");
            }
        }

        return true;
    }
}
