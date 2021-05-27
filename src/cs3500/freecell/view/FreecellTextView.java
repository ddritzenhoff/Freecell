package cs3500.freecell.view;

import cs3500.freecell.model.FreecellModel;
import java.io.IOException;
import java.util.Objects;

/**
 * Represents the ascii view of the card pile within the Freecell card game.
 */
public class FreecellTextView implements FreecellView {

  private final FreecellModel<?> model;
  private Appendable ap;

  /**
   * Constructs a FreecellModel object
   *
   * @param model the FreecellModel game implementation, and the current game state.
   */
  public FreecellTextView(FreecellModel<?> model) {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null.");
    }
    this.model = model;
    this.ap = System.out;
  }

  /**
   * @param model the FreecellModel game implementation, and the current game state.
   * @param ap    Appendable object to be used in the game rendering.
   */
  public FreecellTextView(FreecellModel<?> model, Appendable ap) {
    this(model);

    // if the passed in appendable is not valid, you have to print everything to console instead.
    if (ap == null) {
      this.ap = System.out;
    } else {
      this.ap = ap;
    }
  }

  @Override
  public String toString() {

    boolean gameHasntStarted = model.getNumOpenPiles() == -1;

    if (gameHasntStarted) {
      return "";
    }

    // at this point, you know that the game begun.

    String result = "";

    // displaying the foundation pile.
    for (int pileIndex = 0; pileIndex < 4; pileIndex++) {
      result = result + "F" + (pileIndex + 1) + ":";

      for (int cardI = 0; cardI < model.getNumCardsInFoundationPile(pileIndex); cardI++) {
        result = result + " " + model.getFoundationCardAt(pileIndex, cardI).toString();
        if (cardI + 1 != model.getNumCardsInFoundationPile(pileIndex)) {
          result += ",";
        }
      }
      result += "\n";
    }
    for (int pileIndex = 0; pileIndex < model.getNumOpenPiles(); pileIndex++) {
      result = result + "O" + (pileIndex + 1) + ":";
      // O1:
      if (model.getOpenCardAt(pileIndex) != null) {
        result += " " + model.getOpenCardAt(pileIndex).toString() + "\n";
        // O1: K♥\n
      } else {
        result += "\n";
        // O1:/n
      }
    }
    for (int pileIndex = 0; pileIndex < model.getNumCascadePiles(); pileIndex++) {
      result = result + "C" + (pileIndex + 1) + ":";

      for (int cardI = 0; cardI < model.getNumCardsInCascadePile(pileIndex); cardI++) {
        result = result + " " + model.getCascadeCardAt(pileIndex, cardI).toString();
        if (cardI + 1 != model.getNumCardsInCascadePile(pileIndex)) {
          result += ",";
        }
      }

      if (pileIndex + 1 != model.getNumCascadePiles()) {
        result += "\n";
      }
    }

    return result;
  }

  @Override
  public void renderBoard() throws IOException {
    this.ap.append(toString());
  }

  @Override
  public void renderMessage(String message) throws IOException {
    if (message == null) {
      return;
    }

    // At this point, the message is not null
    this.ap.append(message);
  }
}
