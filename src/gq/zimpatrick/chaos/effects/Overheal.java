package gq.zimpatrick.chaos.effects;

import gq.zimpatrick.chaos.helper.LittleHelper;
import gq.zimpatrick.chaos.helper.ProgressiveEffect;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;

public class Overheal extends ProgressiveEffect {

    public Overheal() {
        super("Overheal");
    }

    @Override
    public void onEnable() {
        super.onEnable();
        AttributeInstance attribute = LittleHelper.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH);
        assert attribute != null;
        attribute.setBaseValue(40D);
        LittleHelper.getPlayer().setHealth(40D);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        AttributeInstance attribute = LittleHelper.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH);
        assert attribute != null;
        attribute.setBaseValue(20D);
    }

}
