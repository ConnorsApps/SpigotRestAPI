package io.wlf.mc.SpigotRestAPI.Models;

import org.bukkit.entity.Player;

public class wPlayer extends wEntity {
    public wPlayerStats stats;
    public wPlayerInventory inventory;
    public wEffect[] effects;

    public String displayName;

    public wPlayer (Player player) {
        this.fromBukkit(player, false);
    }

    public wPlayer (Player player, boolean extended) {
        this.fromBukkit(player, extended);
    }

    public void fromBukkit (Player player, boolean extended) {
        super.fromBukkit(player);
        if (player != null) {
            this.displayName = player.getDisplayName();

            if (extended) {
                this.stats = new wPlayerStats(player);
                this.inventory = new wPlayerInventory(player);
                this.effects = new wPlayerEffects(player).effects;
            }
        }
    }
}
