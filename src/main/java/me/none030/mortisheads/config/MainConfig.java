package me.none030.mortisheads.config;

import me.none030.mortisheads.heads.Head;
import me.none030.mortisheads.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainConfig extends Config {

    private final ConfigManager configManager;

    public MainConfig(ConfigManager configManager) {
        super("config.yml");
        this.configManager = configManager;
        loadConfig();
    }

    @Override
    public void loadConfig() {
        File file = saveConfig();
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        loadHeads(config.getConfigurationSection("heads"));
    }

    private void loadHeads(ConfigurationSection section) {
        if (section == null) {
            return;
        }
        for (String key : section.getKeys(false)) {
            ConfigurationSection head = section.getConfigurationSection(key);
            if (head == null) {
                continue;
            }
            ItemBuilder builder = new ItemBuilder(Material.PLAYER_HEAD, 1);
            if (head.contains("texture")) {
                String texture = head.getString("texture");
                builder.setTexture(texture);
            }
            if (head.contains("name")) {
                String name = head.getString("name");
                builder.setName(name);
            }
            if (head.contains("lore")) {
                List<String> lore = head.getStringList("lore");
                builder.setLore(lore);
            }
            if (head.contains("enchants")) {
                List<String> enchants = head.getStringList("enchants");
                builder.addEnchants(enchants);
            }
            if (head.contains("flags")) {
                List<String> flags = head.getStringList("flags");
                builder.addFlags(flags);
            }
            boolean onDeath = head.getBoolean("stays-on-after-death");
            List<PotionEffect> effects = new ArrayList<>();
            ConfigurationSection effectSection = head.getConfigurationSection("effects");
            if (effectSection == null) {
                continue;
            }
            for (String potionEffect : effectSection.getKeys(false)) {
                PotionEffectType type = PotionEffectType.getByName(potionEffect);
                if (type == null) {
                    continue;
                }
                int duration = effectSection.getInt(potionEffect + ".duration") * 20;
                int amplifier = effectSection.getInt(potionEffect + ".amplifier");
                boolean ambient = effectSection.getBoolean(potionEffect + ".ambient");
                boolean particles = effectSection.getBoolean(potionEffect + ".particles");
                boolean icon = effectSection.getBoolean(potionEffect + ".icon");
                PotionEffect effect = new PotionEffect(type, duration, amplifier, ambient, particles, icon);
                effects.add(effect);
            }
            Head heads = new Head(key, builder.getItem(), effects, onDeath);
            configManager.getManager().getHeadManager().getHeadById().put(key, heads);
        }
    }
}
