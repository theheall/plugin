package me.theheall.plugin.commands;

import me.theheall.plugin.Plugin;
import me.theheall.plugin.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class VanishCmd implements CommandExecutor {

    public static ArrayList<Player> invisible = new ArrayList<>();
    private final Plugin plugin = Plugin.getPlugin(Plugin.class);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Chat chat = new Chat(player);

            if (player.hasPermission("theheall.vanish")) {
                if (invisible.contains(player)) {
                    invisible.remove(player);
                    for (Player target : Bukkit.getOnlinePlayers()) {
                        target.showPlayer(plugin, player);
                    }
                } else {
                    invisible.add(player);
                    for (Player target : Bukkit.getOnlinePlayers()) {
                        target.hidePlayer(plugin, player);
                    }
                }
            } else {
                chat.perm();
            }
        }
        return true;
    }

}
