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

import static org.sct.BlockRestore.Main.Instance;

public class VariableManager {
    private CopyOnWriteArrayList<Location> location = new CopyOnWriteArrayList<>();
    private HashMap<Location, Material> lt_mr = new HashMap<>();
    private HashMap<Location, Long> lt_time = new HashMap<>();
    private HashMap<Player,Boolean> player_chat= new HashMap<>();
    private HashMap<Player,Integer> player_int = new HashMap<>();
    private int time = -1;
    private List<String> placelist,breaklist;
    private Material inputmr;
    private Inventory blocksetup;
    private ArrayList<String> blocklist = new ArrayList<>();

    public HashMap getplayer_int() {
        return player_int;
    }

    public ArrayList getblocklist() {
        return blocklist;
    }

    public Material getInputmr() {
        return inputmr;
    }

    public Inventory getBlocksetup() {
        return blocksetup;
    }

    public CopyOnWriteArrayList getlocation() {
        return location;
    }

    public HashMap getlt_mr() {
        return lt_mr;
    }

    public HashMap getlt_time() {
        return lt_time;
    }

    public HashMap getplayer_chat() {
        return player_chat;
    }

    public int gettime() {
        return time;
    }

    public List getplacelist() {
        return placelist;
    }

    public List getbreaklist() {
        return breaklist;
    }

    public static Main getInstance() {
        return Instance;
    }
}
