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
    private static final String logDir = "logs";

    private static DateFormat dateFormat;
    private static File logFile;
    private static Options options;
    private static Bot bot;

    public static void main(String[] args) throws Exception
    {
        Robot robot = new Robot();
        Date date = new Date();
        Point dungeon = new Point(0, 0);
        Point bard = new Point(0, 0);

        // create logs directory if doesn't exist
        logFile = new File(logDir);
        if (!logFile.exists())
            logFile.mkdir();

        dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        logFile = new File("logs/" + dateFormat.format(date) + ".log");
        options = new Options();
        bot = new Bot(robot, logFile, options);

        // parse options
        try {
            JCommander.newBuilder()
                .addObject(options)
                .build()
                .parse(args);
        } catch (ParameterException ex) {
            System.out.printf("Type --help for more information.\n");

            return;
        }

        if (options.help) {
            options.showHelp();

            return;
        }

        if (options.hell) {
            options.showHell();

            return;
        }

        // wait a bit, while user open game window
        Thread.sleep(1500);

        // declare all future stuff used
        bot.defineStuff();

        // get user preffered dungeon before start...
        if (options.checkDungeons) {
            dungeon = bot.record(3);

            bot.closeWindows();
        }

        Thread.sleep(10000);

        // ...and bard for expeditions
        if (options.checkExpeditions) {
            // check access to the expeditions
            if (bot.checkExpeditionAccessibility())
                bard = bot.record(3);
            else
                options.checkExpeditions = false;
        }

        // main loop
        while (true) {
            if (bot.checkLobby()) {
                bot.initPassage();

                if (options.checkAdsLobby)
                    bot.checkAd();

                if (options.checkDungeons)
                    bot.checkDungeon(dungeon);

                if (options.checkRaids)
                    bot.checkRaid();

                if (options.checkPvps)
                    bot.checkPvp();

                if (options.checkTrials)
                    bot.checkTrial();

                if (options.checkExpeditions)
                    bot.checkExpedition(bard);

                if (options.checkFish)
                    bot.checkFish();

                bot.countTotal();
            } else {
                bot.closeWindows();

                continue;
            }

            if (options.checkBounties)
                bot.collectBounties();

            bot.walkCircle();
        }
    }
}
