package me.none030.mortisheads;

import me.none030.mortisheads.manager.MainManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class MortisHeads extends JavaPlugin {

    private static MortisHeads Instance;
    private boolean crackShot;
    private MainManager manager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        Instance = this;
        crackShot = getServer().getPluginManager().getPlugin("CrackShot") != null;
        manager = new MainManager();
    }

    public static MortisHeads getInstance() {
        return Instance;
    }

    public boolean hasCrackShot() {
        return crackShot;
    }

    public MainManager getManager() {
        return manager;
    }
}
