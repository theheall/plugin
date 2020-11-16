package tk.theheall.plugin.files;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.Objects;

public class LocationFile {

    private static final Config config = new Config("locations");

    public static Location getLocation(String code) {
        config.reload();
        return config.getLocation(code + ".location");
    }

    public static boolean hasLocation(String code) {
        config.reload();
        return code != null && config.contains(code);
    }

    public static boolean hasLocation(int slot) {
        config.reload();
        return slot != -1 && getLocationsCode().stream().anyMatch(i -> getLocationSlot(i) == slot);
    }

    public static ItemStack getLocationItem(String code) {
        config.reload();
        return config.getItemStack(code + ".item");
    }

    public static int getLocationSlot(String code) {
        config.reload();
        return config.getInt(code + ".slot");
    }

    public static Location getLocationBySlot(int slot) {
        config.reload();
        return getLocationsCode()
                .stream()
                .filter(code -> getLocationSlot(code) == slot)
                .map(LocationFile::getLocation)
                .findFirst()
                .orElse(null);
    }

    public static String getLocationNameBySlot(int slot) {
        config.reload();
        return getLocationsCode()
                .stream()
                .filter(code -> getLocationSlot(code) == slot)
                .map(code -> Objects.requireNonNull(getLocationItem(code).getItemMeta()).getDisplayName())
                .findFirst()
                .orElse(null);
    }

    public static ItemStack getLocationItemBySlot(int slot) {
        config.reload();
        return getLocationsCode()
                .stream()
                .filter(code -> getLocationSlot(code) == slot)
                .map(LocationFile::getLocationItem)
                .findFirst()
                .orElse(null);
    }

    public static Collection<String> getLocationsCode() {
        config.reload();
        return Objects.requireNonNull(config.getRoot()).getKeys(false);
    }

    public static void addLocation(String code, ItemStack item, int slot, Location location) {
        config.reload();
        config.set(code + ".item", item);
        config.set(code + ".slot", slot);
        config.set(code + ".location", location);
        config.save();
    }

    public static void removeLocation(String code) {
        config.reload();
        config.set(code, null);
        config.save();
    }
}
