import org.bcit.comp2522.project.Enemy;
import org.bcit.comp2522.project.Bullet;
import org.bcit.comp2522.project.Player;
import org.bcit.comp2522.project.Window;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PApplet;
import processing.core.PVector;

import static org.junit.jupiter.api.Assertions.*;

class EnemyTest {

  Enemy e1, e2, e3;
  float width, height;
  Window window;

  @BeforeEach
  void setUp() {
    window = new Window();
    String[] processingArgs = {"window"};
    PApplet.runSketch(processingArgs, window);
    PVector enemyPosition1 = new PVector(50f, 50f);
    PVector enemyPosition2 = new PVector(50f, 50f);
    PVector enemyPosition3 = new PVector(50f, 50f);
    e1 = new Enemy(enemyPosition1, "enemy1", window);
    e2 = new Enemy(enemyPosition2, "enemy2", window);
    e3 = new Enemy(enemyPosition3, "enemy3", window);
  }

  /**
   * Tests for the different enemy type.
   */
  @Test
  void setEnemy() {
    assertNotEquals(e1, e2);
    assertNotEquals(e1, e3);
    assertNotEquals(e2, e3);
  }

  /**
   * Tests for enemy 1 bullet type.
   */
  @Test
  void shoot1() {
    Bullet bullet = e1.shoot();
    assertEquals(bullet.getBulletType(),"enemy1");
    assertEquals(bullet.getParentType(),"enemy");
  }

  /**
   * Tests for enemy 2 bullet type.
   */
  @Test
  void shoot2() {
    Bullet bullet = e2.shoot();
    assertEquals(bullet.getBulletType(),"enemy2");
    assertEquals(bullet.getParentType(),"enemy");
  }

  /**
   * Tests for enemy 3 bullet type.
   */
  @Test
  void shoot3() {
    Bullet bullet = e3.shoot();
    assertEquals(bullet.getBulletType(),"enemy3");
    assertEquals(bullet.getParentType(),"enemy");
  }

  /**
   * Tests for enemy1 and player bullet collision.
   */
  @Test
  void collisionBulletE1_1() {
    PVector posP1 = new PVector(10f, 30f);
    PVector posE1 = new PVector(60f, 30f);

    Player p1 = Player.getInstance(posP1, window);
    e1.setPosition(posE1);

    Bullet b = p1.shoot();
    b.move();

    System.out.println("collisionBulletE1_1\nbullet pos: " + b.getPosition());
    System.out.println("enemy pos: " + e1.getPosition());

    System.out.println("bullet right bBox: " + b.getBoundingBox().getRight());
    System.out.println("enemy left bBox: " + e1.getBoundingBox().getLeft());

    assertEquals(b.getParentType(),"player");
    assertTrue(e1.getBoundingBox().collides(b.getBoundingBox()));
  }

  /**
   * Tests for enemy1 and player bullet collision.
   */
  @Test
  void collisionBulletE1_2() {
    PVector posP1 = new PVector(10f, 300f);
    PVector posE1 = new PVector(66f, 300f);

    Player p1 = Player.getInstance(posP1, window); //Initialize the player.
    p1.setPosition(posP1); //set player position manually because it is singleton.
    e1.setPosition(posE1);

    Bullet b = p1.shoot();
    b.move();
    b.move();
    b.move();

    System.out.println("collisionBulletE1_2\nbullet pos: " + b.getPosition());
    System.out.println("enemy pos: " + e1.getPosition());

    System.out.println("bullet right bBox: " + b.getBoundingBox().getRight());
    System.out.println("enemy left bBox: " + e1.getBoundingBox().getLeft());

    assertEquals(b.getParentType(),"player");
    assertTrue(e1.getBoundingBox().collides(b.getBoundingBox()));
  }

  /**
   * Tests for enemy1 and player bullet collision.
   */
  @Test
  void collisionBulletE1_3() {
    PVector posP1 = new PVector(10f, 400f);
    PVector posE1 = new PVector(75f, 400f);

    Player p1 = Player.getInstance(posP1, window); //Initialize the player.
    p1.setPosition(posP1); //set player position manually because it is singleton.
    e1.setPosition(posE1);

    Bullet b = p1.shoot();
    b.move();
    b.move();
    b.move();
    b.move();
    b.move();
    b.move();

    System.out.println("collisionBulletE1_3\nbullet pos: " + b.getPosition());
    System.out.println("enemy pos: " + e1.getPosition());

    System.out.println("bullet right bBox: " + b.getBoundingBox().getRight());
    System.out.println("enemy left bBox: " + e1.getBoundingBox().getLeft());

    assertEquals(b.getParentType(),"player");
    assertTrue(e1.getBoundingBox().collides(b.getBoundingBox()));
  }

  /**
   * Tests for enemy2 and player bullet collision.
   */
  @Test
  void collisionBulletE2_1() {
    PVector posP1 = new PVector(10f, 30f);
    PVector posE1 = new PVector(70f, 30f);

    Player p1 = Player.getInstance(posP1, window); //Initialize the player.
    p1.setPosition(posP1); //set player position manually because it is singleton.
    e2.setPosition(posE1);

    Bullet b = p1.shoot();
    b.move();

    System.out.println("collisionBulletE2_1\nbullet pos: " + b.getPosition());
    System.out.println("enemy pos: " + e2.getPosition());

    System.out.println("bullet right bBox: " + b.getBoundingBox().getRight());
    System.out.println("enemy left bBox: " + e2.getBoundingBox().getLeft());

    assertEquals(b.getParentType(),"player");
    assertTrue(e2.getBoundingBox().collides(b.getBoundingBox()));
  }

  /**
   * Tests for enemy2 and player bullet collision.
   */
  @Test
  void collisionBulletE2_2() {
    PVector posP1 = new PVector(10f, 300f);
    PVector posE1 = new PVector(76f, 300f);

    Player p1 = Player.getInstance(posP1, window); //Initialize the player.
    p1.setPosition(posP1); //set player position manually because it is singleton.
    e2.setPosition(posE1);

    Bullet b = p1.shoot();
    b.move();
    b.move();
    b.move();

    System.out.println("collisionBulletE2_2\nbullet pos: " + b.getPosition());
    System.out.println("enemy pos: " + e2.getPosition());

    System.out.println("bullet right bBox: " + b.getBoundingBox().getRight());
    System.out.println("enemy left bBox: " + e2.getBoundingBox().getLeft());

    assertEquals(b.getParentType(),"player");
    assertTrue(e2.getBoundingBox().collides(b.getBoundingBox()));
  }

  /**
   * Tests for enemy2 and player bullet collision.
   */
  @Test
  void collisionBulletE2_3() {
    PVector posP1 = new PVector(10f, 400f);
    PVector posE1 = new PVector(85f, 400f);

    Player p1 = Player.getInstance(posP1, window); //Initialize the player.
    p1.setPosition(posP1); //set player position manually because it is singleton.
    e2.setPosition(posE1);

    Bullet b = p1.shoot();
    b.move();
    b.move();
    b.move();
    b.move();
    b.move();
    b.move();

    System.out.println("collisionBulletE2_3\nbullet pos: " + b.getPosition());
    System.out.println("enemy pos: " + e2.getPosition());

    System.out.println("bullet right bBox: " + b.getBoundingBox().getRight());
    System.out.println("enemy left bBox: " + e2.getBoundingBox().getLeft());

    assertEquals(b.getParentType(),"player");
    assertTrue(e2.getBoundingBox().collides(b.getBoundingBox()));
  }

  /**
   * Tests for enemy3 and player bullet collision.
   */
  @Test
  void collisionBulletE3_1() {
    PVector posP1 = new PVector(10f, 30f);
    PVector posE1 = new PVector(80f, 30f);

    Player p1 = Player.getInstance(posP1, window); //Initialize the player.
    p1.setPosition(posP1); //set player position manually because it is singleton.
    e3.setPosition(posE1);

    Bullet b = p1.shoot();
    b.move();

    System.out.println("collisionBulletE3_1\nbullet pos: " + b.getPosition());
    System.out.println("enemy pos: " + e3.getPosition());

    System.out.println("bullet right bBox: " + b.getBoundingBox().getRight());
    System.out.println("enemy left bBox: " + e3.getBoundingBox().getLeft());

    assertEquals(b.getParentType(),"player");
    assertTrue(e3.getBoundingBox().collides(b.getBoundingBox()));
  }

  /**
   * Tests for enemy3 and player bullet collision.
   */
  @Test
  void collisionBulletE3_2() {
    PVector posP1 = new PVector(10f, 300f);
    PVector posE1 = new PVector(86f, 300f);

    Player p1 = Player.getInstance(posP1, window); //Initialize the player.
    p1.setPosition(posP1); //set player position manually because it is singleton.
    e3.setPosition(posE1);

    Bullet b = p1.shoot();
    b.move();
    b.move();
    b.move();

    System.out.println("collisionBulletE3_2\nbullet pos: " + b.getPosition());
    System.out.println("enemy pos: " + e3.getPosition());

    System.out.println("bullet right bBox: " + b.getBoundingBox().getRight());
    System.out.println("enemy left bBox: " + e3.getBoundingBox().getLeft());

    assertEquals(b.getParentType(),"player");
    assertTrue(e3.getBoundingBox().collides(b.getBoundingBox()));
  }

  /**
   * Tests for enemy3 and player bullet collision.
   */
  @Test
  void collisionBulletE3_3() {
    PVector posP1 = new PVector(10f, 400f);
    PVector posE1 = new PVector(95f, 400f);

    Player p1 = Player.getInstance(posP1, window); //Initialize the player.
    p1.setPosition(posP1); //set player position manually because it is singleton.
    e3.setPosition(posE1);

    Bullet b = p1.shoot();
    b.move();
    b.move();
    b.move();
    b.move();
    b.move();
    b.move();

    System.out.println("collisionBulletE3_3\nbullet pos: " + b.getPosition());
    System.out.println("enemy pos: " + e3.getPosition());

    System.out.println("bullet right bBox: " + b.getBoundingBox().getRight());
    System.out.println("enemy left bBox: " + e3.getBoundingBox().getLeft());

    assertEquals(b.getParentType(),"player");
    assertTrue(e3.getBoundingBox().collides(b.getBoundingBox()));
  }



}