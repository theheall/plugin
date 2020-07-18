package me.theheall.plugin;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class PlayerInventory {

    private static final HashMap<UUID, String> state = new HashMap<>();

    public static void open(Player player, Type type) {
        if(state.containsKey(player.getUniqueId()) && state.get(player.getUniqueId()).equals(type.name())) return;

        player.getInventory().clear();
        type.getConfig().getKeys(false).forEach(slot -> {
            player.getInventory().setItem(Integer.parseInt(slot), type.getConfig().getItemStack(slot));
        });
        state.put(player.getUniqueId(), type.name());
    }

    public enum Type {
        PARKOUR("{'3': {==: org.bukkit.inventory.ItemStack, v: 2567, type: HEAVY_WEIGHTED_PRESSURE_PLATE, meta:" +
                " {==: ItemMeta, meta-type: UNSPECIFIC, display-name: §aCheckpoint}}, '4': {==: " +
                "org.bukkit.inventory.ItemStack, v: 2567, type: OAK_DOOR, meta: {==: ItemMeta, meta-type: UNSPECIFIC," +
                " display-name: §eReset}}, '5': {==: org.bukkit.inventory.ItemStack, v: 2567, type: RED_BED, meta:" +
                " {==: ItemMeta, meta-type: UNSPECIFIC, display-name: §cCancel}}}"),
        MENU("{'4': {==: org.bukkit.inventory.ItemStack, v: 2567, type: NETHER_STAR, meta: {==: ItemMeta," +
                "meta-type: UNSPECIFIC, display-name: §bMenu}}}");

        private String data;

        Type(String data) {
            this.data = data;
        }

        public YamlConfiguration getConfig() {
            YamlConfiguration config = new YamlConfiguration();
            try {
                config.loadFromString(data);
            } catch (InvalidConfigurationException e) {
                e.printStackTrace();
            }

            return config;
        }
    }
}
