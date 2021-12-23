package com.alaa.queueing_theory.utility;

import java.net.URL;
import java.nio.file.FileSystems;

public class View {
    private static String path;

    static {
        View.path = "views" + FileSystems.getDefault().getSeparator();
    }


    public static String getDefaultExtension() {
        return ".fxml";
    }


    /**
     * get Views Path
     *
     * @return
     */
    public static String getPath() {
        return View.path;
    }


    public static URL getViewURL(String name) {
        if (!name.endsWith(View.getDefaultExtension()))
            name = name + View.getDefaultExtension();
        return Globals.getApp().getResource(View.getPath() + name);
    }


    public static String getViewPath(String name) {
        return  View.getViewURL(name).getPath();
    }
}
