package tk.theheall.plugin;

import org.bukkit.plugin.java.JavaPlugin;
import tk.theheall.plugin.commands.TpaCommand;

import java.util.Objects;

public final class Plugin extends JavaPlugin {

    @Override
    public void onEnable() {
        Objects.requireNonNull(getServer().getPluginCommand("tpa")).setExecutor(new TpaCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
