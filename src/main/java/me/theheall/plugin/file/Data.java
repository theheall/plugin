package me.theheall.plugin.file;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.theheall.plugin.VanillaPlugin;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Data {

    protected File file;
    protected JsonObject json = new JsonObject();

    public Data(String filename) {
        file = new File(filename);
        try {
            if (!file.exists()) {
                Files.createFile(Paths.get(filename));
                Files.write(Paths.get(filename), "{}".getBytes());
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
