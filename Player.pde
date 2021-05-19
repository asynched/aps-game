import processing.sound.SoundFile;

class Player {
  final int SIZE = 50;
  final float GRAVITY = 0.75;
  final int OFFSET = 25;

  ArrayList<PImage> images = new ArrayList<PImage>();
  int currentImage = 0;
  
  SoundFile jumpingSound;
  
  // Player position
  int x;
  float y;
  float yVelocity;
  
  public Player(SoundFile sound) {
    this.x = SIZE;
    this.y = height - SIZE;
    this.yVelocity = 0;
    
    this.jumpingSound = sound;
    
    images.add(loadImage("assets/mario-running0000.png"));
    images.add(loadImage("assets/mario-running0001.png"));
    images.add(loadImage("assets/mario-jumping0000.png"));
  }
  
  void jump() {
    // If the player is touching the ground
    if(this.isTouchingTheGround() && looping) {
      this.yVelocity = -15;
      this.jumpingSound.play();
    }
  }
  
  void move() {
    this.y += this.yVelocity;
    this.yVelocity += this.GRAVITY;
    constrainPlayer();
  }
  
  void constrainPlayer() {
    this.y = constrain(this.y, 0, height - (this.SIZE + this.OFFSET));
  }
  
  boolean isTouchingTheGround() {
    return this.y == height - (this.SIZE + this.OFFSET);
  }
  
  void show() {
    if(!isTouchingTheGround()) {
      this.currentImage = 2;
    } else if(frameCount % 10 == 0) {
      this.currentImage = this.currentImage == 0 ? 1 : 0;
    }
    
    image(this.images.get(this.currentImage), this.x, this.y, SIZE, SIZE + 5);
  }
  
  boolean checkIfCollided(Trash trash) {
    float distance = dist(this.x, this.y, trash.x, trash.y);
    
    if(distance <= 50) {
      return true;
    }
    
    return false;
  }
  
  void reset() {
    this.y = height - this.SIZE;
    this.yVelocity = 0;
    this.currentImage = 0;
  }
}
