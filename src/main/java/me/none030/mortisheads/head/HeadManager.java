package me.none030.mortisheads.head;

import me.none030.mortisheads.MortisHeads;
import me.none030.mortisheads.config.ConfigManager;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HeadManager {

    public MortisHeads plugin = MortisHeads.getInstance();

    private ConfigManager configManager;
    private final List<String> headIds;
    private final List<Head> heads;

    private final HashMap<ItemStack, Head> headByItem;
    private final HashMap<String, Head> headById;

    public HeadManager() {
        headIds = new ArrayList<>();
        heads = new ArrayList<>();
        headByItem = new HashMap<>();
        headById = new HashMap<>();
        configManager = new ConfigManager(this);
        HeadCommand headCommand = new HeadCommand(this);
        plugin.getServer().getPluginCommand("heads").setExecutor(headCommand);
        plugin.getServer().getPluginManager().registerEvents(new HeadListener(this), plugin);
        if (plugin.isCrackShot()) {
            plugin.getServer().getPluginManager().registerEvents(new CrackShotListener(this), plugin);
        }
    }

    public List<String> getHeadIds() {
        return headIds;
    }
    public List<Head> getHeads() {
        return heads;
    }
    public HashMap<ItemStack, Head> getHeadByItem() {
        return headByItem;
    }
    public HashMap<String, Head> getHeadById() {
        return headById;
    }
}
