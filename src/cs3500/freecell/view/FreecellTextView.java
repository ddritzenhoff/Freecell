package cs3500.freecell.view;

import cs3500.freecell.model.FreecellModel;
import java.io.IOException;

/**
 * Represents the ascii view of the card pile within the Freecell card game.
 */
public class FreecellTextView implements FreecellView {

  private final FreecellModel<?> model;
  private Appendable ap;

  /**
   * Constructs a FreecellModel object.
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
   * Constructs a FreecellModel object.
   *
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

    StringBuilder builder = new StringBuilder();

    // displaying the foundation pile.
    for (int pileIndex = 0; pileIndex < 4; pileIndex++) {
      builder.append("F").append(pileIndex + 1).append(":");

      for (int cardI = 0; cardI < model.getNumCardsInFoundationPile(pileIndex); cardI++) {
        builder.append(" ").append(model.getFoundationCardAt(pileIndex, cardI).toString());
        if (cardI + 1 != model.getNumCardsInFoundationPile(pileIndex)) {
          builder.append(",");
        }
      }
      builder.append("\n");
    }
    for (int pileIndex = 0; pileIndex < model.getNumOpenPiles(); pileIndex++) {
      builder.append("O").append(pileIndex + 1).append(":");
      // O1:
      if (model.getOpenCardAt(pileIndex) != null) {
        builder.append(" ").append(model.getOpenCardAt(pileIndex).toString()).append("\n");
        // O1: K♥\n
      } else {
        builder.append("\n");
        // O1:/n
      }
    }
    for (int pileIndex = 0; pileIndex < model.getNumCascadePiles(); pileIndex++) {
      builder.append("C").append(pileIndex + 1).append(":");
      for (int cardI = 0; cardI < model.getNumCardsInCascadePile(pileIndex); cardI++) {
        builder.append(" ").append(model.getCascadeCardAt(pileIndex, cardI).toString());
        if (cardI + 1 != model.getNumCardsInCascadePile(pileIndex)) {
          builder.append(",");
        }
      }

      if (pileIndex + 1 != model.getNumCascadePiles()) {
        builder.append("\n");
      }
    }

    return builder.toString();
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
