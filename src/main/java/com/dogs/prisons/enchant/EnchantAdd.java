package com.dogs.prisons.enchant;

import com.dogs.prisons.charm.Pickaxe;
import com.dogs.prisons.utils.EnchantUtils;
import com.dogs.prisons.utils.LoreUtil;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.Bukkit;
import org.bukkit.EntityEffect;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class EnchantAdd {

    public void addRandomEnchant(Player player) {
        Enchant enchant = (Enchant) EnchantUtils.pickRandom(Enchant.ENCHANTS.toArray());
        int successRate = new Random().nextInt(100);
        EnchantOrb orb = new EnchantOrb(1, enchant, successRate, 100 - successRate);
        net.minecraft.server.v1_8_R3.ItemStack stack = CraftItemStack.asNMSCopy(player.getItemInHand());
        NBTTagCompound tag = stack.getTag() != null ? stack.getTag() : new NBTTagCompound();
        if (tag.getInt(enchant.getName()) >= orb.getEnchant().getMaxLevel()) return;
        if (orb.getEnchant().getChance() > successRate) {
            ItemStack itemStack = player.getItemInHand();
            ItemMeta meta = itemStack.getItemMeta();
            List<String> lorea;
            if (meta.hasLore()) lorea = meta.getLore();
            else lorea = new ArrayList<>();
            for (int i = 0; i < lorea.size(); i++) {
                String line = lorea.get(i);
                if (LoreUtil.stripColor(line).startsWith(enchant.getName())) {
                    String[] number = line.split(" ");
                    int value = EnchantUtils.romanNumeralToInt(number[1]);
                    int value2 = Integer.parseInt(number[1]);
                    lorea.set(i, enchant.getName() + " " + (value2 + 1));
                    meta.setLore(lorea);
                    itemStack.setItemMeta(meta);
                    setEnchants(itemStack);
                    Pickaxe pickaxe = new Pickaxe(itemStack);
                    lorea.add("Charm (" + pickaxe.getCharm() + "/" + pickaxe.getCharmMax() + ")");
                    meta.setLore(lorea);
                    itemStack.setItemMeta(meta);
                    player.playEffect(EntityEffect.VILLAGER_HAPPY);
                    player.playSound(player.getLocation(), Sound.ARROW_HIT, 1, 1);
                    return;
                }
            }
            lorea.add(enchant.getName() + " " + orb.getLevel());
            meta.setLore(lorea);
            itemStack.setItemMeta(meta);
            setEnchants(itemStack);
            player.playEffect(EntityEffect.VILLAGER_HAPPY);
            player.playSound(player.getLocation(), Sound.ARROW_HIT, 1, 1);
        }
    }

    public void setEnchants(ItemStack itemStack) {
        ItemMeta meta = itemStack.getItemMeta();
        List<String> lorea;
        if (meta.hasLore()) lorea = meta.getLore();
        else lorea = new ArrayList<>();
        Map<Enchant, Integer> enchantIntegerMap = Enchant.getEnchants(itemStack);
        List<Enchant> enchants = new ArrayList<>();
        for (int i = 0; i < Enchant.ENCHANTS.size(); i++) {
            if (!enchantIntegerMap.isEmpty()) {
                if (!lorea.isEmpty()) {
                    String line = lorea.get(0);
                    String[] name = line.split(" ");
                    if (Enchant.getEnchantByName(name[0]) != null) {
                        enchants.add(Enchant.getEnchantByName(name[0]));
                    }
                    lorea.remove(0);
                    meta.setLore(lorea);
                    itemStack.setItemMeta(meta);
                }
            }
        }
        for (Enchant enchant : enchants) {
            lorea.add(enchant.getName() + " " + enchantIntegerMap.get(enchant));
            meta.setLore(lorea);
            itemStack.setItemMeta(meta);
        }
    }
}
