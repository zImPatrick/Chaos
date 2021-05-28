package gq.zimpatrick.chaos.effects;

import gq.zimpatrick.chaos.helper.ChaosEffect;
import gq.zimpatrick.chaos.helper.LittleHelper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class SpawnCreeper extends ChaosEffect {
    public SpawnCreeper() {
        super("Spawn Creeper");
    }

    @Override
    public void onEnable() {
        super.onEnable();
        Player p = LittleHelper.getPlayer();
        p.getWorld().spawnEntity(p.getLocation(), EntityType.CREEPER);
    }
}
