package me.none030.mortisheads.data;

import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class ItemData extends Data {

    private final ItemMeta meta;
    private final String idKey = "MortisHeadsId";

    public ItemData(@NotNull ItemMeta meta) {
        super(meta.getPersistentDataContainer());
        this.meta = meta;
    }

    public void setId(String id) {
        setString(idKey, id);
    }

    public String getId() {
        return getString(idKey);
    }

    public ItemMeta getMeta() {
        return meta;
    }
}
