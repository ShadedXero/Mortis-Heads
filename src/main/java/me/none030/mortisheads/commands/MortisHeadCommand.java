package me.none030.mortisheads.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
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
            if (args[0].equalsIgnoreCase("drop")) {
                if (args.length == 6) {
                    String head = args[1];
                    World world = Bukkit.getWorld(args[2]);
                    if (world != null) {
                        double x = Double.parseDouble(args[3]);
                        double y = Double.parseDouble(args[4]);
                        double z = Double.parseDouble(args[5]);

                        File file = new File("plugins/MortisHeads", "config.yml");
                        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
                        ConfigurationSection section = config.getConfigurationSection("heads");
                        assert section != null;
                        if (section.contains(head)) {
                            ItemStack item = CreateHead(head);
                            world.dropItemNaturally(new Location(world, x, y, z), item);
                        } else {
                            System.out.println("Invalid Head");
                        }
                    } else {
                        System.out.println("Invalid World");
                    }
                } else {
                    System.out.println("Usage: mortisheads drop <head-id> <world> <x> <y> <z>");
                }
            }
            if (args[0].equalsIgnoreCase("give")) {
                if (args.length == 3) {
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
                } else {
                    System.out.println("Usage: /<command> give <player_name> <head-id>");
                }
            }
        } else {
            Player player = (Player) sender;

            if (player.hasPermission("mortisheads.drop")) {
                if (args[0].equalsIgnoreCase("drop")) {
                    if (args.length == 6) {
                        String head = args[1];
                        World world = Bukkit.getWorld(args[2]);
                        if (world != null) {
                            double x = Double.parseDouble(args[3]);
                            double y = Double.parseDouble(args[4]);
                            double z = Double.parseDouble(args[5]);

                            File file = new File("plugins/MortisHeads", "config.yml");
                            FileConfiguration config = YamlConfiguration.loadConfiguration(file);
                            ConfigurationSection section = config.getConfigurationSection("heads");
                            assert section != null;
                            if (section.contains(head)) {
                                ItemStack item = CreateHead(head);
                                world.dropItemNaturally(new Location(world, x, y, z), item);
                            } else {
                                player.sendMessage("§cInvalid Head");
                            }
                        } else {
                            player.sendMessage("§cInvalid World");
                        }
                    } else {
                        player.sendMessage("§cUsage: mortisheads drop <head-id> <world> <x> <y> <z>");
                    }
                }
            } else {
                player.sendMessage("§cYou don't have permission to use this command");
            }
            if (player.hasPermission("mortisheads.give")) {
                if (args[0].equalsIgnoreCase("give")) {
                    if (args.length == 3) {
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
                                player.sendMessage("§cInvalid Head");
                            }
                        } else {
                            player.sendMessage("§cInvalid Target");
                        }
                    } else {
                        player.sendMessage("§cUsage: /<command> give <player_name> <head-id>");
                    }
                }
            } else {
                player.sendMessage("§cYou don't have permission to use this command");
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {

        File file = new File("plugins/MortisHeads", "config.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);

        if (args.length == 1) {
            List<String> arguments = new ArrayList<>();
            arguments.add("give");
            arguments.add("drop");

            return arguments;
        }

        if (args[0].equalsIgnoreCase("give")) {
            if (args.length == 3) {
                ConfigurationSection section = config.getConfigurationSection("heads");
                assert section != null;

                return new ArrayList<>(section.getKeys(false));
            }
        }

        if (args[0].equalsIgnoreCase("drop")) {
            if (args.length == 2) {
                ConfigurationSection section = config.getConfigurationSection("heads");
                assert section != null;

                return new ArrayList<>(section.getKeys(false));
            }
            if (args.length == 3) {
                List<String> arguments = new ArrayList<>();
                 for (World world : Bukkit.getWorlds()) {
                     arguments.add(world.getName());
                 }

                 return arguments;
            }
        }

        return null;
    }
}
