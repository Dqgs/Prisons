package com.dogs.prisons;

import com.dogs.prisons.charm.CharmChecker;
import com.dogs.prisons.commands.admin.ShardGive;
import com.dogs.prisons.commands.player.Feed;
import com.dogs.prisons.data.DataManager;
import com.dogs.prisons.enchant.EnchBlockBreak;
import com.dogs.prisons.enchant.pickaxe.SuperBreaker;
import com.dogs.prisons.enchant.pickaxe.Test1;
import com.dogs.prisons.enchant.pickaxe.Test2;
import com.dogs.prisons.events.JoinQuitEvent;
import com.dogs.prisons.events.BlockBreak;
import com.dogs.prisons.events.ItemCraft;
import com.dogs.prisons.filemanager.BlockRegenFile;
import com.dogs.prisons.filemanager.LevelCost;
import com.dogs.prisons.filemanager.PlayerData;
import com.dogs.prisons.mining.StrengthChanger;
import com.dogs.prisons.shard.ShardOpen;
import com.dogs.prisons.wormhole.WormHoleEnchant;
import net.milkbowl.vault.economy.Economy;
import net.minecraft.server.v1_8_R3.Blocks;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;

public final class Prisons extends JavaPlugin {

    private static Prisons instance;
    private DataManager dataManager;
    private static Economy econ = null;

    @Override
    public void onEnable(){
        instance = this;
        dataManager = new DataManager();
        if (!setupEconomy() )
            getServer().getPluginManager().disablePlugin(this);
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

        BlockRegenFile.setup();
        BlockRegenFile.get().addDefault("M_X_Y_Z", "");
        BlockRegenFile.get().options().copyDefaults(true);
        BlockRegenFile.save();

        for (Player player : Bukkit.getOnlinePlayers()){
            PlayerData.loadPlayer(player);
        }
        setAllBlock();
    }
    @Override
    public void onDisable(){
        for (Player player : Bukkit.getOnlinePlayers()){
            PlayerData.savePlayer(player);
        }
    }
    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
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
        server.registerEvents(new ShardOpen(), this);
        server.registerEvents(new BlockBreak(), this);
        server.registerEvents(new ItemCraft(), this);
    }
    public void commands(){
        getCommand("Enchant").setExecutor(new WormHoleEnchant());
        getCommand("charm").setExecutor(new CharmChecker());
        getCommand("shard").setExecutor(new ShardGive());
        getCommand("feed").setExecutor(new Feed());
    }

    public static Prisons getInstance() {
        return instance;
    }
    public DataManager getDataManager(){
        return dataManager;
    }

    public void setAllBlock(){
        if (BlockRegenFile.getMissingBlocks() == null){
            System.out.println("There are no missing blocks!");
            return;
        }
        for(Map.Entry<Location, Material> entry : BlockRegenFile.getMissingBlocks().entrySet()){
            Location location = entry.getKey();
            Material material = entry.getValue();
            World world = Bukkit.getWorld("world");
            world.getBlockAt(location).setType(material);
        }
    }

    public static Economy getEconomy() {
        return econ;
    }
}
