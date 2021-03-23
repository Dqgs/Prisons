package com.dogs.prisons.events;

import com.dogs.prisons.Prisons;
import com.dogs.prisons.charm.Pickaxe;
import com.dogs.prisons.data.DataPlayer;
import com.dogs.prisons.filemanager.LevelCost;
import com.dogs.prisons.filemanager.PlayerData;
import com.dogs.prisons.scoreboard.StatsScoreBoard;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class JoinQuitEvent implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent event){
        Prisons.getInstance().getDataManager().add(event.getPlayer());
        PlayerData.loadPlayer(event.getPlayer());
        DataPlayer data = Prisons.getInstance().getDataManager().getDataPlayer(event.getPlayer());;
        for (int i = 0; i < LevelCost.getMaxLevel(); i++){
            if (data.getXp() >= LevelCost.getLevelCost(i)){
                data.setLevel(i, false);
            }
        }
        ItemStack itemStack = new ItemStack(Material.WOOD_PICKAXE);
        Pickaxe pickaxe = new Pickaxe(itemStack);
        pickaxe.setCharm(4900);
        data.player.getInventory().addItem(pickaxe.itemStack);
        new StatsScoreBoard(data);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onLeave(PlayerQuitEvent event){
        PlayerData.savePlayer(event.getPlayer());
        Prisons.getInstance().getDataManager().remove(event.getPlayer());
    }
}