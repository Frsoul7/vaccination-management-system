package app.domain.serializationFiles;

import app.controller.SerializationController;
import app.domain.model.store.EmployeeStore;
import app.ui.console.utils.Utils;

import java.io.*;

public class FileReadAndSave {


    public FileReadAndSave() {
    }

    public <T> T readFile(String fileName) {
        T store = null;
        try {
            File file = new File(fileName);
            if(file.exists()) {
                ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
                try {
                    store = (T)in.readObject();
                }
                finally {
                    in.close();
                }
            } else {
                Utils.showText(String.format("\nFile %s doesn't exist", getFileName(fileName)));
            }
            return store;
        }
        catch(IOException|ClassNotFoundException ex) {
            throw new RuntimeException(String.format("\nFail read the file %s", getFileName(fileName)));
        }
    }

    public <T> boolean saveFile(String fileName, T store) {
        try {
            File file = new File(fileName);
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
            try {
                out.writeObject(store);
            }
            finally {
                out.close();
            }
            return true;
        }
        catch(IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public String getFileName(String path) {
        String[] aux = path.split("\\\\");
        return aux[aux.length - 1];
    }
}
