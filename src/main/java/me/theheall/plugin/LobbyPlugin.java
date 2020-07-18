package me.theheall.plugin;

import me.theheall.plugin.commands.ParkourCmd;
import me.theheall.plugin.events.ParkourEvent;
import me.theheall.plugin.file.Config;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class LobbyPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        Config.setPlugin(LobbyPlugin.getPlugin(LobbyPlugin.class));

        // Plugin Commands
        Objects.requireNonNull(getCommand("parkour")).setExecutor(new ParkourCmd());

        // Plugin Events
        getServer().getPluginManager().registerEvents(new ParkourEvent(), this);
    }
}
