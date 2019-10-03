import java.awt.Robot;
import java.awt.Point;
import java.awt.Color;
import java.awt.event.InputEvent;

final class Main
{
    private static final Point button_start = new Point(887, 727);
    private static final Point button_trade = new Point(895, 685);
    private static final Point button_close = new Point(1141, 391);
    private static final Point button_got_away = new Point(902, 596);
    private static final Point bar_distance = new Point(770, 680);
    private static final Point best_percentage = new Point(1258, 634);
    private static final Point button_raid = new Point(590, 567);
    private static final Point button_summon = new Point(1094, 620);
    private static final Point button_heroic = new Point(1142, 481);
    private static final Point button_accept = new Point(1067, 706);
    private static final Point button_yes = new Point(836, 596);
    private static final Point button_watch = new Point(678, 676);
    private static final Point button_ad_close = new Point(1284, 158);
    private static final Point button_persuade = new Point(731, 565);
    private static final Point button_cinematic_open = new Point(448, 442);
    //private static final Point button_cinematic_close = new Point(1405, 219);
    private static final Point button_fishing = new Point(1316, 649);
    private static final Point button_play = new Point(874, 556);

    private static final Color best_percentage_color = new Color(77, 254, 0);
    private static final Color button_trade_color = new Color(163, 209, 47);
    private static final Color button_yes_color = new Color(165, 211, 51);
    private static final Color button_watch_color = new Color(51, 179, 211);
    private static final Color button_persuade_color = new Color(46, 174, 209);
    private static final Color button_got_away_color = new Color(51, 179, 211);

    private static Robot bot;

    private static void click(int btime, Point p, int atime) throws Exception
    {
        Thread.sleep(btime);

        bot.mouseMove(p.x, p.y);
        bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

        Thread.sleep(atime);
    }

    private static boolean compareColors(Point p, Color c)
    {
        Color color = bot.getPixelColor(p.x, p.y);
        if (color.getRed() == c.getRed() &&
            color.getGreen() == c.getGreen() &&
            color.getBlue() == c.getBlue())
            return true;

        return false;
    }

    private static void check() throws Exception
    {
        while (true) {
            Thread.sleep(500);

            // check if dungeon was finished
            if (compareColors(button_yes, button_yes_color)) {
                click(0, button_yes, 2500);

                break;
            }

            // check for ad
            if (compareColors(button_watch, button_watch_color)) {
                click(0, button_watch, 32500);
                click(0, button_ad_close, 500);
                click(0, button_ad_close, 750);
                click(0, button_cinematic_open, 0);
            }

            // check for treasure

            // check for capture
            if (compareColors(button_persuade, button_persuade_color)) {
                click(250, button_persuade, 750);
                click(0, button_yes, 0);
            }
        }
    }

    private static void startFishing(int baits) throws Exception
    {
        int i;

        click(0, button_fishing, 500);
        click(0, button_play, 6000);

        for (i = 0; i < baits; i++) {
            // TODO: check user for baits availability

            // start fishing
            click(0, button_start, 692);
            click(0, button_start, 7500);

            // if caught garbage
            if (compareColors(button_trade, button_trade_color)) {
                click(0, button_trade, 500);
                click(0, button_close, 500);

                continue;
            }

            while (true) {
                if (compareColors(best_percentage, best_percentage_color)) {
                    click(0, button_start, 0);

                    break;
                }
            }

            Thread.sleep(5000);

            // or fish got away
            if (compareColors(button_got_away, button_got_away_color))
                click(0, button_got_away, 500);
            else {
                // either trade it
                click(0, button_trade, 500);
                click(0, button_close, 500);
            }
        }
    }

    private static void startRaid(int shards) throws Exception
    {
        int i;

        for (i = 0; i < shards; i++) {
            click(0, button_raid, 750);
            click(0, button_summon, 750);
            click(0, button_heroic, 750);
            click(0, button_accept, 750);

            check();
        }
    }

    public static void main(String[] args) throws Exception
    {
        bot = new Robot();

        // TODO: parse options

        // wait a bit, while user open game window
        Thread.sleep(1500);

        // call anything
        //startFishing(Integer.parseInt(args[0]));
        startRaid(Integer.parseInt(args[0]));
    }
}
