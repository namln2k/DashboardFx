/*
 * Copyright (C) Gleidson Neves da Silveira
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.gn;

import com.gn.decorator.GNDecorator;
import com.gn.decorator.options.ButtonType;
import com.gn.global.Section;
import com.gn.global.plugin.SectionManager;
import com.gn.global.plugin.ViewManager;
import com.gn.module.loader.Loader;
import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.application.Preloader;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Init the app class.
 *
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  07/10/2018
 * Version 1.0
 */
public class App extends Application {

    private float increment = 0;
    private float progress = 0;

    private Section section;

    @Override
    public synchronized void init() {
        // Get section
        section = SectionManager.get();

        // Increment for preload progress bar
        float total = 43;
        increment = 100f / total;

        // Load modules
        load("dashboard", "dashboard");
        load("main", "main");
        load("profile", "profile");
        load("Login", "Login");

        // Wait for modules to load
        try {
            wait(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static final GNDecorator decorator = new GNDecorator();
    public static final Scene scene = decorator.getScene();

    public static ObservableList<String> stylesheets;
    public static HostServices hostServices;

    public static GNDecorator getDecorator() {
        return decorator;
    }

    private void configServices() {
        hostServices = getHostServices();
    }

    private void initialScene() {
        decorator.setTitle("DashboardFx");
        decorator.addButton(ButtonType.FULL_EFFECT);
        decorator.initTheme(GNDecorator.Theme.DEFAULT);

        String log = logged();
        assert log != null;

        if (log.equals("Login")) {
            decorator.setContent(ViewManager.getInstance().get(log));
        } else {
            decorator.setContent(ViewManager.getInstance().get("main"));
        }

        decorator.getStage().setOnCloseRequest(event -> {
            Platform.exit();
        });
    }

    @Override
    public void start(Stage primary) {
        configServices();
        initialScene();

        stylesheets = decorator.getScene().getStylesheets();

        stylesheets.addAll(
                getClass().getResource("/com/gn/theme/css/fonts.css").toExternalForm(),
                getClass().getResource("/com/gn/theme/css/material-color.css").toExternalForm(),
                getClass().getResource("/com/gn/theme/css/skeleton.css").toExternalForm(),
                getClass().getResource("/com/gn/theme/css/light.css").toExternalForm(),
                getClass().getResource("/com/gn/theme/css/bootstrap.css").toExternalForm(),
                getClass().getResource("/com/gn/theme/css/shape.css").toExternalForm(),
                getClass().getResource("/com/gn/theme/css/typographic.css").toExternalForm(),
                getClass().getResource("/com/gn/theme/css/helpers.css").toExternalForm(),
                getClass().getResource("/com/gn/theme/css/master.css").toExternalForm()
        );

        decorator.setMaximized(true);
        decorator.getStage().getIcons().add(new Image("/com/gn/module/media/logo2.png"));
        decorator.show();
    }

    public static void main(String[] args) {
        LauncherImpl.launchApplication(App.class, Loader.class, args);
    }

    private void load(String module, String name) {
        try {
            ViewManager.getInstance().put(
                    name,
                    FXMLLoader.load(getClass().getResource("/com/gn/module/" + module + "/" + name + ".fxml"))
            );
            preloaderNotify();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private synchronized void preloaderNotify() {
        progress += increment;
        LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(progress));
    }

    private String logged() {
        try {
            File file = new File("dashboard.properties");
            Properties properties = new Properties();

            if (!file.exists()) {
                file.createNewFile();
                return "Login";
            } else {
                FileInputStream fileInputStream = new FileInputStream(file);
                properties.load(fileInputStream);
                properties.putIfAbsent("logged", "false");
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                properties.store(fileOutputStream, "Dashboard properties");


                File directory = new File("user/");
                properties.load(fileInputStream);
                if (directory.exists()) {
                    if (properties.getProperty("logged").equals("false"))
                        return "Login";
                    else
                        return "main";
                } else
                    return "Login";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
