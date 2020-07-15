package me.theheall.plugin.events;

import me.theheall.plugin.Plugin;
import me.theheall.plugin.commands.VanishCmd;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class JoinEvent implements Listener {

    Plugin plugin = Plugin.getPlugin(Plugin.class);

    @EventHandler
    public void onPlayerJoin(org.bukkit.event.player.PlayerJoinEvent event) {
        Player player = event.getPlayer();
        for (Player target : VanishCmd.invisible) {
            player.hidePlayer(plugin, target);
        }

        if (!player.hasPlayedBefore()) {
            event.setJoinMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Welcome on the server " + ChatColor.GOLD + player.getName() + "!");
        } else {
            player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Welcome back " + ChatColor.LIGHT_PURPLE + player.getName() + "!");
        }

        event.setJoinMessage("");
    }


}
