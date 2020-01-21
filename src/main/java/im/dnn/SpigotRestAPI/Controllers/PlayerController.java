package im.dnn.SpigotRestAPI.Controllers;

import im.dnn.SpigotRestAPI.Services.PlayerService;
import static im.dnn.SpigotRestAPI.Util.Json.*;
import static spark.Spark.*;

public class PlayerController {
    public PlayerController (final PlayerService playerService) {
        get("/players", (req,res) -> playerService.getAllPlayers(), json());
        get("/players/offline", (req,res) -> playerService.getAllOfflinePlayers(), json());
        get("/players/:uuid", (req,res) -> playerService.getPlayer(req.params(":uuid")), json());
    }
}
