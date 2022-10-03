package me.jono.javascriptscript;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import me.jono.javascriptscript.gui.Camera;
import me.jono.javascriptscript.gui.Rectangle;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author jono
 * To understand what a Node is, let me explain how the language JavaScriptScript++#--.jssppsmm works. If you've ever
 * seen graph theory or a circuit diagram or the stuff in Blender, it's basically just that. There are Nodes and
 * connections between the Nodes. Each node does a simple little thing, like add some of its properties together or
 * display text. The nodes come together to make cool programs. In theory, this is a cool visual way of doing code, but
 * in practice, things can get... messy... This is why I made JavaScriptScript++#--.jssppsmm this sort of visual
 * language. The language is a joke that I want to be completely usable, but also just a mess. Then again, this is my
 * second crack at making this. I erased my old code to make stuff work better. So I guess it's just a little
 * serious for me.
 */
public abstract class Node {
    private HashMap<String, InputSocket> inputs;
    private HashMap<String, OutputSocket> outputs;
    private String name; // Must be UNIQUE
    private Rectangle rectangle;
    private Color color;

    /**
     * Creates a new Node
     * @param name The unique name of the Node
     */
    public Node(String name) {
        this.name = name;
        inputs = new HashMap<>();
        outputs = new HashMap<>();
        rectangle = new Rectangle(-0.5, -0.5, 1, 1);
        color = Color.color(0.8, 0.75, 1);
    }

    /**
     * Gets all the input sockets
     * @return the input sockets
     */
    public HashMap<String, InputSocket> getInputs() {return inputs;}

    /**
     * Gets all the output sockets
     * @return the output sockets
     */
    public HashMap<String, OutputSocket> getOutputs() {return outputs;}

    /**
     * Gets the rectangle that the Node is graphically
     * @return the rectangle
     */
    public Rectangle getRectangle() {return rectangle;}

    /**
     * Gets the color that the Node is graphically
     * @return the color
     */
    public Color getColor() {return color;}

    /**
     * Returns the name of the Node.
     * @return the name
     */
    public String getName() {return name;}

    /**
     * Sets the name to something new--make sure the name is unique
     * @param name the new name
     */
    public void setName(String name) {this.name = name;}

    /**
     * Sets the rectangle to a new rectangle
     * @param rectangle the new rectangle
     */
    public void setRectangle(Rectangle rectangle) {this.rectangle = rectangle;}

    /**
     * Sets the color to a new color
     * @param color the new color
     */
    public void setColor(Color color) {this.color = color;}

    /**
     * Gets the input socket with the provided name and creates one if one doesn't exist already. I allow creating new
     * inputs because this could be useful, and if it isn't, it feels like the kind of thing that would be in
     * JavaScriptScript++#--.jssppsmm anyways.
     * @param name The name of the InputSocket
     * @return The InputSocket with the name or a new InputSocket with the name
     */
    public InputSocket getInput(String name) {
        if (inputs.containsKey(name)) {
            return inputs.get(name);
        }
        InputSocket created = new InputSocket(this, name);
        inputs.put(name, created);
        return created;
    }

    /**
     * Gets the output socket with the provided name and returns a new one if it doesn't exist
     * @param name The name of the OutputSocket
     * @return The OutputSocket with the name or null
     */
    public OutputSocket getOutput(String name) {
        if (outputs.containsKey(name)) {
            return outputs.get(name);
        }
        OutputSocket created = new OutputSocket(this, name);
        outputs.put(name, created);
        return created;
    }

    /**
     * Slaps a new InputSocket onto the Node--returns success status
     * @param name the name of the new InputSocket
     * @return the success status
     */
    public boolean newInput(String name) {
        if (inputs.containsKey(name)) return false;
        inputs.put(name, new InputSocket(this, name));
        return true;
    }

    /**
     * Slaps a new InputSocket onto the Node with a default value--returns success status
     * @param name the name of the new InputSocket
     * @param defaultValue the default value of the Socket
     * @return the success status
     */
    public boolean newInput(String name, Value defaultValue) {
        if (inputs.containsKey(name)) return false;
        inputs.put(name, new InputSocket(this, name, defaultValue));
        return true;
    }

    /**
     * Slaps a new OutputSocket onto the Node--returns success status
     * @param name the name of the OutputSocket
     * @return the success status
     */
    protected boolean newOutput(String name) {
        if (outputs.containsKey(name)) return false;
        outputs.put(name, new OutputSocket(this, name));
        return true;
    }

    /**
     * Slaps a new OutputSocket onto the Node with a default value--returns success status
     * @param name the name of the OutputSocket
     * @param defaultValue the default value of the Socket
     * @return the success status
     */
    protected boolean newOutput(String name, Value defaultValue) {
        if (outputs.containsKey(name)) return false;
        outputs.put(name, new OutputSocket(this, name, defaultValue));
        return true;
    }

    /**
     * Lets the Node know that an input has been updated, and that it may want to {@link Node#sendOutputs sendOutputs}
     * after changing them.
     * @param inputUpdated
     */
    public abstract void update(String inputUpdated, ToDoList toDoList);

    /**
     * Go through each of the Connections and add them to the ToDoList.
     */
    public void sendOutputs(ToDoList toDoList) {
        for (OutputSocket socket : outputs.values()) {
            socket.addConnectionsToToDoList(toDoList);
        }
    }

    @Override
    public String toString() {
        return getName() + ":" + getClass().getCanonicalName();
    }

    /**
     * Returns true if any of the InputSockets have the empty MultiValue
     * @return are any of the inputs empty MultiValues? (aka Null)
     */
    public boolean hasNullInput() {
        for (InputSocket socket : getInputs().values()) {
            if (isInputNull(socket.getName())) return true;
        }
        return false;
    }

    /**
     * Returns true if the input is Null (an empty MultiValue)
     * @param inputName the name of the InputSocket to check
     * @return is the socket null?
     */
    public boolean isInputNull(String inputName) {
        return getInput(inputName).getValue() instanceof MultiValue &&
                ((MultiValue)getInput(inputName).getValue()).getValue().size() == 0;
    }

    /**
     * Paints the Node and its sockets using {@link GraphicsContext ctx}
     * @param ctx A {@link GraphicsContext GraphicsContext} to paint with
     * @param camera Where we're looking
     */
    public void paint(GraphicsContext ctx, Camera camera) {
        double width = ctx.getCanvas().getWidth();
        double height = ctx.getCanvas().getHeight();

        // The rectangle part
        ctx.setStroke(Color.WHITE);
        ctx.setFill(color);
        rectangle.paint(ctx, camera);

        // The center text part
        if (color.getBrightness() < 0.5) {
            ctx.setStroke(Color.BLACK);
            ctx.setFill(Color.WHITE);
        } else {
            ctx.setStroke(Color.WHITE);
            ctx.setFill(Color.BLACK);
        }
        ctx.setFont(new Font(rectangle.getHeightPixels(camera)*height/3));
        ctx.setTextAlign(TextAlignment.CENTER);
        ctx.fillText(
                this.getClass().getSimpleName(),
                (rectangle.getXPixels(camera)+rectangle.getWidthPixels(camera)/2) * width,
                (rectangle.getYPixels(camera)+rectangle.getHeightPixels(camera)*2/3) * height,
                rectangle.getWidthPixels(camera) * width
        );

        // InputSockets
        double verticalSpacing = rectangle.getHeightPixels(camera) * height / getInputs().size();
        double x = rectangle.getXPixels(camera) * width;
        double y = rectangle.getYPixels(camera) * height + verticalSpacing / 2;
        ctx.setFont(new Font(verticalSpacing));
        ctx.setTextAlign(TextAlignment.RIGHT);
        ctx.setStroke(Color.color(1, 1, 1, 0.5));
        ctx.setFill(Color.color(1, 1, 1));
        for (InputSocket inputSocket : getInputs().values()) {
            ctx.fillOval(x - 5, y - 5, 10, 10);
            ctx.strokeOval(x - 5, y - 5, 10, 10);
            y += verticalSpacing;
        }

        // OutputSockets
        verticalSpacing = rectangle.getHeightPixels(camera) * height / getOutputs().size();
        x = ( rectangle.getXPixels(camera) + rectangle.getWidthPixels(camera) ) * width;
        y = rectangle.getYPixels(camera) * height + verticalSpacing / 2;
        ctx.setFont(new Font(verticalSpacing));
        ctx.setTextAlign(TextAlignment.RIGHT);
        for (OutputSocket outputSocket : getOutputs().values()) {
            ctx.fillOval(x - 5, y - 5, 10, 10);
            ctx.strokeOval(x - 5, y - 5, 10, 10);
            y += verticalSpacing;
        }

    }

    /**
     * Paints the connections. This is separate so that layering can be done better.
     * @param ctx A {@link GraphicsContext GraphicsContext} to paint with
     * @param camera Where we're looking
     */
    public void paintConnections(GraphicsContext ctx, Camera camera) {
        double width = ctx.getCanvas().getWidth();
        double height = ctx.getCanvas().getHeight();

        double verticalSpacing = rectangle.getHeightPixels(camera) / getOutputs().size();
        double x = ( rectangle.getXPixels(camera) + rectangle.getWidthPixels(camera) ) * width;
        double y = rectangle.getYPixels(camera) * height + verticalSpacing / 2;

        System.out.println("("+x+", "+y+")");

        ctx.setStroke(Color.color(1, 1, 1, 0.5));
        ctx.setFill(Color.color(1, 1, 1));
        for (OutputSocket outputSocket : getOutputs().values()) {
            y += verticalSpacing;
            for (Connection connection : outputSocket.getOutgoingConnections()) {
                ctx.setLineWidth(connection.getPriority());
                Rectangle other = connection.getEnd().getRectangle();
                ctx.strokeLine(
                        x,
                        y,
                        other.getXPixels(camera) * width,
                        other.getYPixels(camera) * height +
                                other.getHeightPixels(camera) *
                                        (connection.getEnd().getInputsList().indexOf(connection.getInputSocket()) /
                                        connection.getEnd().getInputs().size() + 0.5) * height
                );
            }
        }

    }

    /**
     * Gets the input sockets as an array list
     * @return the inputs
     */
    public ArrayList<InputSocket> getInputsList() {
        return new ArrayList<>(getInputs().values());
    }

    /**
     * Gets the output sockets as an array list
     * @return the outputs
     */
    public ArrayList<OutputSocket> getOutputsList() {
        return new ArrayList<>(getOutputs().values());
    }

}
