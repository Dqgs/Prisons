package com.dogs.prisons.charm;

import com.dogs.prisons.enchant.EnchantAdd;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class CharmChecker implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("Charm")){
            if (!(sender instanceof Player)){
                sender.sendMessage("You must be a player");
                return true;
            }
            Player player = (Player) sender;
            Pickaxe pickaxe = new Pickaxe(player.getItemInHand());

            pickaxe.upgrade();
        }
        return false;
    }
}
