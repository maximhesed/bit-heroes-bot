import java.awt.Robot;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.lang.Runtime;
import java.nio.charset.StandardCharsets;
import javax.swing.Timer;

final class Bot extends Auxiliary
{
    private static final int TRIAL = 0;
    private static final int GAUNTLET = 1;
    private static final int EXPEDITION = 0;
    private static final int INVASION = 1;
    private static final int GVG = 2;

    private static int dungeons = 0;
    private static int raids = 0;
    private static int pvps = 0;
    private static int trials = 0;
    private static int gauntlets = 0;
    private static int expeditions = 0;
    private static int invasions = 0;
    private static int gvgs = 0;
    private static int fish = 0;
    private static int persuades = 0;
    private static int ads = 0;
    private static int treasures = 0;

    private static boolean isWalk;

    static PrintWriter logWriter;

    // fish stuff
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

    // quest stuff
    static Point button_quests;

    // raid stuff
    static Point button_raid;
    static Point button_summon;

    // ad stuff
    static Pixel button_watch;
    static Pixel button_watch_decline;
    static Pixel button_watch_lobby;
    static Pixel button_watch_lobby_start;
    static Point button_watch_lobby_skip;

    // PvP stuff
    static Point button_pvp;
    static Point button_pvp_fight;
    static Pixel button_pvp_fight_close;

    // trial/gauntlet stuff
    static Pixel button_trial;
    static Pixel button_gauntlet;

    // expedition/invasion/GvG stuff
    static Pixel button_expedition;
    static Point button_expedition_play;
    static Point button_expedition_enter;
    static Point button_expedition_close;
    static Point button_expedition_close_2;
    static Pixel button_invasion;
    static Pixel button_gvg;
    static Pixel button_gvg_fight_close;

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
    static Pixel zone_dialog;
    static Pixel zone_close;

    /* -1 - undefined;
     *  0 - is trial;
     *  1 - is gauntlet. */
    static int trialValue = -1;

    /* -1 - undefined;
     *  0 - is expedition;
     *  1 - is invasion;
     *  2 - is GvG. */
    static int expeditionValue = -1;

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

        button_trial = new Pixel(1324, 578, 56, 116, 174);
        button_gauntlet = new Pixel(1323, 577, 3, 72, 100);

        button_expedition = new Pixel(1316, 492, 133, 134, 199);
        button_expedition_play = new Point(1102, 522);
        button_expedition_enter = new Point(950, 697);
        button_expedition_close = new Point(1142, 355);
        button_expedition_close_2 = new Point(1281, 314);
        button_invasion = new Pixel(1330, 490, 244, 201, 96);
        button_gvg = new Pixel(1319, 494, 247, 91, 0);
        button_gvg_fight_close = new Pixel(901, 687, 165, 211, 51);

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
        zone_dialog = new Pixel(1231, 459, 27, 33, 43);
        zone_close = new Pixel(760, 425, 197, 145, 0);
    }

    static void init(File logFile) throws Exception
    {
        Point dungeon = new Point();
        Point bard = new Point();

        initRobot();
        logWriter = new PrintWriter(new OutputStreamWriter(
                new FileOutputStream(logFile), StandardCharsets.UTF_8), true);

        // check walk duration
        if (Options.walkDuration < 1000 || Options.walkDuration > 900000)
            Options.invalid();

        // wait a bit, while user open game window
        Thread.sleep(1500);

        // declare all future stuff used
        defineStuff();

        // close all previous opened windows before start
        closeWindows();

        // get user the preffered dungeon before start...
        if (Options.checkDungeons) {
            dungeon = recordDungeon();

            closeWindows();
        }

        if (Options.checkExpeditions) {
            // debug
            System.out.printf("Check expeditions accessibility... ");

            // check access to the expeditions
            determineExpeditionValue();

            /* It's means, that the bot couldn't detect an expeditions icon
             * in the lobby. */
            if (expeditionValue == -1) {
                // debug
                System.out.printf("not available.\n");

                Options.checkExpeditions = false;
            } else {
                // debug
                System.out.printf("available.\n");
                System.out.printf("expeditionValue = %d\n", expeditionValue);
            }
        }

        // ...and bard for the expeditions
        if (Options.checkExpeditions && expeditionValue == 0) {
            bard = recordBard();

            closeWindows();
        }

        // main loop
        startTrack(dungeon, bard);
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

        Timer timer = new Timer(Options.walkDuration, walkPerformer);

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

        if (Options.checkTrials) {
            if (trialValue == TRIAL)
                logWriter.printf("# trials: %d\n", trials);
            else if (trialValue == GAUNTLET)
                logWriter.printf("# gauntlets: %d\n", gauntlets);
        }

        if (Options.checkExpeditions) {
            if (expeditionValue == EXPEDITION)
                logWriter.printf("# expeditions: %d\n", expeditions);
            else if (expeditionValue == INVASION)
                logWriter.printf("# invasions: %d\n", invasions);
            else if (expeditionValue == GVG)
                logWriter.printf("# GvGs: %d\n", gvgs);
        }

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

            // check for dialog (XXX)
            if (compareColors(zone_dialog.getPoint(),
                    zone_dialog.getColor())) {
                int i;

                for (i = 0; i < 12; i++)
                    click(0, new Point(zone_dialog.getX(),
                        zone_dialog.getY() - 30), 250);
            }

            // check for ad
            if (compareColors(button_watch.getPoint(),
                    button_watch.getColor())) {
                click(1000, button_watch.getPoint(), 3000);

                if (compareColors(button_watch_decline.getPoint(),
                        button_watch_decline.getColor()))
                    decline();
                else {
                    Thread.sleep(30000);

                    collectWatch();

                    ads += 1;
                }
            }

            // check for treasure
            if (compareColors(zone_treasure.getPoint(),
                    zone_treasure.getColor())) {
                if (compareColors(zone_treasure_no_keys.getPoint(),
                        zone_treasure_no_keys.getColor()))
                    decline();
                else {
                    accept();

                    treasures += 1;
                }
            }

            /* TODO: Add extreme persuades mode: try bribe the captures
             * if gems is enough. */
            // check for capture
            if (compareColors(button_persuade.getPoint(),
                    button_persuade.getColor())) {
                if (!Options.declineCaptures) {
                    accept();

                    persuades += 1;
                } else
                    decline();
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

            return false;
        }

        return true;
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
            /* Set focus to the game window?.. */
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

                /* TODO: Need to find a way, that will allow send key
                 * event instantly or search another possibility catching
                 * a fish only with 100% chance. I think, that is
                 * feasible. */
                /* By the way, here is observed by-effect the delay above:
                 * catching starts not right away, that is a serious
                 * defect. */
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

        if (checkEnough()) {
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

        if (checkEnough()) {
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

        if (checkEnough()) {
            logWriter.printf("Starting PvP... ");

            click(10000, button_pvp_fight, 1000);

            changeTeam(false, false);

            pressKey(0, KeyEvent.VK_ENTER, 1000, 0);

            pvps += 1;

            while (true) {
                // check for victory
                if (compareColors(button_pvp_fight_close.getPoint(),
                        button_pvp_fight_close.getColor())) {
                    pressKey(0, KeyEvent.VK_ENTER, 2000, 0);

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
        determineTrialValue();

        click(0, button_trial.getPoint(), 2000);
        click(0, button_play_another, 1500);

        if (checkEnough()) {
            changeTeam(false, false);

            pressKey(0, KeyEvent.VK_ENTER, 1000, 0);

            if (trialValue == TRIAL) {
                logWriter.printf("Starting trial... ");

                trials += 1;

                check();
            } else if (trialValue == GAUNTLET) {
                logWriter.printf("Starting gauntlet... ");

                gauntlets += 1;

                while (true) {
                    if (checkBattleOver()) {
                        logWriter.printf("finished.\n");

                        break;
                    }

                    Thread.sleep(500);
                }
            }
        }

        closeWindows();
    }

    // check expedition/invasion/gvgs availability
    static void checkExpedition(Point bard) throws Exception
    {
        determineExpeditionValue();

        if (expeditionValue != GVG)
            click(0, button_expedition.getPoint(), 2000);
        else
            click(0, button_gvg.getPoint(), 2000);

        click(0, button_play_another, 1500);

        if (checkEnough()) {
            if (expeditionValue == EXPEDITION) {
                click(0, bard, 1000);
                click(0, button_expedition_enter, 1000);
            }

            if (expeditionValue != GVG) {
                changeTeam(false, false);

                pressKey(0, KeyEvent.VK_ENTER, 1000, 0);
            }

            if (expeditionValue == EXPEDITION) {
                logWriter.printf("Starting expedition... ");

                expeditions += 1;

                check();

                click(4000, button_expedition_close, 1000);
                click(0, button_expedition_close_2, 1000);
            } else if (expeditionValue == INVASION) {
                logWriter.printf("Starting invasion... ");

                invasions += 1;

                while (true) {
                    if (checkBattleOver()) {
                        logWriter.printf("finished.\n");

                        break;
                    }

                    Thread.sleep(500);
                }
            } else if (expeditionValue == GVG) {
                logWriter.printf("Starting GvG... ");

                click(5000, button_pvp_fight, 2000);

                changeTeam(false, false);

                pressKey(0, KeyEvent.VK_ENTER, 1000, 0);

                gvgs += 1;

                while (true) {
                    // check for victory
                    if (compareColors(button_gvg_fight_close.getPoint(),
                            button_gvg_fight_close.getColor())) {
                        pressKey(0, KeyEvent.VK_ENTER, 2000, 0);

                        logWriter.printf("victory.\n");

                        break;
                    }

                    if (checkDefeat())
                        break;

                    Thread.sleep(500);
                }
            }
        }

        closeWindows();
    }

    static boolean checkBattleOver() throws Exception
    {
        if (compareColors(zone_close.getPoint(),
                zone_close.getColor())) {
            pressKey(0, KeyEvent.VK_ENTER, 2000, 0);

            return true;
        }

        return false;
    }

    static void determineTrialValue()
    {
        if (compareColors(button_trial.getPoint(),
                button_trial.getColor()))
            trialValue = TRIAL;
        else if (compareColors(button_gauntlet.getPoint(),
                button_gauntlet.getColor()))
            trialValue = GAUNTLET;
        else /* For beauty. */
            trialValue = -1;

        // debug
        System.out.printf("trialValue = %d\n", trialValue);
    }

    static void determineExpeditionValue()
    {
        if (compareColors(button_expedition.getPoint(),
                button_expedition.getColor()))
            expeditionValue = EXPEDITION;
        else if (compareColors(button_invasion.getPoint(),
                button_invasion.getColor()))
            expeditionValue = INVASION;
        else if (compareColors(button_gvg.getPoint(),
                button_gvg.getColor()))
            expeditionValue = GVG;
        else
            expeditionValue = -1;
    }

    static Point recordDungeon() throws Exception
    {
        Point dungeon = new Point();

        click(0, button_quests, 1000);

        dungeon = record(3);

        return dungeon;
    }

    static Point recordBard() throws Exception
    {
        Point bard = new Point();

        click(0, button_expedition.getPoint(), 1000);
        click(0, button_expedition_play, 1000);

        bard = record(3);

        return bard;
    }

    static void startTrack(Point dungeon, Point bard) throws Exception
    {
        initPassage();

        while (true) {
            /* Set focus to the game window just in case. */
            click(0, button_craft.getPoint(), 1000);

            closeWindows();

            if (Options.checkAdsLobby)
                checkAd();

            if (Options.checkDungeons)
                checkDungeon(dungeon);

            if (Options.checkRaids)
                checkRaid();

            if (Options.checkPvps)
                checkPvp();

            if (Options.checkTrials) {
                determineTrialValue();

                checkTrial();
            }

            if (Options.checkExpeditions) {
                determineExpeditionValue();

                checkExpedition(bard);
            }

            if (Options.checkFish)
                checkFish();

            if (Options.checkBounties)
                collectBounties();

            walkCircle();
        }
    }

    // close log writer stream on SIGINT interrupting
    static void checkSigint()
    {
        Runtime.getRuntime().addShutdownHook(new Thread()
        {
            public void run()
            {
                countTotal();

                logWriter.close();
            }
        });
    }
}
