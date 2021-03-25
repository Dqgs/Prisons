package com.dogs.prisons;

import com.dogs.prisons.charm.CharmChecker;
import com.dogs.prisons.commands.admin.ShardGive;
import com.dogs.prisons.commands.player.Extract;
import com.dogs.prisons.commands.player.Feed;
import com.dogs.prisons.data.DataManager;
import com.dogs.prisons.data.DataPlayer;
import com.dogs.prisons.enchant.EnchBlockBreak;
import com.dogs.prisons.enchant.pickaxe.SuperBreaker;
import com.dogs.prisons.enchant.pickaxe.Test1;
import com.dogs.prisons.enchant.pickaxe.Test2;
import com.dogs.prisons.events.*;
import com.dogs.prisons.filemanager.BlockRegenFile;
import com.dogs.prisons.filemanager.LevelCost;
import com.dogs.prisons.filemanager.PlayerData;
import com.dogs.prisons.mining.StrengthChanger;
import com.dogs.prisons.scoreboard.StatsScoreBoard;
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
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Map;

public final class Prisons extends JavaPlugin {

    private static Prisons instance;
    private DataManager dataManager;
    private static Economy econ;

    @Override
    public void onEnable(){
        instance = this;
        dataManager = new DataManager();
        if (!setupEconomy() )
            getServer().getPluginManager().disablePlugin(this);
        buildEnchants();
        events();
        commands();
        setBlockStrength();

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
        BlockRegenFile.get().addDefault("X_Y_Z", "");
        BlockRegenFile.get().options().copyDefaults(true);
        BlockRegenFile.save();

        for (Player player : Bukkit.getOnlinePlayers()){
            PlayerData.loadPlayer(player);
        }
        setAllBlock();
        updateScoreBoard();
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
        server.registerEvents(new BlockBreakType(), this);
        server.registerEvents(new BlockBreakPickaxe(), this);
        server.registerEvents(new ItemCraft(), this);
        server.registerEvents(new MobSpawn(), this);
    }

    public void commands(){
        getCommand("Enchant").setExecutor(new WormHoleEnchant());
        getCommand("charm").setExecutor(new CharmChecker());
        getCommand("shard").setExecutor(new ShardGive());
        getCommand("feed").setExecutor(new Feed());
        getCommand("extract").setExecutor(new Extract());
    }

    public void setBlockStrength(){
        StrengthChanger.setStrength(Blocks.COAL_ORE, 2f);
        StrengthChanger.setStrength(Blocks.IRON_ORE, 6f);
        StrengthChanger.setStrength(Blocks.LAPIS_ORE, 10f);
        StrengthChanger.setStrength(Blocks.REDSTONE_ORE, 14f);
        StrengthChanger.setStrength(Blocks.GOLD_ORE, 18f);
        StrengthChanger.setStrength(Blocks.DIAMOND_ORE, 22f);
        StrengthChanger.setStrength(Blocks.EMERALD_ORE, 25f);
    }

    public void updateScoreBoard(){
        new BukkitRunnable(){
            @Override
            public void run(){
                for (Player player : Bukkit.getOnlinePlayers()){
                    DataPlayer data = dataManager.getDataPlayer(player);
                    StatsScoreBoard scoreBoard = new StatsScoreBoard(data);
                    scoreBoard.updateFullScoreBoard();
                }
            }
        }.runTaskTimer(this, 20, 20);
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
