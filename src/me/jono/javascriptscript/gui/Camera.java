package me.jono.javascriptscript.gui;

/**
 * @author jono
 * Keeps track of the position and zoom of the camera
 */
public class Camera {
    private double x, y, scale;

    /**
     * Creates a Camera looking at the origin with a scale to see from -10 to 10.
     */
    public Camera() {
        this(-10, -10, 0.05);
    }

    /**
     * Creates a Camera with the provided position and scale
     * @param x The x coordinate
     * @param y The y coordinate
     * @param scale The scale--higher is more zoomed in
     */
    public Camera(double x, double y, double scale) {
        this.x = x;
        this.y = y;
        this.scale = scale;
    }

    public double getX() {return x;}
    public double getY() {return y;}
    public double getScale() {return scale;}

    public void setX(double x) {this.x = x;}
    public void setY(double y) {this.y = y;}
    public void setScale(double scale) {this.scale = scale;}

    /**
     * Zooms in to a point or out from a point
     * @param screenX The x position on the screen divided by the width (between 0 and 1)
     * @param screenY The y position on the screen divided by the height (between 0 and 1)
     * @param zoom What to multiply the scale by--more than one means zooming out, and less means in.
     */
    public void zoom(double screenX, double screenY, double zoom) {
        this.x += scale * (1 - zoom) * screenX;
        this.y += scale * (1 - zoom) * screenY;
        this.scale *= zoom;
    }

    @Override
    public String toString() {
        return "Camera{x: "+getX()+",y: "+getY()+",scale: "+getScale()+"}";
    }
}
