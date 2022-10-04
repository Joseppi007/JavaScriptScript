package me.jono.javascriptscript.gui;

import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import me.jono.javascriptscript.Socket;
import me.jono.javascriptscript.TextValue;

public class TextValueEditor extends ValueEditorBody {
    private final TextArea textArea;
    public TextValueEditor(Socket socket) {
        super(socket);

        GridPane root = new GridPane();
        getChildren().add(root);

        textArea = new TextArea(getValue().toString());
        root.addColumn(0, textArea);
    }

    @Override
    public void updateValue() {
        getSocket().setValue(new TextValue(textArea.getText()));
    }
}
