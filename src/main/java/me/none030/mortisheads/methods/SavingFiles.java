package me.none030.mortisheads.methods;

import java.io.File;

import static me.none030.mortisheads.MortisHeads.plugin;

public class SavingFiles {

    public static void SaveFiles() {

        File file = new File("plugins/MortisHeads", "config.yml");

        if (!file.exists()) {
            plugin.saveResource("config.yml", true);
        }
    }
}
