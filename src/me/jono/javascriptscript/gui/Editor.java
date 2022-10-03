package me.jono.javascriptscript.gui;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import me.jono.javascriptscript.InputSocket;
import me.jono.javascriptscript.OutputSocket;
import me.jono.javascriptscript.ProgramGraph;
import me.jono.javascriptscript.ToDoList;
import me.jono.javascriptscript.nodes.Embedded;

import java.io.File;
import java.io.IOException;

/**
 * @author jono
 * The GUI for JavaScriptScript++#--.jssppsmm
 */
public class Editor extends Application {
    private Canvas canv;
    private VBox vbox;
    private Group root;
    private Scene scene;
    private MenuBar menuBar;
    private Embedded program;

    public static void main(String[] args) {launch(args);}
    @Override
    public void start(Stage stage) throws Exception {
        vbox = new VBox();
        root = new Group();
        menuBar = new MenuBar();
        vbox.getChildren().addAll(menuBar, root);

        Menu menuFile = new Menu("File");
        Menu menuEdit = new Menu("Edit");
        menuBar.getMenus().addAll(menuFile, menuEdit);

        program = new Embedded("program");
        program.getInput("program").setValue(new ProgramGraph(ToDoList.Ordering.STACK));

        MenuItem menuNew = new MenuItem("New");
        menuNew.setOnAction(event -> {
            program = new Embedded("program");
            program.getInput("program").setValue(new ProgramGraph(ToDoList.Ordering.STACK));
            program.makeSockets();
        });
        MenuItem menuOpen = new MenuItem("Open");
        menuOpen.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open a .jssppsmm file");
            File file = fileChooser.showOpenDialog(stage);

            program = new Embedded("program");
            program.getInput("program").setValue(new ProgramGraph(file));
            program.makeSockets();

            paint();
        });
        MenuItem menuSave = new MenuItem("Save As");
        menuSave.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save as a .jssppsmm file");
            File file = fileChooser.showSaveDialog(stage);

            try {
                ((ProgramGraph)program.getInput("program").getValue()).writeToFile(file);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        });
        menuFile.getItems().addAll(menuNew, menuOpen, menuSave);

        MenuItem menuPopup = new MenuItem("Create Popup");
        menuPopup.setOnAction(event -> {
            Stage popup = new Stage();
            Group popupRoot = new Group();
            Scene popupScene = new Scene(popupRoot, 800, 600, Color.color(0.05, 0.1, 0.0));
            popup.setScene(popupScene);
            popup.setTitle("Popup");
            popup.show();
        });
        menuEdit.getItems().addAll(menuPopup);

        scene = new Scene(vbox, 800, 600, Color.color(0.5, 0.1, 0.2));
        stage.setScene(scene);

        canv = new Canvas(800, 600);
        root.getChildren().add(canv);
        paint();

        scene.widthProperty().addListener((ChangeListener) (observable, oldValue, newValue) -> {paint();});
        scene.heightProperty().addListener((ChangeListener) (observable, oldValue, newValue) -> {paint();});

        stage.setTitle("JavaScriptScript++#--.jssppsmm Editor");
        stage.show();
        paint();
    }

    /**
     * Paints on the Canvas
     */
    public void paint() {
        double width = vbox.getWidth();
        double height = vbox.getHeight()-menuBar.getHeight();

        canv.setWidth(width);
        canv.setHeight(height);

        GraphicsContext ctx = canv.getGraphicsContext2D();
        ctx.setFill(Color.color(0, 0.1, 0.2));
        ctx.fillRect(0, 0, width, height);

        ctx.setStroke(Color.color(1, 0.9, 0.8));
        ctx.strokeLine(width*0.45, 0, width*0.55, height);
        ctx.strokeLine(width*0.55, 0, width*0.45, height);

        ctx.setFill(Color.color(1, 0.9, 0.8));
        double a = 0;
        double h = height/(program.getInputs().size()-1);
        ctx.setFont(new Font(h));
        for (InputSocket inputSocket : program.getInputs().values()) {
            if (inputSocket.getName().equals("program")) continue;
            a += h;
            ctx.fillText(inputSocket.getName()+": "+inputSocket.getValue(), 10, a, width/2-10);
        }
        a = 0;
        h = height/(program.getInputs().size()-1);
        ctx.setFont(new Font(h));
        for (OutputSocket outputSocket : program.getOutputs().values()) {
            a += h;
            ctx.fillText(outputSocket.getName()+": "+outputSocket.getValue(), width/2, a, width/2-10);
        }
    }
}
