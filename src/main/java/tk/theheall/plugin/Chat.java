package tk.theheall.plugin;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Chat {

    private Player player;

    public Chat(Player player) {
        this.player = player;
    }

    public void warn(String message) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                "&7<&6!&7> &6" + message + " &7<&6!&7>"));
    }

    public void info(String message) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                "&7<&b!&7> &b" + message + " &7<&b!&7>"));
    }

    public void error(String message) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                "&7<&c!&7> &c" + message + " &7<&c!&7>"));
    }

    public void perm() {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                "&7<&c!&7> &cNon hai il permesso di eseguire questo comando &7<&c!&7>"));
    }
}
