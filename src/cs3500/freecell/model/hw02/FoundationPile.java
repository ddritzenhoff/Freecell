package cs3500.freecell.model.hw02;

import java.util.ArrayList;
import java.util.List;

// TODO: QUESTION: A lot of my methods here are the same as in CascadingPile
//  can I create an abstract class for the two classes to extend? How would you test
//  an abstract class?

/**
 * Represents the Foundation pile within the Freecell card game.
 */
public class FoundationPile implements IPile<ICard>{

  private final List<ICard> cards;

  /**
   * Constructs a {@code FoundationPile} object.
   */
  public FoundationPile() {
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
  public void move(IPile<ICard> other) throws IllegalArgumentException{
    // At this point, you know that the card can be removed from the deck.
    // You need to make sure that the card can actually make it to the next pile

    // this is the card to be added onto the other pile.
    ICard card = this.getTopCard();

    if (!other.canBeAdded(card)) {
      throw new IllegalArgumentException("card cannot be added to the new pile");
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
      // Only return true if the card being added to the empty pile is an Ace
      return toBeAddedCard.getFaceValue() == Face.ACE.getValue();
    }

    // At this point, you know the deck has 1 or more cards.

    ICard oldCard = this.getTopCard();

    // toBeAddedCard may be added if it is the same color and is one value greater

    return (toBeAddedCard.sameColor(oldCard) && toBeAddedCard.getFaceValue() - oldCard.getFaceValue() == 1);
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
    // the pile is valid if every card is the same color and the base card's value is 1 less
    // than the next card.

    if (this.getNumCards() == 0) {
      return true;
    }

    // at this point, you know that there is at least one card.

    ICard baseCard = this.cards.get(0);

    for (int ii = 1; ii < this.cards.size(); ii++) {
      ICard newCard = this.cards.get(ii);
      if (!baseCard.sameColor(newCard) || newCard.getFaceValue() - baseCard.getFaceValue() != 1) {
        return false;
      }
      baseCard = newCard;
    }

    return true;
  }
}
