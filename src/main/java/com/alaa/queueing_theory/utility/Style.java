package com.alaa.queueing_theory.utility;

import java.net.URL;
import java.nio.file.FileSystems;

public class Style {
    private static String path;
    static {
        Style.path = "styles" + FileSystems.getDefault().getSeparator();
    }


    public static String getDefaultExtension() {
        return ".css";
    }
    /**
     * get Style Path
     * @return String
     */
    public static String getPath() {
        return Style.path;
    }


    public static URL getStyleURL(String name) {
        if (!name.endsWith(Style.getDefaultExtension()))
            name = name + Style.getDefaultExtension();
        return Globals.getApp().getResource(Style.getPath() + name);
    }




    public static String getStylePath(String name) {
        return  Style.getStyleURL(name).toExternalForm();
    }
}
