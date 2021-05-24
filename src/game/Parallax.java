package game;

import processing.core.PImage;

public class Parallax {
    int imageWidth;
    int speed;
    PImage image;

    Game context;

    // Parallax effect;
    int originalBackgroundX;
    int copyBackgroundX;

    public Parallax(Game context, String imageURI, int imageWidth, int speed) {
        this.context = context;
        this.image = this.context.loadImage(imageURI);
        this.imageWidth = imageWidth;
        this.speed = speed;

        this.originalBackgroundX = 0;
        this.copyBackgroundX = this.imageWidth;
    }

    /**
     * Renders the background
     */
    void show() {
        this.context.image(this.image, this.originalBackgroundX, 0);
        this.context.image(this.image, this.copyBackgroundX, 0);

        this.originalBackgroundX -= this.speed;
        this.copyBackgroundX -= this.speed;

        // Check if the background is not showing anymore
        if(this.originalBackgroundX < -this.imageWidth) {
            this.originalBackgroundX = this.imageWidth;
        }

        if(this.copyBackgroundX < -this.imageWidth) {
            this.copyBackgroundX = this.imageWidth;
        }
    }

    /**
     * Resets the position of the background
     */
    void reset() {
        this.originalBackgroundX = 0;
        this.copyBackgroundX = this.imageWidth;
    }
}
