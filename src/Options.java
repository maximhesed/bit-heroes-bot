import com.beust.jcommander.Parameter;

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

    @Parameter(names = {"--decline-ads-lobby"})
    static boolean checkAdsLobby = true;

    @Parameter(names = {"--decline-captures"})
    static boolean declineCaptures = false;

    @Parameter(names = {"--auto-team-dungeon"})
    static boolean autoTeam = false;

    @Parameter(names = {"--check-bounties"})
    static boolean checkBounties = false;

    static void showHelp()
    {
        String indent = "                                   ";

        System.out.printf("Bot options:\n");

        System.out.printf("  -difficult-dungeon DIFFICULT     %s",
            "change the difficult of the dungeons\n");
        System.out.printf("  -difficult-raid DIFFICULT        %s",
            "change the difficult of the raids\n");
        System.out.printf("  --no-check-dungeons              %s",
            "don't go to the dungeons\n");
        System.out.printf("  --no-check-raids                 %s",
            "don't go to the raids\n");
        System.out.printf("  --no-check-pvps                  %s",
            "don't go to PvPs\n");
        System.out.printf("  --no-check-trials                %s",
            "don't go to the trials\n");
        System.out.printf("  --no-check-expeditions           %s",
            "don't go to the expeditions\n");
        System.out.printf("  --no-check-fish                  %s",
            "don't go to fish\n");
        System.out.printf("  --no-team-dungeon                %s",
            "don't take a team in the dungeons\n");
        System.out.printf("  --decline-ads-lobby              %s",
            "don't check ads in the lobby\n");
        System.out.printf("  --decline-captures               %s",
            "decline the captures\n");
        System.out.printf("  --auto-team-dungeon              %s%s%s",
            "if before the dungeon team isn't full,\n", indent,
            "than fill in it automatically\n");
        System.out.printf("  --check-bounties                 %s",
            "check the bounties after the passage\n");
    }

    static void showHell()
    {
        System.out.printf("Of course, but not here.\n");
    }
}
