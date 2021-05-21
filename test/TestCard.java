import cs3500.freecell.model.hw02.Card;
import cs3500.freecell.model.hw02.Face;
import cs3500.freecell.model.hw02.ICard;
import cs3500.freecell.model.hw02.Suite;
import java.util.Objects;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * This respresents all the tests responsible for the testing the methods of the Card class.
 */
public class TestCard {

  @Test(expected = NullPointerException.class)
  public void testCardConstructor() {
    ICard card = new Card(null, Face.ACE);
  }

  @Test
  public void testIsValid() {
    ICard card = new Card(Suite.HEART, Face.ACE);
    assertTrue(card.isValid());
  }

  @Test
  public void testGetFace() {
    ICard card = new Card(Suite.HEART, Face.ACE);
    assertEquals(Face.ACE, card.getFace());
  }

  @Test
  public void testGetFaceValue() {
    ICard card = new Card(Suite.HEART, Face.ACE);
    assertEquals(1, card.getFaceValue());
  }

  @Test
  public void testGetSuite() {
    ICard card = new Card(Suite.HEART, Face.ACE);
    assertEquals(Suite.HEART, card.getSuite());
  }

  @Test
  public void testSameColor() {
    ICard card1 = new Card(Suite.HEART, Face.ACE);
    ICard card2 = new Card(Suite.DIAMOND, Face.TWO);
    assertTrue(card1.sameColor(card2));
    ICard card3 = new Card(Suite.SPADE, Face.THREE);
    ICard card4 = new Card(Suite.CLUB, Face.JACK);
    assertTrue(card3.sameColor(card4));
  }

  @Test
  public void testToString() {
    ICard card1 = new Card(Suite.HEART, Face.ACE);
    assertEquals("A♥", card1.toString());
  }

  @Test
  public void testHashCode() {
    ICard card1 = new Card(Suite.HEART, Face.ACE);
    assertEquals(Objects.hash(Suite.HEART, Face.ACE), card1.hashCode());
  }

  @Test
  public void testEquals() {
    ICard card1 = new Card(Suite.HEART, Face.ACE);
    ICard card2 = new Card(Suite.HEART, Face.ACE);
    assertEquals(card1, card2);
    ICard card3 = new Card(Suite.HEART, Face.KING);
    assertNotEquals(card1, card3);
  }

}
