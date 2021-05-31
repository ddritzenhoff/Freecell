import cs3500.freecell.controller.FreecellController;
import cs3500.freecell.controller.SimpleFreecellController;
import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.hw02.ICard;
import cs3500.freecell.model.hw02.SimpleFreecellModel;
import java.io.Reader;
import java.io.StringReader;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This respresents all the tests responsible for the testing the methods of the
 * SimpleFreecellController.
 */
public class TestSimpleFreecellController {

  @Test(expected = IllegalArgumentException.class)
  public void testSimpleFreeCellControllerConstructorNullModel() {
    FreecellModel<ICard> model = null;
    StringBuffer output = new StringBuffer();
    Reader input = new StringReader("8 4 q");
    FreecellController<ICard> controller = new SimpleFreecellController(model, input, output);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSimpleFreeCellControllerConstructorNullAppendable() {
    FreecellModel<ICard> model = new SimpleFreecellModel();
    StringBuffer output = null;
    Reader input = new StringReader("8 4 q");
    FreecellController<ICard> controller = new SimpleFreecellController(model, input, output);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSimpleFreeCellControllerConstructorNullReadable() {
    FreecellModel<ICard> model = new SimpleFreecellModel();
    StringBuffer output = new StringBuffer();
    Reader input = null;
    FreecellController<ICard> controller = new SimpleFreecellController(model, input, output);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSimpleFreeCellControllerPlaygameNullDeck() {
    FreecellModel<ICard> model = new SimpleFreecellModel();
    StringBuffer output = new StringBuffer();
    Reader input = new StringReader("8 4 q");
    FreecellController<ICard> controller = new SimpleFreecellController(model, input, output);
    controller.playGame(null, 4, 2, false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSimpleFreeCellControllerPlaygameFailedStart() {
    FreecellModel<ICard> model = new SimpleFreecellModel();
    StringBuffer output = new StringBuffer();
    Reader input = new StringReader("8 4 q");
    FreecellController<ICard> controller = new SimpleFreecellController(model, input, output);
    controller.playGame(null, 4, 2, false);
  }

  @Test
  public void TestSimpleFreeCellControllerPlaygame() {
    FreecellModel<ICard> model = new SimpleFreecellModel();
    StringBuffer output = new StringBuffer();
    Reader input = new StringReader("C1 arg 7 n O1 1 C2 7 F1 C3 q");
    FreecellController<ICard> controller = new SimpleFreecellController(model, input, output);

    controller.playGame(model.getDeck(), 8, 4, false);

    String expectedOutput = "F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "C1: K♥, J♥, 9♥, 7♥, 5♥, 3♥, A♥\n"
        + "C2: K♠, J♠, 9♠, 7♠, 5♠, 3♠, A♠\n"
        + "C3: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♣\n"
        + "C4: K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♦\n"
        + "C5: Q♥, 10♥, 8♥, 6♥, 4♥, 2♥\n"
        + "C6: Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n"
        + "C7: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣\n"
        + "C8: Q♦, 10♦, 8♦, 6♦, 4♦, 2♦\n"
        + "Invalid move. Try again. Card index is invalid.\n"
        + "Invalid move. Try again. Destination pile is invalid.\n"
        + "F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1: A♥\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "C1: K♥, J♥, 9♥, 7♥, 5♥, 3♥\n"
        + "C2: K♠, J♠, 9♠, 7♠, 5♠, 3♠, A♠\n"
        + "C3: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♣\n"
        + "C4: K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♦\n"
        + "C5: Q♥, 10♥, 8♥, 6♥, 4♥, 2♥\n"
        + "C6: Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n"
        + "C7: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣\n"
        + "C8: Q♦, 10♦, 8♦, 6♦, 4♦, 2♦\n"
        + "F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1: A♥\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "C1: K♥, J♥, 9♥, 7♥, 5♥, 3♥\n"
        + "C2: K♠, J♠, 9♠, 7♠, 5♠, 3♠, A♠\n"
        + "C3: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♣\n"
        + "C4: K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♦\n"
        + "C5: Q♥, 10♥, 8♥, 6♥, 4♥, 2♥\n"
        + "C6: Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n"
        + "C7: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣\n"
        + "C8: Q♦, 10♦, 8♦, 6♦, 4♦, 2♦\n"
        + "Invalid move. Try again. Source pile is invalid.\n"
        + "F1: A♠\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1: A♥\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "C1: K♥, J♥, 9♥, 7♥, 5♥, 3♥\n"
        + "C2: K♠, J♠, 9♠, 7♠, 5♠, 3♠\n"
        + "C3: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♣\n"
        + "C4: K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♦\n"
        + "C5: Q♥, 10♥, 8♥, 6♥, 4♥, 2♥\n"
        + "C6: Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n"
        + "C7: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣\n"
        + "C8: Q♦, 10♦, 8♦, 6♦, 4♦, 2♦\n"
        + "F1: A♠\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1: A♥\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "C1: K♥, J♥, 9♥, 7♥, 5♥, 3♥\n"
        + "C2: K♠, J♠, 9♠, 7♠, 5♠, 3♠\n"
        + "C3: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♣\n"
        + "C4: K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♦\n"
        + "C5: Q♥, 10♥, 8♥, 6♥, 4♥, 2♥\n"
        + "C6: Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n"
        + "C7: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣\n"
        + "C8: Q♦, 10♦, 8♦, 6♦, 4♦, 2♦\n"
        + "Game quit prematurely.\n";

    assertEquals(expectedOutput, output.toString());
  }

  @Test(expected = IllegalStateException.class)
  public void TestSimpleFreeCellControllerNoMoreInputs() {
    FreecellModel<ICard> model = new SimpleFreecellModel();
    StringBuffer output = new StringBuffer();
    Reader input = new StringReader("C1 arg 7");
    FreecellController<ICard> controller = new SimpleFreecellController(model, input, output);

    controller.playGame(model.getDeck(), 8, 4, false);
  }


}
