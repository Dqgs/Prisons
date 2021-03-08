package com.dogs.prisons.filemanager;

import com.dogs.prisons.Prisons;
import com.dogs.prisons.data.DataPlayer;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class PlayerData {

    private static File file;

    private static FileConfiguration datafile;

    public static void setup() {
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("Prisons").getDataFolder(), "PlayerData.yml");
        if (!file.exists())
            try {
                System.out.println("[Prisons] Creating a new PlayerData.yml");
                file.createNewFile();
                System.out.println("[Prisons] PlayerData.yml Has been Created");
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
            System.out.println("[Prisons] PlayerData.yml Has been Saved");
        } catch (IOException e) {
            System.out.println("[Prisons] Couldn't save PlayerData.yml");
        }
    }

    public static void reload() {
        Bukkit.broadcastMessage("[Prisons] Reloading PlayerData.yml");
        datafile = YamlConfiguration.loadConfiguration(file);
        System.out.println("[Prisons] Completed reloading PlayerData.yml");
    }

    public static void loadPlayer(Player player){
        if (!PlayerData.get().contains("player." + player.getUniqueId() + ".XP")){
            PlayerData.get().set("player." + player.getUniqueId() + ".XP", 0);
        } else {
            int XP = PlayerData.get().getInt("player." + player.getUniqueId() + ".XP");
            DataPlayer data = Prisons.getInstance().getDataManager().getDataPlayer(player);
            data.setXp(XP);
        }
    }

    public static void savePlayer(Player player){
        DataPlayer data = Prisons.getInstance().getDataManager().getDataPlayer(player);
        PlayerData.get().set("player." + player.getUniqueId() + ".XP", data.getXp());
        PlayerData.save();
    }
}
