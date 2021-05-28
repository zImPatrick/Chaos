package gq.zimpatrick.chaos.effects;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import gq.zimpatrick.chaos.helper.ChaosEffect;
import gq.zimpatrick.chaos.helper.LittleHelper;
import gq.zimpatrick.chaos.helper.ProgressiveEffect;
import net.minecraft.server.v1_16_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R3.CraftServer;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.UUID;

public class Clone extends ProgressiveEffect {
    static EntityPlayer npc;
    static GameProfile profile;
    Location locationOfNpc;
    public Clone() {
        super("Clone");
    }

    @Override
    public void onEnable() {
        super.onEnable();
        MinecraftServer nmsServer = ((CraftServer) Bukkit.getServer()).getServer();
        WorldServer nmsWorld = ((CraftWorld)LittleHelper.getPlayer().getWorld()).getHandle(); // Change "world" to the world the NPC should be spawned in.
        GameProfile gameProfile = new GameProfile(UUID.randomUUID(), LittleHelper.getPlayer().getName()); // Change "playername" to the name the NPC should have, max 16 characters.
        profile = gameProfile;
        npc = new EntityPlayer(nmsServer, nmsWorld, gameProfile, new PlayerInteractManager(nmsWorld)); // This will be the EntityPlayer (NPC) we send with the sendNPCPacket method.
        Location loc = LittleHelper.getPlayer().getLocation();
        npc.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());

        sendSetNPCSkinPacket(npc, LittleHelper.getPlayer(), LittleHelper.getPlayer().getName());
    }

    @Override
    public void onDisable() {
        super.onDisable();
        removeNPCPacket(npc, LittleHelper.getPlayer());
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Location loc = LittleHelper.getPlayer().getLocation();
        Location locToMoveTo = loc.subtract(loc.getDirection().multiply(2));
        sendMoveEntityPacket(npc, LittleHelper.getPlayer(), locToMoveTo.getX(), locToMoveTo.getY(), locToMoveTo.getZ(), loc.getYaw(), loc.getPitch());
    }

    public static void addNPCPacket(EntityPlayer npc, Player player) {
        PlayerConnection connection = ((CraftPlayer)player).getHandle().playerConnection;
        connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, npc)); // "Adds the player data for the client to use when spawning a player" - https://wiki.vg/Protocol#Spawn_Player
        connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc)); // Spawns the NPC for the player client.
        connection.sendPacket(new PacketPlayOutEntityHeadRotation(npc, (byte) (npc.yaw * 256 / 360))); // Correct head rotation when spawned in player look direction.

    }

    public static void removeNPCPacket(Entity npc, Player player) {
        PlayerConnection connection = ((CraftPlayer)player).getHandle().playerConnection;
        connection.sendPacket(new PacketPlayOutEntityDestroy(npc.getId()));
    }

    public static void sendMoveEntityPacket(Entity entity, Player player, double x, double y, double z, float yaw, float pitch) {
        npc.setLocation(x, y, z, yaw, pitch);

        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(
        new PacketPlayOutEntityTeleport(entity));

        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(new PacketPlayOutEntityHeadRotation(npc, (byte)(yaw * 256 / 360)));
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(new PacketPlayOutEntity.PacketPlayOutEntityLook(npc.getId(), (byte)(yaw * 256 / 360), (byte)(pitch * 256 / 360), true));
    }

    public static void sendSetNPCSkinPacket(EntityPlayer npc, Player player, String username) { // The username is the name for the player that has the skin.
        removeNPCPacket(npc, player);

        try {
            HttpsURLConnection connection = (HttpsURLConnection) new URL(String.format("https://api.ashcon.app/mojang/v2/user/%s", username)).openConnection();
            if (connection.getResponseCode() == HttpsURLConnection.HTTP_OK) {
                ArrayList<String> lines = new ArrayList<>();
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                reader.lines().forEach(lines::add);

                String reply = String.join(" ",lines);
                int indexOfValue = reply.indexOf("\"value\": \"");
                int indexOfSignature = reply.indexOf("\"signature\": \"");
                String skin = reply.substring(indexOfValue + 10, reply.indexOf("\"", indexOfValue + 10));
                String signature = reply.substring(indexOfSignature + 14, reply.indexOf("\"", indexOfSignature + 14));

                profile.getProperties().put("textures", new Property("textures", skin, signature));
            }

            else {
                Bukkit.getConsoleSender().sendMessage("Connection could not be opened when fetching player skin (Response code " + connection.getResponseCode() + ", " + connection.getResponseMessage() + ")");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // The client settings.
        DataWatcher watcher = npc.getDataWatcher();
        watcher.set(new DataWatcherObject<>(16, DataWatcherRegistry.a), (byte)127);
        PacketPlayOutEntityMetadata packet = new PacketPlayOutEntityMetadata(npc.getId(), watcher, true);
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);

        addNPCPacket(npc, player);
    }
}
