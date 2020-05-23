package org.sct.blockrestore.data

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.sct.blockrestore.enumeration.Status
import org.sct.blockrestore.util.BlockRestoreThreadFactory
import java.util.*
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import kotlin.collections.HashMap

object BlockRestoreData {
    var locationList: CopyOnWriteArrayList<Location> = CopyOnWriteArrayList()
    var locationMaterial: MutableMap<Location, Material> = HashMap()
    var locationTime: MutableMap<Location, Long> = HashMap()
    var playerChat: MutableMap<Player, Boolean> = HashMap()
    var playerStatus: MutableMap<Player, Status> = HashMap()
    var blockList: MutableList<String> = ArrayList()
    var inputTime: Int = -1
    var inputMaterial: Material? = null
    lateinit var blockSetup: Inventory
    lateinit var modify: Inventory
    lateinit var overview: Inventory
    val pool = ThreadPoolExecutor(10, 25, 5, TimeUnit.MINUTES, ArrayBlockingQueue(100), BlockRestoreThreadFactory("[BlockRestore]"))

    fun createModify(size: Int, title: String) : Inventory{
        modify = Bukkit.createInventory(null, size, title)
        return modify
    }

    fun createBlockSetup(size: Int, title: String) : Inventory{
        blockSetup = Bukkit.createInventory(null, size, title)
        return blockSetup
    }

}