package gq.zimpatrick.chaos.effects;

import gq.zimpatrick.chaos.helper.LittleHelper;
import gq.zimpatrick.chaos.helper.ProgressiveEffect;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class NoJump extends ProgressiveEffect {
    public NoJump() {
        super("Kein Springen");
    }

    @Override
    public void onTick() {
        super.onTick();

        LittleHelper.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 200));
    }
    @Override
    public void onDisable() {
        super.onDisable();
        LittleHelper.getPlayer().removePotionEffect(PotionEffectType.JUMP);
    }
}
