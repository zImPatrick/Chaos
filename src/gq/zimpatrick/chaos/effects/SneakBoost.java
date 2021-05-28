package gq.zimpatrick.chaos.effects;

import gq.zimpatrick.chaos.helper.LittleHelper;
import gq.zimpatrick.chaos.helper.ProgressiveEffect;

public class SneakBoost extends ProgressiveEffect {
    public SneakBoost() {
        super("Sneaken ist Boost");
    }

    @Override
    public void onTick() {
        if(LittleHelper.getPlayer().isSneaking()) {
            LittleHelper.getPlayer().setVelocity(LittleHelper.getPlayer().getLocation().getDirection().multiply(3));
        }
    }
}
