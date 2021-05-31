import cs3500.freecell.controller.FreecellController;
import cs3500.freecell.controller.SimpleFreecellController;
import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.PileType;
import cs3500.freecell.model.hw02.ICard;
import cs3500.freecell.model.hw02.SimpleFreecellModel;
import cs3500.freecell.view.FreecellTextView;
import cs3500.freecell.view.FreecellView;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
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

  @Test
  public void testRenderMessage() throws IOException {
    FreecellModel<ICard> model = new SimpleFreecellModel();
    StringBuffer output = new StringBuffer();
    Reader input = new StringReader("C1 arg 7 n O1 1 C2 7 F1 C3 q");
    FreecellController<ICard> controller = new SimpleFreecellController(model, input, output);

    FreecellView view = new FreecellTextView(model, output);

    view.renderMessage("yuhh this ake askejs ke fs");

    String expectedOutput = "yuhh this ake askejs ke fs";

    assertEquals(expectedOutput, output.toString());
  }

  @Test
  public void testRenderBoard() throws IOException {
    FreecellModel<ICard> model = new SimpleFreecellModel();
    StringBuffer output = new StringBuffer();
    Reader input = new StringReader("q");
    FreecellController<ICard> controller = new SimpleFreecellController(model, input, output);
    controller.playGame(model.getDeck(), 4, 1, false);
    FreecellView view = new FreecellTextView(model, output);

    view.renderBoard();

    String expectedOutput = "F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
        + "C2: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n"
        + "C3: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "C4: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "Game quit prematurely.\n"
        + "F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
        + "C2: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n"
        + "C3: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "C4: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦";

    assertEquals(expectedOutput, output.toString());
  }
}
