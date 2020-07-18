package me.theheall.plugin.games.parkour;

import me.theheall.plugin.PlayerInventory;
import me.theheall.plugin.chat.Chat;
import me.theheall.plugin.chat.Type;
import me.theheall.plugin.file.Config;
import me.theheall.plugin.file.ParkourFile;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class ParkourGame {

    private static final ParkourFile config = new ParkourFile();
    private static final HashMap<UUID, Long> startTime = new HashMap<>();
    private static final HashMap<UUID, Long> fractionalTime = new HashMap<>();
    private static final HashMap<UUID, Checkpoint> checkpoints = new HashMap<>();
    private static final HashMap<UUID, Integer> timer = new HashMap<>();


    public static void start(Player player, Checkpoint checkpoint) {
        if(isPlaying(player)) return;

        long newTime = System.currentTimeMillis();
        startTime.put(player.getUniqueId(), newTime);
        fractionalTime.put(player.getUniqueId(), newTime);
        checkpoints.put(player.getUniqueId(), checkpoint);

        new Chat(player).send(Type.INFO_DATA, "Parkour Started!");
        PlayerInventory.open(player, PlayerInventory.Type.PARKOUR);

        timer.put(player.getUniqueId(), new BukkitRunnable() {
            @Override
            public void run() {
                String time = Chat.timeFormat(System.currentTimeMillis() - startTime.get(player.getUniqueId()));
                Checkpoint pos = checkpoints.get(player.getUniqueId());
                if(pos.getType().equals(CheckpointType.MIDDLE)) {
                    time = "Checkpoint #" + pos.getNumber() + " - " + time;
                }

                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(
                        ChatColor.YELLOW + time));
            }
        }.runTaskTimer(Config.plugin, 0L, 5L).getTaskId());
    }

    public static void checkpoint(Player player, Checkpoint checkpoint) {
        if(!isPlaying(player) || checkpoints.get(player.getUniqueId()).getNumber() >= checkpoint.getNumber()) return;

        long newTime = System.currentTimeMillis();
        long lastTime = fractionalTime.get(player.getUniqueId());

        fractionalTime.put(player.getUniqueId(), newTime);
        checkpoints.put(player.getUniqueId(), checkpoint);

        new Chat(player).send(Type.INFO_DATA, "You have reached {0} in {1}",
                "Checkpoint #" + checkpoint.getNumber(), Chat.timeFormat(newTime - lastTime));
    }

    public static void finish(Player player) {
        if(!isPlaying(player)) return;
        Chat chat = new Chat(player);

        long newTime = System.currentTimeMillis();
        long totalTime = newTime - startTime.get(player.getUniqueId());
        boolean record = totalTime < config.getTime(player);

        if (record) {
            chat.send(Type.INFO_DATA, "Congratulations you broke your previous {0} record. " +
                    "You have now completed parkour in just {1}",
                    Chat.timeFormat(config.getTime(player)), Chat.timeFormat(totalTime));
        } else if (config.getTime(player) == -1L) {
            chat.send(Type.INFO_DATA, "You have completed parkour in {0}", Chat.timeFormat(totalTime));
        } else {
            chat.send(Type.INFO_DATA,"You have completed parkour in {0}, Your best is {1}",
                    Chat.timeFormat(totalTime), Chat.timeFormat(config.getTime(player)));
        }

        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(
                ChatColor.YELLOW + "FINISH!"));
        PlayerInventory.open(player, PlayerInventory.Type.MENU);
        if (record || config.getTime(player) == -1L) config.setTime(player, totalTime);

        stop(player);
    }

    public static void teleport(Player player) {
        if(!isPlaying(player)) return;

        player.teleport(checkpoints.get(player.getUniqueId()).getLocation());
    }

    public static void reset(Player player) {
        player.teleport(config.getReset());
        stop(player);
    }

    public static boolean isPlaying(Player player) {
        return checkpoints.containsKey(player.getUniqueId());
    }

    public static void stop(Player player) {
        if(!isPlaying(player)) return;

        startTime.remove(player.getUniqueId());
        fractionalTime.remove(player.getUniqueId());
        checkpoints.remove(player.getUniqueId());

        Bukkit.getScheduler().cancelTask(timer.get(player.getUniqueId()));
    }
}
