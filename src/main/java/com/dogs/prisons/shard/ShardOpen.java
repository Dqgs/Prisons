package com.dogs.prisons.shard;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Random;

public class ShardOpen implements Listener {

    @EventHandler
    public void onShardOpen(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK){
            ItemStack playerItemInHand = player.getItemInHand();
            if (playerItemInHand.getType().equals(Material.PRISMARINE_SHARD)) {
                Shard shard = new Shard(playerItemInHand);
                int random = new Random().nextInt(shard.shardRarity.items.size());
                List<ItemStack> items = shard.shardRarity.items;
                ItemStack item = items.get(random);
                if (playerItemInHand.getAmount() >= 2) {
                    playerItemInHand.setAmount(playerItemInHand.getAmount() - 1);
                } else {
                    player.setItemInHand(new ItemStack(Material.AIR));
                }
                player.getInventory().addItem(item);
            }
        }
    }
}
