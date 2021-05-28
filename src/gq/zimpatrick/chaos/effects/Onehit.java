package gq.zimpatrick.chaos.effects;

import gq.zimpatrick.chaos.helper.LittleHelper;
import gq.zimpatrick.chaos.helper.ProgressiveEffect;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Onehit extends ProgressiveEffect {
    public Onehit() {
        super("Onehit");
    }

    @Override
    public void onEnable() {
        super.onEnable();
        LittleHelper.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 200));
    }
    @Override
    public void onDisable() {
        super.onDisable();
        LittleHelper.getPlayer().removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
    }
}
