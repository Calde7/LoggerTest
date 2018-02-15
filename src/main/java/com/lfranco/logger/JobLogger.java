package com.lfranco.logger;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JobLogger {

    private static boolean logToFile;
    private static boolean logToConsole;
    private static boolean logToDatabase;
    private static Map dbParams;
    private static Logger logger;

    public JobLogger(boolean logToFileParam, boolean logToConsoleParam, boolean logToDatabaseParam, Map dbParamsMap) {
        logger = Logger.getLogger("MyLog");
        logToDatabase = logToDatabaseParam;
        logToFile = logToFileParam;
        logToConsole = logToConsoleParam;
        dbParams = dbParamsMap;
    }

    public static void LogMessage(String messageText, int type) throws Exception {
        messageText.trim();
        if (messageText == null || messageText.length() == 0) {
            return;
        }
        if (!logToConsole && !logToFile && !logToDatabase) {
            throw new Exception("Invalid configuration");
        }
        //ConsoleHandler ch = new ConsoleHandler();
        if (logToFile) {
            writeLogFile(type, messageText);
        }
        if (logToConsole) {
            //logger.addHandler(ch);
            //logger.log(Level.INFO, messageText);
            printConsole(type, messageText);
        }
        if (logToDatabase) {
            saveLogDb(type, messageText);
        }
    }

    public static boolean writeLogFile(int type, String msg) {
        try {
            File logFile = new File(dbParams.get("logFileFolder") + "/logFile.txt");
            if (!logFile.exists()) {
                logFile.createNewFile();

            }
            FileHandler fh = new FileHandler(dbParams.get("logFileFolder") + "/logFile.txt");
            logger.addHandler(fh);
            switch (type) {
                case 1:
                    logger.log(Level.WARNING, msg);
                    break;
                case 2:
                    logger.log(Level.SEVERE, msg);
                    break;
                case 3:
                    logger.log(Level.WARNING, msg);
                    logger.log(Level.SEVERE, msg);
                    break;
            }
            return true;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            return false;
        }

    }

    public static boolean printConsole(int type, String msg) {
        //ConsoleHandler ch = new ConsoleHandler();
        //logger.addHandler(ch);
        try {
            switch (type) {
                case 1:
                    logger.log(Level.WARNING, msg);
                    break;
                case 2:
                    logger.log(Level.SEVERE, msg);
                    break;
                case 3:
                    logger.log(Level.WARNING, msg);
                    logger.log(Level.SEVERE, msg);
                    break;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean saveLogDb(int type, String msg) throws SQLException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + dbParams.get("serverName") + "/" + dbParams.get("database") + ""
                    + "?user=" + dbParams.get("userName") + "&password=" + dbParams.get("password") + "");
            Statement stmt = connection.createStatement();
            switch (type) {
                case 1:
                    stmt.executeUpdate("insert into LOGGER (type,message,date_of) values('WARNING', '" + msg + "',"
                            + "'" + Utils.convertDateCompleteToString(new Date()) + "')");
                    break;
                case 2:
                    stmt.executeUpdate("insert into LOGGER (type,message,date_of) values('SEVERE', '" + msg + "',"
                            + "'" + Utils.convertDateCompleteToString(new Date()) + "')");
                    break;
                case 3:
                    stmt.executeUpdate("insert into LOGGER (type,message,date_of) values('WARNING', '" + msg + "',"
                            + "'" + Utils.convertDateCompleteToString(new Date()) + "')");
                    stmt.executeUpdate("insert into LOGGER (type,message,date_of) values('SEVERE', '" + msg + "',"
                            + "'" + Utils.convertDateCompleteToString(new Date()) + "')");
                    break;
            }
            return true;
        } catch (Exception e) {
            e.getMessage();
            return false;
        } finally {
            connection.close();
        }

    }
}
