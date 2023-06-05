package me.none030.mortisheads.heads;

import com.shampaggon.crackshot.events.WeaponScopeEvent;
import me.none030.mortisheads.MortisHeads;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class CrackShotListener implements Listener {

    public MortisHeads plugin = MortisHeads.getInstance();

    private final HeadManager headManager;

    public CrackShotListener(HeadManager headManager) {
        this.headManager = headManager;
    }

    @EventHandler
    public void onScope(WeaponScopeEvent e) {
        Player player = e.getPlayer();
        if (e.isZoomIn()) {
            return;
        }
        ItemStack item = player.getInventory().getHelmet();
        if (item == null || item.getType().isAir()) {
            return;
        }
        Head head = headManager.getHead(item);
        if (head == null) {
            return;
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                head.addEffects(headManager, player);
            }
        }.runTaskLater(plugin, 20);
    }
}
