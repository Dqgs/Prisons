package com.dogs.prisons.enchant;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Random;

public class EnchBlockBreak implements Listener {

    @EventHandler
    public void blockBreak(BlockBreakEvent event) {
        Player p = event.getPlayer();
        if (Enchant.getEnchants(p.getItemInHand(), p.getInventory().getBoots(), p.getInventory().getChestplate(), p.getInventory().getHelmet(), p.getInventory().getLeggings()) != null) {
            for (Enchant enchant : Enchant.getEnchantsOnPlayer(p).keySet()) {
                int chance = enchant.getChance();
                if (chance >= 100) {
                    enchant.playerBreakBlock(event);
                    return;
                }
                int random = new Random().nextInt(100) + 1;
                if (chance > random)
                    enchant.playerBreakBlock(event);
            }
        }
    }
}