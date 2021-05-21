import cs3500.freecell.model.hw02.Card;
import cs3500.freecell.model.hw02.Face;
import cs3500.freecell.model.hw02.ICard;
import cs3500.freecell.model.hw02.Suite;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This respresents all the tests responsible for the testing the methods of the Face and Suite
 * enums.
 */
public class TestEnums {

  @Test
  public void testGetSuite() {
    ICard card = new Card(Suite.HEART, Face.ACE);
    assertEquals(Suite.HEART, card.getSuite());
  }

  @Test
  public void testGetFace() {
    ICard card = new Card(Suite.HEART, Face.ACE);
    assertEquals(Face.ACE, card.getFace());
    assertEquals(1, card.getFaceValue());
  }

}
