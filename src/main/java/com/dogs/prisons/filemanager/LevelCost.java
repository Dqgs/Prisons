package com.dogs.prisons.filemanager;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class LevelCost {
    private static File file;

    private static FileConfiguration datafile;

    private static int maxLevel;

    public static void setup() {
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("Prisons").getDataFolder(), "LevelCost.yml");
        if (!file.exists())
            try {
                System.out.println("[Prisons] Creating LevelCost.yml");
                file.createNewFile();
                System.out.println("[Prisons] LevelCost.yml Has been Created");
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
        datafile = YamlConfiguration.loadConfiguration(file);
            maxLevel = maxLevel();
    }

    public static FileConfiguration get() {
        return datafile;
    }

    public static void save() {
        try {
            datafile.save(file);
            System.out.println("[Prisons] LevelCost.yml Has been Saved");
        } catch (IOException e) {
            System.out.println("[Prisons] Couldn't save LevelCost.yml");
        }
    }

    public static int maxLevel() {
        int max = 0;
        try {
            ConfigurationSection levels = LevelCost.get().getConfigurationSection("level");
            if (levels != null){
                for (String lLevel : levels.getKeys(false)){
                    levels.get(lLevel);
                    max = Integer.parseInt(lLevel);
                }
            }
        } catch (NumberFormatException e){
            System.out.println(e);
        }
        return max;
    }

    public static int getLevelCost(int level){
        if (LevelCost.get().contains("level." + level + ".XP")) {
            return PlayerData.get().getInt("player." + level + ".XP");
        }
        return -1;
    }

    public static int getMaxLevel(){
        return maxLevel;
    }
}
