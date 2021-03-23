package com.dogs.prisons.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class MobSpawn implements Listener {

    @EventHandler
    public void onSpawnEvent(EntitySpawnEvent event){
        if(event.getEntity().getType() != EntityType.PLAYER){
            event.setCancelled(true);
        }
    }
}
