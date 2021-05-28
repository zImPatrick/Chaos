package gq.zimpatrick.chaos.effects;

import gq.zimpatrick.chaos.helper.LittleHelper;
import gq.zimpatrick.chaos.helper.ProgressiveEffect;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;

public class HalbesLeben extends ProgressiveEffect {
    public HalbesLeben() {
        super("Halbes Herz");
    }

    @Override
    public void onEnable() {
        super.onEnable();
        AttributeInstance attribute = LittleHelper.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH);
        assert attribute != null;
        attribute.setBaseValue(1D);
        LittleHelper.getPlayer().setHealth(1D);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        AttributeInstance attribute = LittleHelper.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH);
        assert attribute != null;
        attribute.setBaseValue(20D);
    }
}
