package me.atyre.stafftools.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReportCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 0) {
                player.sendMessage(ChatColor.RED + "Usage: /" + cmd.getLabel() + " <player> <reason>");
                return false;
            }

            if (args.length == 1) {
                player.sendMessage(ChatColor.RED + "Usage: /" + cmd.getLabel() + " <player> <reason>");
                return false;
            }

            try {

                Player target = Bukkit.getPlayer(args[0]);

                StringBuilder message = new StringBuilder();

                for (int i = 1; i < args.length; i++) {
                    message.append(args[i]).append(" ");
                }

                player.sendMessage(ChatColor.GREEN + "Your report was submitted.");

                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (p.hasPermission("stafftools.report.see")) {
                        p.sendMessage(ChatColor.BLUE + "[Report] " + ChatColor.AQUA + target.getName() + " was reported for " + ChatColor.WHITE + message.toString());
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
