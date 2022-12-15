package me.none030.mortisheads.head;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class HeadListener implements Listener {

    private final HeadManager manager;

    public HeadListener(HeadManager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {

        Player player = (Player) e.getWhoClicked();

        if(e.getClickedInventory() != null && !e.getClickedInventory().getType().equals(InventoryType.PLAYER)) {
            return;
        }

        if (e.getClick().isShiftClick()) {
            if (e.getCurrentItem() == null) {
                return;
            }
            if (!e.getCurrentItem().getType().equals(Material.PLAYER_HEAD)) {
                return;
            }
            if (manager.getHeadByItem().get(e.getCurrentItem()) == null) {
                return;
            }
            Head head = manager.getHeadByItem().get(e.getCurrentItem());
            head.GiveEffect(player);
        }
        if (e.getSlotType() == InventoryType.SlotType.ARMOR) {
            if (e.getCursor() == null) {
                return;
            }
            if (!e.getCursor().getType().equals(Material.PLAYER_HEAD)) {
                return;
            }
            if (manager.getHeadByItem().get(e.getCursor()) == null) {
                return;
            }
            Head head = manager.getHeadByItem().get(e.getCursor());
            head.GiveEffect(player);
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

        Player player = e.getPlayer();
        PlayerInventory inv = player.getInventory();
        ItemStack item = inv.getHelmet();

        if (item == null || item.getType().equals(Material.AIR)) {
            return;
        }
        if (manager.getHeadByItem().get(item) == null) {
            return;
        }
        Head head = manager.getHeadByItem().get(item);
        head.GiveEffect(player);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {

        Player player = e.getEntity();
        PlayerInventory inv = player.getInventory();
        ItemStack item = inv.getHelmet();

        if (item == null || item.getType().equals(Material.AIR)) {
            return;
        }
        if (manager.getHeadByItem().get(item) == null) {
            return;
        }
        Head head = manager.getHeadByItem().get(item);
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
        PlayerInventory inv = player.getInventory();
        ItemStack item = inv.getHelmet();

        if (item == null || item.getType().equals(Material.AIR)) {
            return;
        }
        if (manager.getHeadByItem().get(item) == null) {
            return;
        }
        Head head = manager.getHeadByItem().get(item);
        head.GiveEffect(player);
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {

        ItemStack item = e.getItemInHand();

        if (item.getType().equals(Material.AIR)) {
            return;
        }
        if (manager.getHeadByItem().get(item) == null) {
            return;
        }
        e.setCancelled(true);
    }
}
