package app.domain.model.utils;

import app.interfaces.Reader;
import app.ui.console.utils.Utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReaderWithHeader implements Reader {

    public List<String> readFile(String filePath) {
        List<String> legacyData = new ArrayList<>();

        File file = new File(filePath);
        try {
            Scanner sc = new Scanner(file);
            String header = sc.nextLine();

            if(!header.equals(Configurations.getHeader())) {
                return null;
            }
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                legacyData.add(line);
            }
            sc.close();
        }
        catch(IOException|ClassNotFoundException|IllegalAccessException|InstantiationException e) {
            Utils.showText("Error reading legacy data");
            return null;
        }
        return legacyData;
    }

    public String[] splitData(String data, String splitDataTimeColumn, String delimiter) {

        String[] dataSplitted = data.split(delimiter);
        String[] dataAux;
        if(splitDataTimeColumn.equals("yes")) {
            dataAux = new String[dataSplitted.length + 4];
            int pos = 0;
            String[] aux;
            for(int i = 0; i < dataSplitted.length; i++) {
                if(i <= 3) {
                    dataAux[i] = dataSplitted[i].trim();
                } else {
                    aux = dataSplitted[i].split(" ");
                    dataAux[i + pos] = aux[0].trim();
                    pos++;
                    dataAux[i + pos] = aux[1].trim();
                }

            }

        } else {
            //to be implemented
            dataAux = null;
        }
        return dataAux;
    }
}
