package org.sct.BlockRestore.Manager;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.sct.BlockRestore.Main;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.sct.BlockRestore.Main.*;
import static org.sct.BlockRestore.listener.AsyncPlayerChat.*;

public class StaticManager {
    public static CopyOnWriteArrayList<Location> location = new CopyOnWriteArrayList<>();
    public static HashMap<Location, Material> lt_mr = new HashMap<>();
    public static HashMap<Location, Long> lt_time = new HashMap<>();
    public static HashMap<Player,Boolean> player_chat= new HashMap<>(),player_chat_2 = new HashMap<>(),player_time = new HashMap<>();
    public static int time = -1;
    public static List<String> placelist;
    public static List<String> breaklist;
    public static Boolean replace,replacerestore,directgetitem,denyplace;
    public static Main getInstance() {
        return Instance;
    }
    public static Material getInputmr() {
        return inputmr;
    }
    public static Inventory blocksetup;
    public static ArrayList<String> blocklist = new ArrayList<>();
}
