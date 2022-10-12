package me.none030.mortisheads.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static me.none030.mortisheads.methods.HeadCreation.CreateHead;

public class MortisHeadCommand implements TabExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {

        if (!(sender instanceof Player)) {
            if (args.length == 3) {
                if (args[0].equalsIgnoreCase("give")) {
                    Player target = Bukkit.getPlayer(args[1]);
                    if (target != null) {
                        String head = args[2];
                        File file = new File("plugins/MortisHeads", "config.yml");
                        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
                        ConfigurationSection section = config.getConfigurationSection("heads");
                        assert section != null;
                        if (section.contains(head)) {
                            ItemStack item = CreateHead(head);
                            if (target.getInventory().firstEmpty() != -1) {
                                target.getInventory().addItem(item);
                                return true;
                            } else {
                                target.getWorld().dropItemNaturally(target.getLocation(), item);
                            }
                        } else {
                            System.out.println("Invalid Head");
                        }
                    } else {
                        System.out.println("Invalid Target");
                    }
                }
            } else {
                System.out.println("Usage: /<command> give <player_name> <head-id>");
            }
        } else {

            Player player = (Player) sender;

            if (player.hasPermission("mortisheads.commands")) {
                if (args.length == 3) {
                    if (args[0].equalsIgnoreCase("give")) {
                        Player target = Bukkit.getPlayer(args[1]);
                        if (target != null) {
                            String head = args[2];
                            File file = new File("plugins/MortisHeads", "config.yml");
                            FileConfiguration config = YamlConfiguration.loadConfiguration(file);
                            ConfigurationSection section = config.getConfigurationSection("heads");
                            assert section != null;
                            if (section.contains(head)) {
                                ItemStack item = CreateHead(head);
                                if (target.getInventory().firstEmpty() != -1) {
                                    target.getInventory().addItem(item);
                                    return true;
                                } else {
                                    target.getWorld().dropItemNaturally(target.getLocation(), item);
                                }
                            } else {
                                player.sendMessage("Invalid Head");
                            }
                        } else {
                            player.sendMessage("Invalid Target");
                        }
                    }
                } else {
                    player.sendMessage("Usage: /<command> give <player_name> <head-id>");
                }
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {

        if (args.length == 1) {
            List<String> arguments = new ArrayList<>();
            arguments.add("give");

            return arguments;
        }
        if (args.length == 3) {

            File file = new File("plugins/MortisHeads", "config.yml");
            FileConfiguration config = YamlConfiguration.loadConfiguration(file);
            ConfigurationSection section = config.getConfigurationSection("heads");
            assert section != null;

            return new ArrayList<>(section.getKeys(false));
        }

        return null;
    }
}
