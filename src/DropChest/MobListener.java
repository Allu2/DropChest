package DropChest;
//Imports
import java.util.Random;
import org.bukkit.block.Chest;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


/**
 *
 * @author Aleksi Palomäki
 */
public class MobListener implements Listener {

    private String lastkiller;
    private final DropChest plugin;
    private int chance;
    private int x;
    private int y;
    private int z;
    private String world;

    public MobListener(DropChest plugin, int chance) {
        this.lastkiller = "";
        this.plugin = plugin;
        this.chance = chance;
        this.world = this.plugin.getConfig().getString("world");
        this.x = this.plugin.getConfig().getInt("chest.x");
        this.y = this.plugin.getConfig().getInt("chest.y");
        this.z = this.plugin.getConfig().getInt("chest.z");
        this.world = this.plugin.getConfig().getString("world");
        plugin.getServer().getPluginManager().registerEvents(this, plugin);


    }

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

    public void setChance(int i) {  //Set the chance that is used to count wether or not run setDrop()'
        this.plugin.getConfig().set("chance", i);
        this.chance = i;
        plugin.saveConfig();
    }
public String getLocation(){
return "x:"+this.x+" y:"+this.y+" z:"+this.z;
}
    public void setLocation(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        plugin.getConfig().set("chest.x", x);
        plugin.getConfig().set("chest.y", y);
        plugin.getConfig().set("chest.z", z);
        plugin.saveConfig();
    }

    public int getChance() {
        return this.chance;
    }

    @EventHandler
    public void onEntityDeathEvent(final EntityDeathEvent event) {
        if (event.getEntity() instanceof Monster) {
            Monster monsterEnt = (Monster) event.getEntity();
            Player mcPlayer = monsterEnt.getKiller();
            if (mcPlayer == null) {
                setKiller("NULL");
            }

            Chest chest = (Chest) plugin.getServer().getWorld(this.world).getBlockAt(this.x, this.y, this.z).getState();
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
