package tk.theheall.plugin.commands;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import tk.theheall.plugin.Chat;

import java.util.*;
import java.util.stream.Collectors;

public class TpaCommand implements CommandExecutor, TabCompleter {

    private final HashMap<UUID, UUID> requests = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Chat chat = new Chat(player);

            if (player.hasPermission("theheall.tpa")) {
                if (args.length == 1) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target != null) {
                        requests.put(player.getUniqueId(), target.getUniqueId());
                        chat.info("Richiesta di teletrasporto inviata");

                        TextComponent accept = new TextComponent("» Accetta « ");
                        accept.setBold(true);
                        accept.setColor(ChatColor.GREEN);
                        accept.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,
                                "/tpa accept " + player.getName()));

                        TextComponent deny = new TextComponent("» Rifiuta «");
                        deny.setBold(true);
                        deny.setColor(ChatColor.RED);
                        deny.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,
                                "/tpa deny " + player.getName()));

                        TextComponent request = new TextComponent("» " + sender.getName()
                                + " vuole teletrasportarsi da te «\n");
                        request.setColor(ChatColor.AQUA);
                        request.addExtra(accept);
                        request.addExtra(deny);

                        target.spigot().sendMessage(request);
                    }
                } else if (args.length > 1) {
                    switch (args[0]) {
                        case "send":
                            Player target = Bukkit.getPlayer(args[1]);
                            if (target != null) {
                                requests.put(player.getUniqueId(), target.getUniqueId());
                                chat.info("Richiesta di teletrasporto inviata");

                                TextComponent accept = new TextComponent("» Accetta « ");
                                accept.setBold(true);
                                accept.setColor(ChatColor.GREEN);
                                accept.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,
                                        "/tpa accept " + player.getName()));

                                TextComponent deny = new TextComponent("» Rifiuta «");
                                deny.setBold(true);
                                deny.setColor(ChatColor.RED);
                                deny.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,
                                        "/tpa deny " + player.getName()));

                                TextComponent request = new TextComponent("» " + sender.getName()
                                        + " vuole teletrasportarsi da te «\n");
                                request.setColor(ChatColor.AQUA);
                                request.addExtra(accept);
                                request.addExtra(deny);

                                target.spigot().sendMessage(request);
                            } else chat.error("Inserisci un player");
                            break;

                        case "accept":
                            Player targetAccept = Bukkit.getPlayer(args[1]);
                            if (targetAccept != null) {
                                if (requests.containsKey(targetAccept.getUniqueId())) {
                                    requests.remove(targetAccept.getUniqueId(), player.getUniqueId());
                                    targetAccept.teleport(player);
                                } else chat.warn("Richiesta scaduta oppure inesistente");
                            } else chat.error("Player non online");
                            break;

                        case "deny":
                            Player targetDeny = Bukkit.getPlayer(args[1]);
                            Chat targetChat = new Chat(targetDeny);

                            if (targetDeny != null) {
                                if (requests.containsKey(targetDeny.getUniqueId())) {
                                    requests.remove(targetDeny.getUniqueId(), player.getUniqueId());
                                    targetChat.warn("Il player ha rifiutato la richiesta");
                                } else chat.warn("Richiesta scaduta oppure inesistente");
                            } else chat.error("Player non online");
                    }
                } else chat.error("Formato non valido");
            } else chat.perm();
        } else {
            sender.sendMessage("Questo comando può essere usato solo da un player");
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        final String[] ARGS = {"send", "accept", "deny"};
        final List<String> hints = new ArrayList<>();

        switch (args.length) {
            case 1:
                StringUtil.copyPartialMatches(args[0], Arrays.asList(ARGS), hints);
                break;
            case 2:
                StringUtil.copyPartialMatches(args[1], Bukkit.getOnlinePlayers().stream().map(HumanEntity::getName)
                        .collect(Collectors.toList()), hints);
                break;
        }

        return hints;
    }
}
