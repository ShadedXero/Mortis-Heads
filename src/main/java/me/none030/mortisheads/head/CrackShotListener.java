package me.none030.mortisheads.head;

import com.shampaggon.crackshot.events.WeaponScopeEvent;
import me.none030.mortisheads.MortisHeads;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scheduler.BukkitRunnable;

public class CrackShotListener implements Listener {

    public MortisHeads plugin = MortisHeads.getInstance();

    private final HeadManager manager;

    public CrackShotListener(HeadManager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void onScope(WeaponScopeEvent e) {

        Player player = e.getPlayer();
        PlayerInventory inv = player.getInventory();
        ItemStack item = inv.getHelmet();

        if (e.isZoomIn()) {
            return;
        }
        if (item == null || item.getType().equals(Material.AIR)) {
            return;
        }
        if (manager.getHeadByItem().get(item) == null) {
            return;
        }
        Head head = manager.getHeadByItem().get(item);
        new BukkitRunnable() {
            @Override
            public void run() {
                head.GiveEffect(player);
            }
        }.runTaskLater(plugin, 20);
    }
}
