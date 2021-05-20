package cs3500.freecell.model.hw02;

/**
 * This represents the OpenPile within the Freecell game.
 */
public class OpenPile implements IPile<ICard> {

  private ICard card;

  /**
   * Constructs a {@code OpenPile} object.
   */
  public OpenPile() {
    this.card = null;
  }

  @Override
  public void pushCard(ICard card) {

    if (this.card != null) {
      throw new IllegalStateException("there is already a card here");
    }

    this.card = card;
  }

  @Override
  public void popCard() {

    if (this.card == null) {
      throw new IllegalStateException("there isn't a card to remove");
    }

    this.card = null;
  }

  @Override
  public void move(IPile<ICard> other) {

    // this is the card to be added onto the other pile.
    ICard card = this.getTopCard();

    if (!other.canBeAdded(card)) {
      throw new IllegalArgumentException("card from open pile cannot be added to the new pile");
    }

    other.pushCard(card);
    this.popCard();
  }

  @Override
  public ICard getCard(int cardIndex) {

    if (cardIndex != 0) {
      throw new IllegalArgumentException("the index of the card is invalid");
    }

    return this.card;
  }

  @Override
  public ICard getTopCard() throws IllegalArgumentException {
    // TODO: consider adding throws statements here and for getCard
    return this.card;
  }

  @Override
  public boolean canBeAdded(ICard toBeAddedCard) {
    return toBeAddedCard.isValid();
  }

  @Override
  public boolean canBeRemoved(int cardIndex) {
    return cardIndex == 0 && this.card != null;
  }

  @Override
  public int getNumCards() {

    if (this.card != null) {
      return 1;
    }

    return 0;
  }

  @Override
  public boolean isFull() {
    return this.card != null;
  }

  @Override
  public boolean isPileValid() {
    return this.card != null;
  }
}
