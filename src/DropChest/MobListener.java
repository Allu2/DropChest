package DropChest;

import java.util.Random;
import org.bukkit.block.Chest;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aleksi
 */
public class MobListener implements Listener {

    private String lastkiller;
    private final DropChest plugin;
    private int chance;

    public MobListener(DropChest plugin, int chance) {
        this.lastkiller = "";
        this.plugin = plugin;
        this.chance = chance;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);


    }

//    public MobListener(DropChest plugin) {
//        plugin.getServer().getPluginManager().registerEvents(this, plugin);
//        plugin.getLogger().log(Level.INFO, "We're now in MobListener!");
//        lastkiller = "";
//    }
    String getKiller() {
        return this.lastkiller;
    }

    public void setKiller(String l) {
        this.lastkiller = l;
    }

    public void setDrop(ItemStack drop, EntityDeathEvent event) {  //Edit the dropslist from the EntityDeathEvent and add custom drops in it
        ItemStack items = drop;
        event.getDrops().add(items);
    }

    public void setChance(int i) {  //Set the chance that is used to count wether or not run setDrop()
        this.chance = i;
    }

    @EventHandler
    public void onEntityDeathEvent(final EntityDeathEvent event) {
        if (event.getEntity() instanceof Monster) {
            Monster monsterEnt = (Monster) event.getEntity();
            Player mcPlayer = monsterEnt.getKiller();
            if (mcPlayer == null) {
                setKiller("NULL");
            }

            Chest chest = (Chest) plugin.getServer().getWorld("world").getBlockAt(-249, 72, 239).getState();
            if (mcPlayer != null) {
                Random rand = new Random();
                setKiller(mcPlayer.getPlayerListName());
                Inventory inv = chest.getInventory();
                ItemStack droppi = inv.getContents()[rand.nextInt(inv.getContents().length)];
                if (rand.nextInt(100) > this.chance) {
                    setDrop(droppi, event);
                }
                // org.bukkit.Bukkit.getConsoleSender().sendMessage(mcPlayer.getPlayerListName());
            }
        }
        {
            if (event.getEntity() instanceof Monster) {
                Monster monsterEnt = (Monster) event.getEntity();
                Player mcPlayer = monsterEnt.getKiller();
                if (mcPlayer == null) {
                    setKiller("NULL");
                }
            }
        }
    }
}
