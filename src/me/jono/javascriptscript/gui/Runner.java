package me.jono.javascriptscript.gui;

import javafx.scene.Group;
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

        /*

        for (InputSocket inputSocket : program.getInputs().values()) {
            if (inputSocket.getName().equals("program")) {continue;}
            Text t = new Text();
            t.setText(inputSocket.getName());
            t.setFont(new Font(20));
            t.setLayoutX(10);
            t.setLayoutY(vertical_position);
            runRoot.getChildren().add(t);
            vertical_position += 5;

            TextField tf = new TextField();
            tf.setText(inputSocket.getValue().toString());
            tf.setFont(new Font(20));
            tf.setLayoutX(10);
            tf.setLayoutY(vertical_position);
            tf.setOnAction(event1 -> {
                for (String key : inputTextField.keySet()) {
                    program.getInput(key).setValue(new TextValue(inputTextField.get(key).getText()));
                }
                program.update("", new ToDoList(ToDoList.Ordering.STACK));
                for (String key : outputText.keySet()) {
                    outputText.get(key).setText(program.getOutput(key).getValue().toString());
                }
            });
            runRoot.getChildren().add(tf);
            vertical_position += 80;
            inputTextField.put(inputSocket.getName(), tf);
        }
        vertical_position = 30;
        for (OutputSocket outputSocket : program.getOutputs().values()) {
            Text t = new Text();
            t.setText(outputSocket.getName());
            t.setFont(new Font(20));
            t.setLayoutX(410);
            t.setLayoutY(vertical_position);
            runRoot.getChildren().add(t);
            vertical_position += 30;

            Text t1 = new Text();
            t1.setText(outputSocket.getValue().toString());
            t1.setFont(new Font(20));
            t1.setLayoutX(420);
            t1.setLayoutY(vertical_position);
            runRoot.getChildren().add(t1);
            vertical_position += 55;
            outputText.put(outputSocket.getName(), t1);
        }
         */

        Text inputText = new Text("Inputs");
        inputText.setFont(new Font(24));
        root.addColumn(0, inputText);

        Text paddingText = new Text("\t");
        paddingText.setFont(new Font(24));
        root.addColumn(1, paddingText);

        Text outputText = new Text("Outputs");
        outputText.setFont(new Font(24));
        root.addColumn(2, outputText);

        this.show();
    }
}
