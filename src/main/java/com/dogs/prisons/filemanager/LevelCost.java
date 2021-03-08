package com.dogs.prisons.filemanager;

import com.dogs.prisons.Prisons;
import com.dogs.prisons.data.DataPlayer;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class LevelCost {
    private static File file;

    private static FileConfiguration datafile;

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

    public static void reload() {
        Bukkit.broadcastMessage("[Prisons] Reloading LevelCost.yml");
        datafile = YamlConfiguration.loadConfiguration(file);
        System.out.println("[Prisons] Completed reloading LevelCost.yml");
    }

    public static int getLevelCost(int level){
        if (LevelCost.get().contains("level." + level + ".XP")) {
            return PlayerData.get().getInt("player." + level + ".XP");
        } else {
            LevelCost.get().set("level." + level + ".XP", 5);
            LevelCost.save();
        }
        return 0;
    }
}
