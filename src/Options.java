import com.beust.jcommander.Parameter;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;

final class Options
{
    @Parameter(names = {"--help"})
    static boolean help = false;

    @Parameter(names = {"--hell"})
    static boolean hell = false;

    @Parameter(names = {"-difficult-dungeon"})
    static int difficultDungeon = 2;

    @Parameter(names = {"-difficult-raid"})
    static int difficultRaid = 2;

    @Parameter(names = {"-walk-duration"})
    static int walkDuration = 900000; // 15 minutes

    @Parameter(names = {"--no-check-ads"})
    static boolean checkAdsLobby = true;

    @Parameter(names = {"--no-check-dungeons"})
    static boolean checkDungeons = true;

    @Parameter(names = {"--no-check-raids"})
    static boolean checkRaids = true;

    @Parameter(names = {"--no-check-pvps"})
    static boolean checkPvps = true;

    @Parameter(names = {"--no-check-trials"})
    static boolean checkTrials = true;

    @Parameter(names = {"--no-check-expeditions"})
    static boolean checkExpeditions = true;

    @Parameter(names = {"--no-check-fish"})
    static boolean checkFish = true;

    @Parameter(names = {"--no-team-dungeon"})
    static boolean noTeam = false;

    @Parameter(names = {"--decline-captures"})
    static boolean declineCaptures = false;

    @Parameter(names = {"--auto-team-dungeon"})
    static boolean autoTeam = false;

    @Parameter(names = {"--check-bounties"})
    static boolean checkBounties = false;

    static void init(String[] args)
    {
        try {
            JCommander.newBuilder()
                .addObject(new Options())
                .build()
                .parse(args);
        } catch (ParameterException ex) {
            Options.invalid();
        }
    }

    static void showHelp()
    {
        String indent = "                                   ";

        System.out.printf("Bot options:\n");

        System.out.printf("  -difficult-dungeon DIFFICULT     %s",
            "change the difficult of the dungeons\n");
        System.out.printf("  -difficult-raid DIFFICULT        %s",
            "change the difficult of the raids\n");
        System.out.printf("  -walk-duration DURATION          %s%s%s",
            "set anti kick delay in ms (4 times waiting for DURATION)\n",
            indent,
            "(1000 <= DURATION <= 900000) (default is 900000)\n");
        System.out.printf("  --no-check-ads                   %s",
            "don't check ads in the lobby\n");
        System.out.printf("  --no-check-dungeons              %s",
            "don't go to the dungeons\n");
        System.out.printf("  --no-check-raids                 %s",
            "don't go to the raids\n");
        System.out.printf("  --no-check-pvps                  %s",
            "don't go to PvPs\n");
        System.out.printf("  --no-check-trials                %s",
            "don't go to the trials/gauntlets\n");
        System.out.printf("  --no-check-expeditions           %s",
            "don't go to the expeditions/invasions\n");
        System.out.printf("  --no-check-fish                  %s",
            "don't go to fish\n");
        System.out.printf("  --no-team-dungeon                %s",
            "don't take a team in the dungeons\n");
        System.out.printf("  --decline-captures               %s",
            "decline the captures\n");
        System.out.printf("  --auto-team-dungeon              %s%s%s",
            "if before the dungeon team isn't fully\n", indent,
            "formed, then do it automatically\n");
        System.out.printf("  --check-bounties                 %s",
            "check the bounties after a passage\n");

        System.exit(-1);
    }

    static void showHell()
    {
        System.out.printf("Of course, but not here.\n");
        System.exit(-1);
    }

    static void invalid()
    {
        System.out.printf("Type --help for more information.\n");
        System.exit(-1);
    }
}
