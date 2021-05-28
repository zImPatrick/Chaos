package gq.zimpatrick.chaos.helper;

import org.bukkit.event.Listener;

public abstract class ChaosEffect implements Listener {
    public String name;
    public ChaosEffect(String name) {
        this.name = name;
    }
    public void onEnable() {};
    public void onDisable() {};
    public void onTick() {};
}
