package com.dogs.prisons.enchant;

import com.dogs.prisons.utils.EnchantUtils;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.libs.org.ibex.nestedvm.util.Sort;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public abstract class Enchant implements Comparable<Enchant> {

    public static final Set<Enchant> ENCHANTS = new HashSet<>();

    private String name;
    private int maxLevel;
    private Rarity rarity;
    private ItemSet[] itemSet;
    private String description;
    private int id;
    private int chance;
    private boolean isActive;

    public Enchant(String name, String description, int maxLevel, Rarity rarity, ItemSet[] itemSet, int id, int chance, boolean isActive){
        this.name = name;
        this.description =description;
        this.maxLevel = maxLevel;
        this.rarity = rarity;
        this.itemSet = itemSet;
        this.id = id;
        this.chance = chance;
        this.isActive = isActive;

        ENCHANTS.add(this);
    }

    public static Enchant getEnchantByName(String name) {
        for (Enchant enchant : ENCHANTS) {
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
                        enchants.put(getEnchantByName(loreLine), Integer.parseInt(ChatColor.stripColor(temp).split(" ")[1]));
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

    public int getId(){
        return id;
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
