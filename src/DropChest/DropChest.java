package DropChest;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.logging.Level;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author aleksi
 */
public class DropChest extends JavaPlugin {

    PluginDescriptionFile pdfFile;
    MobListener mob;

    @Override
    public void onEnable() {

        this.pdfFile = getDescription();
        this.getLogger().log(Level.INFO, "{0} Version: {1} by Allyoutoo enabled", new Object[]{this.pdfFile.getName(), this.pdfFile.getVersion()});

        mob = new MobListener(this, 95);

    }

    @Override
    public void onDisable() {
        this.getLogger().log(Level.INFO, "{0} Version: {1} by Allyoutoo disabled", new Object[]{this.pdfFile.getName(), this.pdfFile.getVersion()});
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("chance")) { // If the player typed /basic then do the following...
            mob.setChance((Integer.parseInt(args[0])));
            //org.bukkit.Bukkit.broadcastMessage("Last Monster on the server was killed by: "+ mob.getKiller());
            return true;
        } //If this has happened the function will return true. 
        // If this hasn't happened the a value of false will be returned.
        return false;
    }
}
