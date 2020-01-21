package im.dnn.SpigotRestAPI.Controllers;

import im.dnn.SpigotRestAPI.Services.WorldService;

import static im.dnn.SpigotRestAPI.Util.Json.*;
import static spark.Spark.*;

public class WorldController {

    public WorldController (final WorldService worldService) {
        get("/worlds", (req,res) -> worldService.getAllWorlds(), json());
        get("/worlds/:name", (req,res) -> worldService.getWorld(req.params(":name")), json());
    }
}
