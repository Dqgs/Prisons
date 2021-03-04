package com.dogs.prisons.charm;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

public class CharmCalculator {

    ItemStack itemStack;
    Pickaxe pickaxe;
    CharmCalculator charmCalculator;

    public CharmCalculator(Pickaxe pickaxe, ItemStack itemStack){
        this.itemStack = itemStack;
        this.pickaxe = pickaxe;
    }

    public int cost() {
        int finalCalculation = 0;
        switch (itemStack.getType()) {
            case WOOD_PICKAXE:
                finalCalculation =  4800 + ((pickaxe.getLevel() - 1) * 9600);
                Bukkit.broadcastMessage("Pickaxe Level: " + (pickaxe.getLevel() - 1));
                break;
            case STONE_PICKAXE:
                finalCalculation =  6000 + ((pickaxe.getLevel() - 1) * 10800);
                break;
            case GOLD_PICKAXE:
                finalCalculation =  7200 + ((pickaxe.getLevel() - 1) * 12000);
                break;
            case IRON_PICKAXE:
                finalCalculation =  8400 + ((pickaxe.getLevel() - 1) * 13200);
                break;
            case DIAMOND_PICKAXE:
                finalCalculation =  9600 + ((pickaxe.getLevel() - 1) * 14400);
                break;
        }
        return finalCalculation;
    }
    public int setBegginerCost(){
        int finalCalculation = 0;
        switch (itemStack.getType()) {
            case WOOD_PICKAXE:
                finalCalculation =  4800;
                break;
            case STONE_PICKAXE:
                finalCalculation =  6000;
                break;
            case GOLD_PICKAXE:
                finalCalculation =  7200;
                break;
            case IRON_PICKAXE:
                finalCalculation =  8400;
                break;
            case DIAMOND_PICKAXE:
                finalCalculation =  9600;
                break;
        }
        return finalCalculation;
    }
}
