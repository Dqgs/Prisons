package com.dogs.prisons.commands.admin;

import com.dogs.prisons.shard.Shard;
import com.dogs.prisons.shard.ShardRarity;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class ShardGive implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("shard")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("You must be a player");
                return true;
            }
            Player player = (Player) sender;
            if (player.hasPermission("prisons.rank.admin")) {
                if (args[0].equalsIgnoreCase("give")) {
                    if (args[1] != null) {
                        Player target = Bukkit.getPlayerExact(args[1]);
                        if (target == null) {
                            sender.sendMessage(target + " is not online or is null");
                            return true;
                        }
                        if (args[2] != null) {
                            if (args[2].equalsIgnoreCase("simple")) {
                                Shard shard = new Shard(ShardRarity.SIMPLE);
                                target.getInventory().addItem(shard.item);
                            } else if (args[2].equalsIgnoreCase("uncommon")) {
                                Shard shard = new Shard(ShardRarity.UNCOMMON);
                                target.getInventory().addItem(shard.item);
                            } else if (args[2].equalsIgnoreCase("Rare")) {
                                Shard shard = new Shard(ShardRarity.RARE);
                                target.getInventory().addItem(shard.item);
                            } else if (args[2].equalsIgnoreCase("Epic")) {
                                Shard shard = new Shard(ShardRarity.EPIC);
                                target.getInventory().addItem(shard.item);
                            } else if (args[2].equalsIgnoreCase("Legendary")) {
                                Shard shard = new Shard(ShardRarity.LEGENDARY);
                                target.getInventory().addItem(shard.item);
                            } else if (args[2].equalsIgnoreCase("Mythic")) {
                                Shard shard = new Shard(ShardRarity.MYTHIC);
                                target.getInventory().addItem(shard.item);
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
