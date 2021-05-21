import cs3500.freecell.model.hw02.Card;
import cs3500.freecell.model.hw02.CascadingPile;
import cs3500.freecell.model.hw02.Face;
import cs3500.freecell.model.hw02.ICard;
import cs3500.freecell.model.hw02.IPile;
import cs3500.freecell.model.hw02.OpenPile;
import cs3500.freecell.model.hw02.Suite;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * This respresents all the tests responsible for the testing the methods of the OpenPile class.
 */
public class TestOpenPile {

  @Test
  public void testPushCard() {
    ICard card = new Card(Suite.SPADE, Face.ACE);
    IPile<ICard> openPile = new OpenPile();
    assertEquals(0, openPile.getNumCards());
    openPile.pushCard(card);
    assertEquals(1, openPile.getNumCards());
  }

  @Test(expected = IllegalStateException.class)
  public void testPopCardNoCard() {
    IPile<ICard> openPile = new OpenPile();
    openPile.popCard();
  }

  @Test
  public void testPopCardSuccessful() {
    IPile<ICard> openPile = new OpenPile();
    ICard card = new Card(Suite.CLUB, Face.EIGHT);
    openPile.pushCard(card);
    assertEquals(1, openPile.getNumCards());
    openPile.popCard();
    assertEquals(0, openPile.getNumCards());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveNotPossible() {
    ICard card1 = new Card(Suite.HEART, Face.JACK);
    ICard card2 = new Card(Suite.HEART, Face.THREE);
    IPile<ICard> cpile = new CascadingPile();
    IPile<ICard> opile = new OpenPile();
    cpile.pushCard(card1);
    opile.pushCard(card2);
    opile.move(cpile);
  }

  @Test
  public void testMove() {
    ICard card1 = new Card(Suite.HEART, Face.JACK);
    IPile<ICard> cpile = new CascadingPile();
    IPile<ICard> opile = new OpenPile();
    opile.pushCard(card1);
    opile.move(cpile);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetCardOutsideIndex() {
    IPile<ICard> pile = new OpenPile();
    pile.getCard(4);
  }

  @Test
  public void getCard() {
    ICard card1 = new Card(Suite.HEART, Face.JACK);
    IPile<ICard> pile = new OpenPile();
    pile.pushCard(card1);
    assertEquals(new Card(Suite.HEART, Face.JACK), pile.getCard(0));
  }


  @Test
  public void testGetTopCard() {
    ICard card1 = new Card(Suite.HEART, Face.JACK);
    IPile<ICard> pile = new OpenPile();
    pile.pushCard(card1);
    assertEquals(new Card(Suite.HEART, Face.JACK), pile.getTopCard());
  }

  @Test
  public void testCanBeAdded() {
    ICard card1 = new Card(Suite.HEART, Face.JACK);
    IPile<ICard> pile = new OpenPile();
    assertTrue(pile.canBeAdded(card1));
    pile.pushCard(card1);
  }

  @Test
  public void testCanBeRemoved() {
    ICard card1 = new Card(Suite.HEART, Face.JACK);
    IPile<ICard> pile = new OpenPile();
    assertFalse(pile.canBeRemoved(0));
    pile.pushCard(card1);
    assertTrue(pile.canBeRemoved(0));
  }

  @Test
  public void testGetNumCards() {
    ICard card1 = new Card(Suite.HEART, Face.JACK);
    IPile<ICard> pile = new OpenPile();
    pile.pushCard(card1);
    assertEquals(1, pile.getNumCards());
    pile.popCard();
    assertEquals(0, pile.getNumCards());
  }

  @Test
  public void testIsFull() {
    ICard card1 = new Card(Suite.HEART, Face.JACK);
    IPile<ICard> pile = new OpenPile();
    assertFalse(pile.isFull());
    pile.pushCard(card1);
    assertTrue(pile.isFull());
  }

  @Test
  public void testIsPileValid() {
    ICard card1 = new Card(Suite.HEART, Face.JACK);
    IPile<ICard> pile = new OpenPile();
    assertFalse(pile.isPileValid());
    pile.pushCard(card1);
    assertTrue(pile.isPileValid());
  }
}
