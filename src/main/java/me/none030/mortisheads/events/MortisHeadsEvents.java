package me.none030.mortisheads.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.PlayerInventory;

import static me.none030.mortisheads.methods.DeathDropChecker.getDrop;
import static me.none030.mortisheads.methods.HeadEffects.GiveEffects;
import static me.none030.mortisheads.methods.ItemChecker.getEffects;

public class MortisHeadsEvents implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {

        Player player = (Player) e.getWhoClicked();

        if(e.isCancelled()) {
            return;
        }
        if (e.getAction() == InventoryAction.NOTHING) {
            return;
        }
        if(e.getClickedInventory() != null && !e.getClickedInventory().getType().equals(InventoryType.PLAYER)) {
            return;
        }
        if (!e.getInventory().getType().equals(InventoryType.CRAFTING) && !e.getInventory().getType().equals(InventoryType.PLAYER)) {
            return;
        }
        if(!(e.getWhoClicked() instanceof Player)) {
            return;
        }

        if (e.getClick().isShiftClick()) {
            if (e.getCurrentItem() != null) {
                if (e.getCurrentItem().getType() == Material.PLAYER_HEAD) {
                    GiveEffects(player, e.getCurrentItem());
                }
            }
        }
        if (e.getSlotType() == InventoryType.SlotType.ARMOR) {
            if (e.getCursor() != null) {
                if (e.getCursor().getType() == Material.PLAYER_HEAD) {
                    GiveEffects(player, e.getCursor());
                }
            }
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

        Player player = e.getPlayer();
        PlayerInventory inv = player.getInventory();

        if (inv.getHelmet() != null && inv.getHelmet().getType() != Material.AIR) {
            GiveEffects(player, inv.getHelmet());
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {

        Player player = e.getEntity();
        PlayerInventory inv = player.getInventory();

        if (inv.getHelmet() != null && inv.getHelmet().getType() != Material.AIR) {
            String head = getEffects(inv.getHelmet());
            if (head != null) {
                if (getDrop(head)) {
                    e.getItemsToKeep().add(inv.getHelmet());
                    e.getDrops().remove(inv.getHelmet());
                }
            }
        }
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {

        Player player = e.getPlayer();
        PlayerInventory inv = player.getInventory();

        if (inv.getHelmet() != null && inv.getHelmet().getType() != Material.AIR) {
            GiveEffects(player, inv.getHelmet());
        }

    }
}
