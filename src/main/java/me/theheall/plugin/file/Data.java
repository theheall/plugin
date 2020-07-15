package me.theheall.plugin.file;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.theheall.plugin.Plugin;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Data {
    protected static Plugin plugin = Plugin.getPlugin(Plugin.class);

    protected File file;
    protected JsonObject json = new JsonObject();

    public Data(String filename) {
        file = new File(filename);
        try {
            if (!file.exists()) {
                Files.createFile(Path.of(filename));
                Files.write(Path.of(filename), "{}".getBytes());
            }
            json = new JsonParser().parse(new FileReader(file)).getAsJsonObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JsonObject getData() {
        return json;
    }
}
