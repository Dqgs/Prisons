package com.dogs.prisons.charm;

import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Random;

public class Charm {

    public ItemStack itemStack;
    int amount, random;

    public Charm(ItemStack itemStack) {
        if (itemStack != null){
            this.itemStack = itemStack;
            net.minecraft.server.v1_8_R3.ItemStack stack = CraftItemStack.asNMSCopy(itemStack);
            NBTTagCompound tag = stack.getTag() != null ? stack.getTag() : new NBTTagCompound();
            amount = tag.getInt("charm");
        }
    }


    public ItemStack createNewEnergy(int amount){
        if (amount != 0) {
            ItemStack itemStack = new ItemStack(Material.INK_SACK, 1, (short) 13);
            ItemMeta meta = itemStack.getItemMeta();
            meta.setDisplayName("Charm: " + amount);
            itemStack.setItemMeta(meta);
            net.minecraft.server.v1_8_R3.ItemStack stack = CraftItemStack.asNMSCopy(itemStack);
            NBTTagCompound tag = stack.getTag() != null ? stack.getTag() : new NBTTagCompound();
            tag.setInt("charm", amount);
            int random = new Random().nextInt(999_999_999);
            tag.setInt("randomStackNumber", random);
            stack.setTag(tag);
            this.random = random;
            this.amount = amount;
            this.itemStack = CraftItemStack.asCraftMirror(stack);
            Bukkit.broadcastMessage(tag.toString());
            return this.itemStack;
        }
        return null;
    }

    public int getRandom(){
        return random;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName("Charm: " + amount);
        itemStack.setItemMeta(meta);

        net.minecraft.server.v1_8_R3.ItemStack stack = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound tag = stack.getTag() != null ? stack.getTag() : new NBTTagCompound();
        tag.setInt("charm", amount);
        stack.setTag(tag);
        this.itemStack = CraftItemStack.asCraftMirror(stack);
        this.amount = amount;
    }
}
