package gq.zimpatrick.chaos.effects;

import gq.zimpatrick.chaos.helper.ChaosEffect;
import gq.zimpatrick.chaos.helper.LittleHelper;
import org.bukkit.util.Vector;

public class LaunchUp extends ChaosEffect {
    public LaunchUp() {
        super("In die Luft");
    }

    @Override
    public void onEnable() {
        LittleHelper.getPlayer().setVelocity(new Vector(0, 1.5, 0));
    }
}
