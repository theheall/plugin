package me.theheall.plugin.commands;

import me.theheall.plugin.chat.Chat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WorkbenchCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Chat chat = new Chat(player);

            if (player.hasPermission("theheall.workbench")) {
                player.openWorkbench(null, true);
            } else {
                chat.perm();
            }
        }
        return true;
    }
}
