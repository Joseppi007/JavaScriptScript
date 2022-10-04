package me.jono.javascriptscript.gui;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import me.jono.javascriptscript.*;

import java.util.ArrayList;

public class MultiValueEditor extends ValueEditorBody {
    private final ListView<Button> listViewEdit;
    private final ListView<Button> listViewDelete;
    private final Button listViewAdd;
    private final ArrayList<Socket> socketsForValues;

    /**
     * Creates a NumberValueEditor
     * @param socket the value to edit
     */
    public MultiValueEditor(Socket socket) {
        super(socket);

        socketsForValues = new ArrayList<>();
        for (Value value : (MultiValue) socket.getValue()) {
            socketsForValues.add(new InputSocket(socket.getNode(), socket.getName(), value));
        }

        GridPane root = new GridPane();
        getChildren().add(root);

        root.addRow(0, new Text("Edit Element:"));
        root.addRow(2, new Text("Delete Element:"));
        root.addRow(4, new Text("Add Element:"));

        ScrollPane scrollPane = new ScrollPane();
        root.addRow(1, scrollPane);
        listViewEdit = new ListView<>();
        scrollPane.setContent(listViewEdit);

        scrollPane = new ScrollPane();
        root.addRow(3, scrollPane);
        listViewDelete = new ListView<>();
        scrollPane.setContent(listViewDelete);

        scrollPane = new ScrollPane();
        root.addRow(5, scrollPane);
        listViewAdd = new Button("Add Value");
        listViewAdd.setOnAction(event -> {
            socketsForValues.add(new InputSocket(socket.getNode(), socket.getName(), new MultiValue()));
        });
        scrollPane.setContent(listViewAdd);



        //textArea = new TextArea(getValue().toString());
        //root.addColumn(0, textArea);
    }

    @Override
    public void updateValue() {
        MultiValue value = new MultiValue();
        for (Socket socket : socketsForValues) {
            value.getValue().add(socket.getValue());
        }
        getSocket().setValue(value);
    }

    /**
     * Updates the buttons when the values change
     */
    public void updateButtons() {
        listViewEdit.getItems().setAll();
        listViewDelete.getItems().setAll();
        for (Socket socket : socketsForValues) {
            Button button = new Button(socket.getValue().toString());
            button.setOnAction(event -> {
                ValueEditor valueEditor = new ValueEditor(socket);
            });
            listViewEdit.getItems().add(button);

            button = new Button(socket.getValue().toString());
            button.setOnAction(event -> {
                socketsForValues.remove(socket);
            });
            listViewDelete.getItems().add(button);
        }
        listViewEdit.refresh();
        listViewDelete.refresh();
    }
}
