package me.none030.mortisheads.heads;

import com.shampaggon.crackshot.events.WeaponScopeEvent;
import me.none030.mortisheads.MortisHeads;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

public class CrackShotListener implements Listener {

    private final MortisHeads plugin = MortisHeads.getInstance();
    private final HeadManager headManager;

    public CrackShotListener(HeadManager headManager) {
        this.headManager = headManager;
    }

    @EventHandler
    public void onScope(WeaponScopeEvent e) {
        Player player = e.getPlayer();
        new BukkitRunnable() {
            @Override
            public void run() {
                if (e.isZoomIn()) {
                    headManager.getScoping().add(player);
                }else {
                    headManager.getScoping().remove(player);
                }
            }
        }.runTask(plugin);
    }
}
