package me.theheall.plugin.events;

import me.theheall.plugin.file.VaultFile;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class InventoryEvent implements Listener {

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        String name = event.getView().getTitle();
        Inventory inventory = event.getInventory();

        if (name.endsWith("'s Inventory")) {
            Player player = Bukkit.getPlayer(name.split("'")[0]);
            if (player != null) {
                player.getInventory().setContents(Arrays.copyOfRange(inventory.getContents(), 9, 44,
                        ItemStack[].class));
                player.getInventory().setArmorContents(Arrays.copyOf(inventory.getContents(), 3, ItemStack[].class));
            }
        } else if (name.startsWith("Vault")) {
            VaultFile vault = new VaultFile((Player) event.getPlayer());
            vault.save(name.split(": ")[1], inventory);
        }
    }

    @EventHandler
    public void onInventoryAction(InventoryClickEvent event) {
        String name = event.getView().getTitle();
        Inventory inventory = event.getInventory();

        if (name.startsWith("Vault")) {
            VaultFile vaults = new VaultFile((Player) event.getWhoClicked());
            vaults.save(name.split(": ")[1], inventory);
        }
    }
}
