package com.dogs.prisons.wormhole;

import com.dogs.prisons.enchant.Enchant;
import com.dogs.prisons.enchant.EnchantAdd;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class WormHoleEnchant implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("Enchant")){
            if (!(sender instanceof Player)){
                sender.sendMessage("You must be a player");
                return true;
            }
            Player player = (Player) sender;
            EnchantAdd.addRandomEnchant(player);
        }
        return false;
    }
}
