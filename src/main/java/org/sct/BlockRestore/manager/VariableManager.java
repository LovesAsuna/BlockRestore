package org.sct.BlockRestore.manager;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class VariableManager {
    private CopyOnWriteArrayList<Location> location = new CopyOnWriteArrayList<>();
    private HashMap<Location, Material> lt_mr = new HashMap<>();
    private HashMap<Location, Long> lt_time = new HashMap<>();
    private HashMap<Player,Boolean> player_chat= new HashMap<>();
    private HashMap<Player,Integer> player_int = new HashMap<>();
    private int time = -1;
    private Material inputmr;
    private Inventory invsetup,invmodify,inveditor;
    private ArrayList<String> blocklist = new ArrayList<>();

    public void setModify (int size,String title) {
        invmodify = Bukkit.createInventory(null,size,title);
    }

    public void setBlocksetup (int size,String title) {
        invsetup = Bukkit.createInventory(null,size,title);
    }

    public HashMap getplayer_int() {
        return player_int;
    }

    public ArrayList getblocklist() {
        return blocklist;
    }

    public Material getInputmr() {
        return inputmr;
    }

    public Inventory getInvsetup() {
        return invsetup;
    }

    public Inventory getInvmodify() {
        return invmodify;
    }

    public Inventory getInveditor() {
        return inveditor;
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

    public void settime (int time) {
        this.time = time;
    }
}
