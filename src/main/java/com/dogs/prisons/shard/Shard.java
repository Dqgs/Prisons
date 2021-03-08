package com.dogs.prisons.shard;

import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Shard {

    public ItemStack item;
    ShardRarity shardRarity;

    public Shard(ItemStack itemStack){
        this.item = itemStack;
        net.minecraft.server.v1_8_R3.ItemStack stack = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound tag = stack.getTag() != null ? stack.getTag() : new NBTTagCompound();
        shardRarity = ShardRarity.valueOf(tag.getString("shardRarity"));
    }

    public Shard(ShardRarity shardRarity){
        ItemStack itemStack = new ItemStack(Material.PRISMARINE_SHARD);
        net.minecraft.server.v1_8_R3.ItemStack stack = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound tag = stack.getTag() != null ? stack.getTag() : new NBTTagCompound();
        tag.setString("shardRarity", shardRarity.toString());
        stack.setTag(tag);
        itemStack = CraftItemStack.asCraftMirror(stack);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(shardRarity.getColor() + shardRarity.getName());
        itemStack.setItemMeta(meta);
        this.item = itemStack;
        this.shardRarity = shardRarity;
    }
}
