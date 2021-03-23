package com.dogs.prisons.events;

import com.dogs.prisons.Prisons;
import com.dogs.prisons.data.DataPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class BlockBreakPickaxe implements Listener{

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockBreak(BlockBreakEvent event){
        DataPlayer data = Prisons.getInstance().getDataManager().getDataPlayer(event.getPlayer());
        ItemStack hand = data.player.getItemInHand();
        switch (hand.getType()){
            case WOOD_PICKAXE:
                if (data.getLevel() < 10)
                    event.setCancelled(true);
                break;
            case STONE_PICKAXE:
                if (data.getLevel() < 30)
                    event.setCancelled(true);
                break;
            case IRON_PICKAXE:
                if (data.getLevel() < 70)
                    event.setCancelled(true);
                break;
            case DIAMOND_PICKAXE:
                if (data.getLevel() < 90)
                    event.setCancelled(true);
                break;
        }
    }
}
