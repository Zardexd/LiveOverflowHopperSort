package zarde.xd.zardexhoppersort
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

class ZardexHopperSort : JavaPlugin(),Listener {
    override fun onEnable() {
    // Plugin startup logic
        logger.info("Hoppersort just started!");
        server.pluginManager.registerEvents(this,this);
        server.pluginManager.registerEvents(HopperSort(),this);
    }

    override fun onDisable() {
        // Plugin shutdown logic
        logger.info("Hoppersort stopped!");
    }


}