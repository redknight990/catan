package log;

import main.Engine;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings({"unused", "FieldCanBeLocal", "BooleanMethodIsAlwaysInverted"})
public class Logger {

    /**
     * General information during program runtime.
     */
    public static final int INFO = 0;

    /**
     * Debug flags or other unnecessary messages in production.
     */
    public static final int DEBUG = 1;

    /**
     * Generated warnings pertinent to the user.
     */
    public static final int WARN = 2;

    /**
     * Fatal program errors and crashes.
     */
    public static final int ERROR = 3;

    /**
     * Log prefixes.
     */
    private static final String[] LOG_PREFIXES = {
            "[$date] ",
            "[$date] DEBUG - ",
            "[$date] WARNING - ",
            "[$date] ERROR - "
    };

    /**
     * Number of characters to add to new lines.
     */
    private static final int[] NEWLINE_NUM_CHARS = {15, 23, 24, 23};

    /**
     * The date format of the logger.
     */
    private final SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss.SSS");

    /**
     * The default log file path.
     */
    private final String DEFAULT_LOG_FILEPATH = "./logs/" + new SimpleDateFormat("MMddyyyy-HHmmss").format(new Date())
            + ".log";

    /**
     * Written log file.
     */
    private final File log;

    /**
     * Writer stream writing to file.
     */
    private PrintWriter writer;

    /**
     * The logger's current logging level. Used as a shortcut to avoid writing the log level when writing multiple logs in a row. Always ensure you know the current log level and do not assume it will be set to a certain value.
     */
    private int logLevel = 0;

    /**
     * Indicates if the logger has been closed.
     */
    private boolean closed = false;

    /**
     * Creates a new logger object with the default file path.
     */
    public Logger() {
        log = new File(DEFAULT_LOG_FILEPATH);

        if (!createLogFile())
            Engine.error(new RuntimeException("Unable to create log file."));
    }

    /**
     * Creates a new logger object with the specified file path.
     *
     * @param logFilepath The path to the log file to be produced by this logger.
     */
    public Logger(String logFilepath) {
        log = new File(logFilepath);

        if (!createLogFile())
            throw new RuntimeException("Unable to create log file.");
    }

    /**
     * Creates the assigned log file for this logger.
     *
     * @return <b>boolean</b> True if creating the file and its parent directories
     * succeeds, false otherwise.
     */
    private boolean createLogFile() {
        try {

            // Make necessary directories
            if (!log.getParentFile().exists() && !log.getParentFile().mkdirs())
                return false;

            // Init print writer
            writer = new PrintWriter(new FileOutputStream(log));

            // Done!
            return true;

        } catch (Exception e) {

            // Error
            return false;
        }
    }

    /**
     * Logs the specified string to the console and to the log file with the specified logging level.
     *
     * @param logLevel A valid log level describing the nature of the message. (ex: {@link Logger#INFO},
     *                 {@link Logger#DEBUG}, {@link Logger#WARN}, {@link Logger#ERROR})
     * @param msg      The message to log.
     */
    public synchronized void log(int logLevel, String msg) {

        // Invalid log level or log closed.
        if (closed || logLevel < 0 || logLevel >= LOG_PREFIXES.length)
            return;

        // Log string
        String out = LOG_PREFIXES[logLevel].replace("$date", format.format(new Date())) + msg.replace("\n", "\n" + " ".repeat(NEWLINE_NUM_CHARS[logLevel]));
        writer.println(out);
        writer.flush();
        System.out.println(out);

    }

    /**
     * Logs the specified string to the console and to the log file using the current logging level. When
     *
     * @param msg The message to log.
     */
    public synchronized void log(String msg) {

        // Log closed.
        if (closed)
            return;

        // Log string
        String out = LOG_PREFIXES[logLevel].replace("$date", format.format(new Date())) + msg.replace("\n", "\n" + " ".repeat(NEWLINE_NUM_CHARS[logLevel]));
        writer.println(out);
        writer.flush();
        System.out.println(out);

    }

    /**
     * Sets the logger's current log level to the desired value. The log level is not set if the provided value is invalid.
     *
     * @param logLevel A valid log level ({@link #INFO}, {@link #DEBUG}, {@link #WARN}, {@link #ERROR}).
     */
    public void setLogLevel(int logLevel) {

        // Invalid log level
        if (logLevel < 0 || logLevel >= LOG_PREFIXES.length)
            return;

        this.logLevel = logLevel;

    }

    /**
     * Commits any pending log contents to the log file and closes the logger.
     */
    public void close() {

        // Log already closed.
        if (closed)
            return;

        writer.flush();
        writer.close();
        closed = true;
    }

}
