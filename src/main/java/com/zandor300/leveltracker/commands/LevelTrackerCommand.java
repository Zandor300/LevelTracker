package com.zandor300.leveltracker.commands;

import com.zandor300.leveltracker.LevelTracker;
import com.zandor300.zsutilities.commandsystem.Command;
import org.bukkit.command.CommandSender;

/**
 * Created by Zandor on 3/25/15.
 */
public class LevelTrackerCommand extends Command {

	public LevelTrackerCommand() {
		super("leveltracker", "Show info.");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		LevelTracker.getChat().sendMessage(sender, "LevelTracker 1.0.0 by Zandor300");
	}
}
