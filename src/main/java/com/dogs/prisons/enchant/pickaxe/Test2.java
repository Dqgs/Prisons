package com.dogs.prisons.enchant.pickaxe;

import com.dogs.prisons.enchant.Enchant;
import com.dogs.prisons.enchant.ItemSet;
import com.dogs.prisons.enchant.Rarity;
import org.bukkit.event.Listener;

public class Test2 extends Enchant implements Listener {
    public Test2() {
        super("Test2", "Breaks blocks faster", 2, Rarity.EPIC, new ItemSet[]{ItemSet.PICKAXE}, 3,60, true);
    }


    @Override
    public int compareTo(Enchant o) {
        return this.getId() - o.getId();
    }
}
