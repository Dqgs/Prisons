package com.dogs.prisons.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;

public class ItemCraft implements Listener{

    @EventHandler
    public void onStartCrafting(CraftItemEvent event){
        event.setCancelled(true);
    }
}
