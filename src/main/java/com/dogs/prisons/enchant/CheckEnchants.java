package com.dogs.prisons.enchant;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CheckEnchants implements Runnable{

    @Override
    public void run() {
        for(Player p : Bukkit.getOnlinePlayers()) {
            //Check if the armor or the item held contains any enchants
            if(Enchant.getEnchantsOnPlayer(p) != null) {
                //The players armor/item contains an enchant
                for(Enchant ench : Enchant.getEnchantsOnPlayer(p).keySet()) {
                    if(ench.isActive()) {
                     //   ench.alwaysActive(p);
                    }
                }

            }
        }
    }

}
