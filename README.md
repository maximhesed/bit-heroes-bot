### Introduction
This is simple bot for Bit Heroes game. It lets you not spend nights on acting out available resources such as shards, energy, tickets, etc.

What can it do:
1. Grind dungeons, raids, PvPs, trials/gauntlets and expeditions
2. Watch the ads (in lobby and battles)
3. Collect bounties
4. Write logs for you
5. Be online for days
6. To fish

### Build
Easiest tool for this bot building is ant. Use ``ant jar`` to get bot in jar archive.

By the way, bot using [JCommander](jcommander.org). Just create ``lib`` dir in bot root and put jar there. Note, bot tested with JCommander 1.79. 

### Usage
Now you have a ready bot. It support command line arguments. Thanks to JCommander. Well, type --help for more information. :) 

When bot run without ``--no-check-dungeons`` and ``--no-check-expeditions`` it first opens a quests window, where you must choose dungeons, which bot will grind. After 3 seconds bot will close quests window. Before it, move cursor on your preffered dungeons and wait 10 seconds. Then, it will open expeditions window (make sure, that badges enough for play), where you must choose bard, which bot will also grind.

### Important
1. Bot doesn't support Windows, because it using other color definition.
2. If something isn't working - create fork or new issue.
3. When bot work, don't use PC. Or run it in another session.
4. Only Kongregate is support and perhaps only Mozilla Firefox.
5. You must go to cinematic mode and set 100% zoom in browser.

Think that's all, i want to sleep.
