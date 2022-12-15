package me.none030.mortisheads.config;

import me.none030.mortisheads.MortisHeads;
import me.none030.mortisheads.head.Head;
import me.none030.mortisheads.head.HeadManager;
import me.none030.mortisheads.utils.ItemBuilder;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static me.none030.mortisheads.utils.MessageUtils.colorMessage;

public class ConfigManager {

    public MortisHeads plugin = MortisHeads.getInstance();
    private final HeadManager manager;

    public ConfigManager(HeadManager manager) {
        this.manager = manager;
        loadConfig();
    }

    private void loadConfig() {
        File file = saveConfig();
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        ConfigurationSection section = config.getConfigurationSection("heads");
        if (section == null) {
            return;
        }
        for (String key : section.getKeys(false)) {
            ConfigurationSection head = section.getConfigurationSection(key);
            if (head == null) {
                plugin.getLogger().severe(key + " does not work");
                continue;
            }
            String texture = head.getString("texture");
            String name = colorMessage(head.getString("name"));
            List<String> lore = head.getStringList("lore");
            lore.forEach(s -> lore.set(lore.indexOf(s), colorMessage(s)));
            List<String> enchants = head.getStringList("enchants");
            List<String> flags = head.getStringList("flags");
            ItemBuilder builder = new ItemBuilder("PLAYER_HEAD", texture, 1, name, lore, enchants, flags);
            ItemStack item = builder.Build();
            boolean onDeath = head.getBoolean("stays-on-after-death");
            List<PotionEffect> effects = new ArrayList<>();
            ConfigurationSection effectSection = head.getConfigurationSection("effects");
            if (effectSection == null) {
                plugin.getLogger().severe("Potion effects in " + key + " does not exist");
                continue;
            }
            for (String potionEffect : effectSection.getKeys(false)) {
                int duration = effectSection.getInt(potionEffect + ".duration") * 20;
                int amplifier = effectSection.getInt(potionEffect + ".amplifier");
                boolean ambient = effectSection.getBoolean(potionEffect + ".ambient");
                boolean particles = effectSection.getBoolean(potionEffect + ".particles");
                boolean icon = effectSection.getBoolean(potionEffect + ".icon");
                PotionEffect effect = new PotionEffect(Objects.requireNonNull(PotionEffectType.getByName(potionEffect)), duration, amplifier, ambient, particles, icon);
                effects.add(effect);
            }
            Head heads = new Head(key, item, effects, onDeath);
            manager.getHeadIds().add(key);
            manager.getHeads().add(heads);
            manager.getHeadByItem().put(item, heads);
            manager.getHeadById().put(key, heads);
        }
    }

    private File saveConfig() {
        File file = new File(plugin.getDataFolder(), "config.yml");
        if (!file.exists()) {
            plugin.saveResource("config.yml", true);
        }
        return file;
    }
}
