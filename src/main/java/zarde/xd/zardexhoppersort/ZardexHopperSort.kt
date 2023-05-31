package zarde.xd.zardexhoppersort
import org.bukkit.plugin.java.JavaPlugin

class ZardexHopperSort : JavaPlugin() {
    override fun onEnable() {
    // Plugin startup logic
        logger.info("Hoppersort just started!");
    }

    override fun onDisable() {
        // Plugin shutdown logic
        logger.info("Hoppersort stopped!");
    }


}