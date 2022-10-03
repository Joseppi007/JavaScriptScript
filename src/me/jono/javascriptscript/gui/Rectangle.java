package me.jono.javascriptscript.gui;

import javafx.scene.canvas.GraphicsContext;
import org.w3c.dom.css.Rect;

/**
 * @author jono
 * Rectangles are used to define the position and size of a Node.
 * Has an x, y, width, and height.
 */
public class Rectangle {
    private double x, y, width, height;

    public Rectangle(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Gets the x part of the position
     * @return the x coordinate
     */
    public double getX() {return x;}

    /**
     * Gets the y part of the position
     * @return the y coordinate
     */
    public double getY() {return y;}

    /**
     * Gets the width
     * @return the width
     */
    public double getWidth() {return width;}

    /**
     * Gets the height
     * @return the height
     */
    public double getHeight() {return height;}

    /**
     * Gets the x part of the position for pixels
     * @param camera the camera to use to map to screen cords
     * @return the x coordinate
     */
    public double getXPixels(Camera camera) {return (x-camera.getX())*camera.getScale();}

    /**
     * Gets the y part of the position for pixels
     * @param camera the camera to use to map to screen cords
     * @return the y coordinate
     */
    public double getYPixels(Camera camera) {return (y-camera.getY())*camera.getScale();}

    /**
     * Gets the width for pixels
     * @param camera the camera to use to map to screen cords
     * @return the width
     */
    public double getWidthPixels(Camera camera) {return width*camera.getScale();}

    /**
     * Gets the height for pixels
     * @param camera the camera to use to map to screen cords
     * @return the height
     */
    public double getHeightPixels(Camera camera) {return height*camera.getScale();}

    /**
     * Sets the x
     * @param x the new x
     */
    public void setX(double x) {this.x = x;}

    /**
     * Sets the y
     * @param y the new y
     */
    public void setY(double y) {this.y = y;}

    /**
     * Sets the width
     * @param width the new width
     */
    public void setWidth(double width) {this.width = width;}

    /**
     * Sets the height
     * @param height the new height
     */
    public void setHeight(double height) {this.height = height;}

    /**
     * Sets the x from pixels
     * @param x the new x before mapping
     * @param camera the camera to use to map from screen cords
     */
    public void setXPixels(double x, Camera camera) {this.x = (x + camera.getX()) / camera.getScale();}

    /**
     * Sets the y from pixels
     * @param y the new y before mapping
     * @param camera the camera to use to map from screen cords
     */
    public void setYPixels(double y, Camera camera) {this.y = (y + camera.getY()) / camera.getScale();}

    /**
     * Sets the width from pixels
     * @param width the new width before mapping
     * @param camera the camera to use to map from screen cords
     */
    public void setWidthPixels(double width, Camera camera) {this.width = width / camera.getScale();}

    /**
     * Sets the height from pixels
     * @param height the new height before mapping
     * @param camera the camera to use to map from screen cords
     */
    public void setHeightPixels(double height, Camera camera) {this.height = height / camera.getScale();}

    @Override
    public Rectangle clone() {
        return new Rectangle(x, y, width, height);
    }

    @Override
    public String toString() {
        return "Rectangle{x: "+getX()+",y: "+getY()+",width: "+getWidth()+",height: "+getHeight()+"}";
    }

    /**
     * Paints the Rectangle to the GraphicsContext with the provided camera
     * @param ctx the GraphicsContext to paint with
     * @param camera the camera to look through
     */
    public void paint(GraphicsContext ctx, Camera camera) {
        double width = ctx.getCanvas().getWidth();
        double height = ctx.getCanvas().getHeight();

        ctx.fillRect(
                getXPixels(camera)*width,
                getYPixels(camera)*height,
                getWidthPixels(camera)*width,
                getHeightPixels(camera)*height
        );
    }
}
