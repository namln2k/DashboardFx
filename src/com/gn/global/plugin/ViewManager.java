package com.gn.global.plugin;

import javafx.scene.Node;

import java.util.HashMap;

public class ViewManager {

    private static final HashMap<String, Node> SCREENS = new HashMap<>();
    private static ViewManager instance;

    private ViewManager() {
    }

    public static ViewManager getInstance() {
        if (instance == null) {
            instance = new ViewManager();
        }
        return instance;
    }

    public void put(String name, Node node) {
        SCREENS.put(name, node);
    }

    public Node get(String view) {
        return SCREENS.get(view);
    }

    public int getSize() {
        return SCREENS.size();
    }
}
