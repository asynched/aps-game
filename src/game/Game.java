package game;

import processing.core.PImage;
import processing.sound.SoundFile;
import processing.core.PApplet;

import java.util.ArrayList;

public class Game extends PApplet {
    // Images
    private PImage startGameImage;
    private PImage resetGameImage;

    // Player
    private Player player;

    // Obstacles
    private ArrayList<Obstacle> obstacles;

    // Background
    private Parallax background;
    private Parallax poles;
    private VariableSpeedParallax buildings;

    // Miscellaneous
    private boolean isFirstTimePlaying = true;
    private int score = 0;
    private int speed = 4;

    // Sound
    SoundFile music;

    public static void main(String[] args) {
        PApplet.main("game.Game", args);
    }

    /**
     * First method to be called in the pre-render
     * phase
     *
     * Loads all assets and sets the game up
     */
    @Override
    public void settings() {
        // Setup
        this.size(640, 360);

        // Sound
        music = new SoundFile(this, "music0000.mp3");

        // Images
        startGameImage = this.loadImage("assets/start0000.png");
        resetGameImage = this.loadImage("assets/game-over0000.png");

        // Player
        player = new Player(this);

        // Backgrounds
        background = new Parallax(this, "assets/background0000.png", 1810, 1);
        poles = new Parallax(this, "assets/poles0000.png", 2033, 2);
        buildings = new VariableSpeedParallax(this, "assets/buildings0000.png", 2469);

        // Obstacles
        obstacles = new ArrayList<>();

        // Start music
        music.loop();

        this.looping = false;
    }

    /**
     * Renders the UI and checks
     * for business logic
     */
    @Override
    public void draw() {
        // Draw
        background.show();
        poles.show();
        buildings.show();
        player.show();
        player.move();

        // Business logic
        this.calculateScore();
        this.showScore();
        this.recalculateSpeed();
        this.addObstacleRandomly();
        this.renderObstacles();

        // Game related
        if(this.isFirstTimePlaying) {
            image(this.startGameImage, 0, 0);
        }

        if(!this.isFirstTimePlaying && !this.looping) {
            image(this.resetGameImage, 0, 0);
        }
    }

    /**
     * Resets the game state
     */
    private void reset() {
        this.music.play();
        this.obstacles.clear();
        this.player.reset();
        this.buildings.reset();
        this.background.reset();
        this.poles.reset();
        this.score = 0;
        this.speed = 4;
    }

    /**
     * Renders all obstacles and
     * checks if the player has
     * collided with any
     * of them
     */
    private void renderObstacles() {
        for(Obstacle obstacle : this.obstacles) {
            obstacle.show();
            obstacle.move();

            if(this.player.checkIfHasCollided(obstacle))
                this.gameOver();
        }
    }

    /**
     * Stops the music from playing
     * and sets the looping to
     * false
     */
    private void gameOver() {
        this.music.stop();
        this.looping = false;
    }

    /**
     * Generates a new obstacle based
     * on a random number generator
     */
    private void addObstacleRandomly() {
        if(this.random(1) < 0.40 && this.frameCount % 45 == 0) {
            this.obstacles.add(new Obstacle(this));
        }
    }

    /**
     * Recalculates the speed of the
     * game on every 900 frames
     */
    private void recalculateSpeed() {
        if(this.frameCount % 900 == 0)
            this.speed++;
    }

    /**
     * Recalculates the player's
     * score on every 10 frames
     */
    private void calculateScore() {
        if(this.frameCount % 10 == 0)
            this.score++;
    }

    /**
     * Show the user score on the UI
     */
    private void showScore() {
        this.fill(0);
        this.textSize(24);
        this.text("Score: " + this.score, this.width - 200, 25);
    }


    @Override
    public void keyPressed() {
        if(this.key == ' ' || this.keyCode == this.UP && this.looping) {
            player.jump();
        }
    }

    /**
     * Starts the match
     */
    private void startGame() {
        this.looping = true;
        this.isFirstTimePlaying = false;
    }

    /**
     * Resets the match
     */
    private void resetGame() {
        reset();
        this.looping = true;
    }

    @Override
    public void mousePressed() {
        if(this.isFirstTimePlaying)
            this.startGame();

        if(!this.looping && !this.isFirstTimePlaying)
            this.resetGame();
    }

    /**
     * Getter function to get the
     * main context speed
     *
     * @return Returns the game's current speed
     */
    public int getSpeed() {
        return this.speed;
    }
}
