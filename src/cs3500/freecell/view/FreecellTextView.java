package cs3500.freecell.view;

import cs3500.freecell.model.FreecellModel;

/**
 * Represents the ascii view of the card pile within the Freecell card game.
 */
public class FreecellTextView implements FreecellView {

  FreecellModel<?> model;

  public <T> FreecellTextView(FreecellModel<?> model) {
    this.model = model;
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
        if (cardI + 1 == model.getNumCardsInFoundationPile(pileIndex)) {
          result += "\n";
        }
        else {
          result += ",";
        }
      }
    }
    for (int pileIndex = 0; pileIndex < model.getNumOpenPiles(); pileIndex++) {
      result = result + "O" + (pileIndex + 1) + ":";
      if (model.getOpenCardAt(0) != null) {
        result += " " + model.getOpenCardAt(0).toString() + "\n";
      } else {
        result += "\n";
      }
    }
    for (int pileIndex = 0; pileIndex < model.getNumCascadePiles(); pileIndex++) {
      result = result + "C" + (pileIndex + 1) + ":";
      for (int cardI = 0; cardI < model.getNumCardsInCascadePile(pileIndex); cardI++) {
        result = result + " " + model.getCascadeCardAt(pileIndex, cardI).toString();
        if (cardI + 1 == model.getNumCardsInCascadePile(pileIndex)) {
          if (pileIndex + 1 != model.getNumCascadePiles()) {
            result += "\n";
          }
        }
        else {
          result += ",";
        }
      }
    }

    return result;
  }
}
