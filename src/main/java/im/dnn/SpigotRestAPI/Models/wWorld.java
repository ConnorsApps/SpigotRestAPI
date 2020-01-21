package im.dnn.SpigotRestAPI.Models;

import org.bukkit.World;

public class wWorld {
    public String name;
    public String uuid;
    public long time;
    public String difficulty;

    public wWorld (World world) {
        this.name = world.getName();
        this.uuid = world.getUID().toString();
        this.time = world.getTime();
        this.difficulty = world.getDifficulty().name();
    }

}
