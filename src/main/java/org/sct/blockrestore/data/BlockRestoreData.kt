package org.sct.blockrestore.data

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import java.util.*
import java.util.concurrent.CopyOnWriteArrayList
import kotlin.collections.HashMap

object BlockRestoreData {
    var location: CopyOnWriteArrayList<Location>
    var lt_mr: MutableMap<Location, Material>
    var lt_time: MutableMap<Location, Long>
    var player_chat: MutableMap<Player, Boolean>
    var player_int: MutableMap<Player, Int>
    var time: Int = -1
    var inputmr: Material? = null
    var invsetup: Inventory? = null
    var invmodify: Inventory? = null
    var inveditor: Inventory? = null
    var blocklist: MutableList<String>

    fun setModify(size: Int, title: String?) {
        invmodify = Bukkit.createInventory(null, size, title!!)
    }

    fun setBlocksetup(size: Int, title: String?) {
        invsetup = Bukkit.createInventory(null, size, title!!)
    }

    fun settime(time: Int) {
        this.time = time
    }

    init {
        location = CopyOnWriteArrayList()
        lt_mr = HashMap()
        lt_time = HashMap()
        player_chat = HashMap()
        player_int = HashMap()
        time = -1
        blocklist = ArrayList()
    }
}