package me.none030.mortisheads.methods;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class DeathDropChecker {

    public static boolean getDrop(String head) {

        File file = new File("plugins/MortisHeads", "config.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        ConfigurationSection section = config.getConfigurationSection("heads." + head);
        assert section != null;

        if (section.contains("stays-on-after-death")) {
            return section.getBoolean("stays-on-after-death");
        }
        return false;
    }
}
