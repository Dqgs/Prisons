package com.dogs.prisons.events;

import com.dogs.prisons.charm.Charm;
import com.dogs.prisons.charm.Pickaxe;
import com.dogs.prisons.enchant.ItemSet;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryMove implements Listener {

    @EventHandler
    public void onInventoryMove(InventoryClickEvent event){
        if (event.getCurrentItem() == null
        || event.getCursor() == null) {
            Bukkit.broadcastMessage("Null");
            return;
        }

        ItemStack cursor = event.getCursor();
        ItemStack currItem = event.getCurrentItem();
        if (cursor.getType() != Material.AIR
                && currItem.getType() != Material.AIR) {
            if (ItemSet.PICKAXE.getItems().contains(cursor.getType()))
                return;
            net.minecraft.server.v1_8_R3.ItemStack cursorStack = CraftItemStack.asNMSCopy(cursor);
            NBTTagCompound cursorTag = cursorStack.getTag() != null ? cursorStack.getTag() : new NBTTagCompound();

            net.minecraft.server.v1_8_R3.ItemStack currItemStack = CraftItemStack.asNMSCopy(currItem);
            NBTTagCompound currItemTag = currItemStack.getTag() != null ? currItemStack.getTag() : new NBTTagCompound();

            Bukkit.broadcastMessage("Cursor: " + cursor.getType());
            Bukkit.broadcastMessage("Current: " + currItem.getType());

            int cursorAmount = cursorTag.getInt("charm");
            Bukkit.broadcastMessage("cursorAmount: " + cursorAmount);
            int currItemAmount = currItemTag.getInt("charm");
            Bukkit.broadcastMessage("currItemAmount: " + currItemAmount);

            currItemAmount = cursorAmount + currItemAmount;

            if (!ItemSet.PICKAXE.getItems().contains(currItem.getType())) {
                Charm currItemCharm = new Charm(CraftItemStack.asCraftMirror(currItemStack));
                currItemCharm.setAmount(currItemAmount);
                event.setCursor(currItemCharm.itemStack);
            } else if (ItemSet.PICKAXE.getItems().contains(currItem.getType())){
                Pickaxe pickaxe = new Pickaxe(CraftItemStack.asCraftMirror(currItemStack));
                pickaxe.setCharm(currItemAmount);
                event.setCursor(pickaxe.itemStack);
            }
            event.setCurrentItem(new ItemStack(Material.AIR));
        }
    }
}
