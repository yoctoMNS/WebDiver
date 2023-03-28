package jp.xdomain.html.yoctomns.util;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggingUtil {
    private static final String LOG_FILE_DIRECTORY = "/log/system/system.log";
    private static FileHandler fileHandler = null;
    private static Logger logger = Logger.getLogger(LoggingUtil.class.getName());

    static {
        try {
            fileHandler = new FileHandler(LoggingUtil.class.getResource(LOG_FILE_DIRECTORY).getPath(), true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setUseParentHandlers(false);
            logger.setLevel(Level.ALL);
        } catch (IOException e) {
            System.err.println("A file read or write error has occurred.");
            close();
        }
    }

    public static void configPrint(String str) {
        logger.log(Level.CONFIG, String.format("(%s)\t%s", LocalDateTime.now(), str));
    }

    public static void infoPrint(String str) {
        logger.log(Level.INFO, String.format("(%s)\t%s", LocalDateTime.now(), str));
    }

    public static void warningPrint(String str) {
        logger.log(Level.WARNING, String.format("(%s)\t%s", LocalDateTime.now(), str));
    }

    public static void severePrint(String str, Exception e) {
        logger.log(Level.SEVERE, String.format("(%s)\t%s", LocalDateTime.now(), str), e);
    }

    public static void severePrint(String str, Error e) {
        logger.log(Level.SEVERE, String.format("(%s)\t%s", LocalDateTime.now(), str), e);
    }

    public static void close() {
        fileHandler.close();
    }
}
