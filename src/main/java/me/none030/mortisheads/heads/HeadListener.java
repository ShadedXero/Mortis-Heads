package me.none030.mortisheads.heads;

import me.none030.mortisheads.armorequipevent.ArmorEquipEvent;
import me.none030.mortisheads.armorequipevent.ArmorType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

public class HeadListener implements Listener {

    private final HeadManager headManager;

    public HeadListener(HeadManager headManager) {
        this.headManager = headManager;
    }

    @EventHandler
    public void onEquip(ArmorEquipEvent e) {
        Player player = e.getPlayer();
        if (!e.getType().equals(ArmorType.HELMET)) {
            return;
        }
        if (e.getOldArmorPiece() != null && !e.getOldArmorPiece().getType().isAir()) {
            Head head = headManager.getHead(e.getOldArmorPiece());
            if (head != null) {
                headManager.removeTaskId(player);
            }
        }
        if (e.getNewArmorPiece() != null && !e.getNewArmorPiece().getType().isAir()) {
            Head head = headManager.getHead(e.getNewArmorPiece());
            if (head != null) {
                head.addEffects(headManager, player);
            }
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        ItemStack item = player.getInventory().getHelmet();
        if (item == null || item.getType().isAir()) {
            return;
        }
        Head head = headManager.getHead(item);
        if (head == null) {
            return;
        }
        head.addEffects(headManager, player);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player player = e.getPlayer();
        ItemStack item = player.getInventory().getHelmet();
        if (item == null || item.getType().isAir()) {
            return;
        }
        Head head = headManager.getHead(item);
        if (head == null) {
            return;
        }
        if (!head.isOnDeath()) {
            return;
        }
        e.getItemsToKeep().add(item);
        e.getDrops().remove(item);
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        Player player = e.getPlayer();
        ItemStack item = player.getInventory().getHelmet();
        if (item == null || item.getType().isAir()) {
            return;
        }
        Head head = headManager.getHead(item);
        if (head == null) {
            return;
        }
        head.addEffects(headManager, player);
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        ItemStack item = e.getItemInHand();
        if (item.getType().isAir()) {
            return;
        }
        Head head = headManager.getHead(item);
        if (head == null) {
            return;
        }
        e.setCancelled(true);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        headManager.getScoping().remove(player);
        headManager.removeTaskId(player);
    }
}
