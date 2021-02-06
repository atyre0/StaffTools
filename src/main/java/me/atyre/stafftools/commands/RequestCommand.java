package me.atyre.stafftools.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RequestCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 0) {
                player.sendMessage(ChatColor.RED + "Usage: /" + cmd.getLabel() + " <message>");
                return false;
            }

            try {
                StringBuilder message = new StringBuilder();

                for (int i = 0; i < args.length; i++) {
                    message.append(args[i]).append(" ");
                }

                player.sendMessage(ChatColor.GREEN + "You have submitted your request.");

                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (p.hasPermission("stafftools.request.see")) {
                        p.sendMessage(ChatColor.GREEN + "[Request] " + player.getName() +" requested: " + ChatColor.WHITE + message.toString());
                    }
                }
            } catch (NullPointerException e) {
                player.sendMessage(ChatColor.RED + "Something went wrong. Please contact an admin.");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "You must be a player to use this command.");
        }

        return false;
    }
}
