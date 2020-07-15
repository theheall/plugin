package me.theheall.plugin.commands;

import me.theheall.plugin.chat.Chat;
import me.theheall.plugin.chat.Type;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpeedCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Chat chat = new Chat(player);

            if (player.hasPermission("theheall.speed")) {
                if (args.length == 1) {
                    float value = Float.parseFloat(args[0]);
                    if (value >= 1 && value <= 10) {
                        player.setWalkSpeed((float) ((value - 1.0) * (0.8 / 9.0) + 0.2));
                        chat.send(Type.INFO, "Speed level set to " + args[0]);
                    } else {
                        chat.send(Type.WARN, "Enter a value between 1 and 10");
                    }
                } else {
                    chat.send(Type.WARN, "Enter a value between 1 and 10");
                }
            } else {
                chat.perm();
            }
        }

        return true;
    }
}
