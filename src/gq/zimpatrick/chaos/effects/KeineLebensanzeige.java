package gq.zimpatrick.chaos.effects;

import gq.zimpatrick.chaos.helper.LittleHelper;
import gq.zimpatrick.chaos.helper.ProgressiveEffect;
import net.minecraft.server.v1_16_R3.PacketPlayOutGameStateChange;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;

public class KeineLebensanzeige extends ProgressiveEffect {
    public KeineLebensanzeige() {
        super("Keine Lebensanzeige");
    }

    @Override
    public void onTick() {
        super.onTick();
        PacketPlayOutGameStateChange gameStateChange = new PacketPlayOutGameStateChange(new PacketPlayOutGameStateChange.a(3), -1F);
        ((CraftPlayer) LittleHelper.getPlayer()).getHandle().playerConnection.sendPacket(gameStateChange);
    }

    @Override
    public void onDisable() {
        super.onDisable();

        PacketPlayOutGameStateChange gameStateChange = new PacketPlayOutGameStateChange(new PacketPlayOutGameStateChange.a(3), 0F);
        ((CraftPlayer) LittleHelper.getPlayer()).getHandle().playerConnection.sendPacket(gameStateChange);
    }
}
