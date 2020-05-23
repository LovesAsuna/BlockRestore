package org.sct.blockrestore.file;

import org.bukkit.configuration.file.FileConfiguration;
import org.sct.blockrestore.BlockRestore;
import org.sct.blockrestore.enumeration.ConfigType;

import java.util.List;

public class Config {

    private static FileConfiguration config = BlockRestore.instance.getConfig();

    public static void loadConfig() {
        BlockRestore.instance.reloadConfig();
        config = BlockRestore.instance.getConfig();
    }

    public static String getString(ConfigType configType) {
        loadConfig();
        return config.getString(configType.getPath());
    }

    public static int getInteger(ConfigType configType) {
        loadConfig();
        return config.getInt(configType.getPath());
    }

    public static boolean getBoolean(ConfigType configType) {
        loadConfig();
        return config.getBoolean(configType.getPath());
    }

    public static List<String> getStringList(ConfigType configType) {
        loadConfig();
        return config.getStringList(configType.getPath());
    }

    public static boolean setStringList(ConfigType configType, List list) {
        loadConfig();
        config.set(configType.getPath(), list);
        return true;
    }
}
