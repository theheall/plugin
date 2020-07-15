package me.theheall.plugin.file;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class VaultFile extends Config {

    public VaultFile(Player player) {
        super(plugin.getDataFolder() + "/vault/" + player.getUniqueId().toString() + ".yml");
    }

    public void save(String vault, Inventory inventory) {
        for (int i = 0; i < inventory.getContents().length; i++) {
            config.set(vault + "." + i, inventory.getContents()[i]);
        }
        fileSave();
    }

    public Inventory load(String vault) {
        Inventory inventory = Bukkit.createInventory(null, 6 * 9, "Vault: " + vault);
        if (config.contains(vault)) for (int i = 0; i < inventory.getContents().length; i++) {
            if (config.contains(vault + "." + i)) inventory.setItem(i, config.getItemStack(vault + "." + i));
        }

        return inventory;
    }
}
