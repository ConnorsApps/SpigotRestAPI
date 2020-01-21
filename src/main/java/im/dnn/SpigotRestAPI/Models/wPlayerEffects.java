package im.dnn.SpigotRestAPI.Models;

import java.util.List;
import static java.util.stream.Collectors.toList;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

public class wPlayerEffects {
    public wEffect[] effects;

    public wPlayerEffects (Player player) {
        List<PotionEffect> effects = player.getActivePotionEffects().stream().collect(toList());

        this.effects = new wEffect[effects.size()];
        for (int i = 0; i < effects.size(); i++) {
            this.effects[i] = new wEffect(effects.get(i));
        }
    }
}