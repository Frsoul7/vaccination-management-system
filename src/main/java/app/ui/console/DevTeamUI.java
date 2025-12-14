package app.ui.console;

import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @init_author Paulo Maio <pam@isep.ipp.pt>
 *
 * @next_authors
 *  Fernando Ribeiro <1060064@isep.ipp.pt>
 *  José Silva <1060568@isep.ipp.pt>
 *  Pedro Gomes <1060588@isep.ipp.pt>
 *  Edgar Moreira <1010100@isep.ipp.pt>
 */
public class DevTeamUI implements Runnable{

    public DevTeamUI()
    {

    }
    public void run()
    {
        List<MenuItem> options = new ArrayList<MenuItem>();

        System.out.println("\n");
        System.out.printf("# Development Team G074 #\n");
        System.out.printf("\t Edgar Moreira - 1010100@isep.ipp.pt \n");
        System.out.printf("\t Fernando Ribeiro - 1060064@isep.ipp.pt \n");
        System.out.printf("\t José Silva - 1060568@isep.ipp.pt \n");
        System.out.printf("\t Pedro Gomes - 1060588@isep.ipp.pt \n");
        System.out.printf("\t Ricardo Soares - 1200662@isep.ipp.pt \n");
        System.out.printf("\t Inês Veiga - 1210852@isep.ipp.pt \n");
        System.out.println("\n");



        int option = 0;
        do
        {
            option = Utils.showAndSelectIndex(options, "\n\nDev Team Menu:");

            if ( (option >= 0) && (option < options.size()))
            {
                options.get(option).run();
            }
        }
        while (option != -1 );
    }
}
