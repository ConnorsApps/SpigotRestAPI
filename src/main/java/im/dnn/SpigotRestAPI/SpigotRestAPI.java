package im.dnn.SpigotRestAPI;

import im.dnn.SpigotRestAPI.Controllers.PlayerController;
import im.dnn.SpigotRestAPI.Controllers.WorldController;

import im.dnn.SpigotRestAPI.Services.PlayerService;
import im.dnn.SpigotRestAPI.Services.WorldService;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

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

            if (config.getBoolean("cors.enabled")) {
                if (config.getBoolean("cors.multiple")) {
                    List<String> origins = config.getStringList("cors.origins");
                    for (String origin : origins) {
                        res.header("Access-Control-Allow-Origin", origin);
                    }
                } else {
                    String defaultOrigin = config.getBoolean("cors.enabled") ? "*" : "null";
                    res.header("Access-Control-Allow-Origin", config.getString("cors.origin", defaultOrigin));
                }

                res.header("Access-Control-Allow-Methods", "GET, POST, PUT, PATCH, DELETE, OPTIONS");
                res.header("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, Authorization");
                res.header("Access-Control-Allow-Credentials", "true");
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
        this.config.addDefault("cors.enabled", false);
        this.config.addDefault("cors.origin", "null");
        this.config.addDefault("cors.multiple", false);
        this.saveConfig();
    }

    private void initControllers () {
        new PlayerController(new PlayerService(this));
        new WorldController(new WorldService(this));
    }
}
