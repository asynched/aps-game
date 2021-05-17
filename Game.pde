Player player;

Parallax background;
Parallax poles;
Parallax buildings;

SoundFile jumping;

ArrayList<Trash> trashes = new ArrayList<Trash>();

// Images
PImage gameOver;

void setup() {
  // Game setup
  frameRate(60);
  size(640, 360);
  
  // Load assets
  gameOver = loadImage("assets/game-over0000.png");
  jumping = new SoundFile(this, "jumping0000.mp3");

  // Parallax images
  background = new Parallax("assets/background0000.png", 1810, 1);
  poles = new Parallax("assets/poles0000.png", 2033, 2);
  buildings = new Parallax("assets/buildings0000.png", 2471, 4);
  
  // Player
  player = new Player(jumping);
}

void draw() {
  background.show();
  poles.show();
  buildings.show();


  // Show player
  player.show();
  player.move();

  // Show trash
  if(random(1) < 0.005) {
    trashes.add(new Trash(4));
  }

  for(Trash trash : trashes) {
    trash.show();
    trash.move();
    
    if(player.checkIfCollided(trash)) {
      looping = !looping;
      image(gameOver, 0, 0);
    }
  }
  
}


void keyPressed() {
  if(key == ' ' || keyCode == UP) {
    player.jump();
  }
}  
