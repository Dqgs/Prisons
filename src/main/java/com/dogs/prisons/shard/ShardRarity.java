package com.dogs.prisons.shard;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum ShardRarity {
    SIMPLE(ChatColor.BLUE,"Simple Shard",
            new ItemStack(Material.COAL_ORE, 48),
            new ItemStack(Material.COAL, 32),
            new ItemStack(Material.WOOD_SWORD, 1),
            new ItemStack(Material.COOKIE, 5)),
    UNCOMMON(ChatColor.BLUE,"Uncommon Shard",
            new ItemStack(Material.IRON_ORE, 48),
            new ItemStack(Material.IRON_INGOT, 32),
            new ItemStack(Material.STONE_SWORD, 1),
            new ItemStack(Material.CARROT, 5)),
    RARE(ChatColor.BLUE,"Rare Shard",
            new ItemStack(Material.COAL_ORE, 48),
            new ItemStack(Material.COAL, 32),
            new ItemStack(Material.WOOD_SWORD, 1),
            new ItemStack(Material.COOKIE, 5)),
    EPIC(ChatColor.BLUE,"Epic Shard",
            new ItemStack(Material.COAL_ORE, 48),
            new ItemStack(Material.COAL, 32),
            new ItemStack(Material.WOOD_SWORD, 1),
            new ItemStack(Material.COOKIE, 5)),
    LEGENDARY(ChatColor.BLUE,"Legendary Shard",
            new ItemStack(Material.COAL_ORE, 48),
            new ItemStack(Material.COAL, 32),
            new ItemStack(Material.WOOD_SWORD, 1),
            new ItemStack(Material.COOKIE, 5)),
    MYTHIC(ChatColor.BLUE,"Mythic Shard",
            new ItemStack(Material.COAL_ORE, 48),
            new ItemStack(Material.COAL, 32),
            new ItemStack(Material.WOOD_SWORD, 1),
            new ItemStack(Material.COOKIE, 5));

    ChatColor color;
    String name;
    List<ItemStack> items = new ArrayList<>();
    ShardRarity(ChatColor color, String name, ItemStack... itemStack){
        this.color = color;
        this.name = name;
        Collections.addAll(items, itemStack);
    }
    public ChatColor getColor(){
        return color;
    }

    public String getName(){
        return name;
    }
    public List<ItemStack> getItemStack() {
        return items;
    }

}
