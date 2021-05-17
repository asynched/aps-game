class Trash {
  
  final int SIZE = 35;
  final int OFFSET = 20;

  int x;
  int y;
  int speed;

  PImage image;
 
   
  public Trash(int speed) {
    this.speed = speed;
    this.x = width;
    this.y = height - this.SIZE;
   
    if(random(1) > 0.5) {
      this.image = loadImage("assets/trash0000.png");
    } else {
      this.image = loadImage("assets/trash0001.png");
    }
  }
  
  void move() {
    this.x -= this.speed;
  }
  
  void show() {
    image(this.image, this.x, this.y - this.OFFSET, this.SIZE, this.SIZE);
  }
  
}
