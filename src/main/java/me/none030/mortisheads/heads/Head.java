package me.none030.mortisheads.heads;

import me.none030.mortisheads.MortisHeads;
import me.none030.mortisheads.data.ItemData;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.List;

public class Head {

    private final MortisHeads plugin = MortisHeads.getInstance();
    private final String id;
    private final ItemStack item;
    private final List<PotionEffect> effects;
    private final boolean onDeath;

    public Head(String id, ItemStack item, List<PotionEffect> effects, boolean onDeath) {
        this.id = id;
        this.item = item;
        this.effects = effects;
        this.onDeath = onDeath;
        createHead();
    }

    private void createHead() {
        ItemData data = new ItemData(item.getItemMeta());
        data.setId(id);
        item.setItemMeta(data.getMeta());
    }

    public void addEffects(HeadManager headManager, Player player) {
        headManager.removeTaskId(player);
        BukkitTask task = new BukkitRunnable() {
            @Override
            public void run() {
                if (!headManager.getScoping().contains(player)) {
                    if (headManager.getHead(player.getInventory().getHelmet()) == null) {
                        cancel();
                    }
                }
                for (PotionEffect effect : effects) {
                    if (effect.getType().equals(PotionEffectType.SPEED)) {
                        if (headManager.getScoping().contains(player)) {
                            continue;
                        }
                    }
                    player.addPotionEffect(effect);
                }
            }
        }.runTaskTimer(plugin, 0L, 20L);
        headManager.addTaskId(player, task.getTaskId());
    }

    public String getId() {
        return id;
    }

    public ItemStack getItem() {
        return item;
    }

    public List<PotionEffect> getEffects() {
        return effects;
    }

    public boolean isOnDeath() {
        return onDeath;
    }
}
