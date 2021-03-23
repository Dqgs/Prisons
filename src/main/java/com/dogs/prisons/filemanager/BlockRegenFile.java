package com.dogs.prisons.filemanager;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class BlockRegenFile {
    private static File file;

    private static FileConfiguration datafile;

    public static void setup() {
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("Prisons").getDataFolder(), "BlockRegen.yml");
        if (!file.exists())
            try {
                System.out.println("[Prisons] Creating BlockRegen.yml");
                file.createNewFile();
                System.out.println("[Prisons] BlockRegen.yml Has been Created");
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
        } catch (IOException e) {
            System.out.println("[Prisons] Couldn't save BlockRegen.yml \n" + e);
        }
    }

    public static void saveBlock(Block block) {
        BlockRegenFile.get().set("X_Y_Z." + block.getLocation().getBlockX() + "_" +
                        block.getLocation().getBlockY() + "_" +
                        block.getLocation().getBlockZ() + ".blockType",
                block.getType().toString());
        BlockRegenFile.save();
    }
    public static void removeBlock(Block block){
        BlockRegenFile.get().set("X_Y_Z." + block.getLocation().getBlockX() + "_" +
                        block.getLocation().getBlockY() + "_" +
                        block.getLocation().getBlockZ() + ".blockType",
                null);
        BlockRegenFile.get().set("X_Y_Z." + block.getLocation().getBlockX() + "_" +
                        block.getLocation().getBlockY() + "_" +
                        block.getLocation().getBlockZ(),
                null);
        BlockRegenFile.save();
    }

    public static HashMap<Location, Material> getMissingBlocks() {
        try {
            ConfigurationSection locationSection = BlockRegenFile.get().getConfigurationSection("X_Y_Z");
            HashMap<Location, Material> list = new HashMap<>();
            if (locationSection != null) {
                for (String lKey : locationSection.getKeys(false)) {
                    locationSection.get(lKey);
                    String[] locationData = lKey.split("_");
                    double x = Double.parseDouble(locationData[0]);
                    double y = Double.parseDouble(locationData[1]);
                    double z = Double.parseDouble(locationData[2]);

                    Material material = Material.getMaterial(BlockRegenFile.get().getString("X_Y_Z." + lKey + ".blockType"));
                    Location location = new Location(Bukkit.getWorld("world"), x, y, z);
                    list.put(location, material);
                }
            }
            return list;
        } catch (NumberFormatException ex) {
            System.out.println(ex);
        }
        return null;
    }
}
