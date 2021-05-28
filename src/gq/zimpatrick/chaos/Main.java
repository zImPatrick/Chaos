package gq.zimpatrick.chaos;

import gq.zimpatrick.chaos.helper.ChaosEffect;
import gq.zimpatrick.chaos.helper.EffectManager;
import gq.zimpatrick.chaos.helper.WebsocketServer;
import org.bukkit.Bukkit;
import org.bukkit.boss.BossBar;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Iterator;

public class Main extends JavaPlugin {
    public static Main instance;
    public EffectManager effectManager;
    public WebsocketServer ws;
    public HashMap<String, BossBar> bars = new HashMap<>();
    public void onEnable() {
        instance = this;
        effectManager = new EffectManager();
        ws = new WebsocketServer(15023);
        ws.start();

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> effectManager.tick(), 0L, 1L);
    }

    public void onDisable() {
        try {
            if(ws != null) ws.stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(Iterator<ChaosEffect> iterator = effectManager.enabledEffects.iterator(); iterator.hasNext(); ) {
            ChaosEffect effect = iterator.next();
            if(effectManager.enabledEffects.contains(effect)) effectManager.disableEffect(effect);
        }

    }
}
