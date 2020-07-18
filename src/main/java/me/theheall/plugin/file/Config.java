package me.theheall.plugin.file;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Config {

    public static JavaPlugin plugin;

    protected Path path;
    protected File file;
    protected YamlConfiguration config;

    public Config(String filename) {
        path = Paths.get(filename);
        try {
            if (!Files.exists(path.getParent())) Files.createDirectories(path.getParent());
            if (!Files.exists(path)) Files.createFile(Paths.get(filename));
            file = new File(path.toUri());
            config = YamlConfiguration.loadConfiguration(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void fileSave() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setPlugin(JavaPlugin plugin) {
        Config.plugin = plugin;
    }
}
