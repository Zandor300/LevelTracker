package com.zandor300.leveltracker;

import com.zandor300.leveltracker.commands.LevelCommand;
import com.zandor300.leveltracker.commands.LevelTrackerCommand;
import com.zandor300.leveltracker.listener.PlayerListener;
import com.zandor300.zsutilities.commandsystem.CommandManager;
import com.zandor300.zsutilities.config.Config;
import com.zandor300.zsutilities.utilities.Chat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.Metrics;

import java.io.IOException;

/**
 * Created by Zandor on 3/25/15.
 */
public class LevelTracker extends JavaPlugin {

	private static Chat chat = new Chat("LevelTracker", ChatColor.GREEN);
	private static LevelTracker plugin;
	private static Config config;

	public static Chat getChat() {
		return chat;
	}

	public static LevelTracker getPlugin() {
		return plugin;
	}

	public static Config getCustomConfig() {
		return config;
	}

	@Override
	public void onEnable() {
		chat.sendConsoleMessage("Setting things up...");

		plugin = this;
		config = new Config(this, "config.yml", true);
		PluginManager pm = Bukkit.getPluginManager();
		CommandManager cm = new CommandManager();

		chat.sendConsoleMessage("Sending metrics...");
		try {
			new Metrics(this).start();
			chat.sendConsoleMessage("Submitted stats to MCStats.org.");
		} catch (IOException e) {
			chat.sendConsoleMessage("Couldn't submit stats to MCStats.org...");
		}

		chat.sendConsoleMessage("Registering events...");
		pm.registerEvents(new PlayerListener(), this);
		chat.sendConsoleMessage("Registered events.");

		chat.sendConsoleMessage("Registering commands...");
		cm.registerCommand(new LevelCommand(), this);
		cm.registerCommand(new LevelTrackerCommand(), this);
		chat.sendConsoleMessage("Registered commands.");

		chat.sendConsoleMessage("Everything is setup!");
		chat.sendConsoleMessage("Enabled.");
	}
}
