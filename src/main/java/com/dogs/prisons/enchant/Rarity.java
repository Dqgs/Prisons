package com.dogs.prisons.enchant;

import org.bukkit.ChatColor;

public enum Rarity {
    SIMPLE(ChatColor.WHITE),
    UNCOMMON(ChatColor.GREEN),
    RARE(ChatColor.BLUE),
    EPIC(ChatColor.DARK_PURPLE),
    LEGENDARY(ChatColor.GOLD),
    MYTHIC(ChatColor.RED);

    ChatColor rarityColor;

    Rarity(ChatColor rarityColor){
        this.rarityColor = rarityColor;
    }

    public ChatColor getRarityColor(){
        return rarityColor == null ? ChatColor.GRAY : rarityColor;
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
