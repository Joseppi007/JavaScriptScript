package me.jono.javascriptscript.gui;

import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import me.jono.javascriptscript.InputSocket;
import me.jono.javascriptscript.OutputSocket;
import me.jono.javascriptscript.TextValue;
import me.jono.javascriptscript.ToDoList;
import me.jono.javascriptscript.nodes.Embedded;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author jono
 * The Runner runs the program
 */
public class Runner extends Stage {
    private Embedded program;

    /**
     * Creates a Runner for the provided Embedded program
     * @param program the program
     */
    public Runner(Embedded program) {
        super();

        this.program = program;

        GridPane root = new GridPane();
        Scene scene = new Scene(root, 800, 600, Color.color(0.075, 0.15, 0.0));
        this.setScene(scene);
        this.setTitle("Jossum"); // Jos Opossum

        ArrayList<javafx.scene.Node> inputs = new ArrayList<>();
        ArrayList<javafx.scene.Node> outputs = new ArrayList<>();
        HashMap<String, TextField> inputTextFields = new HashMap<>();
        HashMap<String, Text> outputTexts = new HashMap<>();

        for (InputSocket inputSocket : program.getInputs().values()) {
            if (inputSocket.getName().equals("program")) {continue;}
            Text t = new Text();
            t.setText(inputSocket.getName());
            t.setFont(new Font(18));
            inputs.add(t);

            TextField tf = new TextField();
            tf.setText(inputSocket.getValue().toString());
            tf.setFont(new Font(18));
            inputs.add(tf);
            inputTextFields.put(inputSocket.getName(), tf);
            tf.setOnAction(event -> {
                for (String key : inputTextFields.keySet()) {
                    program.getInput(key).setValue(new TextValue(inputTextFields.get(key).getText()));
                }
                program.update("", new ToDoList(ToDoList.Ordering.STACK));
                for (String key : outputTexts.keySet()) {
                    outputTexts.get(key).setText(program.getOutput(key).getValue().toString());
                }
            });
        }

        for (OutputSocket outputSocket : program.getOutputs().values()) {
            Text t = new Text();
            t.setText(outputSocket.getName());
            t.setFont(new Font(18));
            outputs.add(t);

            Text t1 = new Text();
            t1.setText(outputSocket.getValue().toString());
            t1.setFont(new Font(18));
            outputs.add(t1);
            outputTexts.put(outputSocket.getName(), t1);
        }

        javafx.scene.Node[] template = {};

        Text inputText = new Text("Inputs");
        inputText.setFont(new Font(24));
        root.addColumn(0, inputText);
        root.addColumn(0, inputs.toArray(template));

        Text paddingText = new Text("\t");
        paddingText.setFont(new Font(24));
        root.addColumn(1, paddingText);

        Text outputText = new Text("Outputs");
        outputText.setFont(new Font(24));
        root.addColumn(2, outputText);
        root.addColumn(2, outputs.toArray(template));

        this.show();
    }
}
