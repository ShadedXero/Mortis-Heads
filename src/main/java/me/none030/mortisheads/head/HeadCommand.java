package me.none030.mortisheads.head;

import me.none030.mortisheads.MortisHeads;
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

import static me.none030.mortisheads.head.HeadMessages.*;

public class HeadCommand implements TabExecutor {

    public MortisHeads plugin = MortisHeads.getInstance();

    private final HeadManager manager;

    public HeadCommand(HeadManager manager) {
        this.manager = manager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reload")) {
                if (!sender.hasPermission("mortisheads.reload")) {
                    sender.sendMessage(NO_PERMISSION);
                    return false;
                }
                plugin.setHeadManager(new HeadManager());
            }
        }

        if (args.length == 6) {
            if (args[0].equalsIgnoreCase("drop")) {
                if (!sender.hasPermission("mortisheads.drop")) {
                    sender.sendMessage(NO_PERMISSION);
                    return false;
                }
                String headId = args[1];
                World world = Bukkit.getWorld(args[2]);
                if (world == null) {
                    sender.sendMessage(INVALID_WORLD);
                    return false;
                }
                double x = Double.parseDouble(args[3]);
                double y = Double.parseDouble(args[4]);
                double z = Double.parseDouble(args[5]);
                if (manager.getHeadById().get(headId) == null) {
                    sender.sendMessage(INVALID_HEAD);
                    return false;
                }
                Head head = manager.getHeadById().get(headId);
                world.dropItemNaturally(new Location(world, x, y, z), head.getItem());
            }
        }
        if (args.length == 3) {
            if (args[0].equalsIgnoreCase("give")) {
                if (!sender.hasPermission("mortisheads.give")) {
                    sender.sendMessage(NO_PERMISSION);
                    return false;
                }
                Player target = Bukkit.getPlayer(args[1]);
                if (target == null) {
                    sender.sendMessage(INVALID_TARGET);
                    return false;
                }
                String headId = args[2];
                if (manager.getHeadById().get(headId) == null) {
                    sender.sendMessage(INVALID_HEAD);
                    return false;
                }
                Head head = manager.getHeadById().get(headId);
                if (target.getInventory().firstEmpty() != -1) {
                    target.getInventory().addItem(head.getItem());
                } else {
                    target.getWorld().dropItemNaturally(target.getLocation(), head.getItem());
                }
            }
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (args.length == 1) {
            List<String> arguments = new ArrayList<>();
            arguments.add("give");
            arguments.add("drop");

            return arguments;
        }

        if (args[0].equalsIgnoreCase("give")) {
            if (args.length == 3) {
                return manager.getHeadIds();
            }
        }

        if (args[0].equalsIgnoreCase("drop")) {
            if (args.length == 2) {
                return manager.getHeadIds();
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
