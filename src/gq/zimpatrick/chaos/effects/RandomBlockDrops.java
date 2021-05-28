package gq.zimpatrick.chaos.effects;

import gq.zimpatrick.chaos.helper.ProgressiveEffect;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class RandomBlockDrops extends ProgressiveEffect {
    public RandomBlockDrops() {
        super("Random Block Drops");
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        if(e.isDropItems()) {
            e.setDropItems(false);
            Material m = Material.values()[new Random().nextInt(Material.values().length)];
            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), new ItemStack(m, new Random().nextInt(64)));

        }
    }
}
