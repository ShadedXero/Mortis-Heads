package me.none030.mortisheads.heads;

import me.none030.mortisheads.MortisHeads;
import me.none030.mortisheads.data.ItemData;
import me.none030.mortisheads.manager.Manager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class HeadManager extends Manager {

    private final HashMap<String, Head> headById;
    private final HashMap<Player, Integer> taskIdByPlayer;
    private final Set<Player> scoping;

    public HeadManager() {
        this.headById = new HashMap<>();
        this.taskIdByPlayer = new HashMap<>();
        this.scoping = new HashSet<>();
        MortisHeads plugin = MortisHeads.getInstance();
        plugin.getServer().getPluginManager().registerEvents(new HeadListener(this), plugin);
        if (plugin.hasCrackShot()) {
            plugin.getServer().getPluginManager().registerEvents(new CrackShotListener(this), plugin);
        }
        if (plugin.hasWeaponMechanics()) {
            plugin.getServer().getPluginManager().registerEvents(new WeaponMechanicsListener(this), plugin);
        }
    }

    public Head getHead(ItemStack item) {
        if (item == null || item.getType().isAir()) {
            return null;
        }
        String id = new ItemData(item.getItemMeta()).getId();
        if (id == null) {
            return null;
        }
        return headById.get(id);
    }

    public void addTaskId(Player player, int taskId) {
        Integer previousTaskId = taskIdByPlayer.get(player);
        if (previousTaskId != null) {
            removeTaskId(taskId);
        }
        taskIdByPlayer.put(player, taskId);
    }

    public void removeTaskId(Player player) {
        Integer taskId = taskIdByPlayer.get(player);
        if (taskId == null) {
            return;
        }
        removeTaskId(taskId);
        taskIdByPlayer.remove(player);
    }

    private void removeTaskId(int taskId) {
        Bukkit.getScheduler().cancelTask(taskId);
    }

    public HashMap<String, Head> getHeadById() {
        return headById;
    }

    public HashMap<Player, Integer> getTaskIdByPlayer() {
        return taskIdByPlayer;
    }

    public Set<Player> getScoping() {
        return scoping;
    }
}
