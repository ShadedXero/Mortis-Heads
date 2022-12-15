package me.none030.mortisheads.head;

import me.none030.mortisheads.MortisHeads;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class Head {

    public MortisHeads plugin = MortisHeads.getInstance();

    private String id;
    private final ItemStack item;
    private final List<PotionEffect> effects;
    private final boolean onDeath;

    public Head(String id, ItemStack item, List<PotionEffect> effects, boolean onDeath) {
        this.id = id;
        this.item = item;
        this.effects = effects;
        this.onDeath = onDeath;
    }

    public void GiveEffect(Player player) {
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
        }.runTaskTimer(plugin, 0L, 20L);
    }

    public ItemStack getItem() {
        return item;
    }

    public boolean isOnDeath() {
        return onDeath;
    }
}
