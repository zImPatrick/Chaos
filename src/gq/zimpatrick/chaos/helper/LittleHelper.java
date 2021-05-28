package gq.zimpatrick.chaos.helper;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class LittleHelper {
    public static Player getPlayer() {
        return Bukkit.getOnlinePlayers().iterator().next();
    }
}
