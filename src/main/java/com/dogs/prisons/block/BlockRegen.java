package com.dogs.prisons.block;

import com.dogs.prisons.Prisons;
import com.dogs.prisons.charm.Charm;
import com.dogs.prisons.filemanager.BlockRegenFile;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class BlockRegen {

    BukkitTask taskId;

    public BlockRegen(Block block, int seconds){
        BlockRegenFile.saveBlock(block);
        Material type = block.getType();
        block.setType(Material.BEDROCK);
        this.taskId = new BukkitRunnable(){
            @Override
            public void run(){
                block.setType(type);
                BlockRegenFile.removeBlock(block);
            }
        }.runTaskLater(Prisons.getInstance(), seconds * 20L);
    }

    public void cancelTask(){
        Bukkit.getScheduler().cancelTask(taskId.getTaskId());
    }

    public BukkitTask getTaskId(){
        return taskId;
    }
}
