package me.theheall.plugin.file;

import org.bukkit.Location;

import java.util.Set;

public class WarpFile extends Config {

    public WarpFile() {
        super(plugin.getDataFolder() + "/warps.yml");
    }

    public Location get(String name) {
        return config.getObject(name, Location.class);
    }

    public void set(String name, Location warp) {
        config.set(name, warp);
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
