package me.theheall.plugin.commands;

import me.theheall.plugin.chat.Chat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.theheall.plugin.chat.Type.INFO;

public class FlyCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Chat chat = new Chat(player);

            if (player.hasPermission("theheall.fly")) {
                if (!player.getAllowFlight()) {
                    player.setAllowFlight(true);
                    chat.send(INFO, "Fly enabled");
                } else {
                    player.setFlying(false);
                    player.setAllowFlight(false);
                    chat.send(INFO, "Fly disable");
                }
            } else {
                chat.perm();
            }
        }

        return true;
    }
}
