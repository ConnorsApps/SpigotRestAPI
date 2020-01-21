package io.wlf.mc.SpigotRestAPI.Models;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

public class wPlayerStats {
    public float exp;
    public int level;
    public double health;
    public double armor;
    public int food;

    public wPlayerStats (Player player) {
        this.exp = player.getExp();
        this.health = player.getHealth();
        this.level = player.getLevel();
        this.food = player.getFoodLevel();
        this.armor = player.getAttribute(Attribute.GENERIC_ARMOR).getValue();
    }
}