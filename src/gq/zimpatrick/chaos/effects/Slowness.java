package gq.zimpatrick.chaos.effects;

import gq.zimpatrick.chaos.helper.ChaosEffect;
import gq.zimpatrick.chaos.helper.LittleHelper;
import gq.zimpatrick.chaos.helper.ProgressiveEffect;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Slowness extends ProgressiveEffect {
    public Slowness() {
        super("Slowness");
    }

    @Override
    public void onTick() {
        super.onTick();
        LittleHelper.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 30 * 20, 2));
    }
    @Override
    public void onDisable() {
        super.onDisable();
        LittleHelper.getPlayer().removePotionEffect(PotionEffectType.SLOW);
    }
}
