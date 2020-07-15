package me.theheall.plugin.commands;

import me.theheall.plugin.chat.Chat;
import me.theheall.plugin.chat.Type;
import me.theheall.plugin.file.WarpFile;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Chat chat = new Chat(player);
            WarpFile warps = new WarpFile();

            if (player.hasPermission("theheall.warp")) {
                if (args.length >= 1) {
                    switch (args[0]) {
                        case "list":
                            if (player.hasPermission("theheall.warp.list")) {
                                StringBuilder builder = new StringBuilder();
                                warps.list().forEach(warp -> builder.append(warp).append(" "));
                                if (builder.length() != 0) {
                                    chat.send(Type.INFO, "Warps available: " + builder.toString());
                                } else {
                                    chat.send(Type.INFO, "There are no warps available");
                                }
                            } else {
                                chat.perm();
                            }
                            break;

                        case "set":
                            if (player.hasPermission("theheall.warp.set")) {
                                if (args.length >= 2) {
                                    if (!warps.has(args[1])) {
                                        warps.set(args[1], player.getLocation());
                                        chat.send(Type.SUCCESS, "Warp " + args[1] + " added");
                                    } else {
                                        chat.send(Type.WARN, "Warp " + args[1] + " already exists");
                                    }
                                } else {
                                    chat.send(Type.WARN, "You have to specify the warp name");
                                }
                            } else {
                                chat.perm();
                            }
                            break;

                        case "del":
                            if (player.hasPermission("theheall.warp.del")) {
                                if (args.length >= 2) {
                                    if (warps.has(args[1])) {
                                        warps.del(args[1]);
                                        chat.send(Type.SUCCESS, "Warp " + args[1] + " deleted");
                                    } else {
                                        chat.send(Type.ERROR, "Warp " + args[1] + " does not exists");
                                    }
                                } else {
                                    chat.send(Type.WARN, "You have to specify the warp name");
                                }
                            } else {
                                chat.perm();
                            }
                            break;

                        default:
                            if (player.hasPermission("theheall.warp.tp")) {
                                if (warps.has(args[0])) {
                                    player.teleport(warps.get(args[0]));
                                    chat.send(Type.INFO, "You have been teleported to " + args[0]);
                                } else {
                                    chat.send(Type.WARN, "Warp " + args[0] + " does not exists");
                                }
                            } else {
                                chat.perm();
                            }
                    }
                } else {
                    chat.send(Type.WARN, "Try /warp list to see the available warps");
                }
            } else {
                chat.perm();
            }
        }

        return true;
    }
}
