package me.none030.mortisheads.methods;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Objects;

import static me.none030.mortisheads.MortisHeads.plugin;
import static me.none030.mortisheads.methods.ItemChecker.getEffects;
import static me.none030.mortisheads.methods.PotionEffectsCreation.CreatePotionEffects;

public class HeadEffects {

    public static void GiveEffects(Player player, ItemStack item) {

        String head = getEffects(item);

        if (head != null) {

            List<PotionEffect> effects = CreatePotionEffects(head);

            new BukkitRunnable() {
                @Override
                public void run() {

                    player.addPotionEffects(effects);
                    if (player.getInventory().getHelmet() == null || player.getInventory().getHelmet().getItemMeta() == null || !player.getInventory().getHelmet().getItemMeta().getDisplayName().equalsIgnoreCase(item.getItemMeta().getDisplayName())) {
                        cancel();
                    }
                    if (!player.isOnline()) {
                        cancel();
                    }

                }
            }.runTaskTimer(plugin, 20L, 20L);
        }
    }
}
