package com.dogs.prisons.enchant;

import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;
import org.bukkit.EntityEffect;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnchantAdd {

    public static void addEnchant(Player player, Enchant enchant){
        List<String> lorea;
        if (player.getItemInHand().getItemMeta().hasLore()) lorea = player.getItemInHand().getItemMeta().getLore();
        else lorea = new ArrayList<>();
    }
    public static void addRandomEnchant(Player player) {
        List<String> lorea;
        if (player.getItemInHand().getItemMeta().hasLore()) lorea = player.getItemInHand().getItemMeta().getLore();
        else lorea = new ArrayList<>();
        Enchant enchant = (Enchant) EnchantUtils.pickRandom(Enchant.enchants.toArray());
        int successRate = new Random().nextInt(100);
        EnchantOrb orb = new EnchantOrb(new Random().nextInt(enchant.getMaxLevel()) + 1, enchant, successRate, 100 - successRate);
        if (orb.getEnchant().getChance() > successRate) {
            lorea.add(orb.getEnchant().getRarity().getRarityColor() + orb.getEnchant().getName() + " " + EnchantUtils.intToRomanNumeral(orb.getLevel()));
            ItemMeta meta = player.getItemInHand().getItemMeta();
            meta.setLore(lorea);
            player.getItemInHand().setItemMeta(meta);
            net.minecraft.server.v1_8_R3.ItemStack nmsStack = CraftItemStack.asNMSCopy(player.getItemInHand());
            NBTTagCompound tag = null;
            if (!nmsStack.hasTag()) {
                tag = new NBTTagCompound();
                nmsStack.setTag(tag);
            }
            if (tag == null) tag = nmsStack.getTag();
            NBTTagList ench = new NBTTagList();
            tag.set("ench", ench);
            nmsStack.setTag(tag);
            player.getItemInHand().setType(CraftItemStack.asCraftMirror(nmsStack).getType());
            player.playEffect(EntityEffect.VILLAGER_HAPPY);
            player.playSound(player.getLocation(), Sound.ARROW_HIT, 1, 1);
        }
    }
}
