package cs3500.freecell.model.hw02;

/**
 * This interface represents different operations that an IPile model must support to return various
 * aspects of its state.
 *
 * @param <ICard> The Card type.
 */
public interface IPile<ICard> {

  /**
   * Adds an ICard to the top of the pile.
   *
   * @param card represnts the ICard to be added to the pile.
   */
  void pushCard(ICard card);

  /**
   * Removes an ICard from the top of the pile.
   *
   * @throws IllegalStateException when there are no cards to remove.
   */
  void popCard() throws IllegalStateException;

  /**
   * Moves a card from 'this' pile onto the 'other' pile.
   *
   * @param other represents the pile to be added to.
   * @throws IllegalArgumentException when 'this' card cannot be added to 'other' pile.
   */
  void move(IPile<ICard> other) throws IllegalArgumentException;

  /**
   * Returns an ICard at a specific index within the pile.
   *
   * @param cardIndex the index at which the card exists within the pile.
   * @return the ICard at the index specified.
   * @throws IllegalArgumentException when the index of the card is invalid.
   */
  ICard getCard(int cardIndex) throws IllegalArgumentException;

  /**
   * Returns an ICard from the top of the pile.
   *
   * @return the ICard that exists at the top of the pile.
   * @throws IllegalArgumentException when there are no cards to get from the pile (i.e. the pile is
   *                                  empty).
   */
  ICard getTopCard() throws IllegalArgumentException;

  /**
   * Determines if the passed-in ICard can be added to 'this' pile.
   *
   * @param toBeAddedCard is the ICard to be added to 'this' pile.
   * @return true if the ICard parameter can be added to 'this' pile and false otherwise.
   */
  boolean canBeAdded(ICard toBeAddedCard);

  /**
   * Determines if the ICard at the specified index can be removed.
   *
   * @param cardIndex is the index of the ICard to be removed from the pile.
   * @return true if the ICard at the specified index may be removed and false otherwise.
   */
  boolean canBeRemoved(int cardIndex);

  /**
   * Gets the number of ICards within the pile.
   *
   * @return the integer value of the number of ICards in the pile.
   */
  int getNumCards();

  /**
   * Determines if the pile full of ICards is full or not.
   *
   * @return true if the pile cannot accept any more ICards
   */
  boolean isFull();

  /**
   * Checks the sequence of cards obeys the rules set for each pile.
   *
   * @return true if all the rules were followed and false otherwise.
   */
  boolean isPileValid();

}


