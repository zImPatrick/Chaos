package gq.zimpatrick.chaos.effects;

import com.destroystokyo.paper.Title;
import gq.zimpatrick.chaos.helper.LittleHelper;
import gq.zimpatrick.chaos.helper.ProgressiveEffect;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

public class Pause extends ProgressiveEffect {
    public Pause() {
        super("Pause");
    }

    @Override
    public void onEnable() {
        super.onEnable();
        LittleHelper.getPlayer().sendTitle(new Title(ChatColor.GOLD + "Hey!", ChatColor.GRAY + "Während du wartest, kannst du ja Wasser trinken"));
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if(e.getFrom().getX() != e.getTo().getX()
        || e.getFrom().getY() != e.getTo().getY()
        || e.getFrom().getZ() != e.getTo().getZ())
        e.setCancelled(true); // :D
    }
}
