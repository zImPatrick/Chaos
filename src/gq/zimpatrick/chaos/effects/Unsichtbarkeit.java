package gq.zimpatrick.chaos.effects;

import gq.zimpatrick.chaos.helper.LittleHelper;
import gq.zimpatrick.chaos.helper.ProgressiveEffect;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;

public class Unsichtbarkeit extends ProgressiveEffect {
    ArrayList<LivingEntity> setEntities = new ArrayList<>();

    public Unsichtbarkeit() {
        super("Alle sind unsichtbar");
    }

    @Override
    public void onTick() {
        for(LivingEntity e : LittleHelper.getPlayer().getWorld().getLivingEntities()) {
            if(!setEntities.contains(e) && !e.hasAI()) {
                e.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 30 * 20, 1));
                setEntities.add(e);
            }
        }
    }
    @Override
    public void onDisable() {
        super.onDisable();

        for(LivingEntity entity : setEntities) {
            entity.removePotionEffect(PotionEffectType.INVISIBILITY);
        }
        setEntities.clear();
    }
}
