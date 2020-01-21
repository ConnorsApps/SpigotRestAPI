package im.dnn.SpigotRestAPI.Models;

import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

public class wPlayerInventory {
    public wPlayerHands hands;
    public wPlayerArmor armor;
    public wItem[] hotbar;

    public wPlayerInventory (Player p) {
        PlayerInventory inventory = p.getInventory();
        this.armor = new wPlayerArmor(inventory);
        this.hotbar = new wPlayerHotbar(inventory).items;
        this.hands = new wPlayerHands(inventory);
    }

    public class wPlayerArmor {
        public wItem helmet;
        public wItem chestplate;
        public wItem leggings;
        public wItem boots;

        public wPlayerArmor (PlayerInventory inventory) {
            this.helmet = new wItem(inventory.getHelmet());
            this.chestplate = new wItem(inventory.getChestplate());
            this.leggings = new wItem(inventory.getLeggings());
            this.boots = new wItem(inventory.getBoots());
        }
    }

    public class wPlayerHotbar {
        public wItem[] items;

        public wPlayerHotbar (PlayerInventory inv) {
            if (inv != null) {
                this.items = new wItem[9];
                for (int i = 0; i < 9; i++) {
                    this.items[i] = new wItem(inv.getItem(i));
                }
            }
        }
    }

    public class wPlayerHands {
        public wItem main;
        public wItem off;

        public wPlayerHands (PlayerInventory inv) {
            this.main = new wItem(inv.getItemInMainHand());
            this.off = new wItem(inv.getItemInOffHand());
        }
    }
}
