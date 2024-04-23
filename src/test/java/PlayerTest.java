import org.bcit.comp2522.project.Enemy;
import org.bcit.comp2522.project.Player;
import org.bcit.comp2522.project.Bullet;
import org.bcit.comp2522.project.Window;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PApplet;
import processing.core.PVector;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
  Player p1, p2;
  Enemy e1;
  float width, height;
  int upgrade;
  Window window;

  @BeforeEach
  void setUp() {
    window = new Window();
    PVector playerPosition = new PVector(width/2f, height / 2f);
    String[] processingArgs = {"window"};
    PApplet.runSketch(processingArgs, window);
    p1 = Player.getInstance(playerPosition, window);
  }

  /**
   * Tests for player singleton.
   */
  @Test
  void getInstance() {
    PVector posB = new PVector(width/2f, height/2f);
    p2 = Player.getInstance(posB, window);
    assertEquals(p1, p2);
  }

  /**
   * Tests for player position after regenerating.
   */
  @Test
  void regenPosition() {
    PVector posB = new PVector(50f, 0f);
    p1.regen();
    assertEquals(p1.getPosition(), posB);
  }

  /**
   * Tests for player regeneration.
   * Checks if player health and lives are updated.
   */
  @Test
  void regen1() {
    PVector posB = new PVector(width/2f, height/2f);
    p1 = Player.getInstance(posB, window);
    p1.setHealth(50);
    p1.setLives(1);
    p1.regen();
    assertEquals(p1.getHealth(), 100);
    assertEquals(p1.getLives(),0);
  }

  /**
   * Tests for player regeneration.
   * Checks if player health and lives are updated.
   */
  @Test
  void regen2() {
    PVector posB = new PVector(width/2f, height/2f);
    p1 = Player.getInstance(posB, window);
    p1.setHealth(500);
    p1.setLives(5);
    p1.regen();
    assertEquals(p1.getHealth(), 100);
    assertEquals(p1.getLives(),4);
  }

  /**
   * Tests for player regeneration.
   * Checks if player health and lives are updated.
   */
  @Test
  void regen3() {
    PVector posB = new PVector(width/2f, height/2f);
    p1 = Player.getInstance(posB, window);
    p1.setHealth(90);
    p1.setLives(2);
    p1.regen();
    assertEquals(p1.getHealth(), 100);
    assertEquals(p1.getLives(),1);
  }

  /**
   * Tests for player bullet type changes on upgrade.
   */
  @Test
  void shoot1() {
    Bullet bullet = p1.shoot();
    assertEquals(bullet.getBulletType(),"p1");
    assertEquals(bullet.getParentType(),"player");
  }

  /**
   * Tests for player bullet type changes on upgrade.
   */
  @Test
  void shoot2() {
    p1.setUpgrade(2);
    Bullet bullet = p1.shoot();
    assertEquals(bullet.getBulletType(),"p2");
    assertEquals(bullet.getParentType(),"player");
  }

  /**
   * Tests for player bullet type changes on upgrade.
   */
  @Test
  void shoot3() {
    p1.setUpgrade(3);
    Bullet bullet = p1.shoot();
    assertEquals(bullet.getBulletType(),"p3");
    assertEquals(bullet.getParentType(),"player");
  }

  /**
   * Tests for player bullet type changes on upgrade.
   */
  @Test
  void shoot4() {
    p1.setUpgrade(4);
    Bullet bullet = p1.shoot();
    assertEquals(bullet.getBulletType(),"p4");
    assertEquals(bullet.getParentType(),"player");
  }

  /**
   * Tests for player bullet type changes on upgrade.
   */
  @Test
  void shoot5() {
    p1.setUpgrade(5);
    Bullet bullet = p1.shoot();
    assertEquals(bullet.getBulletType(),"p4");
    assertEquals(bullet.getParentType(),"player");
  }

  /**
   * Tests for player bullet type changes on upgrade.
   */
  @Test
  void shoot6() {
    p1.setUpgrade(12);
    Bullet bullet = p1.shoot();
    assertEquals(bullet.getBulletType(),"p4");
    assertEquals(bullet.getParentType(),"player");
  }

  /**
   * Tests for player and enemy1 bullet collision.
   */
  @Test
  void collisionBulletEnemy1_1() {
    PVector enemyPosition1 = new PVector(55f, 30f);
    e1 = new Enemy(enemyPosition1, "enemy1", window);

    PVector posP1 = new PVector(10f, 30f);
    p1.setPosition(posP1);

    Bullet b = e1.shoot();
    b.move();
    System.out.println("collisionBulletEnemy1_1\nbullet pos: " + b.getPosition());
    System.out.println("player pos: " + p1.getPosition());

    System.out.println("bullet left bBox: " + b.getBoundingBox().getLeft());
    System.out.println("player right bBox: " + p1.getBoundingBox().getRight());

    assertEquals(b.getParentType(),"enemy");
    assertTrue(p1.getBoundingBox().collides(b.getBoundingBox()));
  }

  /**
   * Tests for player and enemy1 bullet collision.
   */
  @Test
  void collisionBulletEnemy1_2() {
    PVector enemyPosition1 = new PVector(60f, 350f);
    e1 = new Enemy(enemyPosition1, "enemy1", window);

    PVector posP1 = new PVector(10f, 350f);
    p1.setPosition(posP1);

    Bullet b = e1.shoot();
    b.move();
    b.move();
    b.move();

    System.out.println("collisionBulletEnemy1_2\nbullet pos: " + b.getPosition());
    System.out.println("player pos: " + p1.getPosition());

    System.out.println("bullet left bBox: " + b.getBoundingBox().getLeft());
    System.out.println("player right bBox: " + p1.getBoundingBox().getRight());

    assertEquals(b.getParentType(),"enemy");
    assertTrue(p1.getBoundingBox().collides(b.getBoundingBox()));
  }

  /**
   * Tests for player and enemy1 bullet collision.
   */
  @Test
  void collisionBulletEnemy1_3() {
    PVector enemyPosition1 = new PVector(64f, 20f);
    e1 = new Enemy(enemyPosition1, "enemy1", window);

    PVector posP1 = new PVector(10f, 20f);
    p1.setPosition(posP1);

    Bullet b = e1.shoot();
    b.move();
    b.move();
    b.move();
    b.move();

    System.out.println("collisionBulletEnemy1_3\nbullet pos: " + b.getPosition());
    System.out.println("player pos: " + p1.getPosition());

    System.out.println("bullet left bBox: " + b.getBoundingBox().getLeft());
    System.out.println("player right bBox: " + p1.getBoundingBox().getRight());

    assertEquals(b.getParentType(),"enemy");
    assertTrue(p1.getBoundingBox().collides(b.getBoundingBox()));
  }

  /**
   * Tests for player and enemy2 bullet collision.
   */
  @Test
  void collisionBulletEnemy2_1() {
    PVector enemyPosition1 = new PVector(68f, 20f);
    e1 = new Enemy(enemyPosition1, "enemy2", window);

    PVector posP1 = new PVector(10f, 20f);
    p1.setPosition(posP1);

    Bullet b = e1.shoot();
    b.move();
    b.move();
    b.move();
    b.move();

    System.out.println("collisionBulletEnemy2_1\nbullet pos: " + b.getPosition());
    System.out.println("player pos: " + p1.getPosition());

    System.out.println("bullet left bBox: " + b.getBoundingBox().getLeft());
    System.out.println("player right bBox: " + p1.getBoundingBox().getRight());

    assertEquals(b.getParentType(),"enemy");
    assertTrue(p1.getBoundingBox().collides(b.getBoundingBox()));
  }

  /**
   * Tests for player and enemy2 bullet collision.
   */
  @Test
  void collisionBulletEnemy2_2() {
    PVector enemyPosition1 = new PVector(64f, 350f);
    e1 = new Enemy(enemyPosition1, "enemy2", window);

    PVector posP1 = new PVector(10f, 350f);
    p1.setPosition(posP1);

    Bullet b = e1.shoot();
    b.move();
    b.move();
    b.move();

    System.out.println("collisionBulletEnemy2_2\nbullet pos: " + b.getPosition());
    System.out.println("player pos: " + p1.getPosition());

    System.out.println("bullet left bBox: " + b.getBoundingBox().getLeft());
    System.out.println("player right bBox: " + p1.getBoundingBox().getRight());

    assertEquals(b.getParentType(),"enemy");
    assertTrue(p1.getBoundingBox().collides(b.getBoundingBox()));
  }

  /**
   * Tests for player and enemy2 bullet collision.
   */
  @Test
  void collisionBulletEnemy2_3() {
    PVector enemyPosition1 = new PVector(56f, 30f);
    e1 = new Enemy(enemyPosition1, "enemy2", window);

    PVector posP1 = new PVector(10f, 30f);
    p1.setPosition(posP1);

    Bullet b = e1.shoot();
    b.move();
    System.out.println("collisionBulletEnemy2_3\nbullet pos: " + b.getPosition());
    System.out.println("player pos: " + p1.getPosition());

    System.out.println("bullet left bBox: " + b.getBoundingBox().getLeft());
    System.out.println("player right bBox: " + p1.getBoundingBox().getRight());

    assertEquals(b.getParentType(),"enemy");
    assertTrue(p1.getBoundingBox().collides(b.getBoundingBox()));
  }

  /**
   * Tests for player and enemy3 bullet collision.
   */
  @Test
  void collisionBulletEnemy3_1() {
    PVector enemyPosition1 = new PVector(57f, 35f);
    e1 = new Enemy(enemyPosition1, "enemy3", window);

    PVector posP1 = new PVector(10f, 30f);
    p1.setPosition(posP1);

    Bullet b = e1.shoot();
    b.move();
    System.out.println("collisionBulletEnemy3_1\nbullet pos: " + b.getPosition());
    System.out.println("player pos: " + p1.getPosition());

    System.out.println("bullet left bBox: " + b.getBoundingBox().getLeft());
    System.out.println("player right bBox: " + p1.getBoundingBox().getRight());

    assertEquals(b.getParentType(),"enemy");
    assertTrue(p1.getBoundingBox().collides(b.getBoundingBox()));
  }

  /**
   * Tests for player and enemy3 bullet collision.
   */
  @Test
  void collisionBulletEnemy3_2() {
    PVector enemyPosition1 = new PVector(67f, 340f);
    e1 = new Enemy(enemyPosition1, "enemy3", window);

    PVector posP1 = new PVector(10f, 350f);
    p1.setPosition(posP1);

    Bullet b = e1.shoot();
    b.move();
    b.move();
    b.move();

    System.out.println("collisionBulletEnemy3_2\nbullet pos: " + b.getPosition());
    System.out.println("player pos: " + p1.getPosition());

    System.out.println("bullet left bBox: " + b.getBoundingBox().getLeft());
    System.out.println("player right bBox: " + p1.getBoundingBox().getRight());

    assertEquals(b.getParentType(),"enemy");
    assertTrue(p1.getBoundingBox().collides(b.getBoundingBox()));
  }

  /**
   * Tests for player and enemy3 bullet collision.
   */
  @Test
  void collisionBulletEnemy3_3() {
    PVector enemyPosition1 = new PVector(72f, 25f);
    e1 = new Enemy(enemyPosition1, "enemy3", window);

    PVector posP1 = new PVector(10f, 20f);
    p1.setPosition(posP1);

    Bullet b = e1.shoot();
    b.move();
    b.move();
    b.move();
    b.move();

    System.out.println("collisionBulletEnemy3_3\nbullet pos: " + b.getPosition());
    System.out.println("player pos: " + p1.getPosition());

    System.out.println("bullet left bBox: " + b.getBoundingBox().getLeft());
    System.out.println("player right bBox: " + p1.getBoundingBox().getRight());

    assertEquals(b.getParentType(),"enemy");
    assertTrue(p1.getBoundingBox().collides(b.getBoundingBox()));
  }



}