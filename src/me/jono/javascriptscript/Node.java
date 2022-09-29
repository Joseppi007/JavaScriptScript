package me.jono.javascriptscript;

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
     * Gets the output socket with the provided name and returns null if it doesn't exist
     * @param name The name of the OutputSocket
     * @return The OutputSocket with the name or null
     */
    public OutputSocket getOutput(String name) {
        if (outputs.containsKey(name)) {
            return outputs.get(name);
        }
        return null;
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

    @Override
    public String toString() {
        return getName() + ":" + getClass().getCanonicalName();
    }

    /**
     * Returns a string representation of the object.
     * @return a string representation of the object.
     */
    public String toDetailedString() {
        StringBuilder a = new StringBuilder(toString());
        a.append("{");
        for (InputSocket input : inputs.values()) {
            a.append(input+",");
        }
        a.delete(a.length()-1, a.length());
        a.append("}{");
        for (OutputSocket output : outputs.values()) {
            a.append(output+",");
        }
        a.delete(a.length()-1, a.length());
        a.append("}");
        return a.toString();
    }
}
