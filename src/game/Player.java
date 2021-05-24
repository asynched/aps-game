package game;

import processing.core.PImage;
import processing.sound.SoundFile;
// import processing.sound.SoundFile;

import java.util.ArrayList;

public class Player {
    final int SIZE = 50;
    final double GRAVITY = 0.75;
    final int OFFSET = 25;

    ArrayList<PImage> images = new ArrayList<>();

    // Player position
    int x;
    double y;
    double yVelocity;

    // Sound
    SoundFile jumping;
    SoundFile death;

    // Player sprite
    int currentImage = 0;

    // game.Game context
    Game context;

    public Player(Game context) {
        this.context = context;
        this.x = this.SIZE;
        this.y = this.context.height - (this.SIZE + this.OFFSET);
        this.yVelocity = 0;

        this.jumping = new SoundFile(this.context, "jumping0000.mp3");
        this.death = new SoundFile(this.context, "death0000.mp3");

        images.add(this.context.loadImage("assets/mario-running0000.png"));
        images.add(this.context.loadImage("assets/mario-running0001.png"));
        images.add(this.context.loadImage("assets/mario-jumping0000.png"));
    }

    public void jump() {
     if(this.isTouchingTheGround() && this.context.isLooping()) {
         this.yVelocity = -15;
         this.jumping.play();
     }
    }

    public void move() {
        this.y += this.yVelocity;
        this.yVelocity += this.GRAVITY;

        this.constrainPlayer();
    }

    private void constrainPlayer() {
        this.y = this.context.constrain((int) this.y, 0, this.context.height - (this.SIZE + this.OFFSET));
    }


    private boolean isTouchingTheGround() {
        return this.y == this.context.height - (this.SIZE + this.OFFSET);
    }

    private void chooseImage() {
        if(this.currentImage == 1) {
            this.currentImage = 0;
            return;
        }

        this.currentImage = 1;
    }

    public boolean checkIfHasCollided(Obstacle obstacle) {
        float distance = this.context.dist(this.x, (int) this.y, obstacle.getX(), obstacle.getY());

        if(distance <= 50) {
            death.play();
            return true;
        }

        return false;
    }

    public void show() {
        if(!this.isTouchingTheGround()) {
            this.currentImage = 2;
        } else if(this.context.frameCount % 10 == 0) {
            this.chooseImage();
        }

        PImage playerImage = this.images.get(this.currentImage);

        this.context.image(playerImage, this.x, (int) this.y, this.SIZE, this.SIZE + 5);
    }

    public void reset() {
        this.y = this.context.height - this.SIZE;
        this.yVelocity = 0;
        this.currentImage = 0;
    }

}
