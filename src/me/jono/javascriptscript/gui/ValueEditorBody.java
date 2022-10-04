package me.jono.javascriptscript.gui;

import me.jono.javascriptscript.Socket;
import me.jono.javascriptscript.Value;

/**
 * @author jono
 * The body of a ValueEditor
 * The {@link ValueEditor ValueEditor} has this and a menu for changing the value type if desired
 */
public abstract class ValueEditorBody extends javafx.scene.Parent {
    private final Socket socket;

    /**
     * Gets the value
     * @return the value
     */
    public Value getValue() {return socket.getValue();}

    /**
     * Gets the socket
     * @return socket
     */
    public Socket getSocket() {return socket;}

    /**
     * Creates a ValueEditorBody
     * @param socket the socket to edit the value of
     */
    public ValueEditorBody(Socket socket) {
        this.socket = socket;
    }

    public abstract void updateValue();
}
