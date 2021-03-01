package com.dogs.prisons;

import com.dogs.prisons.enchant.EnchBlockBreak;
import com.dogs.prisons.enchant.Enchant;
import com.dogs.prisons.enchant.EnchantOrb;
import com.dogs.prisons.enchant.pickaxe.SuperBreaker;
import com.dogs.prisons.mining.StrengthChanger;
import com.dogs.prisons.wormhole.WormHoleEnchant;
import net.minecraft.server.v1_8_R3.Blocks;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Prisons extends JavaPlugin {

    @Override
    public void onEnable(){
        buildEnchants();
        StrengthChanger strengthChanger = new StrengthChanger(this);
        Bukkit.getServer().getPluginManager().registerEvents(strengthChanger, this);
        Bukkit.getServer().getPluginManager().registerEvents(new EnchBlockBreak(), this);
        StrengthChanger.setStrength(Blocks.COAL_BLOCK, 0.2f);
        getCommand("Enchant").setExecutor(new WormHoleEnchant());

    }
    @Override
    public void onDisable(){

    }

    private void buildEnchants() {
        new SuperBreaker();
    }
}
