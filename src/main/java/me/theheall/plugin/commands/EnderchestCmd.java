package me.theheall.plugin.commands;

import me.theheall.plugin.chat.Chat;
import me.theheall.plugin.chat.Type;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EnderchestCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            Chat chat = new Chat(player);

            if (args.length == 0) {
                if (player.hasPermission("theheall.echest.own")) {
                    player.openInventory(player.getEnderChest());
                } else {
                    chat.perm();
                }
            }

            if (args.length >= 1) {
                if (player.hasPermission("theheall.echest.others")) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target != null) {
                        player.openInventory(target.getEnderChest());
                    } else {
                        chat.send(Type.WARN, "Player " + args[0] + " is not online");
                    }
                } else {
                    chat.perm();
                }
            }
        }
        return true;
    }
}