package Utils;

import java.io.*;
import java.util.Properties;

public class LoadData {

    //methode to load the data from the file "config.properties"
    public static Properties userData = loadProperties(System.getProperty("user.dir") +
            "\\src\\test\\resources\\config.properties");


    private static Properties loadProperties(String path) {
        Properties pro = new Properties();
        FileInputStream stream;

        try {
            stream = new FileInputStream(path);
            pro.load(stream);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return pro;
    }

//    private static Properties loadProperties(String path) {
//        Properties pro = new Properties();
//        FileInputStream stream;
//
//        try {
//            stream = new FileInputStream(path);
//            pro.load(stream);
//        } catch (FileNotFoundException e) {
//            System.out.println(e.getMessage());
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
//        return pro;
//    }

}
