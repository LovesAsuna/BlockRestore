package org.sct.blockrestore.data

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.sct.blockrestore.enumeration.SetupStatus
import java.util.*
import java.util.concurrent.CopyOnWriteArrayList
import kotlin.collections.HashMap

object BlockRestoreData {
    var location: CopyOnWriteArrayList<Location> = CopyOnWriteArrayList()
    var lt_mr: MutableMap<Location, Material> = HashMap()
    var lt_time: MutableMap<Location, Long> = HashMap()
    var playerChat: MutableMap<Player, Boolean> = HashMap()
    var playerStatus: MutableMap<Player, SetupStatus> = HashMap()
    var blocklist: MutableList<String> = ArrayList()
    var time: Int = -1
    var inputMaterial: Material? = null
    lateinit var invsetup: Inventory
    lateinit var invmodify: Inventory
    lateinit var inveditor: Inventory

    fun setModify(size: Int, title: String) {
        invmodify = Bukkit.createInventory(null, size, title)
    }

    fun setBlockSetup(size: Int, title: String) : Inventory{
        invsetup = Bukkit.createInventory(null, size, title)
        return invsetup
    }

}