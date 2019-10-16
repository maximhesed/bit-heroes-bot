import java.awt.Robot;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.PrintWriter;
import javax.swing.Timer;

final class Bot extends Auxiliary
{
    static PrintWriter logWriter;

    // fishing stuff
    static Pixel button_trade;
    static Pixel button_got_away;
    static Point button_fishing;
    static Pixel button_play;
    static Pixel button_baits;
    static Pixel zone_green;
    static Pixel frame_common;
    static Pixel frame_rare;
    static Pixel frame_epic;
    static Pixel frame_legendary;
    static Pixel frame_unicorn;
    static Point button_start;

    // quests stuff
    static Point button_quests;

    // raids stuff
    static Point button_raid;
    static Point button_summon;

    // ads stuff
    static Pixel button_watch;
    static Pixel button_watch_decline;
    //static Pixel button_watch_lobby;
    static Pixel button_watch_lobby;
    static Pixel button_watch_lobby_start;
    static Point button_watch_lobby_skip;

    // pvp stuff
    static Point button_pvp;
    static Point button_pvp_fight;
    static Pixel button_pvp_fight_close;

    // expeditions stuff
    static Pixel button_expedition;
    static Point button_expedition_enter;

    // trials stuff
    static Pixel button_trial;
    static Pixel zone_trial_close;

    // another and common stuff
    static Pixel button_defeat;
    static Point button_play_another;
    static Pixel button_craft;
    static Pixel button_normal;
    static Pixel button_hard;
    static Pixel button_heroic;
    static Pixel button_yes;
    static Pixel button_no;
    static Pixel button_persuade;
    static Point button_cinematic;
    static Point button_ad_close;
    static Pixel zone_treasure;
    static Pixel zone_treasure_no_keys;
    static Pixel button_team_auto;
    static Pixel button_team_clear;
    static Point button_bounties;
    static Pixel button_loot;

    private static int persuades = 0;
    private static int ads = 0;
    private static int treasures = 0;
    private static int dungeons = 0;
    private static int raids = 0;
    private static int pvps = 0;
    private static int trials = 0;
    private static int expeditions = 0;
    private static int fish = 0;

    private static int walkDuration = 3000;
    private static boolean isGantlet;
    private static boolean isWalk;

    static void defineStuff()
    {
        button_trade = new Pixel(895, 685, 163, 209, 47);
        button_got_away = new Pixel(902, 596, 51, 179, 211);
        button_fishing = new Point(1316, 649);
        button_play = new Pixel(877, 537, 51, 179, 211);
        button_baits = new Pixel(1022, 632, 26, 24, 19);
        zone_green = new Pixel(1258, 634, 77, 254, 0);
        frame_common = new Pixel(1010, 453, 126, 255, 126);
        frame_rare = new Pixel(1010, 453, 126, 135, 244);
        frame_epic = new Pixel(1010, 453, 255, 129, 126);
        frame_legendary = new Pixel(1010, 453, 255, 255, 126);
        frame_unicorn = new Pixel(1010, 453, 239, 0, 54);
        button_start = new Point(887, 727);

        button_quests = new Point(589, 276);

        button_raid = new Point(590, 567);
        button_summon = new Point(1094, 620);

        button_watch = new Pixel(827, 614, 51, 179, 211);
        button_watch_decline = new Pixel(968, 612, 244, 143, 61);

        /* Watch button changes it's location, depending on the
         * expedition availability (in the game self). */
        if (!Options.checkExpeditions)
            button_watch_lobby = new Pixel(1307, 502, 76, 61, 21);
        else
            button_watch_lobby = new Pixel(1307, 430, 76, 61, 21);

        button_watch_lobby_start = new Pixel(884, 505, 95, 214, 240);
        button_watch_lobby_skip = new Point(953, 670);

        button_pvp = new Point(589, 349);
        button_pvp_fight = new Point(1154, 444);
        button_pvp_fight_close = new Pixel(951, 676, 160, 209, 46);

        button_expedition = new Pixel(1314, 500);
        button_expedition_enter = new Point(950, 697);

        button_trial = new Pixel(1311, 577, 231, 45, 85);
        zone_trial_close = new Pixel(760, 425, 197, 145, 0);

        button_defeat = new Pixel(906, 595, 47, 175, 209);
        button_play_another = new Point(1102, 514);
        button_craft = new Pixel(598, 415, 199, 136, 48);
        button_normal = new Pixel(706, 476, 51, 179, 211);
        button_hard = new Pixel(904, 475, 165, 211, 51);
        button_heroic = new Pixel(1089, 475, 244, 144, 63);
        button_yes = new Pixel(836, 596, 165, 211, 51);
        button_no = new Pixel(996, 596, 51, 179, 211);
        button_persuade = new Pixel(731, 565, 46, 174, 209);
        button_cinematic = new Point(448, 442);
        button_ad_close = new Point(1284, 158);
        zone_treasure = new Pixel(800, 491, 255, 0, 0);
        zone_treasure_no_keys = new Pixel(1046, 573, 254, 0, 0);
        button_team_auto = new Pixel(759, 699, 51, 179, 211);
        button_team_clear = new Pixel(875, 696, 244, 143, 61);
        button_bounties = new Point(680, 682);
        button_loot = new Pixel(1050, 494, 165, 211, 52);
    }

    // anti kick
    static void walkCircle() throws Exception
    {
        ActionListener walkPerformer = new ActionListener()
        {
            int turns = 0;

            public void actionPerformed(ActionEvent event)
            {
                try {
                    switch (turns) {
                    case 0:
                        pressKey(0, KeyEvent.VK_LEFT, 0, 250);

                        break;
                    case 1:
                        pressKey(0, KeyEvent.VK_UP, 0, 250);

                        break;
                    case 2:
                        pressKey(0, KeyEvent.VK_RIGHT, 0, 250);

                        break;
                    case 3:
                        pressKey(0, KeyEvent.VK_DOWN, 2000, 250);

                        // debug
                        System.out.printf("break\n");

                        Bot.isWalk = false;

                        break;
                    }
                } catch (Exception ex) {
                    System.err.printf("%s\n", ex.getMessage());
                }

                turns += 1;
            }
        };

        Timer timer = new Timer(walkDuration, walkPerformer);

        // debug
        System.out.printf("In walkCircle()... ");

        Bot.isWalk = true;

        timer.setRepeats(true);
        timer.start();

        while (Bot.isWalk) {
            Thread.sleep(500);

            closeWindows();
        }

        timer.stop();
    }

    static void initPassage()
    {
        logWriter.printf("--- Passage has been started ---\n\n");
    }

    static void countTotal()
    {
        logWriter.printf("\n");
        logWriter.printf("--- Passage has been finished ---\n\n");
        logWriter.printf("Total proceeds:\n");

        if (Options.checkDungeons)
            logWriter.printf("# dungeons: %d\n", dungeons);

        if (Options.checkRaids)
            logWriter.printf("# raids: %d\n", raids);

        if (Options.checkPvps)
            logWriter.printf("# PvPs: %d\n", pvps);

        if (Options.checkTrials)
            logWriter.printf("# trials: %d\n", trials);

        if (Options.checkExpeditions)
            logWriter.printf("# expeditions: %d\n", expeditions);

        if (Options.checkFish)
            logWriter.printf("# fish: %d\n", fish);

        logWriter.printf("-\n");
        logWriter.printf("# persuades: %d\n", persuades);
        logWriter.printf("# ads: %d\n", ads);
        logWriter.printf("# treasures: %d\n", treasures);
        logWriter.printf("\n");
    }

    static void accept() throws Exception
    {
        pressKey(0, KeyEvent.VK_ENTER, 1000, 0);
        pressKey(0, KeyEvent.VK_ENTER, 1000, 0);
    }

    static void decline() throws Exception
    {
        pressKey(0, KeyEvent.VK_ESCAPE, 1000, 0);
        pressKey(0, KeyEvent.VK_ENTER, 1000, 0);
    }

    static boolean checkDefeat() throws Exception
    {
        if (compareColors(button_defeat.getPoint(),
                button_defeat.getColor())) {
            pressKey(0, KeyEvent.VK_ENTER, 1000, 0);

            logWriter.printf("defeat.\n");

            return true;
        }

        return false;
    }

    static void check() throws Exception
    {
        // debug
        System.out.printf("In check()... ");

        while (true) {
            Thread.sleep(500);

            // TODO: User disabled ads in the game settings.
            // check for ad
            if (compareColors(button_watch.getPoint(),
                    button_watch.getColor())) {
                click(1000, button_watch.getPoint(), 3000);

                if (compareColors(button_watch_decline.getPoint(),
                        button_watch_decline.getColor())) {
                    decline();

                    ads -= 1;
                } else {
                    Thread.sleep(30000);

                    collectWatch();

                    ads += 1;
                }
            }

            // TODO: User disabled treasures in the game settings.
            // check for treasure
            if (compareColors(zone_treasure.getPoint(),
                    zone_treasure.getColor())) {
                if (!compareColors(zone_treasure_no_keys.getPoint(),
                        zone_treasure_no_keys.getColor())) {
                    accept();

                    treasures += 1;
                } else {
                    decline();

                    treasures -= 1;
                }
            }

            // check for capture
            if (compareColors(button_persuade.getPoint(),
                    button_persuade.getColor())) {
                if (!Options.declineCaptures) {
                    click(0, button_persuade.getPoint(), 750);
                    pressKey(0, KeyEvent.VK_ENTER, 1000, 0);

                    persuades += 1;
                } else {
                    decline();

                    persuades -= 1;
                }
            }

            // check if adventure has been finished
            if (compareColors(button_yes.getPoint(),
                    button_yes.getColor()) ||
                    compareColors(button_no.getPoint(),
                    button_no.getColor())) {
                pressKey(0, KeyEvent.VK_ENTER, 1000, 0);

                logWriter.printf("victory.\n");

                break;
            }

            if (checkDefeat())
                break;
        }

        // debug
        System.out.printf("break.\n");
    }

    static boolean checkEnough() throws Exception
    {
        if (compareColors(button_no.getPoint(), button_no.getColor())) {
            pressKey(0, KeyEvent.VK_ESCAPE, 750, 0);

            return true;
        }

        return false;
    }

    // check for ad
    static void checkAd() throws Exception
    {
        if (compareColors(button_watch_lobby.getPoint(),
                button_watch_lobby.getColor())) {
            logWriter.printf("Ad found in lobby.\n");

            click(0, button_watch_lobby.getPoint(), 1500);
            click(0, button_watch_lobby_start.getPoint(), 3000);

            if (compareColors(button_watch_lobby_start.getPoint(),
                    button_watch_lobby_start.getColor())) {
                click(0, button_watch_lobby_skip, 1000);
                pressKey(0, KeyEvent.VK_ENTER, 1000, 0);
            } else {
                Thread.sleep(30000);

                collectWatch();

                closeWindows();
            }
        }
    }

    static boolean checkLobby() throws Exception
    {
        if (compareColors(button_craft.getPoint(),
                button_craft.getColor()))
            return true;

        return false;
    }

    static void closeWindows() throws Exception
    {
        while (!checkLobby()) {
            /* Set focus on the game?.. */
            click(500, button_craft.getPoint(), 500);
            pressKey(0, KeyEvent.VK_ESCAPE, 1000, 0);
        }
    }

    static void collectWatch() throws Exception
    {
        click(0, button_ad_close, 1500);
        click(0, button_ad_close, 1500);
        click(0, button_cinematic, 250);
        click(0, button_craft.getPoint(), 1000);
    }

    static void collectBounties() throws Exception
    {
        click(0, button_bounties, 1000);

        while (compareColors(button_loot.getPoint(),
                button_loot.getColor())) {
            click(0, button_loot.getPoint(), 1000);
            move(new Point(button_loot.getX() - 50, button_loot.getY()));
            pressKey(0, KeyEvent.VK_ENTER, 1000, 0);
        }

        pressKey(0, KeyEvent.VK_ESCAPE, 1000, 0);
    }

    // check fish availability
    static void checkFish() throws Exception
    {
        int i;

        click(0, button_fishing, 1500);

        /* I'm not sure, that it's right. */
        while (!compareColors(button_play.getPoint(),
                button_play.getColor()))
            closeWindows();

        click(0, button_play.getPoint(), 15000);

        logWriter.printf("Starting fishing...\n");

        while (true) {
            if (!compareColors(button_baits.getPoint(),
                    button_baits.getColor())) {
                // start fishing
                click(0, button_start, 695);

                /* TODO: Stability of a garbage caught checker directly
                 * depends on post-cast delay. Don't know yet how to
                 * rewrite this better. However it, perhaps, worth
                 * doing as soon as possible... */
                click(0, button_start, 6500);

                // check if garbage caught
                if (compareColors(button_trade.getPoint(),
                        button_trade.getColor())) {
                    accept();

                    continue;
                }

                /* By the way, here is observed by-effect the delay above:
                 * catching starts not right away, that is a serious
                 * defect. */
                /* TODO: Need to find a way, that will allow send key
                 * event instantly or search another possibility catching
                 * a fish only with 100% chance. I think, that is
                 * feasible. */
                while (true) {
                    if (compareColors(zone_green.getPoint(),
                            zone_green.getColor())) {
                        pressKey(0, KeyEvent.VK_SPACE, 0, 0);

                        break;
                    }
                }

                Thread.sleep(5500);

                // either fish got away...
                if (compareColors(button_got_away.getPoint(),
                        button_got_away.getColor()))
                    pressKey(0, KeyEvent.VK_ENTER, 1000, 0);
                else {
                    if (compareColors(frame_common.getPoint(),
                            frame_common.getColor()))
                        logWriter.printf("Caught common fish.\n");
                    else if (compareColors(frame_rare.getPoint(),
                            frame_rare.getColor()))
                        logWriter.printf("Caught rare fish.\n");
                    else if (compareColors(frame_epic.getPoint(),
                            frame_epic.getColor()))
                        logWriter.printf("Caught epic fish.\n");
                    else if (compareColors(frame_legendary.getPoint(),
                            frame_legendary.getColor()))
                        logWriter.printf("Caught legendary fish!\n");
                    else if (compareColors(frame_unicorn.getPoint(),
                            frame_unicorn.getColor()))
                        logWriter.printf("Caught unicorn fish!!!\n");

                    fish += 1;

                    // ...or trade it
                    accept();
                }
            } else {
                logWriter.printf("Not enough baits.\n");

                pressKey(0, KeyEvent.VK_ESCAPE, 1000, 0);

                break;
            }
        }

        closeWindows();
    }

    static void chooseDifficult(int difficult) throws Exception
    {
        switch (difficult) {
        case 0:
            click(0, button_normal.getPoint(), 1000);

            break;
        case 1:
            click(0, button_hard.getPoint(), 1000);

            break;
        case 2:
            click(0, button_heroic.getPoint(), 1000);

            break;
        default:
            click(0, button_heroic.getPoint(), 1000);

            break;
        }
    }

    static void changeTeam(boolean solo, boolean auto) throws Exception
    {
        if (solo) {
            click(0, button_team_clear.getPoint(), 1000);
            accept();
        } else if (auto)
            click(0, button_team_auto.getPoint(), 1000);
    }

    // check dungeon availability
    static void checkDungeon(Point dungeon) throws Exception
    {
        click(0, button_quests, 2000);
        click(0, dungeon, 1000);

        chooseDifficult(Options.difficultDungeon);
        changeTeam(Options.noTeam, Options.autoTeam);

        pressKey(0, KeyEvent.VK_ENTER, 1000, 0);

        if (!checkEnough()) {
            logWriter.printf("Starting dungeon... ");

            dungeons += 1;

            check();
        }

        closeWindows();
    }

    // check raid availability
    static void checkRaid() throws Exception
    {
        click(0, button_raid, 2000);
        click(0, button_summon, 1000);

        chooseDifficult(Options.difficultRaid);
        changeTeam(false, false);

        pressKey(0, KeyEvent.VK_ENTER, 1000, 0);

        if (!checkEnough()) {
            logWriter.printf("Starting raid... ");

            raids += 1;

            check();
        }

        closeWindows();
    }

    // check pvp availability
    static void checkPvp() throws Exception
    {
        click(0, button_pvp, 2000);
        click(0, button_play_another, 1000);

        if (!checkEnough()) {
            logWriter.printf("Starting PvP... ");

            pvps += 1;

            click(10000, button_pvp_fight, 1000);

            changeTeam(false, false);

            pressKey(0, KeyEvent.VK_ENTER, 1000, 0);

            while (true) {
                // check for victory
                if (compareColors(button_pvp_fight_close.getPoint(),
                        button_pvp_fight_close.getColor())) {
                    click(0, button_pvp_fight_close.getPoint(), 2000);

                    logWriter.printf("victory.\n");

                    break;
                }

                if (checkDefeat())
                    break;

                Thread.sleep(500);
            }
        }

        closeWindows();
    }

    // check trial/gauntlet availability
    static void checkTrial() throws Exception
    {
        if (compareColors(button_trial.getPoint(),
                button_trial.getColor()))
            isGantlet = true;
        else
            isGantlet = false;

        click(0, button_trial.getPoint(), 2000);
        click(0, button_play_another, 1500);

        if (!checkEnough()) {
            trials += 1;

            changeTeam(false, false);

            pressKey(0, KeyEvent.VK_ENTER, 1000, 0);

            if (isGantlet) {
                logWriter.printf("Starting gauntlet... ");

                while (true) {
                    // check for end of the battle
                    if (compareColors(zone_trial_close.getPoint(),
                            zone_trial_close.getColor())) {
                        pressKey(0, KeyEvent.VK_ENTER, 2000, 0);

                        logWriter.printf("finished.\n");

                        break;
                    }

                    Thread.sleep(500);
                }
            } else {
                logWriter.printf("Starting trial... ");

                check();
            }
        }

        closeWindows();
    }

    // check expedition availability
    static void checkExpedition(Point bard) throws Exception
    {
        click(0, button_expedition.getPoint(), 2000);
        click(0, button_play_another, 1500);

        if (!checkEnough()) {
            logWriter.printf("Starting expedition... ");

            expeditions += 1;

            click(0, bard, 1000);
            click(0, button_expedition_enter, 1000);

            changeTeam(false, false);

            pressKey(0, KeyEvent.VK_ENTER, 1000, 0);

            check();
        }

        closeWindows();
    }

    // FIXME: Update button_expedition.
    static boolean checkExpeditionAccessibility() throws Exception
    {
        if (compareColors(button_expedition.getPoint(),
                button_expedition.getColor()))
            return true;

        return false;
    }
}
