package me.theheall.plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PositionCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            if (sender.hasPermission("theheall.pos")) {
                Player target = Bukkit.getPlayer(args[0]);
                if (target != null) {
                    Location location = target.getLocation();
                    sender.sendMessage(ChatColor.GOLD + target.getDisplayName() + " is at: " + (int) location.getX() + " " +
                            (int) location.getY() + " " + (int) location.getZ());
                }
            }
            // TODO: 21/05/2020 send message both to player and console
        }
        return true;
    }
}
