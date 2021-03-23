package com.dogs.prisons.commands.player;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.permissions.Permission;

public class Feed implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("feed")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("You must be a player");
                return true;
            }
            Player player = (Player) sender;
            int saturation = 0;
            if (player.hasPermission("prisons.rank.5")){
                saturation = 45;
            } else if (player.hasPermission("prisons.rank.4")){
                saturation = 35;
            } else if (player.hasPermission("prisons.rank.3")){
                saturation = 25;
            }  else if (player.hasPermission("prisons.rank.2")){
                saturation = 20;
            }  else if (player.hasPermission("prisons.rank.1")){
                saturation = 10;
            } else {
                player.sendMessage("You need to have a rank for this");
                return false;
            }
            player.setFoodLevel(20);
            player.sendMessage("You have been fed for " + saturation + "!");
            return true;
        }
        return false;
    }
}
