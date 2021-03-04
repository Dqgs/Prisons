package com.dogs.prisons.enchant;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

public enum Rarity {
    SIMPLE(ChatColor.WHITE, new ItemStack(Material.INK_SACK, 1, DyeColor.WHITE.getData())),
    UNCOMMON(ChatColor.GREEN, new ItemStack(Material.INK_SACK, 1, DyeColor.GREEN.getData())),
    RARE(ChatColor.BLUE, new ItemStack(Material.INK_SACK, 1, DyeColor.BLUE.getData())),
    EPIC(ChatColor.LIGHT_PURPLE, new ItemStack(Material.INK_SACK, 1, DyeColor.PINK.getData())),
    LEGENDARY(ChatColor.GOLD, new ItemStack(Material.INK_SACK, 1, DyeColor.ORANGE.getData())),
    MYTHIC(ChatColor.RED, new ItemStack(Material.INK_SACK, 1, DyeColor.RED.getData()));

    ChatColor rarityColor;
    ItemStack itemStack;

    Rarity(ChatColor rarityColor, ItemStack itemStack){
        this.rarityColor = rarityColor;
        this.itemStack = itemStack;
    }

    public ChatColor getRarityColor(){
        return rarityColor == null ? ChatColor.GRAY : rarityColor;
    }
    public ItemStack getItem(){
        return itemStack == null ? new ItemStack(Material.BOOK) : itemStack;
    }

    public static Rarity fromString(String rarity){
        switch(rarity.toLowerCase()) {
            case "simple": return Rarity.SIMPLE;
            case "uncommon": return Rarity.UNCOMMON;
            case "rare": return Rarity.RARE;
            case "epic": return Rarity.EPIC;
            case "legendary": return Rarity.LEGENDARY;
            case "mythic": return Rarity.MYTHIC;
            default: return null;
        }
    }
}
