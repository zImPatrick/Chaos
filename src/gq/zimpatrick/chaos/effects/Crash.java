package gq.zimpatrick.chaos.effects;

import gq.zimpatrick.chaos.helper.ChaosEffect;
import gq.zimpatrick.chaos.helper.LittleHelper;
import net.minecraft.server.v1_16_R3.PacketPlayOutExplosion;
import net.minecraft.server.v1_16_R3.Vec3D;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;

import java.util.Collections;
import java.util.Random;

public class Crash extends ChaosEffect {
    public Crash() {
        super("Crash");
    }

    @Override
    public void onEnable() {
        if(new Random().nextBoolean()) {
            ((CraftPlayer) LittleHelper.getPlayer()).getHandle().playerConnection.sendPacket(
                new PacketPlayOutExplosion(Double.MAX_VALUE,
                        Double.MAX_VALUE,
                        Double.MAX_VALUE,
                        Float.MAX_VALUE,
                        Collections.EMPTY_LIST,
                        new Vec3D(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE)));
        } else {
            LittleHelper.getPlayer().sendMessage(ChatColor.GRAY + "Du hattest Glück und wurdest nicht gecrasht!");
        }

    }
}
