package cs3500.freecell.model.hw04;

import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.PileType;
import cs3500.freecell.model.hw02.CascadingPile;
import cs3500.freecell.model.hw02.ICard;
import cs3500.freecell.model.hw02.IPile;
import cs3500.freecell.model.hw02.SimpleFreecellModel;

/*
CHANGES TO OLD CODE SO FAR
- changed the private methods and variables to protected within SimpleFreecellModel
-
 */

public final class MultiMoveFreecellModel extends SimpleFreecellModel implements
    FreecellModel<ICard> {

  public MultiMoveFreecellModel() {
    super();
  }

  @Override
  public void move(PileType source, int pileNumber, int cardIndex, PileType destination,
      int destPileNumber) throws IllegalArgumentException, IllegalStateException {

    if (!this.hasGameStarted) {
      throw new IllegalStateException("Game has not begun");
    }

    if (source == PileType.CASCADE && destination == PileType.CASCADE) {
      handleMultiMove(source, pileNumber, cardIndex, destination, destPileNumber);
    } else {

      if (!getCorrectPile(source, pileNumber).canBeRemoved(cardIndex)) {
        throw new IllegalArgumentException("invalid index for card");
      }

      getCorrectPile(source, pileNumber).move(getCorrectPile(destination, destPileNumber));
    }

  }

  protected void handleMultiMove(PileType source, int pileNumber, int cardIndex,
      PileType destination,
      int destPileNumber) {

    // this is where the caveat to multimove exists. Only when these two conditions are true.
    IPile<ICard> sourceCascadePile = getCorrectPile(source, pileNumber);
    IPile<ICard> destinationCascadePile = getCorrectPile(destination, destPileNumber);

    // at this point, you simply have the correct source and destination piles.

    if (cardIndex < 0 || cardIndex >= sourceCascadePile.getNumCards()) {
      throw new IllegalArgumentException("invalid index for card.");
    }

    // at this point, you know that the cardIndex is within range of the source cascade pile.
    // This means that the number of cards you can move is anywhere between
    // sourceCascadePile.getNumCards() and 1.

    int numCardsToMove = sourceCascadePile.getNumCards() - cardIndex;
    int numFreeOpenPiles = getNumFreeOpenPiles();
    int numFreeCascadePiles = getNumFreeCascadePiles();
    if (numCardsToMove > ((numFreeOpenPiles + 1) * ((int) Math.pow(2, numFreeCascadePiles)))) {
      throw new IllegalArgumentException("not possible to move so many cards at once");
    }

    // At this point you know that the cards you want to move are logistically possible.
    // again, they are anywhere between 1 and sourceCascadePile.getNumCards()

    // check to see if the cards form a valid build.
    if (!sourcePileValidBuild(sourceCascadePile, cardIndex)) {
      throw new IllegalArgumentException("source pile build is not valid");
    }

    // check to see if the deepest card can attach onto the next cascade pile card
    if (!connectionValid(sourceCascadePile, destinationCascadePile, cardIndex)) {
      throw new IllegalArgumentException("cannot form build with last card in dest pile");
    }

    // at this point, you are set to move the cards.
    bulkMove(sourceCascadePile, destinationCascadePile, cardIndex);

    }


  protected void bulkMove(IPile<ICard> sourceCascadePile,
      IPile<ICard> destinationCascadePile, int cardIndex) {

    IPile<ICard> tempPile = new CascadingPile();

    int beginningSourcePileSize = sourceCascadePile.getNumCards();

    for (int ii = beginningSourcePileSize - 1; ii >= cardIndex; ii-=1) {
      tempPile.pushCard(sourceCascadePile.getCard(ii));
    }

    // tempPile is now in reverse order, meaning that it has the highest card at the top.

    for(int ii = beginningSourcePileSize - 1; ii >= cardIndex; ii-=1) {
      sourceCascadePile.popCard();
    }

    // at this point, all of the cards to be moved are within tempPile. They have been removed
    // from sourceCascadePile.

    int size = tempPile.getNumCards();
    for (int ii = 0; ii < size; ii++) {
      tempPile.move(destinationCascadePile);
    }
  }

  protected boolean connectionValid(IPile<ICard> sourceCascadePile,
      IPile<ICard> destinationCascadePile, int cardIndex) {

    if (destinationCascadePile.getNumCards() == 0) {
      return true;
    }

    // at this point, you know that there is at least 1 card within the destination cascade pile.

    ICard destTopCard = destinationCascadePile.getTopCard();
    ICard sourceConnectionCard = sourceCascadePile.getCard(cardIndex);

    IPile<ICard> tempPile = new CascadingPile();
    tempPile.pushCard(destTopCard);
    tempPile.pushCard(sourceConnectionCard);

    return tempPile.isPileValid();
  }

  protected boolean sourcePileValidBuild(IPile<ICard> sourceCascadePile, int cardIndex) {
    // this method is only called with a cascade pile passed in.
    // I could create a new Cascade pile
    IPile<ICard> tempPile = new CascadingPile();
    for (int ii = cardIndex; ii < sourceCascadePile.getNumCards(); ii++) {
      tempPile.pushCard(sourceCascadePile.getCard(ii));
    }

    return tempPile.isPileValid();
  }

  protected int getNumFreeCascadePiles() {
    int numFree = 0;
    for (int ii = 0; ii < getNumCascadePiles(); ii++) {
      if (getNumCardsInCascadePile(ii) == 0) {
        numFree++;
      }
    }
    return numFree;
  }

  protected int getNumFreeOpenPiles() {
    int numFree = 0;
    for (int ii = 0; ii < getNumOpenPiles(); ii++) {
      if (getNumCardsInOpenPile(ii) == 0) {
        numFree++;
      }
    }

    return numFree;
  }

}
