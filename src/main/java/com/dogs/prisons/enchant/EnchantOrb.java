package com.dogs.prisons.enchant;

import com.dogs.prisons.utils.EnchantUtils;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class EnchantOrb {

    private Rarity rarity;
    private final ItemStack book;
    private int level;
    private Enchant enchant;
    private int success;
    private int destroy;

    public EnchantOrb(int level, Enchant enchant, int success, int destroy) {
        this.level = level;
        this.enchant = enchant;
        this.success = success;
        this.destroy = destroy;
        this.rarity = enchant.getRarity();
        book = rarity.getItem();

        //Make the item using the following variables (Reference Image: http://prntscr.com/elt45o)
        ItemMeta meta = book.getItemMeta();
        meta.setDisplayName(ChatColor.RESET + "" + enchant.getRarity().getRarityColor() + "" + ChatColor.UNDERLINE + enchant.getName() + " " + EnchantUtils.intToRomanNumeral(level));
        List<String> lore = new ArrayList<>();
        lore.add(0, ChatColor.GREEN + "" + success + "% Success Rate");
        lore.add(1, ChatColor.RED + "" + destroy + "% Destroy Rate");
        //Format the description so that it doesn't go off page
        lore.addAll(EnchantUtils.loreLineFormat(enchant.getDescription(), ChatColor.YELLOW));
        //If the enchant can go on more than 1 thing
        if (enchant.getItemSet().length > 1) {
            String items = "";
            //Add all the items the enchant can be added to, to one string seperated with a ','
            for (int i = 0; i < enchant.getItemSet().length; i++) {
                items += enchant.getItemSet()[i].toString() + ", ";
            } //If there is a ', ' at the end then remove it
            if (items.endsWith(", ")) {
                items = items.substring(0, items.length() - 2);
            }
            //Turn the first enum from all caps to first letter capital
            lore.add(ChatColor.GRAY + EnchantUtils.camelCase(items).replaceAll("_", " ") + " Enchantment");
        } else {
            //Turn the enum from all caps to first letter capital
            lore.add(ChatColor.GRAY + EnchantUtils.camelCase(enchant.getItemSet()[0].toString()).replaceAll("_", " ") + "s Enchantment");
        }

        lore.add(ChatColor.DARK_AQUA + "Max Level: " + ChatColor.YELLOW + enchant.getMaxLevel());
        meta.setLore(lore);
        book.setItemMeta(meta);
    }
    public static EnchantOrb getEnchantBook(ItemStack item) {
        int success;
        int destroy;
        if (Enchant.getEnchantByName(ChatColor.stripColor(item.getItemMeta().getDisplayName().split(" ")[0])) == null)
            return null;
        List<String> lore = item.getItemMeta().getLore();
        if (lore == null) return null;
        if (lore.get(0).split("%")[0] == null || lore.get(1).split("%")[0] == null) return null;
        try {
            success = Integer.parseInt(ChatColor.stripColor(lore.get(0).split("%")[0]));
            destroy = Integer.parseInt(ChatColor.stripColor(lore.get(1).split("%")[0]));
        } catch (Exception e) {
            return null;
        }

        return new EnchantOrb(EnchantUtils.romanNumeralToInt(ChatColor.stripColor(item.getItemMeta().getDisplayName().split(" ")[1])), Enchant.getEnchantByName(ChatColor.stripColor(item.getItemMeta().getDisplayName().split(" ")[0])), success, destroy);
    }


    public ItemStack getBook() {
        return book;
    }

    public int getLevel() {
        return level;
    }

    public Enchant getEnchant() {
        return enchant;
    }

    public int getSuccess() {
        return success;
    }

    public int getDestroy() {
        return destroy;
    }

    public Rarity getRarity() {
        return rarity;
    }
}
