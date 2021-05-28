package gq.zimpatrick.chaos.effects;

import gq.zimpatrick.chaos.helper.LittleHelper;
import gq.zimpatrick.chaos.helper.ProgressiveEffect;
import org.bukkit.entity.Player;

import java.util.Random;

public class HotbarParty extends ProgressiveEffect {
    public HotbarParty() {
        super("Hotbar Party");
    }

    @Override
    public void onTick() {
        Player p = LittleHelper.getPlayer();
        p.getInventory().setHeldItemSlot(new Random().nextInt(9));
    }
}
