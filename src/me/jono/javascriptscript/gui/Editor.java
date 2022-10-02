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
import me.jono.javascriptscript.ProgramGraph;
import me.jono.javascriptscript.ToDoList;
import me.jono.javascriptscript.nodes.Embedded;

import java.io.File;
import java.io.IOException;

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
        });
        MenuItem menuOpen = new MenuItem("Open");
        menuOpen.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open a .jssppsmm file");
            File file = fileChooser.showOpenDialog(stage);

            program = new Embedded("program");
            program.getInput("program").setValue(new ProgramGraph(file));
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
        ctx.strokeLine(0, 0, width, height);
        ctx.strokeLine(width, 0, 0, height);

        ctx.setFill(Color.color(1, 0.9, 0.8));
        ctx.setFont(new Font(100));
        ctx.fillText("Work in Progress", width/3, height-25, width/3);
        ctx.fillText("This is a placeholder.", width/5, 100, 3*width/5);
    }
}
