package com.dogs.prisons.utils;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class LoreUtil {


    public static void incrementLines(ItemStack item, String identifier) {
        ItemMeta meta = item.getItemMeta();
        if (!meta.hasLore()) {
            return;
        }
        List<String> lore = meta.getLore();
        incrementLines(identifier, lore);
        meta.setLore(lore);
        item.setItemMeta(meta);
    }
    public static void incrementLines(String identifier, List<String> lore) {
        // For every line in lore
        for (int index = 0; index < lore.size(); index++) {
            String line = lore.get(index);
            // Checks if line starts with identifier (Coal for example)
            if (stripColor(line).startsWith(identifier)) {
                // 1)Strip color 2)replace all but 0-9 3)parse to int
                int value = Integer.parseInt(extractNumber(stripColor(line)));
                // set a new lore line that replaces the old one
                lore.set(index, "§6" + identifier + ": §7" + ++value);
            }
        }
    }
    // Replaces everything but 0-9
    public static String extractNumber(String input) {
        return input.replaceAll("[^0-9]", "");
    }
    // Strips color codes
    public static String stripColor(String input) {
        return ChatColor.stripColor(input);
    }
}
