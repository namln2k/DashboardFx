package com.gn.global.util;

import com.gn.App;
import com.jfoenix.controls.JFXDialog;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;

@SuppressWarnings("unchecked")
class Dialog {

    private static Color color;
    private static final JFXDialog dialog = new JFXDialog();

    private static final EventHandler<MouseEvent> close = event -> dialog.close();

    static void createAlert(Type type, String title, String message) {
        createLayout(createHeader(type), createContent(title, message), createActions(type, new EventHandler[]{close}));
    }

    static void createAlert(Type type, String title, String message, EventHandler<MouseEvent>... confirm) {
        createLayout(createHeader(type), createContent(title, message), createActions(type, confirm));
    }

    private static void createLayout(VBox header, VBox content, HBox actions) {
        StackPane root = new StackPane();
        root.setPadding(new Insets(10));

        VBox box = new VBox();
        box.setStyle("-fx-background-radius : 10 0 10 0; -fx-background-color : white;");

        box.getChildren().addAll(header, content, actions);
        root.getChildren().add(box);

        show(root);
    }

    private static VBox createHeader(Type type) {
        VBox header = new VBox();

        header.setMinHeight(120);
        header.setAlignment(Pos.CENTER);

        ImageView icon = null;

        switch (type) {
            case INFO:
                color = Color.web("#33B5E5");
                icon = new ImageView(new Image("/com/gn/module/media/img/info_48dp.png"));
                break;
            case WARNING:
                color = Color.web("#FC6E51");
                icon = new ImageView(new Image("/com/gn/module/media/img/warning_48dp.png"));
                break;
            case ERROR:
                color = Color.web("#ED5565");
                icon = new ImageView(new Image("/com/gn/module/media/img/error_48dp.png"));
                break;
            case SUCCESS:
                color = Color.web("#02C852");
                icon = new ImageView(new Image("/com/gn/module/media/img/done_48dp.png"));
                break;
        }
        header.setBackground(new Background(new BackgroundFill(color, new CornerRadii(10, 0, 0, 0, false), Insets.EMPTY)));

        icon.setPreserveRatio(true);
        icon.setSmooth(true);
        icon.setFitWidth(151);
        icon.setFitHeight(78);

        header.getChildren().add(icon);
        return header;
    }

    private static VBox createContent(String title, String message) {
        VBox container = new VBox();
        container.setAlignment(Pos.TOP_CENTER);
        container.setSpacing(20D);

        VBox.setMargin(container, new Insets(10, 0, 0, 0));

        Label lblTitle = new Label(title);
        lblTitle.getStyleClass().add("h2");

        Label text = new Label();
        text.setWrapText(true);
        text.setText(message);
        text.setMaxWidth(420);
        text.setAlignment(Pos.CENTER);
        text.setStyle("-fx-text-fill : -text-color; ");

        container.getChildren().addAll(lblTitle, text);

        return container;
    }

    private static HBox createActions(Type type, EventHandler<MouseEvent>[] event) {
        HBox actions = new HBox();
        actions.setMinSize(480, 73);
        actions.setAlignment(Pos.CENTER);
        VBox.setMargin(actions, new Insets(10, 0, 0, 10));
        actions.setSpacing(5D);

        ArrayList<EventHandler<MouseEvent>> list = new ArrayList<>(Arrays.asList(event));

        switch (type) {
//            case WARNING:
//                actions.getChildren().add(
//                            createButton(ButtonType.CANCEL, "Cancel", close));
//                break;
            default:
                actions.getChildren().add(createButton(ButtonType.OK, "OK", list.get(0)));
                break;
        }
        return actions;
    }

    private static Button createButton(ButtonType type, String text, EventHandler<MouseEvent> eventEventHandler) {
        Button button = new Button(text);
        button.setCursor(Cursor.HAND);
        button.setOnMouseReleased(eventEventHandler);
        button.setPrefWidth(100);
        button.addEventHandler(MouseEvent.MOUSE_RELEASED, close);

        switch (type) {
            case CANCEL:
                button.setDefaultButton(true);
                break;
            case OK:
                button.setDefaultButton(true);
                break;
        }
        return button;
    }

    private static void show(Region region) {

        dialog.setDialogContainer(App.getDecorator().getBackground());
        dialog.setContent(region);
        dialog.setTransitionType(JFXDialog.DialogTransition.TOP);

        Platform.runLater(() -> new Thread(new Task() {
            @Override
            protected Object call() {
                dialog.show();
                return null;
            }
        }).start());
    }

    public enum Type {INFO, WARNING, ERROR, SUCCESS}

    public enum ButtonType {OK, CANCEL}
}
