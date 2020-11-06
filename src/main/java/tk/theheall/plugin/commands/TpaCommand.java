package tk.theheall.plugin.commands;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tk.theheall.plugin.Chat;

import net.md_5.bungee.api.chat.TextComponent;
import java.util.HashMap;
import java.util.UUID;

public class TpaCommand implements CommandExecutor {

    private final HashMap<UUID, UUID> requests = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Chat chat = new Chat(player);

            if(player.hasPermission("tpa.request")) {
                if (args.length >= 1) {
                    switch (args[0]) {
                        case "send":
                            if (args.length >= 2) {
                                Player target = Bukkit.getPlayer(args[1]);
                                if (target != null) {
                                    requests.put(player.getUniqueId(), target.getUniqueId());
                                    chat.info("Richiesta di teletrasporto inviata");

                                    TextComponent accept = new TextComponent("Accetta ");
                                    accept.setBold(true);
                                    accept.setColor(ChatColor.GREEN);
                                    accept.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,
                                            "/tpa accept " + player.getName()));

                                    TextComponent deny = new TextComponent("Rifiuta");
                                    deny.setBold(true);
                                    deny.setColor(ChatColor.RED);
                                    deny.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,
                                            "/tpa deny " + player.getName()));

                                    TextComponent request = new TextComponent(">>> " + sender.getName()
                                            + " vuole teletrasportarsi da te <<<\n");
                                    request.setColor(ChatColor.DARK_AQUA);
                                    request.addExtra(accept);
                                    request.addExtra(deny);

                                    target.spigot().sendMessage(request);

                                } else chat.error("Il player non è online");
                            } else chat.error("Inserisci un player");
                            break;

                        case "accept":
                            if (args.length >= 2) {
                                Player target = Bukkit.getPlayer(args[1]);

                                if (target != null) {
                                    if (requests.containsKey(target.getUniqueId())) {
                                        requests.remove(target.getUniqueId(), player.getUniqueId());
                                        target.teleport(player);
                                    } else chat.warn("Richiesta scaduta oppure inesistente");
                                } else chat.error("Player non online");
                            } else chat.error("Inserisci un player");
                            break;

                        case "deny":
                            if (args.length >= 2) {
                                Player target = Bukkit.getPlayer(args[1]);
                                Chat targetChat = new Chat(target);

                                if (target != null) {
                                    if (requests.containsKey(target.getUniqueId())) {
                                        requests.remove(target.getUniqueId(), player.getUniqueId());
                                        targetChat.warn("Il player ha rifiutato la richiesta");
                                    } else chat.warn("Richiesta scaduta oppure inesistente");
                                } else chat.error("Player non online");
                            } else chat.error("Inserisci un player");
                    }
                } else chat.error("Inserisci send, accept o deny");
            } else chat.perm();
        } else {
            sender.sendMessage("Questo comando può essere usato solo da un player");
        }
        return true;
    }
}
