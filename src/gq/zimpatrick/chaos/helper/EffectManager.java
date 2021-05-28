package gq.zimpatrick.chaos.helper;

import gq.zimpatrick.chaos.Main;
import gq.zimpatrick.chaos.effects.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

import java.util.ArrayList;

public class EffectManager {
    public ArrayList<ChaosEffect> enabledEffects = new ArrayList<>();
    public ArrayList<ChaosEffect> availableEffects = new ArrayList<>();
    public EffectManager instance;
    public EffectManager() {
        instance = this;

        availableEffects.add(new SpawnCreeper());
        availableEffects.add(new LaunchUp());
        availableEffects.add(new HotbarParty());
        availableEffects.add(new NoJump());
        availableEffects.add(new Speed200());
        availableEffects.add(new HalbesLeben());
        availableEffects.add(new DropInventory());
        availableEffects.add(new Kicken());
        availableEffects.add(new Crash());
        availableEffects.add(new Slowness());
        availableEffects.add(new Levitation());
        availableEffects.add(new Hunger());
        availableEffects.add(new FakeKill());
        availableEffects.add(new Blindness());
        availableEffects.add(new Overheal());
        availableEffects.add(new Locate());
        availableEffects.add(new VehicleFly());
        availableEffects.add(new KeineLebensanzeige());
        availableEffects.add(new Pause());
        availableEffects.add(new Nichts());
        availableEffects.add(new Onehit());
        availableEffects.add(new RandomBlockDrops());
        availableEffects.add(new Unsichtbarkeit());
        availableEffects.add(new Essen());
        availableEffects.add(new SneakBoost());

        System.out.println("Geladene Effekte: " + availableEffects.size());
    }

    public ChaosEffect getEffectByName(String name) {
        for(ChaosEffect effect : availableEffects) {
            if(effect.name.equals(name)) return effect;
        }
        return null;
    }

    public void enableEffect(ChaosEffect effect) {
        System.out.println(effect.name + " wurde aktiviert");
        effect.onEnable();
        Bukkit.getServer().getPluginManager().registerEvents(effect, Main.instance);
        if(effect instanceof ProgressiveEffect) {
            enabledEffects.add(effect);
            NamespacedKey barNameKey = new NamespacedKey(Main.instance, effect.name.replaceAll(" ", "_"));
            BossBar bar = Bukkit.getServer().createBossBar(barNameKey, ChatColor.GOLD + effect.name, BarColor.PURPLE, BarStyle.SOLID);
            bar.setVisible(true);
            for(Player pl : Bukkit.getServer().getOnlinePlayers()) {
                bar.addPlayer(pl);
            }
            bar.setProgress(1.0F);
            Main.instance.bars.put(effect.name, bar);
        }
    }

    public void disableEffect(ChaosEffect effect) {
        HandlerList.unregisterAll(effect);
        if(effect instanceof ProgressiveEffect) {
            enabledEffects.remove(effect);

            if(Main.instance.bars.containsKey(effect.name)) {
                BossBar bar = Main.instance.bars.get(effect.name);
                bar.removeAll();
                NamespacedKey barNameKey = new NamespacedKey(Main.instance, effect.name.replaceAll(" ", "_"));
                Main.instance.getServer().removeBossBar(barNameKey);
            }
        }
        effect.onDisable();
    }

    public void tick() {
        for(ChaosEffect effect : enabledEffects) {
            ProgressiveEffect progressiveEffect = (ProgressiveEffect) effect;
            if(System.currentTimeMillis() - progressiveEffect.startTime >= 30000) {
                disableEffect(effect);
                return;
            }
            if(Main.instance.bars.containsKey(effect.name)) {
                Main.instance.bars.get(effect.name).setProgress((System.currentTimeMillis() - progressiveEffect.startTime) / 30000D);
            }
            effect.onTick();
        }
    }


}
