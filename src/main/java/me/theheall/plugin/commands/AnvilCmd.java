package me.theheall.plugin.commands;

import me.theheall.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class AnvilCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            Chat chat = new Chat(player);
            if (player.hasPermission("theheall.anvil")) {
                Inventory anvil = Bukkit.createInventory(null, InventoryType.ANVIL);
                player.openInventory(anvil);
            } else {
                chat.perm();
            }
        }
        return true;
    }
}
