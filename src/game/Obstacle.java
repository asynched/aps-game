package game;

import processing.core.PImage;

public class Obstacle {
    final int SIZE = 35;
    final int OFFSET = 20;

    private int x;
    private int y;

    private PImage image;

    private Game context;

    public Obstacle(Game context) {
        this.context = context;
        this.x = this.context.width;
        this.y = this.context.height - this.SIZE;
        this.image = this.chooseImage();
    }

    /**
     * Generates a random image to every
     * object instantiated
     * @return A random image
     */
    private PImage chooseImage() {
        if(this.context.random(1) > 0.5)
            return this.context.loadImage("assets/trash0000.png");
        else
            return this.context.loadImage("assets/trash0001.png");
    }

    /**
     * Moves the obstacle
     */
    public void move() {
        this.x -= this.context.getSpeed();
    }

    /**
     * Renders the obstacle in the UI
     */
    public void show() {
        this.context.image(
                this.image,
                this.x,
                this.y - this.OFFSET,
                this.SIZE,
                this.SIZE
        );
    }

    /**
     * Getter function to get the obstacle's X position
     * @return Obstacle's X position
     */
    public int getX() {
        return this.x;
    }

    /**
     * Getter function to get the obstacle's Y position
     * @return Obstacle's Y position
     */
    public int getY() {
        return this.y;
    }

}
