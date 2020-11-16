package tk.theheall.plugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import tk.theheall.plugin.commands.HomeCommand;
import tk.theheall.plugin.commands.TpaCommand;
import tk.theheall.plugin.commands.WarpCommand;
import tk.theheall.plugin.events.FarmlandEvent;
import tk.theheall.plugin.events.MenuEvent;
import tk.theheall.plugin.events.SeedEvent;

import java.util.Objects;

public final class Plugin extends JavaPlugin {

    @Override
    public void onEnable() {
        Objects.requireNonNull(getServer().getPluginCommand("tpa")).setExecutor(new TpaCommand());
        Objects.requireNonNull(getServer().getPluginCommand("tpa")).setTabCompleter(new TpaCommand());
        Objects.requireNonNull(getServer().getPluginCommand("warps")).setExecutor(new WarpCommand());
        Objects.requireNonNull(getServer().getPluginCommand("home")).setExecutor(new HomeCommand());

        Bukkit.getPluginManager().registerEvents(new FarmlandEvent(), this);
        Bukkit.getPluginManager().registerEvents(new SeedEvent(), this);
        Bukkit.getPluginManager().registerEvents(new MenuEvent(), this);
    }
}
