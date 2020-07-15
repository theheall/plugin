package me.theheall.plugin.commands;

import me.theheall.plugin.chat.Chat;
import me.theheall.plugin.file.VaultFile;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VaultCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Chat chat = new Chat(player);

            if (args.length == 1) {
                if (player.hasPermission("theheall.vault")) {
                    VaultFile vaults = new VaultFile(player);
                    player.openInventory(vaults.load(args[0]));
                } else {
                    chat.perm();
                }
            }


        }
        return true;
    }
}
