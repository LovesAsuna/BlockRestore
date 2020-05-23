package org.sct.blockrestore.file;

import org.bukkit.configuration.file.YamlConfiguration;
import org.sct.blockrestore.BlockRestore;
import org.sct.blockrestore.enumeration.LangType;
import org.sct.easylib.util.BasicUtil;

import java.io.File;
import java.util.List;

public class Lang {

    private static File file;
    private static YamlConfiguration config;

    public static void loadLang() {
        file = new File(BlockRestore.instance.getDataFolder() + File.separator + ".yml");
        if (!file.exists()) { BlockRestore.instance.saveResource("lang.yml",false); }
        config = YamlConfiguration.loadConfiguration(file);
    }

    public static String getString(LangType langType) {
        loadLang();
        return BasicUtil.convert(config.getString(langType.getPath()));
    }

    public static List<String> getStringList(LangType langType) {
        loadLang();
        return BasicUtil.convert(config.getStringList(langType.getPath()));
    }

}
