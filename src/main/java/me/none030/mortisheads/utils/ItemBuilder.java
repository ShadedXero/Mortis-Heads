package me.none030.mortisheads.utils;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class ItemBuilder {

    private final Material material;
    private final String texture;
    private final int amount;
    private final String name;
    private final List<String> lore;
    private final HashMap<Enchantment, Integer> enchants;
    private final List<ItemFlag> flags;

    public ItemBuilder(String material, String texture, int amount, String name, List<String> lore, List<String> enchants, List<String> flags) {
        this.material = Material.valueOf(material);
        this.texture = texture;
        this.amount = amount;
        this.name = name;
        this.lore = lore;
        this.enchants = new HashMap<>();
        for (String enchant : enchants) {
            String[] raw = enchant.split(":");
            this.enchants.put(Enchantment.getByName(raw[0]), Integer.parseInt(raw[1]));
        }
        this.flags = new ArrayList<>();
        for (String flag : flags) {
            this.flags.add(ItemFlag.valueOf(flag));
        }
    }

    public ItemStack Build() {
        if (material == null|| amount == 0) {
            return null;
        }
        ItemStack item = new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();
        if (texture != null) {
            GameProfile profile = new GameProfile(UUID.randomUUID(), null);
            profile.getProperties().put("textures", new Property("textures", texture));
            try {
                Method mtd = meta.getClass().getDeclaredMethod("setProfile", GameProfile.class);
                mtd.setAccessible(true);
                mtd.invoke(meta, profile);
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException ex) {
                ex.printStackTrace();
            }
        }
        if (name != null) {
            meta.setDisplayName(name);
        }
        if (lore != null) {
            meta.setLore(lore);
        }
        if (enchants != null) {
            for (Enchantment enchant : enchants.keySet()) {
                meta.addEnchant(enchant, enchants.get(enchant), true);
            }
        }
        if (flags != null) {
            for (ItemFlag flag : flags) {
                meta.addItemFlags(flag);
            }
        }
        item.setItemMeta(meta);
        return item;
    }
}
