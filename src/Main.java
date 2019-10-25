import java.awt.Robot;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;

final class Main
{
    public static void main(String[] args) throws Exception
    {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String logDir = "logs";
        File logFile = new File(logDir);

        // create logs directory if doesn't exist
        if (!logFile.exists()) {
            if (!logFile.mkdir())
                System.exit(-1);
        }

        logFile = new File("logs/" + dateFormat.format(date) + ".log");

        // parse options
        try {
            JCommander.newBuilder()
                .addObject(new Options())
                .build()
                .parse(args);
        } catch (ParameterException ex) {
            Options.invalid();
        }

        if (Options.help) {
            Options.showHelp();

            System.exit(-1);
        }

        if (Options.hell) {
            Options.showHell();

            System.exit(-1);
        }

        Bot.init(logFile);
    }
}
