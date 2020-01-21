package io.wlf.mc.SpigotRestAPI;

import io.wlf.mc.SpigotRestAPI.Controllers.PlayerController;
import io.wlf.mc.SpigotRestAPI.Controllers.WorldController;

import io.wlf.mc.SpigotRestAPI.Services.PlayerService;
import io.wlf.mc.SpigotRestAPI.Services.WorldService;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import static spark.Spark.*;

public class SpigotRestAPI extends JavaPlugin {
    private FileConfiguration config;

    @Override
    public void onEnable () {
        getLogger().info("SpigotRestAPI Enabled");

        this.saveDefaultConfig();
        this.setupConfig();

        getLogger().info("Listening on port: " + config.getInt("port"));
        port(config.getInt("port"));

        before((req, res) -> {
            res.type("application/json");

            if (config.getBoolean("cors")) {
                res.header("Access-Control-Allow-Origin", "*");
                res.header("Access-Control-Allow-Methods", "GET");
                res.header("Access-Control-Allow-Methods", "POST");
            }
        });

        exception(Exception.class, (e, req, res) -> {
            res.status(500);

            StringBuilder str = new StringBuilder();
            for (StackTraceElement elem : e.getStackTrace()) {
                str.append("\n")
                    .append(elem.getFileName())
                    .append(" : ")
                    .append(elem.getLineNumber());
            }
            res.body(ExceptionUtils.getStackTrace(e) + "\n\n" + e.getClass().getName() + "\n" + e.getMessage() + "\n\n" + str.toString());
        });

        initControllers();
    }

    @Override
    public void onDisable () {
        stop();
        getLogger().info("SpigotRestAPI Disabled");
    }

    private void setupConfig () {
        this.config = this.getConfig();
        this.config.addDefault("port", 8765);
        this.config.addDefault("cors", false);
        this.saveConfig();
    }

    private void initControllers () {
        new PlayerController(new PlayerService(this));
        new WorldController(new WorldService(this));
    }
}
