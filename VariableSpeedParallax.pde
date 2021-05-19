class VariableSpeedParallax {
  int imageWidth;
  Game context;
  PImage image;
  
  // Parallax effect;
  int originalBackgroundX;
  int copyBackgroundX;
  
  public VariableSpeedParallax(String imageURI, int imageWidth, Game context) {
    this.image = loadImage(imageURI);
    this.imageWidth = imageWidth;
    this.context = context;
    
    this.originalBackgroundX = 0;
    this.copyBackgroundX = this.imageWidth;
  }
  
  void show() {
    image(this.image, this.originalBackgroundX, 0);
    image(this.image, this.copyBackgroundX, 0);
    
    this.originalBackgroundX -= this.context.speed;
    this.copyBackgroundX -= this.context.speed;
    
    // Check if the background is not showing anymore
    if(this.originalBackgroundX < -this.imageWidth) {
      this.originalBackgroundX = this.imageWidth;
    }
    
    if(this.copyBackgroundX < -this.imageWidth) {
      this.copyBackgroundX = this.imageWidth;
    }
  }
  
  void reset() {
    this.originalBackgroundX = 0;
    this.copyBackgroundX = this.imageWidth;
  }
}
