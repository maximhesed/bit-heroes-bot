import java.awt.Robot;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
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
        Point dungeon = new Point(0, 0);
        Point bard = new Point(0, 0);

        // create logs directory if doesn't exist
        if (!logFile.exists()) {
            if (!logFile.mkdir())
                return;
        }

        logFile = new File("logs/" + dateFormat.format(date) + ".log");

        Bot.initRobot();
        Bot.logWriter = new PrintWriter(new OutputStreamWriter(
                new FileOutputStream(logFile), StandardCharsets.UTF_8), true);

        // parse options
        try {
            JCommander.newBuilder()
                .addObject(new Options())
                .build()
                .parse(args);
        } catch (ParameterException ex) {
            System.out.printf("Type --help for more information.\n");

            return;
        }

        if (Options.help) {
            Options.showHelp();

            return;
        }

        if (Options.hell) {
            Options.showHell();

            return;
        }

        // wait a bit, while user open game window
        Thread.sleep(1500);

        // declare all future stuff used
        Bot.defineStuff();

        // get user preffered dungeon before start...
        if (Options.checkDungeons) {
            Bot.click(0, Bot.button_quests, 1000);

            dungeon = Bot.record(3);

            Bot.closeWindows();
        }

        if (Options.checkExpeditions) {
            // debug
            System.out.printf("Check expeditions accessibility... ");

            // check access to the expeditions
            if (!Bot.checkExpeditionAccessibility()) {
                // debug
                System.out.printf("not available.\n");

                Options.checkExpeditions = false;
            } else
                // debug
                System.out.printf("available.\n");
        }

        Thread.sleep(10000);

        // ...and bard for expeditions
        if (Options.checkExpeditions) {
            Bot.click(0, Bot.button_expedition.getPoint(), 1000);
            Bot.click(0, Bot.button_expedition_play, 1000);

            bard = Bot.record(3);

            Bot.closeWindows();
        }

        // main loop
        while (true) {
            Bot.closeWindows();
            Bot.initPassage();

            if (Options.checkAdsLobby)
                Bot.checkAd();

            if (Options.checkDungeons)
                Bot.checkDungeon(dungeon);

            if (Options.checkRaids)
                Bot.checkRaid();

            if (Options.checkPvps)
                Bot.checkPvp();

            if (Options.checkTrials)
                Bot.checkTrial();

            if (Options.checkExpeditions)
                Bot.checkExpedition(bard);

            if (Options.checkFish)
                Bot.checkFish();

            Bot.countTotal();

            if (Options.checkBounties)
                Bot.collectBounties();

            Bot.walkCircle();
        }
    }
}
