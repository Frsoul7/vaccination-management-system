package app.domain.model.utils;

import app.domain.algorithms.BruteForce;
import app.interfaces.BruteForceAlg;
import app.interfaces.Constants;
import app.interfaces.Reader;
import app.interfaces.SortAlg;

import java.io.*;
import java.util.Properties;

public abstract class Configurations implements Constants {


    public static Reader getReader()
            throws FileNotFoundException, IOException, ClassNotFoundException, InstantiationException,
                   IllegalAccessException {
        Properties properties;
        InputStream in = new FileInputStream(new File(PARAMS_FILENAME));
        properties = new Properties();
        properties.load(in);
        String className = properties.getProperty("fileReader");
        Reader cl;
        cl = (Reader)Class.forName(className).newInstance();
        return cl;
    }

    public static String getDelimiter()
            throws FileNotFoundException, IOException, ClassNotFoundException, InstantiationException,
                   IllegalAccessException {
        Properties properties;
        InputStream in = new FileInputStream(new File(PARAMS_FILENAME));
        properties = new Properties();
        properties.load(in);
        return properties.getProperty("delimiter");
    }

    public static int getNumberOfAttributes()
            throws FileNotFoundException, IOException, ClassNotFoundException, InstantiationException,
                   IllegalAccessException {
        Properties properties;
        InputStream in = new FileInputStream(new File(PARAMS_FILENAME));
        properties = new Properties();
        properties.load(in);
        return Integer.parseInt(properties.getProperty("numberOfAttributesLegacyData"));
    }

    public static String getHeader()
            throws FileNotFoundException, IOException, ClassNotFoundException, InstantiationException,
                   IllegalAccessException {
        Properties properties;
        InputStream in = new FileInputStream(new File(PARAMS_FILENAME));
        properties = new Properties();
        properties.load(in);
        return properties.getProperty("headerLegacyData");
    }

    public static String getSplitDataTimeInfo()
            throws FileNotFoundException, IOException, ClassNotFoundException, InstantiationException,
                   IllegalAccessException {
        Properties properties;
        InputStream in = new FileInputStream(new File(PARAMS_FILENAME));
        properties = new Properties();
        properties.load(in);
        return properties.getProperty("splitDataTimeColumn");
    }

    public static SortAlg getSortAlg()
            throws FileNotFoundException, IOException, ClassNotFoundException, InstantiationException,
                   IllegalAccessException {

        Properties properties;
        InputStream in = new FileInputStream(new File(PARAMS_FILENAME));
        properties = new Properties();
        properties.load(in);
        String className = properties.getProperty("sortAlg");
        SortAlg cl;
        cl = (SortAlg)Class.forName(className).newInstance();
        return cl;
    }

    public static BruteForceAlg getBrutAlg()
            throws FileNotFoundException, IOException, ClassNotFoundException, InstantiationException,
                   IllegalAccessException {

        Properties properties;
        InputStream in = new FileInputStream(new File(PARAMS_FILENAME));
        properties = new Properties();
        properties.load(in);
        String className = properties.getProperty("brutAlg");
        BruteForceAlg cl;
        cl = (BruteForceAlg)Class.forName(className).newInstance();
        return cl;
    }

    public static String getTaskRunPeopleVaccinated()
            throws FileNotFoundException, IOException, ClassNotFoundException, InstantiationException,
                   IllegalAccessException {
        Properties properties;
        InputStream in = new FileInputStream(new File(PARAMS_FILENAME));
        properties = new Properties();
        properties.load(in);
        return properties.getProperty("TaskRun.PeopleVaccinated");
    }

}
