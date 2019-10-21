### Introduction
This is simple bot for Bit Heroes game. It lets you not spend nights on walkthrough of adventures or just more automate a game process while you don't have access to Bit Heroes.

What can bot do:
1. Grind dungeons, raids, PvPs, trials/gauntlets and expeditions
2. Watch the ads (in lobby and battles)
3. Collect bounties
4. Write logs about its work for you
5. Be online for days
6. To fish

### Build
Easiest tool for this bot building is ant. Use ``ant jar`` to get bot in jar archive.

By the way, bot using [JCommander](jcommander.org). Just create ``lib`` dir in bot root and put jar there. Note, bot tested with JCommander 1.79.

### Usage
Now you have a ready bot. It supports command line arguments. Thanks to JCommander. Well, type --help for more information. :)

When bot starts without ``--no-check-dungeons`` and ``--no-check-expeditions`` it first opens a quests window, where you must hover over on preffered dungeon, which bot will grind. After 3 seconds bot will close quests window. Wait 5 seconds. Bot will open expeditions window (make sure, that you have enough badges for play), where you must hover over on preffered bard, which bot will also grind.

### Important
1. Bot doesn't support Windows, because it using other color definition.
2. If something isn't working - create fork or new issue.
3. When bot is works, don't use PC. Or run the bot in the another session.
4. Only Kongregate is supported and perhaps only Firefox browser.
5. You must go to cinematic mode and set 100% zoom in browser.
6. Auto pilot must be enabled.
7. Merchants must be disabled.
