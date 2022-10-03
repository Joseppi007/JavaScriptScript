package me.jono.javascriptscript.gui;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import me.jono.javascriptscript.*;
import me.jono.javascriptscript.nodes.Embedded;

import java.io.File;
import java.util.HashMap;

/**
 * @author jono
 * The first crack at the GUI.
 * This may be deleted later.
 */
public class Runner extends Application {

    public static void main(String[] args) {launch(args);}

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        stage.setTitle("JavaScriptScript++#--.jssppsmm");
        Scene scene = new Scene(root,800, 600, Color.color(0.05, 0.1, 0.15));

        Text text = new Text();
        text.setText("Right now this only runs programs... :/ Sorry");
        text.setFill(Color.color(1, 1, 0.8));
        text.setFont(Font.font(50));
        text.setX(10);
        text.setY(50);
        root.getChildren().add(text);

        Text text1 = new Text();
        text1.setText("Think you could maybe select the file you want to run after clicking that button?");
        text1.setFill(Color.color(1, 1, 0.8));
        text1.setFont(Font.font(20));
        text1.setX(10);
        text1.setY(85);
        root.getChildren().add(text1);

        Text text2 = new Text();
        text2.setText("Sorry that this is bad, I'm still learning JavaFX.");
        text2.setFill(Color.color(1, 1, 0.8));
        text2.setFont(Font.font(15));
        text2.setX(10);
        text2.setY(110);
        root.getChildren().add(text2);

        Button button = new Button("Select a file to run");
        button.setFont(new Font(20));
        button.setLayoutX(10);
        button.setLayoutY(130);
        button.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Pick a .jssppsmm file, please :)");
            File file = fileChooser.showOpenDialog(stage);

            Group runRoot = new Group();
            Scene runScene = new Scene(runRoot,800, 600);
            stage.setScene(runScene);
            Embedded program = new Embedded("program");
            program.getInput("program").setValue(new ProgramGraph(file));
            program.makeSockets();
            int vertical_position = 30;
            HashMap<String, TextField> inputTextField = new HashMap<>();
            HashMap<String, Text> outputText = new HashMap<>();
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
        });
        root.getChildren().add(button);

        /*
        javafx.scene.control.TextField textField = new TextField();
        textField.setFont(new Font(20));
        textField.setLayoutX(10);
        textField.setLayoutY(130);
        textField.setOnAction(event -> {
            File file = new File(textField.getText());
            Group runRoot = new Group();
            Scene runScene = new Scene(runRoot,800, 600);
            stage.setScene(runScene);
            Embedded program = new Embedded("program");
            program.getInput("program").setValue(new ProgramGraph(file));
            program.makeSockets();
            int vertical_position = 30;
            HashMap<String, TextField> inputTextField = new HashMap<>();
            HashMap<String, Text> outputText = new HashMap<>();
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
        });
        root.getChildren().add(textField);
        */

        stage.setScene(scene);
        stage.show();

    }

}
