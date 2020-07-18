package me.theheall.plugin.chat;

import org.bukkit.ChatColor;

public enum Type {
    ERROR(ChatColor.DARK_RED),
    PERM(ChatColor.RED),
    WARN(ChatColor.GOLD),
    INFO(ChatColor.AQUA),
    SUCCESS(ChatColor.GREEN),
    INFO_DATA(ChatColor.GREEN, ChatColor.YELLOW);

    private final ChatColor[] colors;

    Type(ChatColor... colors) {
        this.colors = colors;
    }

    public String getPrefix() {
        return colors[0] + "# " + ChatColor.GRAY;
    }

    public ChatColor getColor(int index) {
        return colors[index];
    }

    public boolean multicolor() {
        return colors.length > 1;
    }
}
