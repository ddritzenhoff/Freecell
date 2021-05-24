import cs3500.freecell.model.PileType;
import cs3500.freecell.model.hw02.SimpleFreecellModel;
import cs3500.freecell.view.FreecellTextView;
import cs3500.freecell.view.FreecellView;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * This respresents all the tests responsible for the testing the methods of the FreecellTextView
 * class.
 */
public class TestFreecellTextView {

  @Test
  public void testToString() {

    SimpleFreecellModel model = new SimpleFreecellModel();
    model.startGame(model.getDeck(), 4, 1, false);

    for (int ii = 0; ii < 13; ii++) {
      int cardsInCPile = model.getNumCardsInCascadePile(ii % 4);
      int cardIndex = cardsInCPile - 1;

      model.move(PileType.CASCADE,
          ii % 4,
          cardIndex,
          PileType.FOUNDATION,
          ii % 4);
    }

    FreecellView tview = new FreecellTextView(model);

    String finalStr = "F1: A♥, 2♥, 3♥, 4♥\n"
        + "F2: A♠, 2♠, 3♠\n"
        + "F3: A♣, 2♣, 3♣\n"
        + "F4: A♦, 2♦, 3♦\n"
        + "O1:\n"
        + "C1: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥\n"
        + "C2: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠\n"
        + "C3: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣\n"
        + "C4: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦";

    assertEquals(finalStr, tview.toString());
  }
}
