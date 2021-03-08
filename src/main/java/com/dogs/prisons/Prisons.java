package com.dogs.prisons;

import com.dogs.prisons.charm.CharmChecker;
import com.dogs.prisons.data.DataManager;
import com.dogs.prisons.enchant.EnchBlockBreak;
import com.dogs.prisons.enchant.pickaxe.SuperBreaker;
import com.dogs.prisons.enchant.pickaxe.Test1;
import com.dogs.prisons.enchant.pickaxe.Test2;
import com.dogs.prisons.events.JoinQuitEvent;
import com.dogs.prisons.filemanager.LevelCost;
import com.dogs.prisons.filemanager.PlayerData;
import com.dogs.prisons.mining.BlockBreak;
import com.dogs.prisons.mining.StrengthChanger;
import com.dogs.prisons.shard.ShardOpen;
import com.dogs.prisons.wormhole.WormHoleEnchant;
import net.minecraft.server.v1_8_R3.Blocks;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
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

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        PlayerData.setup();
        PlayerData.get().addDefault("player", "UUID");
        PlayerData.get().options().copyDefaults(true);
        PlayerData.save();

        LevelCost.setup();
        LevelCost.get().addDefault("level", "XP");
        LevelCost.get().options().copyDefaults(true);
        LevelCost.save();

        for (Player player : Bukkit.getOnlinePlayers()){
            PlayerData.loadPlayer(player);
        }

    }
    @Override
    public void onDisable(){
        for (Player player : Bukkit.getOnlinePlayers()){
            PlayerData.savePlayer(player);
        }
    }

    private void buildEnchants() {
        new SuperBreaker();
        new Test1();
        new Test2();
    }

    public void events(){
        PluginManager server = Bukkit.getServer().getPluginManager();
        server.registerEvents(new StrengthChanger(this), this);
        server.registerEvents(new EnchBlockBreak(), this);
        server.registerEvents(new JoinQuitEvent(), this);
        server.registerEvents(new BlockBreak(), this);
        server.registerEvents(new ShardOpen(), this);
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
