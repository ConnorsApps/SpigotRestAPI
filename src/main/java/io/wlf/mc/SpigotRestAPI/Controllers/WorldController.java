package io.wlf.mc.SpigotRestAPI.Controllers;

import io.wlf.mc.SpigotRestAPI.Services.WorldService;

import static io.wlf.mc.SpigotRestAPI.Util.Json.*;
import static spark.Spark.*;

public class WorldController {

    public WorldController (final WorldService worldService) {
        get("/worlds", (req,res) -> worldService.getAllWorlds(), json());
        get("/worlds/:name", (req,res) -> worldService.getWorld(req.params(":name")), json());
    }
}
