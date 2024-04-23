package org.bcit.comp2522.project;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import processing.core.PVector;
import processing.sound.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Window extends PApplet  {
  private MongoDB mongo;
  private PImage gameBg;
  private Player player;
  private EnemyCollection<Enemy> enemyCol;
  private BulletCollection<Bullet> bulletCol;
  private UpgradeCollection<Upgrade> upgradeCol;
  private Button startBtn;
  private Button leaderboardBtn;
  private Button nameSubmitBtn;
  private Button backBtn;
  private Lives lives;
  private HealthBar healthBar;
  private int screen;
  private int wave;
  private int score;
  private int enemy1Count;
  private int enemy2Count;
  private int enemy3Count;
  private int startWaveTime;
  private boolean waveCompleted = false;
  private boolean speedUpgrade = false;
  private String username;
  private String json;
  private static final float PLAYER_LEFT_PAD = 50f;
  private static final float ENEMY_POSX = 100f;
  private static final int ENEMY_BULLET_DELAY = 2000;
  private static final int NEXT_WAVE_DELAY = 4000;
  private SoundFile music;
  private SoundFile shoot;
  private SoundFile explode;

  /**
   * Runs before applet starts.
   */
  public void setup() {
    // Start the database connection
    mongo = new MongoDB(this);
    mongo.run();

    // Play music
    music = new SoundFile(this, "/assets/music.mp3");
    music.play();

    // Initialize sounds
    shoot = new SoundFile(this, "/assets/shoot.wav");
    explode = new SoundFile(this, "/assets/explode.wav");

    // Initialize background image
    gameBg = loadImage("assets/menu-bg.jpg");

    // Initialize font
    PFont font = createFont("assets/OpenSans-SemiBold.ttf", 128, true);
    textFont(font);

    // Initialize player
    PVector playerPosition = new PVector(PLAYER_LEFT_PAD, height / 2f);
    player = Player.getInstance(playerPosition, this);

    // Initialize EnemyCollection
    this.enemyCol = new EnemyCollection<>();

    // Initialize BulletCollection
    this.bulletCol = new BulletCollection<>();

    // Initialize UpgradeCollection
    this.upgradeCol = new UpgradeCollection<>();

    // Initialize start button
    this.startBtn = new Button(new PVector(208, 330), 26, "N E W   G A M E", this);

    // Initialize leaderboard button
    this.leaderboardBtn = new Button(new PVector(242, 390), 26, "L E A D E R B O A R D", this);

    // Initialize name submit button
    this.nameSubmitBtn = new Button(new PVector(this.width / 2f, this.height / 2f + 122), 26, "S U B M I T", this);

    // Initialize back button
    this.backBtn = new Button(new PVector(90, 50), 26, "B A C K", this);

    // Initialize lives
    this.lives = new Lives(55, this.height - 85);

    // Initialize health bar
    this.healthBar = new HealthBar(player.getHealth());

    // Set state to 0 (main menu)
    this.screen = 0;

    // Set game wave to 0
    this.wave = 0;

    // Set player score to 0
    this.score = 0;

    // Initialize user name
    this.username = "";
  }

  /**
   * Runs on each frame.
   */
  public void draw() {
    // Draw the background image
    background(gameBg);

    // Draw the current screen
    switch (screen) {
      case 0 -> mainMenuScreen();
      case 1 -> gameScreen();
      case 2 -> gameOverScreen();
      case 3 -> leaderboardScreen();
    }
  }

  public void settings() {
    size(1080, 720);
  }

  /**
   * Main menu screen.
   * Allows player to start the game or view leaderboards.
   */
  public void mainMenuScreen() {
    // Game title text
    fill(255, 255, 255);
    textSize(52);
    text("D  O  G  F  I  G  H  T", 114, 230);

    // Draw the buttons
    startBtn.draw();
    leaderboardBtn.draw();
  }

  /**
   * Runs the game.
   */
  public void gameScreen() {
    // Thread-safe remove bullets
    bulletCol.removeAllBullets();

    // Draw the bullets
    for (Bullet b : bulletCol) {
      b.draw();
      b.move();

      // Remove the bullet when it reaches end of screen
      if (b.getBoundingBox().getRight() >= this.width) {
        bulletCol.removeBullet(b, "player");
      } else if (b.getBoundingBox().getLeft() <= 0) {
        bulletCol.removeBullet(b, "enemy");
      }

      // Player collision with enemy bullet
      if (player.getBoundingBox().collides(b.getBoundingBox()) && b.getParentType().equals("enemy")) {
        bulletCol.removeBullet(b, "enemy");
        player.setHealth(player.getHealth() - b.getDamage());
      }

      // Player bullet collision with enemy bullet
      for (Bullet b2 : bulletCol) {
        if (b.getBoundingBox().collides(b2.getBoundingBox())
                && b.getParentType().equals("enemy")
                && b2.getParentType().equals("player")) {
          if (b.getDamage() > b2.getDamage()) {
            bulletCol.removeBullet(b2, "player");
          } else if (b.getDamage() == b2.getDamage()){
            bulletCol.removeBullet(b, "enemy");
            bulletCol.removeBullet(b2, "player");
          } else if (b.getDamage() < b2.getDamage()) {
            bulletCol.removeBullet(b, "enemy");
          }
        }
      }
    }

    // Draw the player
    player.draw(this);
    player.move(this);

    // Thread-safe remove enemies
    enemyCol.removeAllEnemies();

    // Draw the enemies
    for (Enemy e : enemyCol) {
      e.draw(this);
      e.move();

      // Remove the enemy when it reaches end of screen
      if (e.getBoundingBox().getLeft() <= 0) {
        switch (e.getType()) {
          case "enemy1" -> score -= 5;
          case "enemy2" -> score -= 10;
          case "enemy3" -> score -= 15;
        }

        enemyCol.removeEnemy(e);
      }
      // Remove the enemy when it collides with player and regenerate the player
      else if (e.getBoundingBox().collides(player.getBoundingBox())) {
        enemyCol.removeEnemy(e);
        player.regen();
        explode.play();
      }

      // Enemies shoot with a delay of 1000 milliseconds
      if (millis() - e.getStartTime() >= ENEMY_BULLET_DELAY) {
        if (e.isInWindow()) {
          bulletCol.addBullet(e.shoot(), e.type);
          shoot.play();
          e.setStartTime(millis());
        }
      }

      // Enemy collision with player bullet
      for (Bullet b : bulletCol) {
        if (e.getBoundingBox().collides(b.getBoundingBox()) && b.getParentType().equals("player")) {
          bulletCol.removeBullet(b, "player");
          e.setHealth(b.getDamage());
          System.out.println(e.getHealth());

          // Kill enemy
          if (e.getHealth() <= 0) {
            enemyCol.removeEnemy(e);
            explode.play();

            // Add score for killing enemy
            switch (e.getType()) {
              case "enemy1" -> score += 5;
              case "enemy2" -> score += 10;
              case "enemy3" -> score += 15;
            }

            // Initialize the chance percentage
            double chance = Math.random();

            // Spawn an upgrade
            if (chance < 0.20) {
              Upgrade upgrade = new Upgrade(e.getPosition(), "health", this);
              upgradeCol.addUpgrade(upgrade);
            } else if (chance < 0.35) {
              Upgrade upgrade = new Upgrade(e.getPosition(), "speed", this);
              upgradeCol.addUpgrade(upgrade);
            } else if (chance < 0.40) {
              Upgrade upgrade = new Upgrade(e.getPosition(), "upgrade", this);
              upgradeCol.addUpgrade(upgrade);
            }
          }
        }
      }
    }

    // Thread-safe remove all upgrades
    upgradeCol.removeAllUpgrades();

    // Draw the upgrades
    for (Upgrade u : upgradeCol) {
      u.draw();
      u.move();

      if (u.getBoundingBox().collides(player.getBoundingBox())) {
        upgradeCol.removeUpgrade(u);

        if (u.type.equals("health")) {
          player.setHealth(min(player.getHealth() + 25, 100));
        } else if (u.type.equals("speed")) {
          speedUpgrade = true;

          // Start speed upgrade effect
          for (Enemy e : enemyCol) {
            if (e.isInWindow()) {
              e.setSpeed(max(e.getSpeed() - 1f, 0.5f));
            }
          }
        } else if (u.type.equals("upgrade")) {
          player.setUpgrade(player.getUpgrade() + 1);
        }
      }
    }

    // Regenerate the player when health is 0
    if (player.getHealth() <= 0) {
      player.regen();
      explode.play();
    }

    // Save time when wave ends
    if (!waveCompleted) {
      startWaveTime = millis();
    }

    // Start next wave when player kills all enemies in current wave
    if (enemyCol.getCount() <= 0 && player.getLives() >= 0) {
      //set enemy count to 0 to prevent enemy count from going to negative
      enemyCol.setCount(0);
      waveCompleted = true;
      speedUpgrade = false;

      // Stage clear text
      push();
      fill(255);
      textAlign(CENTER);
      textSize(64);
      text("W A V E   " + wave + "   C L E A R", width / 2f, height / 2f);
      pop();

      // Start next wave
      if (millis() - startWaveTime >= NEXT_WAVE_DELAY) {
        wave++;
        startWave(wave);
        waveCompleted = false;
      }
    }

    // Draw the lives
    this.lives.draw(player.getLives(), this);

    // Draw the health bar
    this.healthBar.draw(player.getHealth(), this);

    // Player score heading text
    fill(255);
    textSize(16);
    text("SCORE", 245, this.height - 30);

    // Player score text
    textSize(24);
    text(this.score, 305, this.height - 30);

    // Game over if player loses all lives or finishes all waves
    if (player.getLives() <= 0 || wave > 3) {
      screen = 2;
    }
  }

  /**
   * Game over screen.
   */
  public void gameOverScreen() {
    // Total score heading text
    push();
    fill(255);
    textSize(38);
    text("SCORE", this.width / 2f - 145, this.height / 2f - 70);

    // Total score text
    textSize(68);
    text(this.score, this.width / 2f, this.height / 2f - 70);

    // Enter name heading text
    textSize(32);
    text("Enter your name", this.width / 2f - 320, this.height / 2f + 30);

    // Name input text box
    noFill();
    stroke(255);
    strokeWeight(3);
    rect(this.width / 2f - 20, this.height / 2f - 8, 315, 50);

    // Name input text
    fill(255);
    textSize(28);
    text(this.username, this.width / 2f - 4, this.height / 2f + 28);

    // Name submit button
    fill(255);
    rect(this.width / 2f - 100, this.height / 2f + 100, 200, 50);
    nameSubmitBtn.draw();
    nameSubmitBtn.setColor(0);
    pop();
  }

  /**
   * Leaderboard screen.
   */
  public void leaderboardScreen() {
    // Back button
    push();
    fill(255);
    rect(30, 30, 120, 50);
    backBtn.draw();
    backBtn.setColor(0);
    pop();

    // Title text
    push();
    textAlign(CENTER);
    text("L E A D E R B O A R D", this.width / 2f, 75);
    pop();

    // Table headings text
    push();
    textSize(28);
    text("R A N K", 150, 175);
    text("N A M E", 320, 175);
    text("S C O R E", 620, 175);
    text("W A V E S", 800, 175);
    stroke(150);
    line(140, 200, 935, 200);
    pop();

    // Leaderboard rankings text
    push();
    textSize(20);
    rankings();
    pop();
  }

  /**
   * Starts the game stage.
   *
   * @param wave the stage the player is on
   */
  public void startWave(int wave) {
    // Assign combination of enemy types for each stage.
    switch (wave) {
      case 1 -> {
//        enemy1Count = 1;
//        enemy2Count = 1;
//        enemy3Count = 1;
        enemy1Count = 20;
        enemy2Count = 0;
        enemy3Count = 0;
      }
      case 2 -> {
//        enemy1Count = 1;
//        enemy2Count = 1;
//        enemy3Count = 1;
        enemy1Count = 12;
        enemy2Count = 8;
        enemy3Count = 0;
      }
      case 3 -> {
//        enemy1Count = 1;
//        enemy2Count = 1;
//        enemy3Count = 1;
        enemy1Count = 10;
        enemy2Count = 7;
        enemy3Count = 3;
      }
    }

    // Add enemy type 1 to EnemyCollection
    for (int i = 0; i < enemy1Count; i++) {
      PVector enemyPosition = new PVector(this.width + (ENEMY_POSX * 2 * i)
              + random(0, ENEMY_POSX), random(0, height));
      Enemy enemy1 = new Enemy(enemyPosition, "enemy1", this);
      enemyCol.addEnemy(enemy1);
    }

    // Add enemy type 2 to EnemyCollection
    for (int i = 0; i < enemy2Count; i++) {
      PVector enemyPosition = new PVector(this.width + (ENEMY_POSX * 2 * i)
              + random(0, ENEMY_POSX), random(0, height));
      Enemy enemy2 = new Enemy(enemyPosition, "enemy2", this);
      enemyCol.addEnemy(enemy2);
    }

    // Add enemy type 3 to EnemyCollection
    for (int i = 0; i < enemy3Count; i++) {
      PVector enemyPosition = new PVector(this.width + (ENEMY_POSX * 2 * i)
              + random(0, ENEMY_POSX), random(0, height));
      Enemy enemy3 = new Enemy(enemyPosition, "enemy3", this);
      enemyCol.addEnemy(enemy3);
    }
  }

  /**
   * Resets the game.
   */
  public void resetGame() {
    player.setLives(3);
    player.setHealth(100);
    player.setUpgrade(1);
    wave = 0;
    score = 0;
    enemy1Count = 0;
    enemy2Count = 0;
    enemy3Count = 0;
    enemyCol.clearAllEnemies();
    bulletCol.clearAllBullets();
    upgradeCol.clearAllUpgrades();
    username = "";
  }

  public void rankings() {
    text("1 S T", 150, 240);
    text("2 N D", 150, 240 + (45));
    text("3 R D", 150, 240 + (2 * 45));

    for (int i = 3; i < 10; i++) {
      text((i + 1) + " T H", 150, 240 + (i * 45));
    }

//    for (int i = 0; i < 10; i++) {
//      text("N O U F I L", 352, 240 + (i * 45));
//      text("4 5 5", 802, 240 + (i * 45));
//    }

    if (json != null) {
      JSONArray jsonarr = new JSONArray("[" + json + "]");
      int top = jsonarr.length();
      if (top > 10) {
        top = 10;
      }
      for (int i = 0; i < top; i++) {
        JSONObject jsonobj = jsonarr.getJSONObject(i);
        text(jsonobj.getString("name"), 322, 240 + (i * 45));
        text(jsonobj.getInt("score"), 622, 240 + (i * 45));
        text(jsonobj.getInt("wave"), 802, 240 + (i * 45));
      }
    }

  }

  /**
   * Player shoots on mouse click.
   */
  public void mousePressed() {
    switch (screen) {
      case 0:
        if (startBtn.isHovered()) {
          screen = 1;
          gameBg = loadImage("assets/bg.jpg");
          wave++;
          startWave(wave);
        }

        if (leaderboardBtn.isHovered()) {
          screen = 3;
          gameBg = loadImage("assets/bg.jpg");
          mongo.createLeaderboard();
        }
        break;
      case 1:
        bulletCol.addBullet(player.shoot(), player.type);
        shoot.play();
        break;
      case 2:
        if (nameSubmitBtn.isHovered() && this.username.length() > 0) {
          username = username.replace("", " ").trim();
          System.out.println(username);
          screen = 0;
          gameBg = loadImage("assets/menu-bg.jpg");
          mongo.createDoc(this.username, this.score, this.wave);
          resetGame();
        }
        break;
      case 3:
        if (backBtn.isHovered()) {
          screen = 0;
          gameBg = loadImage("assets/menu-bg.jpg");
          mongo.clearDocuments();
        }

        break;
    }
  }

  /**
   * Checks user input for player name.
   */
  public void keyTyped() {
    if (screen == 2) {
      if (key == BACKSPACE) {
        if (username.length() > 0) {
          username = username.substring(0, username.length() - 1);
        }
      } else if (key != ENTER && textWidth(username) < 230) {
        username = (username + key).toUpperCase();
      }
    }
  }

  /**
   * Retrieves the leaderboard from database.
   * @param j the leaderboard as json string
   */
  public void getLeaderboard(String j) {
    json = j;
    System.out.println(json);
  }

  public static void main(String[] args) {
    String[] processingArgs = {"window"};
    Window window = new Window();
    PApplet.runSketch(processingArgs, window);
  }
}
