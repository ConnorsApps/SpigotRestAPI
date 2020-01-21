package io.wlf.mc.SpigotRestAPI.Services;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import io.wlf.mc.SpigotRestAPI.Models.wResponse;
import io.wlf.mc.SpigotRestAPI.Models.wWorld;

public class WorldService extends WebResponseService {
    private JavaPlugin plugin;

    public WorldService (JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public wResponse getAllWorlds () {
        List<wWorld> worlds = new ArrayList<>();
        for (World world : plugin.getServer().getWorlds()) {
            worlds.add(new wWorld(world));
        }
        return success(worlds.toArray(new wWorld[0]));
    }

    public wResponse getWorld (String name) {
        World world = getWorldByName(name);
        if (world != null) {
            return success(new wWorld(world));
        }
        return failure("not found");
    }

    private World getWorldByName (String name) {
        return plugin.getServer().getWorld(name);
    }
}