package com.zandor300.leveltracker.listener;

import com.zandor300.leveltracker.LevelTracker;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;

/**
 * Created by Zandor on 3/25/15.
 */
public class PlayerListener implements Listener {

	@EventHandler
	public void onPlayerLevelChange(PlayerLevelChangeEvent event) {
		int latest = LevelTracker.getCustomConfig().getInt("levels." + event.getPlayer().getUniqueId().toString() +
				".highestLevel");
		if(latest < event.getPlayer().getLevel())
			LevelTracker.getCustomConfig().set("levels." + event.getPlayer().getUniqueId().toString() +
					".highestLevel", event.getPlayer().getLevel());
	}

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		int latest = LevelTracker.getCustomConfig().getInt("levels." + event.getEntity().getUniqueId().toString() +
				".highestLevelDeath");
		if(latest < event.getEntity().getLevel())
			LevelTracker.getCustomConfig().set("levels." + event.getEntity().getUniqueId().toString() +
					".highestLevelDeath", event.getEntity().getLevel());

		int highestLevel = LevelTracker.getCustomConfig()
				.getInt("levels." + event.getEntity().getUniqueId().toString() + ".highestLevel");
		int highestLevelDeath = LevelTracker.getCustomConfig()
				.getInt("levels." + event.getEntity().getUniqueId().toString() + ".highestLevelDeath");

		LevelTracker.getChat().sendMessage(event.getEntity(), "Your highest level of all time is " +
				LevelTracker.getChat().bold(highestLevel) + ".");
		LevelTracker.getChat().sendMessage(event.getEntity(), "Your highest level since last death is " +
				LevelTracker.getChat().bold(highestLevelDeath) + ".");
	}
}
