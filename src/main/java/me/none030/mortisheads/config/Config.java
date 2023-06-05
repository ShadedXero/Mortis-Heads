package me.none030.mortisheads.config;

import me.none030.mortisheads.MortisHeads;
import me.none030.mortisheads.utils.MessageUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.HashMap;

public abstract class Config {

    private final MortisHeads plugin = MortisHeads.getInstance();
    private final String fileName;

    public Config(@NotNull String fileName) {
        this.fileName = fileName;
    }

    public abstract void loadConfig();

    public HashMap<String, Component> loadMessages(ConfigurationSection section) {
        HashMap<String, Component> messageById = new HashMap<>();
        if (section == null) {
            return messageById;
        }
        for (String key : section.getKeys(false)) {
            String id = key.replace("-", "_").toUpperCase();
            String message = section.getString(key);
            messageById.put(id, MessageUtils.color(message));
        }
        return messageById;
    }

    public File saveConfig() {
        File file = new File(plugin.getDataFolder(), fileName);
        if (!file.exists()) {
            plugin.saveResource(fileName, true);
        }
        return file;
    }

    public MortisHeads getPlugin() {
        return plugin;
    }

    public String getFileName() {
        return fileName;
    }
}
