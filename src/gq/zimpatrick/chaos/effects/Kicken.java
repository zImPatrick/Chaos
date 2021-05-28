package gq.zimpatrick.chaos.effects;

import gq.zimpatrick.chaos.helper.ChaosEffect;
import gq.zimpatrick.chaos.helper.LittleHelper;

public class Kicken extends ChaosEffect {
    public Kicken() {
        super("Aus der Welt werfen");
    }

    @Override
    public void onEnable() {
        LittleHelper.getPlayer().kickPlayer(":D");
    }
}
