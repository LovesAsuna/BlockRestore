package org.sct.BlockRestore;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.sct.BlockRestore.commands.blr;
import org.sct.BlockRestore.listener.BlockBreak;
import org.sct.BlockRestore.listener.BlockPlace;

import static org.sct.BlockRestore.Manager.StaticManager.*;

public class Main extends JavaPlugin {
    public static Main Instance;

    @Override
    public void onEnable() {
        Instance = this;
        initialize();
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
        Bukkit.getPluginManager().registerEvents(new BlockPlace(),this);
        Bukkit.getPluginManager().registerEvents(new BlockBreak(),this);
        placelist = getConfig().getStringList("place");
        breaklist = getConfig().getStringList("break");
    }

}
