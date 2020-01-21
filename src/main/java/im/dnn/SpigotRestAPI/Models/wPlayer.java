package im.dnn.SpigotRestAPI.Models;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class wPlayer extends wEntity {
    public wPlayerStats stats;
    public wPlayerInventory inventory;
    public wEffect[] effects;
    public boolean online;

    public String displayName;

    public wPlayer (Player player) {
        this.fromBukkit(player, false);
        this.online = true;
    }

    public wPlayer (Player player, boolean extended) {
        this.fromBukkit(player, extended);
        this.online = true;
    }

    public wPlayer (OfflinePlayer offlinePlayer) {
        this.fromBukkit(offlinePlayer.getPlayer(), false);
        this.online = false;
        this.name = offlinePlayer.getName();
        this.uuid = offlinePlayer.getUniqueId().toString();
    }

    public wPlayer (OfflinePlayer offlinePlayer, boolean extended) {
        this.fromBukkit(offlinePlayer.getPlayer(), extended);
        this.online = false;
        this.name = offlinePlayer.getName();
        this.uuid = offlinePlayer.getUniqueId().toString();
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
