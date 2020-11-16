package tk.theheall.plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import tk.theheall.plugin.Chat;
import tk.theheall.plugin.files.LocationFile;

import java.util.Arrays;
import java.util.Collections;

public class WarpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Chat chat = new Chat(player);

            if (args.length == 0 && player.hasPermission("theheall.warp.use")) {
                Inventory menu = Bukkit.createInventory(null, 27, "Warps");
                LocationFile.getLocationsCode().forEach(code -> {
                    menu.setItem(LocationFile.getLocationSlot(code), LocationFile.getLocationItem(code));
                });
                player.openInventory(menu);
            } else if (player.hasPermission("theheall.warp.set")) {
                switch (args[0]) {
                    case "add":
                        if (args.length > 2) {
                            if (Integer.parseInt(args[2]) < 0 || Integer.parseInt(args[2]) > 27) {
                                chat.error("Formato non valido - numero slot sbagliato");
                                return true;
                            }

                            if (LocationFile.hasLocation(Integer.parseInt(args[2]))) {
                                chat.error("Posizione già occupata");
                                return true;
                            } else if (LocationFile.hasLocation(args[0])) {
                                chat.info("Warp modificato");
                            } else {
                                chat.info("Warp creato");
                            }

                            ItemStack item = player.getInventory().getItemInMainHand();
                            ItemMeta meta = player.getInventory().getItemInMainHand().getItemMeta();
                            assert meta != null;
                            meta.setLore(Collections.singletonList(ChatColor.GRAY + "Pozione: "
                                    + player.getLocation().getBlockX() + " " + player.getLocation().getBlockY()
                                    + " " + player.getLocation().getBlockZ()));
                            item.setItemMeta(meta);

                            LocationFile.addLocation(args[1], item, Integer.parseInt(args[2]), player.getLocation());
                        } else chat.error("Formato non valido");
                        break;

                    case "remove":
                        if (args.length > 1) {
                            if (LocationFile.hasLocation(args[1])) {
                                LocationFile.removeLocation(args[1]);
                                chat.info("Warp eliminato");
                            } else {
                                chat.warn("Warp inesistente");
                            }
                        } else chat.error("Formato non valido");
                }
            } else chat.perm();
        } else sender.sendMessage("Questo comando può essere usato solo da un player");
        return true;
    }
}
