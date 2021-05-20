package cs3500.freecell.model.hw02;

import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.PileType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The {@code SimpleFreecellModel} class represents the Freecell card game.
 */
public class SimpleFreecellModel implements FreecellModel<ICard> {

  private final List<IPile<ICard>> cascadePiles;
  private final List<IPile<ICard>> openPiles;
  private final List<IPile<ICard>> foundationPiles;
  private boolean hasGameStarted;

  /**
   * Constructs an {@code SimpleFreecellModel} object.
   */
  public SimpleFreecellModel() {
    this.cascadePiles = new ArrayList<>();
    this.openPiles = new ArrayList<>();
    this.foundationPiles = new ArrayList<>();
    this.hasGameStarted = false;
  }

  @Override
  public List<ICard> getDeck() {
    List<ICard> new_deck = new ArrayList<>();

    for (Suite suite : Suite.values()) {
      for (Face face : Face.values()) {
        new_deck.add(new Card(suite, face));
      }
    }

    return new_deck;
  }

  /**
   * checks to see if the deck is invalid.
   * @param deck the list of cards making up the deck.
   * @return true if the deck is invlaid and false if the deck is valid.
   */
  private boolean isInvalidDeck(List<ICard> deck) {
    // checks to see if there are 52 cards
    if (deck.size() != 52) {
      return true;
    }

    // checks to see if there are no duplicates
    Set<ICard> deckSet = new HashSet<>(deck);
    if (deckSet.size() < deck.size()) {
      return true;
    }

    // checks to if the card is of an invalid suite or number
    for (ICard card : deck) {
      if (!card.isValid()) {
        return true;
      }
    }

    // at this point, you know that you have a valid deck
    return false;
  }

  @Override
  public void startGame(List<ICard> deck, int numCascadePiles, int numOpenPiles, boolean shuffle)
      throws IllegalArgumentException {

    if (isInvalidDeck(deck)) {
      throw new IllegalArgumentException("deck is invalid");
    }

    if (numCascadePiles < 4) {
      throw new IllegalArgumentException("cascade piles must be 4 or greater");
    }

    if (numOpenPiles < 1) {
      throw new IllegalArgumentException("open piles must be 1 or greater");
    }

    // At this point, the deck is considered valid

    if (shuffle) {
      Collections.shuffle(deck);
    }

    // At this point, the deck is shuffled

    // initialize piles

    // Cascade pile
    for (int ii = 0; ii < numCascadePiles; ii++) {
      this.cascadePiles.add(new CascadingPile());
    }

    // Open piles
    for (int ii = 0; ii < numOpenPiles; ii++) {
      this.openPiles.add(new OpenPile());
    }

    // foundation piles
    for (int ii = 0; ii < 4; ii++) {
      this.foundationPiles.add(new FoundationPile());
    }

    // fill cascade pile
    for (int ii = 0; ii < deck.size(); ii++) {
      IPile<ICard> pile = this.cascadePiles.get(ii % numCascadePiles);

      pile.pushCard(deck.get(ii));
    }

    // Start the game
    this.hasGameStarted = true;
  }

  /**
   * Gets the correct pile from the three existing piles (Cascade, Open, or Foundation).
   * @param pile the type of pile to be taken from (Cascade, Open, or Foundation).
   * @param pileNumber the index of the pile to get.
   * @return the pile specified by pile (from which pile) and pileNumber (index of the pile).
   * @throws IllegalArgumentException when the specified pile index is out of bounds.
   */
  private IPile<ICard> getCorrectPile(PileType pile, int pileNumber)
      throws IllegalArgumentException {
    switch(pile) {
      case OPEN:
        if (pileNumber < 0 || pileNumber >= this.openPiles.size()) {
          throw new IllegalArgumentException("destPileNumber is out of bounds for open pile");
        }
        return this.openPiles.get(pileNumber);
      case CASCADE:
        if (pileNumber < 0 || pileNumber >= this.cascadePiles.size()) {
          throw new IllegalArgumentException("destPileNumber is out of bounds for cascade pile");
        }
        return this.cascadePiles.get(pileNumber);
      case FOUNDATION:
        if (pileNumber < 0 || pileNumber >= this.foundationPiles.size()) {
          throw new IllegalArgumentException("destPileNumber is out of bounds for foundation pile");
        }
        return this.foundationPiles.get(pileNumber);
      default:
        throw new IllegalStateException("Unexpected value: " + pile);
    }
  }

  @Override
  public void move(PileType source, int pileNumber, int cardIndex, PileType destination,
      int destPileNumber) throws IllegalArgumentException, IllegalStateException {

    if (!this.hasGameStarted) {
      throw new IllegalStateException("Game has not begun");
    }

    if (!getCorrectPile(source, pileNumber).canBeRemoved(cardIndex)) {
      throw new IllegalArgumentException("invalid index for card");
    }

    getCorrectPile(source, pileNumber).move(getCorrectPile(destination, destPileNumber));
  }

  @Override
  public boolean isGameOver() {
    // the game is over when
    // - each of the foundation piles is full (i.e. 13 cards each)
    // - it goes from Ace to King
    // - all cards are of the same family
    for (int ii = 0; ii < 4; ii++) {
      IPile<ICard> foundationPile = this.foundationPiles.get(ii);

      if (!foundationPile.isFull()) {
        return false;
      }

      if(!foundationPile.isPileValid()) {
        return false;
      }
    }

    return true;
  }

  @Override
  public int getNumCardsInFoundationPile(int index)
      throws IllegalArgumentException, IllegalStateException {

    if (!this.hasGameStarted) {
      throw new IllegalStateException("game has not yet started");
    }

    if (index < 0 || index >= this.foundationPiles.size()) {
      throw new IllegalArgumentException("no such index for the foundation pile exists");
    }

    return this.foundationPiles.get(index).getNumCards();
  }

  @Override
  public int getNumCascadePiles() {
    if (!this.hasGameStarted) {
      return -1;
    }

    return this.cascadePiles.size();
  }

  @Override
  public int getNumCardsInCascadePile(int index)
      throws IllegalArgumentException, IllegalStateException {

    if (!this.hasGameStarted) {
      throw new IllegalStateException("game has not yet started");
    }

    if (index < 0 || index >= this.cascadePiles.size()) {
      throw new IllegalArgumentException("no such index for the cascade pile exists");
    }

    return this.cascadePiles.get(index).getNumCards();
  }

  @Override
  public int getNumCardsInOpenPile(int index)
      throws IllegalArgumentException, IllegalStateException {

    if (!this.hasGameStarted) {
      throw new IllegalStateException("game has not yet started");
    }

    if (index < 0 || index >= this.openPiles.size()) {
      throw new IllegalArgumentException("no such index for the open pile exists");
    }

    return this.openPiles.get(index).getNumCards();

  }

  @Override
  public int getNumOpenPiles() {
    if (!this.hasGameStarted) {
      return -1;
    }

    return this.openPiles.size();
  }

  @Override
  public ICard getFoundationCardAt(int pileIndex, int cardIndex)
      throws IllegalArgumentException, IllegalStateException {

    if (!this.hasGameStarted) {
      throw new IllegalStateException("game has not yet started");
    }

    if (pileIndex < 0 || pileIndex >= this.foundationPiles.size()) {
      throw new IllegalArgumentException("pileIndex is out of bounds for foundation pile");
    }

    if (cardIndex < 0 || cardIndex >= this.getNumCardsInFoundationPile(pileIndex)) {
      throw new IllegalArgumentException("no such card within foundation pile");
    }

    IPile<ICard> pile = this.foundationPiles.get(pileIndex);
    return pile.getCard(cardIndex);

  }

  @Override
  public ICard getCascadeCardAt(int pileIndex, int cardIndex)
      throws IllegalArgumentException, IllegalStateException {

    if (!this.hasGameStarted) {
      throw new IllegalStateException("game has not yet started");
    }

    if (pileIndex < 0 || pileIndex >= this.cascadePiles.size()) {
      throw new IllegalArgumentException("pileIndex is out of bounds for cascade pile");
    }

    if (cardIndex < 0 || cardIndex >= this.getNumCardsInCascadePile(pileIndex)) {
      throw new IllegalArgumentException("no such card within cascade pile");
    }

    IPile<ICard> pile = this.cascadePiles.get(pileIndex);
    return pile.getCard(cardIndex);

  }

  @Override
  public ICard getOpenCardAt(int pileIndex) throws IllegalArgumentException, IllegalStateException {
    if (!this.hasGameStarted) {
      throw new IllegalStateException("game has not yet started");
    }

    if (pileIndex < 0 || pileIndex >= this.openPiles.size()) {
      throw new IllegalArgumentException("pileIndex is out of bounds for open pile");
    }

    IPile<ICard> pile = this.openPiles.get(pileIndex);
    return pile.getTopCard();
  }
}
