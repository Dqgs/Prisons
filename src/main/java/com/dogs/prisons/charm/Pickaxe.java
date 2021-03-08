package com.dogs.prisons.charm;

import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Pickaxe {

    public ItemStack itemStack;
    CharmCalculator calculator;
    int level, charm, charmMax;

    public Pickaxe(ItemStack itemStack) {
        this.itemStack = itemStack;
        calculator = new CharmCalculator(this, itemStack);
        List<String> lorea;
        if (itemStack.getItemMeta().getLore() != null){
            net.minecraft.server.v1_8_R3.ItemStack stack = CraftItemStack.asNMSCopy(itemStack);
            NBTTagCompound tag = stack.getTag() != null ? stack.getTag() : new NBTTagCompound();
            charm = tag.getInt("charm");
            charmMax = tag.getInt("charmMax");
            level = tag.getInt("level");
        } else {
            lorea = new ArrayList<>();
            net.minecraft.server.v1_8_R3.ItemStack stack = CraftItemStack.asNMSCopy(itemStack);
            NBTTagCompound tag = stack.getTag() != null ? stack.getTag() : new NBTTagCompound();
            tag.setInt("charm", 0);
            charm = 0;
            tag.setInt("charmMax", 4800);
            charmMax = 4800;
            tag.setInt("level", 1);
            level = 1;
            stack.setTag(tag);
            itemStack = CraftItemStack.asCraftMirror(stack);
            ItemMeta meta = itemStack.getItemMeta();
            lorea.add("Charm (" + charm + "/" + charmMax + ")");
            meta.setLore(lorea);
            meta.setDisplayName(itemStack.getType() + " 1");
            itemStack.setItemMeta(meta);
            this.itemStack = itemStack;
        }
    }

    public void upgrade() {
        setLevel(getLevel() + 1);
        setCharm(getCharm() - getCharmMax());
        setCharmMax(calculator.cost());
    }

    public void setLevel(int level){
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(itemStack.getType() + " " + level);
        net.minecraft.server.v1_8_R3.ItemStack stack = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound tag = stack.getTag() != null ? stack.getTag() : new NBTTagCompound();
        tag.setInt("level", level);
        this.level = level;
        stack.setTag(tag);
        itemStack.setType(CraftItemStack.asCraftMirror(stack).getType());
    }


    public int getLevel() {
        net.minecraft.server.v1_8_R3.ItemStack stack = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound tag = stack.getTag() != null ? stack.getTag() : new NBTTagCompound();
        level = tag.getInt("level");
        return level;
    }

    public void setCharm(int charm) {
        net.minecraft.server.v1_8_R3.ItemStack stack = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound tag = stack.getTag() != null ? stack.getTag() : new NBTTagCompound();
        tag.setInt("charm", charm);
        stack.setTag(tag);
        this.charm = charm;
    }

    public int getCharm(){
        net.minecraft.server.v1_8_R3.ItemStack stack = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound tag = stack.getTag() != null ? stack.getTag() : new NBTTagCompound();
        tag.getInt("charm");
        return tag.getInt("charm");
    }

    public void setCharmMax(int charmMax){
        net.minecraft.server.v1_8_R3.ItemStack stack = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound tag = stack.getTag() != null ? stack.getTag() : new NBTTagCompound();
        tag.setInt("charmMax", charmMax);
        stack.setTag(tag);
        this.charmMax = charmMax;
    }

    public int getCharmMax(){
        net.minecraft.server.v1_8_R3.ItemStack stack = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound tag = stack.getTag() != null ? stack.getTag() : new NBTTagCompound();
        return tag.getInt("charmMax");
    }
}
