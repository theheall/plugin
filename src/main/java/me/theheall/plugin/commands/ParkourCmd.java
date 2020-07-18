package me.theheall.plugin.commands;

import me.theheall.plugin.chat.Chat;
import me.theheall.plugin.chat.Type;
import me.theheall.plugin.file.ParkourFile;
import me.theheall.plugin.games.parkour.CheckpointType;
import me.theheall.plugin.games.parkour.ParkourGame;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class ParkourCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Chat chat = new Chat(player);
            ParkourFile config = new ParkourFile();

            if (player.hasPermission("theheall.parkour")) {
                if (args.length >= 1) {
                    switch (args[0]) {
                        case "set":
                            if (args.length == 3 && player.hasPermission("theheall.parkour.create")) {
                                config.setCheckpoint(player.getLocation().getBlock(), CheckpointType.valueOf(args[1]),
                                        Integer.parseInt(args[2]));
                                chat.send(Type.SUCCESS, "Checkpoint added");
                            } else {
                                chat.send(Type.WARN, "You have to specify START, MIDDLE or FINISH and number");
                            }
                            break;

                        case "checkpoint":
                            if (player.hasPermission("theheall.parkour.play")) {
                                if(ParkourGame.isPlaying(player)) {
                                    ParkourGame.teleport(player);
                                } else {
                                    chat.send(Type.WARN, "Non stai giocando");
                                }
                            }
                            break;

                        case "record":
                            chat.send(Type.INFO_DATA, "Your best time is {0}. The best player is {1} " +
                                    "with a time of {2}", Chat.timeFormat(config.getTime(player)),
                                    Objects.requireNonNull(Bukkit.getPlayer(config.getBestPlayer())).getName(),
                                    Chat.timeFormat(config.getTime(Objects.requireNonNull(Bukkit.getPlayer(
                                            config.getBestPlayer())))));
                            break;
                    }
                }
            }
        }
        return true;
    }
}
