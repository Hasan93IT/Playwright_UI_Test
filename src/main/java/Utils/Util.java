package Utils;


import java.util.logging.Level;
import java.util.logging.Logger;



public class Util {

    //to print the info on the console
    public static void logger(Level level, String message) {
        Logger logger = Logger.getAnonymousLogger();
        logger.log(level, message);
    }
}
