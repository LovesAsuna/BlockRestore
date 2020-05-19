package org.sct.BlockRestore;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.sct.BlockRestore.commands.blockrestore;
import org.sct.BlockRestore.listener.BlockBreak;
import org.sct.BlockRestore.listener.BlockPlace;
import org.sct.BlockRestore.listener.asyncplayerchat;
import org.sct.BlockRestore.listener.guilistener;
import org.sct.BlockRestore.manager.VariableManager;
import org.sct.BlockRestore.updater.update;

import java.util.ArrayList;

public class Main extends JavaPlugin {
    public static Main Instance;
    public static VariableManager variableManager = new VariableManager();
    private ArrayList<String> blocklist = variableManager.getblocklist();

    @Override
    public void onEnable() {
        Instance = this;
        initialize();
        registerEvents();
        Thread check = new Thread(new update());
        check.run();
        getServer().getConsoleSender().sendMessage("§7[§eBlockRestore§7]§2插件已加载");
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage("§7[§eBlockRestore§7]§c插件已卸载");
    }

    public void initialize() {
        saveDefaultConfig();
        reloadConfig();
        readconfig();
        Bukkit.getPluginCommand("blr").setExecutor(new blockrestore());

    }

    private void registerEvents() {
        Listener listener[] = {new BlockBreak(),new BlockPlace(),new asyncplayerchat(),new guilistener()};
        for (Listener Listener : listener) {
            Bukkit.getPluginManager().registerEvents(Listener,this);
        }
    }

    private void readconfig() {
        blocklist.clear();
        if (getConfig().getConfigurationSection("blocks") == null) return;
        for (String block : getConfig().getConfigurationSection("blocks").getKeys(true)) {
            if (!block.contains(".")) {
                blocklist.add(block);
            }
        }
    }

    public static Main getInstance() {
        return Instance;
    }
}