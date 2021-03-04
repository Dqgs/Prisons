package com.dogs.prisons.events;

import com.dogs.prisons.charm.Pickaxe;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class JoinQuitEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        ItemStack itemStack = new ItemStack(Material.WOOD_PICKAXE);
        Pickaxe pickaxe = new Pickaxe(itemStack);
        event.getPlayer().getInventory().addItem(pickaxe.itemStack);
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event){

    }
}
