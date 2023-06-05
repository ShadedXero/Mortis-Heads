package me.none030.mortisheads.config;

import me.none030.mortisheads.manager.MainManager;

public class ConfigManager {

    private final MainManager manager;
    private final MainConfig mainConfig;

    public ConfigManager(MainManager manager) {
        this.manager = manager;
        this.mainConfig = new MainConfig(this);
    }

    public MainManager getManager() {
        return manager;
    }

    public MainConfig getMainConfig() {
        return mainConfig;
    }
}
