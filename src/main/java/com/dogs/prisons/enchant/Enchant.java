package com.dogs.prisons.enchant;

import com.dogs.prisons.utils.EnchantUtils;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Enchant {


    public static List<Enchant> enchants = new ArrayList<>();

    private String name; //Enchants name
    private int maxLevel; //Enchant max level
    private Rarity rarity; //The enchants rarity
    private ItemSet[] itemSet; //Items the enchant can be applied to
    private String description; //What the enchant does
    private int chance; //The chance of the enchant activating
    private boolean isActive; //True if the enchant is always active

    public Enchant(String name, String description, int maxLevel, Rarity rarity, ItemSet[] itemSet, int chance, boolean isActive){
        this.name = name;
        this.description =description;
        this.maxLevel = maxLevel;
        this.rarity = rarity;
        this.itemSet = itemSet;
        this.chance = chance;
        this.isActive = isActive;

        enchants.add(this);
    }

    public static Enchant getEnchantByName(String name) {
        for (Enchant enchant : enchants) {
            if (enchant.getName().equalsIgnoreCase(name)) {
                return enchant;
            }
        }
        return null;
    }

    public static Map<Enchant, Integer> getEnchants(ItemStack... items) {
        Map<Enchant, Integer> enchants = new HashMap<>();
        for (ItemStack item : items) {
            if (item == null) continue;
            if (item.hasItemMeta()) {
                if (!item.getItemMeta().hasLore()) continue;
                List<String> lore = item.getItemMeta().getLore();
                for (String temp : lore) {
                    String loreLine = ChatColor.stripColor(temp).split(" ")[0];
                    if (getEnchantByName(loreLine) != null) {
                        enchants.put(getEnchantByName(loreLine), EnchantUtils.romanNumeralToInt(ChatColor.stripColor(temp).split(" ")[1]));
                    }
                }
            }
        }
        if (enchants.isEmpty()) return null;
        return enchants;
    }

    public static Map<Enchant, Integer> getHeldEnchants(Player player) {
        return getEnchants(player.getItemInHand());
    }

    public static Map<Enchant, Integer> getArmorEnchants(Player player) {
        return getEnchants(player.getEquipment().getArmorContents());
    }

    //Gets the enchants on the players armour and currently held item
    public static Map<Enchant, Integer> getEnchantsOnPlayer(Player player) {
        return getEnchants(player.getItemInHand(), player.getInventory().getHelmet(), player.getInventory().getChestplate(), player.getInventory().getLeggings(), player.getInventory().getBoots());
    }

    public void playerBreakBlock(BlockBreakEvent e) {}

    public String getName() {
        return name;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public ItemSet[] getItemSet() {
        return itemSet;
    }

    public String getDescription() {
        return description;
    }

    public int getChance() {
        return chance;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

}
