package zarde.xd.zardexhoppersort

import org.bukkit.block.Container
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryMoveItemEvent
import org.bukkit.event.inventory.InventoryPickupItemEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.plugin.java.JavaPlugin
import java.util.*

class HopperSort : Listener {

    fun getItemName(translationKey: String?): String? {
        if (translationKey == null) return null
        val names = translationKey.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        return names[names.size - 1]
    }

    fun filterMatch(filterString: String, fullitemname: String?): Boolean {
        val itemName = getItemName(fullitemname)
        val filter = filterString.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        return Arrays.stream(filter).anyMatch { filter_i: String ->
            if (filter_i.endsWith("*")) {
                return@anyMatch itemName!!.startsWith(filter_i.substring(0, filter_i.length - 1))
            } else if (filter_i.startsWith("*")) {
                return@anyMatch itemName!!.endsWith(filter_i.substring(1))
            } else {
                return@anyMatch filter_i.equals(itemName, ignoreCase = true)
            }
        }
    }

    @EventHandler
    fun onInventoryMoveItemEvent(event: InventoryMoveItemEvent) {
        if (event.destination.type == InventoryType.HOPPER && event.destination.holder is Container) {
            val customName = (event.destination.holder as Container?)!!.customName
            if (customName != null) {
                val itemName = event.item.type.translationKey()
                if (!filterMatch(customName, itemName)) {
                    event.isCancelled = true
                }
            }
        }
    }

    @EventHandler
    fun onInventoryPickupItemEvent(event: InventoryPickupItemEvent) {
        if (event.inventory.holder is Container) {
            val customName = (event.inventory.holder as Container?)!!.customName
            if (customName != null) {
                val itemName = event.item.itemStack.type.translationKey()
                if (!filterMatch(customName, itemName)) {
                    event.isCancelled = true
                }
            }
        }
    }
}