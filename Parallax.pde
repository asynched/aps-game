class Parallax {
  int imageWidth;
  int speed;
  PImage image;
  
  // Parallax effect;
  int originalBackgroundX;
  int copyBackgroundX;
  
  public Parallax(String imageURI, int imageWidth, int speed) {
    this.image = loadImage(imageURI);
    this.imageWidth = imageWidth;
    this.speed = speed;
    
    this.originalBackgroundX = 0;
    this.copyBackgroundX = this.imageWidth;
  }
  
  void show() {
    image(this.image, this.originalBackgroundX, 0);
    image(this.image, this.copyBackgroundX, 0);
    
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
}
