import org.bcit.comp2522.project.BoundingBox;
import org.bcit.comp2522.project.Window;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PVector;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Tests to check the bounding box returns a correct value.
 *
 * @author Taswinder Dhaliwal & Alan Fung & Noufil Saqib
 * @version 1.0.0
 */
class BoundingBoxTest {

  BoundingBox a, b;
  float width, height;

  Window window;


  @BeforeEach
  void setUp() {
    window = new Window();
    width = 10f;
    height = 10f;
  }

  /**
   * Tests if the top of the bounding box is correct.
   * */
  @Test
  void getTop() {
    PVector posA = new PVector(100, 100);
    a = new BoundingBox(posA, width, height, window);
    assertEquals(posA.y - (height / 2f), a.getTop());
  }

  /**
   * Tests if the bottom of the bounding box is correct.
   * */
  @Test
  void getBottomTest() {
    PVector posA = new PVector(100, 100);
    a = new BoundingBox(posA, width, height, window);
    assertEquals(posA.y + (height / 2f), a.getBottom());
  }

  /**
   * Tests if the left side of the bounding box is correct.
   * */
  @Test
  void getLeftTest() {
    PVector posA = new PVector(100, 100);
    a = new BoundingBox(posA, width, height, window);
    assertEquals(posA.x - (width / 2f), a.getLeft());
  }

  /**
   * Tests if the right side of the bounding box is correct.
   * */
  @Test
  void getRightTest() {
    PVector posA = new PVector(100, 100);
    a = new BoundingBox(posA, width, height, window);
    assertEquals(posA.x + (width / 2f), a.getRight());
  }

  /**
   * Tests objects for collision.
   * */
  @Test
  void collideTest() {
    PVector posA = new PVector(100, 100);
    a = new BoundingBox(posA, width, height, window);

    for (int i = 0; i < width; i++) {
      PVector posB = new PVector(100 + ((float) i), 100 + ((float) i));
      b = new BoundingBox(posB, width, height, window);
      assertTrue(a.collides(b));
      assertTrue(b.collides(a));
    }
  }
}