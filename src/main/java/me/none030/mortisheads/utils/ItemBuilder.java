package me.none030.mortisheads.utils;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static me.none030.mortisheads.utils.MessageUtils.color;

public class ItemBuilder {

    private final ItemStack item;

    public ItemBuilder(ItemStack item) {
        this.item = item;
    }

    public ItemBuilder(Material material, int amount) {
        this.item = new ItemStack(material, amount);
    }

    public void setCustomModelData(int customModelData) {
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(customModelData);
        item.setItemMeta(meta);
    }

    public void setName(String name) {
        ItemMeta meta = item.getItemMeta();
        meta.displayName(color(name));
        item.setItemMeta(meta);
    }

    public void setLore(List<String> lore) {
        ItemMeta meta = item.getItemMeta();
        List<Component> components = new ArrayList<>();
        for (String line : lore) {
            components.add(color(line));
        }
        meta.lore(components);
        item.setItemMeta(meta);
    }

    public void addEnchants(List<String> enchants) {
        HashMap<Enchantment, Integer> enchantments = convertEnchants(enchants);
        ItemMeta meta = item.getItemMeta();
        for (Enchantment enchant : enchantments.keySet()) {
            int level = enchantments.get(enchant);
            meta.addEnchant(enchant, level, true);
        }
        item.setItemMeta(meta);
    }

    public void addFlags(List<String> flags) {
        ItemMeta meta = item.getItemMeta();
        for (String rawFlag : flags) {
            ItemFlag flag;
            try {
                flag = ItemFlag.valueOf(rawFlag);
            }catch (IllegalArgumentException exp) {
                continue;
            }
            meta.addItemFlags(flag);
        }
        item.setItemMeta(meta);
    }

    public void setTexture(String texture) {
        if (!item.getType().equals(Material.PLAYER_HEAD)) {
            return;
        }
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        profile.getProperties().put("textures", new Property("textures", texture));
        try {
            Method mtd = meta.getClass().getDeclaredMethod("setProfile", GameProfile.class);
            mtd.setAccessible(true);
            mtd.invoke(meta, profile);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
            ex.printStackTrace();
        }
        item.setItemMeta(meta);
    }

    private HashMap<Enchantment, Integer> convertEnchants(List<String> enchants) {
        HashMap<Enchantment, Integer> enchantments = new HashMap<>();
        for (String line : enchants) {
            String[] raw = line.split(":");
            NamespacedKey key = NamespacedKey.minecraft(raw[0]);
            Enchantment enchant = Enchantment.getByKey(key);
            int level = Integer.parseInt(raw[1]);
            if (enchant == null) {
                continue;
            }
            enchantments.put(enchant, level);
        }
        return enchantments;
    }

    public ItemStack getItem() {
        return item;
    }
}
