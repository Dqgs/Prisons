package com.dogs.prisons.charm;

import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Charm {

    public ItemStack itemStack;
    int amount;

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
            ItemStack itemStack = new ItemStack(Material.INK_SACK, 1, DyeColor.MAGENTA.getData());
            ItemMeta meta = itemStack.getItemMeta();
            meta.setDisplayName("Charm: " + amount);
            itemStack.setItemMeta(meta);
            this.itemStack = itemStack;
            return itemStack;
        }
        return null;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        net.minecraft.server.v1_8_R3.ItemStack stack = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound tag = stack.getTag() != null ? stack.getTag() : new NBTTagCompound();
        tag.setInt("charm", amount);
        stack.setTag(tag);
        this.amount = amount;
    }
}
