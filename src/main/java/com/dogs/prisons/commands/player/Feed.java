package com.dogs.prisons.commands.player;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class Feed implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("feed")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("You must be a player");
                return true;
            }
            Player player = (Player) sender;
            if (player.hasPermission("prisons.rank.1")){
                player.setFoodLevel(20);
                player.setSaturation(10);
                player.sendMessage("You have been fed!");
            } else if (player.hasPermission("prisons.rank.2")){
                player.setFoodLevel(20);
                player.setSaturation(20);
                player.sendMessage("You have been fed!");
            } else if (player.hasPermission("prisons.rank.3")){
                player.setFoodLevel(20);
                player.setSaturation(25);
                player.sendMessage("You have been fed!");
            }  else if (player.hasPermission("prisons.rank.4")){
                player.setFoodLevel(20);
                player.setSaturation(35);
                player.sendMessage("You have been fed!");
            }  else if (player.hasPermission("prisons.rank.5")){
                player.setFoodLevel(20);
                player.setSaturation(45);
                player.sendMessage("You have been fed!");
            }
        }
        return false;
    }
}
