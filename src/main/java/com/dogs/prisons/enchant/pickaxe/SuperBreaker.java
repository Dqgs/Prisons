package com.dogs.prisons.enchant.pickaxe;

import com.dogs.prisons.enchant.Enchant;
import com.dogs.prisons.enchant.ItemSet;
import com.dogs.prisons.enchant.Rarity;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class SuperBreaker extends Enchant implements Listener {

    public SuperBreaker() {
        super("SuperBreaker", "Breaks blocks faster", 2, Rarity.EPIC, new ItemSet[]{ItemSet.PICKAXE}, 1,60, true);
    }

    @Override
    public void playerBreakBlock(BlockBreakEvent event){
        Player player = event.getPlayer();
        int level = Enchant.getHeldEnchants(player).get(this);
        Bukkit.broadcastMessage("WORKED POGGIES");
    }

    @Override
    public int compareTo(Enchant o) {
        return this.getId() - o.getId();
    }
}
