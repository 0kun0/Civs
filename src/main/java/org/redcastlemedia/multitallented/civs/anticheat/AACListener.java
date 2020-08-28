package org.redcastlemedia.multitallented.civs.anticheat;

import me.konsolas.aac.api.PlayerViolationEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.redcastlemedia.multitallented.civs.Civs;
import org.redcastlemedia.multitallented.civs.civilians.Civilian;
import org.redcastlemedia.multitallented.civs.civilians.CivilianManager;

public class AACListener implements Listener {

    public AACListener() {
        Bukkit.getPluginManager().registerEvents(this, Civs.getInstance());
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerViolation(PlayerViolationEvent event) {
        Player player = event.getPlayer();
        Civilian civilian = CivilianManager.getInstance().getCivilian(player.getUniqueId());
        for (ExemptionType exemptionType : civilian.getExemptions()) {
            if (AACExemptionAssembler.mapExemptionTypeToHackTypes(exemptionType).contains(event.getHackType())) {
                event.setCancelled(true);
                return;
            }
        }
    }
}
