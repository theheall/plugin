package me.theheall.plugin.chat;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Chat {

    private static final String PERM_MSG = "You don't have permission to perform this command";
    private final Player player;

    public Chat(Player player) {
        this.player = player;
    }

    public void send(Type type, String message, Object... data) {
        if(data.length == 0 && !type.multicolor()) {
            player.sendMessage(type.getPrefix() + ChatColor.translateAlternateColorCodes('&', message));
        } else {
            for(int i=0; i<data.length; i++) {
                message = message.replace("{" + i + "}", type.getColor(1) +
                        String.valueOf( data[i]) + type.getColor(0));
            }

            send(type.getColor(0) + message);
        }
    }

    private void send(String message) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    public void perm() {
        send(Type.PERM, PERM_MSG);
    }

    public static String timeFormat(long time) {
        return new SimpleDateFormat("mm:ss.SSS").format(new Date(time));
    }
}
