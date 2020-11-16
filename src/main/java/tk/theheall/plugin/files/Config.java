package tk.theheall.plugin.files;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import tk.theheall.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

public class Config extends YamlConfiguration {

    private static final Plugin plugin = Plugin.getPlugin(Plugin.class);
    private final File file;

    public Config(String configName) {
        if (!Files.exists(Paths.get(plugin.getDataFolder().getPath() + "/" + configName + ".yml"))) {
            try {
                Files.createFile(Paths.get(plugin.getDataFolder().getPath() + "/" + configName + ".yml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        this.file = new File(plugin.getDataFolder().getPath() + "/" + configName + ".yml");
        try {
            super.load(this.file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public Config(String type, UUID playerUuid) {
        if (!Files.exists(Paths.get(plugin.getDataFolder().getPath() + "/" + type + "/" + playerUuid + ".yml"))) {
            try {
                Files.createDirectories(Paths.get(plugin.getDataFolder().getPath() + type));
                Files.createFile(Paths.get(plugin.getDataFolder().getPath()
                        + "/" + type + "/" + playerUuid + ".yml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        this.file = new File(plugin.getDataFolder().getPath() + "/" + type + "/" + playerUuid + ".yml");
        try {
            super.load(this.file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            super.save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reload() {
        try {
            super.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
}
