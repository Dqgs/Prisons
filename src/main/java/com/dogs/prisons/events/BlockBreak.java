package com.dogs.prisons.events;

import com.dogs.prisons.Prisons;
import com.dogs.prisons.block.BlockRegen;
import com.dogs.prisons.data.DataPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreak implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        Player player = event.getPlayer();
        Block block = event.getBlock();
        DataPlayer data = Prisons.getInstance().getDataManager().getDataPlayer(player);
        Material type = event.getBlock().getType();
        int booster = 1;
        if (event.getPlayer().hasPermission("prisons.booster.2")){
            booster = 2;
        }
        if (type.equals(Material.COAL_BLOCK) || type.equals(Material.COAL_ORE)){
            if (data.getLevel() >= 1) {
                new BlockRegen(block, 1);
                data.setXp(data.getXp() + (2 * booster));
            }
        } else if (type.equals(Material.IRON_BLOCK) || type.equals(Material.IRON_ORE)){
            if (data.getLevel() >= 10) {
                new BlockRegen(block, 1);
                data.setXp(data.getXp() + (2 * booster));
            }
        } else if (type.equals(Material.LAPIS_BLOCK) || type.equals(Material.LAPIS_ORE)){
            if (data.getLevel() >= 30) {
                new BlockRegen(block, 1);
                data.setXp(data.getXp() + (2 * booster));
            }
        } else if (type.equals(Material.REDSTONE_BLOCK) || type.equals(Material.REDSTONE_ORE)){
            if (data.getLevel() >= 50) {
                new BlockRegen(block, 1);
                data.setXp(data.getXp() + (2 * booster));
            }
        } else if (type.equals(Material.GOLD_BLOCK) || type.equals(Material.GOLD_ORE)){
            if (data.getLevel() >= 70) {
                new BlockRegen(block, 1);
                data.setXp(data.getXp() + (2 * booster));
            }
        } else if (type.equals(Material.DIAMOND_BLOCK) || type.equals(Material.DIAMOND_ORE)){
            if (data.getLevel() >= 90) {
                new BlockRegen(block, 1);
                data.setXp(data.getXp() + (2 * booster));
            }
        } else if (type.equals(Material.EMERALD_BLOCK) || type.equals(Material.EMERALD_ORE)){
            if (data.getLevel() >= 100) {
                new BlockRegen(block, 1);
                data.setXp(data.getXp() + (2 * booster));
            }
        }
        event.setCancelled(true);
    }
}
