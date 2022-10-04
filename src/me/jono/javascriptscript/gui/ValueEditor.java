package me.jono.javascriptscript.gui;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import me.jono.javascriptscript.MultiValue;
import me.jono.javascriptscript.NumberValue;
import me.jono.javascriptscript.ProgramGraph;
import me.jono.javascriptscript.Socket;

/**
 * A GUI for editing the value of a socket
 */
public class ValueEditor extends Stage {
    public ValueEditor(Socket socket) {
        setTitle("Editing the value of the "+socket.getName()+" socket of the "+socket.getNode().getName()+" node...");

        VBox vbox = new VBox();

        Scene scene = new Scene(vbox, 600, 600);
        setScene(scene);

        ValueEditorBody body;
        if (socket.getValue() instanceof NumberValue) {
            body = new NumberValueEditor(socket);
        } else if (socket.getValue() instanceof MultiValue) {
            body = new MultiValueEditor(socket);
        } else if (socket.getValue() instanceof ProgramGraph) {
            body = new ProgramGraphEditor(socket);
        } else {
            body = new TextValueEditor(socket);
        }

        MenuBar menuBar = new MenuBar();
        Menu editMenu = new Menu("Edit");
        MenuItem updateMenuItem = new MenuItem("Update Value");
        updateMenuItem.setOnAction(event -> {
            body.updateValue();
        });
        editMenu.getItems().addAll(updateMenuItem);
        menuBar.getMenus().addAll(editMenu);

        vbox.getChildren().addAll(menuBar, body);
    }
}
