package gq.zimpatrick.chaos.effects;

import gq.zimpatrick.chaos.helper.ChaosEffect;
import gq.zimpatrick.chaos.helper.LittleHelper;
import gq.zimpatrick.chaos.helper.ProgressiveEffect;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.StructureType;

import java.util.Random;

public class Locate extends ChaosEffect {
    public Locate() {
        super("Locate");
    }
    @Override
    public void onEnable() {
        StructureType[] netherStructures = { StructureType.BASTION_REMNANT, StructureType.NETHER_FORTRESS };
        StructureType[] overworldStructures = { StructureType.BURIED_TREASURE, StructureType.DESERT_PYRAMID, StructureType.IGLOO, StructureType.JUNGLE_PYRAMID, StructureType.MINESHAFT, StructureType.OCEAN_MONUMENT, StructureType.OCEAN_RUIN, StructureType.PILLAGER_OUTPOST, StructureType.SHIPWRECK, StructureType.SWAMP_HUT, StructureType.VILLAGE, StructureType.WOODLAND_MANSION};
        StructureType randomValue;
        switch(LittleHelper.getPlayer().getWorld().getEnvironment()) {
            case NETHER:
                randomValue = netherStructures[new Random().nextInt(netherStructures.length)];
                break;
            case NORMAL:
                randomValue = overworldStructures[new Random().nextInt(overworldStructures.length)];
                break;
            case THE_END:
                randomValue = StructureType.END_CITY;
                break;
            default: randomValue = StructureType.BASTION_REMNANT;
            break;
        }

        Location loc = LittleHelper.getPlayer().getWorld().locateNearestStructure(LittleHelper.getPlayer().getLocation(), randomValue, 16 * 16, false);
        LittleHelper.getPlayer().sendMessage(ChatColor.GRAY + "Die nächste Struktur, " + ChatColor.GOLD + randomValue.getName() + ChatColor.GRAY + ", ist bei den Koordinaten " + ChatColor.GOLD + loc.getX() + " " + + loc.getZ() + ChatColor.GRAY + " zu finden.");
    }
}
