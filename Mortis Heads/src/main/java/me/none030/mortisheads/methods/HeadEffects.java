package me.none030.mortisheads.methods;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

import static me.none030.mortisheads.MortisHeads.plugin;
import static me.none030.mortisheads.methods.ItemChecker.getEffects;
import static me.none030.mortisheads.methods.PotionEffectsCreation.CreatePotionEffects;

public class HeadEffects {

    public static void GiveEffects(Player player, ItemStack item) {

        String head = getEffects(item);

        if (head != null) {
            System.out.println("head is not null");

            List<PotionEffect> effects = CreatePotionEffects(head);

            System.out.println("potions are fine");

            new BukkitRunnable() {
                @Override
                public void run() {

                    player.addPotionEffects(effects);
                    if (player.getInventory().getHelmet() == null) {
                        cancel();
                    }
                    if (!player.isOnline()) {
                        cancel();
                    }

                }
            }.runTaskTimer(plugin, 0L, 20L);
        }
    }
}
