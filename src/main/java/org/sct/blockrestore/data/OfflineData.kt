package org.sct.blockrestore.data

import org.bukkit.Location
import java.util.concurrent.CopyOnWriteArrayList

data class OfflineData(val locationList: CopyOnWriteArrayList<Location>) {
    // todo 离线数据保存部分
}