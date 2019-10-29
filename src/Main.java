import java.awt.Robot;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

final class Main
{
    public static void main(String[] args) throws Exception
    {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        final String logDirPath = "logs/";
        File logDir = new File(logDirPath);
        File logFile;

        // create logs directory if doesn't exists
        if (!logDir.exists()) {
            if (!logDir.mkdir())
                System.exit(-1);
        }

        // create log file
        logFile = new File(logDirPath + dateFormat.format(date) + ".log");

        // parse options
        Options.init(args);

        if (Options.help)
            Options.showHelp();

        if (Options.hell)
            Options.showHell();

        Bot.checkSigint();
        Bot.init(logFile);
    }
}
