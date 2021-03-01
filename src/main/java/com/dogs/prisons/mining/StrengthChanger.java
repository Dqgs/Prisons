package com.dogs.prisons.mining;

import com.dogs.prisons.Prisons;
import com.dogs.prisons.enchant.Enchant;
import com.dogs.prisons.enchant.EnchantOrb;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.util.CraftMagicNumbers;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StrengthChanger implements Listener{

    private Map<Player, BukkitTask> task;
    private Field pim_f, pim_k, pim_d, pim_h, pim_lastdig, pim_currentdig;

    private static List<Block> changedBlocks;
    private static Field strength;
    private final Prisons plugin;

    public StrengthChanger(Prisons plugin) {
        this.plugin = plugin;
        task = new HashMap<>();
        changedBlocks = new ArrayList<>();
        try {
            strength = Block.class.getDeclaredField("strength");
            pim_f = PlayerInteractManager.class.getDeclaredField("f");
            pim_k = PlayerInteractManager.class.getDeclaredField("k");
            pim_d = PlayerInteractManager.class.getDeclaredField("d");
            pim_h = PlayerInteractManager.class.getDeclaredField("h");
            pim_lastdig = PlayerInteractManager.class.getDeclaredField("lastDigTick");
            pim_currentdig = PlayerInteractManager.class.getDeclaredField("currentTick");
            strength.setAccessible(true);
            pim_f.setAccessible(true);
            pim_k.setAccessible(true);
            pim_d.setAccessible(true);
            pim_h.setAccessible(true);
            pim_lastdig.setAccessible(true);
            pim_currentdig.setAccessible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void setStrength(Block block, float strengthValue) {
        try {
            strength.set(block, strengthValue);
            changedBlocks.add(block);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        final CraftPlayer player = (CraftPlayer) event.getPlayer();
        final EntityPlayer handle = player.getHandle();
        task.put(event.getPlayer(), plugin.getServer().getScheduler().runTaskTimer(plugin, new Runnable() {
            boolean wasMining = false;
            @Override
            public void run() {
                try {
                    int k = pim_k.getInt(handle.playerInteractManager);
                    if ((pim_h.getBoolean(handle.playerInteractManager) || pim_d.getBoolean(handle.playerInteractManager) && changedBlocks.contains(handle.world.getType((BlockPosition) pim_f.get(handle.playerInteractManager)).getBlock()))) {
                        handle.playerConnection.sendPacket(new PacketPlayOutBlockBreakAnimation(0, (BlockPosition) pim_f.get(handle.playerInteractManager), k));
                        if (k >= 10) {

                            handle.playerInteractManager.a((BlockPosition) pim_f.get(handle.playerInteractManager));
                        }
                        wasMining = true;
                    } else if (wasMining) {
                        wasMining = false;
                        handle.playerConnection.sendPacket(new PacketPlayOutBlockBreakAnimation(0, (BlockPosition) pim_f.get(handle.playerInteractManager), -1));
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 1));
        EnchantOrb orb = new EnchantOrb(2, Enchant.getEnchantByName("SuperBreaker"), 100, 0);
        player.getPlayer().getInventory().addItem(orb.getBook());

    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        if (task.get(event.getPlayer()) != null) task.remove(event.getPlayer());
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Location loc = event.getBlock().getLocation();
        EntityPlayer handle = ((CraftPlayer) event.getPlayer()).getHandle();
        handle.playerConnection.sendPacket(new PacketPlayOutWorldEvent(2001, new BlockPosition(loc.getX(), loc.getY(), loc.getZ()), Block.getCombinedId(CraftMagicNumbers.getBlock(event.getBlock()).getBlockData()), false));
        plugin.getServer().getScheduler().runTaskLater(plugin, () -> {

            try {
                event.getBlock().setType(Material.COAL_BLOCK);
                pim_lastdig.set(handle.playerInteractManager, pim_currentdig.get(handle.playerInteractManager));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }, 3);
    }
    @EventHandler
    public void onBlockDamage(BlockDamageEvent event){
        ItemStack item = event.getItemInHand();

    }

    public void setStrengthBasedOnPickaxe(ItemStack item){
        switch (item.getType()){
            case WOOD_PICKAXE:

                setStrength(Blocks.COAL_BLOCK, 0.6f);
                setStrength(Blocks.COAL_ORE, 0.6f);

                setStrength(Blocks.IRON_ORE, 0.6f);
                setStrength(Blocks.IRON_BLOCK, 0.6f);

                setStrength(Blocks.LAPIS_ORE, 0.7f);
                setStrength(Blocks.LAPIS_BLOCK, 0.7f);

                setStrength(Blocks.LAPIS_BLOCK, 0.7f);
                setStrength(Blocks.LAPIS_BLOCK, 0.7f);

                setStrength(Blocks.LAPIS_BLOCK, 0.7f);
                setStrength(Blocks.LAPIS_BLOCK, 0.7f);

                setStrength(Blocks.LAPIS_BLOCK, 0.7f);
                setStrength(Blocks.LAPIS_BLOCK, 0.7f);

                setStrength(Blocks.LAPIS_BLOCK, 0.7f);
                setStrength(Blocks.LAPIS_BLOCK, 0.7f);

                break;
        }
    }
}
