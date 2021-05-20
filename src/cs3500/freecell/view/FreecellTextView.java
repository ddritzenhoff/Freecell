package cs3500.freecell.view;

import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.hw02.SimpleFreecellModel;

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
      for (int cardI = 0; cardI < model.getNumCardsInFoundationPile(pileIndex); cardI++) {
        result = result + "F" + (pileIndex + 1) + ":" + model.getFoundationCardAt(pileIndex, cardI)
            .toString() +
            "\n";
      }
    }
    for (int pileIndex = 0; pileIndex < model.getNumOpenPiles(); pileIndex++) {
      result =
          result + "O" + (pileIndex + 1) + ":" + model.getOpenCardAt(pileIndex).toString() + "\n";
    }
    for (int pileIndex = 0; pileIndex < model.getNumCascadePiles(); pileIndex++) {
      for (int cardI = 0; cardI < model.getNumCardsInCascadePile(pileIndex); cardI++) {
        result = result + "C" + (pileIndex + 1) + ":" + model.getCascadeCardAt(pileIndex, cardI).toString();

        // don't include a return statement on the very last card.
        if (pileIndex + 1 != model.getNumCascadePiles() && cardI + 1 != model.getNumCardsInCascadePile(pileIndex)) {
          result = result + "\n";
        }
      }
    }

    return result;
  }
}
