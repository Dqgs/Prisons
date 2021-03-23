package com.dogs.prisons.commands.player;

import com.dogs.prisons.charm.Charm;
import com.dogs.prisons.charm.Pickaxe;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Extract implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("extract")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("You must be a player");
                return true;
            }
            Player player = (Player) sender;
            if (player.getInventory().getItemInHand().getType().toString().toLowerCase().contains("pickaxe")) {
                if (args[0] != null) {
                    Pickaxe pickaxe = new Pickaxe(player.getInventory().getItemInHand());
                    int amount = Integer.parseInt(args[0]);
                    Charm charm = new Charm(player.getInventory().getItemInHand());
                    if (amount <= pickaxe.getCharm()){
                        pickaxe.setCharm(pickaxe.getCharm() - amount);
                        player.getInventory().addItem(charm.createNewEnergy(amount));
                        player.updateInventory();
                    }
                }
            }
        }
        return false;
    }
}
