package me.none030.mortisheads.heads;

import com.shampaggon.crackshot.events.WeaponScopeEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class CrackShotListener implements Listener {

    private final HeadManager headManager;

    public CrackShotListener(HeadManager headManager) {
        this.headManager = headManager;
    }

    @EventHandler
    public void onScope(WeaponScopeEvent e) {
        Player player = e.getPlayer();
        if (e.isZoomIn()) {
            headManager.getScoping().add(player);
        } else {
            headManager.getScoping().remove(player);
        }
    }
}
