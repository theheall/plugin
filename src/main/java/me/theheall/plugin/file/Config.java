package me.theheall.plugin.file;

import me.theheall.plugin.Plugin;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Config {

    protected static Plugin plugin = Plugin.getPlugin(Plugin.class);

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
}
