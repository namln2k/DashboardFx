package com.gn.global;

public class ComboBox {
    public static <T> void prepareComboBox(javafx.scene.control.ComboBox comboBox, Iterable<T> options) {
        for (T option : options) {
            comboBox.getItems().add(option);
        }
    }

    public static <T> void setValue(javafx.scene.control.ComboBox comboBox, T value) {
        comboBox.setValue(value);
    }

}
