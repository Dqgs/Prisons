package com.dogs.prisons.events;

import com.dogs.prisons.Prisons;
import com.dogs.prisons.block.BlockRegen;
import com.dogs.prisons.charm.Pickaxe;
import com.dogs.prisons.data.DataPlayer;
import com.dogs.prisons.enchant.ItemSet;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class BlockBreakType implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        Player player = event.getPlayer();
        Block block = event.getBlock();
        DataPlayer data = Prisons.getInstance().getDataManager().getDataPlayer(player);
        Material type = event.getBlock().getType();
        ItemStack hand = player.getItemInHand();
        Pickaxe pickaxe = null;
        if (event.isCancelled())
            return;
        if (ItemSet.PICKAXE.getItems().contains(hand.getType())) {
            pickaxe = new Pickaxe(hand);
        }
        int booster = 1;
        int charm = 0;
        if (event.getPlayer().hasPermission("prisons.booster.2")){
            booster = 2;
        }
        if (type.equals(Material.COAL_BLOCK) || type.equals(Material.COAL_ORE)){
            if (data.getLevel() >= 1) {
                new BlockRegen(block, 5);
                data.setXp(data.getXp() + (3 * booster));
                charm = 2;
            }
        } else if ((type.equals(Material.IRON_BLOCK) || type.equals(Material.IRON_ORE))){
            if (hand.getType().equals(Material.WOOD_PICKAXE) || hand.getType().equals(Material.STONE_PICKAXE)
                    || hand.getType().equals(Material.IRON_PICKAXE) || hand.getType().equals(Material.DIAMOND_PICKAXE)) {
                if (data.getLevel() >= 10) {
                    new BlockRegen(block, 10);
                    data.setXp(data.getXp() + (7 * booster));
                    charm = 3;
                }
            }
        } else if (type.equals(Material.LAPIS_BLOCK) || type.equals(Material.LAPIS_ORE)){
            if (hand.getType().equals(Material.STONE_PICKAXE) ||
                    hand.getType().equals(Material.IRON_PICKAXE) ||
                    hand.getType().equals(Material.DIAMOND_PICKAXE)) {
                if (data.getLevel() >= 30) {
                    new BlockRegen(block, 20);
                    data.setXp(data.getXp() + (36 * booster));
                    charm = 4;
                }
            }
        } else if (type.equals(Material.REDSTONE_BLOCK) || type.equals(Material.REDSTONE_ORE)){
            if (hand.getType().equals(Material.STONE_PICKAXE)
                    || hand.getType().equals(Material.IRON_PICKAXE) || hand.getType().equals(Material.DIAMOND_PICKAXE)) {
                if (data.getLevel() >= 50) {
                    new BlockRegen(block, 45);
                    data.setXp(data.getXp() + (59 * booster));
                    charm = 5;
                }
            }
        } else if (type.equals(Material.GOLD_BLOCK) || type.equals(Material.GOLD_ORE)){
            if (hand.getType().equals(Material.IRON_PICKAXE)
                    || hand.getType().equals(Material.DIAMOND_PICKAXE)) {
                if (data.getLevel() >= 70) {
                    new BlockRegen(block, 90);
                    data.setXp(data.getXp() + (140 * booster));
                    charm = 6;
                }
            }
        } else if (type.equals(Material.DIAMOND_BLOCK) || type.equals(Material.DIAMOND_ORE)){
            if (hand.getType().equals(Material.DIAMOND_PICKAXE)) {
                if (data.getLevel() >= 90) {
                    new BlockRegen(block, 180);
                    data.setXp(data.getXp() + (512 * booster));
                    charm = 7;
                }
            }
        } else if (type.equals(Material.EMERALD_BLOCK) || type.equals(Material.EMERALD_ORE)){
            if (hand.getType().equals(Material.DIAMOND_PICKAXE)) {
                if (data.getLevel() >= 102) {
                    new BlockRegen(block, 360);
                    data.setXp(data.getXp() + (1200 * booster));
                    charm = 8;
                }
            }
        }
        
        if (pickaxe != null){
            pickaxe.setCharm(pickaxe.getCharm() + charm);
            player.setItemInHand(pickaxe.itemStack);
        }
        event.setCancelled(true);
    }
}
