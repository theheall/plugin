package me.theheall.plugin.chat;

import org.bukkit.ChatColor;

public enum Type {
    ERROR(ChatColor.DARK_RED),
    PERM(ChatColor.RED),
    WARN(ChatColor.GOLD),
    INFO(ChatColor.AQUA),
    SUCCESS(ChatColor.GREEN);

    private final ChatColor color;

    Type(ChatColor color) {
        this.color = color;
    }

    public String getPrefix() {
        return color + "# " + ChatColor.GRAY;
    }
}
