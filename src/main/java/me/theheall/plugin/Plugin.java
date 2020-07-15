package me.theheall.plugin;

import me.theheall.plugin.commands.*;
import me.theheall.plugin.events.InventoryEvent;
import me.theheall.plugin.events.JoinEvent;
import me.theheall.plugin.events.QuitEvent;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Plugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {

        //Plugin Commands
        Objects.requireNonNull(getServer().getPluginCommand("anvil")).setExecutor(new AnvilCmd());
        Objects.requireNonNull(getServer().getPluginCommand("enderchest")).setExecutor(new EnderchestCmd());
        Objects.requireNonNull(getServer().getPluginCommand("feed")).setExecutor(new FeedCmd());
        Objects.requireNonNull(getServer().getPluginCommand("fly")).setExecutor(new FlyCmd());
        Objects.requireNonNull(getServer().getPluginCommand("gamemode")).setExecutor(new GamemodeCmd());
        Objects.requireNonNull(getServer().getPluginCommand("heal")).setExecutor(new HealCmd());
        Objects.requireNonNull(getServer().getPluginCommand("home")).setExecutor(new HomeCmd());
        Objects.requireNonNull(getServer().getPluginCommand("inventorysee")).setExecutor(new InventorySeeCmd());
        Objects.requireNonNull(getServer().getPluginCommand("message")).setExecutor(new MessageCmd());
        Objects.requireNonNull(getServer().getPluginCommand("position")).setExecutor(new PositionCmd());
        Objects.requireNonNull(getServer().getPluginCommand("repair")).setExecutor(new RepairCmd());
        Objects.requireNonNull(getServer().getPluginCommand("speed")).setExecutor(new SpeedCmd());
        Objects.requireNonNull(getServer().getPluginCommand("vanish")).setExecutor(new VanishCmd());
        Objects.requireNonNull(getServer().getPluginCommand("vault")).setExecutor(new VaultCmd());
        Objects.requireNonNull(getServer().getPluginCommand("warp")).setExecutor(new WarpCmd());
        Objects.requireNonNull(getServer().getPluginCommand("workbench")).setExecutor(new WorkbenchCmd());

        //Plugin Events
        getServer().getPluginManager().registerEvents(new InventoryEvent(), this);
        getServer().getPluginManager().registerEvents(new JoinEvent(), this);
        getServer().getPluginManager().registerEvents(new QuitEvent(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
