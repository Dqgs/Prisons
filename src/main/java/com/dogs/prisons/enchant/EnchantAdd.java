package com.dogs.prisons.enchant;

import com.dogs.prisons.utils.EnchantUtils;
import com.dogs.prisons.utils.LoreUtil;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;
import org.bukkit.Bukkit;
import org.bukkit.EntityEffect;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftItem;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnchantAdd {

    public void addRandomEnchant(Player player) {
        List<String> lorea;
        if (player.getItemInHand().getItemMeta().hasLore()) lorea = player.getItemInHand().getItemMeta().getLore();
        else lorea = new ArrayList<>();
        Enchant enchant = (Enchant) EnchantUtils.pickRandom(Enchant.enchants.toArray());
        int successRate = new Random().nextInt(100);
        EnchantOrb orb = new EnchantOrb(1, enchant, successRate, 100 - successRate);
        net.minecraft.server.v1_8_R3.ItemStack stack = CraftItemStack.asNMSCopy(player.getItemInHand());
        NBTTagCompound tag = stack.getTag() != null ? stack.getTag() : new NBTTagCompound();
        if (tag.getInt(enchant.getName()) >= orb.getEnchant().getMaxLevel()) return;
        if (orb.getEnchant().getChance() > successRate) {

            player.playEffect(EntityEffect.VILLAGER_HAPPY);
            player.playSound(player.getLocation(), Sound.ARROW_HIT, 1, 1);
        }
    }

    public boolean isMaxLevel(ItemStack itemStack, EnchantOrb orb){
        net.minecraft.server.v1_8_R3.ItemStack stack = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound tag = stack.getTag();
        int level = tag.getInt(orb.getEnchant().getName());
        if (level <= orb.getEnchant().getMaxLevel()){
            tag.setInt(orb.getEnchant().getName(), level+1);
            Bukkit.broadcastMessage("" + tag.getInt(orb.getEnchant().getName()));
        }
        return false;
    }
}
