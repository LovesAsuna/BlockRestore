package org.sct.BlockRestore.file;

import org.bukkit.configuration.file.FileConfiguration;
import org.sct.BlockRestore.Main;
import org.sct.BlockRestore.enumeration.ConfigType;

import java.util.List;

public class Config {

    private static FileConfiguration config = Main.instance.getConfig();

    public static void loadConfig() {
        Main.instance.reloadConfig();
        config = Main.instance.getConfig();
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
