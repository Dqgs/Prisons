package com.dogs.prisons;

import com.dogs.prisons.charm.CharmChecker;
import com.dogs.prisons.data.DataManager;
import com.dogs.prisons.enchant.EnchBlockBreak;
import com.dogs.prisons.enchant.pickaxe.SuperBreaker;
import com.dogs.prisons.events.JoinQuitEvent;
import com.dogs.prisons.mining.StrengthChanger;
import com.dogs.prisons.wormhole.WormHoleEnchant;
import net.minecraft.server.v1_8_R3.Blocks;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Prisons extends JavaPlugin {

    private static Prisons instance;
    private DataManager dataManager;

    @Override
    public void onEnable(){
        instance = this;
        dataManager = new DataManager();
        buildEnchants();
        events();
        commands();
        StrengthChanger.setStrength(Blocks.COAL_BLOCK, 1.4f);
    }
    @Override
    public void onDisable(){

    }

    private void buildEnchants() {
        new SuperBreaker();
    }

    public void events(){
        PluginManager server = Bukkit.getServer().getPluginManager();
        server.registerEvents(new StrengthChanger(this), this);
        server.registerEvents(new EnchBlockBreak(), this);
        server.registerEvents(new JoinQuitEvent(), this);
    }
    public void commands(){
        getCommand("Enchant").setExecutor(new WormHoleEnchant());
        getCommand("charm").setExecutor(new CharmChecker());
    }

    public static Prisons getInstance() {
        return instance;
    }
    public DataManager getDataManager(){
        return dataManager;
    }
}
