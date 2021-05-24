import cs3500.freecell.model.hw02.Card;
import cs3500.freecell.model.hw02.CascadingPile;
import cs3500.freecell.model.hw02.Face;
import cs3500.freecell.model.hw02.FoundationPile;
import cs3500.freecell.model.hw02.ICard;
import cs3500.freecell.model.hw02.IPile;
import cs3500.freecell.model.hw02.OpenPile;
import cs3500.freecell.model.hw02.Suite;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * This respresents all the tests responsible for the testing the methods of the FoundationPile
 * class.
 */
public class TestFoundationPile {

  @Test
  public void testPushCard() {
    ICard card1 = new Card(Suite.HEART, Face.ACE);
    IPile<ICard> pile = new FoundationPile();
    assertEquals(0, pile.getNumCards());
    pile.pushCard(card1);
    assertEquals(1, pile.getNumCards());
  }

  @Test(expected = IllegalStateException.class)
  public void testPopCardNoCards() {
    IPile<ICard> pile = new CascadingPile();
    pile.popCard();
  }

  @Test
  public void testPopCard() {
    ICard card1 = new Card(Suite.HEART, Face.ACE);
    IPile<ICard> pile = new FoundationPile();
    assertEquals(0, pile.getNumCards());
    pile.pushCard(card1);
    assertEquals(1, pile.getNumCards());
    pile.popCard();
    assertEquals(0, pile.getNumCards());
  }

  @Test(expected = IllegalStateException.class)
  public void testMoveNotPossible() {
    ICard card1 = new Card(Suite.HEART, Face.ACE);
    ICard card2 = new Card(Suite.CLUB, Face.TWO);
    IPile<ICard> fpile = new FoundationPile();
    IPile<ICard> opile = new OpenPile();
    fpile.pushCard(card1);
    opile.pushCard(card2);
    fpile.move(opile);
  }

  @Test
  public void testMove() {
    ICard card1 = new Card(Suite.HEART, Face.JACK);
    IPile<ICard> fpile = new FoundationPile();
    IPile<ICard> cpile = new CascadingPile();
    fpile.pushCard(card1);
    assertEquals(1, fpile.getNumCards());
    fpile.move(cpile);
    assertEquals(0, fpile.getNumCards());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetCardOutsideIndex() {
    IPile<ICard> pile = new FoundationPile();
    pile.getCard(4);
  }

  @Test
  public void getCard() {
    ICard card1 = new Card(Suite.HEART, Face.ACE);
    IPile<ICard> pile = new FoundationPile();
    pile.pushCard(card1);
    assertEquals(new Card(Suite.HEART, Face.ACE), pile.getCard(0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetTopCardNoCard() {
    IPile<ICard> pile = new FoundationPile();
    pile.getTopCard();
  }

  @Test
  public void testGetTopCardSuccess() {
    ICard card1 = new Card(Suite.HEART, Face.ACE);
    IPile<ICard> pile = new FoundationPile();
    pile.pushCard(card1);
    assertEquals(new Card(Suite.HEART, Face.ACE), pile.getTopCard());
  }

  @Test
  public void testCanBeAdded() {
    ICard card1 = new Card(Suite.HEART, Face.ACE);
    IPile<ICard> pile = new FoundationPile();
    assertTrue(pile.canBeAdded(card1));
    pile.pushCard(card1);
    ICard card2 = new Card(Suite.HEART, Face.TWO);
    assertTrue(pile.canBeAdded(card2));
  }

  @Test
  public void testCanBeRemoved() {
    ICard card1 = new Card(Suite.HEART, Face.ACE);
    IPile<ICard> pile = new FoundationPile();
    assertFalse(pile.canBeRemoved(0));
    pile.pushCard(card1);
    assertTrue(pile.canBeRemoved(0));
  }

  @Test
  public void testGetNumCards() {
    ICard card1 = new Card(Suite.HEART, Face.ACE);
    ICard card2 = new Card(Suite.HEART, Face.TWO);
    IPile<ICard> pile = new FoundationPile();
    pile.pushCard(card1);
    assertEquals(1, pile.getNumCards());
    pile.pushCard(card2);
    assertEquals(2, pile.getNumCards());
  }

  @Test
  public void testIsFull() {
    ICard card1 = new Card(Suite.HEART, Face.ACE);
    IPile<ICard> pile = new FoundationPile();
    pile.pushCard(card1);
    assertFalse(pile.isFull());
  }

  @Test
  public void testIsPileValid() {
    ICard card1 = new Card(Suite.HEART, Face.ACE);
    ICard card2 = new Card(Suite.HEART, Face.ACE);
    IPile<ICard> pile = new FoundationPile();
    pile.pushCard(card1);
    assertTrue(pile.isPileValid());
    pile.pushCard(card2);
    assertFalse(pile.isPileValid());
  }
}
