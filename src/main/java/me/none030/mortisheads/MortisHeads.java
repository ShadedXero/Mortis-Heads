package me.none030.mortisheads;

import me.none030.mortisheads.commands.MortisHeadCommand;
import me.none030.mortisheads.events.GunsEvent;
import me.none030.mortisheads.events.MortisHeadsEvents;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import static me.none030.mortisheads.methods.SavingFiles.SaveFiles;

public final class MortisHeads extends JavaPlugin {

    public static Plugin plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginCommand("mortisheads").setExecutor(new MortisHeadCommand());
        getServer().getPluginManager().registerEvents(new MortisHeadsEvents(), this);
        plugin = this;
        SaveFiles();
        boolean guns = getServer().getPluginManager().getPlugin("CrackShot") != null;
        if (guns) {
            getServer().getPluginManager().registerEvents(new GunsEvent(), this);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
