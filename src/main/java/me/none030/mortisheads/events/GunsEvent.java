package me.none030.mortisheads.events;

import com.shampaggon.crackshot.events.WeaponScopeEvent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.PlayerInventory;

import static me.none030.mortisheads.methods.HeadEffects.GiveEffects;

public class GunsEvent implements Listener {

    @EventHandler
    public void onScope(WeaponScopeEvent e) {

        Player player = e.getPlayer();
        PlayerInventory inv = player.getInventory();

        if (!e.isZoomIn()) {
            if (inv.getHelmet() != null && inv.getHelmet().getType() != Material.AIR) {
                GiveEffects(player, inv.getHelmet());
            }
        }

    }
}
