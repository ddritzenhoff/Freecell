import cs3500.freecell.controller.FreecellController;
import cs3500.freecell.controller.MockSimpleFreeCellModel;
import cs3500.freecell.controller.SimpleFreecellController;
import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.hw02.ICard;
import cs3500.freecell.model.hw02.SimpleFreecellModel;
import java.io.Reader;
import java.io.StringReader;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * This respresents all the tests responsible for the testing the parameters passed in from the
 * controller to a FreecellModel model.
 */
public class TestMockModel {

  @Test
  public void testStartGameAndGetNumCardsInFoundationPile() {
    Reader in = new StringReader("q");
    StringBuilder dontCareOutput = new StringBuilder();
    StringBuilder log = new StringBuilder();
    FreecellModel<ICard> model = new MockSimpleFreeCellModel(log);
    FreecellModel<ICard> actualModel = new SimpleFreecellModel();
    FreecellController<ICard> controller = new SimpleFreecellController(model, in, dontCareOutput);
    controller.playGame(actualModel.getDeck(), 4, 1, false);
    String expectedOutput = "startGame: numCascadePiles = 4, numOpenPiles = 1, shuffle = false\n"
        + "getNumCardsInFoundationPile: index = 0\n"
        + "getNumCardsInFoundationPile: index = 1\n"
        + "getNumCardsInFoundationPile: index = 2\n"
        + "getNumCardsInFoundationPile: index = 3\n";
    assertEquals(expectedOutput, log.toString());
  }

  @Test
  public void testMove() {
    Reader in = new StringReader("C1 13 O1 q");
    StringBuilder dontCareOutput = new StringBuilder();
    StringBuilder log = new StringBuilder();
    FreecellModel<ICard> model = new MockSimpleFreeCellModel(log);
    FreecellModel<ICard> actualModel = new SimpleFreecellModel();
    FreecellController<ICard> controller = new SimpleFreecellController(model, in, dontCareOutput);
    controller.playGame(actualModel.getDeck(), 4, 1, false);
    String expectedOutput = "startGame: numCascadePiles = 4, numOpenPiles = 1, shuffle = false\n"
        + "getNumCardsInFoundationPile: index = 0\n"
        + "getNumCardsInFoundationPile: index = 1\n"
        + "getNumCardsInFoundationPile: index = 2\n"
        + "getNumCardsInFoundationPile: index = 3\n"
        + "move: source = CASCADE, pileNumber = 0, cardIndex = 12, destination = OPEN, "
        + "destPileNumber = 0\n"
        + "getNumCardsInFoundationPile: index = 0\n"
        + "getNumCardsInFoundationPile: index = 1\n"
        + "getNumCardsInFoundationPile: index = 2\n"
        + "getNumCardsInFoundationPile: index = 3\n"
        + "getNumCardsInFoundationPile: index = 0\n"
        + "getNumCardsInFoundationPile: index = 1\n"
        + "getNumCardsInFoundationPile: index = 2\n"
        + "getNumCardsInFoundationPile: index = 3\n";
    assertEquals(expectedOutput, log.toString());
  }
}
