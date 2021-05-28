package gq.zimpatrick.chaos.effects;

import gq.zimpatrick.chaos.helper.ChaosEffect;
import gq.zimpatrick.chaos.helper.LittleHelper;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Essen extends ChaosEffect {
    public Essen() {
        super("Essen");
    }

    @Override
    public void onEnable() {
        LittleHelper.getPlayer().getInventory().addItem(new ItemStack(Material.COOKED_BEEF, 64));
    }
}
