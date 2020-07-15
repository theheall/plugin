package me.theheall.plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessageCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("theheall.msg")) {
            if (args.length > 1) {
                StringBuilder message = new StringBuilder();
                for (String arg : args) message.append(arg).append(" ");
                message.deleteCharAt(message.length() - 1);

                for (Player target : Bukkit.getOnlinePlayers()) target.sendMessage(message.toString());
                // TODO: 21/05/2020 send message both player and console
            }
        }
        return true;
    }
}
