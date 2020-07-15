package me.theheall.plugin.commands;

import me.theheall.plugin.chat.Chat;
import me.theheall.plugin.chat.Type;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            Chat chat = new Chat(player);

            if (player.hasPermission("theheall.heal")) {
                player.setHealth(20);
                chat.send(Type.INFO, "Your health has been restored");
            } else {
                chat.perm();
            }
        }

        return true;
    }
}
