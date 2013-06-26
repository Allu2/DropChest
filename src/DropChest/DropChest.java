package DropChest;

//Import stuff needed.
import java.util.logging.Level;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Aleksi Palomäki
 */
public class DropChest extends JavaPlugin {

    PluginDescriptionFile pdfFile;
    MobListener mob;

    @Override
    public void onEnable() {

        this.pdfFile = getDescription();
        this.getLogger().log(Level.INFO, "{0} Version: {1} by Allyoutoo enabled", new Object[]{this.pdfFile.getName(), this.pdfFile.getVersion()});
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();

        mob = new MobListener(this, this.getConfig().getInt("chance"));


    }

    @Override
    public void onDisable() {
        this.getLogger().log(Level.INFO, "{0} Version: {1} by Allyoutoo disabled", new Object[]{this.pdfFile.getName(), this.pdfFile.getVersion()});
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("DropChest")) { // If the player typed /basic then do the following...
            if (args[0].equalsIgnoreCase("chance")) {
                mob.setChance((Integer.parseInt(args[1])));
                sender.sendMessage("Chance for drops has been set to: " + mob.getChance());
                return true;
            }
            if (args[0].equalsIgnoreCase("location")) {
                mob.setLocation((Integer.parseInt(args[1])), (Integer.parseInt(args[2])), (Integer.parseInt(args[3])));
                sender.sendMessage("DropChest location has been set to: " + mob.getLocation());
                return true;
            }
            sender.sendMessage("Usage: /DropChest location/chance");

        } //If this has happened the function will return true. 
        // If this hasn't happened the a value of false will be returned.
        return false;
    }
}
