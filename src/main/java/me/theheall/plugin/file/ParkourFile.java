package me.theheall.plugin.file;

import me.theheall.plugin.games.parkour.Checkpoint;
import me.theheall.plugin.games.parkour.CheckpointType;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public class ParkourFile extends Config {

    public ParkourFile() {
        super(plugin.getDataFolder() + "/parkour.yml");
    }

    public void setCheckpoint(Block block, CheckpointType type, int number) {
        config.set("checkpoints." + block.hashCode() + ".location", block.getLocation());
        config.set("checkpoints." + block.hashCode() + ".type", type.name());
        config.set("checkpoints." + block.hashCode() + ".number", number);
        fileSave();
    }

    public void setTime(Player player, long time) {
        config.set("times." + player.getUniqueId(), time);
        fileSave();
    }

    public Checkpoint getCheckpoint(Block block) {
        return new Checkpoint(config.getObject("checkpoints." + block.hashCode() + ".location", Location.class),
                CheckpointType.valueOf(config.getString("checkpoints." + block.hashCode() + ".type")),
                config.getInt("checkpoints." + block.hashCode() + ".number"));
    }

    public long getTime(Player player) {
        if (config.contains("times." + player.getUniqueId())) {
            return config.getInt("times." + player.getUniqueId());
        } else {
            return -1L;
        }
    }

    public Location getReset() {
        return config.getObject("reset", Location.class);
    }

    public UUID getBestPlayer() {
        AtomicLong bestTime = new AtomicLong(Long.MAX_VALUE);
        AtomicReference<String> bestPlayer = new AtomicReference<>();

        Objects.requireNonNull(config.getConfigurationSection("times")).getValues(false)
                .forEach((uuid, time) -> {
                    if((int)time < bestTime.get()) {
                        bestTime.set((int)time);
                        bestPlayer.set(uuid);
                    }
                });

        return UUID.fromString(bestPlayer.get());
    }

    public boolean hasCheckpoint(Block block) {
        return config.contains("checkpoints." + block.hashCode());
    }
}
