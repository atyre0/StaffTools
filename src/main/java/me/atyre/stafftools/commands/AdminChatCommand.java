package me.atyre.stafftools.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdminChatCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission("stafftools.adminchat")) {
                if (args.length == 0) {
                    player.sendMessage(ChatColor.RED + "Usage: /" + cmd.getLabel() + " <message>");
                    return false;
                }

                try {
                    StringBuilder message = new StringBuilder();

                    for (int i = 0; i < args.length; i++) {
                        message.append(args[i]).append(" ");
                    }

                    for (Player p : Bukkit.getOnlinePlayers()) {
                        if (p.hasPermission("stafftools.adminchat.see")) {
                            p.sendMessage(ChatColor.RED + "[Admin Chat] " + player.getName() +": " + ChatColor.WHITE + message.toString());
                        }
                    }
                } catch (NullPointerException e) {
                    player.sendMessage(ChatColor.RED + "Something went wrong. Please contact an admin.");
                }
            } else {
                player.sendMessage(ChatColor.RED + "You do not have permissions to execute this command.");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "You must be a player to use this command.");
        }

        return false;
    }
}
