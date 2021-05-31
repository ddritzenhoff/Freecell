package cs3500.freecell.controller;

import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.PileType;
import cs3500.freecell.model.hw02.ICard;
import java.util.List;
import java.util.Objects;

/**
 * Represents a mock SimpleFreecellModel class to test the validity of the input paramters from the
 * controller to the model.
 */
public class MockSimpleFreeCellModel implements FreecellModel<ICard> {

  final StringBuilder log;

  /**
   * Used to build a MockSimpleFreeCellModel object.
   *
   * @param log field used to check the passed in parameters.
   */
  public MockSimpleFreeCellModel(StringBuilder log) {
    this.log = Objects.requireNonNull(log);
  }

  @Override
  public List<ICard> getDeck() {
    return null;
  }

  @Override
  public void startGame(List<ICard> deck, int numCascadePiles, int numOpenPiles, boolean shuffle)
      throws IllegalArgumentException {
    log.append(String
        .format("startGame: numCascadePiles = %d, numOpenPiles = %d, shuffle = %b\n",
            numCascadePiles,
            numOpenPiles, shuffle));
  }

  @Override
  public void move(PileType source, int pileNumber, int cardIndex, PileType destination,
      int destPileNumber) throws IllegalArgumentException, IllegalStateException {
    log.append(String
        .format(
            "move: source = %s, pileNumber = %d, cardIndex = %d, destination = %s,"
                + " destPileNumber = %d\n",
            source.toString(),
            pileNumber, cardIndex, destination.toString(), destPileNumber));
  }

  @Override
  public boolean isGameOver() {
    return false;
  }

  @Override
  public int getNumCardsInFoundationPile(int index)
      throws IllegalArgumentException, IllegalStateException {
    log.append(String.format("getNumCardsInFoundationPile: index = %d\n", index));
    return 0;
  }

  @Override
  public int getNumCascadePiles() {
    return 0;
  }

  @Override
  public int getNumCardsInCascadePile(int index)
      throws IllegalArgumentException, IllegalStateException {
    log.append(String.format("getNumCardsInCascadePile: index = %d\n", index));
    return 0;
  }

  @Override
  public int getNumCardsInOpenPile(int index)
      throws IllegalArgumentException, IllegalStateException {
    log.append(String.format("getNumCardsInOpenPile: index = %d\n", index));
    return 0;
  }

  @Override
  public int getNumOpenPiles() {
    return 0;
  }

  @Override
  public ICard getFoundationCardAt(int pileIndex, int cardIndex)
      throws IllegalArgumentException, IllegalStateException {
    log.append(String
        .format("getFoundationCardAt: pileIndex = %d, cardIndex = %d\n", pileIndex, cardIndex));
    return null;
  }

  @Override
  public ICard getCascadeCardAt(int pileIndex, int cardIndex)
      throws IllegalArgumentException, IllegalStateException {
    log.append(
        String.format("getCascadeCardAt: pileIndex = %d, cardIndex = %d\n", pileIndex, cardIndex));
    return null;
  }

  @Override
  public ICard getOpenCardAt(int pileIndex) throws IllegalArgumentException, IllegalStateException {
    log.append(String.format("getOpenCardAt: pileIndex = %d\n", pileIndex));
    return null;
  }
}
