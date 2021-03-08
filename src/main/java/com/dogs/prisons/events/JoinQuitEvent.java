package com.dogs.prisons.events;

import com.dogs.prisons.Prisons;
import com.dogs.prisons.filemanager.PlayerData;
import com.dogs.prisons.shard.Shard;
import com.dogs.prisons.shard.ShardRarity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinQuitEvent implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent event){
        Prisons.getInstance().getDataManager().add(event.getPlayer());
        PlayerData.loadPlayer(event.getPlayer());
        Shard shard = new Shard(ShardRarity.LEGENDARY);
        event.getPlayer().getInventory().addItem(shard.item);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onLeave(PlayerQuitEvent event){
        PlayerData.savePlayer(event.getPlayer());
        Prisons.getInstance().getDataManager().remove(event.getPlayer());
    }
}
