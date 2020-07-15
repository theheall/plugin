package me.theheall.plugin.commands;

import me.theheall.plugin.chat.Chat;
import me.theheall.plugin.chat.Type;
import me.theheall.plugin.file.HomeFile;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



public class HomeCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Chat chat = new Chat(player);
            HomeFile homes = new HomeFile(player);

            if (player.hasPermission("theheall.home")) {
                if (args.length >= 1) {
                    switch (args[0]) {
                        case "set":
                            if (args.length == 2) {
                                if (!homes.has(args[1])) {
                                    homes.set(args[1]);
                                    chat.send(Type.SUCCESS, "Home " + args[1] + " added");
                                } else {
                                    chat.send(Type.ERROR, "Home " + args[1] + "already exists");
                                }
                            } else {
                                chat.send(Type.WARN, "You have to specify the home name");
                            }
                            break;

                        case "del":
                            if (args.length == 2) {
                                if (homes.has(args[1])) {
                                    homes.del(args[1]);
                                    chat.send(Type.SUCCESS, "Home " + args[1] + " removed");
                                } else {
                                    chat.send(Type.ERROR, "Home " + args[1] + " does not exists");
                                }
                            } else {
                                chat.send(Type.WARN, "You have to specify the home name");
                            }
                            break;

                        case "list":
                            StringBuilder builder = new StringBuilder();
                            homes.list().forEach(home -> builder.append(home).append(" "));
                            if (builder.length() != 0) {
                                chat.send(Type.INFO, "Homes available: " + builder.toString());
                            } else {
                                chat.send(Type.INFO, "There are no homes available. Try with /home set <name>");
                            }
                            break;

                        default:
                            if (homes.has(args[0])) {
                                player.teleport(homes.get(args[0]));
                                chat.send(Type.INFO, "You have been teleported to " + args[0]);
                            } else {
                                chat.send(Type.WARN, "Home " + args[0] + " does not exist. Try with /home set "
                                        + args[0]);
                            }
                            break;
                    }
                } else {
                    chat.send(Type.WARN, "Type set for create a new home, list to see them");
                }
            } else {
                chat.perm();
            }
        }

        return true;
    }
}
