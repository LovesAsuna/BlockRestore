package org.sct.blockrestore.enumeration;

public enum ConfigType {

    SETTING_LANGUAGE("Setting.Language");


    String path;

    public String getPath() {
        return path;
    }

    ConfigType(String path) {
        this.path = path;
    }

}
