package me.theheall.plugin.file;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Set;

public class HomeFile extends Config {

    private final Player player;

    public HomeFile(Player player) {
        super(plugin.getDataFolder() + "/home/" + player.getUniqueId().toString() + ".yml");
        this.player = player;
    }

    public Location get(String name) {
        return config.getObject(name, Location.class);
    }

    public void set(String name) {
        config.set(name, player.getLocation());
        fileSave();
    }

    public void del(String name) {
        config.set(name, null);
        fileSave();
    }

    public boolean has(String name) {
        return config.contains(name);
    }

    public Set<String> list() {
        return config.getKeys(false);
    }
}
