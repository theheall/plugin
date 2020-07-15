package me.theheall.plugin.commands;

import me.theheall.plugin.chat.Chat;
import me.theheall.plugin.chat.Type;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventorySeeCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Chat chat = new Chat(player);

            if (args.length == 1) {
                if (player.hasPermission("theheall.invsee")) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target != null) {
                        ItemStack[] items = target.getInventory().getStorageContents();
                        ItemStack[] armors = target.getInventory().getArmorContents();
                        Inventory inventory = Bukkit.createInventory(null, 6 * 9, target.getDisplayName() + "'s Inventory");

                        for (ItemStack armor : armors) {
                            if (armor != null) inventory.addItem(armor);
                        }

                        int i = 0;
                        for (ItemStack item : items) {
                            inventory.setItem(i++ + 9, item);
                        }

                        player.openInventory(inventory);
                    } else {
                        chat.send(Type.WARN, "Player " + args[0] + " is not online");
                    }
                } else {
                    chat.perm();
                }
            } else {
                chat.send(Type.WARN, "Insert a player name");
            }
        }
        return true;
    }
}