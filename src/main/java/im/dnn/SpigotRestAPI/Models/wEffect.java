package im.dnn.SpigotRestAPI.Models;

import org.bukkit.potion.PotionEffect;

public class wEffect {

    public String type;
    public int duration;
    public int amplifier​;

    public wEffect () {}

    public wEffect (PotionEffect e) {
        this.fromBukkit(e);
    }

    public void fromBukkit (PotionEffect e) {
        if (e != null) {
            this.type = e.getType().getName();
            this.duration = e.getDuration();
            this.amplifier​ = e.getAmplifier();
        }
    }
}
