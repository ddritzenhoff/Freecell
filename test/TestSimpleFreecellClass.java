import cs3500.freecell.model.PileType;
import cs3500.freecell.model.hw02.Face;
import cs3500.freecell.model.hw02.ICard;
import cs3500.freecell.model.hw02.SimpleFreecellModel;
import cs3500.freecell.model.hw02.Suite;
import java.util.List;
import org.junit.Test;

import static org.junit.Assert.*;

// TODO: QUESTION: Do I have to write comments for the methods?

/**
 * This respresents all the tests responsible for the testing the methods of
 * the SimpleFreecellClass
 */
public class TestSimpleFreecellClass {

  @Test
  public void testOrderOfGetDeck() {
    SimpleFreecellModel model = new SimpleFreecellModel();
    List<ICard> deck = model.getDeck();

    // I am expecting all of the face cards to be created in descending order.
    // i.e. King (K) of Hearts, K of Spades, K of Clubs, K of Diamonds, Q of Hearts, etc.
    assertSame(deck.get(0).getSuite(), Suite.HEART);
    assertSame(deck.get(0).getFace(), Face.KING);
    assertSame(deck.get(1).getSuite(), Suite.SPADE);
    assertSame(deck.get(1).getFace(), Face.KING);
    assertSame(deck.get(2).getSuite(), Suite.CLUB);
    assertSame(deck.get(2).getFace(), Face.KING);
    assertSame(deck.get(3).getSuite(), Suite.DIAMOND);
    assertSame(deck.get(3).getFace(), Face.KING);
    assertSame(deck.get(47).getSuite(), Suite.DIAMOND);
    assertSame(deck.get(47).getFace(), Face.TWO);

    // this shows the order of cards is all Kings in the order of Heart, Spade, Club, Diamond,
    // Queens in the same order, etc.
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidCascadePileSize() {
    SimpleFreecellModel model = new SimpleFreecellModel();
    model.startGame(model.getDeck(), 3, 4, false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidOpenPileSize() {
    SimpleFreecellModel model = new SimpleFreecellModel();
    model.startGame(model.getDeck(), 4, 0, false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidDeck() {
    SimpleFreecellModel model = new SimpleFreecellModel();
    List<ICard> deck = model.getDeck();
    deck.remove(deck.size() - 1);
    model.startGame(deck, 4, 4, false);
  }

  @Test
  public void testMultipleStartGameCalls() {
    SimpleFreecellModel model = new SimpleFreecellModel();
    List<ICard> deck = model.getDeck();
    model.startGame(deck, 5, 2, true);
    assertEquals(model.getNumCascadePiles(), 5);
    model.startGame(deck, 9, 2, true);
    assertEquals(model.getNumCascadePiles(), 9);
  }

  @Test
  public void testIfCardsInRightCascadePileAfterStartGame() {
    SimpleFreecellModel model = new SimpleFreecellModel();
    model.startGame(model.getDeck(), 4, 2, false);
    // with 52 cards and 5 piles, you are expecting the 0th, 5th, 10th, 15th, etc. cards to be
    // in pile 1.

    // the testOrderOfGetDeck lets me make assumptions about the order of cards.
    assertSame(model.getCascadeCardAt(0, 0).getSuite(), Suite.HEART);
    assertSame(model.getCascadeCardAt(0, 2).getFace(), Face.JACK);
    assertSame(model.getCascadeCardAt(1, 4).getSuite(), Suite.SPADE);
    assertSame(model.getCascadeCardAt(1, 4).getFace(), Face.NINE);
    assertSame(model.getCascadeCardAt(2, 6).getSuite(), Suite.CLUB);
    assertSame(model.getCascadeCardAt(3, 9).getSuite(), Suite.DIAMOND);
    assertSame(model.getCascadeCardAt(0, 12).getFace(), Face.ACE);
  }

  @Test(expected = IllegalStateException.class)
  public void testMoveWithoutStartingGame() {
    SimpleFreecellModel model = new SimpleFreecellModel();
    model.move(PileType.CASCADE, 0, 12, PileType.OPEN, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveIllegalIndex() {
    SimpleFreecellModel model = new SimpleFreecellModel();
    model.startGame(model.getDeck(), 4, 2, false);
    model.move(PileType.CASCADE, 0, 10, PileType.OPEN, 0);
  }

  @Test
  public void testSuccessfulMove() {
    SimpleFreecellModel model = new SimpleFreecellModel();
    model.startGame(model.getDeck(), 4, 2, false);

    assertSame(Face.ACE, model.getCascadeCardAt(0, 12).getFace());
    assertSame(1, model.getCascadeCardAt(0, 12).getFaceValue());
    model.move(PileType.CASCADE, 0, 12, PileType.OPEN, 0);
    assertSame(Face.ACE, model.getOpenCardAt(0).getFace());
    assertSame(1, model.getOpenCardAt(0).getFaceValue());
    model.move(PileType.CASCADE, 0, 11, PileType.OPEN, 1);
    assertEquals(1, model.getNumCardsInOpenPile(1));
  }

  @Test
  public void testIfGameOver() {
    SimpleFreecellModel model = new SimpleFreecellModel();
    model.startGame(model.getDeck(), 4, 1, false);
    assertFalse(model.isGameOver());

    assertSame(Face.ACE, model.getCascadeCardAt(0, 12).getFace());
    assertSame(1, model.getCascadeCardAt(0, 12).getFaceValue());

    for (int ii = 0; ii < 52; ii++) {
      int cardsInCPile = model.getNumCardsInCascadePile(ii % 4);
      int cardIndex = cardsInCPile - 1;

      model.move(PileType.CASCADE,
          ii % 4,
          cardIndex,
          PileType.FOUNDATION,
          ii % 4);
    }

    assertTrue(model.isGameOver());
  }

  @Test
  public void testGetNumberOfCardsInFoundationPile() {
    SimpleFreecellModel model = new SimpleFreecellModel();
    model.startGame(model.getDeck(), 4, 1, false);
    assertEquals(0, model.getNumCardsInFoundationPile(0));
    model.move(PileType.CASCADE, 0, 12, PileType.FOUNDATION, 0);
    assertEquals(1, model.getNumCardsInFoundationPile(0));
    model.move(PileType.CASCADE, 0, 11, PileType.FOUNDATION, 0);
    assertEquals(2, model.getNumCardsInFoundationPile(0));
  }

  @Test(expected = IllegalStateException.class)
  public void testGetNumberOfCardsInFoundationPileWithoutGameStarting() {
    SimpleFreecellModel model = new SimpleFreecellModel();
    model.getNumCardsInFoundationPile(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetNumberOfCardsInFoundationPileWithWrongIndex() {
    SimpleFreecellModel model = new SimpleFreecellModel();
    model.startGame(model.getDeck(), 4, 2, false);
    model.getNumCardsInFoundationPile(5);
  }

  @Test
  public void testGetNumberOfCardsInFoundationPileSuccessful() {
    SimpleFreecellModel model = new SimpleFreecellModel();
    model.startGame(model.getDeck(), 4, 1, false);

    for (int ii = 0; ii < 52; ii++) {
      int cardsInCPile = model.getNumCardsInCascadePile(ii % 4);
      int cardIndex = cardsInCPile - 1;

      model.move(PileType.CASCADE,
          ii % 4,
          cardIndex,
          PileType.FOUNDATION,
          ii % 4);
    }

    assertEquals(13, model.getNumCardsInFoundationPile(0));
  }

  @Test
  public void testGetNumCascadePiles() {
    SimpleFreecellModel model = new SimpleFreecellModel();
    assertEquals(-1, model.getNumCascadePiles());
    model.startGame(model.getDeck(), 4, 1, false);
    assertEquals(4, model.getNumCascadePiles());
  }

  @Test(expected = IllegalStateException.class)
  public void testGetNumCardsInCascadePileGameNotStarted() {
    SimpleFreecellModel model = new SimpleFreecellModel();
    model.getNumCardsInCascadePile(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetNumCardsInCascadePileNoSuchIndex() {
    SimpleFreecellModel model = new SimpleFreecellModel();
    model.startGame(model.getDeck(), 4, 1, false);
    model.getNumCardsInCascadePile(-1);
  }

  @Test
  public void testGetNumCardsInCascadePile() {
    SimpleFreecellModel model = new SimpleFreecellModel();
    model.startGame(model.getDeck(), 4, 1, false);
    assertEquals(13, model.getNumCardsInCascadePile(0));
    model.move(PileType.CASCADE, 0, 12, PileType.OPEN, 0);
    assertEquals(12, model.getNumCardsInCascadePile(0));
  }

  @Test(expected = IllegalStateException.class)
  public void testGetNumCardsInOpenPileGameNotStarted() {
    SimpleFreecellModel model = new SimpleFreecellModel();
    model.getNumCardsInOpenPile(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetNumCardsInOpenPileNoSuchIndex() {
    SimpleFreecellModel model = new SimpleFreecellModel();
    model.startGame(model.getDeck(), 4, 1, false);
    model.getNumCardsInOpenPile(-1);
  }

  @Test
  public void testGetNumCardsInOpenPileSuccessful() {
    SimpleFreecellModel model = new SimpleFreecellModel();
    model.startGame(model.getDeck(), 4, 1, false);
    assertEquals(0, model.getNumCardsInOpenPile(0));
    model.move(PileType.CASCADE, 0, 12, PileType.OPEN, 0);
    assertEquals(1, model.getNumCardsInOpenPile(0));
  }

  @Test
  public void testGetNumOpenPiles() {
    SimpleFreecellModel model = new SimpleFreecellModel();
    assertEquals(-1, model.getNumOpenPiles());
    model.startGame(model.getDeck(), 4, 1, false);
    assertEquals(1, model.getNumOpenPiles());
  }

  @Test(expected = IllegalStateException.class)
  public void testGetFoundationCardAtGameNotStarted() {
    SimpleFreecellModel model = new SimpleFreecellModel();
    model.getFoundationCardAt(0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetFoundationCardAtNoSuchIndexPile() {
    SimpleFreecellModel model = new SimpleFreecellModel();
    model.startGame(model.getDeck(), 4, 1, false);
    model.getFoundationCardAt(-1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetFoundationCardAtNoSuchIndexCard() {
    SimpleFreecellModel model = new SimpleFreecellModel();
    model.startGame(model.getDeck(), 4, 1, false);
    model.getFoundationCardAt(0, 20);
  }

  @Test
  public void testGetFoundationCardAtSuccessful() {
    SimpleFreecellModel model = new SimpleFreecellModel();
    model.startGame(model.getDeck(), 4, 1, false);

    for (int ii = 0; ii < 4; ii++) {
      int cardsInCPile = model.getNumCardsInCascadePile(ii % 4);
      int cardIndex = cardsInCPile - 1;

      model.move(PileType.CASCADE,
          ii % 4,
          cardIndex,
          PileType.FOUNDATION,
          ii % 4);
    }

    assertEquals(Face.ACE, model.getFoundationCardAt(1, 0).getFace());
    assertEquals(Suite.SPADE, model.getFoundationCardAt(1, 0).getSuite());

  }

  @Test(expected = IllegalStateException.class)
  public void testGetCascadeCardGameNotStarted() {
    SimpleFreecellModel model = new SimpleFreecellModel();
    model.getCascadeCardAt(0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetCascadeCardNoSuchIndexPile() {
    SimpleFreecellModel model = new SimpleFreecellModel();
    model.startGame(model.getDeck(), 4, 1, false);
    model.getCascadeCardAt(-1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetCascadeCardNoSuchIndexCard() {
    SimpleFreecellModel model = new SimpleFreecellModel();
    model.startGame(model.getDeck(), 4, 1, false);
    model.getCascadeCardAt(0, 20);
  }

  @Test
  public void testGetCascadeCardAtSuccessful() {
    SimpleFreecellModel model = new SimpleFreecellModel();
    model.startGame(model.getDeck(), 4, 1, false);

    assertEquals(Face.TWO, model.getCascadeCardAt(0, 11).getFace());
    assertEquals(Suite.HEART, model.getCascadeCardAt(0, 11).getSuite());
    model.move(PileType.CASCADE, 0, 12, PileType.FOUNDATION, 0);
    assertEquals(Face.TWO, model.getCascadeCardAt(0, 11).getFace());
    assertEquals(Suite.HEART, model.getCascadeCardAt(0, 11).getSuite());
  }

  @Test(expected = IllegalStateException.class)
  public void testGetOpenCardAtGameNotStarted() {
    SimpleFreecellModel model = new SimpleFreecellModel();
    model.getOpenCardAt(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetOpenCardAtNoSuchIndexPile() {
    SimpleFreecellModel model = new SimpleFreecellModel();
    model.startGame(model.getDeck(), 4, 1, false);
    model.getOpenCardAt(5);
  }

  @Test
  public void getOpenCardAtSuccessful() {
    SimpleFreecellModel model = new SimpleFreecellModel();
    model.startGame(model.getDeck(), 4, 1, false);

    assertEquals(Face.ACE, model.getCascadeCardAt(0, 12).getFace());
    assertEquals(Suite.HEART, model.getCascadeCardAt(0, 12).getSuite());
    model.move(PileType.CASCADE, 0, 12, PileType.OPEN, 0);
    assertEquals(Face.ACE, model.getOpenCardAt(0).getFace());
    assertEquals(Suite.HEART, model.getOpenCardAt(0).getSuite());
  }
}
