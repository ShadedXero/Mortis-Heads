package me.none030.mortisheads.manager;

import me.none030.mortisheads.MortisHeads;
import me.none030.mortisheads.armorequipevent.ArmorEquipEvent;
import me.none030.mortisheads.config.ConfigManager;
import me.none030.mortisheads.heads.HeadManager;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;

public class MainManager {

    private final MortisHeads plugin = MortisHeads.getInstance();
    private HeadManager headManager;
    private ConfigManager configManager;

    public MainManager() {
        ArmorEquipEvent.registerListener(plugin);
        this.headManager = new HeadManager();
        this.configManager = new ConfigManager(this);
        plugin.getServer().getPluginCommand("heads").setExecutor(new HeadCommand(this));
    }

    public void reload() {
        HandlerList.unregisterAll(plugin);
        Bukkit.getScheduler().cancelTasks(plugin);
        ArmorEquipEvent.registerListener(plugin);
    }

    public HeadManager getHeadManager() {
        return headManager;
    }

    public void setHeadManager(HeadManager headManager) {
        this.headManager = headManager;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public void setConfigManager(ConfigManager configManager) {
        this.configManager = configManager;
    }
}
