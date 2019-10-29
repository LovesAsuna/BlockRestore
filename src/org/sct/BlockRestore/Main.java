package org.sct.BlockRestore;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.sct.BlockRestore.commands.blr;
import org.sct.BlockRestore.listener.AsyncPlayerChat;
import org.sct.BlockRestore.listener.BlockBreak;
import org.sct.BlockRestore.listener.BlockPlace;
import org.sct.BlockRestore.listener.InventoryClick;
import org.sct.BlockRestore.updater.update;

import static org.sct.BlockRestore.Manager.StaticManager.*;

public class Main extends JavaPlugin {
    public static Main Instance;

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
        replace = getConfig().getBoolean("replace");
        replacerestore = getConfig().getBoolean("replacestore");
        directgetitem = getConfig().getBoolean("directgetitem");
        denyplace = getConfig().getBoolean("denyplace");
        Bukkit.getPluginCommand("blr").setExecutor(new blr());
        placelist = getConfig().getStringList("place");
        breaklist = getConfig().getStringList("break");
    }

    private void registerEvents() {
        Listener listener[] = {new BlockBreak(),new BlockPlace(),new InventoryClick(),new AsyncPlayerChat()};
        for (Listener Listener : listener) {
            Bukkit.getPluginManager().registerEvents(Listener,this);
        }
    }

}
