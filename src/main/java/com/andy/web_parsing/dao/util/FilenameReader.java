package com.andy.web_parsing.dao.util;

import java.util.ResourceBundle;

public class FilenameReader {

    private FilenameReader() {
    }

    public static String readFilename(){
        ResourceBundle rs = ResourceBundle.getBundle("config");
        return rs.getString("fileName");
    }
}
