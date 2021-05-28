package gq.zimpatrick.chaos.effects;

import gq.zimpatrick.chaos.helper.ChaosEffect;
import gq.zimpatrick.chaos.helper.LittleHelper;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class DropInventory extends ChaosEffect {
    public DropInventory() {
        super("Inventar fallen lassen");
    }

    @Override
    public void onEnable() {
        super.onEnable();
        Player p = LittleHelper.getPlayer();
        for(ItemStack item : p.getInventory()) {
            if(item != null)
                p.getWorld().dropItem(p.getLocation(), item);
        }
        p.getInventory().clear();
        p.updateInventory();
    }
}
