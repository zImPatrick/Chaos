package gq.zimpatrick.chaos.effects;

import gq.zimpatrick.chaos.helper.ChaosEffect;
import gq.zimpatrick.chaos.helper.LittleHelper;
import net.minecraft.server.v1_16_R3.*;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;

public class FakeKill extends ChaosEffect {
    public FakeKill() {
        super("Fake Kill");
    }

    @Override
    public void onEnable() {
        super.onEnable();

        PacketPlayOutEntityStatus status = new PacketPlayOutEntityStatus(((CraftPlayer) LittleHelper.getPlayer()).getHandle(), (byte) 3);
        ((CraftPlayer)LittleHelper.getPlayer()).getHandle().playerConnection.sendPacket(status);

        PacketPlayOutCombatEvent packet = new PacketPlayOutCombatEvent(new CombatTracker(((CraftPlayer) LittleHelper.getPlayer()).getHandle()), PacketPlayOutCombatEvent.EnumCombatEventType.ENTITY_DIED);
        ((CraftPlayer) LittleHelper.getPlayer()).getHandle().playerConnection.sendPacket(packet);


    }
}
