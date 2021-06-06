import static org.junit.Assert.assertEquals;

import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.PileType;
import cs3500.freecell.model.hw02.ICard;
import cs3500.freecell.model.hw04.MultiMoveFreecellModel;
import org.junit.Test;

/**
 * This respresents all the tests responsible for the testing the multimove functionality of the
 * MultiMoveFreecellModel class.
 */
public class TestMultiMoveFreecellModel {

  @Test(expected = IllegalArgumentException.class)
  public void testMultiMoveInvalidIndex() {
    FreecellModel<ICard> model = new MultiMoveFreecellModel();
    model.startGame(model.getDeck(), 4, 2, false);
    model.move(PileType.CASCADE, 2, 20, PileType.FOUNDATION, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMultiMoveInvalidConnectionBuild() {
    FreecellModel<ICard> model = new MultiMoveFreecellModel();
    model.startGame(model.getDeck(), 4, 2, false);

    for (int ii = 12; ii >= 1; ii -= 1) {
      int cardsInCPile = model.getNumCardsInCascadePile(0);
      int cardIndex = cardsInCPile - 1;

      model.move(PileType.CASCADE,
          0,
          cardIndex,
          PileType.FOUNDATION,
          0);
    }

    model.move(PileType.CASCADE, 1, 12, PileType.CASCADE, 0);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testMultiMoveInvalidSourcePileBuild() {
    FreecellModel<ICard> model = new MultiMoveFreecellModel();
    model.startGame(model.getDeck(), 4, 2, false);

    for (int ii = 12; ii >= 1; ii -= 1) {
      int cardsInCPile = model.getNumCardsInCascadePile(0);
      int cardIndex = cardsInCPile - 1;

      model.move(PileType.CASCADE,
          0,
          cardIndex,
          PileType.FOUNDATION,
          0);
    }

    model.move(PileType.CASCADE, 1, 10, PileType.CASCADE, 0);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testNotEnoughOpenCascadeOrOpenPiles() {
    FreecellModel<ICard> model = new MultiMoveFreecellModel();
    model.startGame(model.getDeck(), 4, 2, false);

    for (int ii = 12; ii >= 1; ii -= 1) {
      int cardsInCPile = model.getNumCardsInCascadePile(0);
      int cardIndex = cardsInCPile - 1;

      model.move(PileType.CASCADE,
          0,
          cardIndex,
          PileType.FOUNDATION,
          0);
    }

    model.move(PileType.CASCADE, 1, 6, PileType.CASCADE, 0);
  }

  @Test
  public void successfulMultiMove() {
    FreecellModel<ICard> model = new MultiMoveFreecellModel();
    model.startGame(model.getDeck(), 4, 2, false);

    for (int ii = 0; ii < 50; ii++) {
      int cardsInCPile = model.getNumCardsInCascadePile(ii % 4);
      int cardIndex = cardsInCPile - 1;

      model.move(PileType.CASCADE,
          ii % 4,
          cardIndex,
          PileType.FOUNDATION,
          ii % 4);
    }

    model.move(PileType.FOUNDATION, 3, 11, PileType.OPEN, 0);
    model.move(PileType.FOUNDATION, 2, 11, PileType.CASCADE, 0);
    model.move(PileType.FOUNDATION, 3, 10, PileType.CASCADE, 0);
    assertEquals(2, model.getNumCardsInCascadePile(0));
    assertEquals(1, model.getNumCardsInCascadePile(3));
    model.move(PileType.CASCADE, 0, 0, PileType.CASCADE, 3);
  }

}
