package me.none030.mortisheads.heads;

import me.deecaad.weaponmechanics.weapon.weaponevents.WeaponScopeEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class WeaponMechanicsListener implements Listener {

    private final HeadManager headManager;

    public WeaponMechanicsListener(HeadManager headManager) {
        this.headManager = headManager;
    }

    @EventHandler
    public void onScope(WeaponScopeEvent e) {
        Player player = (Player) e.getShooter();
        if (e.getScopeType().equals(WeaponScopeEvent.ScopeType.IN)) {
            headManager.getScoping().add(player);
        }else {
            headManager.getScoping().remove(player);
        }
    }
}
