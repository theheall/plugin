package me.theheall.plugin.commands;

import me.theheall.plugin.chat.Chat;
import me.theheall.plugin.chat.Type;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Repairable;

public class RepairCmd implements CommandExecutor {
    @Override
    @SuppressWarnings("deprecation")
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Chat chat = new Chat(player);

            if (args.length == 0) {
                if (player.hasPermission("theheall.repair.item")) {
                    ItemStack item = player.getInventory().getItemInMainHand();
                    if (item.getItemMeta() instanceof Repairable) {
                        item.setDurability((short) 0);
                        chat.send(Type.INFO, "Item repaired");
                    } else {
                        chat.send(Type.ERROR, "You can't repair this item");
                    }
                } else {
                    chat.perm();
                }
            } else if (args.length == 1) {
                if (player.hasPermission("theheall.repair.all")) {
                    if (args[0].equals("all")) {
                        ItemStack[] items = player.getInventory().getContents();
                        int i = 0;
                        for (ItemStack item : items) {
                            if (item.getItemMeta() instanceof Repairable) {
                                item.setDurability((short) 0);
                                i++;
                            }
                        }
                        if (i == 0) {
                            chat.send(Type.INFO, "Nothing to repair");
                        } else {
                            chat.send(Type.INFO, i + " items have been repaired");
                        }
                    } else {
                        chat.send(Type.WARN, "You can repair all your items with /repair all");
                    }
                } else {
                    chat.perm();
                }
            } else {
                chat.send(Type.WARN, "Try with /repair or /repair all");
            }
        }

        return true;
    }
}