package me.jono.javascriptscript.gui;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
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
