package app.interfaces;

import java.util.List;

public interface Reader {

    public List<String> readFile(String filePath);

    public String[] splitData(String data, String splitDataTimeColumn, String delimiter);
}
