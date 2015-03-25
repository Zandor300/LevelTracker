package com.zandor300.leveltracker.commands;

import com.zandor300.leveltracker.LevelTracker;
import com.zandor300.zsutilities.commandsystem.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Zandor on 3/25/15.
 */
public class LevelCommand extends Command {

	public LevelCommand() {
		super("level", "Show your highest level of all time.");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if(!(sender instanceof Player))
			return;

		Player player = (Player) sender;

		if(!LevelTracker.getCustomConfig().getConfigurationSection("levels")
				.getKeys(false).contains(player.getUniqueId().toString())) {
			LevelTracker.getChat().sendMessage(player, "Your levels have not been tracked before.");
			return;
		}

		int highestLevel = LevelTracker.getCustomConfig()
				.getInt("levels." + player.getUniqueId().toString() + ".highestLevel");
		int highestLevelDeath = LevelTracker.getCustomConfig()
				.getInt("levels." + player.getUniqueId().toString() + ".highestLevelDeath");

		if(highestLevelDeath != 0)
			LevelTracker.getChat().sendMessage(player, "Your highest level of all time is " +
					LevelTracker.getChat().bold(highestLevel) + ".");
		else
			LevelTracker.getChat().sendMessage(player, "Your highest level has not been logged.");

		if(highestLevelDeath != 0)
			LevelTracker.getChat().sendMessage(player, "Your highest level since last death is " +
					LevelTracker.getChat().bold(highestLevelDeath) + ".");
		else
			LevelTracker.getChat().sendMessage(player, "Your death level has not been logged.");
	}
}
