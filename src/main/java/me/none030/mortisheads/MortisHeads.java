package me.none030.mortisheads;

import me.none030.mortisheads.head.HeadManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class MortisHeads extends JavaPlugin {

    private static MortisHeads Instance;
    private boolean crackShot = false;
    private HeadManager headManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        Instance = this;
        if (getServer().getPluginManager().getPlugin("CrackShot") != null) {
            crackShot = true;
        }
        headManager = new HeadManager();
    }

    public static MortisHeads getInstance() {
        return Instance;
    }

    public boolean isCrackShot() {
        return crackShot;
    }

    public HeadManager getHeadManager() {
        return headManager;
    }

    public void setHeadManager(HeadManager headManager) {
        this.headManager = headManager;
    }
}
