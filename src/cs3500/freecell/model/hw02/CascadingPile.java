package cs3500.freecell.model.hw02;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the Cascade pile within the Freecell card game.
 */
public class CascadingPile implements IPile<ICard> {

  private final List<ICard> cards;

  /**
   * Constructs a {@code CascadingPile} object.
   */
  public CascadingPile() {
    this.cards = new ArrayList<>();
  }

  @Override
  public void pushCard(ICard card) {
    this.cards.add(card);
  }

  @Override
  public void popCard() {

    if (this.cards.size() == 0) {
      throw new IllegalStateException("there are no cards to remove");
    }

    this.cards.remove(this.cards.size() - 1);
  }

  @Override
  public void move(IPile<ICard> other) {
    // At this point, you know that the card can be removed from the deck.
    // You need to make sure that the card can actually make it to the next pile

    // this is the card to be added onto the other pile.
    ICard card = this.getTopCard();

    if (!other.canBeAdded(card)) {
      throw new IllegalArgumentException(
          "card from cascading pile cannot be added to the new pile");
    }

    other.pushCard(card);
    this.popCard();
  }

  @Override
  public ICard getCard(int cardIndex) {
    if (cardIndex < 0 || cardIndex >= cards.size()) {
      throw new IllegalArgumentException("the index of the card is invalid");
    }

    return cards.get(cardIndex);
  }

  @Override
  public ICard getTopCard() throws IllegalArgumentException {
    if (this.cards.size() == 0) {
      throw new IllegalArgumentException("there are no cards to take. List of cards is empty");
    }

    return this.getCard(this.cards.size() - 1);
  }

  @Override
  public boolean canBeAdded(ICard toBeAddedCard) {

    if (this.cards.size() == 0) {
      return true;
    }

    // At this point, you know the deck has 1 or more cards.

    ICard oldCard = this.getTopCard();

    if (oldCard.getFace() == toBeAddedCard.getFace() && oldCard.getSuite() == toBeAddedCard
        .getSuite()) {
      return true;
    }

    // toBeAddedCard may be added if it is of a different color and is one value less

    return (!toBeAddedCard.sameColor(oldCard)
        && oldCard.getFaceValue() - toBeAddedCard.getFaceValue() == 1);
  }

  @Override
  public boolean canBeRemoved(int cardIndex) {
    // you cannot remove if there are no cards to remove
    // you cannot remove if cardIndex isn't the topmost card on the deck
    return cardIndex >= 0 && cardIndex == cards.size() - 1;
  }

  @Override
  public int getNumCards() {
    return this.cards.size();
  }

  @Override
  public boolean isFull() {
    return this.getNumCards() == 13;
  }

  @Override
  public boolean isPileValid() {
    // the pile is valid if every card alternates in color and the base card's value is 1 greater
    // than the next card.

    if (this.getNumCards() == 0) {
      return true;
    }

    // at this point, you know that there is at least one card.

    ICard baseCard = this.cards.get(0);

    for (int ii = 1; ii < this.cards.size(); ii++) {
      ICard newCard = this.cards.get(ii);
      if (baseCard.sameColor(newCard) || baseCard.getFaceValue() - newCard.getFaceValue() != 1) {
        return false;
      }
      baseCard = newCard;
    }

    return true;
  }

}
