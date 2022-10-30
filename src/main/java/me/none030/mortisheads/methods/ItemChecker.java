package me.none030.mortisheads.methods;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class ItemChecker {

    public static String getEffects(ItemStack item) {

        File file = new File("plugins/MortisHeads", "config.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        ConfigurationSection section = config.getConfigurationSection("heads");

        assert section != null;
        for (String key : section.getKeys(false)) {

            ConfigurationSection head = section.getConfigurationSection(key);
            assert head != null;

            String name = null;
            List<String> lore = null;
            List<String> enchants = null;

            if (head.contains("name")) {
                name = Objects.requireNonNull(head.getString("name")).replace("&", "§");
            }
            if (head.contains("lore")) {
                List<String> lore1 = head.getStringList("lore");
                lore1.replaceAll(s -> s.replace("&", "§"));
                lore = lore1;
            }
            if (head.contains("enchants")) {
                enchants = head.getStringList("enchants");
            }


            boolean name1 = false;
            boolean lore1 = false;
            boolean enchants1 = false;

            if (item.getItemMeta() == null) {
                name1 = true;
                lore1 = true;
                enchants1 = true;
            } else {
                if (!item.getItemMeta().hasDisplayName() || name == null) {
                    name1 = true;
                } else if (item.getItemMeta().getDisplayName().equalsIgnoreCase(name)) {
                    name1 = true;
                }
                if (!item.getItemMeta().hasLore() || lore == null) {
                    lore1 = true;
                } else if (item.getItemMeta().getLore().equals(lore)) {
                    lore1 = true;
                }
                if (!item.getItemMeta().hasEnchants() || enchants == null) {
                    enchants1 = true;
                } else {
                    HashMap<Enchantment, Integer> enchantments = new HashMap<>();
                    for (String line : enchants) {
                        String[] raw = line.split(":");
                        enchantments.put(Enchantment.getByName(String.valueOf(raw[0])), Integer.parseInt(raw[1]));
                    }
                    if (item.getEnchantments().equals(enchantments)) {
                        enchants1 = true;
                    }
                }
            }
            if (name1 && lore1 && enchants1) {
                return key;
            }
        }
        return null;

    }
}
