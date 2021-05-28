package gq.zimpatrick.chaos.effects;

import gq.zimpatrick.chaos.helper.LittleHelper;
import gq.zimpatrick.chaos.helper.ProgressiveEffect;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerKickEvent;

public class VehicleFly extends ProgressiveEffect {
    public VehicleFly() {
        super("VehicleFly");
    }

    @Override
    public void onTick() {
        super.onTick();

        Player p = LittleHelper.getPlayer();
        Location loc = p.getLocation();
        if(p.isInsideVehicle()) {
            Entity veh = p.getVehicle();
            assert veh != null;
            veh.setVelocity(loc.getDirection().multiply(3).normalize());
        }
    }
    @Override
    public void onEnable() {
        super.onEnable();
        this.startTime = System.currentTimeMillis() - 15000;
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    @EventHandler
    public void onKick(PlayerKickEvent e) {
        System.out.println(e.getReason());
    }
}
