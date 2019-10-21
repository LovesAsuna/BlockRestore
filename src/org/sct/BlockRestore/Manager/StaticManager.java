package org.sct.BlockRestore.Manager;

import org.bukkit.Location;
import org.bukkit.Material;
import org.sct.BlockRestore.Main;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.sct.BlockRestore.Main.*;

public class StaticManager {
    public static CopyOnWriteArrayList<Location> location = new CopyOnWriteArrayList<>();
    public static HashMap<Location, Material> lt_mr = new HashMap<>();
    public static HashMap<Location, Long> lt_time = new HashMap<>();
    public static List<String> placelist;
    public static List<String> breaklist;
    public static Boolean replace,replacerestore,directgetitem,denyplace;
    public static Material mr = Material.getMaterial(getInstance().getConfig().getString("replaceblock"));
    public static Main getInstance() {
        return Instance;
    }
}
