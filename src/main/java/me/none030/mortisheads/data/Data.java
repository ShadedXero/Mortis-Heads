package me.none030.mortisheads.data;

import me.none030.mortisheads.MortisHeads;
import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public abstract class Data {

    private final MortisHeads plugin = MortisHeads.getInstance();
    private final PersistentDataContainer container;

    public Data(@NotNull PersistentDataContainer container) {
        this.container = container;
    }

    public void setString(@NotNull String key, String value) {
        if (value == null) {
            remove(new NamespacedKey(plugin, key));
            return;
        }
        container.set(new NamespacedKey(plugin, key), PersistentDataType.STRING, value);
    }

    public String getString(@NotNull String key) {
        return container.get(new NamespacedKey(plugin, key), PersistentDataType.STRING);
    }

    public void setString(@NotNull NamespacedKey key, String value) {
        if (value == null) {
            remove(key);
            return;
        }
        container.set(key, PersistentDataType.STRING, value);
    }

    public String getString(@NotNull NamespacedKey key) {
        return container.get(key, PersistentDataType.STRING);
    }

    public void setByte(@NotNull String key, Byte value) {
        if (value == null) {
            remove(new NamespacedKey(plugin, key));
            return;
        }
        container.set(new NamespacedKey(plugin, key), PersistentDataType.BYTE, value);
    }

    public Byte getByte(@NotNull String key) {
        return container.get(new NamespacedKey(plugin, key), PersistentDataType.BYTE);
    }

    public void setByte(@NotNull NamespacedKey key, Byte value) {
        if (value == null) {
            remove(key);
            return;
        }
        container.set(key, PersistentDataType.BYTE, value);
    }

    public Byte getByte(@NotNull NamespacedKey key) {
        return container.get(key, PersistentDataType.BYTE);
    }

    public void setByteArray(@NotNull String key, byte[] value) {
        if (value == null) {
            remove(new NamespacedKey(plugin, key));
            return;
        }
        container.set(new NamespacedKey(plugin, key), PersistentDataType.BYTE_ARRAY, value);
    }

    public byte[] getByteArray(@NotNull String key) {
        return container.get(new NamespacedKey(plugin, key), PersistentDataType.BYTE_ARRAY);
    }

    public void setByteArray(@NotNull NamespacedKey key, byte[] value) {
        if (value == null) {
            remove(key);
            return;
        }
        container.set(key, PersistentDataType.BYTE_ARRAY, value);
    }

    public byte[] getByteArray(@NotNull NamespacedKey key) {
        return container.get(key, PersistentDataType.BYTE_ARRAY);
    }

    public void setShort(@NotNull String key, Short value) {
        if (value == null) {
            remove(new NamespacedKey(plugin, key));
            return;
        }
        container.set(new NamespacedKey(plugin, key), PersistentDataType.SHORT, value);
    }

    public Short getShort(@NotNull String key) {
        return container.get(new NamespacedKey(plugin, key), PersistentDataType.SHORT);
    }

    public void setShort(@NotNull NamespacedKey key, Short value) {
        if (value == null) {
            remove(key);
            return;
        }
        container.set(key, PersistentDataType.SHORT, value);
    }

    public Short getShort(@NotNull NamespacedKey key) {
        return container.get(key, PersistentDataType.SHORT);
    }

    public void setInt(@NotNull String key, Integer value) {
        if (value == null) {
            remove(new NamespacedKey(plugin, key));
            return;
        }
        container.set(new NamespacedKey(plugin, key), PersistentDataType.INTEGER, value);
    }

    public Integer getInt(@NotNull String key) {
        return container.get(new NamespacedKey(plugin, key), PersistentDataType.INTEGER);
    }

    public void setInt(@NotNull NamespacedKey key, Integer value) {
        if (value == null) {
            remove(key);
            return;
        }
        container.set(key, PersistentDataType.INTEGER, value);
    }

    public Integer getInt(@NotNull NamespacedKey key) {
        return container.get(key, PersistentDataType.INTEGER);
    }

    public void setIntArray(@NotNull String key, int[] value) {
        if (value == null) {
            remove(new NamespacedKey(plugin, key));
            return;
        }
        container.set(new NamespacedKey(plugin, key), PersistentDataType.INTEGER_ARRAY, value);
    }

    public int[] getIntArray(@NotNull String key) {
        return container.get(new NamespacedKey(plugin, key), PersistentDataType.INTEGER_ARRAY);
    }

    public void setIntArray(@NotNull NamespacedKey key, int[] value) {
        if (value == null) {
            remove(key);
            return;
        }
        container.set(key, PersistentDataType.INTEGER_ARRAY, value);
    }

    public int[] getIntArray(@NotNull NamespacedKey key) {
        return container.get(key, PersistentDataType.INTEGER_ARRAY);
    }

    public void setLong(@NotNull String key, Long value) {
        if (value == null) {
            remove(new NamespacedKey(plugin, key));
            return;
        }
        container.set(new NamespacedKey(plugin, key), PersistentDataType.LONG, value);
    }

    public Long getLong(@NotNull String key) {
        return container.get(new NamespacedKey(plugin, key), PersistentDataType.LONG);
    }

    public void setLong(@NotNull NamespacedKey key, Long value) {
        if (value == null) {
            remove(key);
            return;
        }
        container.set(key, PersistentDataType.LONG, value);
    }

    public Long getLong(@NotNull NamespacedKey key) {
        return container.get(key, PersistentDataType.LONG);
    }

    public void setLongArray(@NotNull String key, long[] value) {
        if (value == null) {
            remove(new NamespacedKey(plugin, key));
            return;
        }
        container.set(new NamespacedKey(plugin, key), PersistentDataType.LONG_ARRAY, value);
    }

    public long[] getLongArray(@NotNull String key) {
        return container.get(new NamespacedKey(plugin, key), PersistentDataType.LONG_ARRAY);
    }

    public void setLongArray(@NotNull NamespacedKey key, long[] value) {
        if (value == null) {
            remove(key);
            return;
        }
        container.set(key, PersistentDataType.LONG_ARRAY, value);
    }

    public long[] getLongArray(@NotNull NamespacedKey key) {
        return container.get(key, PersistentDataType.LONG_ARRAY);
    }

    public void setFloat(@NotNull String key, Float value) {
        if (value == null) {
            remove(new NamespacedKey(plugin, key));
            return;
        }
        container.set(new NamespacedKey(plugin, key), PersistentDataType.FLOAT, value);
    }

    public Float getFloat(@NotNull String key) {
        return container.get(new NamespacedKey(plugin, key), PersistentDataType.FLOAT);
    }

    public void setFloat(@NotNull NamespacedKey key, Float value) {
        if (value == null) {
            remove(key);
            return;
        }
        container.set(key, PersistentDataType.FLOAT, value);
    }

    public Float getFloat(@NotNull NamespacedKey key) {
        return container.get(key, PersistentDataType.FLOAT);
    }

    public void setDouble(@NotNull String key, Double value) {
        if (value == null) {
            remove(new NamespacedKey(plugin, key));
            return;
        }
        container.set(new NamespacedKey(plugin, key), PersistentDataType.DOUBLE, value);
    }

    public Double getDouble(@NotNull String key) {
        return container.get(new NamespacedKey(plugin, key), PersistentDataType.DOUBLE);
    }

    public void setDouble(@NotNull NamespacedKey key, Double value) {
        if (value == null) {
            remove(key);
            return;
        }
        container.set(key, PersistentDataType.DOUBLE, value);
    }

    public Double getDouble(@NotNull NamespacedKey key) {
        return container.get(key, PersistentDataType.DOUBLE);
    }

    public void remove(NamespacedKey key) {
        if (key == null) {
            return;
        }
        container.remove(key);
    }

    public void clear() {
        container.getKeys().clear();
    }

    public PersistentDataContainer getContainer() {
        return container;
    }
}
