package me.theheall.plugin.commands;

import me.theheall.plugin.chat.Chat;
import me.theheall.plugin.chat.Type;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Chat chat = new Chat(player);

            if (player.hasPermission("theheall.gamemodes")) {
                if (args.length == 1) {
                    switch (args[0]) {
                        case "0":
                            player.setGameMode(GameMode.SURVIVAL);
                            break;

                        case "1":
                            player.setGameMode(GameMode.CREATIVE);
                            break;

                        case "2":
                            player.setGameMode(GameMode.ADVENTURE);
                            break;

                        case "3":
                            player.setGameMode(GameMode.SPECTATOR);
                            break;

                        default:
                            chat.send(Type.WARN, "Try to insert number from 0 to 4");
                            break;
                    }
                    chat.send(Type.INFO, "Gamemode set to " + player.getGameMode().name());
                } else {
                    chat.send(Type.WARN, "Try to insert number from 0 to 4");
                }
            } else {
                chat.perm();
            }
        }

        return true;
    }
}
