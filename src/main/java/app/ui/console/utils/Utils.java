package app.ui.console.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Paulo Maio <pam@isep.ipp.pt> Edgar Moreira <1010100@isep.ipp.pt> Fernando Ribeiro <1060064@isep.ipp.pt> José
 * Silva <1060568@isep.ipp.pt> Pedro Gomes <1060588@isep.ipp.pt>
 */
public class Utils {

    static public String readLineFromConsole(String prompt) {
        try {
            System.out.print(prompt);

            InputStreamReader converter = new InputStreamReader(System.in);
            BufferedReader in = new BufferedReader(converter);

            return in.readLine();
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    static public int readIntegerFromConsole(String prompt, int defultValue) {
        do {
            try {
                String input = readLineFromConsole(prompt);

                int value = Integer.parseInt(input);

                return value;
            }
            catch(NumberFormatException ex) {
                return defultValue;
                //Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while(true);
    }

    static public long readLongFromConsole(String prompt, long defaultValue) {
        do {
            try {
                String input = readLineFromConsole(prompt);
                long value = Long.parseLong(input);

                return value;
            }
            catch(NumberFormatException ex) {
                return defaultValue;
                //Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while(true);
    }

    static public double readDoubleFromConsole(String prompt, double defaultValue) {
        do {
            try {
                String input = readLineFromConsole(prompt);

                double value = Double.parseDouble(input);

                return value;
            }
            catch(NumberFormatException ex) {
                return defaultValue;
                //Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while(true);
    }

    static public Date readDateFromConsole(String prompt, String dateFormat, Date defaultValue) {
        do {
            try {
                String strDate = readLineFromConsole(prompt);

                SimpleDateFormat df = new SimpleDateFormat(dateFormat);
                Date date = df.parse(strDate);

                return date;
            }
            catch(ParseException ex) {
                return defaultValue;
                //Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while(true);
    }

    static public boolean confirm(String message) {
        String input;
        do {
            input = Utils.readLineFromConsole(message);
        } while(!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("n"));

        return input.equalsIgnoreCase("y");
    }

    static public Object showAndSelectOne(List list, String header) {
        showList(list, header);
        return selectsObject(list);
    }

    static public int showAndSelectIndex(List list, String header) {
        showList(list, header);
        return selectsIndex(list);
    }

    static public int showAndSelectIndexWithoutCancel(List list, String header) {
        showListWithoutCancel(list, header);
        return selectsIndexWithoutCancel(list);
    }

    static public void showList(List list, String header) {
        System.out.println(header);

        int index = 0;
        for(Object o : list) {
            index++;

            System.out.println(index + ". " + o.toString());
        }
        System.out.printf("%n0. Cancel%n");
    }

    static public void showListWithoutCancel(List list, String header) {
        System.out.println(header);

        int index = 0;
        for(Object o : list) {
            index++;

            System.out.println(index + ". " + o.toString());
        }
    }


    static public Object selectsObject(List list) {
        String input;
        Integer value;
        do {
            input = Utils.readLineFromConsole("Type your option: ");
            value = Integer.valueOf(input);
        } while(value < 0 || value > list.size());

        if(value == 0) {
            return null;
        } else {
            return list.get(value - 1);
        }
    }

    static public int selectsIndex(List list) {
        String input;
        Integer value;
        do {
            input = Utils.readLineFromConsole("Type your option: ");
            try {
                value = Integer.valueOf(input);
            }
            catch(NumberFormatException e) {
                showText("Please insert a valid option");
                value = -1;
            }
        } while(value < 0 || value > list.size());

        return value - 1;
    }

    static public int selectsIndexWithoutCancel(List list) {
        String input;
        Integer value;
        do {
            input = Utils.readLineFromConsole("Type your option: ");
            try {
                value = Integer.valueOf(input);
            }
            catch(NumberFormatException e) {
                showText("Please insert a valid option");
                value = -1;
            }
        } while(value <= 0 || value > list.size());

        return value - 1;
    }

    public static void showText(String text) {
        System.out.println(text);
    }


}
