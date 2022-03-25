package ru.job4j.autosale.store;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SourcePath {
   private static String pathToImages;
   private static String pathToImageSub;

    private SourcePath() {
        Properties properties = new Properties();
        InputStream in = SourcePath.class.getClassLoader()
                .getResourceAsStream("Resource.properties");
        try {
            properties.load((in));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.pathToImages = properties.getProperty("pathToDir");
        this.pathToImageSub = properties.getProperty("pathtoImages");
    }

    public static String giveSub() {
        new SourcePath();
        return pathToImageSub;
    }

    public static String give() {
        new SourcePath();
        return pathToImages;
    }
}
