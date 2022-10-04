package me.jono.javascriptscript.gui;

import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import me.jono.javascriptscript.NumberValue;
import me.jono.javascriptscript.Socket;

import java.math.BigDecimal;

public class NumberValueEditor extends ValueEditorBody {
    private final TextArea textArea;

    /**
     * Creates a NumberValueEditor
     * @param socket the value to edit
     */
    public NumberValueEditor(Socket socket) {
        super(socket);

        GridPane root = new GridPane();
        getChildren().add(root);

        Text text = new Text("Enter a number:");
        textArea = new TextArea(getValue().toString());
        root.addColumn(0, text, textArea);
    }

    @Override
    public void updateValue() {
        getSocket().setValue(new NumberValue(new BigDecimal(textArea.getText())));
    }
}
