Player player;

Parallax background;
Parallax poles;
VariableSpeedParallax buildings;

SoundFile jumping;

ArrayList<Trash> trashes = new ArrayList<Trash>();

int speed = 4;
int score = 1;

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
  buildings = new VariableSpeedParallax("assets/buildings0000.png", 2469, this);
  
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
    trashes.add(new Trash(this));
  }

  for(Trash trash : trashes) {
    trash.show();
    trash.move();
    
    if(player.checkIfCollided(trash)) {
      looping = !looping;
      image(gameOver, 0, 0);
    }
  }
  
  if(frameCount % 10 == 0)
    score++;
  
  if(frameCount %  900 == 0) {
    speed++;
    println("Dificuldade aumentou, dificuldade: " + speed);
  }
  
  fill(0);
  textSize(24);
  text("Score: " + score, width - 200, 25);
}

void reset() {
  player.reset();
  trashes.clear();
  background.reset();
  poles.reset();
  buildings.reset();
  score = 1;
  speed = 4;
}

void mousePressed() {
  if(!looping) {
    reset();
    looping = true;
  }
}

void keyPressed() {
  if(key == ' ' || keyCode == UP && looping) {
    player.jump();
  }
}
