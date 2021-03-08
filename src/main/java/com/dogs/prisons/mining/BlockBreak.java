package com.dogs.prisons.mining;

import com.dogs.prisons.Prisons;
import com.dogs.prisons.data.DataPlayer;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.Listener;

public class BlockBreak implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        DataPlayer data = Prisons.getInstance().getDataManager().getDataPlayer(event.getPlayer());
        Material type = event.getBlock().getType();
        int booster = 1;
        if (event.getPlayer().hasPermission("prisons.booster.2")){
            booster = 2;
        }
        if (type.equals(Material.COAL_BLOCK) || type.equals(Material.COAL_ORE)){
            data.setXp(data.getXp() + (2 * booster));
        } else if (type.equals(Material.IRON_BLOCK) || type.equals(Material.IRON_ORE)){
            data.setXp(data.getXp() + (2 * booster));
        } else if (type.equals(Material.LAPIS_BLOCK) || type.equals(Material.LAPIS_ORE)){
            data.setXp(data.getXp() + (2 * booster));
        } else if (type.equals(Material.REDSTONE_BLOCK) || type.equals(Material.REDSTONE_ORE)){
            data.setXp(data.getXp() + (2 * booster));
        } else if (type.equals(Material.GOLD_BLOCK) || type.equals(Material.GOLD_ORE)){
            data.setXp(data.getXp() + (2 * booster));
        } else if (type.equals(Material.DIAMOND_BLOCK) || type.equals(Material.DIAMOND_ORE)){
            data.setXp(data.getXp() + (2 * booster));
        } else if (type.equals(Material.EMERALD_BLOCK) || type.equals(Material.EMERALD_ORE)){
            data.setXp(data.getXp() + (2 * booster));
        }
    }
}
