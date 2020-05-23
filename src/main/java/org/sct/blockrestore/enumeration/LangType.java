package org.sct.blockrestore.enumeration;

public enum LangType {

    /**
     * 语言文件的路径
     */
    LANGUAGE_COMMANDHELP("Language.CommandHelp"),
    LANG_NOPERMISSION("Language.NoPermission"),
    LANG_NOTAPLAYER("Language.NotAPlayer"),
    LANG_RELOAD("Language.Reload"),
    LANG_COMMANDERROR("Language.CommandError");

    String path;

    public String getPath() {
        return path;
    }

    LangType(String path) {
        this.path = path;
    }

}
