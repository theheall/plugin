package tk.theheall.plugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tk.theheall.plugin.Chat;
import tk.theheall.plugin.files.LocationFile;

public class HomeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            Chat chat = new Chat(player);

            if (player.hasPermission("theheall.home")) {
                if (LocationFile.hasLocation("home")) {
                    player.teleport(LocationFile.getLocation("home"));
                    chat.info("Sei stato teletrasportato alla home");
                } else chat.warn("La home non è stata impostata");
            } else chat.perm();
        } else {
            sender.sendMessage("Questo comando può essere usato solo da un player");
        }
        return true;
    }
}
