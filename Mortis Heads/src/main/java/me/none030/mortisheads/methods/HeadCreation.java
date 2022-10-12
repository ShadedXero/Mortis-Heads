package me.none030.mortisheads.methods;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class HeadCreation {

    public static ItemStack CreateHead(String head) {

        File file = new File("plugins/MortisHeads", "config.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        ConfigurationSection section = config.getConfigurationSection("heads." + head);
        assert section != null;

        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        assert meta != null;
        if (section.contains("name")) {
            meta.setDisplayName(section.getString("name"));
        }
        if (section.contains("texture")) {
            GameProfile profile = new GameProfile(UUID.randomUUID(), null);
            profile.getProperties().put("textures", new Property("textures", section.getString("texture")));
            try {
                Method mtd = meta.getClass().getDeclaredMethod("setProfile", GameProfile.class);
                mtd.setAccessible(true);
                mtd.invoke(meta, profile);
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException ex) {
                ex.printStackTrace();
            }
        }
        if (section.contains("lore")) {
           meta.setLore(section.getStringList("lore"));
        }
        if (section.contains("enchants")) {
            List<String> enchants = section.getStringList("enchants");
            for (String line : enchants) {

                String[] raw = line.split(":");
                meta.addEnchant(Objects.requireNonNull(Enchantment.getByName(raw[0])), Integer.parseInt(raw[1]), true);

            }
        }
        if (section.contains("flags")) {
            List<String> flags = section.getStringList("flags");
            for (String flag : flags) {
                meta.addItemFlags(ItemFlag.valueOf(flag));
            }
        }
        item.setItemMeta(meta);

        return item;
    }
}
