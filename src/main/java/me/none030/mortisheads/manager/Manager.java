package me.none030.mortisheads.manager;

import net.kyori.adventure.text.Component;

import java.util.HashMap;

public abstract class Manager {

    private final HashMap<String, Component> messageById;

    public Manager() {
        this.messageById = new HashMap<>();
    }

    public void addMessage(String id, Component message) {
        messageById.put(id, message);
    }

    public void addMessages(HashMap<String, Component> messageById) {
        this.messageById.putAll(messageById);
    }

    public Component getMessage(String id) {
        return messageById.get(id);
    }
}
