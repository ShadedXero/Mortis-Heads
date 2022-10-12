package me.none030.mortisheads.methods;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PotionEffectsCreation {

    public static List<PotionEffect> CreatePotionEffects(String head) {

        File file = new File("plugins/MortisHeads", "config.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        ConfigurationSection section = config.getConfigurationSection("heads." + head + ".effects");
        assert section != null;
        List<PotionEffect> effects = new ArrayList<>();
        for (String key : section.getKeys(false)) {
            ConfigurationSection potion = section.getConfigurationSection(key);
            assert potion != null;
            PotionEffect effect = new PotionEffect(Objects.requireNonNull(PotionEffectType.getByName(key)), potion.getInt("duration") * 20, potion.getInt("amplifier"), potion.getBoolean("ambient"), potion.getBoolean("particles"), potion.getBoolean("icon"));
            effects.add(effect);
        }
        return effects;
    }
}
