package me.none030.mortisheads.manager;

import me.none030.mortisheads.heads.Head;
import me.none030.mortisheads.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class HeadCommand implements TabExecutor {

    private final MainManager manager;

    public HeadCommand(MainManager manager) {
        this.manager = manager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0) {
            return false;
        }
        if (args[0].equalsIgnoreCase("give")) {
            if (!sender.hasPermission("mortisheads.give")) {
                sender.sendMessage(MessageUtils.color("&cYou don't have permission to use this"));
                return false;
            }
            if (args.length != 3) {
                sender.sendMessage(MessageUtils.color("&cUsage: /heads give <player_name> <head-id>"));
                return false;
            }
            Player target = Bukkit.getPlayer(args[1]);
            if (target == null) {
                sender.sendMessage(MessageUtils.color("&cPlease enter a valid target"));
                return false;
            }
            Head head = manager.getHeadManager().getHeadById().get(args[2]);
            if (head == null) {
                sender.sendMessage(MessageUtils.color("&cPlease enter a valid head id"));
                return false;
            }
            if (target.getInventory().firstEmpty() != -1) {
                target.getInventory().addItem(head.getItem());
            } else {
                target.getWorld().dropItemNaturally(target.getLocation(), head.getItem());
            }
            sender.sendMessage(MessageUtils.color("&aHead given to the specified target"));
        }
        if (args[0].equalsIgnoreCase("drop")) {
            if (!sender.hasPermission("mortisheads.drop")) {
                sender.sendMessage(MessageUtils.color("&cYou don't have permission to use this"));
                return false;
            }
            Head head = manager.getHeadManager().getHeadById().get(args[1]);
            if (head == null) {
                sender.sendMessage(MessageUtils.color("&cPlease enter a valid head id"));
                return false;
            }
            World world = Bukkit.getWorld(args[2]);
            if (world == null) {
                sender.sendMessage(MessageUtils.color("&cPlease enter a valid world"));
                return false;
            }
            double x = Double.parseDouble(args[3]);
            double y = Double.parseDouble(args[4]);
            double z = Double.parseDouble(args[5]);
            world.dropItemNaturally(new Location(world, x, y, z), head.getItem());
            sender.sendMessage(MessageUtils.color("&aHead dropped to the specified location"));
        }
        if (args[0].equalsIgnoreCase("reload")) {
            if (args[0].equalsIgnoreCase("reload")) {
                if (!sender.hasPermission("mortisheads.reload")) {
                    sender.sendMessage(MessageUtils.color("&cYou don't have permission to use this"));
                    return false;
                }
                manager.reload();
                sender.sendMessage(MessageUtils.color("&aMortisHeads Reloaded"));
            }
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) {
            List<String> arguments = new ArrayList<>();
            arguments.add("give");
            arguments.add("drop");
            arguments.add("reload");
            return arguments;
        }
        if (args[0].equalsIgnoreCase("give")) {
            if (args.length == 3) {
                return new ArrayList<>(manager.getHeadManager().getHeadById().keySet());
            }
        }
        if (args[0].equalsIgnoreCase("drop")) {
            if (args.length == 2) {
                return new ArrayList<>(manager.getHeadManager().getHeadById().keySet());
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
