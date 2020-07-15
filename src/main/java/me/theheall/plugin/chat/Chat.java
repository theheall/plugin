package me.theheall.plugin.chat;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Chat {

    public static final String PERM_MSG = "You don't have permission to perform this command";

    private final Player player;

    public Chat(Player player) {
        this.player = player;
    }

    public void send(Type type, String message) {
        player.sendMessage(type.getPrefix() + ChatColor.translateAlternateColorCodes('&', message));
    }

    public void perm() {
        send(Type.PERM, PERM_MSG);
    }
}
