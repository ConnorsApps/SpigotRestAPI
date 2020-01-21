package im.dnn.SpigotRestAPI.Services;

import im.dnn.SpigotRestAPI.Models.wPlayer;
import im.dnn.SpigotRestAPI.Models.wResponse;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerService extends WebResponseService {
    private JavaPlugin plugin;

    public PlayerService (JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public wResponse getAllPlayers () {
        List<wPlayer> players = new ArrayList<>();
        for (Player player : plugin.getServer().getOnlinePlayers()) {
            players.add(new wPlayer(player));
        }
        return success(players.toArray(new wPlayer[0]));
    }

    public wResponse getAllOfflinePlayers () {
        List<wPlayer> players = new ArrayList<>();
        for (OfflinePlayer player : plugin.getServer().getOfflinePlayers()) {
            players.add(new wPlayer(player));
        }
        return success(players.toArray(new wPlayer[0]));
    }

    public wResponse getPlayer (String uuid) {
        Player player = getPlayerByID(uuid);
        if (player != null) {
            return success(new wPlayer(player, true));
        }

        OfflinePlayer offlinePlayer = getOfflinePlayerByID(uuid);
        if (offlinePlayer != null) {
            return success(new wPlayer(offlinePlayer, true));
        }
        
        return failure("not found");
    }

    private Player getPlayerByID (String uuid) {
        return plugin.getServer().getPlayer(
            UUID.fromString(uuid.trim())
        );
    }

    private OfflinePlayer getOfflinePlayerByID (String uuid) {
        return plugin.getServer().getOfflinePlayer(
            UUID.fromString(uuid.trim())
        );
    }
}
