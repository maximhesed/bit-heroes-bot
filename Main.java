import java.awt.AWTException;
import java.awt.Robot;
import java.awt.PointerInfo;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.InputEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.Timer;

final class Main
{
    static int successfulWaited_tranzistor = 0;
    static Color color = new Color(0, 0, 0); // it's more comfortable for me
    static int ms = 0;

    private static void Click(Robot r, Point p, int time)
    {
        try {
            r.mouseMove(p.x, p.y);
            r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

            Thread.sleep(time);
        } catch (InterruptedException exception) {
            System.err.println(exception.getMessage());
        }
    }

    public static void main(String[] args) throws Exception
    {
        final Point button_start = new Point(770, 770);
        final Point button_trade = new Point(745, 734);
        final Point button_close = new Point(990, 438);
        final Point button_got_away = new Point(770, 654);
        final Point bar_distance = new Point(770, 680);
        final Point best_percentage = new Point(1108, 681);
        final Point frame_garbage = new Point(745, 535);
        final Color best_percentage_color = new Color(77, 254, 0);
        final Color button_trade_color = new Color(165, 211, 51);

        Robot bot = new Robot();
        int baits;
        int i;

        if (args.length == 1)
            baits = Integer.parseInt(args[0]);
        else
            return;

        // wait a bit, while user open game window
        Thread.sleep(1500);

        Timer timer = new Timer(1, new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                color = bot.getPixelColor(best_percentage.x, best_percentage.y);
                if (color.getGreen() == best_percentage_color.getGreen()) {
                    ms += 1;

                    //System.out.printf("TIMER: ms = %d\n", ms);

                    if (ms > 0) {
                        Click(bot, button_start, 0);

                        successfulWaited_tranzistor = 1;
                    }
                } else {
                    //System.out.printf("TIMER: Colors changed!\n", ms);

                    successfulWaited_tranzistor = 2;
                }

                //System.out.printf("TIMER: I'm working...\n", ms);
            }
        });

        for (i = 0; i < baits; i++) {
            boolean isGotAway = false;

            successfulWaited_tranzistor = 0;
            ms = 0;

            // TODO: check user for baits availability

            // start fishing
            Click(bot, button_start, 0);

            /**
             * In theory was excepted, that distance always is will be max.
             * Of course not. But it makes you less perceptible. :)
             */
            Thread.sleep(744);

            Click(bot, button_start, 7500);

            // if caught garbage
            color = bot.getPixelColor(frame_garbage.x, frame_garbage.y);

            //System.out.printf("r: %d; g: %d; b: %d\n",
            //    color.getRed(), color.getGreen(), color.getBlue());

            if (color.getRed() > 200 && color.getGreen() > 200 && color.getBlue() > 200) {
                Click(bot, button_trade, 500);
                Click(bot, button_close, 500);

                continue;
            }

catching:
            while (true) {
                color = bot.getPixelColor(best_percentage.x, best_percentage.y);
                if (color.getGreen() == best_percentage_color.getGreen()) {
                    timer.start();

                    // wait end of timer
                    while (true) {
                        if (successfulWaited_tranzistor == 1) {
                            System.out.printf("Fish was catched with best rate. Break.\n");

                            timer.stop();

                            break catching;
                        } else if (successfulWaited_tranzistor == 2) {
                            System.out.printf("Rate was slipped. Continue.\n");

                            timer.stop();

                            Thread.sleep(250);

                            // check if fish got away
                            color = bot.getPixelColor(button_start.x, button_start.y);
                            if (color.getRed() < 20 &&
                                color.getGreen() < 20 &&
                                color.getBlue() < 20) {

                                Click(bot, button_got_away, 500);

                                isGotAway = true;

                                break catching;
                            }

                            continue catching;
                        } else
                            System.out.printf("Timer is working...\n");
                    }
                }

                //Thread.sleep(25);
            }

            if (isGotAway)
                continue;

            Thread.sleep(5000);

            // or fish got away
            Click(bot, button_got_away, 500);

            // either trade it
            Click(bot, button_trade, 500);
            Click(bot, button_close, 500);
        }
    }
}
